package com.eins.energypresso.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.entity.VisitedCafe
import com.eins.domain.repository.VisitCafeListRepository
import com.eins.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VisitedCafeListViewModel @Inject constructor(
    private val visitCafeListRepository: VisitCafeListRepository
): ViewModel() {
    var cafeListData: MutableStateFlow<List<VisitedCafe>> = MutableStateFlow(arrayListOf())
        private set

    var error: MutableStateFlow<String?> = MutableStateFlow(null)

    fun getVisitCafeList(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = visitCafeListRepository.getVisitCageList()){
                is Resource.Error -> error.value = result.message
                is Resource.Success -> cafeListData.value = result.data
            }
        }
    }
}