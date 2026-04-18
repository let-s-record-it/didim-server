package com.didim.api.auth.service

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.auth.domain.IdToken
import com.didim.domain.auth.domain.OidcPublicKeys
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.RSAPublicKeySpec
import kotlin.io.encoding.Base64

@Component
class IdTokenValidator {
    fun validateIdToken(idToken: IdToken, publicKeys: OidcPublicKeys, iss: String, aud: String) {
        idToken.validatePayload(iss, aud)
        val publicKey = publicKeys.getPublicKey(idToken.kid)

        try {
            Jwts.parser()
                .verifyWith(getKeyByRsa(publicKey.n, publicKey.e))
                .build()
                .parseSignedClaims(idToken.idToken)
        } catch (e: Exception) {
            throw AppException(ErrorType.ID_TOKEN_INVALID_SIGNATURE)
        }
    }

    private fun getKeyByRsa(modulus: String, exponent: String): PublicKey {
        val decodedModulus = Base64.UrlSafe.decode(modulus)
        val decodedExponent = Base64.UrlSafe.decode(exponent)

        return KeyFactory.getInstance("RSA")
            .generatePublic(
                RSAPublicKeySpec(
                    BigInteger(1, decodedModulus),
                    BigInteger(1, decodedExponent),
                )
            )
    }

}