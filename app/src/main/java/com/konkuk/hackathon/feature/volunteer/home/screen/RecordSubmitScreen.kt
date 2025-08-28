package com.konkuk.hackathon.feature.volunteer.home.screen

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.konkuk.hackathon.core.common.component.OnItTopAppBar
import com.konkuk.hackathon.core.common.extension.noRippleClickable
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray1
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray3
import com.konkuk.hackathon.core.designsystem.theme.gray7
import com.konkuk.hackathon.feature.volunteer.home.components.StatusButton
import com.konkuk.hackathon.feature.volunteer.home.uistate.HealthState
import com.konkuk.hackathon.feature.volunteer.home.uistate.CallPerformanceState
import com.konkuk.hackathon.feature.volunteer.home.uistate.PsychologicalState
import com.konkuk.hackathon.feature.volunteer.home.viewmodel.RecordSubmitViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun RecordSubmitScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    id: Int,
    elderName: String,
    startTime: LocalDateTime,
    phone: String,
    recordSubmitViewModel: RecordSubmitViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by recordSubmitViewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val isAnyCallPerformed =
        uiState.callLogList.any { it.callPerformanceState == CallPerformanceState.PERFORMED }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            // 화면이 다시 활성화될 때 (ON_RESUME)
            if (event == Lifecycle.Event.ON_RESUME) {
                recordSubmitViewModel.fetchCallLogs(startTime, phone)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        // Composable이 화면에서 사라질 때 Observer를 제거
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }




    Column(
        Modifier
            .fillMaxSize()
            .background(OnItTheme.colors.white)
            .padding(padding)

    ) {
        OnItTopAppBar(title = "기록 작성", popBackStack = popBackStack)
        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(gray1)
            ) {
                Column(
                    Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        "$elderName 어르신",
                        style = OnItTheme.typography.SB_16,
                        color = gray7
                    )
                    uiState.callLogList.reversed().forEach {
                        Text(
                            "${
                                it.dateTime.format(
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                                )
                            } / 통화 ${it.callTime}",
                            style = OnItTheme.typography.R_14,
                            color = gray7
                        )
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("수행 여부", style = OnItTheme.typography.SB_16)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    CallPerformanceState.entries.forEach { performanceState ->
                        val isSelected = when (performanceState) {
                            CallPerformanceState.PERFORMED -> isAnyCallPerformed
                            CallPerformanceState.ABSENT -> !isAnyCallPerformed
                        }

                        StatusButton(
                            content = performanceState.displayName,
                            isDisabled = !isSelected,
                            isSelected = isSelected,
                            onClick = {},
                            modifier = Modifier.weight(1f)
                        )
                    }

                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("건강 상태", style = OnItTheme.typography.SB_16)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HealthState.entries.forEach { status ->
                        StatusButton(
                            content = status.displayName,
                            isDisabled = !isAnyCallPerformed,
                            isSelected = uiState.selectedHealthState == status,
                            onClick = { recordSubmitViewModel.onHealthStateSelected(status) },
                            modifier = Modifier.weight(1f)
                        )
                    }

                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("심리 상태", style = OnItTheme.typography.SB_16)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PsychologicalState.entries.forEach { status ->
                        StatusButton(
                            modifier = Modifier.weight(1f),
                            content = status.displayName,
                            emoji = status.emoji,
                            isDisabled = !isAnyCallPerformed,
                            isSelected = uiState.selectedPsychologicalState == status,
                            onClick = {
                                recordSubmitViewModel.onPsychologicalStateSelected(
                                    status
                                )
                            }
                        )
                    }

                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("의견", style = OnItTheme.typography.SB_16)
                OutlinedTextField(
                    value = uiState.opinionText,
                    onValueChange = { recordSubmitViewModel.onOpinionChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    placeholder = {
                        Text(
                            "식사, 수면, 복약 등 특이사항을 적어 주세요\n(최대 300자)",
                            color = gray3,
                            style = OnItTheme.typography.R_14,
                        )
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = gray2,
                        focusedBorderColor = OnItTheme.colors.primary
                    )
                )
            }

            Box(
                Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(OnItTheme.colors.primary_container)
                    .clickable(onClick = {
                        val intent = Intent(Intent.ACTION_CALL, "tel:$phone".toUri())

                        try {
                            context.startActivity(intent)
                        } catch (e: SecurityException) {
                            Toast.makeText(context, "통화 오류입니다. 다시 시도해주세요.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            ) {
                Text(
                    "다시 전화걸기",
                    style = OnItTheme.typography.SB_16,
                    color = OnItTheme.colors.primary,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 18.dp)
                )
            } // 이 부분만 제거?

            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(OnItTheme.colors.primary)
                    .noRippleClickable(onClick = popBackStack)
            ) {
                Text(
                    "저장",
                    Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 16.dp),
                    style = OnItTheme.typography.B_17,
                    color = OnItTheme.colors.white
                )
            }
        }

    }

}

@Preview
@Composable
private fun RSSPrev() {
    RecordSubmitScreen(
        padding = PaddingValues(),
        {},
        id = 0,
        elderName = "김재훈",
        phone = "010-9460-1439",
        startTime = LocalDateTime.now()
    )
}