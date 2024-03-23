package com.eins.energypresso

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.usecase.LoginUseCase
import com.eins.domain.usecase.network.MqttBaseUseCase
import com.eins.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: LoginUseCase,
    private val mqttBaseUseCase: MqttBaseUseCase
): ViewModel() {
    var errorMessage = mutableStateOf<String?>(null)

    var loginData: MutableSharedFlow<Boolean> = MutableSharedFlow(1, 1)
        private set

    init {
        viewModelScope.launch {
            mqttBaseUseCase.subscribe("test").collect{
                Log.d("LoginViewModel", """
                    message : $it
                """.trimIndent())
            }
        }
    }

    fun login(id: String, pass: String){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getLoginUseCase(id, pass)
            }.onSuccess {
                loginData.tryEmit(true)
            }.onFailure {
                errorMessage.value = "로그인 중 오류가 발생했습니다"
            }
        }
    }
}