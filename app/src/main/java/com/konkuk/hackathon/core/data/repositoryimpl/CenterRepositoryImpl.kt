package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.data.repository.CenterRepository
import com.konkuk.hackathon.core.network.response.SeniorDetailResponse
import com.konkuk.hackathon.core.network.response.SeniorsResponse
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.service.CenterService
import javax.inject.Inject

class CenterRepositoryImpl @Inject constructor(
    private val centerService: CenterService,
) : CenterRepository {

    override suspend fun getSeniors(): Result<SeniorsResponse> = runCatching {
        return centerService.getSeniors().handleBaseResponse()
    }
    
    override suspend fun getSeniorDetail(seniorId: Long): Result<SeniorDetailResponse> = runCatching {
        return centerService.getSeniorDetail(seniorId).handleBaseResponse()
    }
}