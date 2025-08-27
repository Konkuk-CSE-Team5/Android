package com.konkuk.hackathon.feature.user.setting.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun MyInfoTextField(value: String, modifier: Modifier = Modifier, category : String, placeHolder: String, onValueChange: (String) -> Unit,keyboardType: KeyboardType = KeyboardType.Text,visualTransformation: VisualTransformation = VisualTransformation.None,
                    maxLength: Int = Int.MAX_VALUE) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            category,
            color = OnItTheme.colors.gray7,
            style = OnItTheme.typography.M_17
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            modifier = modifier.fillMaxWidth(),
            placeholder = { Text(placeHolder, style = OnItTheme.typography.M_16) },
            shape = RoundedCornerShape(14.dp),
            visualTransformation = visualTransformation,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = OnItTheme.colors.white,
                unfocusedPlaceholderColor = OnItTheme.colors.gray3,
                unfocusedBorderColor = OnItTheme.colors.gray2,
                unfocusedTextColor = OnItTheme.colors.gray7,
                focusedContainerColor = OnItTheme.colors.white,
                focusedTextColor = OnItTheme.colors.gray7,
                focusedBorderColor = OnItTheme.colors.primary,
                focusedPlaceholderColor = OnItTheme.colors.gray3,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            textStyle = OnItTheme.typography.M_16
        )
    }
}

@Preview
@Composable
private fun MyInfoTextFieldPreview() {
    var id by remember { mutableStateOf("hoban924")}
    var pw by remember { mutableStateOf("hoban924")}
    Column() {
        MyInfoTextField(
            value = id,
            category = "아이디",
            placeHolder = "아이디",
            onValueChange = { id = it },
            maxLength = 20
        )
        MyInfoTextField(
            value = pw,
            category = "비밀번호",
            placeHolder = "비밀번호",
            onValueChange = { pw = it },
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            maxLength = 20
        )
    }
}