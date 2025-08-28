package com.konkuk.hackathon.feature.volunteer.home.screen

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.OnItTopAppBar
import com.konkuk.hackathon.core.common.extension.noRippleClickable
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray1
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray3
import com.konkuk.hackathon.feature.volunteer.home.components.StatusButton
import com.konkuk.hackathon.feature.volunteer.home.uistate.HealthState
import com.konkuk.hackathon.feature.volunteer.home.uistate.PerformanceState
import com.konkuk.hackathon.feature.volunteer.home.uistate.PsychologicalState
import com.konkuk.hackathon.feature.volunteer.home.viewmodel.RecordSubmitViewModel

@Composable
fun RecordSubmitScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    recordSubmitViewModel: RecordSubmitViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by recordSubmitViewModel.uiState.collectAsState()



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
                    Text("김순자 어르신", style = OnItTheme.typography.SB_16) // 어르신 이름 받아오기
                    Text(
                        "2025-08-26 19:32 / 통화 07:12",
                        style = OnItTheme.typography.R_14
                    ) // 로컬에서 통화기록 가져오기 (List로 구현)
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("수행 여부", style = OnItTheme.typography.SB_16)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PerformanceState.entries.forEach { status ->
                        StatusButton(
                            content = status.displayName,
                            isSelected = uiState.selectedPerformanceState == status,
                            onClick = { recordSubmitViewModel.onPerformanceStateSelected(status) },
                            modifier = Modifier.weight(1f)
                        )
                    }

                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("건강 상태", style = OnItTheme.typography.SB_16)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    HealthState.entries.forEach { status ->
                        StatusButton(
                            content = status.displayName,
                            isSelected = uiState.selectedHealthState == status,
                            onClick = { recordSubmitViewModel.onHealthStateSelected(status) },
                            modifier = Modifier.weight(1f)
                        )
                    }

                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("심리 상태", style = OnItTheme.typography.SB_16)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PsychologicalState.entries.forEach { status ->
                        StatusButton(
                            modifier = Modifier.weight(1f),
                            content = status.displayName,
                            emoji = status.emoji,
                            isSelected = uiState.selectedPsychologicalState == status,
                            onClick = { recordSubmitViewModel.onPsychologicalStateSelected(status) }
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
                    .clickable(onClick = {})
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
    RecordSubmitScreen(padding = PaddingValues(), {})
}