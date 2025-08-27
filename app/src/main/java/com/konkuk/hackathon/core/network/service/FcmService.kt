package com.konkuk.hackathon.core.network.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.konkuk.hackathon.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class FcmService : FirebaseMessagingService() {

    // AppPreferences는 예시이며, 토큰 저장 로직에 맞게 수정 필요
    @Inject
    lateinit var appPreferences: AppPreferences


     /*FCM 토큰이 새로 생성되거나 갱신될 때 호출.
     이 토큰은 사용자별 푸시 알림을 보내는 데 사용되므로 서버에 저장해야 함.*/
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM_SERVICE", "새로운 토큰: $token")
        // 서버에 토큰을 보내거나 로컬에 저장하는 로직을 구현합니다.
        appPreferences.saveFcmToken(token)
    }

    //푸시 알림 메시지를 수신할 때 호출
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // notification payload
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }

        // data-only payload
        if (remoteMessage.data.isNotEmpty()) {
            val title = remoteMessage.data["title"] ?: "새로운 알림"
            val body = remoteMessage.data["body"] ?: "알림 메시지입니다."
            showNotification(title, body)
        }
    }

    //알림을 생성하고 표시
    private fun showNotification(title: String?, body: String?) {
        val nm = ContextCompat.getSystemService(this, NotificationManager::class.java)!!
        // 채널 ID는 strings.xml 값과 동일해야 함
        val channelId = getString(R.string.default_notification_channel_id)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title ?: "새로운 알림")
            .setContentText(body ?: "알림 메시지입니다.")
            .setSmallIcon(android.R.drawable.ic_dialog_info) // 앱 전용 흰색 아이콘 권장
            .setAutoCancel(true)
            .build()

        nm.notify(System.currentTimeMillis().toInt(), notification)
    }
}