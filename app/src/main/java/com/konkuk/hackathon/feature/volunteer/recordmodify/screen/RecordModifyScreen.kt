package com.konkuk.hackathon.feature.volunteer.recordmodify.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.core.common.component.OnItButtonPrimaryContent
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_1
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary_Container
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray3
import com.konkuk.hackathon.feature.volunteer.recordmodify.component.SelectBox
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.HealthCondition
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.MindCondition
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.RecordModifyUiState
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.RecordModifyViewModel

@Composable
fun RecordModifyScreen(
    paddingValues: PaddingValues,
    id: Long,
    popBackStack: () -> Unit,
    viewModel: RecordModifyViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isCompleted by viewModel.isCompleted.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchRecordData(id)
    }

    LaunchedEffect(isCompleted) {
        if (isCompleted) {
            popBackStack()
        }
    }

    RecordModifyScreen(
        padding = paddingValues,
        uiState = uiState,
        popBackStack = popBackStack,
        onModifyClick = { viewModel.patchRecordData(id) },
        onHealthConditionChange = { viewModel.updateHealthCondition(it) },
        onMindConditionChange = { viewModel.updateMindCondition(it) },
    )
}

@Composable
private fun RecordModifyScreen(
    padding: PaddingValues = PaddingValues(0.dp),
    uiState: RecordModifyUiState,
    popBackStack: () -> Unit,
    onModifyClick: () -> Unit = {},
    onHealthConditionChange: (HealthCondition) -> Unit = {},
    onMindConditionChange: (MindCondition) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            ) {
                IconButton(
                    onClick = popBackStack,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = null,
                    )
                }
                Text(
                    text = "기록 수정",
                    style = OnItTheme.typography.B_17.copy(color = Gray_7),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        containerColor = Color.White,
        bottomBar = {
            OnItButtonPrimaryContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                text = "수정",
                height = 50.dp,
                onClick = { onModifyClick() },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            // 어르신 정보
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Gray_1,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = "${uiState.name} 어르신",
                    style = OnItTheme.typography.SB_16.copy(color = Gray_7)
                )
                VerticalSpacer(4.dp)
                uiState.callTimes.forEach { callTime ->
                    Text(
                        text = "${callTime.time} / 통화 ${callTime.duration}",
                        style = OnItTheme.typography.R_14.copy(color = Gray_7)
                    )
                }
            }
            VerticalSpacer(24.dp)
            Text(
                text = "수행 여부",
                style = OnItTheme.typography.SB_16.copy(color = Gray_7),
            )
            VerticalSpacer(8.dp)
            CalledComponent(
                modifier = Modifier,
                isAbsent = !uiState.hasCalled,
            )
            VerticalSpacer(24.dp)
            Text(
                text = "건강 상태",
                style = OnItTheme.typography.SB_16.copy(color = Gray_7),
            )
            VerticalSpacer(8.dp)
            HealthConditionComponent(
                modifier = Modifier,
                isAbsent = !uiState.hasCalled,
                selectedOption = uiState.healthCondition,
                onOptionSelected = onHealthConditionChange,
            )
            VerticalSpacer(24.dp)
            Text(
                text = "심리 상태",
                style = OnItTheme.typography.SB_16.copy(color = Gray_7),
            )
            VerticalSpacer(8.dp)
            MindConditionComponent(
                modifier = Modifier,
                isAbsent = !uiState.hasCalled,
                selectedOption = uiState.mindCondition,
                onOptionSelected = onMindConditionChange,
            )
            VerticalSpacer(24.dp)
            Text(
                text = "의견",
                style = OnItTheme.typography.SB_16.copy(color = Gray_7),
            )
            VerticalSpacer(8.dp)
            BasicTextField(
                state = uiState.memo,
                onKeyboardAction = KeyboardActionHandler {
                    // 키보드 숨김 처리

                },
                decorator = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 128.dp)
                            .border(
                                width = 1.dp,
                                color = Gray_2,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        if (uiState.memo.text.isEmpty()) {
                            Text(
                                "식사·수면·복약 등 특이사항을 적어 주세요 \n" +
                                        "(최대 300자)",
                                style = OnItTheme.typography.R_14,
                                color = gray3,
                            )
                        }
                        innerTextField()
                    }

                }
            )
        }
    }
}

@Composable
private fun CalledComponent(
    modifier: Modifier = Modifier,
    isAbsent: Boolean = false,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = modifier
                .weight(1f)
                .background(
                    color =
                        if (isAbsent) Main_Primary_Container
                        else Gray_1,
                    shape = RoundedCornerShape(14.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isAbsent) Main_Primary
                    else Gray_2,
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(vertical = 13.5.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(
                    text = "부재중",
                    style = OnItTheme.typography.SB_14.copy(
                        color = if (isAbsent) Main_Primary else Gray_7
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Box(
            modifier = modifier
                .weight(1f)
                .background(
                    color =
                        if (!isAbsent) Main_Primary_Container
                        else Gray_1,
                    shape = RoundedCornerShape(14.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (!isAbsent) Main_Primary
                    else Gray_2,
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(vertical = 13.5.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(
                    text = "수행",
                    style = OnItTheme.typography.SB_14.copy(
                        color = if (!isAbsent) Main_Primary else Gray_7
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun HealthConditionComponent(
    modifier: Modifier = Modifier,
    isAbsent: Boolean = false,
    selectedOption: HealthCondition,
    onOptionSelected: (HealthCondition) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HealthCondition.entries.forEach { condition ->
            SelectBox(
                isDisabled = isAbsent,
                isSelected = selectedOption == condition,
                text = condition.label,
                onClick = { onOptionSelected(condition) },
            )
        }
    }
}

@Composable
private fun MindConditionComponent(
    modifier: Modifier = Modifier,
    isAbsent: Boolean = false,
    selectedOption: MindCondition,
    onOptionSelected: (MindCondition) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MindCondition.entries.forEach { condition ->
            SelectBox(
                isDisabled = isAbsent,
                verticalPadding = 9.5.dp,
                isSelected = selectedOption == condition,
                text = condition.label,
                emoji = condition.emoji,
                onClick = { onOptionSelected(condition) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecordScreenPreview() {
    OnItTheme {
        RecordModifyScreen(
            popBackStack = {},
            uiState = RecordModifyUiState(
                name = "김순자",
                callTimes = listOf(
                    RecordModifyUiState.CallTime(
                        time = "2024-06-15 14:00",
                        duration = "07:12"
                    ),
                    RecordModifyUiState.CallTime(
                        time = "2024-06-15 14:00",
                        duration = "07:12"
                    ),
                    RecordModifyUiState.CallTime(
                        time = "2024-06-15 14:00",
                        duration = "07:12"
                    )
                ),
                hasCalled = false,
                healthCondition = HealthCondition.GOOD,
                mindCondition = MindCondition.GOOD,
            )
        )
    }
}