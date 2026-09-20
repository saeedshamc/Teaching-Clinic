# جلسه 74: Tables و Pagination در Bootstrap (2 ساعت)

## اهداف یادگیری
- استایل جدول با کلاس‌های `table-*`
- جدول ریسپانسیو با `table-responsive`
- صفحه‌بندی ظاهری با `pagination`
- ترکیب جدول ادمین با badge و دکمه

---

## 1. جدول پایه

```html
<table class="table">
  <thead>
    <tr><th>#</th><th>نام</th><th>نقش</th></tr>
  </thead>
  <tbody>
    <tr><td>1</td><td>سارا</td><td>ادمین</td></tr>
  </tbody>
</table>
```

کلاس‌های پرکاربرد:

| کلاس | اثر |
|------|-----|
| `table-striped` | ردیف‌های یک‌درمیان |
| `table-hover` | هایلایت هنگام هاور |
| `table-bordered` | حاشیه سلول‌ها |
| `table-sm` | فشرده‌تر |
| `table-dark` | پس‌زمینه تیره روی thead یا کل جدول |

رنگ ردیف: `table-success`، `table-warning`، ...

---

## 2. ریسپانسیو

روی موبایل جدول عریض اسکرول افقی می‌گیرد:

```html
<div class="table-responsive">
  <table class="table">...</table>
</div>
```

`table-responsive-md` فقط زیر breakpoint مشخص اسکرول می‌دهد.

---

## 3. Pagination

```html
<nav aria-label="صفحه‌بندی نتایج">
  <ul class="pagination">
    <li class="page-item disabled"><a class="page-link" href="#">قبلی</a></li>
    <li class="page-item active" aria-current="page"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">بعدی</a></li>
  </ul>
</nav>
```

ظاهر: `pagination-sm` / `pagination-lg` و تراز با `justify-content-center`.

> این جلسه صفحه‌بندی **ظاهر** است؛ منطق صفحه‌بندی واقعی را بعداً با بک‌اند یا JS می‌سازید.

---

## اشتباهات رایج
- فراموش کردن `<thead>` و `<th>` (معنا و دسترس‌پذیری)
- جدول عریض بدون `table-responsive`
- لینک‌های pagination بدون `aria-label` روی `<nav>`
- مخلوط کردن utilityهای Tailwind وسط کلاس‌های table

---

## تمرین‌ها
### آسان
ستون «ایمیل» به جدول اضافه کنید.
### متوسط
یک ردیف را با `table-warning` هایلایت کنید.
### چالش
جدول را داخل `card` بگذارید و بالای آن عنوان + دکمه «افزودن» بسازید.

## خلاصه
جدول + pagination اسکلت داشبوردهای ادمین است — در جلسه ۷۷ دوباره می‌بینیدشان.

## گام بعد
[جلسه 75: Icons و RTL](../session-75/index.html)
