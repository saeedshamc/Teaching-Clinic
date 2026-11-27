# پروژهٔ جلسه ۲۷ — سیستم دفترچهٔ تلفن

یک دفترچهٔ تلفن بسازید که:
- `Map<String, String>` برای name → phone
- `Map<String, List<String>>` برای group → members (اختیاری)
- متدهای `addContact`, `removeContact`, `searchContact`, `displayAll`
- جستجو بر اساس نام یا شماره
- در `main` چند مخاطب اضافه/حذف/جستجو کنید

نمونه:
```
Contacts:
Ali → 09123456789
Sara → 09351234567
```

## صورت پروژه
پاسخ در `solution/PhoneBook.java` — از `HashMap` و `entrySet` استفاده کنید.
