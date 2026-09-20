# جلسه ۲۲: Flexbox پایه — Container Properties (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- `display: flex` و محور **main/cross** را بدانید
- **justify-content**، **align-items**، **flex-direction**، **flex-wrap**، **gap** بکار ببرید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور positioning | پرسش و پاسخ |
| 10–25 | display:flex و محورها | main/cross در RTL |
| 25–40 | justify-content و align-items | centering |
| 40–55 | flex-direction و wrap | responsive row |
| 55–70 | gap و navbar | live coding |
| 70–85 | تمرین کلاسی | flex toolbar |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. display flex

```css
.container { display: flex; }
```

---

## 2. flex-direction

row | row-reverse | column | column-reverse

---

## 3. justify-content

flex-start | center | space-between | space-around | space-evenly

---

## 4. align-items

stretch | flex-start | center | flex-end | baseline

---

## 5. flex-wrap

nowrap | wrap — responsive rows

---

## 6. gap

فاصله بین items — modern

---

## 7. main vs cross

row: main=horizontal در LTR — در RTL جهت معکوس.

---

## 8. when flex

navbar، row cards، centering، equal columns.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| flex روی wrong element | parent container |
| width 100% child conflict | flex:1 |
| float + flex | یک روش layout |

---

## بهترین روش‌ها

- gap به جای margin hack
- flex-direction column mobile
- align-items center nav

---



## ۹. محورهای Flex — در RTL

```css
.container {
    display: flex;
    flex-direction: row; /* در RTL: main از راست به چپ */
}
```

| flex-direction | main axis | cross axis |
|----------------|-----------|------------|
| row | افقی | عمودی |
| column | عمودی | افقی |

---

## ۱۰. justify-content — توزیع در main axis

```css
.toolbar { display: flex; justify-content: space-between; }
.centered { justify-content: center; }
```

| مقدار | رفتار |
|-------|--------|
| flex-start | ابتدای main |
| center | وسط |
| space-between | فاصله بین، بدون کناره |
| space-evenly | فاصله یکسان همه جا |

---

## ۱۱. align-items — تراز در cross axis

```css
.cards {
    display: flex;
    align-items: stretch; /* پیش‌فرض — ارتفاع یکسان */
    align-items: center;    /* عمودی وسط */
}
```

---

## ۱۲. flex-wrap و responsive

```css
.gallery {
    display: flex;
    flex-wrap: wrap;
    gap: 16px;
}
.gallery > * { flex: 1 1 200px; }
```

---

## ۱۳. gap — جایگزین margin hack

```css
.nav { display: flex; gap: 1rem; }
/* دیگر li:last-child margin:0 لازم نیست */
```

---

## ۱۴. الگوی navbar

```css
.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem 2rem;
}
.nav-links { display: flex; gap: 1.5rem; list-style: none; }
```

---

## ۱۵. تمرین کلاسی

نوار ابزار با logo راست، لینک‌ها وسط، دکمه چپ — فقط flex container properties.

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_flex_container.html`
- `02_justify_align.html`
- `03_flex_direction.html`
- `04_flex_wrap.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 22 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۳: Flexbox پیشرفته**

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 22 — Flexbox پایه | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 21: Position و z-index
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «Flexbox پایه» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **Flexbox پایه** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **Flexbox پایه** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.

### مدل ذهنی Flexbox

محور اصلی با `flex-direction` تعیین می‌شود؛ `justify-content` روی محور اصلی و `align-items` روی محور متقاطع اثر دارد. برای جلوگیری از overflow متن در فرزند انعطاف‌پذیر، گاهی `min-width: 0` لازم است.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_flex_container.html`](./examples/01_flex_container.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «Flexbox پایه» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «Flexbox پایه» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **Flexbox پایه** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 23: Flexbox پیشرفته** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

