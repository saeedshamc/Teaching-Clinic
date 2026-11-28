// MainActivity.java — main activity با RecyclerView و Fragment
// نیازمند Android SDK
package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnContactClickListener {

    private List<Contact> contacts;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------- Data ----------
        contacts = new ArrayList<>();
        contacts.add(new Contact(1, "Ali", "123-4567", "ali@mail.com", "Friends"));
        contacts.add(new Contact(2, "Sara", "987-6543", "sara@mail.com", "Family"));
        contacts.add(new Contact(3, "Reza", "555-1234", "reza@mail.com", "Work"));

        // ---------- RecyclerView ----------
        RecyclerView recyclerView = findViewById(R.id.contact_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(contacts, this);
        recyclerView.setAdapter(adapter);

        // ---------- Add button ----------
        findViewById(R.id.add_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivityForResult(intent, 1);
        });

        // ---------- Fragment container ----------
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new ContactDetailFragment());
            ft.commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Contact contact = (Contact) data.getSerializableExtra("contact");
            if (contact != null) {
                contacts.add(contact);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onContactClick(Contact contact) {
        // Show contact details in fragment
        ContactDetailFragment fragment = (ContactDetailFragment) getSupportFragmentManager()
            .findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.updateContact(contact);
        }
    }
}
