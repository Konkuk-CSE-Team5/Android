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
import com.konkuk.hackathon.core.designsystem.theme.Negative
import com.konkuk.hackathon.core.designsystem.theme.Negative_container
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.Positive
import com.konkuk.hackathon.core.designsystem.theme.Positive_container

@Composable
fun VolunteerMatchingChip(
    volunteerMatchingType: VolunteerMatchingType,
    modifier: Modifier = Modifier
) {
    val textColor = when (volunteerMatchingType) {
        VolunteerMatchingType.ACTIVE -> Positive
        VolunteerMatchingType.DONE -> Negative
    }

    val containerColor = when (volunteerMatchingType) {
        VolunteerMatchingType.ACTIVE -> Positive_container
        VolunteerMatchingType.DONE -> Negative_container
    }

    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(containerColor)
    ) {
        Text(
            volunteerMatchingType.label,
            style = OnItTheme.typography.B_12,
            color = textColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}

enum class VolunteerMatchingType(val label: String) {
    ACTIVE("진행중"),
    DONE("진행종료")

}