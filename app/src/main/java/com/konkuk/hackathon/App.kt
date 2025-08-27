package com.konkuk.hackathon

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val nm = ContextCompat.getSystemService(this, NotificationManager::class.java)
        val channel = NotificationChannel(
            getString(R.string.default_notification_channel_id),
            getString(R.string.default_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = getString(R.string.default_notification_channel_desc)
        }
        nm?.createNotificationChannel(channel)

    FirebaseMessaging.getInstance().token
    .addOnCompleteListener { task ->
        if (!task.isSuccessful) {
            Log.w("FCM", "토큰 가져오기 실패", task.exception)
            return@addOnCompleteListener
        }
        val token = task.result
        Log.d("FCM", "token=$token")
        // TODO: 필요하면 서버에 업로드
    }
    }
}
