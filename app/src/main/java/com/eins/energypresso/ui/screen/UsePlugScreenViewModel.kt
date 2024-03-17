package com.eins.energypresso.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eins.domain.usecase.GetLeftUsableTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsePlugScreenViewModel @Inject constructor(
    private val getLeftUsableTimeUseCase: GetLeftUsableTimeUseCase
): ViewModel() {
    private val useStartTimeAmount = mutableStateOf(Minute(0))

    private val _usableTimeAmount = MutableStateFlow<Minute>(Minute(-1))
    var usableTimeAmount: StateFlow<Minute> = _usableTimeAmount

    private val _useTimePercentage: MutableStateFlow<Float> = MutableStateFlow<Float>(0f)
    var useTimePercentage: StateFlow<Float> = _useTimePercentage

    private var timerJob = viewModelScope.launch(Dispatchers.IO, start = CoroutineStart.LAZY) {}

    init {
        viewModelScope.launch {
            getLeftUsableTimeUseCase.getLeftUsableTime().collect(){
                setUseTime(Minute(it.get()))
            }
        }
    }

    private fun setUseTime(minute: Minute){
        viewModelScope.launch {
            timerJob.cancel()
            timerJob = viewModelScope.launch(Dispatchers.IO, start = CoroutineStart.LAZY) {
                while (_usableTimeAmount.value.get() > 0){
                    delay(1000)
                    _usableTimeAmount.value = Minute(_usableTimeAmount.value.get() - 1)

                    val percent = try {
                        ((_usableTimeAmount.value.get().toFloat()) / useStartTimeAmount.value.get().toFloat()).toFloat()
                    }catch (e : Exception){
                        e.printStackTrace()
                        0f
                    }
                    _useTimePercentage.tryEmit(percent)
                }
            }
            delay(1000)
            useStartTimeAmount.value = minute
            _usableTimeAmount.value = minute
            timerJob.start()
        }
    }
}

@JvmInline
value class Minute(private val minute: Int){
    fun get() = minute
    override fun toString(): String {
        val hour = minute / 60
        val min = minute % 60

        return "${if(hour > 0) "${hour}시간 " else ""} ${min}분"
    }
}