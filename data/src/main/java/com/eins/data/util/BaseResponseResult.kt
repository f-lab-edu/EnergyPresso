package com.eins.data.util

sealed class BaseResponseResult<T>{
    data class Success<T>(val data: T): BaseResponseResult<T>()
    data class Error<T>(val message: String): BaseResponseResult<T>()
}