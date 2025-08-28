package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.request.SeniorRequest
import com.konkuk.hackathon.core.network.response.SeniorUpdateFormResponse

interface SeniorRepository {
    suspend fun registerSenior(request: SeniorRequest): Result<Unit>

    suspend fun getSeniorUpdateForm(seniorId: Long): Result<SeniorUpdateFormResponse>
}