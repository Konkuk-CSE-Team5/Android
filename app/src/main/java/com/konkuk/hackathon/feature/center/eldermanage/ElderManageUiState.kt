package com.konkuk.hackathon.feature.center.eldermanage

data class ElderManageUiState(
    val elderId: Long = 0L,
    val name: String = "",
    val birth: String = "",
    val phoneNumber: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val code: String = "",
    val volunteer: Volunteer = Volunteer(),
) {
    data class Volunteer(
        val name: String = "",
        val recentActivityDate: String = "",
    )
}
