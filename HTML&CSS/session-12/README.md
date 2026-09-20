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

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 12 — متادیتا و SEO پایه | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 11: HTML معنایی
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «متادیتا و SEO پایه» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **متادیتا و SEO پایه** یک جزیرهٔ جدا نیست؛ مستقیماً روی ساختار و معنای سند در DOM اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **متادیتا و SEO پایه** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_meta_tags.html`](./examples/01_meta_tags.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «متادیتا و SEO پایه» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «متادیتا و SEO پایه» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

#### تمرین چالشی

مثال را با محتوای فارسی واقعی گسترش دهید؛ نسخه باید در عرض ۳۲۰ تا ۱۲۸۰ پیکسل، با Tab، و بدون خطای مسیر فایل کار کند. یک محدودیت عمدی ایجاد کنید، آن را تشخیص دهید و علت/اصلاح را بنویسید.

### راهنمای حل

- ابتدا معیار پذیرش را بنویسید: محتوای کامل، ساختار معتبر، نبود overflow و focus قابل‌مشاهده.
- نسخهٔ آسان را با کوچک‌ترین کد ممکن بسازید و با نمونه مقایسه کنید.
- در تمرین چالشی از Responsive Mode و پنل Computed استفاده کنید؛ فقط پس از یافتن علت، کد را تغییر دهید.
- پاسخ یکتا نیست؛ راه‌حل خوب باید **قابل توضیح، قابل تست و قابل نگهداری** باشد.

### خودآزمایی

1. موضوع اصلی این جلسه در کدام مرحله از کار مرورگر یا تجربهٔ کاربر اثر می‌گذارد؟
2. چگونه بدون حدس‌زدن می‌فهمید کد شما واقعاً اعمال شده است؟
3. یک تصمیم ضعیف در این موضوع چه اثری بر موبایل یا accessibility دارد؟
4. چه زمانی راه‌حل ساده‌تر از abstraction پیچیده بهتر است؟

<details>
<summary>راهنمای پاسخ</summary>

پاسخ باید به‌ترتیب به مدل DOM/CSSOM و render، مشاهده در DevTools و آزمون واقعی، اثر بر ترتیب/خوانایی/focus، و اصل «کمترین پیچیدگی لازم» اشاره کند. پاسخ را با یک مثال از فایل همین جلسه کامل کنید.
</details>

### جمع‌بندی و پل جلسهٔ بعد

اکنون باید بتوانید **متادیتا و SEO پایه** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 13: آشنایی با CSS** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

