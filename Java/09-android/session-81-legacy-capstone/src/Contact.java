// Contact.java — model برای contact
// نیازمند Android SDK
package com.example.myapp;

public class Contact {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String category;

    public Contact(int id, String name, String phone, String email, String category) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getCategory() { return category; }
}
