package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("id")]
)

data class Reminds (
    @PrimaryKey val id : Int,
    var text : String = "",
    var datatime : Long = 0
)