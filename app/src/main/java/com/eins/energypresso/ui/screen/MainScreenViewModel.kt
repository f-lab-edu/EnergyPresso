package com.eins.energypresso.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.entity.VisitedCafe
import com.eins.domain.usecase.GetVisitCafeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val visitCafeListUseCase: GetVisitCafeListUseCase
): ViewModel() {
    var cafeListData = MutableStateFlow<List<VisitedCafe>>(arrayListOf())

    var errorMessage = MutableStateFlow<String?>(null)

    init {
        getVisitCafeList()
    }

    fun getVisitCafeList(){
        viewModelScope.launch(Dispatchers.IO) {
            visitCafeListUseCase()
                .catch {
                    errorMessage.value = "방문 카페 목록을 가져오는 중 오류가 발생했습니다"
                }.collect{
                    cafeListData.value = it
                }
        }
    }
}