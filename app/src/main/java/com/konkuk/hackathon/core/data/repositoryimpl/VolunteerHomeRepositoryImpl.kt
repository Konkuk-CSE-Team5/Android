package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.data.repository.VolunteerHomeRepository
import com.konkuk.hackathon.core.network.request.CodeRequest
import com.konkuk.hackathon.core.network.response.PostCodeResponse
import com.konkuk.hackathon.core.network.response.VolunteerHomeResponse
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.response.base.handleNullableResponse
import com.konkuk.hackathon.core.network.service.VolunteerHomeService
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
}