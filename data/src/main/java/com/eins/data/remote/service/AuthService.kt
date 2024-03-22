package com.eins.data.remote.service

import com.eins.data.remote.BaseResponse
import com.eins.data.remote.data.GetUserInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {
    @GET("/user")
    suspend fun login(@Query("id") id: String, @Query("pass") pass: String): Response<BaseResponse<GetUserInfoResponse>>
}