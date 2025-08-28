package com.konkuk.hackathon.core.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeniorResponse(
    @SerialName("orgName")
    val orgName: String,
    @SerialName("seniorCode")
    val seniorCode: String
)