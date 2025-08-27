package com.konkuk.hackathon.feature.volunteer.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.common.extension.noRippleClickable
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun StatusButton(
    content: String,
    isSelected: Boolean = false,
    emoji: String? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .clip(RoundedCornerShape(14.dp))
            .background(
                color = if (isSelected)
                    OnItTheme.colors.primary_container
                else OnItTheme.colors.white
            )
            .border(
                width = 1.dp, color = if (isSelected)
                    OnItTheme.colors.primary
                else OnItTheme.colors.gray2,
                shape = RoundedCornerShape(14.dp)
            )
            .noRippleClickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 18.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (emoji != null) Text(emoji, style = OnItTheme.typography.B_20)
            Text(
                content,
                style = if (isSelected) OnItTheme.typography.SB_14
                else OnItTheme.typography.R_14,
                color = if (isSelected) OnItTheme.colors.primary
                else OnItTheme.colors.gray7
            )
        }
    }
}
