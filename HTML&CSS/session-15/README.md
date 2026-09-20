# جلسه ۱۵: سلکتورها بخش ۲ — Combinator، Pseudo-class، Pseudo-element (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **Combinator**ها را بشناسید: descendant، child (`>`)، adjacent sibling (`+`)، general sibling (`~`)
- **Pseudo-class** مثل `:hover`، `:focus`، `:nth-child`، `:first-child` را بکار ببرید
- **Pseudo-element** مثل `::before`، `::after`، `::first-line` را تفکیک کنید
- تفاوت `:before` و `::before` (سینتکس قدیم/جدید) را بدانید
- منو، جدول و لیست را با pseudo-class استایل دهید
- محتوای تزئینی با `::before`/`::after` بدون HTML اضافه بسازید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور جلسه ۱۴ | element، class، id، attribute |
| 10–25 | Combinatorها | فاصله، `>`، `+`، `~` |
| 25–45 | Pseudo-class | `:hover`، `:focus`، `:nth-child` |
| 45–60 | Pseudo-element | `::before`، `::after`، content |
| 60–80 | تمرین | منوی hover، شماره‌گذاری لیست |
| 80–90 | جمع‌بندی | تکلیف |

---

## ۱. Combinator (ترکیب‌کننده) چیست؟

Combinator رابطه **سلسله‌مراتبی** بین دو selector را مشخص می‌کند.

### Descendant (نواده — فاصله)
```css
article p {
    color: #555;
}
```
هر `<p>` **داخل** `<article>` — در هر عمق.

### Child (`>`)
```css
ul > li {
    list-style: square;
}
```
فقط `<li>` که **فرزند مستقیم** `ul` است — نه `<li>` داخل `<li>`.

### Adjacent Sibling (`+`)
```css
h2 + p {
    font-size: 1.1em;
    font-weight: bold;
}
```
اولین `<p>` **بلافاصله بعد** از `h2`.

### General Sibling (`~`)
```css
h2 ~ p {
    margin-right: 20px;
}
```
همه `<p>`های **هم‌سطح بعد از** `h2` (نه لزوماً بلافاصله).

| Combinator | سینتکس | معنی |
|------------|--------|------|
| Descendant | `A B` | B داخل A |
| Child | `A > B` | B فرزند مستقیم A |
| Adjacent | `A + B` | B بلافاصله بعد A |
| General | `A ~ B` | B هم‌سطح بعد A |

---

## ۲. مثال combinator در منو

```html
<nav>
  <ul>
    <li><a href="#">خانه</a></li>
    <li><a href="#">درباره</a></li>
  </ul>
</nav>
```

```css
nav ul {
    list-style: none;
    padding: 0;
}

nav > ul > li {
    display: inline-block;
}

nav a:hover {
    color: #3498db;
}
```

---

## ۳. Pseudo-class (شبه‌کلاس)

Pseudo-class **حالت** عنصر را هدف می‌گیرد — بدون class اضافه در HTML.

### لینک و تعامل
```css
a:link { color: #2980b9; }
a:visited { color: #8e44ad; }
a:hover { text-decoration: underline; }
a:active { color: #c0392b; }
a:focus { outline: 2px solid #3498db; }
```

### فرم
```css
input:focus {
    border-color: #3498db;
    box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.3);
}

input:disabled {
    opacity: 0.6;
}

input:checked + label {
    font-weight: bold;
}
```

### ساختار (Structural)
```css
li:first-child { font-weight: bold; }
li:last-child { border-bottom: none; }
li:nth-child(odd) { background: #f9f9f9; }
li:nth-child(even) { background: #fff; }
li:nth-child(3n) { color: #e74c3c; }
```

**`:nth-child(an+b)`** — n از ۰ شروع می‌شود.
- `2n` → زوج (در RTL ترتیب DOM)
- `2n+1` → فرد
- `-n+3` → سه تای اول

### `:not()`
```css
p:not(.intro) {
    color: #666;
}

button:not(:disabled):hover {
    background: #2980b9;
}
```

---

## ۴. Pseudo-element (شبه‌عنصر)

Pseudo-element **بخشی از عنصر** یا **محتوای مجازی** را استایل می‌دهد.

### `::before` و `::after`
```css
.quote::before {
    content: "«";
    color: #3498db;
    font-size: 2em;
}

.quote::after {
    content: "»";
}

.required-label::after {
    content: " *";
    color: red;
}
```

**`content` اجباری** برای `::before`/`::after` — حتی `content: ""` برای تزئین.

### `::first-line` و `::first-letter`
```css
p::first-line {
    font-weight: bold;
}

p::first-letter {
    font-size: 3em;
    float: left;
    line-height: 1;
}
```

### `::placeholder`
```css
input::placeholder {
    color: #aaa;
    font-style: italic;
}
```

### `::selection`
```css
::selection {
    background: #3498db;
    color: white;
}
```

---

## ۵. تفاوت Pseudo-class و Pseudo-element

| | Pseudo-class | Pseudo-element |
|---|--------------|----------------|
| سینتکس | یک `:`` | دو `::` (CSS3) |
| مثال | `:hover` | `::before` |
| هدف | حالت عنصر | بخش/محتوای مجازی |
| HTML | بدون تغییر | بدون تگ اضافه |

**سازگاری:** `:before` قدیمی هنوز کار می‌کند؛ `::before` استاندارد CSS3.

---

## ۶. الگوی رایج: آیکون با ::before

```css
.icon-download::before {
    content: "⬇ ";
}

.external-link::after {
    content: " ↗";
    font-size: 0.85em;
}
```

---

## ۷. جدول zebra با nth-child

```css
tr:nth-child(even) {
    background: #f8f9fa;
}

tr:hover {
    background: #e8f4fc;
}
```

---

## ۸. DevTools

- `:hov` → force `:hover`، `:active`، `:focus`
- در Styles pseudo-elements جدا نمایش داده می‌شوند

---

## ۹. اشتباهات رایج

| اشتباه | درست |
|--------|------|
| `::before` بدون `content` | `content: ""` یا متن |
| `ul li` وقتی فقط direct لازم است | `ul > li` |
| `:nth-child(0)` | nth از ۱ برای اولین child |
| pseudo-element روی `<img>` | before/after روی replaced elements محدود |
| `:hover` روی موبایل | `:focus` یا class `.active` هم در نظر بگیرید |

---

## ۱۰. بهترین روش‌ها

1. Combinator عمیق (`div div div p`) را کم کنید — class بهتر
2. `:focus-visible` برای keyboard accessibility
3. `::before`/`::after` برای تزئین — نه محتوای مهم SEO
4. `:not()` برای استثنا — خوانا نگه دارید
5. ترتیب link states: `:link` → `:visited` → `:hover` → `:active`

---

## ۱۱. تمرین‌ها

- `01_combinators.html` — descendant، child، sibling
- `02_pseudo_classes.html` — hover، focus، nth-child
- `03_pseudo_elements.html` — before، after، first-letter
- `04_navigation_menu.html` + `04_nav.css` — منوی کامل

---

## ۱۲. جمع‌بندی

- **Combinator:** فاصله، `>`، `+`، `~`
- **Pseudo-class:** حالت (`:hover`، `:nth-child`)
- **Pseudo-element:** بخش مجازی (`::before`، `::after`)
- `content` برای before/after ضروری است
- structural pseudo-class برای جدول و لیست عالی

---

## 🔜 جلسه بعد

**جلسه ۱۶: Box Model** — margin، border، padding، content

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 15 — ترکیب انتخابگرها | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 14: انتخابگرهای CSS
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «ترکیب انتخابگرها» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **ترکیب انتخابگرها** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **ترکیب انتخابگرها** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_combinators.html`](./examples/01_combinators.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «ترکیب انتخابگرها» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «ترکیب انتخابگرها» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **ترکیب انتخابگرها** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 16: Box Model** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

