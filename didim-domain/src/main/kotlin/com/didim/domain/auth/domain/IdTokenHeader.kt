package com.didim.domain.auth.domain

data class IdTokenHeader(
    val kid: String,
    val typ: String,
    val alg: String,
)