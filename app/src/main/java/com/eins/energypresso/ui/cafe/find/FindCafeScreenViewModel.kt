package com.eins.energypresso.ui.cafe.find

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.entity.cafe.find.AroundCafeData
import com.eins.domain.entity.cafe.find.FrequentlyCafeData
import com.eins.domain.usecase.cafe.GetAroundCafeUseCase
import com.eins.domain.usecase.cafe.GetFrequentlyCafeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindCafeScreenViewModel @Inject constructor(
    private val getAroundCafeUseCase: GetAroundCafeUseCase,
    private val getFrequentlyCafeUseCase: GetFrequentlyCafeUseCase
): ViewModel() {

    val errorMessage = MutableStateFlow<String?>(null)

    val aroundCafeList = MutableStateFlow<List<AroundCafeData>>(arrayListOf())
    val frequentlyCafeList = MutableStateFlow<List<FrequentlyCafeData>>(arrayListOf())

    init {
        getAroundCafeDataList()
        getFrequentlyCafeList()
    }

    fun getAroundCafeDataList(){
        viewModelScope.launch {
            getAroundCafeUseCase()
                .catch {
                    errorMessage.value = ""
                }.collect{
                    aroundCafeList.value = it
                }
        }
    }

    fun getFrequentlyCafeList(){
        viewModelScope.launch {
            getFrequentlyCafeUseCase()
                .catch {

                }.collect{
                    frequentlyCafeList.value = it
                }
        }
    }
}