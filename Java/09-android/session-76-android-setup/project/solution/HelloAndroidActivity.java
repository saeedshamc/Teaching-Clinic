// HelloAndroidActivity.java — پاسخ پروژهٔ جلسه ۷۶
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HelloAndroidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        TextView title = findViewById(R.id.title);
        EditText nameInput = findViewById(R.id.name_input);
        Button greetBtn = findViewById(R.id.greet_button);
        TextView greeting = findViewById(R.id.greeting);

        greetBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            if (!name.isEmpty()) {
                greeting.setText("Hello, " + name + "!");
            } else {
                greeting.setText("Please enter a name");
            }
        });
    }
}
