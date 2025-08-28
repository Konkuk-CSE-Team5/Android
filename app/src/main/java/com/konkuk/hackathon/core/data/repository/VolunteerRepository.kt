package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.response.VolunteerRecordsResponse

interface VolunteerRepository {
    suspend fun getVolunteerRecords(): Result<VolunteerRecordsResponse>
}