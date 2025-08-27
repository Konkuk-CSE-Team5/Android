package com.konkuk.hackathon.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.OnItButtonPrimaryContainer
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun OnboardingScreen(
    padding: PaddingValues,
    navigateToLogin: () -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VerticalSpacer(203f)
        Image(
            painter = painterResource(R.drawable.img_onboarding_logo),
            contentDescription = null,
        )
        VerticalSpacer(9f)
        Text(
            text = "소개소개소개소개소개소개소개\n소개소개소개소개소개",
            style = OnItTheme.typography.M_16.copy(color = Gray_7, fontSize = 18.sp),
            textAlign = TextAlign.Center,
        )
        VerticalSpacer(322f)
        OnItButtonPrimaryContainer(
            modifier = Modifier.fillMaxWidth(),
            text = "시작하기",
            onClick = navigateToLogin,
        )
        VerticalSpacer(20.dp)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    OnItTheme {
        OnboardingScreen(
            padding = PaddingValues(0.dp),
            navigateToLogin = {},
        )
    }
}