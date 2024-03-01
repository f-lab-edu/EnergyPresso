package com.eins.energypresso

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(

): ViewModel() {
    var idString: MutableStateFlow<String> = MutableStateFlow("")

    var passString: MutableStateFlow<String> = MutableStateFlow("")

    var error: MutableStateFlow<String?> = MutableStateFlow(null)
        private set // 외부에서는 이 값을 직접 변경하지 못하도록 합니다.
}