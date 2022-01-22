package com.masterbit.populationsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PopulationEntity::class, LastUpdatedEntity::class], version = 1, exportSchema = false)
abstract class PopulationDatabase: RoomDatabase() {

    abstract fun populationDao(): PopulationDao
    abstract fun lastUpdatedDao(): LastUpdatedDao

    companion object {
        @Volatile
        private var INSTANCE: PopulationDatabase? = null
        fun getDatabase(context: Context): PopulationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, PopulationDatabase::class.java, "population_db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}