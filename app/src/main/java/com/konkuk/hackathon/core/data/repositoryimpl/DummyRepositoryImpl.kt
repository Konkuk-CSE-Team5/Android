package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.data.repository.DummyRepository
import com.konkuk.hackathon.core.network.response.DummyResponse
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.service.ApiService
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : DummyRepository {
    override suspend fun getDummyData(): Result<DummyResponse> = runCatching {
        return apiService.getDummyData().handleBaseResponse()
    }
}