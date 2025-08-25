package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.DummyResponse

interface DummyRepository {
    suspend fun getDummyData(): DummyResponse
}