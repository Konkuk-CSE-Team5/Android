package com.konkuk.hackathon.core.data.model

enum class CallStatusType(
    val label: String,
) {
    COMPLETE("완료"),
    PENDING("실시 전"),
    ABSENT("부재중"),
    NOT_CONDUCTED("미실시"),
}