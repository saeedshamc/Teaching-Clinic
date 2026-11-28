// DashboardActivity.java — پاسخ پروژهٔ جلسه ۷۸
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView title = findViewById(R.id.title);
        Button menuBtn = findViewById(R.id.menu_button);
        Button helpBtn = findViewById(R.id.help_button);

        menuBtn.setOnClickListener(v -> {
            title.setText("Menu clicked");
        });

        helpBtn.setOnClickListener(v -> {
            title.setText("Help clicked");
        });
    }
}
