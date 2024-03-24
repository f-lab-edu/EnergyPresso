package com.eins.energypresso.ui.point

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.usecase.log.GetChargePointLogUseCase
import com.eins.domain.usecase.log.GetChargeUseLogUseCase
import com.eins.domain.usecase.log.GetUsePointLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointLogScreenViewModel @Inject constructor(
    private val getChargeUseLogUseCase: GetChargeUseLogUseCase,
    private val getChargePointLogUseCase: GetChargePointLogUseCase,
    private val getUsePointLogUseCase: GetUsePointLogUseCase
): ViewModel() {
    val chargeUseLogList = MutableStateFlow<List<ChargeUseLog>>(arrayListOf())

    val errorMessage = MutableStateFlow<String?>(null)

    init {
        getChargeUseLog()
    }

    private fun getChargeUseLog(){
        viewModelScope.launch(Dispatchers.IO) {
            getChargeUseLogUseCase().catch {
                errorMessage.value = "사용 및 충전 기록을 가져오는 중 오류가 발생했습니다"
            }.collect(){
                chargeUseLogList.value = it
            }
        }
    }
}