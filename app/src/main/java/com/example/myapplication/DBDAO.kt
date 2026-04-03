package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DBDAO {
    @Query("SELECT * FROM Reminds order by datatime")
    fun getReminds(): Flow<List<Reminds>>

    @Insert(entity = Reminds::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemind(remind: Reminds)

    @Delete(entity = Reminds::class)
    suspend fun deleteRemind(remind: Reminds)
}