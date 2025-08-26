package com.konkuk.hackathon.core.network.request

import kotlinx.serialization.Serializable

@Serializable
data class DummyRequest(
    val message: String,
    val status: String,
)