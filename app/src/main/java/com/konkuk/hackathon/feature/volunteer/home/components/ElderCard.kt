package com.konkuk.hackathon.feature.volunteer.home.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme


/**
 * * @param phone 010-1234-5678 형식
 */
@Composable
fun ElderCard(
    elderName: String,
    age: Int,
    phone: String, // 010-1234-5678 형식
    onCallClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxWidth()
            .background(OnItTheme.colors.white)
            .border(
                1.dp,
                OnItTheme.colors.gray2,
                shape = RoundedCornerShape(14.dp)

            )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "$elderName 어르신",
                        style = OnItTheme.typography.B_20,
                        color = OnItTheme.colors.gray7
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "만 ${age}세",
                        style = OnItTheme.typography.R_14,
                        color = OnItTheme.colors.gray4
                    )
                }
                Box(
                    Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(OnItTheme.colors.primary_container)
                ) {
                    Text(
                        "다음 봉사: 8/28(목)",
                        style = OnItTheme.typography.B_17.copy(
                            fontSize = 12.sp
                        ),
                        color = OnItTheme.colors.primary,
                        maxLines = 1,
                        modifier = Modifier.padding(vertical = 7.dp, horizontal = 13.dp)
                    )
                }
            }
            Column {
                Text(
                    "스케줄: 주 2회 · 화/목 · 19:00",
                    style = OnItTheme.typography.R_14,
                    color = OnItTheme.colors.gray7
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "특이사항: 당뇨, 주행보조기 사용",
                    style = OnItTheme.typography.R_14,
                    color = OnItTheme.colors.gray7
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(OnItTheme.colors.primary)
                    .clickable(onClick = {

                        val intent = Intent(Intent.ACTION_CALL, "tel:$phone".toUri())

                        try {
                            context.startActivity(intent)
                        } catch (e: SecurityException) {
                            Toast.makeText(context, "통화 오류입니다. 다시 시도해주세요.", Toast.LENGTH_SHORT)
                                .show()
                        }
                        onCallClick()
                    })
            ) {
                Text(
                    "전화걸기",
                    style = OnItTheme.typography.B_17,
                    color = OnItTheme.colors.white,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 13.dp)
                )

            }
        }
    }
}
