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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray1
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray3
import com.konkuk.hackathon.feature.volunteer.home.components.StatusButton

@Composable
fun RecordSubmitScreen(padding: PaddingValues) {
    val scrollState = rememberScrollState()


    Column(
        Modifier
            .fillMaxSize()
            .background(OnItTheme.colors.white)
            .padding(padding)
    ) {
        Box {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "뒤 화살표 아이콘",
                    tint = OnItTheme.colors.gray7
                )
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "기록 작성",
                        style = OnItTheme.typography.SB_20,
                        color = OnItTheme.colors.black
                    )
                }
                Box(modifier = Modifier.size(24.dp))

            }
        }
        Column(
            Modifier
                .padding(16.dp)
                .verticalScroll(scrollState),
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
                Text("건강 상태", style = OnItTheme.typography.SB_16)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    StatusButton(content = "좋음")
                    StatusButton(content = "보통")
                    StatusButton(content = "나쁨")
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("심리 상태", style = OnItTheme.typography.SB_16)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    StatusButton(content = "좋음", emoji = "\uD83D\uDE04")
                    StatusButton(content = "보통", emoji = "\uD83D\uDE11")
                    StatusButton(content = "나쁨", emoji = "\uD83D\uDE41")
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("의견", style = OnItTheme.typography.SB_16)
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
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
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(OnItTheme.colors.primary)
            ) {
                Text(
                    "저장",
                    Modifier.align(Alignment.Center).padding(vertical = 16.dp),
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
    RecordSubmitScreen(padding = PaddingValues())
}