package com.example.exposproj.viewmodel

import androidx.lifecycle.ViewModel
import com.example.exposproj.model.CounterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel(){
    private var _counter = MutableStateFlow(CounterModel())
    val counter : StateFlow<CounterModel> = _counter.asStateFlow()

    fun increase(){
        _counter.update { stateFlow ->
            stateFlow.copy(count = stateFlow.count + 1)
        }
    }

    fun decrease(){
        _counter.update { stateFlow ->
            stateFlow.copy(count = stateFlow.count - 1)
        }
    }
}