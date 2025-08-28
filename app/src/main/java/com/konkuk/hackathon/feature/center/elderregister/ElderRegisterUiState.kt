package com.konkuk.hackathon.feature.center.elderregister

import androidx.compose.foundation.text.input.TextFieldState

data class ElderRegisterUiState(
    val name: TextFieldState = TextFieldState(""),
    val birth: TextFieldState = TextFieldState(""),
    val phoneNumber: TextFieldState = TextFieldState(""),
    val startDate: String = (""),
    val endDate: String = (""),
    val startTime: String = (""),
    val endTime: String = (""),
    val schedules: List<Schedule> = emptyList(),
    val memo: TextFieldState = TextFieldState(""),
) {

    val birthValid: Boolean
        get() = birth.text.length == 8
                && birth.text.all { it.isDigit() }
                && birth.text.toString().toInt() <= 20240000
                && birth.text.toString().toInt() >= 19000101
    val phoneNumberValid: Boolean
        get() = phoneNumber.text.length == 11 && phoneNumber.text.all { it.isDigit() }
    val isValid: Boolean
        get() = name.text.isNotEmpty()
                && birthValid
                && phoneNumberValid
                && startDate.isNotEmpty()
                && endDate.isNotEmpty()
                && startTime.isNotEmpty()
                && endTime.isNotEmpty()
                && schedules.isNotEmpty()
                && memo.text.isNotEmpty()

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
