package com.konkuk.hackathon.core.common.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun OnItButtonPrimaryContainer(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(50.dp),
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = OnItTheme.colors.primary.container,
            contentColor = Gray_7,
        )
    ) {
        Text(
            text = text,
            style = OnItTheme.typography.Body1_N_B.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
            )
        )
    }
}

@Composable
fun OnItButtonPrimaryContent(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    height: Dp = 50.dp,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(height),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = OnItTheme.colors.primary.content,
            contentColor = Color.White,
        )
    ) {
        Text(
            text = text,
            style = OnItTheme.typography.Body1_N_B.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
            )
        )
    }
}