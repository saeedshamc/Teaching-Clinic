# جلسه 5: فرم‌ها ۱ (2 ساعت)

## اهداف یادگیری
- ساختار `form`، `label`، `input`، `button`
- اتصال label به کنترل با `for` / `id`
- انواع رایج: `text`، `email`، `password`، `number`
- ویژگی‌های `required`، `placeholder`، `minlength`

---

## 1. فرم پایه

```html
<form action="#" method="get">
  <label for="email">ایمیل</label>
  <input id="email" name="email" type="email" required>

  <button type="submit">ارسال</button>
  <button type="reset">پاک کردن</button>
</form>
```

- بدون `label` درست، فرم از نظر دسترسی ضعیف است
- `name` برای ارسال داده لازم است
- در تمرین‌های این دوره معمولاً `submit` را با JS جلوی رفتن واقعی صفحه می‌گیریم

---

## 2. انواع ورودی مهم

| type | کاربرد |
|------|--------|
| text | متن آزاد |
| email | ایمیل + اعتبارسنجی مرورگر |
| password | رمز (مخفی) |
| number | عدد |
| tel | تلفن (معنا؛ اعتبارسنجی سخت‌گیرانه نیست) |

---

## اشتباهات رایج
- قرار دادن متن کنار input بدون `label` واقعی
- یکی بودن چند `id`
- دکمه بدون `type` داخل فرم (پیش‌فرض submit است)

---

## تمرین‌ها
### آسان
فرم ورود با ایمیل و رمز.
### متوسط
فیلد سن با `type="number"` و `min`/`max`.
### چالش
پیام خطای سفارشی با `setCustomValidity` (اختیاری).

## گام بعد
[جلسه 6: فرم‌ها ۲](../session-06/index.html)
