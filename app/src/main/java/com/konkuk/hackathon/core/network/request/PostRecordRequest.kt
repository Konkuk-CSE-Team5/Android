package com.konkuk.hackathon.core.network.request


import com.konkuk.hackathon.core.data.model.CallStatusType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostRecordRequest(
    @SerialName("callHistory")
    val callHistory: List<CallHistory>,
    @SerialName("health")
    val health: String?,
    @SerialName("mentality")
    val mentality: String?,
    @SerialName("opinion")
    val opinion: String,
    @SerialName("seniorId")
    val seniorId: Int,
    @SerialName("status")
    val status: CallStatusType
) {
    @Serializable
    data class CallHistory(
        @SerialName("callTime")
        val callTime: String,
        @SerialName("dateTime")
        val dateTime: String
    )
}