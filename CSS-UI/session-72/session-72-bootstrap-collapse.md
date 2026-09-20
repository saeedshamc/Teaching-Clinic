# جلسه 72: Accordion، Tabs و Collapse (2 ساعت)

## اهداف یادگیری
- ساخت تب با `nav-tabs` و `tab-pane`
- ساخت FAQ با `accordion`
- فهم رابطه Accordion با Collapse
- استفاده درست از `data-bs-parent` برای باز بودن فقط یک آیتم

---

## 1. Tabs

تب‌ها محتوا را بدون ترک صفحه عوض می‌کنند:

```html
<ul class="nav nav-tabs" role="tablist">
  <li class="nav-item" role="presentation">
    <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab">خانه</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab">پروفایل</button>
  </li>
</ul>
<div class="tab-content">
  <div class="tab-pane fade show active" id="home" role="tabpanel">محتوای خانه</div>
  <div class="tab-pane fade" id="profile" role="tabpanel">محتوای پروفایل</div>
</div>
```

جایگزین ظاهر: `nav-pills` به‌جای `nav-tabs`.

`data-bs-target` باید با `id` همان `tab-pane` یکی باشد.

---

## 2. Collapse ساده

برای باز/بسته کردن یک بلوک:

```html
<button class="btn btn-primary" data-bs-toggle="collapse" data-bs-target="#more">بیشتر</button>
<div class="collapse" id="more">متن پنهان...</div>
```

---

## 3. Accordion

Accordion چند Collapse است که معمولاً فقط یکی باز می‌ماند:

```html
<div class="accordion" id="faq">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#a1">سؤال ۱</button>
    </h2>
    <div id="a1" class="accordion-collapse collapse show" data-bs-parent="#faq">
      <div class="accordion-body">پاسخ ۱</div>
    </div>
  </div>
</div>
```

`data-bs-parent="#faq"` باعث می‌شود با باز شدن یک آیتم، بقیه بسته شوند. اگر parent نگذارید، چند آیتم هم‌زمان باز می‌مانند.

---

## اشتباهات رایج
- فراموش کردن `bootstrap.bundle.min.js`
- `id` تکراری بین paneها
- اشتباه نوشتن `data-bs-target` (باید `#id` باشد)
- گذاشتن `accordion-button` بدون `type="button"` داخل فرم

---

## تمرین‌ها
### آسان
یک تب سوم اضافه کنید.
### متوسط
Accordion را به سه سؤال متداول گسترش دهید.
### چالش
با `nav-pills` و `flex-column` یک منوی عمودی تب بسازید (`col` برای سایدبار).

## خلاصه
Tabs برای سوییچ افقی محتوا، Accordion برای FAQ و بخش‌های تاشو.

## گام بعد
[جلسه 73: Carousel](../session-73/index.html)
