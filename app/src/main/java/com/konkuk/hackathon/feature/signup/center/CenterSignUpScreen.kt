package com.konkuk.hackathon.feature.signup.center

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.insert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.core.common.component.HorizontalSpacer
import com.konkuk.hackathon.core.common.component.OnItButtonPrimaryContent
import com.konkuk.hackathon.core.common.component.OnItTextField
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_7
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.center.CenterActivity
import com.konkuk.hackathon.feature.signup.component.SignUpTopBar
import com.konkuk.hackathon.feature.signup.volunteer.SignUpInputField
import kotlin.jvm.java

@Composable
fun CenterSignUpScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: CenterSignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val buttonEnabled by remember {
//        derivedStateOf {
//            uiState.idValid && uiState.passwordValid && uiState.nameValid && uiState.representativeValid && uiState.phoneNumberValid && uiState.isAllTermsAccepted && uiState.isPrivacyTermsAccepted
//        }
        mutableStateOf(true)
    }
    val context = LocalContext.current

    CenterSignUpScreen(
        padding = padding,
        uiState = uiState,
        enabled = buttonEnabled,
        navigateToCenter = {
            context.startActivity(
                Intent(context, CenterActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            )
        },
        popBackStack = popBackStack,
        updateAllTermsAccepted = viewModel::updateAllTermsAccepted,
        updatePrivacyTermsAccepted = viewModel::updatePrivacyTermsAccepted,
    )
}

@Composable
private fun CenterSignUpScreen(
    padding: PaddingValues,
    uiState: CenterSignUpUiState,
    enabled: Boolean,
    updateAllTermsAccepted: (Boolean) -> Unit = {},
    updatePrivacyTermsAccepted: (Boolean) -> Unit = {},
    navigateToCenter: () -> Unit,
    popBackStack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {

        CenterSignUpContent(
            uiState = uiState,
            updateAllTermsAccepted = updateAllTermsAccepted,
            updatePrivacyTermsAccepted = updatePrivacyTermsAccepted,
        )
        SignUpTopBar(
            modifier = Modifier.align(Alignment.TopCenter),
            title = "기관 회원가입",
            onBackClick = popBackStack
        )

        OnItButtonPrimaryContent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(20.dp),
            text = "회원가입",
            enabled = enabled,
            onClick = navigateToCenter
        )
    }
}

@Composable
fun CenterSignUpContent(
    uiState: CenterSignUpUiState,
    updateAllTermsAccepted: (Boolean) -> Unit = {},
    updatePrivacyTermsAccepted: (Boolean) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(top = 72.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SignUpInputField(
            state = uiState.idState,
            title = "아이디",
            placeholder = "아이디",
        )
        SignUpInputField(
            state = uiState.passwordState,
            title = "비밀번호",
            placeholder = "비밀번호",
            isPassword = true,
        )
        SignUpInputField(
            state = uiState.nameState,
            title = "이름",
            placeholder = "이름",
        )
        // 기관명
        // 담당자명
        // 담당자 전화번호
        SignUpInputField(
            state = uiState.representativeState,
            title = "담당자명",
            placeholder = "담당자명",
        )
        SignUpInputField(
            state = uiState.phoneNumberState,
            title = "담당자 전화번호",
            placeholder = "담당자 전화번호",
            inputTransformation = InputTransformation {
                if (asCharSequence().isNotEmpty()) {
                    if (asCharSequence().last().isDigit().not()) revertAllChanges()
                    if (length > 11) {
                        revertAllChanges()
                    }
                }
            },
            outputTransformation = OutputTransformation {
                if (length > 3) insert(3, "-")
                if (length > 8) insert(8, "-")
            },
            keyboardType = KeyboardType.Number
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .clickable { updateAllTermsAccepted(uiState.isAllTermsAccepted) }
                    .padding(vertical = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            color = if (uiState.isAllTermsAccepted) Main_Primary else Gray_2,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                HorizontalSpacer(8.dp)
                Text(
                    text = "이용약관 동의 (필수)",
                    style = OnItTheme.typography.R_15.copy(
                        color = Gray_7,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
            VerticalSpacer(8.dp)
            Row(
                modifier = Modifier
                    .clickable { updatePrivacyTermsAccepted(uiState.isPrivacyTermsAccepted) }
                    .padding(vertical = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            color = if (uiState.isPrivacyTermsAccepted) Main_Primary else Gray_2,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                HorizontalSpacer(8.dp)
                Text(
                    text = "개인정보 처리방침 동의 (필수)",
                    style = OnItTheme.typography.R_15.copy(
                        color = Gray_7,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
        }
    }
}

@Composable
fun SignUpInputField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    title: String,
    placeholder: String,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            style = OnItTheme.typography.R_17.copy(
                color = Gray_7,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        OnItTextField(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            placeHolder = placeholder,
            interactionSource = interactionSource,
            inputTransformation = inputTransformation,
            outputTransformation = outputTransformation,
            isFocused = isFocused,
            isPassword = isPassword,
            keyboardType = keyboardType,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CenterSignUpScreenPreview() {
    OnItTheme {
        CenterSignUpScreen(
            padding = PaddingValues(),
            enabled = true,
            uiState = CenterSignUpUiState(),
            navigateToCenter = {},
            popBackStack = {}
        )
    }
}