package com.example.myapplication

import android.app.AlarmManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.db.Reminds

class MainViewModel : ViewModel() {
    lateinit var alarmManager: AlarmManager

    var date by mutableStateOf("")
    var time by mutableStateOf("")
    var text by mutableStateOf("")

    val p = AppRepository.getInstance()
    val d = p.myDB
    val t = p.listReminds
    val remindList = AppRepository.getInstance().listReminds

    fun addReminder(context: Context) {
        if (date.isEmpty() && time.isEmpty())
            return Toast.makeText(context, "select date or choose time!",
                Toast.LENGTH_LONG).show()
        if (text.isEmpty())
            return Toast.makeText(context, "Enter a reminder",
                Toast.LENGTH_LONG).show()
        if (date.isEmpty())
            date = Utils.getCurrentDate()
        if (time.isEmpty())
            time = "08:00"

        val reminder = Reminds(Utils.generateID(), text,
            Utils.DateTimeToMillisec(date, time))

        AppRepository.getInstance().addRemind(reminder)
        scheduleNotification(context, reminder)

        text = ""
        date = ""
        time = ""

        Toast.makeText(context, "Reminder has been created!", Toast.LENGTH_LONG).show()
    }

    fun deleteReminder(context: Context, reminder: Reminds) {
        AppRepository.getInstance().deleteRemind(reminder)
        alarmManager.cancel(Utils.geetPendingIntent(context, reminder.id, reminder.text))
        Toast.makeText(context, "Reminder has been removed", Toast.LENGTH_LONG).show()
    }

    fun scheduleNotification(context: Context, reminder: Reminds) {
        val pendingIntent = Utils.geetPendingIntent(context, reminder.id, reminder.text)

        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.S || alarmManager.canScheduleExactAlarms())
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminder.datatime, pendingIntent)
    }
}