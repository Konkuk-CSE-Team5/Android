package com.konkuk.hackathon.core.network.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.PowerManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.konkuk.hackathon.MainActivity
import com.konkuk.hackathon.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class FcmService : FirebaseMessagingService() {

    @Inject lateinit var appPreferences: AppPreferences

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        appPreferences.saveFcmToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let { showNotification(it.title, it.body) }
        if (remoteMessage.data.isNotEmpty()) {
            val title = remoteMessage.data["title"] ?: "새로운 알림"
            val body  = remoteMessage.data["body"]  ?: "알림 메시지입니다."
            showNotification(title, body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        val nm = ContextCompat.getSystemService(this, NotificationManager::class.java)!!
        val channelId = "fcm_alert"

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val contentPi = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_bell)
            .setContentTitle(title ?: getString(R.string.app_name))
            .setContentText(body ?: "")
            .setStyle(NotificationCompat.BigTextStyle().bigText(body ?: ""))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(contentPi)

        // 사용자가 “중요 팝업”을 켰고, 현재 화면이 꺼져 있으면 풀스크린으로 띄우기
        val isInteractive = (getSystemService(POWER_SERVICE) as PowerManager).isInteractive
        val userWantsPopup = appPreferences.criticalPopupEnabled()   // ← 주입 필드 사용!
        if (userWantsPopup && !isInteractive) {
            builder.setFullScreenIntent(contentPi, true)
        }

        nm.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}
