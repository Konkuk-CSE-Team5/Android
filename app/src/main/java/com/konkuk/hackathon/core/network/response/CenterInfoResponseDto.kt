package com.konkuk.hackathon.core.network.response

data class CenterInfoResponseDto(
    val centerId: Int,
    val name: String,
    val address: String,
    val contact: String
)
