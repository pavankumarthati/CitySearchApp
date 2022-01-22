package com.masterbit.populationsample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface PopulationDao {
    @Query("SELECT * FROM population")
    fun queryAllPopulationCities(): Single<List<PopulationEntity>>

    @Query("SELECT * FROM population WHERE lower(country)=lower(:country)")
    fun queryPopulationCities(country: String): Single<List<PopulationEntity>>

    @Insert
    fun insertPopulationCities(populationCities: List<PopulationEntity>)

    @Query("DELETE FROM population")
    fun deleteAll()
}