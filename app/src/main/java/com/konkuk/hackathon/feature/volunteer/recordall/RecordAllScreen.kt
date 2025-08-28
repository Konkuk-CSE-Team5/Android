package com.konkuk.hackathon.feature.volunteer.recordall

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.core.common.component.HorizontalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_5
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary_Container
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.CallRecord
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.RecordType

@Composable
fun RecordAllScreen(
    id: Long,
    padding: PaddingValues,
    popBackStack: () -> Unit = { },
    navigateToRecordModify: (Long) -> Unit = { }, // 얘는 알아서 수정하기
    viewModel: RecordAllViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadRecordAll(id)
    }

    RecordAllScreen(
        padding = padding,
        uiState = uiState,
        popBackStack = popBackStack,
        onRecordClick = navigateToRecordModify,
    )
}

@Composable
private fun RecordAllScreen(
    padding: PaddingValues,
    uiState: RecordAllUiState,
    popBackStack: () -> Unit = { },
    onRecordClick: (Long) -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .heightIn(min = 56.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = popBackStack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "뒤로가기",
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "${uiState.name} 어르신",
                style = OnItTheme.typography.B_17.copy(
                    color = Gray_7,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Gray_2,
                    shape = RoundedCornerShape(14.dp)
                )
                .clip(RoundedCornerShape(14.dp)),
        ) {

            items(uiState.records) { callRecord ->
                CallRecordComponent(
                    modifier = Modifier.clickable {
                        onRecordClick(callRecord.id)
                    },
                    callRecord = callRecord
                )
                if (callRecord != uiState.records.last()) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Gray_2,
                        thickness = 1.dp,
                    )
                }
            }
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
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
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

@Preview(showBackground = true)
@Composable
private fun RecordAllScreenPreview() {
    OnItTheme {
        RecordAllScreen(
            padding = PaddingValues(16.dp),
            uiState = RecordAllUiState(
                name = "김순자",
                records = listOf(
                    CallRecord(
                        id = 1L,
                        time = "2024-01-01 10:00",
                        duration = "30분",
                        recordType = RecordType.SUCCESS,
                    ),
                    CallRecord(
                        id = 2L,
                        time = "2024-01-02 11:00",
                        duration = "15분",
                        recordType = RecordType.CALL_NOT_MADE,
                    ),
                    CallRecord(
                        id = 3L,
                        time = "2024-01-03 12:00",
                        duration = "0분",
                        recordType = RecordType.ABSENCE,
                    ),
                    CallRecord(
                        id = 1L,
                        time = "2024-01-01 10:00",
                        duration = "30분",
                        recordType = RecordType.SUCCESS,
                    ),
                    CallRecord(
                        id = 2L,
                        time = "2024-01-02 11:00",
                        duration = "15분",
                        recordType = RecordType.CALL_NOT_MADE,
                    ),
                    CallRecord(
                        id = 3L,
                        time = "2024-01-03 12:00",
                        duration = "0분",
                        recordType = RecordType.ABSENCE,
                    ),
                    CallRecord(
                        id = 1L,
                        time = "2024-01-01 10:00",
                        duration = "30분",
                        recordType = RecordType.SUCCESS,
                    ),
                    CallRecord(
                        id = 2L,
                        time = "2024-01-02 11:00",
                        duration = "15분",
                        recordType = RecordType.CALL_NOT_MADE,
                    ),
                    CallRecord(
                        id = 3L,
                        time = "2024-01-03 12:00",
                        duration = "0분",
                        recordType = RecordType.ABSENCE,
                    ),
                )
            ),
        )
    }
}