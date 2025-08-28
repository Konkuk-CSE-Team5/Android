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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.common.component.CallStatusChip
import com.konkuk.hackathon.core.common.component.OnItTopAppBar
import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray4
import com.konkuk.hackathon.core.designsystem.theme.gray7

@Composable
fun ElderStatusScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToRecordDetail: () -> Unit,
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
            items(3) { index -> // 실제 데이터 리스트 넣기
                val shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
                    2 -> RoundedCornerShape(
                        bottomStart = 14.dp,
                        bottomEnd = 14.dp
                    ) // 마지막 index 일때로 수정 필요
                    else -> RectangleShape
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clip(shape)
                        .border(1.dp, gray2, shape)
                        .clickable(onClick = navigateToRecordDetail )
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "8/25(화) 홍길동 봉사자",
                                style = OnItTheme.typography.R_15,
                                color = gray7
                            ) // 실제 데이터로 변경 필요
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "통화시간 30분 24초",
                                style = OnItTheme.typography.R_14,
                                color = gray4
                            ) // 실제 데이터로 변경 필요
                        }
                        CallStatusChip(CallStatusType.COMPLETE) // 실제 데이터로 변경 필요
                    }
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