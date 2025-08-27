package com.konkuk.hackathon.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun ElderCard(
    elderName: String,
    age: Int,
    phone: String,
    modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(OnItTheme.colors.white)
            .border(
                1.dp,
                OnItTheme.colors.gray2,
                shape = RoundedCornerShape(14.dp)

            )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "$elderName 어르신",
                        style = OnItTheme.typography.B_20,
                        color = OnItTheme.colors.gray7
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "만 ${age}세 - $phone",
                        style = OnItTheme.typography.R_14,
                        color = OnItTheme.colors.gray4
                    )
                }
                Box(
                    Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(OnItTheme.colors.primary_container)
                ) {
                    Text(
                        "다음 봉사: 8/28(목)",
                        style = OnItTheme.typography.SB_14,
                        color = OnItTheme.colors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(vertical = 7.dp, horizontal = 13.dp)
                    )
                }
            }
            Column {
                Text(
                    "스케줄: 주 2회 · 화/목 · 19:00",
                    style = OnItTheme.typography.R_14,
                    color = OnItTheme.colors.gray7
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "특이사항: 당뇨, 주행보조기 사용",
                    style = OnItTheme.typography.R_14,
                    color = OnItTheme.colors.gray7
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(OnItTheme.colors.primary)
            ) {
                Text(
                    "전화걸기",
                    style = OnItTheme.typography.B_17,
                    color = OnItTheme.colors.white,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 13.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ElderCardPrev() {
    ElderCard(elderName = "김순자", age = 77, phone = "010-1234-5678")
}