# تمرین‌های جلسه ۳۷ — EF Core

## کلاسی (۳۰ دقیقه)

1. Entity `Book` (Id, Title, Author, Price)
2. InMemory DbContext با CRUD
3. LINQ: کتاب‌های بالای 500k

## خانه (۴۵ دقیقه)

4. پروژه واقعی + `Microsoft.EntityFrameworkCore.InMemory`
5. Entity `Author` + `Book` — One-to-Many
6. Migration با SQL Server LocalDB

## Setup

```powershell
dotnet add package Microsoft.EntityFrameworkCore
dotnet add package Microsoft.EntityFrameworkCore.InMemory
dotnet ef migrations add InitialCreate
dotnet ef database update
```

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
