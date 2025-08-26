package com.konkuk.hackathon.core.designsystem.theme

import androidx.compose.runtime.Immutable
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
    // display
    val D1_B: TextStyle,
    val D1_M: TextStyle,
    val D1_R: TextStyle,

    val D2_B: TextStyle,
    val D2_M: TextStyle,
    val D2_R: TextStyle,

    // heading
    val H1_B: TextStyle,
    val H1_M: TextStyle,
    val H1_R: TextStyle,

    val H2_B: TextStyle,
    val H2_M: TextStyle,
    val H2_R: TextStyle,

    val H3_B: TextStyle,
    val H3_M: TextStyle,
    val H3_R: TextStyle,

    val H4_B: TextStyle,
    val H4_M: TextStyle,
    val H4_R: TextStyle,

    // body
    // Normal
    val Body1_N_B: TextStyle,
    val Body1_N_M: TextStyle,
    val Body1_N_R: TextStyle,

    // Reading
    val Body1_R_B: TextStyle,
    val Body1_R_M: TextStyle,
    val Body1_R_R: TextStyle,

    val Body2_N_B: TextStyle,
    val Body2_N_M: TextStyle,
    val Body2_N_R: TextStyle,

    val Body2_R_B: TextStyle,
    val Body2_R_M: TextStyle,
    val Body2_R_R: TextStyle,

    // Normal
    val Label1_N_B: TextStyle,
    val Label1_N_M: TextStyle,
    val Label1_N_R: TextStyle,

    // Reading
    val Label1_R_B: TextStyle,
    val Label1_R_M: TextStyle,
    val Label1_R_R: TextStyle,

    val Label2_B: TextStyle,
    val Label2_M: TextStyle,
    val Label2_R: TextStyle,

    val Caption1_B: TextStyle,
    val Caption1_M: TextStyle,
    val Caption1_R: TextStyle,

    val Caption2_B: TextStyle,
    val Caption2_M: TextStyle,
    val Caption2_R: TextStyle,
)

val defaultOnItTypography = OnItTypography(
    D1_B = TextStyle(
        fontSize = 56.sp,
        lineHeight = 72.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    D1_M = TextStyle(
        fontSize = 56.sp,
        lineHeight = 72.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    D1_R = TextStyle(
        fontSize = 56.sp,
        lineHeight = 72.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    D2_B = TextStyle(
        fontSize = 40.sp,
        lineHeight = 52.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    D2_M = TextStyle(
        fontSize = 40.sp,
        lineHeight = 52.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    D2_R = TextStyle(
        fontSize = 40.sp,
        lineHeight = 52.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    H1_B = TextStyle(
        fontSize = 36.sp,
        lineHeight = 50.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    H1_M = TextStyle(
        fontSize = 36.sp,
        lineHeight = 50.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    H1_R = TextStyle(
        fontSize = 36.sp,
        lineHeight = 50.sp,
        letterSpacing = (-0.03).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    H2_B = TextStyle(
        fontSize = 24.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.015.em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    H2_M = TextStyle(
        fontSize = 24.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.015.em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    H2_R = TextStyle(
        fontSize = 24.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.015.em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    H3_B = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    H3_M = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    H3_R = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    H4_B = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    H4_M = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    H4_R = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Body1_N_B = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Body1_N_M = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Body1_N_R = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Body1_R_B = TextStyle(
        fontSize = 16.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Body1_R_M = TextStyle(
        fontSize = 16.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Body1_R_R = TextStyle(
        fontSize = 16.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Body2_N_B = TextStyle(
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Body2_N_M = TextStyle(
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Body2_N_R = TextStyle(
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Body2_R_B = TextStyle(
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Body2_R_M = TextStyle(
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Body2_R_R = TextStyle(
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Label1_N_B = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Label1_N_M = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Label1_N_R = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Label1_R_B = TextStyle(
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Label1_R_M = TextStyle(
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Label1_R_R = TextStyle(
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Label2_B = TextStyle(
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Label2_M = TextStyle(
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Label2_R = TextStyle(
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Caption1_B = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Caption1_M = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Caption1_R = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
    Caption2_B = TextStyle(
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold
    ),
    Caption2_M = TextStyle(
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium
    ),
    Caption2_R = TextStyle(
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.005).em,
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal
    ),
)

val LocalOnItTypographyProvider = androidx.compose.runtime.staticCompositionLocalOf {
    defaultOnItTypography
}
