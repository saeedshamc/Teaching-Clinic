// SecondActivity.java — second activity با result
// نیازمند Android SDK
package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView message = findViewById(R.id.message);
        message.setText("This is Second Activity");

        Button backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", "Hello from Second!");
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
