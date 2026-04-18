package com.didim.domain.auth.domain.kakao

data class KakaoProfile(
    val nickname: String,
    val thumbnailImageUrl: String,
    val profileImageUrl: String,
    val isDefaultImage: Boolean,
    val isDefaultNickname: Boolean,
)
