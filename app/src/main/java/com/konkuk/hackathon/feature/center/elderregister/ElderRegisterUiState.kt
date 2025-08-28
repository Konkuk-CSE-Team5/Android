package com.konkuk.hackathon.feature.center.elderregister

import androidx.compose.foundation.text.input.TextFieldState

data class ElderRegisterUiState(
    val name: TextFieldState = TextFieldState(""),
    val birth: TextFieldState = TextFieldState(""),
    val phoneNumber: TextFieldState = TextFieldState(""),
    val startDate: String = (""),
    val endDate: String = (""),
    val code: TextFieldState = TextFieldState(""),
    val schedules: List<Schedule> = emptyList(),
    val memo: TextFieldState = TextFieldState(""),
) {
    enum class Schedule(val displayName: String) {
        MON("월"),
        TUE("화"),
        WED("수"),
        THU("목"),
        FRI("금"),
        SAT("토"),
        SUN("일"),
    }
}
