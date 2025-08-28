package com.konkuk.hackathon.feature.center.eldermodify

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.insert
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.OnItButtonPrimaryContent
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_1
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_3
import com.konkuk.hackathon.core.designsystem.theme.Gray_4
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary_Container
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray3
import com.konkuk.hackathon.feature.signup.volunteer.SignUpInputField
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ElderModifyScreen(
    padding: PaddingValues,
    id: Long,
    popBackStack: () -> Unit = {},
    viewModel: ElderModifyViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val buttonEnabled = remember {
        derivedStateOf { uiState.isValid }
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchSeniorInfo(id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OnItButtonPrimaryContent(
            text = "수정",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            onClick = {
                // TODO: 수정 API
            },
            enabled = buttonEnabled.value
        )

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            IconButton(
                onClick = popBackStack,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "뒤로가기 아이콘",
                )
            }
            Text(
                text = "어르신 정보 수정",
                style = OnItTheme.typography.B_20.copy(color = Gray_7),
                modifier = Modifier.align(Alignment.Center)
            )

        }

        ElderModifyScreen(
            uiState = uiState,
            updateSchedule = { viewModel.updateSchedule(it) },
            onStartTimeChange = { time ->
                viewModel.updateStartTime(time)
                // 종료 시간이 새로운 시작 시간보다 빠르면 종료 시간 초기화
                if (uiState.endTime.isNotEmpty() && time.isNotEmpty()) {
                    if (isTimeEarlier(uiState.endTime, time)) {
                        viewModel.updateEndTime("")
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("시작 시간이 종료 시간보다 늦어 종료 시간을 초기화했습니다.")
                        }
                    }
                }
            },
            onEndTimeChange = { time ->
                if (uiState.startTime.isNotEmpty() && time.isNotEmpty()) {
                    if (isTimeEarlier(time, uiState.startTime)) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("종료 시간은 시작 시간보다 늦어야 합니다.")
                        }
                        return@ElderModifyScreen
                    }
                }
                viewModel.updateEndTime(time)
            },
            onStartDateChange = { date ->
                viewModel.updateStartDate(date)
                // 종료 날짜가 새로운 시작 날짜보다 빠르면 종료 날짜 초기화
                if (uiState.endDate.isNotEmpty() && date.isNotEmpty()) {
                    if (isDateEarlier(uiState.endDate, date)) {
                        viewModel.updateEndDate("")
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("시작 날짜가 종료 날짜보다 늦어 종료 날짜를 초기화했습니다.")
                        }
                    }
                }
            },
            onEndDateChange = { date ->
                if (uiState.startDate.isNotEmpty() && date.isNotEmpty()) {
                    if (isDateEarlier(date, uiState.startDate)) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("종료 날짜는 시작 날짜보다 늦어야 합니다.")
                        }
                        return@ElderModifyScreen
                    }
                }
                viewModel.updateEndDate(date)
            }
        )
    }
}

@Composable
fun ElderModifyScreen(
    uiState: ElderModifyUiState = ElderModifyUiState(),
    updateSchedule: (ElderModifyUiState.Schedule) -> Unit = {},
    onStartTimeChange: (String) -> Unit = {},
    onEndTimeChange: (String) -> Unit = {},
    onStartDateChange: (String) -> Unit = {},
    onEndDateChange: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        VerticalSpacer(height = 16.dp)
        SignUpInputField(
            state = uiState.name,
            title = "성함",
            placeholder = "성함",
        )
        VerticalSpacer(height = 16.dp)
        SignUpInputField(
            state = uiState.birth,
            title = "생년월일",
            placeholder = "YYYY / MM / DD",
            inputTransformation = InputTransformation {
                if (asCharSequence().isNotEmpty()) {
                    if (asCharSequence().last().isDigit().not()) revertAllChanges()
                    if (length > 8) {
                        revertAllChanges()
                    }
                }
            },
            outputTransformation = OutputTransformation {
                if (length > 4) insert(4, "/")
                if (length > 6) insert(7, "/")
            },
            keyboardType = KeyboardType.Number
        )
        VerticalSpacer(height = 16.dp)
        DateRangeSelector(
            title = "봉사 기간",
            startDate = uiState.startDate,
            endDate = uiState.endDate,
            onStartDateChange = onStartDateChange,
            onEndDateChange = onEndDateChange
        )
        VerticalSpacer(height = 16.dp)
        SignUpInputField(
            state = uiState.phoneNumber,
            title = "전화번호",
            placeholder = "전화번호",
            inputTransformation = InputTransformation {
                if (asCharSequence().isNotEmpty()) {
                    if (asCharSequence().last().isDigit().not()) revertAllChanges()
                    if (length > 11) {
                        revertAllChanges()
                    }
                }
            },
            outputTransformation = OutputTransformation {
                if (length > 3) insert(3, "-")
                if (length > 8) insert(8, "-")
            },
            keyboardType = KeyboardType.Number
        )
        VerticalSpacer(height = 16.dp)
        VolunteerDateRange(
            modifier = Modifier.padding(top = 16.dp),
            startDate = uiState.startTime,
            endDate = uiState.endTime,
            updateSchedule = uiState.schedules,
            onScheduleClick = { updateSchedule(it) },
            onStartDateChange = onStartTimeChange,
            onEndDateChange = onEndTimeChange,
        )
        VerticalSpacer(height = 16.dp)
        Text(
            text = "특이사항",
            style = OnItTheme.typography.R_17.copy(
                color = Gray_7,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        VerticalSpacer(height = 8.dp)
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

@Composable
fun VolunteerDateRange(
    modifier: Modifier = Modifier,
    startDate: String = "",
    endDate: String = "",
    updateSchedule: List<ElderModifyUiState.Schedule>,
    onScheduleClick: (ElderModifyUiState.Schedule) -> Unit = { },
    onStartDateChange: (String) -> Unit = {},
    onEndDateChange: (String) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "봉사스케줄",
            style = OnItTheme.typography.R_17.copy(
                color = Gray_7,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            ElderModifyUiState.Schedule.entries.forEach { schedule ->
                val isSelected = updateSchedule.contains(schedule)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) OnItTheme.colors.primary
                            else Gray_1
                        )
                        .clickable {
                            onScheduleClick(schedule)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = schedule.displayName,
                        style = OnItTheme.typography.SB_14.copy(
                            color = if (isSelected) Color.White
                            else Gray_7
                        )
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TimeSelector(
                selectedTime = startDate,
                onTimeChange = onStartDateChange,
                placeholder = "시작 시간",
            )
            Text(
                text = "~",
                style = OnItTheme.typography.SB_18.copy(color = Gray_7),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            TimeSelector(
                selectedTime = endDate,
                onTimeChange = onEndDateChange,
                placeholder = "종료 시간",
            )
        }
    }
}

@Composable
fun RowScope.TimeSelector(
    modifier: Modifier = Modifier,
    selectedTime: String = "",
    onTimeChange: (String) -> Unit = {},
    placeholder: String = "시작 시간",
) {
    var showTimePicker by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .weight(1f)
            .clickable {
                showTimePicker = true
            }
            .border(
                width = 1.dp,
                color = Gray_2,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(vertical = 14.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedTime.ifEmpty { placeholder },
            style = OnItTheme.typography.R_15.copy(
                color = if (selectedTime.isNotEmpty()) Gray_7 else Gray_3
            )
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_clock),
            contentDescription = "달력 아이콘",
            tint = Gray_4
        )
    }

    if (showTimePicker) {
        TimePickerDialog(
            onTimeSelected = { time ->
                onTimeChange(time)
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }
}

@Composable
fun TimePickerDialog(
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val timeSlots = generateTimeSlots()

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "시간 선택",
                    style = OnItTheme.typography.B_20.copy(color = Gray_7),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(timeSlots) { time ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onTimeSelected(time) }
                                .border(
                                    width = 1.dp,
                                    color = Gray_3,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = time,
                                style = OnItTheme.typography.R_16.copy(color = Gray_7)
                            )
                        }
                    }
                }

                OnItButtonPrimaryContent(
                    text = "취소",
                    height = 40.dp,
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
        }
    }
}

fun generateTimeSlots(): List<String> {
    val timeSlots = mutableListOf<String>()
    for (hour in 0..23) {
        for (minute in listOf(0, 30)) {
            val formattedHour = String.format("%02d", hour)
            val formattedMinute = String.format("%02d", minute)
            timeSlots.add("$formattedHour:$formattedMinute")
        }
    }
    timeSlots.add("24:00")
    return timeSlots
}

fun isTimeEarlier(time1: String, time2: String): Boolean {
    try {
        val parts1 = time1.split(":")
        val parts2 = time2.split(":")

        val hour1 = parts1[0].toInt()
        val minute1 = parts1[1].toInt()
        val hour2 = parts2[0].toInt()
        val minute2 = parts2[1].toInt()

        return when {
            hour1 < hour2 -> true
            hour1 > hour2 -> false
            else -> minute1 < minute2
        }
    } catch (e: Exception) {
        return false
    }
}

fun isDateEarlier(date1: String, date2: String): Boolean {
    try {
        val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val d1 = formatter.parse(date1)
        val d2 = formatter.parse(date2)
        return d1?.before(d2) ?: false
    } catch (e: Exception) {
        return false
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeSelector(
    modifier: Modifier = Modifier,
    title: String,
    startDate: String = "",
    endDate: String = "",
    onStartDateChange: (String) -> Unit = {},
    onEndDateChange: (String) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            style = OnItTheme.typography.R_17.copy(
                color = Gray_7,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DateSelector(
                selectedDate = startDate,
                onDateChange = onStartDateChange,
                placeholder = "시작 날짜"
            )
            Text(
                text = "~",
                style = OnItTheme.typography.SB_18.copy(color = Gray_7),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            DateSelector(
                selectedDate = endDate,
                onDateChange = onEndDateChange,
                placeholder = "종료 날짜"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.DateSelector(
    modifier: Modifier = Modifier,
    selectedDate: String = "",
    onDateChange: (String) -> Unit = {},
    placeholder: String = "날짜 선택",
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Row(
        modifier = modifier
            .weight(1f)
            .clickable {
                showDatePicker = true
            }
            .border(
                width = 1.dp,
                color = Gray_2,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(vertical = 14.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedDate.ifEmpty { placeholder },
            style = OnItTheme.typography.R_15.copy(
                color = if (selectedDate.isNotEmpty()) Gray_7 else Gray_3
            )
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_clock),
            contentDescription = "달력 아이콘",
            tint = Gray_4
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val date = Date(millis)
                            val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                            onDateChange(formatter.format(date))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("확인")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text("취소")
                }
            },
            colors = androidx.compose.material3.DatePickerDefaults.colors(
                containerColor = Main_Primary_Container
            )
        ) {
            DatePicker(
                state = datePickerState,
                colors = androidx.compose.material3.DatePickerDefaults.colors(
                    containerColor = Main_Primary_Container
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ElderModifyScreenPreview() {
    OnItTheme {
        ElderModifyScreen()
    }
}