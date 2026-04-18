package com.didim.domain.auth.domain

data class OidcPublicKey(
    val kid: String,
    val alg: String,
    val kty: String,
    val use: String,
    val n: String,
    val e: String,
)
