# جلسه ۱۶: Box Model — Margin، Border، Padding، Content (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **Box Model** CSS را با چهار لایه content، padding، border، margin توضیح دهید
- تفاوت `width`/`height` در `content-box` و `border-box` را بدانید
- `box-sizing: border-box` را globally اعمال کنید
- **margin collapse** (ریزش حاشیه) را پیش‌بینی و مدیریت کنید
- padding و margin را در layout RTL درست استفاده کنید
- با DevTools diagram جعبه را ببینید و debug کنید
- border: width، style، color و shorthand بنویسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور سلکتورها | جلسه ۱۴–۱۵ |
| 10–25 | Box model چیست؟ | diagram، content area |
| 25–40 | Padding و Border | داخل جعبه، shorthand |
| 40–55 | Margin | بیرون جعبه، collapse |
| 55–65 | box-sizing | content-box vs border-box |
| 65–80 | تمرین | کارت، دکمه، layout |
| 80–90 | جمع‌بندی | تکلیف |

---

## ۱. Box Model چیست؟

هر عنصر HTML در CSS یک **جعبه مستطیلی** است — حتی `<span>` و `<a>`.

```
┌────────────── margin ──────────────┐
│  ┌─────────── border ───────────┐  │
│  │  ┌──────── padding ────────┐  │  │
│  │  │                         │  │  │
│  │  │       CONTENT           │  │  │
│  │  │                         │  │  │
│  │  └─────────────────────────┘  │  │
│  └───────────────────────────────┘  │
└─────────────────────────────────────┘
```

| لایه | جهت | رنگ در DevTools |
|------|-----|-----------------|
| Content | متن/تصویر داخل | آبی |
| Padding | فاصله داخل تا border | سبز |
| Border | خط دور | زرد |
| Margin | فاصله بیرون تا عنصر بعد | نارنجی |

---

## ۲. Content (محتوا)

```css
.box {
    width: 300px;
    height: 150px;
}
```

**width/height** به‌طور پیش‌فرض فقط **content area** را اندازه می‌گیرند (`box-sizing: content-box`).

محتوای بیش از عرض → overflow (جلسات بعد).

---

## ۳. Padding (فاصله داخلی)

فاصله بین **content** و **border**:

```css
.card {
    padding: 20px;              /* همه طرف */
    padding: 10px 20px;         /* عمودی | افقی */
    padding: 10px 20px 15px 25px; /* top right bottom left — ساعتگرد */
}

/* RTL: right/left فیزیکی هستند — margin-right همان سمت راست صفحه */
```

### نکات padding
- پس‌زمینه (`background`) تا padding کشیده می‌شود
- padding روی inline element فقط افقی (قدیمی) — inline-block بهتر
- padding بزرگ برای دکمه و کارت

---

## ۴. Border (حاشیه)

```css
.box {
    border: 2px solid #3498db;
    /* shorthand: width style color */

    border-width: 1px 2px 3px 4px;
    border-style: solid dashed dotted double;
    border-color: red;
    border-radius: 8px; /* گوشه گرد — جدا از box model کلاسیک */
}
```

**style:** `solid`، `dashed`، `dotted`، `double`، `none`

---

## ۵. Margin (فاصله خارجی)

فاصله **خارج از border** تا عنصر مجاور:

```css
section {
    margin: 24px 0;
    margin-left: auto;
    margin-right: auto; /* center block با width مشخص */
}
```

### margin collapse (ریزش)
دو margin عمودی مجاور **بزرگ‌تر** را merge می‌کنند — نه جمع:

```css
.box-a { margin-bottom: 30px; }
.box-b { margin-top: 20px; }
/* فاصله بین آن‌ها 30px است — نه 50px */
```

**راه‌حل:** padding والد، `overflow: auto`، flex/grid، یا `border` شفاف.

---

## ۶. box-sizing

```css
*, *::before, *::after {
    box-sizing: border-box;
}

.box {
    width: 300px;
    padding: 20px;
    border: 2px solid black;
    /* با border-box: کل عرض visible = 300px */
    /* با content-box: کل = 300 + 40 padding + 4 border */
}
```

**توصیه حرفه‌ای:** reset/global `border-box` — محاسبه layout آسان‌تر.

---

## ۷. width، max-width، min-width

```css
.container {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 16px;
}
```

---

## ۸. margin: auto و center

```css
.block-center {
    width: 400px;
    margin-left: auto;
    margin-right: auto;
}
```

فقط روی **block** با width مشخص — برای flex/grid روش‌های دیگر.

---

## ۹. DevTools Box Model

1. Inspect عنصر
2. پنل Computed یا Layout
3. diagram رنگی — کلیک برای edit موقت
4. margin collapse را با highlight مجاور ببینید

---

## ۱۰. Logical Properties (پیش‌نمایش)

```css
.card {
    margin-inline: auto;
    padding-block: 16px;
    padding-inline: 24px;
}
```

`inline` = جهت متن (RTL: افقی)، `block` = عمودی — برای i18n بهتر.

---

## ۱۱. اشتباهات رایج

| اشتباه | درست |
|--------|------|
| width 100% + padding بدون border-box | `box-sizing: border-box` |
| margin collapse غافل | padding والد یا flex |
| padding برای فاصله بین siblings | margin بین عناصر |
| border جا انداختن در محاسبه | border-box |
| margin روی inline عمودی (legacy) | block یا inline-block |

---

## ۱۲. تمرین‌ها

- `01_box_layers.html` — نمایش چهار لایه
- `02_padding_margin.html` — فاصله‌گذاری
- `03_border_box.html` — border و radius
- `04_box_sizing.html` + `04_sizing.css` — content-box vs border-box

---

## ۱۳. جمع‌بندی

- Box model: content → padding → border → margin
- padding داخل، margin بیرون
- margin collapse در margin عمودی مجاور
- `border-box` برای layout predictable
- DevTools بهترین دوست شما

---

## 🔜 جلسه بعد

**جلسه ۱۷: رنگ، پس‌زمینه و واحدها** — hex، rgb، gradient، px، rem

---

## پیوست: تمرین‌های بیشتر Box Model

### A. محاسبه عرض کل

با `content-box` و `width: 200px`، `padding: 20px`، `border: 2px`:
- عرض content = 200px
- عرض padding box = 200 + 40 = 240px
- عرض border box = 240 + 4 = 244px

با `border-box` و همان مقادیر:
- عرض border box = 200px (شامل padding و border)

### B. margin collapse سناریوها

1. دو sibling block با margin عمودی
2. parent اولین child با margin-top
3. empty block با margin top/bottom

### C. checklist layout

- [ ] global border-box
- [ ] padding برای فضای داخلی clickable
- [ ] margin برای فاصله بین components
- [ ] max-width + margin auto برای container

### D. منابع

- MDN: Introduction to the CSS box model
- DevTools: Layout tab

### E. سوالات کلاسی

1. تفاوت padding و margin؟
2. چرا border-box محبوب است؟
3. margin collapse چه زمانی رخ می‌دهد؟

### F. ارتباط با جلسات بعد

Box model پایه **همه** layout است — flex/grid/item sizing روی همین جعبه‌ها اعمال می‌شود.

### G. نمونه reset رایج

```css
*, *::before, *::after {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}
/* سپس component-level padding/margin */
```

### H. تمرین سریع

سه div با margin 20px — فاصله بین div1 و div2 را پیش‌بینی کنید (collapse).

### I. border-radius

`border-radius` شکل گوشه را تغییر می‌دهد — box model diagram همچنان مستطیل.

### J. overflow (پیش‌نمایش)

اگر content از width بزرگ‌تر شود: `overflow: hidden | scroll | auto`.

### K. logical properties

```css
margin-block: 16px;
padding-inline: 24px;
```

برای RTL/LTR یکسان — inline = جهت متن.

### L. جمع‌بندی نهایی جلسه

Box model زبان مشترک CSS layout — مسلط باشید قبل از flex و grid.
