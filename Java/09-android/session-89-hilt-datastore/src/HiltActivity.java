// HiltActivity.java — Activity با Hilt dependency injection
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class HiltActivity extends AppCompatActivity {

    @Inject
    ContactRepository contactRepository;

    @Inject
    SettingsRepository settingsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt);

        TextView statusText = findViewById(R.id.status_text);
        statusText.setText("Hilt is working!");

        // Use injected dependencies
        // contactRepository.getAllContacts();
        // settingsRepository.getTheme();
    }
}
