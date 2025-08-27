package com.konkuk.hackathon.core.network.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    // 새 토큰이 생성될 때 호출됩니다.
    override fun onNewToken(token: String) {
        Log.d("FCM_Token", "Refreshed token: $token")
        // 서버에 토큰을 보내거나 저장하는 로직을 구현하세요.
    }

    // 메시지를 수신할 때 호출됩니다.
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM_Message", "From: ${remoteMessage.from}")

        // 메시지에 데이터 페이로드가 포함되어 있는지 확인
        remoteMessage.data.isNotEmpty().let {
            Log.d("FCM_Data", "Message data payload: " + remoteMessage.data)
        }

        // 메시지에 알림 페이로드가 포함되어 있는지 확인
        remoteMessage.notification?.let {
            Log.d("FCM_Notification", "Message Notification Title: ${it.title}")
            Log.d("FCM_Notification", "Message Notification Body: ${it.body}")
            // 알림을 표시하는 로직을 구현하세요. (예: NotificationManager)
            showNotification(it.title, it.body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        // 알림 채널 및 알림을 생성하고 표시하는 로직
    }
}