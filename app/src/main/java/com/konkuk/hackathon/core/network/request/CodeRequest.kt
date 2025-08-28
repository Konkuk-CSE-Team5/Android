package com.konkuk.hackathon.core.network.request

import kotlinx.serialization.Serializable

@Serializable
data class CodeRequest(
    val code: Int
)
