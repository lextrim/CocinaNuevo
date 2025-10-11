package com.valen.cocinanuevo.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log

object NotificationChannels {
    private const val TAG = "NotifDebug"

    fun createChannelIfNeeded(context: Context, channelId: String, name: String, description: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        try {
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val existing = nm.getNotificationChannel(channelId)
            if (existing == null) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(channelId, name, importance).apply {
                    this.description = description
                    enableLights(true)
                    enableVibration(true)
                }
                nm.createNotificationChannel(channel)
                Log.i(TAG, "Created notification channel $channelId")
            } else {
                Log.i(TAG, "Notification channel $channelId already exists")
            }
        } catch (t: Throwable) {
            Log.w(TAG, "Failed creating notification channel", t)
        }
    }
}
