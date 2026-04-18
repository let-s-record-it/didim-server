package com.didim.domain.auth.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class OidcPublicKeys(val keys: List<OidcPublicKey>) {

    fun getPublicKey(kid: String): OidcPublicKey =
        keys.find { it.kid == kid } ?: throw AppException(ErrorType.ID_TOKEN_UNSUPPORTED)
}
