package com.konkuk.hackathon.core.network.response

import android.os.Message
import kotlinx.serialization.Serializable

@Serializable
data class RegisterElderResponseDto(
    val code : Int,
    val status : Int,
    val message: String,
    val data : CodeDataList
)

@Serializable
data class CodeDataList(
    val seniorCode : String,
    val orgName : String
)
