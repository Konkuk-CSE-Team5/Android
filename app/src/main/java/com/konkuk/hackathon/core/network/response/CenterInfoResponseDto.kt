package com.konkuk.hackathon.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class CenterInfoResponseDto(
    val centerId: String,
    val password : String,
    val centerName : String,
    val managerName : String,
    val phone : String,
)
// 임의의 변수명 -> 수정해야함