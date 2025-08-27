package com.konkuk.hackathon.feature.login.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.common.component.OnItTextField
import com.konkuk.hackathon.core.common.component.VerticalSpacer

@Composable
fun LoginInputField(
    modifier: Modifier = Modifier,
    idState: TextFieldState,
    passwordState: TextFieldState,
) {
    val idInteractionSource = remember { MutableInteractionSource() }
    val passwordInteractionSource = remember { MutableInteractionSource() }

    val idFieldFocused = idInteractionSource.collectIsFocusedAsState().value
    val passwordFieldFocused = passwordInteractionSource.collectIsFocusedAsState().value

    Column(modifier = modifier) {
        OnItTextField(
            state = idState,
            placeHolder = "아이디",
            interactionSource = idInteractionSource,
            isFocused = idFieldFocused
        )
        VerticalSpacer(13.dp)
        OnItTextField(
            state = passwordState,
            placeHolder = "비밀번호",
            interactionSource = passwordInteractionSource,
            isFocused = passwordFieldFocused
        )
    }
}