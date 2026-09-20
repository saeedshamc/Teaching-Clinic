# جلسه 13: مدیریت خطا (2 ساعت)

## 🎯 اهداف یادگیری
- `try / catch / finally` را برای کد شکست‌پذیر بنویسید
- `Error` سفارشی پرتاب کنید
- خطای همزمان را با Promise/async قاطی نکنید
- الگوی نتیجه (`{ ok, data }` یا `{ ok, error }`) را به‌عنوان جایگزین پرتاب بشناسید

---

## 1. چرا catch؟

بدون catch، یک خطا اجرای اسکریپت را در همان تیک متوقف می‌کند.

```javascript
try {
    JSON.parse("{");
} catch (err) {
    console.error(err.message);
} finally {
    console.log("همیشه اجرا می‌شود");
}
```

`finally` برای پاکسازی است (بستن UI لودینگ)، چه موفق چه ناموفق.

---

## 2. پرتاب Error

```javascript
function divide(a, b) {
    if (b === 0) {
        throw new Error("تقسیم بر صفر مجاز نیست");
    }
    return a / b;
}
```

پیام را برای انسان بنویسید. برای نوع خطا می‌توانید `class ValidationError extends Error` بسازید.

---

## 3. async و خطا

```javascript
try {
    fetch("/x").then((r) => r.json());
} catch (e) {
    // این catch شبکه را نمی‌گیرد!
}
```

Promise ردشده را با `.catch` یا `async/await` داخل `try` بگیرید (جلسه 15).

---

## 4. الگوی نتیجه

گاهی پرتاب مناسب نیست (مثلاً اعتبارسنجی فرم):

```javascript
function parseAge(raw) {
    const n = Number(raw);
    if (!Number.isInteger(n) || n < 0) {
        return { ok: false, error: "سن نامعتبر" };
    }
    return { ok: true, data: n };
}
```

قانون تجربی: خطای برنامه‌نویس → throw؛ ورودی کاربر → نتیجهٔ قابل نمایش.

---

## 5. تمرین‌ها

### تمرین 1
`JSON.parse` را روی رشتهٔ خراب در try/catch اجرا کنید.

### تمرین 2
تابعی که اگر آرگومان عدد نباشد Error پرتاب کند.

### تمرین 3 (چالش)
`parseAge` با الگوی `{ ok, data | error }`.

---

## 6. نکات مهم

1. ✅ catch را خالی نگذارید — لاگ یا پیام به کاربر
2. ✅ `err.message` را به کاربر خام نشان ندهید اگر جزئیات داخلی دارد
3. ❌ از throw برای کنترل جریان عادی (مثلاً حلقه) استفاده نکنید

---

## 7. خلاصه جلسه

- try / catch / finally
- throw new Error
- خطاهای async جدا هستند
- الگوی نتیجه برای ورودی کاربر

**آماده برای جلسه بعد: Promise و زنجیره! 🚀**
