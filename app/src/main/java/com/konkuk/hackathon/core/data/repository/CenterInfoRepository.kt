package com.konkuk.hackathon.core.data.repository

import android.util.Log
import com.konkuk.hackathon.core.network.request.CenterInfoUpdateRequestDto
import com.konkuk.hackathon.core.network.request.pruned
import com.konkuk.hackathon.core.network.response.CenterInfoResponseDto
import com.konkuk.hackathon.core.network.service.SettingService
import javax.inject.Inject

class CenterInfoRepository @Inject constructor(
    private val settingService : SettingService
){
    suspend fun getOrganizationInfo(): Result<CenterInfoResponseDto> = runCatching {
        Log.d("CenterInfoRepository", "getOrganizationInfo() 진입")
        val res = settingService.getOrganizationInfo()
        if (res.isSuccessful) {
            res.body() ?: throw IllegalStateException("Response body is null")
        } else {
            throw Exception("Error fetching organization info: ${res.code()} ${res.message()}")
        }
    }

    suspend fun updateOrganizationInfo(request: CenterInfoUpdateRequestDto): Result<Unit> = runCatching {
        Log.d("CenterInfoRepository", "updateOrganizationInfo() 진입: $request")
        val response = settingService.updateOrganizationInfo(request.pruned())
        if (!response.isSuccessful) {
            Log.d("CenterInfoRepository", "updateOrganizationInfo() 실패: ${response.code()} ${response.message()}")
            throw Exception("Error updating organization info: ${response.code()} ${response.message()}")
        }
        Log.d("CenterInfoRepository", "updateOrganizationInfo() 성공")
        Unit
    }
}