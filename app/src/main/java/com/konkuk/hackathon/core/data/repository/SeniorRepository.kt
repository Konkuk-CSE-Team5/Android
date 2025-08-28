package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.request.SeniorRequest

interface SeniorRepository {
    suspend fun registerSenior(request: SeniorRequest): Result<Unit>
}