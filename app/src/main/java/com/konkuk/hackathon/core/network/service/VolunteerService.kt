package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.response.VolunteerRecordsResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.GET

interface VolunteerService {

    @GET("volunteers/records")
    suspend fun getVolunteerRecords(): BaseResponse<VolunteerRecordsResponse>
}