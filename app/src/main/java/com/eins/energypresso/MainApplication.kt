package com.eins.energypresso

import android.app.Application
import android.util.Log
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.eins.data.datasource.InAppDataSharedPref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        //Data 모듈 shared preference 에 application context 주입
        Log.d("MainApplication", "inject context to shared preference object")
        InAppDataSharedPref.setUp(this)
        if(InAppDataSharedPref.getAccessToken() == null || InAppDataSharedPref.getAccessToken() == ""){
            Log.d("MainApplication", "set basic accessToken")
            InAppDataSharedPref.saveAccessToken("basic access token")
        }
        if(InAppDataSharedPref.getRefreshToken() == null || InAppDataSharedPref.getRefreshToken() == "") {
            Log.d("MainApplication", "set basic refreshToken")
            InAppDataSharedPref.saveAccessToken("basic refresh token")
        }
    }
}