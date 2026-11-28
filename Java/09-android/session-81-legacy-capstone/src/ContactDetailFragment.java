// ContactDetailFragment.java — fragment برای نمایش جزئیات مخاطب
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContactDetailFragment extends Fragment {

    private TextView nameText, phoneText, emailText, categoryText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);
        nameText = view.findViewById(R.id.detail_name);
        phoneText = view.findViewById(R.id.detail_phone);
        emailText = view.findViewById(R.id.detail_email);
        categoryText = view.findViewById(R.id.detail_category);
        return view;
    }

    public void updateContact(Contact contact) {
        if (nameText != null) {
            nameText.setText(contact.getName());
            phoneText.setText(contact.getPhone());
            emailText.setText(contact.getEmail());
            categoryText.setText(contact.getCategory());
        }
    }
}
