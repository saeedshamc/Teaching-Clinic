# جلسه ۱۲: Metadata، Favicon و SEO پایه (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- meta tagهای مهم (`description`, `robots`, `viewport`) را بنویسید
- favicon با `<link rel="icon">` اضافه کنید
- Open Graph برای اشتراک‌گذاری شبکه اجتماعی تنظیم کنید
- اصول SEO پایه HTML را اعمال کنید
- `canonical` و `title` بهینه بنویسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | metadata چیست؟ |
| 10–25 | meta description, author, robots |
| 25–40 | favicon و apple-touch-icon |
| 40–55 | Open Graph |
| 55–70 | SEO checklist |
| 70–85 | تمرین head کامل |
| 85–90 | جمع‌بندی دوره HTML |

---

## ۱. metadata

داده **درباره** سند — در head — بیشتر برای ماشین (browser، crawler):

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="...">
    <title>...</title>
</head>
```

---

## ۲. meta description

```html
<meta name="description" content="دوره HTML فارسی — ۱۲ جلسه از مقدمه تا SEO">
```

- ~۱۵۰–۱۶۰ کاراکتر
- خلاصه جذاب — در SERP نمایش داده می‌شود
- **یکتا** برای هر صفحه

---

## ۳. robots

```html
<meta name="robots" content="index, follow">
<meta name="robots" content="noindex, nofollow">
```

| مقدار | معنی |
|-------|------|
| index | در نتایج جستجو |
| noindex | پنهان از جستجو |
| follow | دنبال کردن لینک‌ها |
| nofollow | لینک‌ها follow نشوند |

---

## ۴. title

```html
<title>آموزش HTML — جلسه ۱۲ Metadata | Teaching Clinic</title>
```

- ۵۰–۶۰ کاراکتر — مهم‌ترین SEO on-page
- برند + موضوع صفحه

---

## ۵. favicon

```html
<link rel="icon" href="/favicon.ico" sizes="any">
<link rel="icon" href="/favicon.svg" type="image/svg+xml">
<link rel="apple-touch-icon" href="/apple-touch-icon.png">
```

SVG مقیاس‌پذیر — ICO برای سازگاری قدیمی.

---

## ۶. Open Graph

```html
<meta property="og:title" content="عنوان">
<meta property="og:description" content="توضیح">
<meta property="og:type" content="website">
<meta property="og:url" content="https://example.com/page">
<meta property="og:image" content="https://example.com/og-image.jpg">
<meta property="og:locale" content="fa_IR">
```

برای preview در تلگرام، تwitter، linkedin.

---

## ۷. Twitter Card (اختیاری)

```html
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:title" content="...">
```

---

## ۸. canonical

```html
<link rel="canonical" href="https://example.com/page">
```

URL ترجیحی — جلوگیری از duplicate content.

---

## ۹. SEO پایه HTML — چک‌لیست

1. **title** یکتا
2. **meta description**
3. **h1** یکی — موضوع صفحه
4. **سلسله heading** منطقی
5. **alt** تصاویر
6. **lang** روی html
7. **semantic** tags
8. **لینک** متن معنادار
9. **سرعت** — تصاویر بهینه
10. **mobile** — viewport

---

## ۱۰. theme-color

```html
<meta name="theme-color" content="#2563eb">
```

رنگ UI مرورگر موبایل.

---

## ۱۱. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_meta_tags.html](./examples/01_meta_tags.html) | meta |
| [02_favicon.html](./examples/02_favicon.html) | favicon |
| [03_open_graph.html](./examples/03_open_graph.html) | OG |
| [04_seo_basics.html](./examples/04_seo_basics.html) | چک‌لیست |

---

## ۱۲. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| description تکراری همه صفحات | یکتا |
| title فقط «صفحه اصلی» | توصیفی |
| keyword stuffing در meta | حذف |
| بدون viewport | همیشه اضافه |
| og:image کوچک | حداقل 1200×630 |

---

## ۱۳. hreflang (چندزبانه)

```html
<link rel="alternate" hreflang="fa" href="https://example.com/fa/">
<link rel="alternate" hreflang="en" href="https://example.com/en/">
```

---

## ۱۴. structured data (آینده)

JSON-LD برای rich results — خارج از scope این دوره؛ بعداً.

---

## ۱۵. تمرین کلاسی

1. head کامل با description, robots, favicon
2. og:title, og:description
3. title بهینه
4. چک‌لیست SEO روی صفحه خود

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. جمع‌بندی دوره HTML (جلسه ۱–۱۲)

| جلسه | موضوع |
|------|--------|
| ۱ | وب، VS Code، پوشه |
| ۲ | ساختار سند |
| ۳ | متن |
| ۴ | قالب‌بندی |
| ۵ | لیست |
| ۶ | لینک |
| ۷ | تصویر |
| ۸ | جدول |
| ۹–۱۰ | فرم |
| ۱۱ | semantic |
| ۱۲ | metadata/SEO |

**بعد:** CSS از جلسه ۱۳!

---

## ۱۷. خلاصه

- ✅ meta description, robots, viewport
- ✅ favicon link
- ✅ Open Graph
- ✅ SEO on-page پایه

---

**قبل:** [۱۱ — semantic](../session-11/) | **بعد:** CSS جلسه ۱۳
