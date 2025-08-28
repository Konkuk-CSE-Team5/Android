package com.konkuk.hackathon.feature.center.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.core.common.component.CallStatusChip
import com.konkuk.hackathon.core.common.component.OnItTopAppBar
import com.konkuk.hackathon.core.common.extension.toKoreanDuration
import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.data.model.HealthStatusType
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray4
import com.konkuk.hackathon.core.designsystem.theme.gray7
import com.konkuk.hackathon.feature.center.home.components.ElderHealthChip
import com.konkuk.hackathon.feature.center.home.viewmodel.RecordDetailViewModel
import com.konkuk.hackathon.feature.volunteer.recordall.RecordAllViewModel

@Composable
fun RecordDetailScreen(
    id: Long,
    padding: PaddingValues,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecordDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.loadRecordDetail(id)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(OnItTheme.colors.white)
            .padding(padding)
    ) {
        OnItTopAppBar("기록 상세", popBackStack)
        Column(
            Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, gray2, RoundedCornerShape(14.dp))
            ) {
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("수행상태", style = OnItTheme.typography.R_15, color = gray7)
                        CallStatusChip(CallStatusType.COMPLETE)
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("어르신", style = OnItTheme.typography.R_15, color = gray7)
                        Text(
                            uiState.name,
                            style = OnItTheme.typography.R_15,
                            color = gray7
                        ) // 수정 필요
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("봉사자", style = OnItTheme.typography.R_15, color = gray7)
                        Text(
                            uiState.volunteerName,
                            style = OnItTheme.typography.R_15,
                            color = gray7
                        ) // 수정 필요
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("통화일시", style = OnItTheme.typography.R_15, color = gray7)
                        Text(
                            uiState.callTime.substring(0, 10),
                            style = OnItTheme.typography.R_15,
                            color = gray7
                        ) // 수정 필요
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("통화시간", style = OnItTheme.typography.R_15, color = gray7)
                        Text(
                            uiState.callDuration,
                            style = OnItTheme.typography.R_15,
                            color = gray7
                        ) // 수정 필요
                    }
                }
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, gray2, RoundedCornerShape(14.dp))
            ) {
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("건강 상태", style = OnItTheme.typography.R_15, color = gray7)
                        ElderHealthChip(uiState.healthStatus) // 수정 필요
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("수행 상태", style = OnItTheme.typography.R_15, color = gray7)
                        ElderHealthChip(uiState.mindCondition) // 수정 필요
                    }
                }
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, gray2, RoundedCornerShape(14.dp))
            ) {
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text("봉사자 의견", style = OnItTheme.typography.SB_16, color = gray7)
                    Text(
                        uiState.memo,
                        style = OnItTheme.typography.R_15,
                        color = gray7
                    ) // 수정 필요

                }
            }

        }
    }
}

@Preview
@Composable
private fun RecordDetailScreenPrev() {
    RecordDetailScreen(
        1,
        PaddingValues(), {})
}