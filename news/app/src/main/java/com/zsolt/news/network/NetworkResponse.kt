package com.zsolt.news.network

import okhttp3.ResponseBody
import java.io.IOException

sealed class NetworkResponse<out T : Any> {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val data: T) : NetworkResponse<T>()
    data class Failure(val error: NetworkErrorResponse) : NetworkResponse<Nothing>()
}

sealed class NetworkErrorResponse {
    abstract fun errorMsg(): String

    /**
     * Failure response with body
     */
    data class ApiError(val body: ResponseBody, val code: Int) : NetworkErrorResponse() {
        override fun errorMsg() = "ApiError($code) - ${body.string()}"
    }

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : NetworkErrorResponse() {
        override fun errorMsg() = "NetworkError - ${error.localizedMessage}"
    }

    /**
     * AuthenticationError
     */
    data class AuthenticationError(val code: Int = 401) : NetworkErrorResponse() {
        override fun errorMsg() = "AuthError($code)"
    }

    /**
     * Ohter error, for example json parsing error
     */
    data class OtherError(val error: Throwable? = null) : NetworkErrorResponse() {
        override fun errorMsg() = "OtherError - ${error?.localizedMessage}"
    }
}