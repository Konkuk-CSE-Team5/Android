package com.konkuk.hackathon.feature.volunteer.home.uistate

data class RecordSubmitUiState(
    val selectedPerformanceState: PerformanceState = PerformanceState.PERFORMED,
    val selectedHealthState: HealthState? = null,
    val selectedPsychologicalState: PsychologicalState? = null,
    val opinionText: String = ""
)

enum class PerformanceState(val displayName: String) {
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