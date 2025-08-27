package com.konkuk.hackathon.core.network.service

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class AppPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "app_preferences"
        private const val FCM_TOKEN_KEY = "fcm_token"
    }

    // FCM 토큰을 저장하는 메서드
    fun saveFcmToken(token: String) {
        sharedPreferences.edit { putString(FCM_TOKEN_KEY, token) }
    }

    // 저장된 FCM 토큰을 가져오는 메서드 (선택 사항)
    fun getFcmToken(): String? {
        return sharedPreferences.getString(FCM_TOKEN_KEY, null)
    }
}