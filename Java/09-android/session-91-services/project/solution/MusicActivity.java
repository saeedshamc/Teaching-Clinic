// MusicActivity.java — پاسخ پروژهٔ جلسه ۹۱
// نیازمند Android SDK
package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        TextView statusText = findViewById(R.id.status_text);
        Button playBtn = findViewById(R.id.play_button);
        Button pauseBtn = findViewById(R.id.pause_button);
        Button stopBtn = findViewById(R.id.stop_button);

        playBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MusicService.class);
            intent.setAction("PLAY");
            startForegroundService(intent);
            statusText.setText("Playing...");
        });

        pauseBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MusicService.class);
            intent.setAction("PAUSE");
            startService(intent);
            statusText.setText("Paused");
        });

        stopBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MusicService.class);
            intent.setAction("STOP");
            startService(intent);
            statusText.setText("Stopped");
        });
    }
}
