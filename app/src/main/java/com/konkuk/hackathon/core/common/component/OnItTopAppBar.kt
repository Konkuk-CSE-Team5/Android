package com.konkuk.hackathon.core.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.extension.noRippleClickable
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun OnItTopAppBar(title: String, popBackStack: () -> Unit, modifier: Modifier = Modifier) {
    Box {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painterResource(R.drawable.ic_arrow_back),
                contentDescription = "뒤 화살표 아이콘",
                tint = OnItTheme.colors.gray7,
                modifier = Modifier.noRippleClickable(onClick = popBackStack)
            )
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = OnItTheme.typography.SB_20,
                    color = OnItTheme.colors.black
                )
            }
            Box(modifier = Modifier.size(24.dp))

        }
    }
}