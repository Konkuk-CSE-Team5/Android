package com.konkuk.hackathon.feature.center.home.uistate

import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.network.response.ElderStatusResponse
import java.time.LocalDate

fun ElderStatusResponse.toUiState(): ElderStatusUiState {
    return ElderStatusUiState(
        seniorId = this.seniorId,
        seniorName = this.seniorName,
        volunteerName = this.volunteerName,
        matchingStatus = this.matchingStatus,
        summary = this.summary.toUiModel(),
        records = this.records.map { it.toUiModel() }
    )
}

// Summary DTO를 SummaryUiModel로 변환
fun ElderStatusResponse.Summary.toUiModel(): SummaryUiModel {
    return SummaryUiModel(
        totalCalls = this.totalCalls,
        totalDuration = this.totalDuration
    )
}

// Record DTO를 RecordUiModel로 변환
fun ElderStatusResponse.Record.toUiModel(): RecordUiModel {
    return RecordUiModel(
        recordId = this.recordId,
        date = LocalDate.parse(this.date), // String -> LocalDate
        duration = this.duration,
        status = this.status // String -> Enum
    )
}
