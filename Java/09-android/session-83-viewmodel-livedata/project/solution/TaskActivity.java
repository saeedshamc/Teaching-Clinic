// TaskActivity.java — پاسخ پروژهٔ جلسه ۸۳
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TaskActivity extends AppCompatActivity {

    private TaskViewModel viewModel;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.task_recycler);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        EditText titleInput = findViewById(R.id.title_input);
        EditText descInput = findViewById(R.id.desc_input);
        Button addBtn = findViewById(R.id.add_button);
        TextView statusText = findViewById(R.id.status);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(new java.util.ArrayList<>(), task -> {
            // handle task click
        });
        recyclerView.setAdapter(adapter);

        viewModel.getTasks().observe(this, tasks -> {
            adapter.updateTasks(tasks);
            statusText.setText(tasks.size() + " tasks");
        });

        viewModel.getLoading().observe(this, loading -> {
            progressBar.setVisibility(loading ? android.view.View.VISIBLE : android.view.View.GONE);
        });

        viewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

        addBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();
            String desc = descInput.getText().toString().trim();
            if (!title.isEmpty()) {
                viewModel.addTask(title, desc, "MEDIUM");
                titleInput.setText("");
                descInput.setText("");
            }
        });

        viewModel.loadTasks();
    }
}
