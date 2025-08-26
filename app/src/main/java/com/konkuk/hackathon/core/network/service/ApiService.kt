package com.konkuk.hackathon.core.network.service

import com.konkuk.hackathon.core.network.request.DummyRequest
import com.konkuk.hackathon.core.network.response.DummyResponse
import retrofit2.http.GET

interface ApiService {
    @GET("dummy")
    suspend fun getDummyData(
        request: DummyRequest = DummyRequest(
            message = "Hello, World!",
            status = "success"
        ),
    ): DummyResponse
}
