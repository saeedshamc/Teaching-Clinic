# جلسه ۲۰: پروژه OOP — سیستم مدیریت کتابخانه (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- ترکیب **همه مفاهیم OOP** در یک پروژه واقعی
- طراحی سلسله‌مراتب کلاس‌ها
- استفاده از Interface برای Repository
- Encapsulation در عمل
- منوی کنسولی و CRUD ساده
- آماده‌سازی برای Generics و LINQ

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | معرفی پروژه | نیازمندی‌ها و طراحی |
| 10–25 | مدل‌ها | Book, Member, Loan |
| 25–45 | Repository | IBookRepository, InMemory |
| 45–65 | LibraryService | منطق کسب‌وکار |
| 65–80 | منوی کنسول | CRUD و امانت |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. نیازمندی‌های پروژه

سیستم مدیریت کتابخانه باید:

1. **کتاب** — ثبت، جستجو، حذف
2. **عضو** — ثبت‌نام، نمایش
3. **امانت** — امانت دادن و برگشت کتاب
4. **گزارش** — کتاب‌های موجود، امانت‌های فعال

---

## ۲. طراحی کلاس‌ها

```
IBookRepository ──→ InMemoryBookRepository
IMemberRepository ──→ InMemoryMemberRepository

Book (Entity)
Member (Entity)
Loan (Entity)

LibraryService (Business Logic)
Program (UI / Menu)
```

---

## ۳. Entity — Book

```csharp
class Book
{
    public string Isbn { get; }
    public string Title { get; set; }
    public string Author { get; set; }
    public bool IsAvailable { get; private set; } = true;

    public Book(string isbn, string title, string author)
    {
        Isbn = isbn;
        Title = title;
        Author = author;
    }

    public void MarkBorrowed() => IsAvailable = false;
    public void MarkReturned() => IsAvailable = true;
}
```

---

## ۴. Interface Repository

```csharp
interface IBookRepository
{
    void Add(Book book);
    Book? FindByIsbn(string isbn);
    IEnumerable<Book> GetAll();
    IEnumerable<Book> SearchByTitle(string keyword);
    bool Remove(string isbn);
}
```

**چرا Interface؟** فردا می‌توانید `SqlBookRepository` بسازید بدون تغییر `LibraryService`.

---

## ۵. LibraryService

```csharp
class LibraryService
{
    private readonly IBookRepository _books;
    private readonly IMemberRepository _members;
    private readonly List<Loan> _loans = new();

    public bool BorrowBook(string isbn, int memberId)
    {
        var book = _books.FindByIsbn(isbn);
        var member = _members.FindById(memberId);
        if (book == null || member == null || !book.IsAvailable)
            return false;

        book.MarkBorrowed();
        _loans.Add(new Loan(book, member, DateTime.Now));
        return true;
    }
}
```

---

## ۶. اصول طراحی به‌کار رفته

| اصل | کاربرد |
|-----|--------|
| Encapsulation | `IsAvailable` فقط از متدها |
| Abstraction | `IBookRepository` |
| Polymorphism | Repositoryهای مختلف |
| SRP | هر کلاس یک مسئولیت |
| DIP | Service به Interface وابسته |

---

## ۷. منوی کنسول

```
=== کتابخانه ===
1. افزودن کتاب
2. جستجوی کتاب
3. ثبت عضو
4. امانت کتاب
5. برگشت کتاب
6. گزارش
0. خروج
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_entities.cs](./examples/01_entities.cs) | Book, Member, Loan |
| [02_repositories.cs](./examples/02_repositories.cs) | Interface + InMemory |
| [03_library_service.cs](./examples/03_library_service.cs) | منطق کسب‌وکار |
| [04_console_menu.cs](./examples/04_console_menu.cs) | منوی کامل |

---

## ۹. گسترش‌های پیشنهادی

- ذخیره در فایل JSON (جلسه ۲۹)
- جریمه دیرکرد
- محدودیت تعداد امانت
- Unit Test (جلسه ۳۳)

---

## ۱۰. اجرای مثال‌ها

```powershell
./run-example.ps1 session-20-oop-project/examples/04_console_menu.cs
```

---

## ۱۱. اشتباهات رایج در پروژه

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| همه در Program.cs | غیرقابل نگهداری | کلاس‌های جدا |
| بدون Interface | وابستگی سخت | Repository pattern |
| public field | بدون کنترل | Property |
| منطق در UI | تست‌ناپذیر | Service layer |
| کپی-پیست CRUD | تکرار | Generic Repository (جلسه ۲۱) |

---

## ۱۲. تمرین کلاسی

1. اجرای پروژه و امانت ۲ کتاب
2. افزودن `SearchByAuthor`
3. گزارش امانت‌های فعال

---

## ۱۳. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۴. خلاصه

```
Entity     → داده (Book, Member)
Repository → دسترسی داده (Interface)
Service    → منطق کسب‌وکار
UI         → منوی کنسول
```

این پروژه پایه OOP شماست — در جلسات بعد Generic و LINQ آن را قوی‌تر می‌کنیم.

---

**جلسه قبل:** [۱۹ — Encapsulation](../session-19-encapsulation/) | **بعد:** [۲۱ — Generics](../session-21-generics/)
