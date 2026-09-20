# جلسه ۲۱: Positioning — static، relative، absolute، fixed، sticky (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **position** و offsetها (`top`، `right`، `bottom`، `left`) را بکار ببرید
- **absolute** نسبت به ancestor positioned را درک کنید
- **fixed** header و **sticky** sidebar بسازید
- **z-index** و stacking context را مدیریت کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور display | پرسش و پاسخ |
| 10–25 | static و relative | offset و جریان سند |
| 25–40 | absolute | containing block |
| 40–55 | fixed و sticky | header ثابت، sidebar |
| 55–70 | z-index و لایه‌ها | stacking context |
| 70–85 | تمرین | کارت روی تصویر |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. static

پیش‌فرض — offset بی‌اثر.

---

## 2. relative

```css
.box { position: relative; top: 10px; left: 5px; }
``` جابجایی بدون خارج شدن از flow — فضای اصلی حفظ.

---

## 3. absolute

```css
.parent { position: relative; }
.badge { position: absolute; top: 0; left: 0; }
``` از flow خارج — نسبت به نزدیک‌ترین positioned ancestor.

---

## 4. fixed

```css
.header { position: fixed; top: 0; width: 100%; z-index: 100; }
``` نسبت به viewport.

---

## 5. sticky

```css
.sidebar { position: sticky; top: 80px; }
``` hybrid — تا scroll در container.

---

## 6. z-index

فقط روی positioned — stacking context.

---

## 7. use cases

badge، modal overlay، fixed nav، sticky TOC.

---

## 8. pitfalls

absolute بدون relative parent — body reference.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| absolute بدون parent relative | parent position:relative |
| z-index بی‌نهایت | scale منطقی |
| fixed بدون padding body | padding-top برای header |

---

## بهترین روش‌ها

- relative برای anchor absolute
- fixed sparingly
- sticky برای nav

---



## ۹. position: static (پیش‌فرض)

جریان عادی سند — `top/left/right/bottom` و `z-index` بی‌اثر.

---

## ۱۰. position: relative

```css
.box { position: relative; top: 10px; left: 20px; }
```
جابه‌جایی نسبت به **جای اصلی** — فضای اصلی خالی می‌ماند.
**کاربرد:** offset جزئی، ایجاد containing block برای absolute فرزند.

---

## ۱۱. position: absolute

```css
.parent { position: relative; }
.badge {
    position: absolute;
    top: -8px;
    right: -8px;
}
```

نسبت به نزدیک‌ترین ancestor با position ≠ static.
از flow خارج — عناصر بعدی جای آن را نمی‌گیرند.

---

## ۱۲. position: fixed

```css
.header {
    position: fixed;
    top: 0; left: 0; right: 0;
    z-index: 100;
}
```
نسبت به **viewport** — هنگام scroll ثابت می‌ماند.
`body { padding-top: 64px; }` برای جبران ارتفاع header.

---

## ۱۳. position: sticky

```css
thead th {
    position: sticky;
    top: 0;
    background: white;
}
```
بین relative و fixed — تا رسیدن به threshold می‌چسبد.

---

## ۱۴. z-index و stacking context

```css
.modal { z-index: 1000; }
.dropdown { z-index: 100; }
```
فقط داخل همان stacking context مقایسه می‌شود.
`position` + `z-index` یا `opacity < 1` یا `transform` context جدید می‌سازد.

---

## ۱۵. تمرین: کارت با badge

کارت `position: relative`، badge قرمز `absolute` گوشه بالا-چپ (در RTL).

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_relative.html`
- `02_absolute.html`
- `03_fixed_header.html`
- `04_sticky.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 21 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۲: Flexbox پایه**

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 21 — Position و z-index | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 20: Display و جریان سند
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «Position و z-index» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **Position و z-index** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **Position و z-index** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.

### stacking context و z-index

`z-index` عددی جهانی نیست؛ فقط در stacking context مربوط مقایسه می‌شود. `position` همراه z-index، `transform` و `opacity` کمتر از ۱ می‌توانند context تازه بسازند. برای لایه‌ها مقیاس محدود مثل `--layer-nav: 20` و `--layer-modal: 40` تعریف کنید.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_relative.html`](./examples/01_relative.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «Position و z-index» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «Position و z-index» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **Position و z-index** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 22: Flexbox پایه** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

