// MultiFragmentMain.java — پاسخ پروژهٔ جلسه ۸۰
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MultiFragmentMain extends AppCompatActivity implements Fragment1.OnFragment1Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_fragment);

        Button showF1 = findViewById(R.id.show_f1);
        Button showF2 = findViewById(R.id.show_f2);

        showF1.setOnClickListener(v -> showFragment1());
        showF2.setOnClickListener(v -> showFragment2());

        // Show first fragment by default
        if (savedInstanceState == null) {
            showFragment1();
        }
    }

    void showFragment1() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new Fragment1());
        ft.commit();
    }

    void showFragment2() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new Fragment2());
        ft.commit();
    }

    @Override
    public void onFragment1ButtonClick(String data) {
        TextView status = findViewById(R.id.status);
        status.setText("Fragment 1: " + data);
    }
}
