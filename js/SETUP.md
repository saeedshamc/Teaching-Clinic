# راه‌اندازی محیط JavaScript

نسخهٔ هدف: ECMAScript مدرن روی مرورگرهای فعلی و Node LTS برای اسکریپت بدون DOM.

## مرورگر

Chrome، Edge، Firefox یا Safari به‌روز. DevTools با F12: Console، Sources، Network.

صفحات جلسه را با دوبارکلیک هم می‌توان باز کرد، به‌جز ماژول (`session-11` و `session-24`). برای آن‌ها از افزونهٔ Live Server در VS Code یا این دستور از داخل پوشهٔ جلسه استفاده کنید:

```bash
npx --yes serve .
```

سپس آدرس چاپ‌شده را در مرورگر باز کنید.

## Node.js

از https://nodejs.org نسخهٔ **LTS** نصب کنید.

```bash
node -v
npm -v
```

مثال جلسه ۲۲:

```bash
node js/session-22/hello.js
```

`npm init` برای تمرین شخصی روی ماشین شماست؛ داخل این ریپو اجباری نیست.

## ویرایشگر

[VS Code](https://code.visualstudio.com/) توصیه می‌شود. افزونه‌های مفید: Live Server، ESLint (اختیاری).

## تمرین با کنسول

بسیاری از مثال‌های جلسات ۱–۸ خروجی را در Console می‌نویسند. جلسات ۹–۲۴ معمولاً جعبهٔ خروجی روی خود صفحه دارند؛ Console را هم باز نگه دارید.
