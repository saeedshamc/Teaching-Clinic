# تمرین جلسه ۳۹ — ASP.NET MVC

## تمرین ۱ — Controller و Action (آسان)

یک کلاس `BookController` شبیه‌سازی کنید با:

- Action `Index` که لیست ۳ کتاب را برمی‌گرداند
- Action `Details(int id)` که کتاب را پیدا کند یا `NotFound` برگرداند
- Action `Search(string keyword)` که کتاب‌های حاوی keyword را فیلتر کند

**خروجی مورد انتظار:** نمایش نتیجه هر Action در Console

---

## تمرین ۲ — ViewModel و Validation (متوسط)

یک `RegisterViewModel` با فیلدهای زیر بسازید:

| فیلد | Validation |
|------|-----------|
| `Username` | Required، حداقل ۳ کاراکتر |
| `Email` | Required، EmailAddress |
| `Age` | Range(13, 120) |

تابع `ValidateAndPrint` بنویسید که مدل را validate کند و خطاها را چاپ کند.

**تست:** یک مدل معتبر و یک مدل نامعتبر

---

## تمرین ۳ — Razor Template (چالشی)

تابع `RenderBookTable` بنویسید که از لیست کتاب HTML table تولید کند (مثل مثال `04_razor_concepts.cs`).

فرم جستجو HTML هم تولید کنید:

```html
<form method="get" action="/Book/Search">
  <input name="keyword" />
  <button type="submit">جستجو</button>
</form>
```

---

## نکات

- تمرین ۱ و ۲ را در `exercise_01.cs` و `exercise_02.cs` حل کنید
- برای Validation از `System.ComponentModel.DataAnnotations` استفاده کنید
- Controller واقعی ASP.NET نیاز به پروژه Web دارد؛ اینجا Console شبیه‌سازی کنید

**راهنما:** [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
