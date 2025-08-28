package com.konkuk.hackathon.feature.volunteer.home.screen

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.volunteer.home.components.ElderCard
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.hackathon.feature.volunteer.home.viewmodel.VolunteerHomeViewModel
import java.time.LocalDateTime

@Composable
fun VolunteerHomeScreen(
    padding: PaddingValues,
    navigateToRecordSubmit: (Int, String, String, String) -> Unit,
    volunteerHomeViewModel: VolunteerHomeViewModel = hiltViewModel()
) {
    var pin by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val uiState = volunteerHomeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        volunteerHomeViewModel.getVolunteerHome()
    }

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
                Text("홈", style = OnItTheme.typography.SB_24, color = OnItTheme.colors.gray7)
                Icon(
                    painterResource(R.drawable.ic_bell),
                    contentDescription = "알림 아이콘",
                    tint = OnItTheme.colors.gray7
                )
            }
        }
        Column(
            Modifier
                .background(OnItTheme.colors.white)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(OnItTheme.colors.white)
                    .border(
                        1.dp,
                        OnItTheme.colors.gray2,
                        RoundedCornerShape(14.dp)
                    )

            ) {
                Column(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = pin,
                            onValueChange = {
                                pin = it.filter { char ->
                                    char.isDigit()
                                }.take(6)
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            singleLine = true,
                            placeholder = {
                                Box(Modifier.fillMaxWidth()) {
                                    Text(
                                        "●●●●●●",
                                        style = OnItTheme.typography.R_16,
                                        color = OnItTheme.colors.gray3,
                                        modifier = Modifier.align(Alignment.Center),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            textStyle = TextStyle(textAlign = TextAlign.Center),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = OnItTheme.colors.gray3,
                                focusedBorderColor = OnItTheme.colors.primary
                            )
                        )
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(OnItTheme.colors.primary)
                                .clickable(onClick = {
                                    volunteerHomeViewModel.postCode(pin.toInt())
                                    pin = ""
                                }) // 서버 연동 이후 구현
                        ) {
                            Text(
                                "등록",
                                modifier = Modifier.padding(vertical = 18.dp, horizontal = 30.dp),
                                color = OnItTheme.colors.white,
                                style = OnItTheme.typography.B_17
                            )
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "복지기관에서 받은 6자리 코드를 입력하세요.",
                        style = OnItTheme.typography.R_14,
                        color = OnItTheme.colors.gray3
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
            uiState.value.seniors.forEach {
                ElderCard(
                    it.name,
                    66,
                    it.nextSchedule,
                    it.schedule.size,
                    it.schedule.map { scheduleUiModel ->
                        when (scheduleUiModel.day) {
                            "Monday" -> "월"
                            "Tuesday" -> "화"
                            "Wednesday" -> "수"
                            "Thursday" -> "목"
                            "Friday" -> "금"
                            "Saturday" -> "토"
                            "Sunday" -> "일"
                            else -> "" // 예외 처리
                        }
                    },
                    it.schedule.first().startTime,
                    it.schedule.first().endTime,
                    it.notes,
                    it.phone,
                    onCallClick = {
                        navigateToRecordSubmit(
                            it.seniorId,
                            it.name,
                            it.phone,
                            LocalDateTime.now().toString()
                        )
                    })
            }
        }
    }
}
