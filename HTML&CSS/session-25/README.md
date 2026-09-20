# جلسه ۲۵: CSS Grid پیشرفته — Areas، Alignment، Nested (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **grid-template-areas** برای layout نام‌گذاری‌شده
- **justify-items**، **align-items**، **place-items** را بکار ببرید
- **nested grid** بسازید
- responsive grid با **auto-fit/minmax**

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور grid basics | پرسش و پاسخ |
| 10–25 | grid-template-areas | layout نام‌گذاری |
| 25–40 | justify/align items | تراز داخل سلول |
| 40–55 | nested grid | grid داخل grid |
| 55–70 | auto-fit/minmax | responsive بدون media |
| 70–85 | تمرین | صفحه کامل header/sidebar |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. grid-template-areas

```css
grid-template-areas:
  "header header"
  "sidebar main"
  "footer footer";
```

---

## 2. grid-area

item: grid-area: header;

---

## 3. alignment

justify-items, align-items, justify-content, align-content

---

## 4. place-items

shorthand align + justify items

---

## 5. nested grid

child display:grid — subgrid preview

---

## 6. responsive

`grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));`

---

## 7. full layout

header sidebar main footer یک grid

---

## 8. finish

پایان CSS layout core — JS frameworks بعدی.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| areas نام ناهماهنگ | rectangular regions |
| nested بدون plan | sketch first |
| minmax too small | 250px+ cards |

---

## بهترین روش‌ها

- areas for readability
- auto-fit responsive
- gap consistent

---



## ۹. grid-template-areas — layout خوانا

```css
.layout {
    display: grid;
    grid-template-areas:
        "header header"
        "sidebar main"
        "footer footer";
    grid-template-columns: 200px 1fr;
    grid-template-rows: auto 1fr auto;
    min-height: 100vh;
}
header  { grid-area: header; }
.sidebar { grid-area: sidebar; }
main    { grid-area: main; }
footer  { grid-area: footer; }
```

---

## ۱۰. justify-items و align-items

```css
.grid {
    display: grid;
    justify-items: center;   /* افقی داخل سلول */
    align-items: center;     /* عمودی داخل سلول */
    place-items: center;     /* shorthand */
}
```

---

## ۱۱. justify-content و align-content

وقتی grid کوچک‌تر از container:
```css
.grid {
    justify-content: center;
    align-content: start;
}
```

---

## ۱۲. Nested Grid

```css
.parent { display: grid; grid-template-columns: 1fr 1fr; }
.child {
    display: grid;
    grid-template-columns: subgrid; /* مرورگرهای مدرن */
    /* یا: repeat(2, 1fr) */
}
```

---

## ۱۳. Responsive با auto-fit

```css
.gallery {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
    gap: 1.5rem;
}
```
بدون media query — ستون‌ها خودکار wrap.

---

## ۱۴. grid-area shorthand

```css
.item {
    grid-column: 1 / 3;  /* از خط 1 تا 3 */
    grid-row: 2 / 4;
}
/* یا: grid-area: 2 / 1 / 4 / 3; (row-start/col-start/row-end/col-end) */
```

---

## ۱۵. تمرین نهایی: صفحه کامل

header، sidebar، main، footer با `grid-template-areas` — responsive: sidebar زیر main در موبایل با تغییر areas.

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_grid_areas.html`
- `02_grid_alignment.html`
- `03_nested_grid.html`
- `04_responsive_grid.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 25 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**ادامه دوره — Responsive Design و Media Queries**

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 25 — Grid پیشرفته | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 24: Grid پایه
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «Grid پیشرفته» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **Grid پیشرفته** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **Grid پیشرفته** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_grid_areas.html`](./examples/01_grid_areas.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «Grid پیشرفته» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «Grid پیشرفته» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **Grid پیشرفته** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 26: الگوی Navigation واکنش‌گرا** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

