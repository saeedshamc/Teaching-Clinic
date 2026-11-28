// CounterActivity.java — پاسخ پروژهٔ جلسه ۸۲
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class CounterActivity extends AppCompatActivity {

    private CounterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        viewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        TextView counterText = findViewById(R.id.counter_text);
        Button incrementBtn = findViewById(R.id.increment_button);
        Button decrementBtn = findViewById(R.id.decrement_button);
        Button resetBtn = findViewById(R.id.reset_button);

        viewModel.getCounter().observe(this, count -> {
            counterText.setText(String.valueOf(count));
        });

        incrementBtn.setOnClickListener(v -> viewModel.increment());
        decrementBtn.setOnClickListener(v -> viewModel.decrement());
        resetBtn.setOnClickListener(v -> viewModel.reset());
    }
}
