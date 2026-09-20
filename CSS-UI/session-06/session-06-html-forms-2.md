# جلسه 6: فرم‌ها ۲ (2 ساعت)

## اهداف یادگیری
- `select` / `option` و `textarea`
- `checkbox` و `radio` با گروه‌بندی درست
- `fieldset` و `legend` برای دسته‌بندی کنترل‌ها

---

## 1. Select و Textarea

```html
<label for="role">نقش</label>
<select id="role" name="role">
  <option value="student">دانشجو</option>
  <option value="teacher">مدرس</option>
</select>

<label for="bio">درباره شما</label>
<textarea id="bio" name="bio" rows="4"></textarea>
```

---

## 2. Checkbox و Radio

```html
<label><input type="checkbox" name="topics" value="css"> CSS</label>

<label><input type="radio" name="level" value="beginner" checked> مبتدی</label>
<label><input type="radio" name="level" value="pro"> حرفه‌ای</label>
```

- checkbox: چند انتخاب
- radio: یک انتخاب از گروه با `name` یکسان

---

## 3. Fieldset

```html
<fieldset>
  <legend>علاقه‌مندی‌ها</legend>
  ...
</fieldset>
```

---

## تمرین‌ها
### آسان
یک select برای شهر بسازید.
### متوسط
گروه radio برای سطح + دو checkbox موضوع.
### چالش
مقدارهای انتخاب‌شده را هنگام submit در خروجی نشان دهید.

## گام بعد
[جلسه 7: ساختار صفحه](../session-07/index.html)
