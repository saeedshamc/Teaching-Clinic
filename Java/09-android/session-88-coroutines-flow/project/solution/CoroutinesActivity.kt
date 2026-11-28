// CoroutinesActivity.kt — پاسخ پروژهٔ جلسه ۸۸
// نیازمند Android SDK + Kotlin
package com.example.myapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CoroutinesActivity : AppCompatActivity() {

    private lateinit var viewModel: CoroutinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        viewModel = ViewModelProvider(this).get(CoroutinesViewModel::class.java)

        val statusText = findViewById<TextView>(R.id.status_text)
        val counterText = findViewById<TextView>(R.id.counter_text)
        val logsText = findViewById<TextView>(R.id.logs_text)
        val loadBtn = findViewById<Button>(R.id.load_button)
        val incrementBtn = findViewById<Button>(R.id.increment_button)
        val decrementBtn = findViewById<Button>(R.id.decrement_button)
        val streamBtn = findViewById<Button>(R.id.stream_button)

        // ---------- Observe StateFlow ----------
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is CoroutinesViewModel.UiState.Loading -> statusText.text = "Loading..."
                    is CoroutinesViewModel.UiState.Success -> statusText.text = state.data
                    is CoroutinesViewModel.UiState.Error -> statusText.text = "Error: ${state.message}"
                }
            }
        }

        lifecycleScope.launch {
            viewModel.counter.collect { count ->
                counterText.text = "Counter: $count"
            }
        }

        lifecycleScope.launch {
            viewModel.logs.collect { logs ->
                logsText.text = logs.joinToString("\n")
            }
        }

        // ---------- Buttons ----------
        loadBtn.setOnClickListener { viewModel.loadData() }
        incrementBtn.setOnClickListener { viewModel.incrementCounter() }
        decrementBtn.setOnClickListener { viewModel.decrementCounter() }
        streamBtn.setOnClickListener { viewModel.startDataStream() }
    }
}
