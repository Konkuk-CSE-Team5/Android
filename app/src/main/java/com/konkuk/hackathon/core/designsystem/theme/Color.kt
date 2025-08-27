package com.konkuk.hackathon.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt


// Color 객체를 Hex 코드로 쉽게 생성하기 위한 확장 함수
fun Color(hex: String): Color {
    return Color(hex.toColorInt())
}

// JSON 구조를 그대로 따르는 색상 데이터 클래스 정의
data class OnItColors(
    val base: BaseColors,
    val label: LabelColors,
    val contents: ContentsColors,
    val border: BorderColors,
    val container: ContainerColors,
    val layer: LayerColors,
    val decrease: DecreaseColors,
    val positive: PositiveColors,
    val negative: NegativeColors,
    val inverse: InverseColors,
    val primary: PrimaryColors
)

data class BaseColors(
    val black: Color,
    val white: Color
)

data class LabelColors(
    val normal: Color,
    val strong: Color,
    val disabled: Color,
    val alternative: Color,
    val neutral: Color
)

data class ContentsColors(
    val normal: Color,
    val neutral: Color
)

data class BorderColors(
    val normal: Color,
    val neutral: Color
)

data class ContainerColors(
    val selectedHover: Color,
    val normal: Color,
    val alternative: Color,
    val disabled: Color,
    val neutral: Color
)

data class LayerColors(
    val dim: Color,
    val elevated: Color,
    val base: Color
)

data class DecreaseColors(
    val container: Color,
    val content: Color
)

data class PositiveColors(
    val container: Color,
    val content: Color
)

data class NegativeColors(
    val container: Color,
    val content: Color
)

data class InverseColors(
    val white: Color,
    val black: Color
)

data class PrimaryColors(
    val content: Color,
    val container: Color
)

// 라이트 테마 색상 정의
fun lightColors(): OnItColors = OnItColors(
    base = BaseColors(
        black = Color("#000000"),
        white = Color("#ffffff")
    ),
    label = LabelColors(
        normal = Color("#151719"),
        strong = Color("#000000"),
        disabled = Color("#b1b8c0"),
        alternative = Color("#b1b8c0"),
        neutral = Color("#505866")
    ),
    contents = ContentsColors(
        normal = Color("#b1b8c0"),
        neutral = Color("#6d7582")
    ),
    border = BorderColors(
        normal = Color("#e7e9ec"),
        neutral = Color("#b1b8c0")
    ),
    container = ContainerColors(
        selectedHover = Color("#f2f3f5"),
        normal = Color("#fafafb"),
        alternative = Color("#e7e9ec"),
        disabled = Color("#e7e9ec"),
        neutral = Color("#f3f4f8")
    ),
    layer = LayerColors(
        // #00000066 -> Alpha(66) + RGB(000000)
        // Compose Color는 AARRGGBB 순서이므로 0x66000000 로 변환
        dim = Color(0x66000000),
        elevated = Color("#ffffff"),
        base = Color("#f3f4f8")
    ),
    decrease = DecreaseColors(
        container = Color("#e4f2fd"),
        content = Color("#3f94ee")
    ),
    positive = PositiveColors(
        container = Color("#e6f7e9"),
        content = Color("#0bbd49")
    ),
    negative = NegativeColors(
        container = Color("#fdecee"),
        content = Color("#e6533e")
    ),
    inverse = InverseColors(
        white = Color("#ffffff"),
        black = Color("#000000")
    ),
    primary = PrimaryColors(
        content = Color("#ee863b"),
        container = Color("#fff4eb")
    )
)

// CompositionLocal을 통해 앱 전역에 색상 시스템을 제공
val LocalOnItColorsProvider = staticCompositionLocalOf { lightColors() }

val Gray_7 = Color("#3b3b3b")
val Gray_4 = Color("#8A8A8A")
val Gray_2 = Color("#8A8A8A")




