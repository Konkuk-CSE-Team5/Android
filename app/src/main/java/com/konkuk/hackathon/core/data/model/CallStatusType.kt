package com.konkuk.hackathon.core.data.model

enum class CallStatusType(
    val label: String,
) {
    COMPLETE("완료"),
    PENDING("미실시"),
    ABSENT("부재중")
}