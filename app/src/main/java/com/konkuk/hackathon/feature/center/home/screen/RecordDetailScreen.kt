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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.common.component.CallStatusChip
import com.konkuk.hackathon.core.common.component.OnItTopAppBar
import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.data.model.HealthStatusType
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray4
import com.konkuk.hackathon.core.designsystem.theme.gray7
import com.konkuk.hackathon.feature.center.home.components.ElderHealthChip

@Composable
fun RecordDetailScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                        Text("김순자", style = OnItTheme.typography.R_15, color = gray7) // 수정 필요
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("봉사자", style = OnItTheme.typography.R_15, color = gray7)
                        Text("홍길동", style = OnItTheme.typography.R_15, color = gray7) // 수정 필요
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("통화일시", style = OnItTheme.typography.R_15, color = gray7)
                        Text(
                            "2025-08-25 10:32",
                            style = OnItTheme.typography.R_15,
                            color = gray7
                        ) // 수정 필요
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("통화시간", style = OnItTheme.typography.R_15, color = gray7)
                        Text("12분 34초", style = OnItTheme.typography.R_15, color = gray7) // 수정 필요
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
                        ElderHealthChip(HealthStatusType.BAD) // 수정 필요
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("수행 상태", style = OnItTheme.typography.R_15, color = gray7)
                        ElderHealthChip(HealthStatusType.NORMAL) // 수정 필요
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
                        "오늘은 감기기운이 있으셔서 목소리가 안좋으셨습니다. 오늘은 감기기운이 있으셔서 목소리가 안좋으셨습니다.오늘은 감기기운이 있으셔서 목소리가 안좋으셨습니다.오늘은 감기기운이 있으셔서 목소리가 안좋으셨습니다.오늘은 감기기운이 있으셔서 목소리가 안좋으셨습니다.오늘은 감기기운이 있으셔서 목소리가 안좋으셨습니다.",
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
    RecordDetailScreen(PaddingValues(), {})
}