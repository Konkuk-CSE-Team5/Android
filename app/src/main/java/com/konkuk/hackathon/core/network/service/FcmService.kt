package com.konkuk.hackathon.core.network.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.vision.clearcut.LogUtils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FcmService : FirebaseMessagingService() {
    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        LogUtils.d("new token : $token")
        appPreferences.saveFcmToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        LogUtils.d("From: ${remoteMessage.from}")

        // 알림 메시지인 경우
        if (remoteMessage.notification != null) {
            LogUtils.d("Message Notification Body: ${remoteMessage.notification?.body}")
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            FCM_NOTIFICATION_NAME,
            "Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

        val notificationBuilder = NotificationCompat.Builder(this, FCM_NOTIFICATION_NAME)
            .setContentTitle(title ?: "Promotion Title")
            .setContentText(body ?: "Promotion Message")
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setAutoCancel(true)

        notificationManager.notify(FCM_NOTIFICATION_ID, notificationBuilder.build())
    }
}