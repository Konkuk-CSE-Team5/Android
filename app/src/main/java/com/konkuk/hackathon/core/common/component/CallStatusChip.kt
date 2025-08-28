package com.konkuk.hackathon.core.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun CallStatusChip(callStatusType: CallStatusType, modifier: Modifier = Modifier) {
    val textColor = when (callStatusType) {
        CallStatusType.COMPLETE -> OnItTheme.colors.positive
        CallStatusType.PENDING -> OnItTheme.colors.primary
        CallStatusType.ABSENT -> OnItTheme.colors.negative
        else -> OnItTheme.colors.gray4
    }

    val containerColor = when (callStatusType) {
        CallStatusType.COMPLETE -> OnItTheme.colors.positive_container
        CallStatusType.PENDING -> OnItTheme.colors.primary_container
        CallStatusType.ABSENT -> OnItTheme.colors.negative_container
        else -> OnItTheme.colors.gray2
    }
    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(containerColor)
    ) {
        Text(
            callStatusType.label,
            style = OnItTheme.typography.B_12,
            color = textColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}