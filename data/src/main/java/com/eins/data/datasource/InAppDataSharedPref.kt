package com.eins.data.datasource

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


object InAppDataSharedPref {
    private val PREF_FILE_NAME = "TokenPreferences"
    private val ACCESS_TOKEN_KEY = "AccessTokenKey"
    private val REFRESH_TOKEN_KEY = "RefreshTokenKey"
    private val UNIQUE_USER_ID_KEY = "UNIQUE_USER_ID_KEY"

    private var prefs: SharedPreferences? = null

    fun setUp(context: Context){
        prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun saveAccessToken(accessToken: String): Boolean = prefs?.edit()?.putString(ACCESS_TOKEN_KEY, accessToken)?.let { it.apply()
        true } ?: run { false }
    fun saveRefreshToken(refreshToken: String): Boolean = prefs?.edit()?.putString(REFRESH_TOKEN_KEY, refreshToken)?.let { it.apply()
        true} ?: run { false }
    fun getAccessToken(): String? = prefs?.getString(ACCESS_TOKEN_KEY, "")
    fun getRefreshToken(): String? = prefs?.getString(REFRESH_TOKEN_KEY, "")
    fun saveUniqueUserId(accessToken: String): Boolean = prefs?.edit()?.putString(UNIQUE_USER_ID_KEY, accessToken)?.let { it.apply()
        true } ?: run { false }
    fun getUniqueUserId(): String? = prefs?.getString(UNIQUE_USER_ID_KEY, "")
}