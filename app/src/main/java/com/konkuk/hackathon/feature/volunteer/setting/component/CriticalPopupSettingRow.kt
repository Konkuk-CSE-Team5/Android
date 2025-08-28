package com.konkuk.hackathon.feature.volunteer.setting.component

import android.widget.Switch
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.network.service.PrefsEntryPoint
import dagger.hilt.android.EntryPointAccessors

@Composable
fun CriticalPopupSettingRow() {
    val ctx = LocalContext.current
    val prefs = remember {
        EntryPointAccessors.fromApplication(ctx.applicationContext, PrefsEntryPoint::class.java)
            .appPreferences()
    }
    var enabled by remember { mutableStateOf(prefs.criticalPopupEnabled()) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("잠금화면 팝업(중요 알림)", style = OnItTheme.typography.M_16, color = OnItTheme.colors.gray7)
        Spacer(Modifier.weight(1f))
        Switch(
            checked = enabled,
            onCheckedChange = {
                enabled = it
                prefs.setCriticalPopupEnabled(it)
            }
        )
    }
}