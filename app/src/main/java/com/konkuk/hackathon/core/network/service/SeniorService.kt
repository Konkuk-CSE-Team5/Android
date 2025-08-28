package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.request.SeniorRequest
import com.konkuk.hackathon.core.network.response.SeniorResponse
import com.konkuk.hackathon.core.network.response.SeniorUpdateFormResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SeniorService {
    @POST("seniors")
    suspend fun registerSenior(
        @Body request: SeniorRequest,
    ): BaseResponse<SeniorResponse>

    @GET("seniors/{seniorId}/updateForm")
    suspend fun getSeniorUpdateForm(
        @Path("seniorId") seniorId: Long
    ): BaseResponse<SeniorUpdateFormResponse>
}