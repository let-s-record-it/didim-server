package com.didim.domain.auth.implement

import com.didim.domain.member.domain.Jwt

interface AuthTokenGenerator {

    fun generateJwt(memberKey: String): Jwt
}