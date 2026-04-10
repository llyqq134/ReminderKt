package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.myapplication.db.DBRepository
import com.example.myapplication.db.DataBase
import com.example.myapplication.db.Reminds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AppRepository {
    companion object {
        private var INSTANCE: AppRepository?=null

        fun getInstance() : AppRepository {
            if (INSTANCE == null) {
                INSTANCE = AppRepository()
            }

            return INSTANCE ?:
            throw IllegalStateException("Repo is not initialized")
        }
    }

    val myDB by lazy { DBRepository(DataBase.getDatabase(ReminderApplication.context).dbDAO()) }

    private val myCoroutineScope = CoroutineScope(Dispatchers.Main)

    val listReminds: LiveData<List<Reminds>> = myDB.getReminds().asLiveData()

    fun addRemind(remind: Reminds) {
        myCoroutineScope.launch {
            myDB.insertRemind(remind)
        }
    }

    fun deleteRemind(remind: Reminds) {
        myCoroutineScope.launch {
            myDB.deleteRemind(remind)
        }
    }

    fun onDestroy() {
        myCoroutineScope.cancel()
    }
}