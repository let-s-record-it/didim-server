package com.didim.domain.auth.implement

import com.didim.common.enums.TokenType

interface AuthTokenValidator {

    fun getSubjectIfValidWithType(token: String, expectedType: TokenType): String
}