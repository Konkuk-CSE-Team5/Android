package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.request.UpdateRecordRequest
import com.konkuk.hackathon.core.network.response.SeniorUpdateFormResponse
import com.konkuk.hackathon.core.network.response.VolunteerRecordDetailResponse
import com.konkuk.hackathon.core.network.response.VolunteerRecordUpdateFormResponse
import com.konkuk.hackathon.core.network.response.VolunteerRecordsResponse
import com.konkuk.hackathon.core.network.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface VolunteerService {

    @GET("volunteers/records")
    suspend fun getVolunteerRecords(): BaseResponse<VolunteerRecordsResponse>
    
    @GET("volunteers/records/{recordId}")
    suspend fun getVolunteerRecord(
        @Path("recordId") recordId: Long
    ): BaseResponse<VolunteerRecordDetailResponse>
    
    @GET("volunteers/records/{recordId}/updateForm")
    suspend fun getVolunteerRecordUpdateForm(
        @Path("recordId") recordId: Long
    ): BaseResponse<VolunteerRecordUpdateFormResponse>
    
    @PATCH("volunteers/records/{recordId}")
    suspend fun updateVolunteerRecord(
        @Path("recordId") recordId: Long,
        @Body request: UpdateRecordRequest
    ): BaseResponse<Unit>
}