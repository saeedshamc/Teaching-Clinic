# جلسه ۲: ساختار سند HTML (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- هر بخش سند HTML5 (`DOCTYPE`, `html`, `head`, `body`) را توضیح دهید
- تگ‌های `meta` ضروری (`charset`, `viewport`) را بنویسید
- تفاوت محتوای `head` (متا) و `body` (قابل مشاهده) را بدانید
- تگ‌ها را به‌درستی **تو در تو (nest)** و **ببندید**
- attributeهای `lang` و `dir` را برای فارسی تنظیم کنید
- سند HTML معتبر با ساختار استاندارد بسازید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | بازخوانی جلسه ۱، نمای کلی سند HTML |
| 10–25 | `<!DOCTYPE html>` و ریشه `<html>` |
| 25–45 | بخش `<head>`: title، meta، charset |
| 45–60 | بخش `<body>` و nesting صحیح |
| 60–75 | مثال‌های عملی و تمرین |
| 75–85 | تمرین کلاسی: سند کامل |
| 85–90 | اشتباهات، تکلیف، Q&A |

---

## ۱. نمای کلی سند HTML5

هر صفحه HTML استاندارد این ساختار را دارد:

```html
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <!-- اطلاعات متا — در صفحه دیده نمی‌شود -->
</head>
<body>
    <!-- محتوای قابل مشاهده -->
</body>
</html>
```

**خط به خط:**
- `DOCTYPE` — به مرورگر می‌گوید HTML5 است
- `html` — ریشه درخت DOM
- `head` — متاداده
- `body` — آنچه کاربر می‌بیند

---

## ۲. `<!DOCTYPE html>`

```html
<!DOCTYPE html>
```

- **همیشه خط اول** فایل
- نسخه HTML5 — ساده و کوتاه
- بدون آن مرورگر ممکن است **Quirks Mode** برود و layout عجیب شود

### ۲.۱. Quirks Mode چیست؟

حالت سازگاری قدیمی IE — margin/padding پیش‌فرض متفاوت. با DOCTYPE صحیح **Standards Mode** فعال می‌شود.

---

## ۳. عنصر `<html>`

```html
<html lang="fa" dir="rtl">
```

| Attribute | مقدار | کاربرد |
|-----------|--------|--------|
| `lang` | `fa`, `en`, … | زبان صفحه — screen reader و SEO |
| `dir` | `rtl` / `ltr` | جهت متن — فارسی معمولاً rtl |

**نکته:** `lang` روی `<html>` کل صفحه را تعریف می‌کند؛ برای بخش انگلیسی بعداً `lang="en"` روی همان عنصر می‌گذاریم.

---

## ۴. بخش `<head>`

محتوای `head` در صفحه **رندر نمی‌شود** (به‌جز `<title>` در تب).

### ۴.۱. `<title>`

```html
<title>دوره HTML — جلسه ۲</title>
```

- عنوان تب مرورگر
- در نتایج جستجو مهم است
- **یکتا و توصیفی** بنویسید

### ۴.۲. `<meta charset="UTF-8">`

```html
<meta charset="UTF-8">
```

**ضروری** برای فارسی، emoji و اکثر زبان‌ها. ترجیحاً در ۱۰۲۴ بایت اول سند باشد.

### ۴.۳. `<meta name="viewport">`

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

برای **responsive** روی موبایل — از همین الان در همه صفحات بگذارید.

### ۴.۴. سایر meta (اختیاری این جلسه)

```html
<meta name="description" content="توضیح کوتاه صفحه">
<meta name="author" content="نام نویسنده">
```

---

## ۵. بخش `<body>`

همه عناصر visible — عنوان، پاراگراف، تصویر، فرم — داخل `body`:

```html
<body>
    <h1>عنوان اصلی</h1>
    <p>متن صفحه.</p>
</body>
```

فقط **یک** `<body>` در هر سند.

---

## ۶. Nesting — تو در تو بودن

تگ‌ها مثل جعبه‌های تو در تو بسته می‌شوند:

```html
<!-- ✅ درست -->
<p>این <strong>کلمه</strong> پررنگ است.</p>

<!-- ❌ اشتباه — ترتیب بستن -->
<p>این <strong>کلمه</p></strong>
```

**قانون:** آخرین تگ بازشده، **اول** بسته می‌شود (LIFO).

### ۶.۱. عناصر block vs inline (پیش‌نمای)

| نوع | مثال | رفتار کلی |
|-----|------|-----------|
| Block | `h1`, `p`, `div` | خط جدید |
| Inline | `strong`, `a`, `span` | در همان خط |

جلسه‌های بعد جزئی‌تر توضیح می‌دهند.

---

## ۷. سند کامل — الگو

```html
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="آموزش ساختار HTML">
    <title>ساختار سند HTML</title>
</head>
<body>
    <h1>ساختار صحیح HTML5</h1>
    <p>این صفحه تمام بخش‌های استاندارد را دارد.</p>
</body>
</html>
```

---

## ۸. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_structure.html](./examples/01_basic_structure.html) | ساختار پایه |
| [02_head_meta.html](./examples/02_head_meta.html) | metaهای head |
| [03_nesting_correct.html](./examples/03_nesting_correct.html) | nesting صحیح |
| [04_nesting_errors.html](./examples/04_nesting_errors.html) | مقایسه درست/غلط (کامنت) |

---

## ۹. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| دو `<head>` | سند نامعتبر | یک head |
| `<title>` داخل body | نامعتبر | title فقط در head |
| فراموش `</html>` | parse سخت | همه تگ‌ها بسته |
| `charset` بعد از body | ممکن است دیر اعمال شود | charset در head اول |
| `<HTML>` بزرگ | HTML case-insensitive ولی convention کوچک | `<html>` |

### ❌ head و body جابه‌جا

```html
<body>...</body>
<head>...</head>  <!-- اشتباه -->
```

### ❌ محتوای visible در head

```html
<head>
    <h1>عنوان</h1>  <!-- نامعتبر -->
</head>
```

---

## ۱۰. اعتبارسنجی HTML

- [Validator W3C](https://validator.w3.org/)
- VS Code: افزونه HTMLHint
- DevTools: خطای parse گاهی در Console

**تمرین:** یک فایل را validate کنید و خطاها را برطرف کنید.

---

## ۱۱. بهترین شیوه‌ها

1. **الگوی ثابت** — همان head را در همه صفحات کپی کنید
2. **indent** — ۲ یا ۴ space برای خوانایی
3. **بستن تگ‌ها** — حتی optionalها مثل `</li>` را بنویسید
4. **lang و dir** — برای فارسی فراموش نشود
5. **title معنادار** — نه «صفحه ۱»

---

## ۱۲. تمرین کلاسی (۳۰ دقیقه)

1. سند HTML5 کامل با h1 و دو p
2. `description` meta با جمله درباره خودتان
3. عمداً یک nesting غلط بنویسید — در DevTools ببینید مرورگر چه می‌کند
4. اصلاح و validate

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۳. خلاصه جلسه

- ✅ `DOCTYPE html` → HTML5 standards mode
- ✅ `head`: charset، viewport، title
- ✅ `body`: محتوای visible
- ✅ nesting صحیح و بستن تگ‌ها
- ✅ `lang="fa"` و `dir="rtl"`

**جلسه بعد:** عناصر متنی — `h1` تا `h6`، `p`، `br`، `hr`

---

**قبل:** [۱ — مقدمه](../session-01-intro/) | **بعد:** [۳ — عناصر متنی](../session-03/)
