package com.eins.energypresso.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
import com.eins.domain.usecase.UserUniqueIdUseCase
import com.eins.energypresso.util.AWSIoTManagerHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsableWattViewModel @Inject constructor(
    private val application: Application,
    private val uniqueIdUseCase: UserUniqueIdUseCase
): ViewModel(), AWSIotMqttNewMessageCallback, AWSIotMqttClientStatusCallback {
    private val awsIoTHandler by lazy {
        AWSIoTManagerHandler(
            context = application,
            mqttClientId = "main"
        )
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            awsIoTHandler.connect(this@UsableWattViewModel)
        }
    }

    override fun onMessageArrived(topic: String?, data: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onStatusChanged(
        status: AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus?,
        throwable: Throwable?
    ) {
        Log.d("UsableWattViewModel", """
            status = $status
        """.trimIndent())
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("UsableWattViewModel", "call onCleared")
        awsIoTHandler.disconnect()
    }
}