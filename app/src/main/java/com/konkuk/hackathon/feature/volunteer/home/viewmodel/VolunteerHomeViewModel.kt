package com.konkuk.hackathon.feature.volunteer.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.VolunteerHomeRepository
import com.konkuk.hackathon.feature.volunteer.home.uistate.VolunteerHomeUiState
import com.konkuk.hackathon.feature.volunteer.home.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VolunteerHomeViewModel @Inject constructor(
    private val volunteerHomeRepository: VolunteerHomeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(VolunteerHomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getVolunteerHome()
    }

    fun getVolunteerHome() {
        viewModelScope.launch {
            volunteerHomeRepository.getVolunteerHome().onSuccess {
                _uiState.value = it.toUiState()
                Log.d("getHome", _uiState.value.toString())
            }
                .onFailure { exc ->
                    Log.e("getHome", "오류, ${exc.message}")
                }
        }
    }

    fun postCode(code: Int) {
        viewModelScope.launch {
            volunteerHomeRepository.postCode(code).onSuccess {
                Log.d("postHome", "")
                getVolunteerHome()
            }
                .onFailure { exception -> Log.e("postHome", exception.message ?: "") }
        }
    }
}