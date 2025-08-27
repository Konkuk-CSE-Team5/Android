package com.konkuk.hackathon.core.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.hackathon.core.designsystem.theme.Gray_2
import com.konkuk.hackathon.core.designsystem.theme.Gray_4
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun OnItTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    placeHolder: String,
    interactionSource: MutableInteractionSource,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    isFocused: Boolean = false,
) {
    BasicTextField(
        state = state,
        modifier = modifier,
        enabled = true,
        inputTransformation = inputTransformation,
        textStyle = OnItTheme.typography.B_17.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        interactionSource = interactionSource,
        outputTransformation = outputTransformation,
        decorator = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 54.dp)
                    .border(
                        width = 1.dp,
                        color = if (isFocused) Color(0xFFFFF4EB) else Gray_2,
                        shape = RoundedCornerShape(14.dp),
                    )
                    .padding(vertical = 14.dp)
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (state.text.isEmpty()) {
                    Text(
                        text = placeHolder,
                        style = OnItTheme.typography.B_17.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Gray_4
                        )
                    )
                }
                innerTextField()
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun OnIntTextFieldPreview() {
    OnItTheme {
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused = interactionSource.collectIsFocusedAsState().value
        OnItTextField(
            state = TextFieldState(),
            placeHolder = "텍스트를 입력하세요",
            interactionSource = interactionSource,
            modifier = Modifier.padding(16.dp),
            isFocused = isFocused,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnIntTextFieldPreview2() {
    OnItTheme {
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused = interactionSource.collectIsFocusedAsState().value
        OnItTextField(
            state = TextFieldState("hong@konkuk.ac.kr"),
            placeHolder = "텍스트를 입력하세요",
            interactionSource = interactionSource,
            modifier = Modifier.padding(16.dp),
            isFocused = true,
        )
    }
}