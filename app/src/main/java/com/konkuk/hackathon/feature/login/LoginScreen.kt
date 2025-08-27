package com.konkuk.hackathon.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun LoginScreen(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
}

@Composable
private fun LoginScreen(
    padding: PaddingValues,
    uiState: LoginUiState,
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {

    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    OnItTheme {
//        LoginScreen()
    }
}