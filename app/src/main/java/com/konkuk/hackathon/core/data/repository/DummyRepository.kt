package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.response.DummyResponse

interface DummyRepository {
    suspend fun getDummyData(): Result<DummyResponse>

}