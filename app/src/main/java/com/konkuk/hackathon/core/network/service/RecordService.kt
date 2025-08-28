package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.response.RecordDetailResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RecordService {
    @GET("records/{recordId}")
    suspend fun getRecordDetail(
        @Path("recordId") recordId: Long
    ): BaseResponse<RecordDetailResponse>
}