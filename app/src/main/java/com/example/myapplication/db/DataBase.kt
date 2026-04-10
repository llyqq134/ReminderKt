package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(
    entities = [Reminds::class],
    version = 1,
    exportSchema = false
)

abstract class DataBase : RoomDatabase() {
    abstract fun dbDAO(): DBDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context?): DataBase {
            return INSTANCE ?: synchronized(this) {
                buildDatabase(context).also {INSTANCE = it}
            }APp

        }

        private fun buildDatabase(context: Context) = databaseBuilder(
            context,
            DataBase::class.java,
            "list_database")
            .fallbackToDestructiveMigration(false)
            .build()
    }
}