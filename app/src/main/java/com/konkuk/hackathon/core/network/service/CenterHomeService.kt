package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.response.CenterHomeResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.GET

interface CenterHomeService {
    @GET("/organizations/main")
    suspend fun getCenterHomeData(): BaseResponse<CenterHomeResponse>
}