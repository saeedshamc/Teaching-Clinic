// MyViewModel.kt — ViewModel با StateFlow
// نیازمند Android SDK + Kotlin
package com.example.myapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: String) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                // simulate network call
                kotlinx.coroutines.delay(2000)
                _uiState.value = UiState.Success("Data loaded!")
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error")
            }
        }
    }

    fun incrementCounter() {
        _counter.value += 1
    }

    fun decrementCounter() {
        if (_counter.value > 0) {
            _counter.value -= 1
        }
    }
}
