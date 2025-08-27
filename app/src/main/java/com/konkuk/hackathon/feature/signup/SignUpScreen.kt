package com.konkuk.hackathon.feature.signup

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import com.konkuk.hackathon.core.common.component.OnItButtonPrimaryContent
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.signup.component.SignUpTopBar

@Composable
fun SignUpScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToVolunteerSignUp: () -> Unit,
    navigateToOrganizationSignUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        SignUpTopBar(
            title = "회원가입",
            onBackClick = popBackStack
        )
        VerticalSpacer(24.dp)
        SignUpType.entries.forEach { signUpType ->
            SignUpTypeComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                signUpType = signUpType,
                onClick = {
                    when (it) {
                        SignUpType.VOLUNTEER -> navigateToVolunteerSignUp()
                        SignUpType.ORGANIZATION -> navigateToOrganizationSignUp()
                    }
                }
            )
            VerticalSpacer(16.dp)
        }
    }
}

@Composable
fun SignUpTypeComponent(
    modifier: Modifier = Modifier,
    signUpType: SignUpType,
    onClick: (SignUpType) -> Unit,
) {
    // 피그마보다 조금 더 여유름 줌.
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Gray_2,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 30.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = signUpType.label,
            style = OnItTheme.typography.H4_B.copy(color = Gray_7)
        )
        Text(
            text = signUpType.description,
            style = OnItTheme.typography.H4_B.copy(
                color = Gray_7,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        )
        OnItButtonPrimaryContent(
            modifier = Modifier.fillMaxWidth(),
            text = "시작하기",
            height = 40.dp,
            onClick = { onClick(signUpType) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    OnItTheme {
        SignUpScreen(
            padding = PaddingValues(),
            popBackStack = {},
            navigateToVolunteerSignUp = {},
            navigateToOrganizationSignUp = {}
        )
    }
}