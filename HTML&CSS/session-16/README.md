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

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 16 — Box Model | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 15: ترکیب انتخابگرها
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «Box Model» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **Box Model** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **Box Model** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.

### مدل محاسبهٔ اندازه

با `box-sizing: border-box` مقدار `width` شامل content، padding و border است. Margin بیرون جعبه می‌ماند و margin عمودی بلوک‌ها ممکن است **collapse** شود؛ برای فاصلهٔ اعضای layout اغلب `gap` قابل‌پیش‌بینی‌تر است.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_box_layers.html`](./examples/01_box_layers.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «Box Model» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «Box Model» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **Box Model** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 17: رنگ و پس‌زمینه** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

