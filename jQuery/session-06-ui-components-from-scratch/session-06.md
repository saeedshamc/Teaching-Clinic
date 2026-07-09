# جلسه ۶ — ساخت کامپوننت‌های UI از صفر (بدون jQuery UI)

## مقدمه

در پروژه‌های واقعی معمولاً یا از کتابخانه `jQuery UI` (مجموعه ویجت‌های آماده: Tabs, Dialog, Datepicker, Draggable, Sortable) استفاده می‌شود، یا کامپوننت‌ها را خودتان با jQuery خام می‌سازید. در این جلسه یاد می‌گیریم چطور رایج‌ترین کامپوننت‌های UI را **از صفر** و بدون هیچ وابستگی اضافه بسازیم — این کار درک شما از رویدادها، پیمایش DOM و مدیریت state را به‌شدت عمیق می‌کند.

## ۱. Tabs (تب‌ها)

### ساختار HTML

```html
<div class="tabs">
  <div class="tab-headers">
    <button class="tab-header active" data-tab="tab1">تب یک</button>
    <button class="tab-header" data-tab="tab2">تب دو</button>
  </div>
  <div class="tab-panel" id="tab1">محتوای تب یک</div>
  <div class="tab-panel" id="tab2" style="display:none">محتوای تب دو</div>
</div>
```

### منطق jQuery

```javascript
$('.tab-headers').on('click', '.tab-header', function () {
  var $header = $(this);
  var targetId = $header.data('tab');
  var $tabsContainer = $header.closest('.tabs');

  // غیرفعال‌کردن همه هدرها و پنل‌ها
  $tabsContainer.find('.tab-header').removeClass('active');
  $tabsContainer.find('.tab-panel').hide();

  // فعال‌کردن مورد انتخاب‌شده
  $header.addClass('active');
  $tabsContainer.find('#' + targetId).show();
});
```

نکته طراحی مهم: با استفاده از `closest('.tabs')` و `find()` داخل آن، این کد برای **چند نمونه Tabs مستقل روی یک صفحه** هم درست کار می‌کند (بدون اینکه به id های سراسری وابسته باشد).

## ۲. Modal / Dialog

### ساختار HTML

```html
<button id="openModalBtn">باز کردن مودال</button>

<div class="modal-overlay" id="myModal" style="display:none">
  <div class="modal-box">
    <button class="modal-close">✕</button>
    <h3>عنوان مودال</h3>
    <p>محتوای مودال...</p>
  </div>
</div>
```

### منطق jQuery

```javascript
function openModal($modal) {
  $modal.fadeIn(200);
  $('body').addClass('modal-open'); // برای جلوگیری از اسکرول پس‌زمینه
}
function closeModal($modal) {
  $modal.fadeOut(200);
  $('body').removeClass('modal-open');
}

$('#openModalBtn').on('click', function () {
  openModal($('#myModal'));
});

// بستن با دکمه ضربدر
$('.modal-close').on('click', function () {
  closeModal($(this).closest('.modal-overlay'));
});

// بستن با کلیک روی پس‌زمینه تیره (نه خودِ باکس مودال)
$('.modal-overlay').on('click', function (event) {
  if (event.target === this) { // یعنی خودِ overlay کلیک شده، نه فرزندانش
    closeModal($(this));
  }
});

// بستن با کلید Escape
$(document).on('keydown', function (event) {
  if (event.key === 'Escape') {
    closeModal($('.modal-overlay:visible'));
  }
});
```

نکته مهم `event.target === this`: چون کلیک روی هر جای داخل overlay (حتی داخل `.modal-box`) به‌خاطر bubbling به هندلر روی `.modal-overlay` هم می‌رسد، باید بررسی کنیم که آیا واقعاً خودِ overlay کلیک شده (نه چیزی داخلش). این یک تکنیک بسیار پرکاربرد است.

## ۳. Tooltip سفارشی

(نمونه پایه‌ای در جلسه ۵ با `offset()` ساخته شد؛ اینجا نسخه کامل‌تر با HTML پویا و موقعیت‌گذاری هوشمندتر می‌سازیم.)

```javascript
$(document).on('mouseenter', '[data-tooltip]', function () {
  var $el = $(this);
  var text = $el.data('tooltip');
  var $tip = $('<div class="custom-tooltip"></div>').text(text).appendTo('body');

  var off = $el.offset();
  $tip.css({
    top: off.top - $tip.outerHeight() - 8,
    left: off.left + ($el.outerWidth() / 2) - ($tip.outerWidth() / 2)
  });
}).on('mouseleave', '[data-tooltip]', function () {
  $('.custom-tooltip').remove();
});
```

نکته: چون Tooltip را در لحظه هاور می‌سازیم و در لحظه خروج حذف می‌کنیم (به‌جای اینکه از قبل در HTML بنویسیم)، هم DOM تمیزتر می‌ماند و هم این الگو با Event Delegation (`document.on(...)`) روی المان‌های پویا هم کار می‌کند.

## ۴. Dropdown با «بستن با کلیک بیرون»

```javascript
$('.dropdown-toggle').on('click', function (event) {
  event.stopPropagation(); // جلوگیری از رسیدن این کلیک به هندلر document (پایین)
  $(this).next('.dropdown-menu').toggle();
});

// کلیک در هر جای دیگر صفحه => بستن همه دراپ‌داون‌های باز
$(document).on('click', function () {
  $('.dropdown-menu').hide();
});
```

الگوی `event.stopPropagation()` روی دکمه باز/بسته‌کن + یک هندلر عمومی روی `document` برای بستن، یکی از رایج‌ترین الگوهای UI در jQuery است (همین الگو برای منوهای کشویی، اتوکامپلیت، و منوی کاربری هم استفاده می‌شود).

## ۵. Drag & Drop ساده (بدون jQuery UI) با HTML5 Drag Events

مرورگرهای مدرن رویدادهای Drag & Drop بومی دارند (`dragstart`, `dragover`, `drop`, ...) که می‌توان با jQuery مدیریتشان کرد:

```html
<ul id="sortableList">
  <li draggable="true" data-id="1">آیتم ۱</li>
  <li draggable="true" data-id="2">آیتم ۲</li>
  <li draggable="true" data-id="3">آیتم ۳</li>
</ul>
```

```javascript
var draggedId = null;

$('#sortableList').on('dragstart', 'li', function (event) {
  draggedId = $(this).data('id');
  $(this).addClass('dragging');
});

$('#sortableList').on('dragend', 'li', function () {
  $(this).removeClass('dragging');
});

// لازم است dragover را preventDefault کنیم تا drop اصلاً مجاز شود
$('#sortableList').on('dragover', 'li', function (event) {
  event.preventDefault();
});

$('#sortableList').on('drop', 'li', function (event) {
  event.preventDefault();
  var targetId = $(this).data('id');
  if (targetId === draggedId) return;

  var $dragged = $('#sortableList').find('[data-id="' + draggedId + '"]');
  var $target = $(this);

  // تشخیص اینکه آیتم کشیده‌شده باید قبل یا بعد از هدف قرار بگیرد
  if ($dragged.index() < $target.index()) {
    $dragged.insertAfter($target);
  } else {
    $dragged.insertBefore($target);
  }
});
```

نکته: `event.originalEvent` هم در دسترس است اگر به خصوصیات خام رویداد مرورگر (مثل `dataTransfer`) نیاز داشتید، چون jQuery رویداد را "نرمال‌سازی" می‌کند ولی رویداد اصلی مرورگر را هم نگه می‌دارد:

```javascript
$('li').on('dragstart', function (event) {
  event.originalEvent.dataTransfer.setData('text/plain', $(this).data('id'));
});
```

## ۶. اصل طراحی کلی این جلسه: State در `data()`، رفتار در Event Delegation

اگر به همه کامپوننت‌های بالا نگاه کنید، یک الگوی مشترک دارند:

1. **رفتار** (باز/بسته‌شدن، فعال/غیرفعال‌شدن) همیشه با **Event Delegation** پیاده‌سازی می‌شود، پس اگر بعداً یک تب یا آیتم جدید با جاوااسکریپت اضافه شود، همچنان کار می‌کند.
2. **وضعیت (State)** یا در کلاس CSS (`active`, `open`, `dragging`) نگه داشته می‌شود یا در `$.data()`، نه در متغیرهای سراسری پراکنده.
3. **پیمایش نسبی** (`closest`, `next`, `find`) به‌جای id های ثابت استفاده می‌شود تا کامپوننت چندبار در صفحه قابل استفاده باشد.

## ۷. تله‌های رایج

1. **فراموش‌کردن `event.stopPropagation()`** در دکمه‌های toggle که با یک هندلر سراسری روی document بسته می‌شوند → دکمه هرگز باز نمی‌ماند (چون همان کلیک باعث بسته‌شدن فوری هم می‌شود).
2. **فراموش‌کردن `event.preventDefault()`** در `dragover` → رویداد `drop` اصلاً اجرا نمی‌شود (این رفتار پیش‌فرض HTML5 است).
3. **مقایسه `event.target` به‌جای `this`/`event.currentTarget`** برای تشخیص «خودِ overlay کلیک شد یا فرزندش» — این‌ها اغلب به‌اشتباه یکسان فرض می‌شوند.
4. **نوشتن ID های ثابت برای چند نمونه از یک کامپوننت** (مثلاً چند Tabs در یک صفحه) → باعث تداخل و کار نکردن نمونه‌های دوم به بعد می‌شود؛ به‌جایش از `closest`/`find` نسبت به کانتینر استفاده کنید.

## ۸. تمرین‌های پیشنهادی

1. یک Accordion (شبیه جلسه ۲) را طوری بازنویسی کنید که چند نمونه مستقل از آن بتوانند هم‌زمان در یک صفحه باشند (با الگوی `closest`/`find`).
2. به Modal بالا، انیمیشن ورود از پایین (`slideDown` روی `.modal-box`) اضافه کنید.
3. Dropdown بالا را طوری تغییر دهید که فقط یکی از چند دراپ‌داون هم‌زمان باز بماند.
4. لیست Drag & Drop را طوری تغییر دهید که ترتیب نهایی آیتم‌ها را در کنسول چاپ کند (با `.each()` روی `data-id` ها).

فایل `code/index.html` این جلسه، هر پنج کامپوننت (Tabs, Modal, Tooltip, Dropdown, Drag & Drop) را به‌صورت کامل و زنده پیاده‌سازی کرده است.
