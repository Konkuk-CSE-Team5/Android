package com.konkuk.hackathon.core.navigation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.Gray_1
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Primary
import com.konkuk.hackathon.core.navigation.center.CenterTab

@Composable
internal fun CenterBottomBar(
    visible: Boolean,
    tabs: List<CenterTab>,
    currentTab: CenterTab?,
    onTabSelected: (CenterTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) },
    ) {
        Box(modifier = Modifier.background(Color.White)) {
            Column {
                HorizontalDivider(
                    color = Gray_1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )
                Row(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 10.dp)
                        .height(64.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    tabs.forEach { tab ->
                        CenterBottomBarItem(
                            tab = tab,
                            selected = tab == currentTab,
                            onClick = {
                                if (tab != currentTab) {
                                    onTabSelected(tab)
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.CenterBottomBarItem(
    tab: CenterTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = if (selected) ImageVector.vectorResource(tab.selectedIconResId)
                else ImageVector.vectorResource(tab.iconResId),
                contentDescription = tab.contentDescription,
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = tab.label,
                color = if (selected) Primary else Gray_7
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    val tabs = CenterTab.entries
    CenterBottomBar(
        visible = true,
        tabs = tabs,
        currentTab = tabs.first(),
        onTabSelected = {},
    )
}