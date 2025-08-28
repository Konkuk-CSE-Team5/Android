package com.konkuk.hackathon.core.data.repository

import android.util.Log
import com.konkuk.hackathon.core.network.request.ProfileUpdateRequestDto
import com.konkuk.hackathon.core.network.request.pruned
import com.konkuk.hackathon.core.network.response.VolunteerInfoResponseDto
import com.konkuk.hackathon.core.network.service.SettingService
import retrofit2.HttpException
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val settingService: SettingService
) {
    suspend fun getVolunteerInfo(): Result<VolunteerInfoResponseDto> = runCatching {
        Log.d("ProfileRepository", "getVolunteerInfo() 진입")
        val res = settingService.getVolunteerInfo()
        if (res.isSuccessful) {
            Log.d("ProfileRepository", "getVolunteerInfo: ${res.body()}")
            res.body() ?: throw IllegalStateException("Response body is null")
        } else {
            throw HttpException(res)
        }
    }
    suspend fun updateProfile(request : ProfileUpdateRequestDto) : Result<Unit> =
        runCatching {
            Log.d("UpdateProfileRepository", "updateProfile: $request")
            val response = settingService.updateVolunteerInfo(request.pruned())
            if (!response.isSuccessful) {
                Log.d("UpdateProfileRepository", "updateProfile: ${response.code()}")
                Log.d("UpdateProfileRepository", "updateProfile: ${response.errorBody()?.string()}")
                throw HttpException(response)
            }
            Log.d("UpdateProfileRepository", "updateProfile: Success")
            Unit
        }
}