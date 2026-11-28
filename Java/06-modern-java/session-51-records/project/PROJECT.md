# پروژهٔ جلسه ۵۱ — سیستم مدیریت کتاب با Record

یک سیستم مدیریت کتاب بسازید که:
- `record Book(String title, String author, double price, int year)`
- compact constructor با validation (price > 0, year > 1900)
- `record Library` که `List<Book>` را مدیریت کند
- متدهای `addBook`, `findByAuthor`, `totalValue`, `averagePrice`
- در `main` چند کتاب اضافه کنید و گزارش چاپ کنید

نمونه:
```
Books: 3
Total value: 150000
Average price: 50000
By author: {Ali=2, Sara=1}
```

## صورت پروژه
پاسخ در `solution/Library.java` — از record و compact constructor استفاده کنید.
