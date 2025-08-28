package com.konkuk.hackathon.feature.volunteer.home.uistate

import java.time.LocalDateTime

data class RecordSubmitUiState(
    val selectedHealthState: HealthState? = null,
    val selectedPsychologicalState: PsychologicalState? = null,
    val opinionText: String = "",
    val callLogList: List<CallLogData> = emptyList()
)

enum class CallPerformanceState(val displayName: String) {
    ABSENT("부재중"),
    PERFORMED("수행")
}

enum class HealthState(val displayName: String) {
    GOOD("좋음"),
    NORMAL("보통"),
    BAD("나쁨")
}

enum class PsychologicalState(val displayName: String, val emoji: String) {
    GOOD("좋음", "😄"),
    NORMAL("보통", "😐"),
    BAD("나쁨", "😟")
}

data class CallLogData(
    val dateTime: LocalDateTime,
    val callTime: String, // 00:11:22 형식의 스트링
    val callPerformanceState: CallPerformanceState
)