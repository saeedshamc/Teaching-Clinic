// ContactAdapter.java — RecyclerView adapter برای contacts
// نیازمند Android SDK
package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> contacts;
    private OnContactClickListener listener;

    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    public ContactAdapter(List<Contact> contacts, OnContactClickListener listener) {
        this.contacts = contacts;
        this.listener = listener;
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
        holder.nameText.setText(contact.getName());
        holder.phoneText.setText(contact.getPhone());
        holder.emailText.setText(contact.getEmail());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onContactClick(contact);
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, phoneText, emailText;
        ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.contact_name);
            phoneText = itemView.findViewById(R.id.contact_phone);
            emailText = itemView.findViewById(R.id.contact_email);
        }
    }
}
