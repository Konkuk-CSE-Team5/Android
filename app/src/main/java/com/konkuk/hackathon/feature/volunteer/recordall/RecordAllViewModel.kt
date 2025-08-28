package com.konkuk.hackathon.feature.volunteer.recordall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.CallRecord
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.RecordType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordAllViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(RecordAllUiState())
    val uiState = _uiState.asStateFlow()

    fun loadRecordAll(id: Long) {
        // TODO: Api 가져오기
        viewModelScope.launch {
            // API 호출
            _uiState.value = _uiState.value.copy(
                name = "가져온 이름",
                records = emptyList() // 가져온 값들 CallRecord 라는 객체로 매핑하는 함수 작성하면됨.
            )
        }
    }
}