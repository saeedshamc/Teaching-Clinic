# جلسه ۱۹: Specificity، Cascade، Inheritance (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- **specificity** را محاسبه و مقایسه کنید
- **cascade** — ترتیب اعمال ruleها — را توضیح دهید
- **inheritance** — چه propertyهایی ارث می‌برند
- `inherit`، `initial`، `unset`، `revert` را بکار ببرید
- از `!important` فقط در موارد ضروری استفاده کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور display و box model | پرسش و پاسخ |
| 10–25 | Specificity | محاسبه امتیاز selector |
| 25–40 | Cascade | ترتیب منبع و !important |
| 40–55 | Inheritance | propertyهای inherited |
| 55–70 | Override عملی | DevTools — کدام rule برنده است |
| 70–85 | تمرین کلاسی | رفع تداخل استایل |
| 85–90 | جمع‌بندی | تکلیف |

---

## 1. Cascade

منبع: browser default → user → author. ترتیب در author: import → inline → id/class/element. آخرین rule برنده (specificity برابر).

---

## 2. Specificity

| selector | (a,b,c,d) |
| `#id` | (1,0,0,0) |
| `.class` | (0,1,0,0) |
| `element` | (0,0,0,1) |
| inline style | (1,0,0,0) style attribute |

```css
#nav .link { }  /* 0,1,1,0 */
p.intro { }     /* 0,0,1,1 */
```

---

## 3. Inheritance

ارث: color، font-family، line-height. **نه** margin، padding، border، width.

---

## 4. Keywords

```css
.child { color: inherit; }
.reset { all: unset; }
```

---

## 5. !important

```css
.override { color: red !important; }
```
فقط utility یا override library — نه habit.

---

## 6. Layer و order

ترتیب `<link>` — فایل بعد override.

---

## 7. DevTools

rule خط‌خورده = specificity یا order باخت.

---

## 8. معماری CSS

class-based، BEM، کم nesting — specificity پایین.

---

## اشتباهات رایج

| اشتباه | درست |
|--------|------|
| !important برای همه | refactor selector |
| #id برای هر استایل | class |
| selector خیلی طولانی | class component |
| inline style زیاد | external CSS |

---

## بهترین روش‌ها

- specificity پایین
- class component
- !important rare
- یک source truth

---



## ۹. محاسبه Specificity — گام‌به‌گام

```
#nav .item a:hover
(1, 2, 2) = 1 id + 2 class/pseudo + 2 element
```

**مقایسه:** از چپ به راست — id اول، بعد class، بعد element.

---

## ۱۰. Cascade — منابع استایل

1. User agent (پیش‌فرض مرورگر)
2. User (تنظیمات کاربر)
3. Author (CSS شما)
4. !important author
5. !important user

**ترتیب در یک منبع:** بعدی برنده — مگر specificity بالاتر.

---

## ۱۱. Inheritance — چه چیز به ارث می‌رسد؟

**Inherited:** color, font-family, font-size, line-height, text-align, ...
**Not inherited:** margin, padding, border, width, background, display, ...

```css
body { color: #333; font-family: Tahoma; }
/* همه descendants رنگ و فونت می‌گیرند مگر override */
```

### inherit و initial
```css
a { color: inherit; }
.reset { all: unset; }
```

---

## ۱۲. !important — کی استفاده کنیم؟

```css
.btn { background: blue !important; } /* اجتناب در پروژه */
```

فقط برای: override کتابخانه third-party، utility موقت در debug.
**بهتر:** specificity بالاتر با selector دقیق‌تر.

---

## ۱۳. @layer (مدرن)

```css
@layer reset, base, components, utilities;
@layer components {
    .btn { padding: 1rem; }
}
```
کنترل cascade بدون specificity war.

---

## ۱۴. DevTools — Computed Styles

1. Inspect element
2. تب Computed — مقدار نهایی
3. فلش کنار property → منبع rule

---

## ۱۵. تمرین: چه رنگی نمایش داده می‌شود؟

```css
p { color: blue; }
.intro { color: green; }
#lead { color: red; }
p.intro { color: orange; }
```
`<p id="lead" class="intro">` → **red** (id برنده)

---

## تمرین‌ها

فایل‌های نمونه در `examples/`:
- `01_specificity.html`
- `02_inheritance.html`
- `03_cascade_order.html`
- `04_important.html`

تمرین‌ها: `exercises/question.md`

---

## جمع‌بندی

مفاهیم کلیدی جلسه 19 را مرور کنید و مثال‌ها را در مرورگر اجرا کنید.

---

## 🔜 جلسه بعد

**جلسه ۲۰: Display**

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 19 — Cascade، Specificity و Inheritance | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 18: تایپوگرافی
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «Cascade، Specificity و Inheritance» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **Cascade، Specificity و Inheritance** یک جزیرهٔ جدا نیست؛ مستقیماً روی انتخاب rule، محاسبهٔ style، layout و paint در مرورگر اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **Cascade، Specificity و Inheritance** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.

### الگوریتم حل تعارض

مرورگر ابتدا مبدأ و اهمیت، سپس cascade layer، سپس specificity و در پایان ترتیب منبع را مقایسه می‌کند. ویژگی‌های ارث‌پذیر مثل `color` از والد می‌آیند، اما `margin` ارث نمی‌رسد. پیش از افزودن `!important`، قانون برنده را در پنل **Computed** پیدا کنید.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_specificity.html`](./examples/01_specificity.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «Cascade، Specificity و Inheritance» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «Cascade، Specificity و Inheritance» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **Cascade، Specificity و Inheritance** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 20: Display و جریان سند** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

