package com.didim.common.exception

class AppException(
    val errorType: ErrorType,
    val errorData: Any? = null,
) : RuntimeException() {
}