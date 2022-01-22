package com.masterbit.populationsample

import android.app.Application

class PopulationApp : Application() {
    lateinit var populationDatabase: PopulationDatabase
    override fun onCreate() {
        super.onCreate()
        populationDatabase = PopulationDatabase.getDatabase(this)
    }
}