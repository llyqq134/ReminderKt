package com.example.myapplication.db

import kotlinx.coroutines.flow.Flow

class DBRepository(val dao: DBDAO) : DB_Repository {
    override fun getReminds(): Flow<List<Reminds>> = dao.getReminds()
    override suspend fun insertRemind(remind: Reminds) = dao.insertRemind(remind)
    override suspend fun deleteRemind(remind: Reminds) = dao.deleteRemind(remind)
}