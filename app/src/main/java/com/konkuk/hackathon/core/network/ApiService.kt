package com.konkuk.hackathon.core.network

import retrofit2.http.GET

interface ApiService {
    @GET("dummy")
    suspend fun getDummyData(): DummyResponse
}

data class DummyResponse(
    val message: String,
    val status: String
)