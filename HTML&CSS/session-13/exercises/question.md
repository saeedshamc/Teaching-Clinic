# تمرین‌های جلسه ۱۳ — معرفی CSS

## کلاسی (۳۰ دقیقه)

### ۱. کارت خوش‌آمدگویی با Internal CSS
صفحه HTML بسازید با:
- `lang="fa"` و `dir="rtl"`
- تگ `<style>` در `<head>`
- عنوان، پاراگراف و یک دکمه استایل‌دهی‌شده

### ۲. جداسازی External
همان طراحی را با فایل `style.css` جداگانه پیاده کنید و با `<link>` وصل کنید.

### ۳. تشخیص روش
در کد زیر هر خط را inline / internal / external مشخص کنید:
```html
<head>
  <style>body { margin: 0; }</style>
  <link rel="stylesheet" href="main.css">
</head>
<body>
  <p style="color: red;">سلام</p>
</body>
```

---

## خانه (۴۵ دقیقه)

### ۴. صفحه درباره من
- فایل `about.html` + `about.css`
- بخش header، main، footer
- حداقل ۵ rule در CSS خارجی

### ۵. تحقیق
چرا inline CSS برای accessibility و maintenance مشکل‌ساز است؟ (۵ خط)

---

پاسخ نمونه: [exercise_01.html](./exercise_01.html) | [exercise_02.html](./exercise_02.html)
