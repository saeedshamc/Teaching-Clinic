// MultiActivitySecond.java — پاسخ پروژهٔ جلسه ۷۷
// نیازمند Android SDK
package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MultiActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_second);

        TextView message = findViewById(R.id.message);
        String msg = getIntent().getStringExtra("message");
        if (msg != null) {
            message.setText(msg);
        }

        Button sendBtn = findViewById(R.id.send_button);
        sendBtn.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", "Response from Second!");
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
