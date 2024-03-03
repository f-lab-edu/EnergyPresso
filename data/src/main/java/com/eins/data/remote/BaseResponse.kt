package com.eins.data.remote

data class BaseResponse<T>(
    val result: Boolean,
    val data: T?,
    val error: String?
)

sealed class BaseResponseResult<T>{
    data class Success<T>(val data: T): BaseResponseResult<T>()
    data class Error<T>(val message: String): BaseResponseResult<T>()
}