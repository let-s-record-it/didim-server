package com.didim.domain.auth.domain.kakao

data class KakaoAccount(
    val profileNicknameNeedsAgreement: Boolean,
    val profileImageNeedsAgreement: Boolean,
    val email: String,
    val profile: KakaoProfile,
)
