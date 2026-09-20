# جلسه 71: Modal، Toast، Tooltip و Popover (2 ساعت)

## اهداف یادگیری
- ساخت Modal با `data-bs-toggle` و `data-bs-target`
- نمایش Toast برای پیام‌های کوتاه
- فعال‌سازی Tooltip و Popover با JavaScript
- دانستن اینکه این کامپوننت‌ها به `bootstrap.bundle.min.js` نیاز دارند

---

## 1. Modal

مودال یک دیالوگ روی صفحه است. معمولاً با دکمه باز می‌شود:

```html
<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#demoModal">
  باز کردن
</button>

<div class="modal fade" id="demoModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">عنوان</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="بستن"></button>
      </div>
      <div class="modal-body">متن بدنه</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">بستن</button>
      </div>
    </div>
  </div>
</div>
```

نکته‌ها:
- `fade` انیمیشن محو شدن می‌دهد
- `tabindex="-1"` برای فوکوس کیبورد لازم است
- همیشه `aria-label` برای دکمه بستن بگذارید

اندازه: `modal-dialog modal-sm` / `modal-lg` / `modal-xl`

---

## 2. Toast

برای اعلان موقت (مثل «ذخیره شد»):

```html
<div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
  <div class="toast-header">...</div>
  <div class="toast-body">...</div>
</div>
```

معمولاً Toast را با JS نشان می‌دهید:

```js
const toast = new bootstrap.Toast(document.getElementById('myToast'));
toast.show();
```

کانتینر `toast-container` را گوشه صفحه قرار دهید.

---

## 3. Tooltip

Tooltip با `data-bs-toggle="tooltip"` و `title` (یا `data-bs-title`) کار می‌کند، ولی **باید یک‌بار مقداردهی شود**:

```js
document.querySelectorAll('[data-bs-toggle="tooltip"]').forEach((el) => {
  new bootstrap.Tooltip(el);
});
```

بدون این خط، Tooltip ظاهر نمی‌شود.

---

## 4. Popover

شبیه Tooltip ولی محتوای بیشتر دارد:

```html
<button
  type="button"
  class="btn btn-outline-secondary"
  data-bs-toggle="popover"
  data-bs-title="عنوان"
  data-bs-content="توضیح کوتاه"
>
  Popover
</button>
```

و در JS:

```js
document.querySelectorAll('[data-bs-toggle="popover"]').forEach((el) => {
  new bootstrap.Popover(el);
});
```

---

## اشتباهات رایج
- فراموش کردن `bootstrap.bundle.min.js` (بدون آن Modal و Collapse کار نمی‌کنند)
- مقداردهی نکردن Tooltip/Popover
- گذاشتن چند Modal با `id` تکراری
- بستن Modal فقط با کلیک بیرون بدون دکمهٔ دسترس‌پذیر

---

## تمرین‌ها
### آسان
متن داخل Modal را عوض کنید و یک دکمه «تأیید» در footer اضافه کنید.
### متوسط
با کلیک دکمه، یک Toast سبز «عملیات موفق» نشان دهید.
### چالش
یک Popover با `data-bs-placement="left"` بسازید (در RTL جای قرارگیری را تست کنید).

## خلاصه
Overlayها قدرت Bootstrap در تعامل هستند؛ همیشه bundle و مقداردهی JS را چک کنید.

## گام بعد
[جلسه 72: Accordion و Tabs](../session-72/index.html)
