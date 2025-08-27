package com.konkuk.hackathon.feature.user.setting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun GenderButton(modifier: Modifier = Modifier, isMale: Boolean, onClick: (Boolean) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(14.dp))
            .background(
                OnItTheme.colors.white
            )
    ) {
        Box(
            Modifier
                .weight(1f)
                .background(color = if (isMale) OnItTheme.colors.primary_container else OnItTheme.colors.white)
                .border(
                    width = 1.2.dp,
                    color = if (isMale) OnItTheme.colors.primary else OnItTheme.colors.gray2,
                    shape = RoundedCornerShape(topStart = 14.dp, bottomStart = 14.dp)
                )
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = { onClick(true) },
                ),

            contentAlignment = Alignment.Center
        ) {
            Text(
                "남성",
                color = if (isMale) OnItTheme.colors.primary else OnItTheme.colors.gray7,
                style = if (isMale) OnItTheme.typography.B_17 else OnItTheme.typography.M_16,
                modifier = Modifier
                    .padding(vertical = if (isMale) 13.5.dp else 14.dp)
            )
        }
        Box(
            Modifier
                .weight(1f)
                .background(color = if (!isMale) OnItTheme.colors.primary_container else OnItTheme.colors.white)
                .border(
                    width = 1.2.dp,
                    color = if (!isMale) OnItTheme.colors.primary else OnItTheme.colors.gray2,
                    shape = RoundedCornerShape(topEnd = 14.dp, bottomEnd = 14.dp)
                )
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = { onClick(false) }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "여성",
                color = if (!isMale) OnItTheme.colors.primary else OnItTheme.colors.gray7,
                style = if (!isMale) OnItTheme.typography.B_17 else OnItTheme.typography.M_16,
                modifier = Modifier
                    .padding(vertical = if (!isMale) 13.5.dp else 14.dp)

            )
        }
    }
}