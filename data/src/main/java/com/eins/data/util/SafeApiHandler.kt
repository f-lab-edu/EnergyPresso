package com.eins.data.util

import android.util.Log
import com.eins.data.remote.BaseResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

object SafeApiHandler {
    suspend fun <T> handleApi(call : suspend () -> Response<BaseResponse<T>>): BaseResponseResult<T> =
        runCatching {
            call.invoke().apply {
                Log.d("handleApi", """
                    isSuccessful = ${this.isSuccessful}
                    code = ${this.code()}
                    headers = ${this.headers()}
                    body = ${this.body()}
                    errorBody = ${this.errorBody()}
                """.trimIndent())
            }
        }.fold(
            onSuccess = { response ->
                if(response.isSuccessful){
                    BaseResponseResult.Success(data = response.body()?.data ?: return@fold BaseResponseResult.Error("RESPONSE_NOT_VALID"))
                }else{
                    BaseResponseResult.Error(message = response.errorBody()?.errorBodyToBaseResponse()?.error ?: "RESPONSE_NOT_VALID")
                }
            },
            onFailure = { exception ->
                BaseResponseResult.Error(message = exception.message ?: "Unknown error")
            }
        )

    fun ResponseBody.errorBodyToBaseResponse(): BaseResponse<*>? {
        return try { Gson().fromJson(this.string(), BaseResponse::class.java) ?: null }
        catch (e : Exception){ null }
    }
}