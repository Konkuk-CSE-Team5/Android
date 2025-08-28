package com.konkuk.hackathon.feature.volunteer.record.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.common.math.LinearTransformation.vertical
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.HorizontalSpacer
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_5
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary_Container
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.CallRecord
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.Elder
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.ElderType
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.RecordType
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.RecordUiState
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.RecordViewModel

@Composable
fun RecordScreen(
    padding: PaddingValues,
    navigateToRecordModify: (Long) -> Unit = {},
    navigateToRecordAll: (Long) -> Unit = {},
    viewModel: RecordViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                text = "기록",
                style = OnItTheme.typography.SB_24.copy(color = Gray_7),
            )
        }
        RecordScreen(
            uiState = uiState,
            onRecordClick = navigateToRecordModify,
            onAllRecordClick = navigateToRecordAll,
        )
    }
}

@Composable
private fun RecordScreen(
    uiState: RecordUiState = RecordUiState(),
    onRecordClick: (Long) -> Unit = {},
    onAllRecordClick: (Long) -> Unit = { },
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(uiState.elders) { elder ->
            ElderComponent(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                elder = elder,
                onRecordClick = onRecordClick,
                onAllRecordClick = onAllRecordClick,
            )
        }
    }
}

@Composable
private fun ElderComponent(
    modifier: Modifier = Modifier,
    elder: Elder,
    onRecordClick: (Long) -> Unit = { },
    onAllRecordClick: (Long) -> Unit = { },
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray_2,
                shape = RoundedCornerShape(14.dp),
            )
            .clip(RoundedCornerShape(14.dp))
    ) {
        VerticalSpacer(16.dp)
        Row(
            modifier = Modifier
                .heightIn(min = 26.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${elder.name} 어르신",
                style = OnItTheme.typography.SB_16.copy(color = Gray_7),
            )
            HorizontalSpacer(4.dp)
            ElderTypeChip(type = elder.type)
        }
        VerticalSpacer(4.dp)
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "총 통화 ${elder.callCount}회 · 총 시간 ${elder.totalTime}",
            style = OnItTheme.typography.R_14.copy(color = Gray_5),
        )
        VerticalSpacer(16.dp)
        elder.records.take(3).forEach { record ->
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Gray_2,
                thickness = 1.dp,
            )
            CallRecordComponent(
                callRecord = record,
                onClick = { onRecordClick(record.id) },
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Gray_2,
            thickness = 1.dp,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable { onAllRecordClick(elder.id) },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "전체 기록 보기",
                style = OnItTheme.typography.SB_14.copy(color = Main_Primary),
            )
        }

    }
}

@Composable
private fun CallRecordComponent(
    modifier: Modifier = Modifier,
    callRecord: CallRecord,
    onClick: () -> Unit = { },
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = callRecord.time,
                style = OnItTheme.typography.R_14.copy(color = Gray_7),
            )
            HorizontalSpacer(8.dp)
            Text(
                text = callRecord.duration,
                style = OnItTheme.typography.R_14.copy(color = Gray_5),
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RecordTypeChip(type = callRecord.recordType)
            HorizontalSpacer(13.dp)
            Icon(
                painter = painterResource(R.drawable.ic_arrow_big_right),
                contentDescription = null,
                tint = Gray_7,
            )
        }
    }
}

@Composable
private fun RecordTypeChip(
    modifier: Modifier = Modifier,
    type: RecordType,
) {
    val backgroundColor = when (type) {
        RecordType.SUCCESS -> OnItTheme.colors.positive_container
        RecordType.CALL_NOT_MADE -> Main_Primary_Container
        RecordType.ABSENCE -> OnItTheme.colors.negative_container
    }
    val textColor = when (type) {
        RecordType.SUCCESS -> OnItTheme.colors.positive
        RecordType.CALL_NOT_MADE -> Main_Primary
        RecordType.ABSENCE -> OnItTheme.colors.negative
    }

    Box(
        modifier = modifier
            .size(50.dp, 20.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = type.label,
            style = OnItTheme.typography.B_12.copy(color = textColor)
        )
    }
}

@Composable
private fun ElderTypeChip(
    modifier: Modifier = Modifier,
    type: ElderType,
) {
    Box(
        modifier = modifier
            .size(60.dp, 20.dp)
            .background(
                color = OnItTheme.colors.positive_container,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = type.label,
            style = OnItTheme.typography.B_12.copy(color = OnItTheme.colors.positive)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecordScreenPreview() {
    OnItTheme {
        RecordScreen(
            uiState = RecordUiState(
                elders = listOf(
                    Elder(
                        name = "홍길동",
                        type = ElderType.COMPLETED,
                        callCount = 3,
                        totalTime = "0시간 10분 30초",
                        records = listOf(
                            CallRecord(
                                time = "2024-06-01 14:00",
                                duration = "10분 30초",
                                recordType = RecordType.SUCCESS
                            ),
                            CallRecord(
                                time = "2024-06-02 14:00",
                                duration = "",
                                recordType = RecordType.CALL_NOT_MADE
                            ),
                            CallRecord(
                                time = "2024-06-03 14:00",
                                duration = "00:00",
                                recordType = RecordType.ABSENCE
                            ),
                        ),
                    ),
                    Elder(
                        name = "홍길동",
                        type = ElderType.COMPLETED,
                        callCount = 3,
                        totalTime = "0시간 10분 30초",
                        records = listOf(
                            CallRecord(
                                time = "2024-06-01 14:00",
                                duration = "10분 30초",
                                recordType = RecordType.SUCCESS
                            ),
                            CallRecord(
                                time = "2024-06-02 14:00",
                                duration = "",
                                recordType = RecordType.CALL_NOT_MADE
                            ),
                            CallRecord(
                                time = "2024-06-03 14:00",
                                duration = "00:00",
                                recordType = RecordType.ABSENCE
                            ),
                        ),
                    ),
                )
            )
        )
    }
}