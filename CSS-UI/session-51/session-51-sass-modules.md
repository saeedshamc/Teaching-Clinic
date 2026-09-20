# جلسه 51: ماژول‌های Sass با @use و @forward (2 ساعت)

## اهداف یادگیری
- بارگذاری فایل با `@use`
- تفاوت با `@import` قدیمی
- آشنایی با `@forward` برای API پکیج
- جلوگیری از آلودگی namespace سراسری

---

## 1. چرا @use؟

`@import` قدیمی:
- چندبار ممکن بود لود شود
- همه چیز را در فضای سراسری می‌ریخت

`@use` مدرن:
- هر فایل یک‌بار به‌عنوان ماژول
- اعضا با namespace در دسترس‌اند (مگر `as *`)

```scss
@use "colors" as *;
// حالا $brand از _colors.scss در دسترس است
```

یا:

```scss
@use "colors";
.btn { background: colors.$brand; }
```

---

## 2. نام فایل partial

فایل `_colors.scss` را این‌طور استفاده می‌کنید:

```scss
@use "colors";
```

آندراسکور یعنی «مستقیم کامپایل نکن؛ ماژول باش».

---

## 3. @forward

وقتی یک پوشهٔ abstracts دارید و می‌خواهید یک ورودی تمیز بدهید:

```scss
// abstracts/_index.scss
@forward "colors";
@forward "spacing";
```

بعد: `@use "../abstracts" as *;`

---

## تمرین‌ها
### آسان
`_colors.scss` بسازید و با `@use` مصرف کنید.
### متوسط
یک بار با `as *` و یک بار با namespace مقایسه کنید.
### چالش
یک `_index.scss` با `@forward` اسکلت بزنید.

## گام بعد
[جلسه 52: Mixin](../session-52/index.html)
