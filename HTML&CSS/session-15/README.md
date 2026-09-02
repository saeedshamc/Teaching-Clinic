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
