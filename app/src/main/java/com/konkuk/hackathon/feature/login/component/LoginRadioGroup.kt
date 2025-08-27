package com.konkuk.hackathon.feature.login.component

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.common.component.HorizontalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary_Container
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.login.LoginType

@Composable
fun LoginRadioGroup(
    modifier: Modifier = Modifier,
    selectedType: LoginType = LoginType.VOLUNTEER,
    onTypeSelected: (LoginType) -> Unit = { },
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp),
    ) {
        LoginType.entries.forEach { type ->
            Row(
                modifier = Modifier
                    .clickable { onTypeSelected(type) }
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = if(type == selectedType) Main_Primary_Container else Gray_2
                        )
                ) {
                    if (type == selectedType) {
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .background(
                                    shape = CircleShape,
                                    color = Main_Primary
                                )
                                .align(Alignment.Center)
                        )
                    }
                }
                Text(
                    text = type.label,
                    style = OnItTheme.typography.R_15.copy(color = Gray_7)
                )
            }
            HorizontalSpacer(8.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInRadioGroupPreview() {
    OnItTheme {
        LoginRadioGroup()
    }
}