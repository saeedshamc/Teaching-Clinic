// ContactActivity.java — Activity با ViewModel و LiveData
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ContactActivity extends AppCompatActivity {

    private ContactViewModel viewModel;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // ---------- ViewModel ----------
        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        // ---------- Views ----------
        RecyclerView recyclerView = findViewById(R.id.contact_recycler);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        Button addBtn = findViewById(R.id.add_button);
        TextView statusText = findViewById(R.id.status);

        // ---------- RecyclerView ----------
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(new java.util.ArrayList<>(), contact -> {
            Toast.makeText(this, "Selected: " + contact.getName(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);

        // ---------- Observe LiveData ----------
        viewModel.getContacts().observe(this, contacts -> {
            adapter.updateContacts(contacts);
            statusText.setText(contacts.size() + " contacts");
        });

        viewModel.getLoading().observe(this, loading -> {
            progressBar.setVisibility(loading ? android.view.View.VISIBLE : android.view.View.GONE);
        });

        viewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

        // ---------- Buttons ----------
        addBtn.setOnClickListener(v -> {
            Contact contact = new Contact(
                (int) System.currentTimeMillis(),
                "New Contact",
                "000-0000",
                "new@mail.com",
                "Friends"
            );
            viewModel.addContact(contact);
        });

        // ---------- Load data ----------
        viewModel.loadContacts();
    }
}
