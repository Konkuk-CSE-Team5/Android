package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.data.repository.VolunteerHomeRepository
import com.konkuk.hackathon.core.network.request.CodeRequest
import com.konkuk.hackathon.core.network.request.PostRecordRequest
import com.konkuk.hackathon.core.network.response.AttentionRequiredResponse
import com.konkuk.hackathon.core.network.response.PostCodeResponse
import com.konkuk.hackathon.core.network.response.VolunteerHomeResponse
import com.konkuk.hackathon.core.network.response.base.NullableResponse
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.response.base.handleNullableResponse
import com.konkuk.hackathon.core.network.service.VolunteerHomeService
import com.konkuk.hackathon.feature.volunteer.home.uistate.CallLogData
import java.time.format.DateTimeFormatter
import kotlin.Result
import javax.inject.Inject

class VolunteerHomeRepositoryImpl @Inject constructor(
    private val volunteerHomeService: VolunteerHomeService
) : VolunteerHomeRepository {
    override suspend fun getVolunteerHome(): Result<VolunteerHomeResponse> = runCatching {
        return volunteerHomeService.getVolunteerHome().handleBaseResponse()
    }

    override suspend fun postCode(code: Int): Result<PostCodeResponse> = runCatching {
        return volunteerHomeService.postCode(CodeRequest(code)).handleBaseResponse()
    }

    override suspend fun postRecord(
        health: String?,
        mentality: String?,
        opinion: String,
        seniorId: Int,
        status: CallStatusType,
        callList: List<CallLogData>
    ): Result<Unit> = runCatching {
        return volunteerHomeService.postRecord(
            request = PostRecordRequest(
                health = health,
                mentality = mentality,
                opinion = opinion,
                seniorId = seniorId,
                status = status,
                callHistory = callList.map {
                    PostRecordRequest.CallHistory(
                        callTime = it.callTime,
                        dateTime = it.dateTime.toString()
                    )
                })
        ).handleNullableResponse()
    }

    override suspend fun getAttentionRequired(): Result<AttentionRequiredResponse> = runCatching {

        return volunteerHomeService.getAttentionRequired().handleBaseResponse()
    }


}