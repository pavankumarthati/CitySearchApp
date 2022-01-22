package com.masterbit.populationsample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface LastUpdatedDao {
    @Query("SELECT * FROM last_updated where lower(country)=lower(:country)")
    fun queryLastUpdated(country: String): Single<List<LastUpdatedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastUpdated(lastUpdated: LastUpdatedEntity)

    @Query("DELETE FROM last_updated")
    fun deleteAll()
}