package com.konkuk.hackathon.feature.signup

enum class SignUpType(
    val label: String,
    val description: String,
) {
    VOLUNTEER("\uD83E\uDD1D 봉사자 가입하기", "코드 등록 후 어르신께 안부전화를 드려요."),
    ORGANIZATION("\uD83C\uDFE2 기관 회원가입하기", "어르신을 등록하고 봉사 현황을 확인해요.")
}