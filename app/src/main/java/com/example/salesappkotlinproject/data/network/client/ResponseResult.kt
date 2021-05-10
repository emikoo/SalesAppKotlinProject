package com.example.salesappkotlinproject.data.network.client

enum class ResponseResultStatus {
    SUCCESS,
    LOADING,
    ERROR
}

data class ResponseResult<T>(
    var status: ResponseResultStatus? = null,
    var result: T? = null,
    var message: String? = null
) {
    companion object {
        fun <T> success(data: T?): ResponseResult<T> {
            return ResponseResult(ResponseResultStatus.SUCCESS, data)
        }

        fun <T> error(message: String?, data: T? = null): ResponseResult<T> {
            return ResponseResult(ResponseResultStatus.ERROR, data, message)
        }

        fun <T> loading(message: String? = null): ResponseResult<T> {
            return ResponseResult(ResponseResultStatus.LOADING, null, message)
        }
    }
}