package com.konkuk.hackathon.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt



val BG = Color(0xFFFAFAFA)
val dim = Color(0xFF000000)

// Main
val Primary = Color(0xFFEE863B)
val Primary_container = Color(0xFFFFF4EB)


// System
val Warning = Color(0xFFFFCB3D)
val Warning2 = Color(0xFFFFA13D)
val Active = Color(0xFF2D8FFF)

// Positive
val Positive = Color(0xFF218A21)
val Positive_container = Color(0xFFDBFAE3)

// Negative
val Negative = Color(0xFFE6533E)
val Negative_container = Color(0xFFE6533E)

// Gray
val White = Color(0xFFFFFFFF)
val gray1 = Color(0xFFECECEC)
val gray2 = Color(0xFFD2D2D2)
val gray3 = Color(0xFFAFAFAF)
val gray4 = Color(0xFF8A8A8A)
val gray5 = Color(0xFF666666)
val gray6 = Color(0xFF454545)
val gray7 = Color(0xFF3B3B3B)
val gray8 = Color(0xFF313131)
val gray9 = Color(0xFF272727)
val gray10 = Color(0xFF1F1F1F)
val Black = Color(0xFF000000)

@Immutable
data class OnItColors(
    val bg: Color,
    val dim: Color,
    val primary: Color,
    val primary_container: Color,
    val warning: Color,
    val warning2: Color,
    val active: Color,
    val positive: Color,
    val positive_container: Color,
    val negative: Color,
    val negative_container: Color,
    val white: Color,
    val gray1: Color,
    val gray2: Color,
    val gray3: Color,
    val gray4: Color,
    val gray5: Color,
    val gray6: Color,
    val gray7: Color,
    val gray8: Color,
    val gray9: Color,
    val gray10: Color,
    val black: Color
)

val defaultOnItColors = OnItColors(
    bg = BG,
    dim = dim,
    primary = Primary,
    primary_container = Primary_container,
    warning = Warning,
    warning2 = Warning2,
    active = Active,
    positive = Positive,
    positive_container = Positive_container,
    negative = Negative,
    negative_container = Negative_container,
    white = White,
    gray1 = gray1,
    gray2 = gray2,
    gray3 = gray3,
    gray4 = gray4,
    gray5 = gray5,
    gray6 = gray6,
    gray7 = gray7,
    gray8 = gray8,
    gray9 = gray9,
    gray10 = gray10,
    black = Black
)

// CompositionLocal을 통해 앱 전역에 색상 시스템을 제공
val LocalOnItColorsProvider = staticCompositionLocalOf { defaultOnItColors }

val Gray_7 = Color("#3b3b3b")
val Gray_4 = Color("#8A8A8A")
val Gray_2 = Color("#D2D2D2")




