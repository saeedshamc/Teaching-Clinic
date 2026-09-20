# جلسه 95: مقدمه Bulma (2 ساعت)

## اهداف یادگیری
- فلسفه Bulma: کلاس‌محور، مدرن، **بدون JS اجباری**
- تفاوت با Bootstrap (کامپوننت JS) و Tailwind (utility-first)
- شروع با CDN
- اولین کلاس‌ها: `button`، `notification`، `container`

---

## 1. Bulma چیست؟

فریم‌ورک CSS مبتنی بر Flexbox که:

- کامپوننت‌هایی با نام خوانا دارد (`navbar`، `card`، `modal` ساختار HTML)
- برای تعامل (مثلاً باز شدن منو) JS را خودتان می‌نویسید یا از مثال‌های مستندات الگو می‌گیرید
- با Sass قابل سفارشی‌سازی است (جلسه ۹۹)

---

## 2. CDN

```html
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@1.0.2/css/bulma.min.css">
```

برای RTL می‌توانید `dir="rtl"` بگذارید و در صورت نیاز اصلاحات کوچک در `style.css` اضافه کنید.

---

## 3. مقایسه سه‌تایی (پیش‌نمایش جلسه ۱۰۰)

| | Bootstrap | Tailwind | Bulma |
|--|-----------|----------|-------|
| ذهنیت | کامپوننت + JS | utility | کامپوننت CSS |
| دکمه | `btn btn-primary` | چند utility | `button is-primary` |
| وابستگی JS | زیاد | ندارد | اختیاری |

در این بلوک فقط Bulma بنویسید.

---

## اشتباهات رایج
- قاطی کردن `btn` بوت‌استرپ با `button` بولما
- انتظار Modal خودکار بدون JS
- فراموش کردن `container` برای عرض خوانا

---

## تمرین‌ها
### آسان
یک `notification is-info`.
### متوسط
سه دکمه `is-primary` / `is-link` / `is-danger`.
### چالش
یک `buttons` group بسازید.

## گام بعد
[جلسه 96: Layout Bulma](../session-96/index.html)
