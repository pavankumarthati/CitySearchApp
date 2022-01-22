package com.masterbit.populationsample

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PopulationViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PopulationViewModel((application as PopulationApp).populationDatabase) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val populationRv = findViewById<RecyclerView>(R.id.populationRv)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        populationRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        populationRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        populationRv.adapter = PopulationItemAdapter()

        val tvObservable = findViewById<TextView>(R.id.tv).textChanges().debounce(600, TimeUnit.MILLISECONDS)

        viewModel.filterData(tvObservable.filter { it.isNotBlank() })

        viewModel.getCityPopulation().observe(this) {
            (populationRv.adapter as PopulationItemAdapter).submitList(it.data)
        }

        viewModel.getProgressBarLiveData().observe(this) {
            progressBar.isVisible = it
        }

        viewModel.onError().observe(this) {
            Toast.makeText(this, it.msg, Toast.LENGTH_LONG).show()
        }
    }
}