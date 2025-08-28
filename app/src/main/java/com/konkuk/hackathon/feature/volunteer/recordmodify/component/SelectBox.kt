package com.konkuk.hackathon.feature.volunteer.recordmodify.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.common.extension.noRippleClickable
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary_Container
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun RowScope.SelectBox(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String = "",
    emoji: String? = null,
    verticalPadding: Dp = 13.5.dp,
    onClick: () -> Unit = { },
) {
    Box(
        modifier = modifier
            .noRippleClickable(onClick = onClick)
            .weight(1f)
            .background(
                color = if (isSelected) Main_Primary_Container else Color.White,
                shape = RoundedCornerShape(14.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) Main_Primary else Gray_2,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(vertical = verticalPadding)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
        ) {
            emoji?.let {
                Text(
                    text = it,
                    style = OnItTheme.typography.SB_24.copy(
                        color = if (isSelected) Main_Primary else Gray_7
                    ),
                    textAlign = TextAlign.Center,
                )
            }
            Text(
                text = text,
                style = OnItTheme.typography.SB_14.copy(
                    color = if (isSelected) Main_Primary else Gray_7
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}