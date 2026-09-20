# جلسه ۲۰: Display — block، inline، inline-block، none (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **display: block/inline/inline-block/none** را تفکیک کنید
- width/height روی inline vs block را بدانید
- **visibility: hidden** vs **display: none** را مقایسه کنید
- inline-block برای دکمه و badge بکار ببرید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور specificity | پرسش و پاسخ |
| 10–25 | block و inline | رفتار پیش‌فرض تگ‌ها |
| 25–40 | inline-block | دکمه‌ها و badge |
| 40–55 | display: none vs visibility | تفاوت در layout |
| 55–70 | تمرین | نوار ابزار و تگ‌ها |
| 70–85 | معرفی flex preview | display:flex |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. block

```css
div, p, h1 { display: block; }
```
عرض کامل، line break بعد، width/height/margin عمودی.

---

## 2. inline

```css
span, a { display: inline; }
```
در خط، width/height نادیده، margin عمودی محدود.

---

## 3. inline-block

```css
.btn { display: inline-block; padding: 8px 16px; }
```
در خط + box model کامل.

---

## 4. none

```css
.hidden { display: none; }
```
از layout حذف — screen reader هم معمولاً skip.

---

## 5. visibility

```css
.invisible { visibility: hidden; }
```
جای خالی حفظ — متفاوت از none.

---

## 6. hidden attribute

```html
<div hidden>...</div>
```

---

## 7. تغییر display

`<a>` block برای nav full-width click area.

---

## 8. accessibility

display:none برای toggle — aria-hidden هم در نظر بگیرید.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| width روی span inline | inline-block یا block |
| none برای SEO content | off-screen یا aria |
| br زیاد برای layout | display block |
| float برای layout | flex/grid |

---

## بهترین روش‌ها

- inline-block برای UI chip
- none برای JS toggle
- semantic HTML first

---



## ۹. جدول display — رفتار کامل

| display | عرض/ارتفاع | margin/padding عمودی | line break |
|---------|------------|---------------------|------------|
| block | قابل تنظیم | ✅ | قبل و بعد |
| inline | محتوا | فقط افقی | نه |
| inline-block | قابل تنظیم | ✅ | نه |
| none | — | — | از layout حذف |

---

## ۱۰. block — تگ‌های پیش‌فرض

`div`, `p`, `h1-h6`, `section`, `article`, `ul`, `li` (لیست block)

```css
p { display: block; width: 100%; margin: 1em 0; }
```

---

## ۱۱. inline — تگ‌های پیش‌فرض

`span`, `a`, `strong`, `em`, `img` (replaced inline)

```css
span { display: inline; }
/* width/height روی pure inline اثر ندارد */
```

---

## ۱۲. inline-block — بهترین هر دو دنیا

```css
.btn {
    display: inline-block;
    padding: 8px 16px;
    width: auto;
}
```

دکمه‌ها، badge، chip — کنار هم در یک خط + ابعاد قابل کنترل.

---

## ۱۳. display: none vs visibility: hidden

```css
.hidden-layout { display: none; }      /* جا نمی‌گیرد */
.hidden-visual { visibility: hidden; } /* جا می‌گیرد، دیده نمی‌شود */
```

برای accessibility منو: `aria-hidden` + focus management.

---

## ۱۴. display: flex و grid (پیش‌نمایش)

```css
.nav { display: flex; gap: 1rem; }
.gallery { display: grid; grid-template-columns: repeat(3, 1fr); }
```

جلسات ۲۲–۲۵ عمیق‌تر می‌شوند.

---

## ۱۵. تمرین: تبدیل لیست افقی

```html
<ul class="nav">
  <li><a href="#">خانه</a></li>
  <li><a href="#">درباره</a></li>
</ul>
```
```css
.nav { list-style: none; padding: 0; display: flex; gap: 1rem; }
.nav li { display: inline-block; }
```

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_block_inline.html`
- `02_inline_block.html`
- `03_display_none.html`
- `04_visibility.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 20 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۱: Positioning**

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 20 — Display و جریان سند | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 19: Cascade، Specificity و Inheritance
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «Display و جریان سند» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **Display و جریان سند** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **Display و جریان سند** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_block_inline.html`](./examples/01_block_inline.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «Display و جریان سند» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «Display و جریان سند» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **Display و جریان سند** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 21: Position و z-index** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

