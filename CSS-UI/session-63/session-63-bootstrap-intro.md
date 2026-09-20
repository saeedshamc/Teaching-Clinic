# جلسه 63: مقدمه Bootstrap 5 (2 ساعت)

## اهداف یادگیری
- فلسفهٔ Bootstrap: کامپوننت آماده + utility
- شروع با CDN نسخهٔ RTL
- تفاوت مسیر CDN و مسیر npm/Sass (جلسه ۷۶)
- جدا نگه داشتن ذهنیت از Tailwind (از جلسه ۷۹)

---

## 1. Bootstrap چیست؟

یک فریم‌ورک CSS (با JS اختیاری برای بعضی کامپوننت‌ها) که:

- سیستم Grid دوازده‌ستونی دارد
- کامپوننت‌هایی مثل Navbar، Modal، Card دارد
- کلاس‌های utility برای فاصله و نمایش دارد

برای فارسی، فایل **RTL** را بگیرید تا جهت درست باشد.

---

## 2. شروع با CDN

```html
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.rtl.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
```

برای کامپوننت‌های فقط-CSS، JS لازم نیست. برای Collapse/Modal/Dropdown به bundle نیاز دارید.

---

## 3. اولین کلاس‌ها

```html
<div class="container">
  <button class="btn btn-primary">Primary</button>
  <button class="btn btn-outline-secondary">Secondary</button>
</div>
```

`container` عرض را محدود و وسط‌چین می‌کند. `btn btn-primary` دکمهٔ برند Bootstrap است.

---

## اشتباهات رایج
- مخلوط کردن کلاس‌های Tailwind وسط پروژه Bootstrap
- فراموش کردن نسخه RTL برای UI فارسی
- لود کردن JS قبل از اینکه DOM آماده باشد (اسکریپت ته body بهتر است)

---

## تمرین‌ها
### آسان
یک `alert alert-success` اضافه کنید.
### متوسط
سه دکمه با رنگ‌های primary/success/danger.
### چالش
یک `container` با دو پاراگراف `lead` بسازید.

## خلاصه
بلوک Bootstrap از اینجا تا جلسه ۷۸ جدا و کامل است.

## گام بعد
[جلسه 64: Grid Bootstrap](../session-64/index.html)
