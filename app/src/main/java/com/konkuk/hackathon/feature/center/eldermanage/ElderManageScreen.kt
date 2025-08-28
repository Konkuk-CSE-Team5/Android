package com.konkuk.hackathon.feature.center.eldermanage

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun ElderManageScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToElderModify: (Long) -> Unit,
    viewModel: ElderManageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
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
                text = "어르신 관리",
                style = OnItTheme.typography.B_20.copy(color = Gray_7),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        ElderManageScreen(
            uiState = uiState,
            navigateToElderModify = navigateToElderModify,

        )
    }
}

@Composable
private fun ElderManageScreen(
    uiState: ElderManageUiState,
    navigateToElderModify: (Long) -> Unit,
    onCodeReissueClick: () -> Unit = {},
    onMatchingEndClick: () -> Unit = {},
) {
    val clipboardManager = LocalClipboardManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 기본 정보
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = OnItTheme.colors.gray2,
                    shape = RoundedCornerShape(14.dp),
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "기본 정보",
                    style = OnItTheme.typography.B_17.copy(
                        fontSize = 18.sp,
                        color = OnItTheme.colors.gray7
                    ),
                )
                Text(
                    modifier = Modifier.clickable { navigateToElderModify(uiState.elderId) },
                    text = "정보 수정",
                    style = OnItTheme.typography.M_16.copy(
                        fontSize = 15.sp,
                        color = Main_Primary
                    ),
                )
            }

            Text(
                text = "성함        ${uiState.name}",
                style = OnItTheme.typography.M_16.copy(
                    fontSize = 15.sp,
                    color = OnItTheme.colors.gray7
                ),
            )
            Text(
                text = "생년월일    ${uiState.birth}",
                style = OnItTheme.typography.M_16.copy(
                    fontSize = 15.sp,
                    color = OnItTheme.colors.gray7
                ),
            )
            Text(
                text = "연락처      ${uiState.phoneNumber}",
                style = OnItTheme.typography.M_16.copy(
                    fontSize = 15.sp,
                    color = OnItTheme.colors.gray7
                ),
            )
            Text(
                text = "봉사 기간   ${uiState.startDate} ~ ${uiState.endDate}",
                style = OnItTheme.typography.M_16.copy(
                    fontSize = 15.sp,
                    color = OnItTheme.colors.gray7
                ),
            )
        }

        // 참여코드 관리
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = OnItTheme.colors.gray2,
                    shape = RoundedCornerShape(14.dp),
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "참여코드 관리",
                style = OnItTheme.typography.B_17.copy(
                    fontSize = 18.sp,
                    color = OnItTheme.colors.gray7
                ),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // 코드
                Row(
                    modifier = Modifier
                        .clickable(onClick = {
                            clipboardManager.setText(AnnotatedString(uiState.code))
                        })
                        .padding(vertical = 9.5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    Text(
                        text = "코드 ${uiState.code}  ",
                        style = OnItTheme.typography.R_14,
                        color = OnItTheme.colors.gray7,
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        contentDescription = null,
                    )
                }
                // 코드 재발급
                Box(
                    modifier = Modifier
                        .background(
                            color = OnItTheme.colors.negative_container,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { onCodeReissueClick() }
                        .padding(horizontal = 13.5.dp, vertical = 7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "코드 재발급",
                        style = OnItTheme.typography.R_14.copy(
                            color = OnItTheme.colors.negative,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }

        // 매칭된 봉사자
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = OnItTheme.colors.gray2,
                    shape = RoundedCornerShape(14.dp),
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = uiState.volunteer.name,
                style = OnItTheme.typography.B_17.copy(
                    fontSize = 18.sp,
                    color = OnItTheme.colors.gray7
                ),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // 코드
                Text(
                    text = "최근 활동: ${uiState.volunteer.recentActivityDate}",
                    style = OnItTheme.typography.R_14,
                    color = OnItTheme.colors.gray7,
                )

                // 매칭 종료
                Box(
                    modifier = Modifier
                        .background(
                            color = OnItTheme.colors.negative_container,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { onMatchingEndClick() }
                        .padding(horizontal = 13.5.dp, vertical = 7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "매칭 종료",
                        style = OnItTheme.typography.R_14.copy(
                            color = OnItTheme.colors.negative,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ElderManageScreenPreview() {
    OnItTheme {
        ElderManageScreen(
            uiState = ElderManageUiState(
                elderId = 1L,
                name = "홍길동",
                birth = "1935.01.01",
                phoneNumber = "010-1234-5678",
                startDate = "2023.01.01",
                endDate = "2023.12.31",
                code = "A1B2C3",
                volunteer = ElderManageUiState.Volunteer(
                    name = "김철수",
                    recentActivityDate = "2024.05.20",
                )
            ),
            navigateToElderModify = {},
        )
    }
}