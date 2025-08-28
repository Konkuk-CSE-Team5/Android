package com.konkuk.hackathon.feature.center.setting.viewmodel

import androidx.lifecycle.ViewModel
import com.konkuk.hackathon.core.data.repository.CenterInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.network.request.CenterInfoUpdateRequestDto
import com.konkuk.hackathon.feature.volunteer.setting.component.hyphenatePhone
import com.konkuk.hackathon.feature.volunteer.setting.component.phoneOnlyDigits
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CenterInfoViewModel @Inject constructor(
    private val centerInfoRepository: CenterInfoRepository
): ViewModel(){
    private val _ui = MutableStateFlow(CenterInfoUiState(isLoading = true))
    val ui = _ui.asStateFlow()

    private var hasLoaded = false
    private var base: CenterInfoUiState ? = null // 변경 감지 기준

    init {
        loadCenterInfo()
    }

    fun loadCenterInfo() {
        if (hasLoaded) return
        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, error = null)
            centerInfoRepository.getOrganizationInfo()
                .onSuccess { dto ->
                    val phoneServer =  dto.data.profile.managerContact
                    val phoneDigits = phoneOnlyDigits(phoneServer)
                    val next = CenterInfoUiState(
                        isLoading = false,
                        id = dto.data.profile.userId,
                        centerName = dto.data.profile.name,
                        password = "",
                        phone = hyphenatePhone(phoneDigits),
                        phoneDigits = phoneDigits,
                        managerName = dto.data.profile.manager
                    )
                    _ui.value = next
                    base = next
                    hasLoaded = true
                }
                .onFailure { e ->
                    _ui.value = _ui.value.copy(isLoading = false, error = e.message)
                }
        }
    }

    fun updatePassword(v: String) {_ui.value = _ui.value.copy(password = v) }
    fun updateManagerName(v: String) {_ui.value = _ui.value.copy(managerName = v) }
    fun updateCenterName(v: String) {_ui.value = _ui.value.copy(centerName = v) }
    fun updatePhoneDigits(raw: String) {
        val digits = phoneOnlyDigits(raw)  // 최대 11자리
        _ui.value = _ui.value.copy(
            phoneDigits = digits,
            phone = hyphenatePhone(digits) // 서버 전송 포맷도 함께 갱신
        )
    }

    fun updateCenterInfo(onDone: () -> Unit) {
        val s = _ui.value
        val b = base ?: s

        val phoneChanged = s.phoneDigits != b.phoneDigits
        val contactForServer = if (phoneChanged) hyphenatePhone(s.phoneDigits) else null

        val patch = CenterInfoUpdateRequestDto(
            password = s.password.takeIf { it.isNotEmpty() },
            name = s.centerName.takeIf { it != b.centerName },
            manager = s.managerName.takeIf { it != b.managerName },
            managerContact = contactForServer
        )

        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, error = null)
            centerInfoRepository.updateOrganizationInfo(patch)
                .onSuccess {
                    val updated =s.copy(isLoading = false, password = "")
                    _ui.value = updated
                    base = updated            // 기준값 갱신 → 설정 화면도 갱신 반영
                    onDone()
                }
                .onFailure { e ->
                    _ui.value = _ui.value.copy(isLoading = false, error = e.message)
                }
        }
    }
}