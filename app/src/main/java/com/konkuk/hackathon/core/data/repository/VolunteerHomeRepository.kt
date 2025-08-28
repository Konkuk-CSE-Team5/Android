package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.network.response.PostCodeResponse
import com.konkuk.hackathon.core.network.response.VolunteerHomeResponse
import com.konkuk.hackathon.feature.volunteer.home.uistate.CallLogData

interface VolunteerHomeRepository {
    suspend fun getVolunteerHome(): Result<VolunteerHomeResponse>

    suspend fun postCode(code: Int): Result<PostCodeResponse>

    suspend fun postRecord(
        health: String?,
        mentality: String?,
        opinion: String,
        seniorId: Int,
        status: CallStatusType,
        callList: List<CallLogData>
    ): Result<Unit>
}