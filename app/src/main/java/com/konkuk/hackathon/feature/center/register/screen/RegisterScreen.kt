package com.konkuk.hackathon.feature.center.register.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.common.component.VerticalSpacer
import com.konkuk.hackathon.core.designsystem.theme.Main_Primary
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.center.register.viewmodel.RegisterUiState
import com.konkuk.hackathon.feature.center.register.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    padding: PaddingValues,
    navigateToElderManagement: (Long) -> Unit,
    navigateToElderRegister: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "등록",
                    style = OnItTheme.typography.SB_24.copy(color = OnItTheme.colors.gray7)
                )
            }

            RegisterScreen(
                uiState = uiState,
                onManageClick = navigateToElderManagement,
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            shape = CircleShape,
            onClick = { navigateToElderRegister() },
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Main_Primary),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "어르신 등록",
                    tint = Color.White
                )
            }
        }
    }

}

@Composable
private fun RegisterScreen(
    uiState: RegisterUiState,
    onManageClick: (Long) -> Unit = { },
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text(
                text = "등록된 어르신 목록",
                style = OnItTheme.typography.SB_20.copy(
                    fontSize = 20.sp,
                    color = OnItTheme.colors.gray7
                )
            )
        }
        items(uiState.elders) { elder ->
            ElderComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onManageClick(elder.id) },
                elder = elder,
            )
        }
        item {
            VerticalSpacer(60.dp)
        }
    }
}

@Composable
fun ElderComponent(
    modifier: Modifier = Modifier,
    elder: RegisterUiState.Elder,
) {
    val clipboardManager = LocalClipboardManager.current
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = OnItTheme.colors.gray2,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = "${elder.name} | ${elder.age}세",
                style = OnItTheme.typography.B_17,
                color = OnItTheme.colors.gray7,
            )
            VerticalSpacer(4.dp)
            Row(
                modifier = Modifier
                    .clickable(onClick = {
                        clipboardManager.setText(AnnotatedString(elder.code))
                    })
                    .padding(vertical = 9.5.dp),
                horizontalArrangement = Arrangement.spacedBy(3.dp),
            ) {
                Text(
                    text = "코드 ${elder.code}",
                    style = OnItTheme.typography.R_14,
                    color = OnItTheme.colors.gray7,
                )
                Icon(
                    painter = painterResource(R.drawable.ic_copy),
                    contentDescription = null,
                )
                MatchStatueChip(statue = elder.status)
            }
        }
        Box(
            modifier = Modifier
                .size(80.dp, 32.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(
                    shape = RoundedCornerShape(6.dp),
                    color = OnItTheme.colors.gray1,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "관리",
                style = OnItTheme.typography.M_16.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                ),
            )
        }
    }
}

@Composable
fun MatchStatueChip(
    modifier: Modifier = Modifier,
    statue: RegisterUiState.ElderStatus,
) {
    val backgroundColor = when (statue) {
        RegisterUiState.ElderStatus.MATCHING -> OnItTheme.colors.negative_container
        RegisterUiState.ElderStatus.SUCCESS -> OnItTheme.colors.positive_container
        RegisterUiState.ElderStatus.FINISH -> OnItTheme.colors.primary_container
    }
    val textColor = when (statue) {
        RegisterUiState.ElderStatus.MATCHING -> OnItTheme.colors.negative
        RegisterUiState.ElderStatus.SUCCESS -> OnItTheme.colors.positive
        RegisterUiState.ElderStatus.FINISH -> OnItTheme.colors.primary
    }

    Box(
        modifier = modifier
            .size(60.dp, 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = statue.label,
            style = OnItTheme.typography.B_12.copy(color = textColor),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    OnItTheme {
        RegisterScreen(
            uiState = RegisterUiState(
                elders = listOf(
                    RegisterUiState.Elder(
                        id = 1L,
                        name = "홍길동",
                        age = 80,
                        code = "123456",
                        status = RegisterUiState.ElderStatus.MATCHING,
                    ),
                    RegisterUiState.Elder(
                        id = 2L,
                        name = "김철수",
                        age = 75,
                        code = "654321",
                        status = RegisterUiState.ElderStatus.SUCCESS,
                    ),
                    RegisterUiState.Elder(
                        id = 3L,
                        name = "박영희",
                        age = 82,
                        code = "112233",
                        status = RegisterUiState.ElderStatus.FINISH,
                    ),
                )
            )
        )
    }
}