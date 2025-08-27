package com.konkuk.hackathon.feature.volunteer.setting.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * 한국 전화번호 하이픈 자동 삽입:
 * - 02:        02-XXX-XXXX 또는 02-XXXX-XXXX
 * - 010:       010-XXXX-XXXX
 * - 011/016/017/018/019: 3-3-4 (예: 011-XXX-XXXX)
 * - 070/050/080:         3-4-4
 * - 15xx/16xx/18xx:      4-4 (예: 1588-XXXX)
 * - 그 외 0xx 지역번호:   3-3-4 또는 3-4-4
 */

class PhoneTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter(Char::isDigit)
            .take(11) // 일반 최대 11자리 (대표번호 15xx류는 8자리지만 여기선 일단 11까지 허용)

        val breaks = computeBreaks(digits)

        // 하이픈 삽입
        val sb = StringBuilder()
        for (i in digits.indices) {
            sb.append(digits[i])
            val pos = i + 1 // 원본에서 지금까지 입력한 글자 수(1-based)
            if (pos != digits.length && breaks.contains(pos)) {
                sb.append('-')
            }
        }
        val transformed = sb.toString()

        // 커서 매핑
        val mapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // 원본 offset 이전/같은 위치에 들어간 하이픈 개수만큼 이동
                val extra = breaks.count { it <= offset }
                return (offset + extra).coerceAtMost(transformed.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                // 변환 문자열에서 offset 이전에 등장한 하이픈 개수만큼 감소
                var adjusted = offset
                for (i in breaks.indices) {
                    val hyphenIndexInTransformed = breaks[i] + i // i번째 하이픈 위치
                    if (offset > hyphenIndexInTransformed) adjusted-- else break
                }
                return adjusted.coerceAtMost(digits.length)
            }
        }

        return TransformedText(AnnotatedString(transformed), mapping)
    }

    private fun computeBreaks(d: String): List<Int> {
        if (d.isEmpty()) return emptyList()

        // 15xx/16xx/18xx 대표번호 (예: 1588-XXXX)
        if (d.startsWith("15") || d.startsWith("16") || d.startsWith("18")) {
            return if (d.length <= 4) emptyList() else listOf(4)
        }

        return when {
            // 서울 02: 2-3-4 또는 2-4-4
            d.startsWith("02") -> when {
                d.length <= 2 -> emptyList()
                d.length <= 5 -> listOf(2)         // 02-XXX...
                d.length <= 9 -> listOf(2, 5)      // 02-XXX-XXXX (2-3-4)
                else -> listOf(2, 6)               // 02-XXXX-XXXX (2-4-4)
            }

            // 휴대폰 010: 3-4-4
            d.startsWith("010") -> when {
                d.length <= 3 -> emptyList()
                d.length <= 7 -> listOf(3)         // 010-XXXX...
                else -> listOf(3, 7)               // 010-XXXX-XXXX
            }

            // 예전 휴대폰 011/016/017/018/019: 3-3-4
            d.startsWith("011") || d.startsWith("016") ||
                    d.startsWith("017") || d.startsWith("018") ||
                    d.startsWith("019") -> when {
                d.length <= 3 -> emptyList()
                d.length <= 6 -> listOf(3)         // 011-XXX...
                else -> listOf(3, 6)               // 011-XXX-XXXX
            }

            // 070/050/080 등 특수 번호: 보편적으로 3-4-4
            d.startsWith("070") || d.startsWith("050") || d.startsWith("080") -> when {
                d.length <= 3 -> emptyList()
                d.length <= 7 -> listOf(3)         // 070-XXXX...
                else -> listOf(3, 7)               // 070-XXXX-XXXX
            }

            // 기타 '0'으로 시작하는 3자리 지역번호: 3-3-4 또는 3-4-4
            d.startsWith("0") -> when {
                d.length <= 3 -> emptyList()
                d.length <= 6 -> listOf(3)         // 0xx-XXX...
                d.length <= 10 -> listOf(3, 6)     // 0xx-XXX-XXXX (3-3-4)
                else -> listOf(3, 7)               // 0xx-XXXX-XXXX (3-4-4)
            }

            // 그 외: 안전한 기본값 3-4-4
            else -> when {
                d.length <= 3 -> emptyList()
                d.length <= 7 -> listOf(3)
                else -> listOf(3, 7)
            }
        }
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

        val offsetMapping = object : OffsetMapping {
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