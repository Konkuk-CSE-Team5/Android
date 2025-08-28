package com.konkuk.hackathon.feature.volunteer.recordall

import com.konkuk.hackathon.feature.volunteer.record.viewmodel.CallRecord

data class RecordAllUiState(
    val name: String = "",
    val records: List<CallRecord> = emptyList(),
)

/* CallRecord
    val id: Long = 0L,
    val time: String = "",
    val duration: String = "", // 서버 리스폰스 형식에 맞추기
    val recordType: RecordType = RecordType.CALL_NOT_MADE,
* */