package com.konkuk.hackathon.feature.center.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.OnItProgressIndicator
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray1
import com.konkuk.hackathon.core.designsystem.theme.gray2
import com.konkuk.hackathon.core.designsystem.theme.gray4
import com.konkuk.hackathon.core.designsystem.theme.gray7
import com.konkuk.hackathon.feature.center.home.components.ElderStatusCard

@Composable
fun CenterHomeScreen(
    padding: PaddingValues,
    navigateToElderStatus: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .background(OnItTheme.colors.white)
            .padding(padding)
    ) {
        Box(Modifier.height(56.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
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
                .verticalScroll(scrollState)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("주요 현황", style = OnItTheme.typography.SB_18, color = gray7)
            Spacer(Modifier.height(24.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, color = gray2, shape = RoundedCornerShape(14.dp))
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("이번 주 봉사 현황", style = OnItTheme.typography.SB_16, color = gray7)
                    Row(
                        Modifier.align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            "75",
                            style = OnItTheme.typography.B_28,
                            color = OnItTheme.colors.primary
                        )
                        Text("%", style = OnItTheme.typography.SB_22, color = gray4)
                    }
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            "총 20회 중 15회 완료",
                            Modifier.align(Alignment.CenterHorizontally),
                            style = OnItTheme.typography.R_15,
                            color = gray4
                        )
                        Spacer(Modifier.height(8.dp))
                        OnItProgressIndicator({ 15f / 20f })

                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("●", style = OnItTheme.typography.B_12, color = gray2)
                            Text(
                                "예정 15",
                                style = OnItTheme.typography.R_14, color = gray4
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "●",
                                style = OnItTheme.typography.B_12,
                                color = OnItTheme.colors.positive
                            )
                            Text(
                                "완료 15",
                                style = OnItTheme.typography.R_14, color = gray4
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "●",
                                style = OnItTheme.typography.B_12,
                                color = OnItTheme.colors.negative
                            )
                            Text(
                                "부재중 3",
                                style = OnItTheme.typography.R_14, color = gray4
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "●",
                                style = OnItTheme.typography.B_12,
                                color = OnItTheme.colors.primary
                            )
                            Text(
                                "미실시 3",
                                style = OnItTheme.typography.R_14, color = gray4
                            )
                        }

                    }


                }
            }
            Spacer(Modifier.height(24.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(
                        1.dp, color = gray2,
                        shape = RoundedCornerShape(14.dp)
                    )
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("주의가 필요한 봉사", style = OnItTheme.typography.SB_16, color = gray7)
                        Text(
                            "전체 보기",
                            style = OnItTheme.typography.R_16,
                            color = OnItTheme.colors.primary
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(OnItTheme.colors.primary_container)
                        ) {
                            Text(
                                "미실시",
                                Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                                style = OnItTheme.typography.B_12,
                                color = OnItTheme.colors.primary
                            )
                        }
                        Text(
                            "8/25(화) 김순자 어르신",
                            style = OnItTheme.typography.R_15,
                            color = gray7
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(OnItTheme.colors.negative_container)
                        ) {
                            Text(
                                "부재중",
                                Modifier.padding(horizontal = 9.dp, vertical = 2.dp),
                                style = OnItTheme.typography.B_12,
                                color = OnItTheme.colors.negative
                            )
                        }
                        Text(
                            "8/25(화) 김순자 어르신",
                            style = OnItTheme.typography.R_15,
                            color = gray7
                        )
                    }


                }
            }
            Spacer(Modifier.height(24.dp))
            Text("어르신별 현황", style = OnItTheme.typography.SB_18, color = gray7)
            Spacer(Modifier.height(16.dp))

            ElderStatusCard(
                "김순자",
                77,
                "홍길동",
                executionCount = 4,
                totalCount = 6,
                onClick = navigateToElderStatus
            ) // 이후 실제 값으로 수정
        }
    }
}

@Preview
@Composable
private fun CenterHomePrev() {
    CenterHomeScreen(padding = PaddingValues(), navigateToElderStatus = {})

}