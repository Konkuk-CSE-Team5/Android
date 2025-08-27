package com.konkuk.hackathon.feature.signup.volunteer

import android.R.attr.enabled
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.insert
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.signup.component.SignUpTopBar

@Composable
fun VolunteerSignUpScreen(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: VolunteerSignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val enabled by remember { mutableStateOf(viewModel.buttonEnabled) }

    VolunteerSignUpScreen(
        padding = padding,
        uiState = uiState,
        enabled = enabled,
        navigateToHome = navigateToHome,
        popBackStack = popBackStack,
    )
}

@Composable
private fun VolunteerSignUpScreen(
    padding: PaddingValues,
    uiState: VolunteerSignUpUiState,
    enabled: Boolean,
    navigateToHome: () -> Unit,
    popBackStack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        SignUpTopBar(
            modifier = Modifier.align(Alignment.TopCenter),
            title = "봉사자 회원가입",
            onBackClick = popBackStack
        )
        VolunteerSignUpContent(
            uiState = uiState,
        )

        OnItButtonPrimaryContent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(20.dp),
            text = "회원가입",
            enabled = enabled,
            onClick = navigateToHome
        )
    }
}

@Composable
fun VolunteerSignUpContent(
    uiState: VolunteerSignUpUiState,
    updateAllTermsAccepted: (Boolean) -> Unit = {},
    updatePrivacyTermsAccepted: (Boolean) -> Unit = {},
    updateGender: (Gender) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
        )
        SignUpInputField(
            state = uiState.nameState,
            title = "이름",
            placeholder = "이름",
        )
        SignUpInputField(
            state = uiState.birthState,
            title = "생년월일",
            placeholder = "YYYY / MM / DD",
            inputTransformation = InputTransformation {
                // 1900.01.01 dlgn ~ 2024.00.00 dlwjs
                if (asCharSequence().isNotEmpty()) {
                    if (asCharSequence().last().isDigit().not()) revertAllChanges()
                    if (length > 11) {
                        revertAllChanges()
                    }
                }
            },
            outputTransformation = OutputTransformation {
                if (length > 4) insert(4, "/")
                if (length > 6) insert(7, "/")
            }
        )

        Column {
            Text(
                text = "성별",
                style = OnItTheme.typography.Body1_N_M.copy(
                    color = Gray_7,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                )
            )
            VerticalSpacer(10.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .border(
                            width = 1.dp,
                            color = Gray_2,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clip(RoundedCornerShape(14.dp))
                ) {
                    Gender.entries.dropLast(1).forEach { gender ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(
                                    color =
                                        if (uiState.gender == gender) OnItTheme.colors.primary.content
                                        else Color.White
                                )
                                .clickable { updateGender(gender) }
                        ) {
                            Text(
                                text = gender.label,
                                modifier = Modifier
                                    .align(Alignment.Center),
                                style = OnItTheme.typography.Body1_N_M.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color =
                                        if (uiState.gender == gender) Color.White
                                        else Color.Black
                                )
                            )
                        }
                    }
                }
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.Center),
                    color = Gray_2,
                    thickness = 1.dp,
                )
            }
        }

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
                            color = if (uiState.isAllTermsAccepted) OnItTheme.colors.primary.content else Gray_2,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                HorizontalSpacer(8.dp)
                Text(
                    text = "이용약관 동의 (필수)",
                    style = OnItTheme.typography.Body1_N_M.copy(
                        color = Gray_7,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
            VerticalSpacer(8.dp)
            Row(
                modifier = Modifier
                    .clickable { updatePrivacyTermsAccepted(uiState.isAllTermsAccepted) }
                    .padding(vertical = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            color = if (uiState.isAllTermsAccepted) OnItTheme.colors.primary.content else Gray_2,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                HorizontalSpacer(8.dp)
                Text(
                    text = "개인정보 처리방침 동의 (필수)",
                    style = OnItTheme.typography.Body1_N_M.copy(
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
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            style = OnItTheme.typography.Body1_N_M.copy(
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
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VolunteerSignUpScreenPreview() {
    OnItTheme {
        VolunteerSignUpScreen(
            padding = PaddingValues(),
            enabled = true,
            uiState = VolunteerSignUpUiState(),
            navigateToHome = {},
            popBackStack = {}
        )
    }
}