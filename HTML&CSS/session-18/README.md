# جلسه ۱۸: تایپوگرافی — font-family، size، line-height (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **font-family** با fallback stack بنویسید
- **font-size**، **font-weight**، **font-style** را کنترل کنید
- **line-height** و **letter-spacing** برای خوانایی تنظیم کنید
- **text-align**، **text-decoration**، **text-transform** بکار ببرید
- فونت وب (Google Fonts / Vazirmatn) لینک کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور جلسه قبل | پرسش و پاسخ |
| 10–25 | مفاهیم پایه | ارائه + demo |
| 25–45 | مثال‌های عملی | live coding |
| 45–60 | تمرین راهنما | کار گروهی |
| 60–80 | تمرین مستقل | VS Code |
| 80–90 | جمع‌بندی | تکلیف |

---

## 1. font-family

```css
body {
    font-family: "Vazirmatn", Tahoma, "Segoe UI", sans-serif;
}
/* fallback: اگر Vazirmatn نبود → Tahoma → sans-serif */
```

---

## 2. font-size و weight

```css
h1 { font-size: 2.25rem; font-weight: 700; }
p { font-size: 1rem; font-weight: 400; }
.lead { font-size: 1.125rem; }
small { font-size: 0.875rem; }
```

---

## 3. line-height

```css
p { line-height: 1.7; }  /* بدون واحد — ضریب font-size */
h1 { line-height: 1.2; }
```
1.5–1.8 برای پاراگراف فارسی مناسب.

---

## 4. letter-spacing و word-spacing

```css
.uppercase { letter-spacing: 0.05em; text-transform: uppercase; }
```

---

## 5. text-align در RTL

```css
body { text-align: right; } /* dir=rtl */
.center { text-align: center; }
.justify { text-align: justify; }
```

---

## 6. Google Fonts

```html
<link href="https://fonts.googleapis.com/css2?family=Vazirmatn:wght@400;700&display=swap" rel="stylesheet">
```

---

## 7. vertical rhythm

margin-bottom بین heading و paragraph — scale یکسان rem.

---

## 8. خوانایی

contrast، line-length 45–75 character، font-size حداقل 16px body.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| line-height: 14px ثابت | line-height بدون واحد |
| یک font-family بدون fallback | stack کامل |
| font-size px خیلی کوچک | حداقل 1rem body |
| justify بدون hyphenation | justify محتاطانه |

---

## بهترین روش‌ها

- Vazirmatn/Tahoma برای فارسی
- rem scale typography
- line-height 1.6+
- حداکثر عرض متن

---



## ۹. font-family — پشته فونت (Font Stack)

```css
body {
    font-family: "Vazirmatn", "Tahoma", "Segoe UI", sans-serif;
}
code {
    font-family: "Cascadia Code", "Fira Code", monospace;
}
```

| دسته | مثال fallback |
|------|---------------|
| sans-serif | Tahoma, Arial, system-ui |
| serif | Georgia, "Times New Roman" |
| monospace | Consolas, monospace |

**نکته:** فونت فارسی را اول بگذارید؛ اگر لود نشد fallback لاتین فعال می‌شود.

---

## ۱۰. @font-face و فونت وب

```css
@font-face {
    font-family: "Vazirmatn";
    src: url("fonts/Vazirmatn-Regular.woff2") format("woff2");
    font-weight: 400;
    font-display: swap;
}
```

| `font-display` | رفتار |
|----------------|--------|
| `swap` | متن فوراً با fallback — بعد فونت اصلی |
| `block` | انتظار کوتاه — ممکن است FOIT |
| `optional` | اگر دیر لود شد، fallback می‌ماند |

**بهترین روش:** `woff2` + `font-display: swap` + preload در `<head>`.

---

## ۱۱. font-size — scale تایپوگرافیک

```css
html { font-size: 100%; }        /* 16px پیش‌فرض */
h1 { font-size: 2.5rem; }        /* 40px */
h2 { font-size: 2rem; }          /* 32px */
h3 { font-size: 1.5rem; }        /* 24px */
p  { font-size: 1rem; }          /* 16px */
small { font-size: 0.875rem; }   /* 14px */
```

### Type Scale (نسبت 1.25 — Major Third)
| سطح | rem | px (base 16) |
|-----|-----|--------------|
| h1 | 2.5 | 40 |
| h2 | 2 | 32 |
| h3 | 1.5 | 24 |
| body | 1 | 16 |
| caption | 0.875 | 14 |

---

## ۱۲. line-height و فاصله عمودی

```css
p {
    line-height: 1.6;    /* بدون واحد — ضریب font-size */
    margin-bottom: 1em;
}
```

| محتوا | line-height پیشنهادی |
|-------|---------------------|
| پاراگراف body | 1.5 – 1.7 |
| عناوین | 1.2 – 1.3 |
| دکمه/label | 1.4 |

**بدون واحد بهتر از px:** با تغییر font-size، line-height متناسب scale می‌شود.

---

## ۱۳. font-weight و font-style

```css
.light  { font-weight: 300; }
.normal { font-weight: 400; }
.bold   { font-weight: 700; }
.italic { font-style: italic; }
```

مقادیر عددی: 100–900 (متغیر فونت: `font-weight: 550`).

---

## ۱۴. text-align، direction و RTL

```css
html { direction: rtl; }
.quote { text-align: justify; }
.ltr-code { direction: ltr; unicode-bidi: embed; }
```

در RTL: `text-align: right` پیش‌فرض منطقی است؛ برای کد انگلیسی `direction: ltr`.

---

## ۱۵. letter-spacing و text-transform

```css
.heading {
    letter-spacing: 0.05em;
    text-transform: uppercase; /* فقط برای لاتین معنادار */
}
```

---

## ۱۶. text-decoration و خوانایی

```css
a { text-decoration: none; }
a:hover { text-decoration: underline; text-underline-offset: 3px; }
del { text-decoration: line-through; color: #888; }
```

---

## ۱۷. اشتباهات رایج تایپوگرافی

| اشتباه | درست |
|--------|------|
| line-height: 16px روی font 14px | بدون واحد: 1.5 |
| بیش از ۳ فونت مختلف | حداکثر ۲ خانواده |
| font-size px در همه جا | rem برای scale |
| متن خیلی روشن روی سفید | contrast ≥ 4.5:1 |

---

## ۱۸. تمرین کلاسی

صفحه مقاله با: عنوان h1، lead paragraph، blockquote، و code block با فونت monospace جدا.

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_font_family.html`
- `02_font_sizes.html`
- `03_line_height.html`
- `04_web_fonts.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 18 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۱۹: Specificity و Cascade**

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 18 — تایپوگرافی | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 17: رنگ و پس‌زمینه
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «تایپوگرافی» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **تایپوگرافی** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **تایپوگرافی** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_font_family.html`](./examples/01_font_family.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «تایپوگرافی» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «تایپوگرافی» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **تایپوگرافی** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 19: Cascade، Specificity و Inheritance** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

