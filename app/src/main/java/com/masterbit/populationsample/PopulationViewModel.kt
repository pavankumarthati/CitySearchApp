package com.masterbit.populationsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.switchOnNext
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.io.IOException

class PopulationViewModel(private val database: PopulationDatabase) : ViewModel() {
    private val subscription = CompositeDisposable()
    private val populationClient = PopulationClient.getPopulationRestClient()
    private val tvSubject = PublishSubject.create<Observable<String>>()

    private val _populationLiveData = MutableLiveData<Data.Success>()
    private val _errorLiveData = MutableLiveData<Data.Error>()
    private val _progressBarLiveData = MutableLiveData<Boolean>(false)


    init {
        loadData()
    }

    private fun populationCitiesFromDisk(country: String): Observable<List<PopulationItemData>> {
        return database.populationDao().queryPopulationCities(country).toObservable()
            .flatMap { list ->
                if (list.isEmpty()) {
                    println("couldn't load from Disk")
                    Observable.empty()
                } else {
                    database.lastUpdatedDao().queryLastUpdated(country = country).toObservable()
                        .filter {
                            val listEmpty = it.isNotEmpty()
                            val currentTime = System.currentTimeMillis()
                            val diff = (currentTime - it[0].lastUpdated < 300000)
                            val res = listEmpty && diff
                            res
                        }
                        .map {
                            println("loading data from disk")
                            list.map {
                                PopulationItemData(it.city, it.country, it.city == it.country)
                            }
                        }
                }
            }
    }

    private fun populationCitiesFromNetwork(country: String): Observable<List<PopulationItemData>> {
        return populationClient.getFilteredPopulationCities(hashMapOf("country" to country)).map {
            println("loading data from network")
            it.data
        }
        .flatMapIterable {
            it
        }
        .groupBy {
            it.country
        }
        .flatMap {
            it.map {
                PopulationItemData(it.city, it.country, false)
            }.startWith(PopulationItemData(it.key!!, it.key!!, true))
        }
        .toList().toObservable()
        .doOnNext {
            println("saving data to disk")

            database.runInTransaction {
                database.populationDao().insertPopulationCities(it.map { it ->
                    PopulationEntity(city = it.name, country = it.country)
                })

                database.lastUpdatedDao().insertLastUpdated(LastUpdatedEntity(country = country, lastUpdated = System.currentTimeMillis()))
            }
        }
        .subscribeOn(Schedulers.io())
    }

    private fun loadData() {
        subscription.add(tvSubject.switchOnNext().distinctUntilChanged()
            .doOnNext {
                _progressBarLiveData.postValue(true)
            }
            .switchMap {
            println("performing fetch request $it")
            Observable.concat(populationCitiesFromDisk(it), populationCitiesFromNetwork(it))
                .map {
                    Data.Success(data = it) as Data
                }
                .onErrorReturn {
                    when(it) {
                        is IOException -> {
                            Data.Error(1, "Unable to fetch data")
                        }
                        else -> {
                            Data.Error(0, "Something went wrong!!")
                        }
                    }
                }
            .firstOrError()
            .subscribeOn(Schedulers.io())
            .toObservable()
        }
            .subscribe({
                _progressBarLiveData.postValue(false)
            when (it) {
                is Data.Success -> {
                    _populationLiveData.postValue(it)
                }
                is Data.Error -> {
                    _errorLiveData.postValue(it)
                }
            }
        }, {
            println("This is serious problem..")
            it.printStackTrace()
        }) )
    }

    fun filterData(tvObservable: Observable<CharSequence>) {
        tvSubject.onNext(tvObservable.map { it.toString() })
    }

    fun getCityPopulation(): LiveData<Data.Success> {
        return _populationLiveData
    }

    fun getProgressBarLiveData(): LiveData<Boolean> {
        return _progressBarLiveData
    }

    fun onError(): LiveData<Data.Error> {
        return _errorLiveData
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }
}

sealed class Data {
    data class Success(val data: List<PopulationItemData>): Data()
    data class Error(val errorId: Long, val msg: String, val action: (() -> Unit)? = null): Data()
}