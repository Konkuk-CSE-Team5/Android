package com.konkuk.hackathon.feature.center.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.common.component.OnItProgressIndicator
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray3
import com.konkuk.hackathon.core.designsystem.theme.gray4
import com.konkuk.hackathon.core.designsystem.theme.gray7

@Composable
fun ElderStatusCard(
    elderName: String,
    age: Int,
    volunteerName: String,
    progress: () -> Float,
    modifier: Modifier = Modifier
) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(OnItTheme.colors.white)
            .border(1.dp, gray2, RoundedCornerShape(14.dp))
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("$elderName | ${age}세", style = OnItTheme.typography.SB_18, color = gray7)
                Text("봉사자: $volunteerName", style = OnItTheme.typography.R_14, color = gray4)
            }
            Text("다음 예정: 8/28(목) 19:00", style = OnItTheme.typography.R_15, color = gray4)
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("이번 달 통화", style = OnItTheme.typography.R_15, color = gray7)
                    Text("4/6회", style = OnItTheme.typography.B_12, color = gray3)
                }
                OnItProgressIndicator(progress)
            }
        }
    }
}

@Preview
@Composable
private fun ElderStatusCardPrev() {
    ElderStatusCard(
        elderName = "김순자",
        age = 77,
        volunteerName = "홍길동",
        progress = {4f/6f}
    )
}