package com.konkuk.hackathon.feature.center.register.viewmodel

data class RegisterUiState(
    val elders: List<Elder> = emptyList(),
) {
    data class Elder(
        val id: Long = 0L,
        val name: String = "",
        val age: Int = 0,
        val code: String = "000000",
        val status: ElderStatus = ElderStatus.MATCHING,
    )

    enum class ElderStatus(val label: String) {
        MATCHING("매칭중"),
        SUCCESS("매칭 완료"),
        FINISH("진행종료"),
    }
}

