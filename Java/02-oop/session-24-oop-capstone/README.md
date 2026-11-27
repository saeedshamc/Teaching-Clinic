# جلسه ۲۴ — مینی‌پروژهٔ جمع‌بندی: سیستم مدیریت کتابخانه

## اهداف یادگیری
- همهٔ مباحث فاز ۲ را در یک پروژهٔ واقعی ترکیب کنید
- Class، inheritance، polymorphism، interface، enum را هم‌زمان به‌کار ببرید
- کد تمیز و ماژولار بنویسید

## پیش‌نیازها
- همهٔ جلسات ۱۶–۲۳ فاز ۲

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Library Management System

یک سیستم کتابخانه بسازید که:

1. **مدل‌ها**:
   - `Book` — title, author, isbn, available
   - `Member` — name, id, borrowedBooks
   - `enum BookStatus` — AVAILABLE, BORROWED, LOST

2. **رابط‌ها**:
   - `interface Borrowable` — borrow(), return()
   - `interface Searchable` — search(query)

3. **کلاس‌ها**:
   - `Library` — لیست کتاب‌ها و اعضا، متدهای addBook, addMember, borrowBook, returnBook
   - `Book` implements `Borrowable`, `Searchable`

4. **عملیات**:
   - افزودن کتاب و عضو
   - borrow/return کتاب
   - جستجو بر اساس عنوان/نویسنده
   - نمایش لیست کتاب‌های.borrowed هر عضو
   - آمار: تعداد کتاب‌ها، اعضا، کتاب‌های borrow شده

### معماری پیشنهادی

```java
enum BookStatus { AVAILABLE, BORROWED, LOST }

interface Borrowable { void borrow(); void returnBook(); }
interface Searchable { boolean matches(String query); }

class Book implements Borrowable, Searchable {
    String title, author, isbn;
    BookStatus status;
    Member borrowedBy;
    // ...
}

class Member {
    String name;
    int id;
    List<Book> borrowedBooks;   // فاز ۲ collections — فعلاً ArrayList
}

class Library {
    Book[] books;
    Member[] members;
    // ...
}
```

## کد مهم

`src/LibrarySystem.java` — پیاده‌سازی کامل با همهٔ مباحث.

## خطاهای رایج
- `borrowedBy` را null چک نکردن → NullPointerException.
- `==` برای مقایسهٔ String title → `equals()`.
- enum را `new` کردن → compile error.
- interface را فراموش کردن در implements → abstract تلقی می‌شود.

## جمع‌بندی فاز ۲
- OOP: class، object، inheritance، polymorphism، abstract، interface، enum، inner ✅
- **آماده برای فاز ۳: Exception، IO و Concurrency**

**فاز ۳ جلسهٔ بعد:** Exception handling.
