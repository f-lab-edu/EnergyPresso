package com.eins.energypresso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.entity.User
import com.eins.domain.usecase.UserUseCase
import com.eins.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserUseCase: UserUseCase
): ViewModel() {
    var error: MutableStateFlow<String?> = MutableStateFlow(null)
        private set

    var loginData: MutableStateFlow<User?> = MutableStateFlow(null)
        private set

    fun login(id: String, pass: String){
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = getUserUseCase.getUser(id, pass)){
                is Resource.Error -> error.value = result.message
                is Resource.Success -> loginData.value = result.data
            }
        }
    }
}