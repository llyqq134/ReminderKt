package com.example.myapplication

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.UiContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.jvm.java
import kotlin.math.roundToInt

class Utils {
    companion object {
        fun generateID(): Int {
            return (Math.random() * 1000000).roundToInt()
        }

        fun checkZero(num: Int): String {
            return if (num<10) "0$num" else num.toString()
        }

        fun getCurrentDate() :String {
            return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        }

        @SuppressLint("SimpleDateFormat")
        fun DateTimeToMillisec(date: String, time: String) :Long {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
            return dateFormat.parse("$date $time")!!.time
        }

        @SuppressLint("SimpleDateFormat")
        fun MillisecondsToDate(milliseconds: Long): String {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy")
            val date = Date(milliseconds)
            return dateFormat.format(date)
        }

        @SuppressLint("SimpleDateFormat")
        fun MillisecondsToTime(milliseconds: Long): String {
            val dateFormat = SimpleDateFormat("HH:mm")
            val date = Date(milliseconds)
            return dateFormat.format(date)
        }

        fun geetPendingIntent(context: Context, id: Int, text : String): PendingIntent {
            val intent = Intent(context, ReminderBroadcastReceiver::class.java).apply {
                putExtra("text", text)
                putExtra("id", id)
            }
            return PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }
    }
}