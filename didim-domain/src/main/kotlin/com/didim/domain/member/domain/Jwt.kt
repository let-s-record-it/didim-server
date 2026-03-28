package com.didim.domain.member.domain

data class Jwt(
    val accessToken: String,
    val refreshToken: String,
)
