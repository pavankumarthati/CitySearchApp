package com.masterbit.populationsample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_updated")
data class LastUpdatedEntity(
    @PrimaryKey val country: String,
    val lastUpdated: Long
)