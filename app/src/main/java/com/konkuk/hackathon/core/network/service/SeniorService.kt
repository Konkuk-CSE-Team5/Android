package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.request.SeniorRequest
import com.konkuk.hackathon.core.network.response.SeniorResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SeniorService {
    @POST("seniors")
    suspend fun registerSenior(
        @Body request: SeniorRequest,
    ): BaseResponse<SeniorResponse>
}