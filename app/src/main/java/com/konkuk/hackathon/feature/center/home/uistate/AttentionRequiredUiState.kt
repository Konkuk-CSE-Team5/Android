package com.konkuk.hackathon.feature.center.home.uistate

import com.konkuk.hackathon.core.data.model.CallStatusType
import java.time.LocalDate

data class AttentionRequiredUiState(
    val alerts: List<AlertUiModel> = emptyList()
)

// 개별 알림 정보를 담는 UI 모델
data class AlertUiModel(
    val recordId: Int,
    val seniorName: String,
    val volunteerName: String,
    val date: LocalDate, // String -> LocalDate
    val duration: String?, // Nullable 유지
    val status: CallStatusType // String -> Enum
)


