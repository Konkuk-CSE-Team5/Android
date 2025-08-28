package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.request.ProfileUpdateRequestDto
import com.konkuk.hackathon.core.network.response.VolunteerInfoResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface SettingService {
    // 설정 화면 조회(봉사자)
    @GET("volunteers/me")
    suspend fun getVolunteerInfo(): Response<VolunteerInfoResponseDto>

    @PATCH("volunteers/me")
    suspend fun updateVolunteerInfo(
        @Body request: ProfileUpdateRequestDto
    ): Response<Unit>

    // 설정 화면 조회(보호자)
    @GET("organizations/me")
    suspend fun getOrganizationInfo(

    ): Response<VolunteerInfoResponseDto>

}