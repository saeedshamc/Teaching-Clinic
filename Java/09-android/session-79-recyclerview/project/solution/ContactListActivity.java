// ContactListActivity.java — پاسخ پروژهٔ جلسه ۷۹
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        RecyclerView recyclerView = findViewById(R.id.contact_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ContactAdapter.Contact> contacts = new ArrayList<>();
        contacts.add(new ContactAdapter.Contact("Ali", "123-4567", "ali@mail.com"));
        contacts.add(new ContactAdapter.Contact("Sara", "987-6543", "sara@mail.com"));
        contacts.add(new ContactAdapter.Contact("Reza", "555-1234", "reza@mail.com"));

        recyclerView.setAdapter(new ContactAdapter(contacts));
    }
}
