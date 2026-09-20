# جلسه 98: کامپوننت‌های Bulma — navbar، card، modal، panel، tabs (2 ساعت)

## اهداف یادگیری
- ساختار `navbar` و burger (با JS کوچک)
- `card` برای محتوا
- `modal` (فعال‌سازی با کلاس `is-active`)
- `panel` و `tabs`

---

## 1. Navbar

```html
<nav class="navbar" role="navigation">
  <div class="navbar-brand">
    <a class="navbar-item">Brand</a>
    <a role="button" class="navbar-burger" data-target="navMenu">...</a>
  </div>
  <div id="navMenu" class="navbar-menu">...</div>
</nav>
```

با کلیک burger، کلاس `is-active` را به burger و menu اضافه کنید.

---

## 2. Card

```html
<div class="card">
  <div class="card-content">...</div>
  <footer class="card-footer">...</footer>
</div>
```

---

## 3. Modal

```html
<div class="modal">
  <div class="modal-background"></div>
  <div class="modal-card">...</div>
</div>
```

باز: `modal.classList.add("is-active")`.

---

## 4. Tabs و Panel

`tabs` برای ناوبری ظاهری؛ `panel` برای لیست تنظیمات/فیلتر.

---

## تمرین‌ها
### آسان
یک card با عنوان و دکمه.
### متوسط
Modal را با دو دکمه باز/بسته کنید.
### چالش
Tabs با سه آیتم و محتوای جدا (نمایش با JS ساده).

## گام بعد
[جلسه 99: پروژه فروشگاه](../session-99/index.html)
