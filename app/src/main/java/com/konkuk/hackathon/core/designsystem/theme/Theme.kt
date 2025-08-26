package com.konkuk.hackathon.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object OnItTheme{
    val colors: OnItColors
        @Composable
        @ReadOnlyComposable
        get() = LocalOnItColorsProvider.current
    // 폰트 추가
    val typography: OnItTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalOnItTypographyProvider.current
}

@Composable
fun OnItTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}