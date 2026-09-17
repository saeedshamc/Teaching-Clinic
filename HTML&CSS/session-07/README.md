# جلسه ۷: تصاویر — img، alt و responsive (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تصویر با `<img src="" alt="">` درج کنید
- متن جایگزین **alt** را برای a11y و SEO بنویسید
- `width`, `height`, `loading` را به‌کار ببرید
- از `<figure>` و `<figcaption>` استفاده کنید
- مسیر نسبی تصاویر در پوشه `images/` را مدیریت کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | نقش تصویر در وب |
| 10–30 | img و attributeهای پایه |
| 30–50 | alt — معنادار vs تزئینی |
| 50–65 | width/height و loading |
| 65–80 | figure/figcaption |
| 80–90 | تمرین، تکلیف |

---

## ۱. عنصر `<img>`

```html
<img src="images/photo.jpg" alt="توضیح تصویر" width="600" height="400">
```

- **void element** — بدون تگ بسته
- `src` — مسیر فایل یا URL
- `alt` — **الزامی** برای دسترس‌پذیری (حتی اگر خالی)

---

## ۲. alt — متن جایگزین

| نوع تصویر | alt |
|-----------|-----|
| معنادار (اطلاعات) | توضیح کوتاه دقیق |
| لینک تصویری | عمل مقصد |
| تزئینی | `alt=""` |
| متن داخل تصویر | همان متن در alt |

```html
<img src="chart.png" alt="نمودار فروش: فروردین ۱۰، اردیبهشت ۱۵ واحد">
<img src="divider.svg" alt="">
```

**❌** `alt="تصویر"` یا `alt="photo.jpg"` — بی‌فایده

---

## ۳. مسیر تصویر

```html
<!-- نسبی -->
<img src="images/logo.svg" alt="لوگو">

<!-- مطلق -->
<img src="https://example.com/banner.jpg" alt="بنر">
```

پوشه `images/` یا `assets/images/` در پروژه استاندارد است.

---

## ۴. width و height

```html
<img src="hero.jpg" alt="..." width="1200" height="600">
```

- فضای قبل از load رزرو می‌شود — **CLS** کمتر
- CSS بعداً `max-width: 100%; height: auto;` برای responsive

---

## ۵. loading="lazy"

```html
<img src="gallery-3.jpg" alt="..." loading="lazy">
```

تصاویر پایین صفحه دیرتر load می‌شوند — performance بهتر.

---

## ۶. figure و figcaption

```html
<figure>
    <img src="diagram.png" alt="نمودار لایه‌های وب">
    <figcaption>شکل ۲ — HTML، CSS، JS</figcaption>
</figure>
```

برای تصاویر قابل ارجاع در متن.

---

## ۷. فرمت‌های رایج

| فرمت | کاربرد |
|------|--------|
| JPEG | عکس |
| PNG | شفافیت |
| SVG | آیکون، لوگو — مقیاس‌پذیر |
| WebP | فشرده مدرن |

---

## ۸. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_img.html](./examples/01_basic_img.html) | img پایه |
| [02_alt_text.html](./examples/02_alt_text.html) | alt |
| [03_responsive_img.html](./examples/03_responsive_img.html) | width/loading |
| [04_figure_figcaption.html](./examples/04_figure_figcaption.html) | figure |

---

## ۹. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| بدون alt | همیشه alt |
| alt طولانی ۵ خط | کوتاه؛ جزئیات در caption |
| img برای background | CSS background-image |
| src غلط | مسیر relative را تست کنید |
| کشیدن تصویر با width فقط | aspect ratio حفظ شود |

---

## ۱۰. picture و srcset (آینده)

در CSS جلسات بعد `srcset` برای چند resolution — الان width/height کافی است.

```html
<!-- پیش‌نمای آینده -->
<img
    src="photo.jpg"
    srcset="photo-400.jpg 400w, photo-800.jpg 800w"
    sizes="(max-width: 600px) 100vw, 800px"
    alt="..."
>
```

---

## ۱۱. decorative vs informative

| نوع | تست | alt |
|-----|-----|-----|
| informative | حذف تصویر = اطلاعات کم می‌شود | توضیح |
| decorative | حذف = تغییری در معنا نیست | `""` |
| functional | دکمه/لینک تصویری | عمل |

---

## ۱۲. linked image

```html
<a href="product.html">
    <img src="thumb.jpg" alt="محصول A — قیمت ۵۰۰ هزار">
</a>
```

alt باید **مقصد** را بگوید نه «تصویر».

---

## ۱۳. performance

- فشرده‌سازی JPEG/WebP
- ابعاد واقعی — نه 4000px برای thumbnail
- lazy برای below fold
- SVG برای آیکون

---

## ۱۴. broken image

اگر src اشتباه باشد، آیکون شکسته + alt نمایش داده می‌شود — alt مهم‌تر می‌شود.

---

## ۱۵. بهترین شیوه‌ها

1. **فشرده‌سازی** تصاویر قبل از upload
2. **نام فایل معنادار** — `team-photo.jpg`
3. **SVG** برای لوگو
4. **alt معنادار**
5. **figure** برای caption

---

## ۱۶. تمرین کلاسی

1. img با alt از images/
2. figure با figcaption
3. loading lazy برای ۲ تصویر
4. یک alt="" تزئینی

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۷. خلاصه

- ✅ img src + alt
- ✅ alt معنادار یا خالی برای decorative
- ✅ width/height بر ضد layout shift
- ✅ figure/figcaption

**بعد:** [۸ — جداول](../session-08/)

---

**قبل:** [۶ — لینک‌ها](../session-06/) | **بعد:** [۸ — جداول](../session-08/)
