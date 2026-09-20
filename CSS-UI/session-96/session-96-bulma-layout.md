# جلسه 96: Layout در Bulma — columns، container، level، hero (2 ساعت)

## اهداف یادگیری
- `container` و `section`
- سیستم `columns` / `column`
- `level` برای نوار افقی
- `hero` برای بنر تمام‌عرض

---

## 1. Container و Section

```html
<section class="section">
  <div class="container">...</div>
</section>
```

`section` پدینگ عمودی می‌دهد؛ `container` عرض را محدود می‌کند.

---

## 2. Columns

```html
<div class="columns">
  <div class="column">۱</div>
  <div class="column is-one-third">۲</div>
</div>
```

روی موبایل ستون‌ها معمولاً زیر هم می‌آیند مگر با `is-mobile` رفتار عوض شود. اندازه‌ها: `is-half`، `is-one-quarter`، `is-8`، ...

---

## 3. Level

برای هدرهای افقی (لوگو + اکشن):

```html
<nav class="level">
  <div class="level-left">...</div>
  <div class="level-right">...</div>
</nav>
```

---

## 4. Hero

```html
<section class="hero is-primary">
  <div class="hero-body">
    <p class="title">عنوان</p>
    <p class="subtitle">زیرعنوان</p>
  </div>
</section>
```

اندازه: `is-small` / `is-medium` / `is-large` / `is-fullheight`.

---

## تمرین‌ها
### آسان
سه ستون مساوی.
### متوسط
Hero با دکمه داخل `hero-body`.
### چالش
Level با دو دکمه در `level-right`.

## گام بعد
[جلسه 97: عناصر Bulma](../session-97/index.html)
