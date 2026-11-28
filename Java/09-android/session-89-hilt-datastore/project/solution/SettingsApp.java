// SettingsApp.java — پاسخ کامل پروژهٔ جلسه ۸۹
// نیازمند Android SDK + Kotlin
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.lifecycleScope;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.launch;
import javax.inject.Inject;

@AndroidEntryPoint
public class SettingsApp extends AppCompatActivity {

    @Inject
    SettingsRepository settingsRepository;

    private TextView statusText;
    private TextView themeText;
    private TextView languageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_app);

        statusText = findViewById(R.id.status_text);
        themeText = findViewById(R.id.theme_text);
        languageText = findViewById(R.id.language_text);

        Button lightBtn = findViewById(R.id.light_button);
        Button darkBtn = findViewById(R.id.dark_button);
        Button englishBtn = findViewById(R.id.english_button);
        Button persianBtn = findViewById(R.id.persian_button);

        // ---------- Observe DataStore ----------
        lifecycleScope.launch {
            settingsRepository.getTheme().collect(theme -> {
                themeText.setText("Theme: " + theme);
            });
        }

        lifecycleScope.launch {
            settingsRepository.getLanguage().collect(language -> {
                languageText.setText("Language: " + language);
            });
        }

        // ---------- Buttons ----------
        lightBtn.setOnClickListener(v -> {
            lifecycleScope.launch { settingsRepository.setTheme("light"); }
        });

        darkBtn.setOnClickListener(v -> {
            lifecycleScope.launch { settingsRepository.setTheme("dark"); }
        });

        englishBtn.setOnClickListener(v -> {
            lifecycleScope.launch { settingsRepository.setLanguage("en"); }
        });

        persianBtn.setOnClickListener(v -> {
            lifecycleScope.launch { settingsRepository.setLanguage("fa"); }
        });
    }
}
