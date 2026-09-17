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
