package com.konkuk.hackathon.feature.volunteer.record.viewmodel

data class RecordUiState(
    val elders: List<Elder> = emptyList(),
)

data class Elder(
    val id: Long = 0L,
    val callCount: Int = 0,
    val totalTime: String = "", // 서버 리스폰스 형식에 맞추기
    val type: ElderType = ElderType.ONGOING,
    val name: String = "",
    val records: List<CallRecord> = emptyList(),
)

data class CallRecord(
    val id: Long = 0L,
    val time: String = "",
    val duration: String = "", // 서버 리스폰스 형식에 맞추기
    val recordType: RecordType = RecordType.CALL_NOT_MADE,
)

enum class RecordType(val label: String) {
    SUCCESS("완료"),
    CALL_NOT_MADE("미실시"),
    ABSENCE("부재중"),
}

enum class ElderType(val label: String) { // 어르신 상태
    ONGOING("진행중"),
    COMPLETED("진행완료"),
}
