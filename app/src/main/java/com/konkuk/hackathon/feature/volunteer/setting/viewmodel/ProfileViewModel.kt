package com.konkuk.hackathon.feature.volunteer.setting.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.ProfileRepository
import com.konkuk.hackathon.core.network.request.ProfileUpdateRequestDto
import com.konkuk.hackathon.feature.volunteer.setting.component.digitsToIso
import com.konkuk.hackathon.feature.volunteer.setting.component.hyphenatePhone
import com.konkuk.hackathon.feature.volunteer.setting.component.isoToDigits
import com.konkuk.hackathon.feature.volunteer.setting.component.phoneOnlyDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _ui = MutableStateFlow(VolunteerProfileUiState(isLoading = true))
    val ui = _ui.asStateFlow()
    private var hasLoaded = false
    private var base: VolunteerProfileUiState? = null // 변경 감지 기준

    init {
        loadProfile()
    }

    fun loadProfile() {
        if (hasLoaded) return
        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, error = null)
            profileRepository.getVolunteerInfo()
                .onSuccess { dto ->
                    val isoBirth = dto.data.profile.birthday
                    val phoneServer =  dto.data.profile.phone
                    val phoneDigits = phoneOnlyDigits(phoneServer)
                    val next = VolunteerProfileUiState(
                        isLoading = false,
                        id = dto.data.profile.userId,
                        name = dto.data.profile.name,
                        birthday = isoBirth,
                        birthdayDigits = isoToDigits(isoBirth),
                        phone = hyphenatePhone(phoneDigits),
                        phoneDigits = phoneDigits,
                        isMale = dto.data.profile.gender == "MALE",
                        password = ""
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

    fun updatePassword(v: String) { _ui.value = _ui.value.copy(password = v) }
    fun updateName(v: String)     { _ui.value = _ui.value.copy(name = v) }
    fun updateGender(isMale: Boolean) { _ui.value = _ui.value.copy(isMale = isMale)
        Log.d("ProfileViewModel", "updateGender: $isMale")
    }
    fun updateBirthDigits(digitsRaw: String) {
        val digits = digitsRaw.filter { it.isDigit() }.take(8)
        val iso = digitsToIso(digits) ?: _ui.value.birthday // 8자리 완성 전이면 기존 iso 유지
        _ui.value = _ui.value.copy(
            birthdayDigits = digits,
            birthday = iso
        )
    }
    fun updatePhoneDigits(raw: String) {
        val digits = phoneOnlyDigits(raw)  // 최대 11자리
        _ui.value = _ui.value.copy(
            phoneDigits = digits,
            phone = hyphenatePhone(digits) // 서버 전송 포맷도 함께 갱신
        )
    }

    fun updateProfile(onDone: () -> Unit) {
        val s = _ui.value
        val b = base ?: s
        val gender = if (s.isMale) "MALE" else "FEMALE"

        val phoneChanged = s.phoneDigits != b.phoneDigits
        val contactForServer = if (phoneChanged) hyphenatePhone(s.phoneDigits) else null

        val patch = ProfileUpdateRequestDto(
            password = s.password.takeIf { it.isNotEmpty() },
            name     = s.name.takeIf     { it != b.name },
            birthday = s.birthday.takeIf { it != b.birthday },
            gender   = gender.takeIf     { gender != if (b.isMale) "MALE" else "FEMALE" },
            contact  = contactForServer,
        )

        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, error = null)
            profileRepository.updateProfile(patch)
                .onSuccess {
                    val updated = s.copy(isLoading = false, password = "")
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