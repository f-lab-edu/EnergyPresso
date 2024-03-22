package com.eins.energypresso

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(

): ViewModel() {
    private var _getUserLiveData = MutableLiveData<User>()
    val getUserLiveData: LiveData<User> = _getUserLiveData

    fun getUser(id: String, pass: String){
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}