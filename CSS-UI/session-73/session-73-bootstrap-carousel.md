# جلسه 73: Carousel در Bootstrap (2 ساعت)

## اهداف یادگیری
- ساخت اسلایدر با `carousel`
- کنترل prev/next و indicators
- تنظیم `data-bs-ride` و فاصله زمانی
- درک دسترس‌پذیری پایه برای اسلایدر

---

## 1. ساختار پایه

```html
<div id="heroCarousel" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#heroCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="اسلاید ۱"></button>
    <button type="button" data-bs-target="#heroCarousel" data-bs-slide-to="1" aria-label="اسلاید ۲"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="..." class="d-block w-100" alt="توضیح اسلاید ۱">
    </div>
    <div class="carousel-item">
      <img src="..." class="d-block w-100" alt="توضیح اسلاید ۲">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#heroCarousel" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">قبلی</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#heroCarousel" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">بعدی</span>
  </button>
</div>
```

نکته‌ها:
- فقط یک `carousel-item` باید `active` باشد
- `data-bs-target` همه کنترل‌ها باید به همان `id` اسلایدر اشاره کند
- برای تصویر حتماً `alt` معنادار بگذارید

---

## 2. گزینه‌های رایج

| ویژگی | معنا |
|--------|------|
| `data-bs-ride="carousel"` | شروع خودکار بعد از لود |
| `data-bs-interval="4000"` | فاصله ۴ ثانیه روی هر آیتم |
| `carousel-fade` | به‌جای اسلاید، محو شدن |
| `data-bs-wrap="false"` | توقف در ابتدا/انتها |

روی خود `carousel-item` می‌توانید `data-bs-interval` جدا بگذارید.

---

## 3. Caption

متن روی اسلاید:

```html
<div class="carousel-caption d-none d-md-block">
  <h5>عنوان</h5>
  <p>توضیح کوتاه</p>
</div>
```

`d-none d-md-block` روی موبایل caption را مخفی می‌کند تا شلوغ نشود.

---

## اشتباهات رایج
- `id` اسلایدر با `data-bs-target` یکی نیست
- چند آیتم `active` همزمان
- تصویر بدون عرض کامل (`d-block w-100`)
- اتکای کامل به اسلایدر برای محتوای حیاتی (SEO و دسترس‌پذیری ضعیف‌تر)

---

## تمرین‌ها
### آسان
یک اسلاید سوم اضافه کنید و indicator مربوط را بسازید.
### متوسط
با `carousel-fade` انیمیشن را عوض کنید.
### چالش
با JS نمونه: `new bootstrap.Carousel(el, { interval: 2000 })` بسازید.

## خلاصه
Carousel برای بنر و گالری مفید است؛ برای محتوای متنی مهم، جایگزین ساده‌تر را هم در نظر بگیرید.

## گام بعد
[جلسه 74: Tables و Pagination](../session-74/index.html)
