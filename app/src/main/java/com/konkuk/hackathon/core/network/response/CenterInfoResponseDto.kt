package com.konkuk.hackathon.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class CenterInfoResponseDto(
    val status : Int,
    val code : Int,
    val message : String,
    val data : CenterInfoData,
)
// 임의의 변수명 -> 수정해야함
@Serializable
data class CenterInfoData(
    val profile : CenterProfile
)

@Serializable
data class CenterProfile(
    val userId : String,
    val name : String, // 기관명
    val manager : String,
    val managerContact : String,
)

