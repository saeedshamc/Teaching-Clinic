// CrudApiActivity.java — پاسخ کامل پروژهٔ جلسه ۸۵
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class CrudApiActivity extends AppCompatActivity {

    private TextView resultText;
    private ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_api);

        resultText = findViewById(R.id.result_text);
        Button getBtn = findViewById(R.id.get_button);
        Button createBtn = findViewById(R.id.create_button);
        Button updateBtn = findViewById(R.id.update_button);
        Button deleteBtn = findViewById(R.id.delete_button);

        api = RetrofitClient.getApiService();

        getBtn.setOnClickListener(v -> getUsers());
        createBtn.setOnClickListener(v -> createUser());
        updateBtn.setOnClickListener(v -> updateUser());
        deleteBtn.setOnClickListener(v -> deleteUser());
    }

    private void getUsers() {
        api.getUsers().enqueue(new Callback<List<User>>() {
            @Override public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StringBuilder sb = new StringBuilder("Users:\n");
                    for (User u : response.body()) sb.append(u.getName()).append("\n");
                    resultText.setText(sb.toString());
                }
            }
            @Override public void onFailure(Call<List<User>> call, Throwable t) {
                resultText.setText("Failed: " + t.getMessage());
            }
        });
    }

    private void createUser() {
        User user = new User("New User", "new@example.com", "user");
        api.createUser(user).enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    resultText.setText("Created: " + response.body().getName());
                }
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
                resultText.setText("Failed: " + t.getMessage());
            }
        });
    }

    private void updateUser() {
        User user = new User("Updated User", "updated@example.com", "admin");
        api.updateUser(1, user).enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    resultText.setText("Updated: " + response.body().getName());
                }
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
                resultText.setText("Failed: " + t.getMessage());
            }
        });
    }

    private void deleteUser() {
        api.deleteUser(1).enqueue(new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) {
                resultText.setText("Deleted user 1");
            }
            @Override public void onFailure(Call<Void> call, Throwable t) {
                resultText.setText("Failed: " + t.getMessage());
            }
        });
    }
}
