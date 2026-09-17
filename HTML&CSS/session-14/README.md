# جلسه ۱۴: سلکتورهای CSS — Element، Class، ID، Attribute (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت **element selector**، **class selector**، **id selector** و **attribute selector** را توضیح دهید
- عناصر HTML را با نام تگ، کلاس، شناسه و attribute هدف بگیرید
- قوانین نام‌گذاری معتبر برای class و id بنویسید
- selectorهای attribute مثل `[type="text"]` و `[href^="https"]` را بکار ببرید
- تشخیص دهید کدام selector برای کدام سناریو مناسب‌تر است
- specificity اولیه class در برابر id را درک کنید
- با DevTools سلکتور اعمال‌شده روی عنصر را ببینید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور جلسه ۱۳ | external CSS، سینتکس selector |
| 10–25 | Element selector | `p`، `h1`، `div` — انتخاب با نام تگ |
| 25–40 | Class selector | `.classname` — استفاده مجدد |
| 40–50 | ID selector | `#idname` — یکتا بودن |
| 50–65 | Attribute selector | `[attr]`، `[attr="val"]`، عملگرها |
| 65–80 | تمرین کلاسی | استایل‌دهی فرم و منو |
| 80–90 | جمع‌بندی | اشتباهات رایج + تکلیف |

---

## ۱. سلکتور چیست؟

**Selector** بخشی از rule CSS است که مشخص می‌کند **کدام عناصر HTML** استایل می‌گیرند.

```css
selector {
    property: value;
}
```

**تشبیه:** selector مثل آدرس پستی است — CSS فقط به «خانه‌هایی» که آدرسشان match می‌شود تحویل داده می‌شود.

### انواع اصلی (این جلسه)

| نوع | سینتکس | مثال HTML |
|-----|--------|-----------|
| Element | `tagname` | `<p>` |
| Class | `.classname` | `class="card"` |
| ID | `#idname` | `id="header"` |
| Attribute | `[attr]` | `type="email"` |

---

## ۲. Element Selector (سلکتور عنصر)

ساده‌ترین نوع — با **نام تگ HTML** همه عناصر آن نوع را انتخاب می‌کند:

```css
p {
    color: #333;
    line-height: 1.8;
}

h1 {
    font-size: 2rem;
    color: #2c3e50;
}

ul li {
    margin-bottom: 8px;
}
```

### ویژگی‌ها
- بدون نیاز به attribute اضافه در HTML
- همه عناصر همان نوع را تحت تأثیر قرار می‌دهد
- برای استایل پایه (typography، reset) عالی است

### محدودیت
- نمی‌توانید **فقط یک** `<p>` خاص را بدون class/id جدا کنید
- specificity پایین — به‌راحتی override می‌شود

### مثال عملی
```html
<p>پاراگراف اول</p>
<p>پاراگراف دوم</p>
```
هر دو پاراگراف رنگ `#333` می‌گیرند.

---

## ۳. Class Selector (سلکتور کلاس)

با **نقطه** و نام کلاس — `.classname`:

```css
.card {
    background: white;
    padding: 20px;
    border-radius: 8px;
}

.btn-primary {
    background: #3498db;
    color: white;
    padding: 10px 20px;
}
```

```html
<div class="card">محتوا</div>
<button class="btn-primary">ارسال</button>
```

### قوانین class در HTML
- می‌توان **چند class** داشت: `class="card highlight active"`
- class **تکرارپذیر** است — ده `<div class="card">` مجاز
- نام معتبر: حروف، اعداد، `-`، `_` — **نباید** با عدد شروع شود

### نام‌گذاری (BEM ساده)
```css
/* Block */
.product { }

/* Element */
.product__title { }

/* Modifier */
.product--featured { }
```

### چه زمانی class؟
- **همیشه** برای استایل‌های قابل استفاده مجدد
- کامپوننت‌ها: `.btn`، `.nav-link`، `.alert`
- ترجیح class بر id برای استایل‌دهی

---

## ۴. ID Selector (سلکتور شناسه)

با **هشتگ** — `#idname`:

```css
#main-header {
    background: #1a252f;
    color: white;
}

#login-form {
    max-width: 400px;
}
```

```html
<header id="main-header">...</header>
<form id="login-form">...</form>
```

### قوانین id
- در **یک صفحه** باید **یکتا (unique)** باشد
- فقط **یک id** روی هر عنصر (برخلاف class)
- برای anchor لینک: `<a href="#section-2">`
- برای JavaScript: `document.getElementById('login-form')`

### id برای CSS؟
- فقط وقتی عنصر **واقعاً یکتا** است
- specificity **بالا** — override سخت
- **توصیه:** برای استایل از class استفاده کنید؛ id برای JS و anchor

---

## ۵. Attribute Selector (سلکتور attribute)

عناصر را بر اساس **وجود** یا **مقدار** attribute انتخاب می‌کند.

### پایه
```css
/* هر عنصری که attribute disabled دارد */
[disabled] {
    opacity: 0.5;
    cursor: not-allowed;
}

/* مقدار دقیق */
input[type="text"] {
    border: 1px solid #ccc;
}

input[type="email"] {
    border-color: #3498db;
}
```

### عملگرهای attribute

| عملگر | معنی | مثال |
|-------|------|------|
| `[attr]` | attribute وجود دارد | `[required]` |
| `[attr="val"]` | مقدار دقیق | `[type="submit"]` |
| `[attr^="val"]` | شروع با | `[href^="https"]` |
| `[attr$="val"]` | پایان با | `[href$=".pdf"]` |
| `[attr*="val"]` | شامل | `[class*="btn"]` |
| `[attr~="val"]` | کلمه جدا در لیست | `[class~="active"]` |
| `[attr\|="val"]` | شروع با val و `-` | `[lang\|="fa"]` |

### مثال فرم
```css
input[required] {
    border-right: 3px solid #e74c3c;
}

a[href^="mailto:"]::before {
    content: "✉ ";
}

a[target="_blank"] {
    color: #2980b9;
}
```

### کاربردهای رایج
- استایل inputهای مختلف بدون class اضافه
- لینک‌های خارجی (`target="_blank"`)
- عناصر `required`، `disabled`، `readonly`
- data attributes: `[data-status="pending"]`

---

## ۶. ترکیب selectorها (مقدمه)

```css
/* element + class */
p.intro { font-size: 1.2em; }

/* element + attribute */
input[type="checkbox"] { width: auto; }

/* چند class */
.btn.btn-large { padding: 16px 32px; }
```

**نکته:** `.btn.btn-large` یعنی عنصری که **هر دو** class را دارد (combinator جلسه ۱۵).

---

## ۷. Specificity اولیه

وقتی چند rule روی یک عنصر اعمال شود، **specificity** تعیین می‌کند کدام برنده است:

| selector | specificity (تقریبی) |
|----------|---------------------|
| `p` | 0,0,0,1 |
| `.card` | 0,0,1,0 |
| `#header` | 0,1,0,0 |
| `p.intro` | 0,0,1,1 |
| `#header .nav` | 0,1,1,0 |

**قاعده کلی:** id > class > element

جلسه ۱۹ به‌طور کامل specificity را پوشش می‌دهد.

---

## ۸. DevTools — بررسی selector

1. `F12` → Elements
2. عنصر را انتخاب کنید
3. پنل **Styles** — ruleهای match شده
4. rule خط‌خورده = override شده
5. `:hov` برای شبیه‌سازی `:hover`

---

## ۹. اشتباهات رایج

| اشتباه | درست |
|--------|------|
| `#my-class` برای class | class با `.` — `#` فقط id |
| `.my id` با فاصله | `.my-id` یا `.myId` |
| id تکراری در صفحه | id باید یکتا باشد |
| class با عدد اول: `.1item` | `.item-1` |
| فراموش نقطه: `card` | `.card` |
| attribute بدون براکت: `type=text` | `[type="text"]` |
| استایل همه چیز با id | class برای استایل |

---

## ۱۰. بهترین روش‌ها

1. **Element** برای reset و typography پایه
2. **Class** روش اصلی استایل‌دهی کامپوننت
3. **ID** برای JS و anchor — نه برای هر استایل
4. **Attribute** برای فرم‌ها و semantic styling
5. نام class معنادار: `.nav-link` نه `.blue-text`
6. از id تکراری خودداری کنید — HTML invalid می‌شود

---

## ۱۱. تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_element_selectors.html` — انتخاب با نام تگ
- `02_class_selectors.html` — class و چند class
- `03_id_selectors.html` — id یکتا
- `04_attribute_selectors.html` + `04_attributes.css` — attribute

تمرین‌ها: `exercises/question.md`

---

## ۱۲. جمع‌بندی

- **Element:** `p`، `h1` — همه عناصر آن نوع
- **Class:** `.name` — قابل استفاده مجدد، روش اصلی
- **ID:** `#name` — یکتا، specificity بالا
- **Attribute:** `[type="text"]` — بر اساس attribute
- class برای استایل؛ id برای یکتایی و JS
- attribute selector برای فرم و لینک بدون class اضافه

---

## 🔜 جلسه بعد

**جلسه ۱۵: سلکتورها بخش ۲** — combinatorها، pseudo-class، pseudo-element
