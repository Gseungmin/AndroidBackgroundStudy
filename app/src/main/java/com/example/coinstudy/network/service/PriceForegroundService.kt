package com.example.coinstudy.network.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.coinstudy.MainActivity
import com.example.coinstudy.R

/**
 * foreground service 띄울 준비
 * */
class PriceForegroundService : Service() {

    private val NOTIFICATION_ID = 10000

    override fun onCreate() {
        super.onCreate()
    }

    /**
     * Start 및 Stop에 대한 처리
     * */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action) {

            "START" -> {
                startForeground(NOTIFICATION_ID, makeNotification())
            }

            "STOP" -> {

            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    fun makeNotification() : Notification {

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent : PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
            .setContentTitle("title")
            .setContentText("content")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "name"
            val descriptionText = "descriptionText"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }

            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        return builder.build()
    }
}