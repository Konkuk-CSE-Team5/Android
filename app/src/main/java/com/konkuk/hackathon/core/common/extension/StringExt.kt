package com.konkuk.hackathon.core.common.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toDateFormat(): String {
    val parsed = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyyMMdd"))
    return parsed.format(DateTimeFormatter.ISO_DATE)
}

fun String.toPhoneFormat(): String {
    return if (length == 11) {
        "${substring(0,3)}-${substring(3,7)}-${substring(7)}"
    } else this
}