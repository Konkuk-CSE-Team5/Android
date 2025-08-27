package com.konkuk.hackathon.feature.user.setting.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val phoneNum = text.text.filter { it.isDigit() }.take(11)

        val transformedText = buildString {
            for (i in phoneNum.indices) {
                append(phoneNum[i])
                if (i == 2 || i == 6) {
                    append('-')
                }
            }
        }

        val offsetMapping = object : androidx.compose.ui.text.input.OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 6 -> offset + 1
                    offset <= 11 -> offset + 2
                    else -> transformedText.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 3 -> offset
                    offset <= 7 -> offset - 1
                    offset <= 13 -> offset - 2
                    else -> phoneNum.length
                }
            }
        }
        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }
}

class BirthTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val birth = text.text.filter { it.isDigit() }.take(8)

        val transformedText = buildString {
            birth.forEachIndexed { index, c ->
                if (index == 4 || index == 6) {
                    append(' ')
                    append('/')
                    append(' ')
                }

                append(c)
            }
        }

        val offsetMapping = object : androidx.compose.ui.text.input.OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 6 -> offset + 3
                    offset <= 8 -> offset + 6
                    else -> transformedText.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 9 -> offset - 3
                    offset <= 14 -> offset - 6
                    else -> birth.length
                }
            }
        }
        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }

}