# پاسخ تمرین‌های جلسه ۸۱

## آسان

**۱ و ۲.**
```java
public class Contact {
    String name, phone, email, category;
    // getters/setters
}
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    // ViewHolder + onBindViewHolder
}
```

## متوسط

**۳ و ۴.**
```java
RecyclerView recyclerView = findViewById(R.id.contact_recycler);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(new ContactAdapter(contacts, this));

// AddContactActivity
Intent intent = new Intent(this, AddContactActivity.class);
startActivityForResult(intent, 1);
```

## سخت

**۵ و ۶.**
```java
// Intent for data passing
intent.putExtra("contact", contact);
Contact contact = (Contact) data.getSerializableExtra("contact");

// Fragment for details
FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
ft.replace(R.id.fragment_container, new ContactDetailFragment());
ft.commit();
```
