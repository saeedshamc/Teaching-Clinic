# جلسه 75: Bootstrap Icons و RTL (2 ساعت)

## اهداف یادگیری
- اتصال Bootstrap Icons از CDN
- استفاده از کلاس‌های `bi bi-*`
- فهم تفاوت CSS معمولی و فایل `bootstrap.rtl.min.css`
- نکات جهت در آیکون‌ها و کنترل‌ها (مثل carousel)

---

## 1. Bootstrap Icons

آیکون‌ها فونت/SVG مجموعه جدا هستند؛ با CSS خود Bootstrap یکی نیستند:

```html
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
```

استفاده:

```html
<i class="bi bi-heart-fill text-danger" aria-hidden="true"></i>
<span class="visually-hidden">علاقه‌مندی</span>
```

اگر آیکون معنای دکمه را می‌رساند، برای صفحه‌خوان متن جایگزین بگذارید (`aria-label` روی دکمه یا `visually-hidden`).

جستجوی نام آیکون: [icons.getbootstrap.com](https://icons.getbootstrap.com)

---

## 2. RTL در Bootstrap 5

برای فارسی دو کار مهم:

1. روی `<html>`: `lang="fa" dir="rtl"`
2. CSS: نسخهٔ **RTL**  
   `bootstrap.rtl.min.css` نه `bootstrap.min.css`

نسخه RTL فاصله‌ها، float منطقی، و خیلی از کامپوننت‌ها را آینه‌ای می‌کند. هنوز ممکن است بعضی آیکون‌های جهت‌دار (مثل فلش) نیاز به بررسی چشمی داشته باشند.

---

## 3. فاصله منطقی

به‌جای `ml-*` / `mr-*` از utilityهای منطقی استفاده کنید:

- `ms-*` = margin-inline-start
- `me-*` = margin-inline-end
- `ps-*` / `pe-*` برای padding

در RTL، `ms` سمت راست بصری است.

---

## اشتباهات رایج
- لود کردن CSS LTR برای سایت فارسی
- گذاشتن `<link>` آیکون‌ها داخل `<body>`
- تکیه فقط به آیکون بدون برچسب دسترس‌پذیر
- مخلوط کردن `margin-left` دستی با سیستم RTL

---

## تمرین‌ها
### آسان
سه آیکون `bi-house`، `bi-person`، `bi-gear` در یک نوار بسازید.
### متوسط
دکمه‌ای با آیکون + متن بسازید (`bi` قبل از متن با `ms-1` روی آیکون در LTR؛ در RTL با `me-1` تست کنید).
### چالش
یک `list-group` با آیکون وضعیت برای هر ردیف بسازید.

## خلاصه
Icons ظاهر را غنی می‌کنند؛ RTL را از همان اول درست بچینید تا بعداً جنگ CSS نکنید.

## گام بعد
[جلسه 76: سفارشی‌سازی Sass](../session-76/index.html)
