package com.konkuk.hackathon.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.konkuk.hackathon.R

// Combine all font weights into a single FontFamily object
val pretendardFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

@Immutable
data class OnItTypography(
    // Title
    val B_30: TextStyle,
    val B_28: TextStyle,
    val B_26: TextStyle,
    val SB_24: TextStyle,
    val SB_22: TextStyle,
    val B_20: TextStyle,
    val SB_20: TextStyle,
    val M_20: TextStyle,

    // Body
    val SB_18: TextStyle,
    val R_18: TextStyle,
    val B_17: TextStyle,
    val M_17: TextStyle,
    val R_17: TextStyle,
    val SB_16: TextStyle,
    val M_16: TextStyle,
    val R_16: TextStyle,

    // Caption
    val R_15: TextStyle,
    val SB_14: TextStyle,
    val R_14: TextStyle,
)

val defaultOnItTypography = OnItTypography(
    // Title
    B_30 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 30.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em,
        fontWeight = FontWeight.Bold
    ),
    B_28 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 28.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em,
        fontWeight = FontWeight.Bold
    ),
    B_26 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 26.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em,
        fontWeight = FontWeight.Bold
    ),
    SB_24 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 24.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em,
        fontWeight = FontWeight.SemiBold
    ),
    SB_22 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 22.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em,
        fontWeight = FontWeight.SemiBold
    ),
    B_20 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 20.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em,
        fontWeight = FontWeight.Bold
    ),
    SB_20 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 20.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em,
        fontWeight = FontWeight.SemiBold
    ),
    M_20 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 20.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em,
        fontWeight = FontWeight.Medium
    ),

    // Body
    SB_18 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 18.sp,
        lineHeight = 1.6.em,
        letterSpacing = 0.02.em,
        fontWeight = FontWeight.SemiBold
    ),
    R_18 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 18.sp,
        lineHeight = 1.6.em,
        fontWeight = FontWeight.Normal

    ),
    B_17 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        lineHeight = 1.6.em,
        letterSpacing = 0.02.em,
        fontWeight = FontWeight.Bold
    ),
    M_17 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        lineHeight = 1.6.em,
        letterSpacing = 0.02.em,
        fontWeight = FontWeight.Medium
    ),
    R_17 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 17.sp,
        lineHeight = 1.6.em,
        fontWeight = FontWeight.Normal
    ),
    SB_16 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
        fontWeight = FontWeight.SemiBold
    ),
    M_16 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
        fontWeight = FontWeight.Medium
    ),
    R_16 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
        fontWeight = FontWeight.Normal
    ),

    // Caption
    R_15 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 15.sp,
        lineHeight = 1.5.em,
        letterSpacing = (0.01).em,
        fontWeight = FontWeight.Normal
    ),
    SB_14 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 14.sp,
        lineHeight = 1.5.em,
        letterSpacing = (0.01).em,
        fontWeight = FontWeight.SemiBold
    ),
    R_14 = TextStyle(
        fontFamily = pretendardFamily,
        fontSize = 14.sp,
        lineHeight = 1.5.em,
        letterSpacing = (0.01).em,
        fontWeight = FontWeight.Normal
    )
)

val LocalOnItTypographyProvider = staticCompositionLocalOf { defaultOnItTypography }
