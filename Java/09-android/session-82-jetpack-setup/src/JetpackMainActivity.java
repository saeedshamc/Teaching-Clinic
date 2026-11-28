// JetpackMainActivity.java — Activity با ViewModel
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class JetpackMainActivity extends AppCompatActivity {

    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jetpack_main);

        // ---------- ViewModel ----------
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        TextView messageText = findViewById(R.id.message_text);
        TextView counterText = findViewById(R.id.counter_text);
        Button updateBtn = findViewById(R.id.update_button);
        Button incrementBtn = findViewById(R.id.increment_button);

        // ---------- Observe LiveData ----------
        viewModel.getMessage().observe(this, msg -> {
            messageText.setText(msg);
        });

        viewModel.getCounter().observe(this, count -> {
            counterText.setText("Count: " + count);
        });

        // ---------- Buttons ----------
        updateBtn.setOnClickListener(v -> {
            viewModel.setMessage("Updated at " + System.currentTimeMillis());
        });

        incrementBtn.setOnClickListener(v -> {
            viewModel.incrementCounter();
        });
    }
}
