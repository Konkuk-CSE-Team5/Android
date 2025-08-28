package com.konkuk.hackathon.core.network.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeniorRequest(
    @SerialName("birthday")
    val birthday: String,
    @SerialName("contact")
    val contact: String,
    @SerialName("endDate")
    val endDate: String,
    @SerialName("name")
    val name: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("workDays")
    val workDays: List<String>,
    @SerialName("workEndTime")
    val workEndTime: String,
    @SerialName("workStartTime")
    val workStartTime: String,
    @SerialName("note")
    val note: String? = null
)