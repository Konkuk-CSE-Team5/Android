package com.konkuk.hackathon.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class DummyResponse(
    val message: String,
    val status: String
)