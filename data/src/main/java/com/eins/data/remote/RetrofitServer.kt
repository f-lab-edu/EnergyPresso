package com.eins.data.remote

import android.util.Log
import com.eins.data.datasource.InAppDataSharedPref
import com.eins.data.remote.service.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServer {
    private val httpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val tokenInterceptor = Interceptor {
            Log.d("tokenInterceptor", "add token : ${InAppDataSharedPref.getAccessToken() ?: "null"}")
            val request = it.request().newBuilder()
                .addHeader("Authorization", "Bearer ${InAppDataSharedPref.getAccessToken() ?: "null"}")
                .build()
            return@Interceptor it.proceed(request)
        }

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    private val mainServer by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://a2c805ba-6e59-45e9-b1fd-398536a2bf5f.mock.pstmn.io/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun userService(): UserService = mainServer.create(UserService::class.java)
}