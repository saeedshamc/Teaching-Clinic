// CoroutinesExample.kt — coroutines و Flow
// نیازمند Android SDK + Kotlin
package com.example.myapp

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class CoroutinesExample {

    // ---------- suspend function ----------
    suspend fun fetchData(): String {
        delay(1000)   // suspend — doesn't block thread
        return "Data from network"
    }

    suspend fun fetchData1(): String {
        delay(500)
        return "Data 1"
    }

    suspend fun fetchData2(): String {
        delay(500)
        return "Data 2"
    }

    // ---------- launch — fire and forget ----------
    fun launchExample() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = fetchData()
            println("Result: $result")
        }
    }

    // ---------- async — deferred result ----------
    fun asyncExample() {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred1 = async { fetchData1() }
            val deferred2 = async { fetchData2() }
            val result1 = deferred1.await()
            val result2 = deferred2.await()
            println("Results: $result1, $result2")
        }
    }

    // ---------- Flow — reactive streams ----------
    fun getDataFlow(): Flow<String> = flow {
        for (i in 1..5) {
            emit("Data $i")
            delay(1000)
        }
    }

    fun flowExample() {
        CoroutineScope(Dispatchers.Main).launch {
            getDataFlow().collect { data ->
                println("Flow: $data")
            }
        }
    }

    // ---------- StateFlow — for UI state ----------
    sealed class State {
        object Loading : State()
        data class Success(val data: String) : State()
        data class Error(val message: String) : State()
    }

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {
            _state.value = State.Loading
            try {
                val data = fetchData()
                _state.value = State.Success(data)
            } catch (e: Exception) {
                _state.value = State.Error(e.message ?: "Unknown error")
            }
        }
    }
}
