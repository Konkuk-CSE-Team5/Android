package com.konkuk.hackathon.core.data.repositoryimpl

import android.util.Log
import com.konkuk.hackathon.core.data.repository.SeniorRepository
import com.konkuk.hackathon.core.network.request.SeniorRequest
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.service.SeniorService
import javax.inject.Inject

class SeniorRepositoryImpl @Inject constructor(
    private val seniorService: SeniorService,
) : SeniorRepository {
    override suspend fun registerSenior(request: SeniorRequest): Result<Unit> = runCatching {
        val code = seniorService.registerSenior(request).handleBaseResponse()
        Log.d("SeniorRepositoryImpl", "registerSenior: $code")
    }
}