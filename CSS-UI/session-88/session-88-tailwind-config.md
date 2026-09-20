# جلسه 88: پیکربندی تم Tailwind (2 ساعت)

## اهداف یادگیری
- گسترش تم: رنگ، فونت، breakpoint
- تفاوت `theme.extend` (v3) با `@theme` (v4)
- تعریف توکن برند روی CDN با `tailwind.config`

---

## 1. برند روی CDN آموزشی

```html
<script src="https://cdn.tailwindcss.com"></script>
<script>
  tailwind.config = {
    theme: {
      extend: {
        colors: {
          brand: {
            500: "#0ea5e9",
            700: "#0369a1",
          },
        },
        fontFamily: {
          display: ["Segoe UI", "Tahoma", "sans-serif"],
        },
      },
    },
  };
</script>
```

سپس: `bg-brand-500`، `font-display`.

---

## 2. در Vite (v4)

اغلب در CSS:

```css
@import "tailwindcss";

@theme {
  --color-brand-500: #0ea5e9;
  --font-display: "Segoe UI", Tahoma, sans-serif;
}
```

مستندات نسخهٔ نصب‌شده را چک کنید — API بین v3 و v4 فرق دارد، ایده یکی است: توکن متمرکز.

---

## 3. extend در برابر جایگزینی کامل

`extend` مقادیر پیش‌فرض را نگه می‌دارد و اضافه می‌کند. جایگزینی کامل `theme.colors` بدون مراقبت، پالت پیش‌فرض را حذف می‌کند.

---

## اشتباهات رایج
- تعریف رنگ بدون مقیاس (فقط یک hex) وقتی به `brand-500` عادت دارید
- فراموش کردن اینکه تغییر config روی CDN باید قبل از استفاده کلاس‌ها باشد
- مخلوط کردن نام توکن با کلاس Bootstrap

---

## تمرین‌ها
### آسان
یک رنگ `accent` اضافه و روی دکمه استفاده کنید.
### متوسط
`borderRadius.xl2` سفارشی تعریف کنید.
### چالش
در پروژه Vite یک `@theme` با رنگ برند بسازید.

## گام بعد
[جلسه 89: پلاگین‌ها](../session-89/index.html)
