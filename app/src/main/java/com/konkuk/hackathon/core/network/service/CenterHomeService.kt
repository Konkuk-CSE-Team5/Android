package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.response.CenterHomeResponse
import com.konkuk.hackathon.core.network.response.ElderStatusResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CenterHomeService {
    @GET("/organizations/main")
    suspend fun getCenterHomeData(): BaseResponse<CenterHomeResponse>

    @GET("seniors/{seniorId}/records")
    suspend fun getElderStatus(
        @Path("seniorId") seniorId: Int
    ): BaseResponse<ElderStatusResponse>
}