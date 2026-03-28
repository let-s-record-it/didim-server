package com.didim.api.support.response

import com.didim.common.exception.ErrorMessage
import com.didim.common.exception.ErrorType

data class ApiResponse<T>(
    val resultType: ResultType,
    val data: T?,
    val error: ErrorMessage?
) {

    companion object {
        fun <S> success(data: S? = null): ApiResponse<S> = ApiResponse(ResultType.SUCCESS, data, null)


        fun error(error: ErrorType, errorData: Any? = null): ApiResponse<Unit> =
            ApiResponse(ResultType.ERROR, null, ErrorMessage(error, errorData))

    }

}