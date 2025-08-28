package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.request.LoginRequest
import com.konkuk.hackathon.core.network.request.OrganizationSignUpRequest
import com.konkuk.hackathon.core.network.request.VolunteerSignUpRequest
import com.konkuk.hackathon.core.network.response.LoginResponse
import com.konkuk.hackathon.core.network.response.SignUpResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BaseResponse<LoginResponse>

    @POST("volunteers")
    suspend fun signUpVolunteer(
        @Body request: VolunteerSignUpRequest
    ): BaseResponse<SignUpResponse>

    @POST("organizations")
    suspend fun signUpOrganization(
        @Body request: OrganizationSignUpRequest
    ): BaseResponse<SignUpResponse>
}