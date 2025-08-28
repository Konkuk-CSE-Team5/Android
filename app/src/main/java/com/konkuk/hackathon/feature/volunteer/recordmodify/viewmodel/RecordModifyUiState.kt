package com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel

import androidx.compose.foundation.text.input.TextFieldState
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.Elder

data class RecordModifyUiState(
    val name: String = "",
    val callTime: String = "",
    val duration: String = "",
    val hasCalled: Boolean = false, // false = 부재중 , true = 수행
    val healthCondition: HealthCondition = HealthCondition.GOOD,
    val mindCondition: MindCondition = MindCondition.GOOD,
    val memo: TextFieldState = TextFieldState(""),
)

enum class HealthCondition(val label: String) {
    GOOD("좋음"),
    SOSO("보통"),
    BAD("나쁨")
}

enum class MindCondition(val label: String, val emoji: String) {
    GOOD("좋음", "\uD83D\uDE0A"),
    SOSO("보통", "\uD83D\uDE10"),
    BAD("나쁨", "☹\uFE0F")
}

