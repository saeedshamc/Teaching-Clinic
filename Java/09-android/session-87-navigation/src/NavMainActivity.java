// NavMainActivity.java — activity با NavHostFragment
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class NavMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);
        // NavHostFragment در layout تعریف شده — nav_graph خودکار load می‌شود
    }
}
