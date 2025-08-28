package com.konkuk.hackathon.feature.center.setting.viewmodel

data class CenterInfoUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val id: String = "",
    val password : String = "",
    val centerName : String = "",
    val managerName : String = "",
    val phone : String = "",
    val phoneDigits : String = "",
) {

    val isSavable : Boolean =
        managerName.isNotEmpty() && phoneDigits.length in 9..11 && centerName.isNotEmpty()
}
