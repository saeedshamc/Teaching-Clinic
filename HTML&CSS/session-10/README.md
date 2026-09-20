# جلسه ۱۰: فرم‌ها — بخش ۲: select، textarea، checkbox، radio (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- `<select>`, `<option>`, `<optgroup>` بسازید
- `<textarea>` برای متن چندخطی بنویسید
- checkbox (چند انتخاب) و radio (یک انتخاب) را پیاده کنید
- validation پیشرفته‌تر (pattern, minlength) را به کار ببرید
- تفاوت name یکسان برای radio و checkbox را بدانید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | بازخوانی فرم ۱ |
| 10–25 | select و option |
| 25–40 | textarea |
| 40–55 | checkbox |
| 55–70 | radio |
| 70–85 | validation |
| 85–90 | تکلیف |

---

## ۱. select

```html
<label for="city">شهر:</label>
<select id="city" name="city" required>
    <option value="">— انتخاب —</option>
    <option value="teh">تهران</option>
    <option value="isf">اصفهان</option>
</select>
```

### optgroup

```html
<select name="course">
    <optgroup label="Front-end">
        <option value="html">HTML</option>
        <option value="css">CSS</option>
    </optgroup>
    <optgroup label="Back-end">
        <option value="node">Node.js</option>
    </optgroup>
</select>
```

---

## ۲. textarea

```html
<label for="msg">پیام:</label>
<textarea id="msg" name="msg" rows="6" cols="40" maxlength="500" placeholder="..."></textarea>
```

محتوای بین تگ‌ها مقدار اولیه است.

---

## ۳. checkbox

```html
<label>
    <input type="checkbox" name="skill" value="html"> HTML
</label>
<label>
    <input type="checkbox" name="skill" value="css"> CSS
</label>
```

**name یکسان** — چند مقدار ارسال می‌شود.

---

## ۴. radio

```html
<label>
    <input type="radio" name="gender" value="m" required> مرد
</label>
<label>
    <input type="radio" name="gender" value="f"> زن
</label>
```

**name یکسان** — فقط **یکی** انتخاب.

---

## ۵. checked و selected

```html
<input type="checkbox" name="news" value="1" checked>
<option value="teh" selected>تهران</option>
```

---

## ۶. validation

```html
<input type="password" minlength="8" required>
<input type="email" pattern="[^@\s]+@[^@\s]+\.[^@\s]+">
<input type="checkbox" name="terms" required>
```

---

## ۷. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_select_textarea.html](./examples/01_select_textarea.html) | select/textarea |
| [02_checkbox.html](./examples/02_checkbox.html) | checkbox |
| [03_radio.html](./examples/03_radio.html) | radio |
| [04_form_validation.html](./examples/04_form_validation.html) | validation |

---

## ۸. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| radio با name مختلف | name یکسان |
| checkbox تک بدون value | value صریح |
| option بدون value | value = متن یا کد |
| textarea بسته نشده | `</textarea>` |
| validation فقط JS | HTML5 + JS بعداً |

---

## ۹. multiple select

```html
<select name="langs" multiple size="4">
    <option value="html">HTML</option>
    <option value="css">CSS</option>
</select>
```

---

## ۱۰. datalist (اختیاری)

```html
<input list="browsers" name="browser">
<datalist id="browsers">
    <option value="Chrome">
    <option value="Firefox">
</datalist>
```

Autocomplete پیشنهادی.

---

## ۱۱. فرم کامل — الگو

```html
<form>
    <fieldset>
        <legend>ثبت‌نام دوره</legend>
        <label for="level">سطح:</label>
        <select id="level" name="level" required>...</select>

        <fieldset>
            <legend>علاقه‌ها</legend>
            <!-- checkboxها -->
        </fieldset>

        <label for="bio">درباره شما:</label>
        <textarea id="bio" name="bio"></textarea>

        <label>
            <input type="checkbox" name="terms" required> قوانین
        </label>
    </fieldset>
    <button type="submit">ثبت</button>
</form>
```

---

## ۱۲. تمرین کلاسی

1. select شهر + textarea پیام
2. radio سطح تجربه
3. checkbox ۳ skill
4. checkbox terms required

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۳. خلاصه

- ✅ select/option/optgroup
- ✅ textarea
- ✅ checkbox چندتایی — radio تک‌انتخابی
- ✅ validation pattern/minlength

**بعد:** [۱۱ — HTML5 معنایی](../session-11/)

---

**قبل:** [۹ — فرم ۱](../session-09/) | **بعد:** [۱۱ — semantic](../session-11/)

---

## راهنمای یادگیری خودآموز

### عنوان، شماره و زمان

**جلسهٔ 10 — اعتبارسنجی بومی فرم | ۹۰ دقیقه**

### پیش‌نیازها

- مفاهیم جلسهٔ 9: فرم‌ها و کنترل‌های ورودی
- توانایی باز کردن فایل HTML در مرورگر و استفادهٔ پایه از DevTools
- اجرای مثال‌های جلسه پیش از شروع تمرین

### اهداف قابل‌اندازه‌گیری

در پایان جلسه باید بتوانید:

1. مفهوم «اعتبارسنجی بومی فرم» را با یک مثال و واژگان فارسی/English توضیح دهید.
2. نمونهٔ موجود را بدون کپی کورکورانه بازسازی و نتیجه را در مرورگر بررسی کنید.
3. حداقل سه خطای رایج این موضوع را با DevTools تشخیص دهید.
4. راه‌حل خود را از نظر معنا، خوانایی، responsive بودن و accessibility ارزیابی کنید.

### چرا این جلسه مهم است؟

موضوع **اعتبارسنجی بومی فرم** یک جزیرهٔ جدا نیست؛ مستقیماً روی ساختار و معنای سند در DOM اثر می‌گذارد. یادگیری آن به‌صورت تصمیم‌محور باعث می‌شود به‌جای حفظ کردن تگ یا property، بتوانید برای محتوای واقعی راه‌حل درست انتخاب کنید و دلیل انتخابتان را توضیح دهید.

### مفهوم عمیق: از کد تا نتیجه

مرورگر کد را از بالا به پایین parse می‌کند، آن را به ساختارهای داخلی مانند DOM و CSSOM تبدیل می‌کند و سپس style، layout و paint را انجام می‌دهد. هنگام کار با **اعتبارسنجی بومی فرم** همیشه سه پرسش داشته باشید: «معنای این کد چیست؟»، «مرورگر چگونه آن را محاسبه می‌کند؟» و «کاربر با keyboard، موبایل یا صفحه‌خوان چه تجربه‌ای دارد؟». این مدل ذهنی از آزمون‌وخطای تصادفی جلوگیری می‌کند.

### پوشش شکاف: HTML Constraint Validation

`required`، `minlength`، `maxlength`، `min`، `max`، `step` و `pattern` قرارداد سمت کاربر را بیان می‌کنند. اعتبارسنجی HTML تجربه را بهتر می‌کند، اما جای اعتبارسنجی امن سمت سرور را نمی‌گیرد.


### سه اشتباه رایج و اصلاح آن‌ها

1. **کپی‌کردن بدون پیش‌بینی نتیجه:** پیش از refresh بنویسید انتظار دارید چه تغییری ببینید؛ سپس با DevTools اختلاف را پیدا کنید.
2. **حل ظاهر و نادیده‌گرفتن معنا:** ابتدا ساختار، ترتیب محتوا و دسترسی با keyboard را درست کنید؛ بعد سراغ polish بصری بروید.
3. **تغییرهای بزرگ و تست دیرهنگام:** هر بار یک تغییر کوچک انجام دهید و در عرض باریک و عریض بررسی کنید.
4. **استفاده از workaround فوری:** پیش از `!important`، selector پیچیده یا مقدار جادویی، علت اصلی را در Computed/Box Model بیابید.

### مثال گام‌به‌گام

فایل [`examples/01_select_textarea.html`](./examples/01_select_textarea.html) را مبنا قرار دهید:

1. فایل را در مرورگر باز کنید و بدون دیدن کد، اجزای صفحه را نام ببرید.
2. سورس را بخوانید و هر بخش مرتبط با «اعتبارسنجی بومی فرم» را با یک کامنت مشخص کنید.
3. یک مقدار یا عنصر را تغییر دهید و نتیجهٔ مورد انتظار را قبل از refresh یادداشت کنید.
4. با Inspect، مقدار نهایی و دلیل اعمال‌شدن آن را بررسی کنید.
5. تغییر را با keyboard و عرض ۳۲۰ پیکسل آزمایش کنید.
6. کد آزمایشی را به نسخه‌ای خوانا با نام‌گذاری معنادار refactor کنید.

### الگوی بهتر (Better Pattern)

چرخهٔ **content → structure → style → test → refactor** را رعایت کنید: با محتوای واقعی شروع کنید، کمترین ساختار معنایی لازم را بسازید، style را از ساده به پیچیده اضافه کنید، در هر مرحله تست کنید و فقط پس از کارکرد صحیح abstraction بسازید. این الگو از over-engineering و CSS شکننده جلوگیری می‌کند.

### تمرین‌ها

#### تمرین آسان

مثال اصلی را از حافظه در یک فایل تازه بازسازی کنید و سه تصمیم خود دربارهٔ «اعتبارسنجی بومی فرم» را در کامنت‌ها توضیح دهید. سپس یک screenshot یا یادداشت از بررسی DevTools ثبت کنید.

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

اکنون باید بتوانید **اعتبارسنجی بومی فرم** را توضیح دهید، در نمونه تشخیص دهید، به‌صورت مرحله‌ای پیاده کنید و با ابزار مرورگر بسنجید. گام بعدی **جلسهٔ 11: HTML معنایی** است؛ پیش از رفتن، تمرین چالشی را نگه دارید تا در جلسهٔ بعد روی همان خروجی توسعه دهید.

