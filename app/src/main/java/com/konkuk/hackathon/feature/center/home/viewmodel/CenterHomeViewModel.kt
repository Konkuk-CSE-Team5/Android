package com.konkuk.hackathon.feature.center.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.CenterHomeRepository
import com.konkuk.hackathon.feature.center.home.uistate.CenterHomeUiState
import com.konkuk.hackathon.feature.center.home.uistate.VolunteersNeedingAttention
import com.konkuk.hackathon.feature.center.home.uistate.WeeklyVolunteerStatus
import com.konkuk.hackathon.feature.center.home.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.onSuccess

@HiltViewModel
class CenterHomeViewModel @Inject constructor(
    private val centerHomeRepository: CenterHomeRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CenterHomeUiState())
    val uiState = _uiState.asStateFlow()


    fun getCenterHomeData() {
        viewModelScope.launch {
            centerHomeRepository.getCenterHome()
                .onSuccess { response ->
                    Log.d("CenterHomeViewModel", "getCenterHomeData: $response")
                    _uiState.update {
                        response.toUiState()
                    }

                }.onFailure {
                    Log.e("CenterHomeViewModel", "getCenterHomeData: ${it.message}")
                }

        }
    }
}