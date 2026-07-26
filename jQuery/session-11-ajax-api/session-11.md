# جلسه ۱۱ — Ajax و کار با API واقعی

## ۱. Ajax چیست؟

Ajax به شما اجازه می‌دهد بدون رفرش کردن صفحه، با سرور ارتباط برقرار کنید. این یکی از مهم‌ترین کاربردهای jQuery در پروژه‌های واقعی است.

## ۲. متدهای Ajax در jQuery

```javascript
$.ajax({
  url: 'https://jsonplaceholder.typicode.com/posts/1',
  method: 'GET',
  dataType: 'json'
});
```

همچنین:

```javascript
$.get('/api/data');
$.post('/api/save', { name: 'علی' });
```

## ۳. مدیریت موفقیت و خطا

```javascript
$.ajax({
  url: '...',
  success: function (data) {
    console.log(data);
  },
  error: function () {
    console.error('درخواست ناموفق بود');
  }
});
```

در نسخه‌های جدیدتر بهتر است از `done`, `fail`, `always` یا `then` استفاده کنید.

## ۴. Promise و Deferred

jQuery به شما اجازه می‌دهد با `Deferred` کنترل بیشتری بر روی جریان درخواست داشته باشید.

```javascript
var dfd = $.Deferred();
dfd.done(function (data) { console.log(data); });
dfd.resolve({ ok: true });
```

## ۵. نکات مهم درباره CORS

در کار با API‌های واقعی، ممکن است خطای CORS رخ دهد. این موضوع به‌خاطر سیاست‌های مرورگر است و معمولاً با تنظیمات سرور یا API مناسب حل می‌شود.

## ۶. تمرین‌های پیشنهادی

1. داده‌های یک API را در صفحه نمایش دهید.
2. یک فرم بسازید و با `$.post()` داده ارسال کنید.
3. یک پیام خطا در صورت شکست درخواست نمایش دهید.

## ۷. جمع‌بندی

Ajax به شما این امکان را می‌دهد که برنامه‌ی شما زنده، تعاملی و بر پایه‌ی داده باشد؛ بدون اینکه کاربر مجبور به رفرش صفحه شود.
