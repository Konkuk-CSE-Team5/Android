package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.request.UpdateRecordRequest
import com.konkuk.hackathon.core.network.response.SeniorUpdateFormResponse
import com.konkuk.hackathon.core.network.response.VolunteerRecordDetailResponse
import com.konkuk.hackathon.core.network.response.VolunteerRecordUpdateFormResponse
import com.konkuk.hackathon.core.network.response.VolunteerRecordsResponse

interface VolunteerRepository {
    suspend fun getVolunteerRecords(): Result<VolunteerRecordsResponse>
    suspend fun getVolunteerRecord(recordId: Long): Result<VolunteerRecordDetailResponse>
    suspend fun getVolunteerRecordUpdateForm(recordId: Long): Result<VolunteerRecordUpdateFormResponse>
    suspend fun updateVolunteerRecord(recordId: Long, request: UpdateRecordRequest): Result<Unit>
}