package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.data.repository.CenterHomeRepository
import com.konkuk.hackathon.core.network.response.CenterHomeResponse
import com.konkuk.hackathon.core.network.response.ElderStatusResponse
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.service.CenterHomeService
import javax.inject.Inject

class CenterHomeRepositoryImpl @Inject constructor(
    private val centerHomeService: CenterHomeService
) : CenterHomeRepository {
    override suspend fun getCenterHome(): Result<CenterHomeResponse> = runCatching {
        return centerHomeService.getCenterHomeData().handleBaseResponse()
    }

    override suspend fun getElderStatus(elderId: Int): Result<ElderStatusResponse> {
        return centerHomeService.getElderStatus(elderId).handleBaseResponse()
    }
}