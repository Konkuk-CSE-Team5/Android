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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.CallStatusChip
import com.konkuk.hackathon.core.common.component.OnItTopAppBar
import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.Primary
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray4
import com.konkuk.hackathon.core.designsystem.theme.gray5
import com.konkuk.hackathon.core.designsystem.theme.gray7
import com.konkuk.hackathon.feature.center.home.components.VolunteerMatchingChip
import com.konkuk.hackathon.feature.center.home.components.VolunteerMatchingType

@Composable
fun ElderStatusScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToRecordDetail: () -> Unit,
    navigateToAllRecord: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(OnItTheme.colors.white)
            .padding(padding)
    ) {
        OnItTopAppBar("김순자 어르신", popBackStack) // 수정 필요

        LazyColumn(Modifier.padding(horizontal = 16.dp)) {
            item {
                Spacer(Modifier.height(16.dp))
                Text("활동 기록", style = OnItTheme.typography.SB_18, color = gray7)
                Spacer(Modifier.height(24.dp))
            }
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = gray2,
                            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
                        )
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("홍길동 봉사자", style = OnItTheme.typography.SB_16, color = gray7)
                            Spacer(Modifier.width(6.dp))
                            VolunteerMatchingChip(VolunteerMatchingType.ACTIVE)
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "총 통화 x회 · 총 시간 x시간 x분 x초",
                            style = OnItTheme.typography.R_14,
                            color = gray5
                        )
                    }
                }
            }
            items(3) { index -> // 실제 데이터 리스트 넣기
                Box(
                    Modifier
                        .fillMaxWidth()
                        .border(1.dp, gray2)
                        .clickable(onClick = navigateToRecordDetail)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Text(
                                "2025-08-26 19:32",
                                style = OnItTheme.typography.R_15,
                                color = gray7
                            ) // 실제 데이터로 변경 필요
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "00:33:22",
                                style = OnItTheme.typography.R_14,
                                color = gray5
                            ) // 실제 데이터로 변경 필요
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CallStatusChip(CallStatusType.COMPLETE) // 실제 데이터로 변경 필요
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_back),
                                contentDescription = "기록 상세 보기",
                                Modifier.graphicsLayer(scaleX = -1f)
                            )
                        }

                    }
                }
            }
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 14.dp, bottomEnd = 14.dp))
                        .border(
                            1.dp,
                            gray2,
                            RoundedCornerShape(bottomStart = 14.dp, bottomEnd = 14.dp)
                        )
                        .clickable(onClick = {})
                ) {
                    Text(
                        "전체 기록 보기",
                        Modifier
                            .padding(vertical = 20.dp)
                            .align(Alignment.Center),
                        style = OnItTheme.typography.SB_14,
                        color = Primary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ElderStatusPreview() {

    ElderStatusScreen(padding = PaddingValues(), popBackStack = {}, {})

}