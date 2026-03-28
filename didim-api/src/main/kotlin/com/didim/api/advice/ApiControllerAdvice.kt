package com.didim.api.advice

import com.didim.api.support.response.ApiResponse
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.common.exception.HttpStatus as AppHttpStatus
import com.didim.common.log.logger
import org.springframework.boot.logging.LogLevel.ERROR
import org.springframework.boot.logging.LogLevel.WARN
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {
    @ExceptionHandler(AppException::class)
    fun handleAppException(e: AppException): ResponseEntity<ApiResponse<Unit>> {
        logAppException(e)
        return ResponseEntity(ApiResponse.error(e.errorType, e.errorData), convertStatus(e.errorType.status))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Unit>> {
        logException(e)
        return ResponseEntity(ApiResponse.error(ErrorType.DEFAULT_ERROR, e), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private fun logAppException(e: AppException) {
        val stackTrace = e.stackTrace.first()
        val status = e.errorType.status
        val errorCode = e.errorType.errorCode
        val logMessage =
            "[AppException]: class=${stackTrace.className} | method=${stackTrace.methodName} | " +
                    "line=${stackTrace.lineNumber} | status=$status | errorCode=$errorCode | " +
                    "message=${e.message} | data=${e.cause}"

        when (e.errorType.logLevel) {
            ERROR -> logger.error { logMessage }
            WARN -> logger.warn { logMessage }
            else -> logger.info { logMessage }
        }
    }

    private fun logException(e: Exception) {
        val stackTrace = e.stackTrace.first()
        logger.error {
            "[Exception]: class=${stackTrace.className} | method=${stackTrace.methodName} | line=${stackTrace.lineNumber} | message=${e.message}"
        }
    }

    private fun convertStatus(status: AppHttpStatus) =
        when (status) {
            AppHttpStatus.REDIRECT -> HttpStatus.TEMPORARY_REDIRECT
            AppHttpStatus.BAD_REQUEST -> HttpStatus.BAD_REQUEST
            AppHttpStatus.UNAUTHORIZED -> HttpStatus.UNAUTHORIZED
            AppHttpStatus.FORBIDDEN -> HttpStatus.FORBIDDEN
            AppHttpStatus.NOT_FOUND -> HttpStatus.NOT_FOUND
            AppHttpStatus.TOO_MANY_REQUESTS -> HttpStatus.TOO_MANY_REQUESTS
            AppHttpStatus.INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR
            AppHttpStatus.NOT_IMPLEMENTED -> HttpStatus.NOT_IMPLEMENTED
        }
}
