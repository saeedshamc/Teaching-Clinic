# جلسه 89: پلاگین‌های Tailwind — forms و typography (2 ساعت)

## اهداف یادگیری
- نقش پلاگین رسمی `@tailwindcss/forms`
- نقش `@tailwindcss/typography` برای محتوای `prose`
- نصب در پروژه Vite (مفهومی)
- استایل پایه فرم بدون پلاگین در CDN

---

## 1. @tailwindcss/forms

فرم‌های خام مرورگر زشت و ناهمگون‌اند. پلاگین forms ریست معقول روی `input`، `select`، `checkbox` می‌گذارد تا utilityها تمیزتر بنشینند.

```bash
npm install -D @tailwindcss/forms
```

ثبت پلاگین مطابق نسخه Tailwind (v3 در `tailwind.config.js`، v4 اغلب در CSS با `@plugin`).

---

## 2. @tailwindcss/typography

برای مقاله و Markdown:

```html
<article class="prose prose-slate lg:prose-lg">
  <h1>...</h1>
  <p>...</p>
</article>
```

`prose` استایل پیش‌فرض خوانا به تگ‌های داخل می‌دهد.

---

## 3. بدون پلاگین (همین دمو)

با utility دستی هم می‌شود؛ پلاگین تکرار و یکنواختی را کم می‌کند. در این صفحه یک بلوک `prose-like` شبیه‌سازی شده است.

---

## اشتباهات رایج
- انتظار `prose` بدون نصب پلاگین در بیلد واقعی
- استایل کردن هر تگ مقاله دستی به‌جای typography
- فراموش کردن dark: برای `prose-invert`

---

## تمرین‌ها
### آسان
یک checkbox با اندازه و رنگ سفارشی (utility).
### متوسط
مقاله کوتاه با سلسله‌مراتب heading.
### چالش
در Vite هر دو پلاگین را نصب و تست کنید.

## گام بعد
[جلسه 90: Dark Mode](../session-90/index.html)
