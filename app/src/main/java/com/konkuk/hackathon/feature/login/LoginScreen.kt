package com.konkuk.hackathon.feature.login

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.HorizontalSpacer
import com.konkuk.hackathon.core.common.component.OnItButtonPrimaryContent
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.common.extension.noRippleClickable
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.center.CenterActivity
import com.konkuk.hackathon.feature.login.component.LoginInputField
import com.konkuk.hackathon.feature.login.component.LoginRadioGroup
import com.konkuk.hackathon.feature.volunteer.VolunteerActivity

@Composable
fun LoginScreen(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LoginScreen(
        padding = padding,
        uiState = uiState,
        navigateToHome = {
            if (uiState.loginType == LoginType.VOLUNTEER) {
                context.startActivity(
                    Intent(context, VolunteerActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                )
            } else {
                context.startActivity(
                    Intent(context, CenterActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                )
            }
        },
        navigateToSignUp = navigateToSignUp,
        updateSignInType = { viewModel.updateLoginType(it) }

    )
}

@Composable
private fun LoginScreen(
    padding: PaddingValues,
    uiState: LoginUiState,
    updateSignInType: (LoginType) -> Unit,
    navigateToHome: () -> Unit = { },
    navigateToSignUp: () -> Unit = { },
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VerticalSpacer(64.dp)
        VerticalSpacer(55.dp)
        Image(
            painter = painterResource(R.drawable.img_onboarding_logo),
            contentDescription = null,
        )
        VerticalSpacer(27.dp)
        LoginRadioGroup(
            selectedType = uiState.loginType,
            onTypeSelected = updateSignInType,
        )
        VerticalSpacer(18.dp)
        LoginInputField(
            modifier = Modifier.fillMaxWidth(),
            idState = uiState.idState,
            passwordState = uiState.passwordState
        )
        VerticalSpacer(9.dp)
        OnItButtonPrimaryContent(
            modifier = Modifier
                .fillMaxWidth(),
            text = "로그인",
            onClick = navigateToHome
        )
        VerticalSpacer(24.dp)
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = "아이디 찾기",
                style = OnItTheme.typography.B_17.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            HorizontalSpacer(8.dp)
            VerticalDivider(
                modifier = Modifier.fillMaxHeight(),
                color = Color.Black,
            )
            HorizontalSpacer(8.dp)
            Text(
                text = "비밀번호 재설정",
                style = OnItTheme.typography.B_17.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
        VerticalSpacer(14.dp)
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .noRippleClickable(
                    onClick = navigateToSignUp,
                )
        ) {
            Text(
                text = "처음이신가요?",
                style = OnItTheme.typography.B_17.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            HorizontalSpacer(8.dp)
            Text(
                text = "회원가입",
                style = OnItTheme.typography.B_17.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Main_Primary
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    OnItTheme {
        LoginScreen(
            padding = PaddingValues(),
            uiState = LoginUiState(),
            updateSignInType = {},
        )
    }
}