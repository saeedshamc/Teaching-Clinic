# جلسه ۹: فرم‌ها — بخش ۱: input و label (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- فرم با `<form>`, `action`, `method` بسازید
- انواع `<input>` (text, email, password, number, date, file…) را به کار ببرید
- `<label>` را با `for`/`id` به input وصل کنید
- از `fieldset` و `legend` برای گروه‌بندی استفاده کنید
- attributeهای `required`, `placeholder`, `min`, `max` را بشناسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | فرم HTML چرا؟ |
| 10–25 | form، action، method |
| 25–45 | input types |
| 45–60 | label و fieldset |
| 60–75 | required و validation پایه |
| 75–90 | تمرین، تکلیف |

---

## ۱. فرم HTML

```html
<form action="/api/contact" method="post">
    <!-- فیلدها -->
    <button type="submit">ارسال</button>
</form>
```

| Attribute | توضیح |
|-----------|--------|
| `action` | URL مقصد ارسال |
| `method` | `get` (query string) یا `post` (body) |
| `enctype` | برای file: `multipart/form-data` |

**یادآوری:** بدون backend، action معمولاً `#` یا خالی — فقط UI.

---

## ۲. input — انواع رایج

```html
<input type="text" name="username">
<input type="email" name="email">
<input type="password" name="password">
<input type="number" name="age" min="1" max="120">
<input type="date" name="birth">
<input type="tel" name="phone">
<input type="url" name="website">
<input type="search" name="q">
<input type="color" name="theme">
<input type="file" name="avatar">
<input type="hidden" name="token" value="abc">
```

**name** — کلید ارسال به سرور؛ **id** — برای label و JS.

---

## ۳. label — دسترس‌پذیری

```html
<label for="email">ایمیل:</label>
<input type="email" id="email" name="email">
```

کلیک روی label فوکوس به input می‌رود — مهم برای موبایل و screen reader.

```html
<label>
    نام:
    <input type="text" name="name">
</label>
```

---

## ۴. fieldset و legend

```html
<fieldset>
    <legend>آدرس</legend>
    <label for="city">شهر:</label>
    <input type="text" id="city" name="city">
</fieldset>
```

---

## ۵. button

```html
<button type="submit">ارسال</button>
<button type="reset">پاک کردن</button>
<button type="button">فقط JS (بعداً)</button>
```

---

## ۶. validation HTML5

```html
<input type="text" required>
<input type="email" required>
<input type="number" min="18" max="99">
<input type="text" minlength="3" maxlength="50">
<input type="text" pattern="[A-Za-z]+" title="فقط حروف لاتین">
```

مرورگر قبل از submit پیام خطا نشان می‌دهد.

---

## ۷. placeholder

```html
<input type="text" placeholder="نام خود را وارد کنید">
```

**جایگزین label نیست** — همیشه label داشته باشید.

---

## ۸. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_form.html](./examples/01_basic_form.html) | form پایه |
| [02_input_types.html](./examples/02_input_types.html) | انواع input |
| [03_labels_fieldset.html](./examples/03_labels_fieldset.html) | label/fieldset |
| [04_validation_basic.html](./examples/04_validation_basic.html) | required |

---

## ۹. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| input بدون name | name برای submit |
| label بدون for/id | اتصال label |
| placeholder به‌جای label | هر دو |
| type="text" برای email | type="email" |
| دکمه submit بیرون form | داخل form |

---

## ۱۰. get vs post

```
GET  /search?q=html     — داده در URL — جستجو
POST /login             — داده در body — حساس
```

---

## ۱۱. دسترس‌پذیری فرم

- label برای هر input
- fieldset برای گروه‌های مرتبط
- پیام خطا قابل فهم (browser default یا custom بعداً)
- autofocus با احتیاط — یک فیلد

---

## ۱۲. autocomplete

```html
<input type="email" name="email" autocomplete="email">
<input type="text" name="name" autocomplete="name">
```

---

## ۱۳. readonly و disabled

```html
<input type="text" value="ثابت" readonly>
<input type="text" disabled>
```

disabled در submit ارسال نمی‌شود.

---

## ۱۴. value و default

```html
<input type="text" name="country" value="ایران">
```

---

## ۱۵. تمرین کلاسی

1. فرم تماس: name, email, phone
2. fieldset «اطلاعات شخصی»
3. required روی email
4. type مناسب برای هر فیلد

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. خلاصه

- ✅ form + action + method
- ✅ input types + name + id
- ✅ label + fieldset
- ✅ required, min, max, placeholder

**بعد:** [۱۰ — فرم ۲](../session-10/)

---

**قبل:** [۸ — جداول](../session-08/) | **بعد:** [۱۰ — فرم ۲](../session-10/)

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 9 — فرم‌ها و کنترل‌های ورودی | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 8: جدول‌های دسترس‌پذیر
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «فرم‌ها و کنترل‌های ورودی» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **فرم‌ها و کنترل‌های ورودی** یک جزیرهٔ جدا نیست؛ مستقیماً روی ساختار و معنای سند در DOM اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **فرم‌ها و کنترل‌های ورودی** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.

### پوشش شکاف: انتخاب نوع ورودی

نوع مناسب مانند `email`، `tel`، `url`، `date` و `number` هم صفحه‌کلید موبایل مناسب‌تری می‌دهد و هم معنای داده را روشن می‌کند. هر کنترل باید `label` متصل داشته باشد؛ placeholder جای label نیست.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_basic_form.html`](./examples/01_basic_form.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «فرم‌ها و کنترل‌های ورودی» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «فرم‌ها و کنترل‌های ورودی» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **فرم‌ها و کنترل‌های ورودی** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 10: اعتبارسنجی بومی فرم** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

