package com.konkuk.hackathon.feature.center.home.uistate

import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.network.response.AttentionRequiredResponse
import java.time.LocalDate

fun AttentionRequiredResponse.toUiState(): AttentionRequiredUiState {
    return AttentionRequiredUiState(
        alerts = this.alerts.map { it.toUiModel() }
    )
}

// Alert DTO를 AlertUiModel로 변환
fun AttentionRequiredResponse.Alert.toUiModel(): AlertUiModel {
    return AlertUiModel(
        recordId = this.recordId,
        seniorName = this.seniorName,
        volunteerName = this.volunteerName,
        date = LocalDate.parse(this.date), // String -> LocalDate
        duration = this.duration,
        status = CallStatusType.valueOf(this.status) // String -> Enum
    )
}
