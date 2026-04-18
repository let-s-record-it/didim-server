package com.didim.domain.auth.domain

data class IdToken(
    val header: IdTokenHeader,
    val payload: IdTokenPayload,
    val idToken: String,
) {
    fun validatePayload(iss: String, aud: String) = payload.validatePayload(iss, aud)

    val kid: String
        get() = header.kid

    val sub: String
        get() = payload.sub
}