package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.network.ApiService
import com.konkuk.hackathon.core.network.DummyResponse
import com.konkuk.hackathon.core.data.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : DummyRepository {
    override suspend fun getDummyData(): DummyResponse {
        return apiService.getDummyData()
    }
}