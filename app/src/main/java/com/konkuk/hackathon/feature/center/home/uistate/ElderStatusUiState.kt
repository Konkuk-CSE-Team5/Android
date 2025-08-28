package com.konkuk.hackathon.feature.center.home.uistate

import com.konkuk.hackathon.core.data.model.CallStatusType
import java.time.LocalDate

data class ElderStatusUiState(
    val seniorId: Int = 0,
    val seniorName: String = "",
    val volunteerName: String = "",
    val matchingStatus: String = "",
    val summary: SummaryUiModel = SummaryUiModel(),
    val records: List<RecordUiModel> = emptyList()
)

// 요약 정보 UI 모델
data class SummaryUiModel(
    val totalCalls: Int = 0,
    val totalDuration: String = "00:00:00"
)

// 개별 통화 기록 UI 모델
data class RecordUiModel(
    val recordId: Int,
    val date: LocalDate,
    val duration: String,
    val status: CallStatusType
)

