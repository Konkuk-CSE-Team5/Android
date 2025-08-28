package com.konkuk.hackathon.core.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.designsystem.theme.gray1

@Composable
fun OnItProgressIndicator(progress: () -> Float, modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp),
        trackColor = gray1,
        color = OnItTheme.colors.primary,
        strokeCap = StrokeCap.Round,
        gapSize = (-15).dp,
        drawStopIndicator = {}

    )
}

@Preview
@Composable
private fun IndicatorPrev() {
    OnItProgressIndicator(progress = { 0.5f })
}