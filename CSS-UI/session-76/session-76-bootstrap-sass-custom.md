# جلسه 76: سفارشی‌سازی Bootstrap با Sass (2 ساعت)

## اهداف یادگیری
- فهم مسیر npm به‌جای فقط CDN
- override کردن متغیرهایی مثل `$primary` قبل از import هسته
- ساخت فایل نمونه `custom.scss`
- دانستن اینکه rebuild واقعی روی ماشین خودتان با پکیج `bootstrap` انجام می‌شود

---

## 1. چرا Sass؟

با CDN رنگ و فاصلهٔ پیش‌فرض Bootstrap را دارید. برای برند خودتان معمولاً:

1. `npm install bootstrap`
2. یک فایل SCSS می‌نویسید که **اول** متغیرها را عوض می‌کند
3. بعد هسته Bootstrap را import می‌کنید
4. با Dart Sass بیلد می‌گیرید و CSS خروجی را به صفحه لینک می‌کنید

ترتیب مهم است: اگر بعد از import متغیر بگذارید، دیر است.

---

## 2. الگوی سفارشی‌سازی

```scss
// custom.scss
$primary: #6f42c1;
$border-radius: 0.75rem;
$font-family-sans-serif: "Vazirmatn", system-ui, sans-serif;

// در پروژه npm واقعی (یکی از این دو سبک):
// @import "bootstrap/scss/bootstrap";
// یا ماژولار با @use مطابق مستندات نسخهٔ شما
```

متغیرهای پرتکرار: `$primary`، `$secondary`، `$success`، `$body-bg`، `$border-radius`، `$spacer`.

لیست کامل در سورس Bootstrap داخل `scss/_variables.scss` است.

---

## 3. CDN در برابر rebuild

| مسیر | مزیت | محدودیت |
|------|------|---------|
| CDN RTL | سریع برای یادگیری | سفارشی‌سازی عمیق سخت |
| npm + Sass | برند دقیق، tree-shake جزئی | نیاز به بیلد |

در این جلسه مفهوم و فایل نمونه را می‌بینید. صفحهٔ دمو هنوز از CDN استفاده می‌کند تا بدون نصب پکیج هم باز شود؛ رنگ primary سفارشی بعد از rebuild واقعی دیده می‌شود.

---

## 4. فایل‌های این جلسه

- `custom.scss` — نمونه متغیرها
- `main.scss` — یادآوری مفهومی (بدون وابستگی کامل به node_modules این ریپو)

روی سیستم خودتان:

```bash
npm install bootstrap sass
npx sass custom.scss:custom.css
```

---

## اشتباهات رایج
- ست کردن متغیر **بعد** از import Bootstrap
- فراموش کردن نسخه RTL بعد از rebuild (یا mixins جهت)
- انتظار تغییر رنگ CDN با عوض کردن فقط `custom.scss` بدون بیلد و لینک CSS جدید

---

## تمرین‌ها
### آسان
`$success` را به یک سبز تیره‌تر تغییر دهید (روی کاغذ/فایل).
### متوسط
`$border-radius-lg` را زیاد کنید و تصور کنید کارت‌ها گردتر می‌شوند.
### چالش
یک پروژه خالی npm بسازید، Bootstrap را نصب و با `$primary` دلخواه بیلد بگیرید.

## خلاصه
سفارشی‌سازی واقعی Bootstrap = متغیر Sass قبل از import + بیلد. CDN برای یادگیری عالی است؛ برای محصول برنددار معمولاً npm می‌روید.

## گام بعد
[جلسه 77: پروژه داشبورد ادمین](../session-77/index.html)
