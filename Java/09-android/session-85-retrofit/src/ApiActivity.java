// ApiActivity.java — API calls با Retrofit
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class ApiActivity extends AppCompatActivity {

    private TextView resultText;
    private ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        resultText = findViewById(R.id.result_text);
        Button getUsersBtn = findViewById(R.id.get_users_button);
        Button getPostsBtn = findViewById(R.id.get_posts_button);
        Button createUserBtn = findViewById(R.id.create_user_button);

        api = RetrofitClient.getApiService();

        getUsersBtn.setOnClickListener(v -> getUsers());
        getPostsBtn.setOnClickListener(v -> getPosts());
        createUserBtn.setOnClickListener(v -> createUser());
    }

    private void getUsers() {
        resultText.setText("Loading users...");
        api.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (User user : users) {
                        sb.append(user.getName()).append(" — ").append(user.getEmail()).append("\n");
                    }
                    resultText.setText(sb.toString());
                } else {
                    resultText.setText("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                resultText.setText("Failed: " + t.getMessage());
            }
        });
    }

    private void getPosts() {
        resultText.setText("Loading posts...");
        api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Post> posts = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (Post post : posts) {
                        sb.append(post.getTitle()).append("\n");
                    }
                    resultText.setText(sb.toString());
                } else {
                    resultText.setText("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                resultText.setText("Failed: " + t.getMessage());
            }
        });
    }

    private void createUser() {
        User newUser = new User("New User", "new@example.com", "user");
        api.createUser(newUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    resultText.setText("Created: " + user.getName() + " (ID: " + user.getId() + ")");
                } else {
                    resultText.setText("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                resultText.setText("Failed: " + t.getMessage());
            }
        });
    }
}
