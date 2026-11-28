// MainActivity.java — basic Android Activity
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------- Find views ----------
        TextView title = findViewById(R.id.title);
        Button button = findViewById(R.id.button);

        // ---------- Set click listener ----------
        button.setOnClickListener(v -> {
            title.setText("Button clicked!");
        });
    }
}
