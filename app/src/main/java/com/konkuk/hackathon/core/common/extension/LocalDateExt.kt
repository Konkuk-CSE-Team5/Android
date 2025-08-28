package com.konkuk.hackathon.core.common.extension

import java.time.LocalDate
import java.util.Locale

fun LocalDate.dayOfWeekKorean(): String {
    return this.dayOfWeek.getDisplayName(
        java.time.format.TextStyle.FULL,
        Locale("ko", "KR")
    )
}