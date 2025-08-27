package com.konkuk.hackathon.core.feature.user.setting.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun UserSettingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = OnItTheme.colors.white)
    ) {
        Box(modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(OnItTheme.colors.white)
            .padding(horizontal = 10.dp),) {
            Text("설정", style = OnItTheme.typography.SB_24, color = OnItTheme.colors.gray7)
        }
    }
}


@Preview
@Composable
private fun UserSettingPreview() {
    UserSettingScreen()
}