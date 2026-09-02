# جلسه ۱۱: HTML5 معنایی — layout semantic (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت `<div>` generic و عناصر semantic را بدانید
- `header`, `nav`, `main`, `footer` را در layout به کار ببرید
- `section`, `article`, `aside` را درست تفکیک کنید
- **یک** `main` در هر صفحه داشته باشید
- `nav` با `aria-label` برای چند منو
- outline معنایی برای SEO و screen reader بسازید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | semantic چرا؟ |
| 10–25 | header, footer |
| 25–40 | nav و aria-label |
| 40–55 | main — یکتا |
| 55–70 | section, article, aside |
| 70–85 | تمرین layout |
| 85–90 | تکلیف |

---

## ۱. HTML معنایی چیست؟

تگی که **نقش** عنصر را توضیح می‌دهد — نه فقط جعبه:

```html
<!-- کمتر معنایی -->
<div id="header">...</div>

<!-- معنایی -->
<header>...</header>
```

فایده: SEO، دسترس‌پذیری، نگهداری کد.

---

## ۲. `<header>`

```html
<header>
    <h1>عنوان سایت</h1>
    <p>شعار</p>
</header>
```

- معرفی **صفحه** یا **بخش**
- می‌تواند داخل article/section هم باشد
- logo، nav، search — معمولاً در header سایت

---

## ۳. `<nav>`

```html
<nav aria-label="منوی اصلی">
    <ul>
        <li><a href="/">خانه</a></li>
        <li><a href="/blog">وبلاگ</a></li>
    </ul>
</nav>
```

**فقط** بلوک‌های ناوبری اصلی — نه every ul.

چند nav در یک صفحه:

```html
<nav aria-label="اصلی">...</nav>
<nav aria-label="پاورقی">...</nav>
```

---

## ۴. `<main>`

```html
<main>
    <h1>موضوع صفحه</h1>
    <p>محتوای اصلی...</p>
</main>
```

- **یک main** در هر صفحه (بدون nested main)
- محتوای منحصربه‌فرد صفحه — نه header/footer تکراری
- skip link «رفتن به محتوا» به main می‌رود

---

## ۵. `<section>`

```html
<section>
    <h2>خدمات ما</h2>
    <p>...</p>
</section>
```

بخش موضوعی — **معمولاً با heading**.

section vs div: section معنا دارد؛ div خنثی.

---

## ۶. `<article>`

```html
<article>
    <h2>عنوان پست</h2>
    <p>متن مستقل...</p>
    <footer>نویسنده</footer>
</article>
```

محتوای **مستقل** — پست وبلاگ، خبر، کامنت.

article می‌تواند داخل section باشد.

---

## ۷. `<aside>`

```html
<aside>
    <h2>مطالب مرتبط</h2>
    <ul>...</ul>
</aside>
```

محتوای **جانبی** — tangential — sidebar، تبلیغ، فهرست مرتبط.

---

## ۸. `<footer>`

```html
<footer>
    <p>&copy; ۱۴۰۴</p>
    <nav aria-label="قانونی">...</nav>
</footer>
```

اطلاعات پایانی صفحه یا article — copyright، لینک، تماس.

---

## ۹. outline نمونه — وبلاگ

```html
<body>
    <header>...</header>
    <nav>...</nav>
    <main>
        <article>...</article>
        <article>...</article>
    </main>
    <aside>...</aside>
    <footer>...</footer>
</body>
```

---

## ۱۰. time

```html
<time datetime="2026-09-02">۲ شهریور ۱۴۰۵</time>
```

تاریخ machine-readable.

---

## ۱۱. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_semantic_layout.html](./examples/01_semantic_layout.html) | layout کامل |
| [02_header_nav_footer.html](./examples/02_header_nav_footer.html) | header/nav/footer |
| [03_main_section_article.html](./examples/03_main_section_article.html) | main/section/article |
| [04_aside_layout.html](./examples/04_aside_layout.html) | aside |

---

## ۱۲. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| چند main | یک main |
| nav برای همه ul | فقط navigation blocks |
| section بدون heading | h2+ اضافه کنید |
| div برای همه | semantic جایگزین |
| aside برای main content | aside فقط جانبی |

---

## ۱۳. div هنوز لازم است

semantic جایگزین **همه** divها نیست — wrapper خنثی CSS:

```html
<div class="card">
    <article>...</article>
</div>
```

---

## ۱۴. SEO و semantic

Google ساختار heading و main/article را می‌فهمد — keyword stuffing نکنید؛ **ساختار درست** کافی است.

---

## ۱۵. Landmark roles

header → banner (implicit)
nav → navigation
main → main
aside → complementary
footer → contentinfo

---

## ۱۶. تمرین کلاسی

1. صفحه وبلاگ با همه landmarkها
2. دو nav با aria-label
3. article با time
4. aside مرتبط

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۷. خلاصه

- ✅ header, nav, main, footer
- ✅ section, article, aside
- ✅ یک main، nav معنادار
- ✅ heading در section

**بعد:** [۱۲ — metadata و SEO](../session-12/)

---

**قبل:** [۱۰ — فرم ۲](../session-10/) | **بعد:** [۱۲ — metadata](../session-12/)
