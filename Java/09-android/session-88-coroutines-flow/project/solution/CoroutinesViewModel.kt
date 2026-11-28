// CoroutinesViewModel.kt — پاسخ پروژهٔ جلسه ۸۸
// نیازمند Android SDK + Kotlin
package com.example.myapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CoroutinesViewModel : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: String) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    private val _logs = MutableStateFlow<List<String>>(emptyList())
    val logs: StateFlow<List<String>> = _logs.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                // parallel async calls
                val deferred1 = kotlinx.coroutines.async { fetchData1() }
                val deferred2 = kotlinx.coroutines.async { fetchData2() }
                val result1 = deferred1.await()
                val result2 = deferred2.await()
                _uiState.value = UiState.Success("$result1 + $result2")
                addLog("Data loaded successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error")
                addLog("Error: ${e.message}")
            }
        }
    }

    fun incrementCounter() {
        _counter.value += 1
        addLog("Counter: ${_counter.value}")
    }

    fun decrementCounter() {
        if (_counter.value > 0) {
            _counter.value -= 1
            addLog("Counter: ${_counter.value}")
        }
    }

    fun startDataStream() {
        viewModelScope.launch {
            dataFlow().collect { data ->
                addLog("Stream: $data")
            }
        }
    }

    private suspend fun fetchData1(): String {
        kotlinx.coroutines.delay(1000)
        return "Data 1"
    }

    private suspend fun fetchData2(): String {
        kotlinx.coroutines.delay(1000)
        return "Data 2"
    }

    private fun dataFlow(): Flow<String> = flow {
        for (i in 1..5) {
            emit("Stream item $i")
            kotlinx.coroutines.delay(1000)
        }
    }

    private fun addLog(message: String) {
        _logs.value = _logs.value + message
    }
}
