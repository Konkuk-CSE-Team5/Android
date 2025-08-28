package com.konkuk.hackathon.feature.center.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.data.model.HealthStatusType
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.MindCondition

@Composable
fun ElderHealthChip(healthStatusType: HealthStatusType, modifier: Modifier = Modifier) {
    val textColor = when (healthStatusType) {
        HealthStatusType.GOOD -> OnItTheme.colors.positive
        HealthStatusType.NORMAL -> OnItTheme.colors.primary
        HealthStatusType.BAD -> OnItTheme.colors.negative
    }

    val containerColor = when (healthStatusType) {
        HealthStatusType.GOOD -> OnItTheme.colors.positive_container
        HealthStatusType.NORMAL -> OnItTheme.colors.primary_container
        HealthStatusType.BAD -> OnItTheme.colors.negative_container
    }
    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(containerColor)
    ) {
        Text(
            healthStatusType.label,
            style = OnItTheme.typography.B_12,
            color = textColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun ElderHealthChip(healthStatusType: MindCondition, modifier: Modifier = Modifier) {
    val textColor = when (healthStatusType) {
        MindCondition.GOOD -> OnItTheme.colors.positive
        MindCondition.SOSO -> OnItTheme.colors.primary
        MindCondition.BAD -> OnItTheme.colors.negative
    }

    val containerColor = when (healthStatusType) {
        MindCondition.GOOD -> OnItTheme.colors.positive_container
        MindCondition.SOSO -> OnItTheme.colors.primary_container
        MindCondition.BAD -> OnItTheme.colors.negative_container
    }
    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(containerColor)
    ) {
        Text(
            healthStatusType.label,
            style = OnItTheme.typography.B_12,
            color = textColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}