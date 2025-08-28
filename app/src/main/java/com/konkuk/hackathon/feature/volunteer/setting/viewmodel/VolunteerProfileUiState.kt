package com.konkuk.hackathon.feature.volunteer.setting.viewmodel

data class VolunteerProfileUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val id: String = "", // 서버에서 받아와야하는 값(임시값)
    val password: String = "",
    val name: String = "",
    val birthday: String = "",
    val birthdayDigits: String = "",
    val phone: String = "",
    val phoneDigits: String = "",
    val isMale: Boolean = true,
) {
    val isSavable: Boolean =
        name.isNotEmpty() && birthdayDigits.length == 8 && phoneDigits.length in 8..11
}
