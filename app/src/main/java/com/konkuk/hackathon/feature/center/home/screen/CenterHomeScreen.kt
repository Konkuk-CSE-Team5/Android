package com.konkuk.hackathon.feature.center.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.CallStatusChip
import com.konkuk.hackathon.core.common.component.OnItProgressIndicator
import com.konkuk.hackathon.core.common.extension.dayOfWeekKorean
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray1
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray4
import com.konkuk.hackathon.core.designsystem.theme.gray7
import com.konkuk.hackathon.feature.center.home.components.ElderStatusCard
import com.konkuk.hackathon.feature.center.home.viewmodel.CenterHomeViewModel
import java.util.Locale

@Composable
fun CenterHomeScreen(
    padding: PaddingValues,
    navigateToElderStatus: (Int) -> Unit,
    navigateToAttentionRequired: () -> Unit,
    centerHomeViewModel: CenterHomeViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val uiState = centerHomeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        centerHomeViewModel.getCenterHomeData()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(OnItTheme.colors.white)
            .padding(padding)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("홈", style = OnItTheme.typography.SB_24, color = OnItTheme.colors.gray7)
        }
        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("주요 현황", style = OnItTheme.typography.SB_18, color = gray7)
            Spacer(Modifier.height(24.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, color = gray2, shape = RoundedCornerShape(14.dp))
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("이번 주 봉사 현황", style = OnItTheme.typography.SB_16, color = gray7)
                    Row(
                        Modifier.align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            "${uiState.value.weeklyVolunteerStatus.progressRate}",
                            style = OnItTheme.typography.B_28,
                            color = OnItTheme.colors.primary
                        )
                        Text("%", style = OnItTheme.typography.SB_22, color = gray4)
                    }
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            "총 ${uiState.value.weeklyVolunteerStatus.totalCount}회 중 ${uiState.value.weeklyVolunteerStatus.completedCount}회 완료",
                            Modifier.align(Alignment.CenterHorizontally),
                            style = OnItTheme.typography.R_15,
                            color = gray4
                        )
                        Spacer(Modifier.height(8.dp))
                        OnItProgressIndicator({
                            if (uiState.value.weeklyVolunteerStatus.progressRate.toFloat() == 0f) 0f
                            else
                                uiState.value.weeklyVolunteerStatus.progressRate.toFloat() / 100f
                        })

                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("●", style = OnItTheme.typography.B_12, color = gray2)
                            Text(
                                "예정 ${uiState.value.weeklyVolunteerStatus.pendingCount}",
                                style = OnItTheme.typography.R_14, color = gray4
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "●",
                                style = OnItTheme.typography.B_12,
                                color = OnItTheme.colors.positive
                            )
                            Text(
                                "완료 ${uiState.value.weeklyVolunteerStatus.completedCount}",
                                style = OnItTheme.typography.R_14, color = gray4
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "●",
                                style = OnItTheme.typography.B_12,
                                color = OnItTheme.colors.negative
                            )
                            Text(
                                "부재중 ${uiState.value.weeklyVolunteerStatus.absentCount}",
                                style = OnItTheme.typography.R_14, color = gray4
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "●",
                                style = OnItTheme.typography.B_12,
                                color = OnItTheme.colors.primary
                            )
                            Text(
                                "미실시 ${uiState.value.weeklyVolunteerStatus.missedCount}",
                                style = OnItTheme.typography.R_14, color = gray4
                            )
                        }

                    }


                }
            }
            Spacer(Modifier.height(24.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(
                        1.dp, color = gray2,
                        shape = RoundedCornerShape(14.dp)
                    )
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("주의가 필요한 봉사", style = OnItTheme.typography.SB_16, color = gray7)
                        Text(
                            "전체 보기",
                            style = OnItTheme.typography.R_16,
                            color = OnItTheme.colors.primary,
                            modifier = Modifier.clickable(onClick = navigateToAttentionRequired)
                        )
                    }
                    uiState.value.attentionRequiredList.forEach {

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CallStatusChip(it.status)
                            Text(
                                "${it.date} (${it.date.dayOfWeekKorean()}) ${it.seniorName} 어르신",
                                style = OnItTheme.typography.R_15,
                                color = gray7
                            )
                        }
                    }


                }
            }
            Spacer(Modifier.height(24.dp))
            Text("어르신별 현황", style = OnItTheme.typography.SB_18, color = gray7)
            Spacer(Modifier.height(16.dp))
            uiState.value.seniorStatusList.forEach {
                ElderStatusCard(
                    it.name,
                    it.age,
                    it.volunteerName,
                    executionCount = it.monthlyCalls["completed"]!!,
                    totalCount = it.monthlyCalls["target"]!!,
                    onClick = { navigateToElderStatus(it.seniorId) }
                ) // 이후 실제 값으로 수정

            }
        }
    }
}

@Preview
@Composable
private fun CenterHomePrev() {
    CenterHomeScreen(
        padding = PaddingValues(),
        navigateToElderStatus = {},
        navigateToAttentionRequired = {})

}