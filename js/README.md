# دوره JavaScript مدرن

مخاطب: کسی که HTML/CSS مقدماتی دارد و می‌خواهد JavaScript را تا سطح ورود به React جلو ببرد.

پیش‌نیاز ابزار: [SETUP.md](./SETUP.md) — مرورگر مدرن، Node LTS، VS Code.

نقشهٔ مطالعه: جلسات ۱–۸ مبانی و آماده‌سازی React هستند (دست‌نخورده مانده‌اند). جلسات ۹–۲۴ همان قالب را ادامه می‌دهند: رشته، ماژول واقعی، خطا، DOM عمیق، فرم، storage، event loop، ابزار، و یک مینی‌اپ.

جدول کامل: [SYLLABUS.md](./SYLLABUS.md) — برگهٔ سریع: [QUICK_REFERENCE.md](./QUICK_REFERENCE.md)

بعد از جلسهٔ ۲۴، مسیر پیشنهادی: پوشهٔ [`Reactjs/`](../Reactjs/).

## نحوهٔ مطالعه

هر جلسه حدود ۲ ساعت است. ترتیب را بشکنید فقط اگر موضوع را از قبل بلدید.

ساختار جلسات ۱–۸ و ۹–۲۴ یکسان است:

```
session-XX/
  ├── index.html
  ├── script.js
  ├── style.css
  └── session-XX-*.md
```

جلسات ماژولی (۱۱ و ۲۴) را با Live Server یا `npx serve` باز کنید، نه `file://`.

## سرفصل

### جلسات ۱–۸ (موجود)

- [جلسه 1: مبانی JavaScript](./session-01/index.html)
- [جلسه 2: ساختارهای کنترل](./session-02/index.html)
- [جلسه 3: اشیاء و آرایه‌ها](./session-03/index.html)
- [جلسه 4: توابع پیشرفته](./session-04/index.html)
- [جلسه 5: ویژگی‌های ES6+](./session-05/index.html)
- [جلسه 6: JavaScript ناهمگام](./session-06/index.html)
- [جلسه 7: DOM Manipulation](./session-07/index.html)
- [جلسه 8: آماده‌سازی برای React](./session-08/index.html)

### جلسات ۹–۲۴ (ادامه)

- [جلسه 9: رشته‌ها و Unicode](./session-09/index.html)
- [جلسه 10: کپی سطحی و عمیق](./session-10/index.html)
- [جلسه 11: ماژول‌ها در عمل](./session-11/index.html)
- [جلسه 12: کلاس‌ها و پروتوتایپ](./session-12/index.html)
- [جلسه 13: مدیریت خطا](./session-13/index.html)
- [جلسه 14: Promise و زنجیره](./session-14/index.html)
- [جلسه 15: async/await و AbortController](./session-15/index.html)
- [جلسه 16: DOM پیشرفته](./session-16/index.html)
- [جلسه 17: رویدادها و Delegation](./session-17/index.html)
- [جلسه 18: فرم‌ها و FormData](./session-18/index.html)
- [جلسه 19: Storage و JSON](./session-19/index.html)
- [جلسه 20: تایمرها و Event Loop](./session-20/index.html)
- [جلسه 21: Set، Map و Date](./session-21/index.html)
- [جلسه 22: DevTools و npm](./session-22/index.html)
- [جلسه 23: الگوی کار با API](./session-23/index.html)
- [جلسه 24: مینی‌اپ Notes](./session-24/index.html)

فهرست کارت‌ها: [index.html](./index.html)

## هدف

پس از ۲۴ جلسه می‌توانید اسکریپت مرورگر بنویسید، داده را در DOM و storage نگه دارید، fetch را با خطا و لغو مدیریت کنید، و با ذهنیت «داده جدا از UI» وارد React شوید.
