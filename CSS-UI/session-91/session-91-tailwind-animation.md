# جلسه 91: انیمیشن در Tailwind (2 ساعت)

## اهداف یادگیری
- `transition-*` و مدت/easing
- `animate-spin`، `animate-pulse`، `animate-bounce`
- حرکت با `transform` و `hover:-translate-y-1`
- احترام به `prefers-reduced-motion` (آگاهی)

---

## 1. Transition

```html
<button class="transition bg-sky-500 hover:bg-sky-600 duration-200 ease-out">
```

یا `transition-colors` / `transition-transform` برای محدود کردن ویژگی‌ها.

---

## 2. Animate آماده

| کلاس | کاربرد |
|------|--------|
| `animate-spin` | لودینگ |
| `animate-pulse` | اسکلتون |
| `animate-bounce` | توجه کوتاه |

برای انیمیشن سفارشی در config یا `@theme` keyframes تعریف می‌شود.

---

## 3. Transform

```html
<article class="transition hover:-translate-y-1 hover:shadow-lg">
```

---

## اشتباهات رایج
- انیمیشن دائمی مزاحم روی محتوای اصلی
- فراموش کردن transition قبل از hover transform
- اسپین بدون `aria-label` / متن جایگزین برای لودینگ

---

## تمرین‌ها
### آسان
دکمه‌ای با `active:scale-95`.
### متوسط
کارت با lift در هاور.
### چالش
اسکلتون pulse سه‌خطی.

## گام بعد
[جلسه 92: استخراج کامپوننت](../session-92/index.html)
