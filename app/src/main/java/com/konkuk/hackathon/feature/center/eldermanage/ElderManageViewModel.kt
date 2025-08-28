package com.konkuk.hackathon.feature.center.eldermanage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.CenterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElderManageViewModel @Inject constructor(
    private val centerRepository: CenterRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ElderManageUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchSeniorDetail(seniorId: Long) {
        viewModelScope.launch {
            centerRepository.getSeniorDetail(seniorId)
                .onSuccess { response ->
                    val volunteer = response.matchedVolunteer?.let { matched ->
                        ElderManageUiState.Volunteer(
                            name = matched.name,
                            recentActivityDate = matched.lastWorkDay ?: ""
                        )
                    } ?: ElderManageUiState.Volunteer()

                    _uiState.value = _uiState.value.copy(
                        elderId = seniorId,
                        name = response.seniorProfile.name,
                        birth = response.seniorProfile.birthday,
                        phoneNumber = response.seniorProfile.contact,
                        startDate = response.seniorProfile.startDate,
                        endDate = response.seniorProfile.endDate,
                        code = response.seniorCode,
                        volunteer = volunteer
                    )
                }
                .onFailure {
                    Log.d("ElderManageViewModel", "fetchSeniorDetail: ${it.message}")
                }
        }
    }
}