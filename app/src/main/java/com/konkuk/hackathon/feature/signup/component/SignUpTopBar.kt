package com.konkuk.hackathon.feature.signup.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun SignUpTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "뒤로가기",
            )
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = OnItTheme.typography.B_17.copy(
                color = Gray_7,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInTopBarPreview() {
    OnItTheme {
        SignUpTopBar(
            title = "회원가입",
            onBackClick = {},
        )
    }
}