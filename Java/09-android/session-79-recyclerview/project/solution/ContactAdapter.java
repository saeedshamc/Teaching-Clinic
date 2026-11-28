// ContactAdapter.java — پاسخ پروژهٔ جلسه ۷۹
// نیازمند Android SDK
package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public static class Contact {
        String name, phone, email;
        Contact(String name, String phone, String email) {
            this.name = name; this.phone = phone; this.email = email;
        }
    }

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameText.setText(contact.name);
        holder.phoneText.setText(contact.phone);
        holder.emailText.setText(contact.email);
        holder.deleteBtn.setOnClickListener(v -> {
            contacts.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, phoneText, emailText;
        Button deleteBtn;
        ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.contact_name);
            phoneText = itemView.findViewById(R.id.contact_phone);
            emailText = itemView.findViewById(R.id.contact_email);
            deleteBtn = itemView.findViewById(R.id.delete_button);
        }
    }
}
