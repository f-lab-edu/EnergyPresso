package com.eins.data.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InAppDataSource @Inject constructor(
    @ApplicationContext context: Context
) {
    private val PREF_FILE_NAME = "TokenPreferences"
    private val ACCESS_TOKEN_KEY = "AccessTokenKey"
    private val REFRESH_TOKEN_KEY = "RefreshTokenKey"

    private val prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    fun saveAccessToken(accessToken: String) = prefs.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
    fun saveRefreshToken(refreshToken: String) = prefs.edit().putString(REFRESH_TOKEN_KEY, refreshToken).apply()
    fun getAccessToken(): String = prefs.getString(ACCESS_TOKEN_KEY, null)!!
    fun getRefreshToken(): String = prefs.getString(REFRESH_TOKEN_KEY, null)!!
}