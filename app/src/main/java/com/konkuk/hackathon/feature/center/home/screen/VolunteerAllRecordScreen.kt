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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.CallStatusChip
import com.konkuk.hackathon.core.common.component.OnItTopAppBar
import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray5
import com.konkuk.hackathon.core.designsystem.theme.gray7
import com.konkuk.hackathon.feature.center.home.viewmodel.ElderStatusViewModel
import java.time.format.DateTimeFormatter
import kotlin.text.format

@Composable
fun VolunteerAllRecordScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    elderId: Int,
    navigateToRecordDetail: (Long) -> Unit,
    elderStatusViewModel: ElderStatusViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val uiState = elderStatusViewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        elderStatusViewModel.getElderStatus(elderId)
    }


    Column(
        Modifier
            .fillMaxSize()
            .background(OnItTheme.colors.white)
            .padding(padding)
    ) {
        OnItTopAppBar("${uiState.value.volunteerName} 봉사자", popBackStack) // 수정 필요

        LazyColumn(Modifier.padding(horizontal = 16.dp)) {
            item {
                Spacer(Modifier.height(16.dp))

            }
            items(uiState.value.records) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .border(1.dp, gray2)
                        .clickable(onClick = { navigateToRecordDetail(it.recordId.toLong()) })
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
                                it.date.format(
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                style = OnItTheme.typography.R_15,
                                color = gray7
                            ) // 실제 데이터로 변경 필요
                            Spacer(Modifier.width(8.dp))
                            Text(
                                it.duration,
                                style = OnItTheme.typography.R_14,
                                color = gray5
                            ) // 실제 데이터로 변경 필요
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CallStatusChip(it.status) // 실제 데이터로 변경 필요
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_back),
                                contentDescription = "기록 상세 보기",
                                Modifier.graphicsLayer(scaleX = -1f),
                                tint = gray7
                            )
                        }

                    }
                }
            }
        }
    }

}

