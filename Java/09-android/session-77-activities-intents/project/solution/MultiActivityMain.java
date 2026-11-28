// MultiActivityMain.java — پاسخ پروژهٔ جلسه ۷۷
// نیازمند Android SDK
package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MultiActivityMain extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_main);

        TextView status = findViewById(R.id.status);
        Button goBtn = findViewById(R.id.go_button);

        goBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MultiActivitySecond.class);
            intent.putExtra("message", "Hello from Main!");
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("result");
            TextView status = findViewById(R.id.status);
            status.setText("Received: " + result);
        }
    }
}
