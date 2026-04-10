package com.example.myapplication.db

import kotlinx.coroutines.flow.Flow

interface DB_Repository {
    fun getReminds(): Flow<List<Reminds>>
    suspend fun insertRemind(remind: Reminds)
    suspend fun deleteRemind(remind: Reminds)
}