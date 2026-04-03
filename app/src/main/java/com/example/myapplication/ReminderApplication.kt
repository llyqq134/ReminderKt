package com.example.myapplication

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.os.Build
import android.provider.Settings
import androidx.room.processor.Context

class ReminderApplication: Application() {
    companion object {
        const val channelName = "myReminder"
        const val channelDecryption = "channel for my reminder"
        const val channelId = "myReminderChannelId"

        private var instance : ReminderApplication? = null
        val context get () = applicationContext()

        private fun applicationContext() : android.content.Context? {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName,
                NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    description = channelDecryption
                    setSound(Settings.System.DEFAULT_NOTIFICATION_URI,
                        AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build())
                }

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}