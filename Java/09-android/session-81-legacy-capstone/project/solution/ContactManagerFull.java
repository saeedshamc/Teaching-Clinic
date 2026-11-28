// ContactManagerFull.java — پاسخ کامل پروژهٔ جلسه ۸۱
// نیازمند Android SDK — این یک mini project است
package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ContactManagerFull extends AppCompatActivity implements ContactAdapter.OnContactClickListener {

    private List<Contact> contacts;
    private ContactAdapter adapter;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);

        contacts = new ArrayList<>();
        contacts.add(new Contact(1, "Ali", "123-4567", "ali@mail.com", "Friends"));
        contacts.add(new Contact(2, "Sara", "987-6543", "sara@mail.com", "Family"));
        contacts.add(new Contact(3, "Reza", "555-1234", "reza@mail.com", "Work"));

        RecyclerView recyclerView = findViewById(R.id.contact_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(contacts, this);
        recyclerView.setAdapter(adapter);

        statusText = findViewById(R.id.status);

        Button addBtn = findViewById(R.id.add_button);
        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivityForResult(intent, 1);
        });

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
                statusText.setText("Added: " + contact.getName());
            }
        }
    }

    @Override
    public void onContactClick(Contact contact) {
        ContactDetailFragment fragment = (ContactDetailFragment) getSupportFragmentManager()
            .findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.updateContact(contact);
            statusText.setText("Selected: " + contact.getName());
        }
    }
}
