package com.konkuk.hackathon.feature.volunteer.recordmodify.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.core.common.component.OnItButtonPrimaryContent
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_1
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray3
import com.konkuk.hackathon.feature.volunteer.recordmodify.component.SelectBox
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.HealthCondition
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.MindCondition
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.RecordModifyUiState
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.RecordModifyViewModel

@Composable
fun RecordModifyScreen(
    popBackStack: () -> Unit,
    viewModel: RecordModifyViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RecordModifyScreen(
        uiState = uiState,
        popBackStack = popBackStack,
        onHasCalledChange = { viewModel.updateHasCalled(it) },
        onHealthConditionChange = { viewModel.updateHealthCondition(it) },
        onMindConditionChange = { viewModel.updateMindCondition(it) },
    )
}

@Composable
private fun RecordModifyScreen(
    uiState: RecordModifyUiState,
    popBackStack: () -> Unit,
    onHasCalledChange: (Boolean) -> Unit = {},
    onHealthConditionChange: (HealthCondition) -> Unit = {},
    onMindConditionChange: (MindCondition) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier
//            .padding(padding)
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
                onClick = { /* TODO: 수정 완료 */ },
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
                Text(
                    text = "${uiState.callTime} / 통화 ${uiState.duration}",
                    style = OnItTheme.typography.R_14.copy(color = Gray_7)
                )
            }
            VerticalSpacer(24.dp)
            Text(
                text = "수행 여부",
                style = OnItTheme.typography.SB_16.copy(color = Gray_7),
            )
            VerticalSpacer(8.dp)
            CalledComponent(
                modifier = Modifier,
                hasCalled = uiState.hasCalled,
                onBoxClick = onHasCalledChange,
            )
            VerticalSpacer(24.dp)
            Text(
                text = "건강 상태",
                style = OnItTheme.typography.SB_16.copy(color = Gray_7),
            )
            VerticalSpacer(8.dp)
            HealthConditionComponent(
                modifier = Modifier,
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
    hasCalled: Boolean, // 수행 했는지 여부
    onBoxClick: (Boolean) -> Unit = { },
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SelectBox(
            isSelected = hasCalled,
            text = "부재중",
            onClick = { onBoxClick(true) },
        )
        SelectBox(
            isSelected = !hasCalled,
            text = "수행",
            onClick = { onBoxClick(false) },
        )
    }
}

@Composable
private fun HealthConditionComponent(
    modifier: Modifier = Modifier,
    selectedOption: HealthCondition,
    onOptionSelected: (HealthCondition) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HealthCondition.entries.forEach { condition ->
            SelectBox(
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
    selectedOption: MindCondition,
    onOptionSelected: (MindCondition) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MindCondition.entries.forEach { condition ->
            SelectBox(
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
                callTime = "2025-08-26 19:32",
                duration = "07:12",
                hasCalled = false,
                healthCondition = HealthCondition.GOOD,
                mindCondition = MindCondition.GOOD,
            )
        )
    }
}