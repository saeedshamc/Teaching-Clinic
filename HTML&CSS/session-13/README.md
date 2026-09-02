# جلسه ۱۳: معرفی CSS — Inline، Internal، External (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- توضیح دهید CSS چیست و چرا جدا از HTML نوشته می‌شود
- سه روش افزودن CSS به صفحه را بشناسید: **inline**، **internal**، **external**
- با تگ `<style>` و `<link>` کار کنید
- تفاوت `style` attribute و فایل `.css` جداگانه را بدانید
- تشخیص دهید کدام روش برای کدام سناریو مناسب‌تر است
- اصول اولیه سینتکس CSS (selector، property، value) را بخوانید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور HTML | ساختار پایه، تگ‌ها، attributeها |
| 10–25 | CSS چیست؟ | جداسازی محتوا و ظاهر، سینتکس پایه |
| 25–40 | Inline CSS | attribute `style`، مزایا و معایب |
| 40–55 | Internal CSS | تگ `<style>` در `<head>` |
| 55–70 | External CSS | فایل `.css` + `<link rel="stylesheet">` |
| 70–85 | تمرین کلاسی | استایل‌دهی کارت پروفایل |
| 85–90 | جمع‌بندی | بهترین روش + تکلیف |

---

## ۱. CSS چیست؟

**CSS** (Cascading Style Sheets) زبان استایل‌دهی صفحات وب است. HTML **ساختار** را مشخص می‌کند؛ CSS **ظاهر** را.

**تشبیه:** HTML مثل اسکلت ساختمان است؛ CSS رنگ دیوار، نوع کفپوش و چیدمان اتاق‌ها را تعیین می‌کند.

### سینتکس پایه

```css
selector {
    property: value;
    property: value;
}
```

مثال:
```css
h1 {
    color: navy;
    font-size: 2rem;
}
```

| بخش | نقش |
|-----|-----|
| `selector` | عنصر HTML هدف |
| `property` | ویژگی ظاهری (رنگ، اندازه، ...) |
| `value` | مقدار ویژگی |
| `;` | پایان هر اعلان — فراموش نکنید! |

---

## ۲. روش اول: Inline CSS

استایل مستقیماً روی تگ HTML با attribute `style`:

```html
<p style="color: red; font-size: 18px;">این پاراگراف قرمز است.</p>
```

### مزایا
- سریع برای تست یا یک عنصر خاص
- بدون فایل اضافه

### معایب
- تکرار زیاد — نگهداری سخت
- نمی‌توان pseudo-class یا media query نوشت
- مخلوط شدن محتوا و ظاهر (نقض separation of concerns)
- specificity بالا — override سخت

### چه زمانی استفاده کنیم؟
- ایمیل HTML (بسیاری از کلاینت‌ها external CSS را بلاک می‌کنند)
- تست سریع در DevTools
- **نه** برای پروژه واقعی

---

## ۳. روش دوم: Internal CSS

استایل داخل همان فایل HTML، معمولاً در `<head>`:

```html
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>صفحه با Internal CSS</title>
    <style>
        body {
            font-family: Tahoma, sans-serif;
            background-color: #f5f5f5;
        }
        h1 {
            color: #2c3e50;
        }
    </style>
</head>
<body>
    <h1>سلام دنیا</h1>
</body>
</html>
```

### مزایا
- یک فایل — انتقال آسان
- مناسب صفحات تک‌صفحه‌ای یا پروtotype

### معایب
- استایل بین صفحات share نمی‌شود
- فایل HTML بزرگ می‌شود
- cache مرورگر برای CSS جدا کار نمی‌کند

### چه زمانی استفاده کنیم؟
- صفحه تک‌صفحه‌ای (landing ساده)
- دمو و آموزش
- وقتی فقط یک HTML دارید

---

## ۴. روش سوم: External CSS (توصیه‌شده)

فایل جداگانه `.css` و لینک در HTML:

**styles.css:**
```css
body {
    margin: 0;
    font-family: "Vazirmatn", Tahoma, sans-serif;
}
```

**index.html:**
```html
<head>
    <link rel="stylesheet" href="styles.css">
</head>
```

### attributeهای مهم `<link>`

| attribute | مقدار | توضیح |
|-----------|-------|-------|
| `rel` | `stylesheet` | نوع رابطه — اجباری |
| `href` | مسیر فایل | نسبی یا مطلق |
| `type` | `text/css` | اختیاری در HTML5 |

### مزایا
- **یک فایل CSS برای چند صفحه** — DRY
- cache مرورگر — بارگذاری سریع‌تر صفحات بعدی
- جداسازی کامل محتوا و ظاهر
- کار تیمی راحت‌تر (طراح / توسعه‌دهنده)

### معایب
- یک درخواست HTTP اضافه (معمولاً ناچیز)
- مسیر فایل باید درست باشد

### چه زمانی استفاده کنیم؟
- **همیشه** در پروژه‌های واقعی
- سایت چندصفحه‌ای
- هر جایی که maintainability مهم است

---

## ۵. مقایسه سه روش

| معیار | Inline | Internal | External |
|-------|--------|----------|----------|
| محل استایل | روی تگ | `<style>` | فایل `.css` |
| قابلیت استفاده مجدد | ❌ | یک صفحه | ✅ چند صفحه |
| نگهداری | سخت | متوسط | آسان |
| Cache | — | — | ✅ |
| Specificity | خیلی بالا | عادی | عادی |
| توصیه پروژه | ❌ | محدود | ✅ |

---

## ۶. چند فایل CSS

می‌توانید چند `<link>` داشته باشید:

```html
<link rel="stylesheet" href="reset.css">
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="components.css">
```

**ترتیب مهم است** — فایل بعدی می‌تواند قبلی را override کند.

---

## ۷. `@import` (کمتر توصیه‌شده)

```css
/* داخل فایل CSS */
@import url("typography.css");
@import url("layout.css");
```

مشکل: درخواست‌های زنجیره‌ای — کندتر از چند `<link>` در HTML. ترجیحاً از `<link>` استفاده کنید.

---

## ۸. DevTools مرورگر

1. `F12` یا راست‌کلیک → Inspect
2. تب **Elements** — استایل‌های اعمال‌شده
3. تب **Sources** — فایل‌های CSS
4. می‌توانید موقت استایل تغییر دهید (ذخیره نمی‌شود مگر در Sources)

---

## ۹. اشتباهات رایج

| اشتباه | درست |
|--------|------|
| فراموش کردن `;` آخر property | همیشه `;` بگذارید |
| مسیر اشتباه `href` | مسیر نسبی را از محل HTML بررسی کنید |
| `rel="style"` | باید `rel="stylesheet"` باشد |
| استایل همه چیز inline | external برای پروژه |
| CSS در `<body>` بدون دلیل | `<style>` در `<head>` |

---

## ۱۰. بهترین روش‌ها (Best Practices)

1. **External CSS** به‌عنوان روش اصلی
2. یک پوشه `css/` برای فایل‌های استایل
3. نام فایل معنادار: `main.css`، `header.css`
4. از inline فقط برای استثناهای واقعی
5. `lang="fa"` و `dir="rtl"` برای فارسی
6. comment در CSS: `/* توضیح */`

---

## ۱۱. تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_inline_styles.html` — استایل inline
- `02_internal_styles.html` — تگ `<style>`
- `03_external_styles.html` + `03_styles.css` — لینک خارجی
- `04_methods_comparison.html` — مقایسه سه روش

تمرین‌ها: `exercises/question.md`

---

## ۱۲. جمع‌بندی

- CSS ظاهر صفحه را کنترل می‌کند
- سه روش: inline، internal، external
- **external** روش استاندارد حرفه‌ای
- سینتکس: `selector { property: value; }`
- `<link rel="stylesheet" href="...">` برای فایل خارجی

---

## 🔜 جلسه بعد

**جلسه ۱۴: سلکتورهای CSS** — element، class، id، attribute
