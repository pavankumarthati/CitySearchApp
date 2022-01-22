package com.masterbit.populationsample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "population")
data class PopulationEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    val city: String,
    val country: String
)