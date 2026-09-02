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
