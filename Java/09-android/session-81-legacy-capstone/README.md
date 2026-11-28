# جلسه ۸۱ — Legacy Android Capstone: سیستم مدیریت مخاطبین

## اهداف یادگیری
- همهٔ مباحث Legacy Android را ترکیب کنید
- یک برنامهٔ کامل با Activities, Intents, Layouts, RecyclerView, Fragments بسازید
- `onCreate`, `findViewById`, `setOnClickListener` را به‌کار ببرید

## پیش‌نیازها
- جلسات ۷۶–۸۰

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Contact Manager (Legacy)

یک سیستم مدیریت مخاطبین بسازید که:

1. **MainActivity** — لیست مخاطبین با RecyclerView
2. **AddContactActivity** — فرم برای افزودن مخاطب
3. **ContactAdapter** — RecyclerView adapter
4. **ContactDetailFragment** — نمایش جزئیات مخاطب
5. **Intent** — برای navigation و data passing

### معماری

```java
public class Contact {
    String name, phone, email, category;
}

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    // RecyclerView adapter
}

public class MainActivity extends AppCompatActivity {
    // RecyclerView + Fragment container
}

public class AddContactActivity extends AppCompatActivity {
    // Form برای add contact
}
```

## کد مهم

`src/Contact.java` — model.
`src/ContactAdapter.java` — adapter.
`src/MainActivity.java` — main activity.
`src/AddContactActivity.java` — add contact.
`src/ContactDetailFragment.java` — detail fragment.

## جمع‌بندی فاز ۶ (بخش Legacy)
- Setup, Activities/Intents, Layouts/XML, RecyclerView, Fragments ✅
- **بعدی: Jetpack — modern Android development**
