package com.masterbit.populationsample

import android.os.Bundle
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
import com.masterbit.populationsample.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PopulationViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PopulationViewModel((application as PopulationApp).populationDatabase) as T
            }
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.populationRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.populationRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.populationRv.adapter = PopulationItemAdapter()

        val tvObservable = findViewById<TextView>(R.id.tv).textChanges().debounce(600, TimeUnit.MILLISECONDS)

        viewModel.filterData(tvObservable.filter { it.isNotBlank() })

        viewModel.getCityPopulation().observe(this) {
            (binding.populationRv.adapter as PopulationItemAdapter).submitList(it.data)
        }

        viewModel.getProgressBarLiveData().observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.onError().observe(this) {
            Toast.makeText(this, it.msg, Toast.LENGTH_LONG).show()
        }
        reportFullyDrawn()
    }
}