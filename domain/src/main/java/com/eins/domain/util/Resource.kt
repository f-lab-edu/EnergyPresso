package com.eins.domain.util

sealed class Resource<out T> {
    /**
     *  Success : 성공 시 non-null 데이터 전달
     *  Error : 실패 시 non-null 에러메시지 전달
     */
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()

    companion object {
        suspend fun <T> create(call: suspend () -> Resource<T>): Resource<T> = call.invoke()
    }

    /**
     * 결과 데이터를 변환해야 할 때 사용
     */
    suspend fun <T2> convert(converter: suspend (T) -> T2): Resource<T2> = when (this) {
        is Success -> Success(converter.invoke(data))
        is Error -> Error(message)
    }

    /**
     * 결과 데이터를 다음 Resource를 리턴하는 람다에 전달 후 실행
     */
    suspend fun <T2> convertResource(converter: suspend (T) -> Resource<T2>): Resource<T2> = when (this) {
        is Success -> converter.invoke(data)
        is Error -> Error(message)
    }
}