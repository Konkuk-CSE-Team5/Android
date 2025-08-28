package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.request.CodeRequest
import com.konkuk.hackathon.core.network.request.PostRecordRequest
import com.konkuk.hackathon.core.network.response.AttentionRequiredResponse
import com.konkuk.hackathon.core.network.response.PostCodeResponse
import com.konkuk.hackathon.core.network.response.VolunteerHomeResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import com.konkuk.hackathon.core.network.response.base.NullableResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VolunteerHomeService {
    @GET("volunteers/main")
    suspend fun getVolunteerHome(): BaseResponse<VolunteerHomeResponse>

    @POST("/volunteers/codes")
    suspend fun postCode(
        @Body request: CodeRequest
    ): BaseResponse<PostCodeResponse>

    @POST("/volunteers/records")
    suspend fun postRecord(
        @Body request: PostRecordRequest
    ): NullableResponse

    @GET("/organizations/me/seniors/attention-needed")
    suspend fun getAttentionRequired(): BaseResponse<AttentionRequiredResponse>
}