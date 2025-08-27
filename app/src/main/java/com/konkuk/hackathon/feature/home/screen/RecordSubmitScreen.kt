package com.konkuk.hackathon.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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

@Composable
fun RecordSubmitScreen(padding: PaddingValues) {
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
            ) {
                Icon(
                    painterResource(R.drawable.ic_bell),
                    contentDescription = "뒤 화살표 아이콘",
                    tint = OnItTheme.colors.gray7
                )
                Text(
                    "기록 작성",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = OnItTheme.typography.B_17
                )
            }
        }
        Column(
            Modifier.padding(16.dp),
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
                    Text("김순자 어르신") // 어르신 이름 받아오기
                    Text("2025-08-26 19:32 / 통화 07:12") // 로컬에서 통화기록 가져오기 (List로 구현)
                }
            }
        }

    }

}

@Preview
@Composable
private fun RSSPrev() {
    RecordSubmitScreen(padding = Modifier.systemBarsPadding() as PaddingValues)

}