package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.response.SeniorDetailResponse
import com.konkuk.hackathon.core.network.response.SeniorsResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CenterService {
    
    @GET("organizations/me/seniors")
    suspend fun getSeniors(): BaseResponse<SeniorsResponse>
    
    @GET("organizations/me/seniors/{seniorId}")
    suspend fun getSeniorDetail(
        @Path("seniorId") seniorId: Long
    ): BaseResponse<SeniorDetailResponse>
}