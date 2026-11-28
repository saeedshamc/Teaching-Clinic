// AddContactActivity.java — فرم برای افزودن مخاطب
// نیازمند Android SDK
package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        EditText nameInput = findViewById(R.id.name_input);
        EditText phoneInput = findViewById(R.id.phone_input);
        EditText emailInput = findViewById(R.id.email_input);
        Button saveBtn = findViewById(R.id.save_button);

        saveBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();

            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                Contact contact = new Contact(
                    contacts.size() + 1, name, phone, email, "Friends"
                );
                Intent resultIntent = new Intent();
                resultIntent.putExtra("contact", contact);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
