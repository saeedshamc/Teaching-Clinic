# جلسه 87: @apply و لایه‌ها در Tailwind (2 ساعت)

## اهداف یادگیری
- مفهوم لایه‌ها: base، components، utilities
- استخراج الگوی تکراری با `@apply` (در فایل CSS پروژه Vite)
- دانستن محدودیت `@apply` و وقت استفاده از کامپوننت HTML/JSX

---

## 1. چرا @apply؟

اگر یک دکمه را ده بار با همان کلاس‌های بلند تکرار می‌کنید:

```html
<button class="rounded-lg bg-sky-600 px-4 py-2 font-medium text-white hover:bg-sky-700">...</button>
```

می‌توانید در CSS ورودی Vite بنویسید:

```css
@layer components {
  .btn-primary {
    @apply rounded-lg bg-sky-600 px-4 py-2 font-medium text-white hover:bg-sky-700;
  }
}
```

سپس: `<button class="btn-primary">`.

---

## 2. لایه‌ها

| لایه | نقش |
|------|-----|
| `base` | ریست و استایل پیش‌فرض تگ‌ها |
| `components` | کلاس‌های ترکیبی مثل `.card` |
| `utilities` | همان utilityهای Tailwind و سفارشی |

ترتیب لایه جلوی جنگ specificity را می‌گیرد.

---

## 3. در این جلسهٔ CDN

CDN به‌تنهایی `@apply` را مثل بیلد Vite پردازش نمی‌کند. اینجا الگوی مفهومی + کلاس‌های شبیه‌سازی‌شده در `style.css` را می‌بینید؛ در پروژه واقعی از `@import "tailwindcss"` و `@layer` استفاده کنید.

---

## اشتباهات رایج
- `@apply` کردن همه‌چیز (HTML شلوغ‌تر از CSS شلوغ نیست اگر تکرار کم باشد)
- قرار دادن utilityهای یک‌بارمصرف داخل components
- فراموش کردن که `@apply` به بیلد نیاز دارد

---

## تمرین‌ها
### آسان
لیست ۳ تکرار دکمه را روی کاغذ به یک `.btn` تبدیل کنید.
### متوسط
`.card` با padding و shadow تعریف کنید (مفهومی یا در Vite).
### چالش
یک لایه `base` برای `a { @apply text-sky-700 underline; }`.

## گام بعد
[جلسه 88: tailwind.config](../session-88/index.html)
