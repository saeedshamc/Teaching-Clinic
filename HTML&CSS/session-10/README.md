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
