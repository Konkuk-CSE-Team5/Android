package com.konkuk.hackathon.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateVolunteerRecordRequest(
    @SerialName("status") val status: String,
    @SerialName("health") val health: String,
    @SerialName("mentality") val mentality: String,
    @SerialName("opinion") val opinion: String
)

enum class RecordStatus(val value: String) {
    COMPLETE("COMPLETE"),
    ABSENT("ABSENT");
    
    companion object {
        fun fromValue(value: String): RecordStatus? = entries.find { it.value == value }
    }
}

enum class HealthStatus(val value: String) {
    GOOD("GOOD"),
    NORMAL("NORMAL"),
    BAD("BAD");
    
    companion object {
        fun fromValue(value: String): HealthStatus? = entries.find { it.value == value }
    }
}

enum class MentalityStatus(val value: String) {
    GOOD("GOOD"),
    NORMAL("NORMAL"),
    BAD("BAD");
    
    companion object {
        fun fromValue(value: String): MentalityStatus? = entries.find { it.value == value }
    }
}

@Serializable
data class UpdateRecordRequest(
    @SerialName("status") val status: String,
    @SerialName("health") val health: String,
    @SerialName("mentality") val mentality: String,
    @SerialName("opinion") val opinion: String
)