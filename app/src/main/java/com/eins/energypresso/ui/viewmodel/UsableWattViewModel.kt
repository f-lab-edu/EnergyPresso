package com.eins.energypresso.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.usecase.UserUniqueIdUseCase
import com.eins.energypresso.util.AWSIoTManagerHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsableWattViewModel @Inject constructor(
): ViewModel() {

    init {

    }

    override fun onCleared() {
        super.onCleared()
    }
}