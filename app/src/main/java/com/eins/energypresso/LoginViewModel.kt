package com.eins.energypresso

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.usecase.UserUseCase
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
    private val getUserUseCase: UserUseCase,
    private val mqttBaseUseCase: MqttBaseUseCase
): ViewModel() {
    var error: MutableStateFlow<String?> = MutableStateFlow(null)
        private set

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
            when(val result = getUserUseCase.getUser(id, pass)){
                is Resource.Error -> error.value = result.message
                is Resource.Success -> loginData.tryEmit(true)
            }
        }
    }
}