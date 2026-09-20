# جلسه 18: فرم‌ها، اعتبارسنجی و FormData (2 ساعت)

## 🎯 اهداف یادگیری
- submit را بگیرید و رفرش صفحه را قطع کنید
- مقدار فیلدها را از `FormData` بخوانید
- اعتبارسنجی سمت کلاینت (required، نوع، طول) بنویسید
- بدانید اعتبارسنجی مرورگر جایگزین اعتبارسنجی سرور نیست

---

## 1. submit

```javascript
form.addEventListener("submit", (e) => {
    e.preventDefault();
    const data = new FormData(e.target);
    const email = data.get("email");
});
```

برای `FormData`، فیلدها باید `name` داشته باشند.

---

## 2. اعتبارسنجی بومی

```html
<input name="email" type="email" required minlength="5">
```

`form.checkValidity()` و `input.setCustomValidity("پیام")` برای پیام سفارشی.

Constraint Validation API سریع است؛ برای قوانین ترکیبی (رمز عبور = تکرار) JS لازم است.

---

## 3. خواندن چند مقدار

```javascript
const payload = Object.fromEntries(data.entries());
```

checkbox تیک‌نخورده در FormData نیست. radio فقط گزینهٔ انتخاب‌شده را می‌دهد.

---

## 4. تمرین‌ها

### تمرین 1
فرم نام + ایمیل با preventDefault و نمایش در output.

### تمرین 2
اگر نام خالی است `setCustomValidity`.

### تمرین 3 (چالش)
FormData را به شیء تبدیل کنید و فیلد خالی را قبل از «ارسال» رد کنید.

---

## 5. نکات مهم

1. ✅ name روی input
2. ✅ پیام خطا کنار فیلد، نه فقط alert
3. ❌ به اعتبارسنجی کلاینت به‌عنوان امنیت اعتماد نکنید

---

## 6. خلاصه جلسه

- submit + FormData
- required / type / custom validity
- آمادگی برای ذخیره در جلسه بعد

**آماده برای جلسه بعد: Storage و JSON! 🚀**
