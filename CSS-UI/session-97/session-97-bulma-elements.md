# جلسه 97: عناصر Bulma — button، form، notification، tag، table (2 ساعت)

## اهداف یادگیری
- دکمه‌ها و اصلاح‌کننده‌های `is-*`
- فیلد فرم: `field`، `label`، `control`، `input`
- `notification` و `tag`
- `table` با `is-striped` / `is-hoverable`

---

## 1. دکمه

```html
<button class="button is-primary is-medium">ذخیره</button>
```

حالت‌ها: `is-light`، `is-outlined`، `is-loading`، `is-static`.

---

## 2. فرم

```html
<div class="field">
  <label class="label" for="email">ایمیل</label>
  <div class="control">
    <input class="input" type="email" id="email">
  </div>
  <p class="help is-danger">پیام خطا</p>
</div>
```

---

## 3. Tag و Notification

```html
<span class="tag is-success">فعال</span>
<div class="notification is-warning">هشدار</div>
```

---

## 4. جدول

```html
<table class="table is-fullwidth is-striped is-hoverable">...</table>
```

---

## تمرین‌ها
### آسان
دو tag رنگی.
### متوسط
فرم با دو field و دکمه submit.
### چالش
جدول ۳ ردیفه داخل `box`.

## گام بعد
[جلسه 98: کامپوننت‌های Bulma](../session-98/index.html)
