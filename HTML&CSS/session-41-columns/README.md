# جلسه 41: چیدمان چندستونه (CSS Columns) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

1. column-count و column-width را استفاده کنید
2. column-gap و column-rule تنظیم کنید
3. break-inside برای جلوگیری از شکستن کارت‌ها
4. column-span برای عنوان تمام‌عرض
5. columns را با Grid/Flexbox مقایسه کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور جلسه قبل و اهداف |
| 10–25 | مفاهیم تئوری |
| 25–45 | مثال‌های عملی |
| 45–60 | راهنمای تمرین |
| 60–80 | تمرین مستقل |
| 80–90 | جمع‌بندی و تکلیف |

---

## ۱. columns

```css
.article { column-count: 3; column-gap: 2rem; }
```

---

## ۲. column-width

```css
.masonry-like { columns: 280px 3; column-gap: 1.5rem; }
```

---

## ۳. column-rule

```css
column-rule: 1px solid #ddd;
```

---

## ۴. break-inside

```css
.card { break-inside: avoid; margin-bottom: 1rem; }
```

---

## ۵. column-span

```css
h2 { column-span: all; }
```

---

## ۶. کاربرد

مقالات روزنامه‌ای، گالری، فهرست

---

## 📁 فایل‌های این جلسه

| فایل | توضیح |
|------|--------|
| [01_basic_columns.html](./examples/01_basic_columns.html) | مثال 1 |
| [02_column_width.html](./examples/02_column_width.html) | مثال 2 |
| [03_break_inside.html](./examples/03_break_inside.html) | مثال 3 |
| [04_column_span.html](./examples/04_column_span.html) | مثال 4 |

---

## ✅ چک‌لیست پایان جلسه

- [ ] همه مثال‌ها در مرورگر باز و بررسی شدند
- [ ] DevTools (F12) برای بررسی عناصر استفاده شد
- [ ] تمرین‌های کلاسی انجام شد
- [ ] تکلیف خانه در `exercises/question.md` شروع شد
- [ ] مفاهیم کلیدی در دفترچه یادداشت خلاصه شد

---

## 📚 مطالعه تکمیلی

- [MDN Web Docs](https://developer.mozilla.org/fa/)
- [web.dev](https://web.dev/)

---

**جلسه قبل:** [40 — CSS Reset و normalize](../session-40-css-reset/) | **بعد:** [42 — پروژه: Landing Page](../session-42-landing-page/)

## 💡 نکات تکمیلی

1. همیشه `lang` و `charset` را در `<head>` تنظیم کنید.
2. CSS خارجی را با `<link rel="stylesheet" href="...">` وصل کنید.
3. قبل از commit، صفحه را در حداقل دو مرورگر تست کنید.
4. از نام‌گذاری معنادار برای class و id استفاده کنید.
5. فایل‌های پروژه را در پوشه‌های `css/`، `images/` و `js/` سازماندهی کنید.
6. برای فارسی، `dir="rtl"` و فونت مناسب (Tahoma، Vazirmatn) انتخاب کنید.
7. از shorthand CSS فقط وقتی همه مقادیر را می‌دانید استفاده کنید.
8. Media query را mobile-first بنویسید: `min-width` به‌جای `max-width`.
9. تصاویر بزرگ را فشرده و با `width`/`height` مناسب بارگذاری کنید.
10. Accessibility را از ابتدا در نظر بگیرید، نه در انتها.
11. کامنت HTML/CSS فارسی برای یادگیری و تیم مفید است.
12. Live Server در VS Code برای پیش‌نمایش سریع عالی است.
13. Specificity را قبل از `!important` بررسی کنید.
14. Flexbox برای یک‌بعدی و Grid برای دو‌بعدی مناسب‌تر است.
15. Custom properties (`--var`) برای تم و maintainability عالی‌اند.
16. همیشه `lang` و `charset` را در `<head>` تنظیم کنید.
17. CSS خارجی را با `<link rel="stylesheet" href="...">` وصل کنید.
18. قبل از commit، صفحه را در حداقل دو مرورگر تست کنید.
19. از نام‌گذاری معنادار برای class و id استفاده کنید.
20. فایل‌های پروژه را در پوشه‌های `css/`، `images/` و `js/` سازماندهی کنید.
21. برای فارسی، `dir="rtl"` و فونت مناسب (Tahoma، Vazirmatn) انتخاب کنید.
22. از shorthand CSS فقط وقتی همه مقادیر را می‌دانید استفاده کنید.
23. Media query را mobile-first بنویسید: `min-width` به‌جای `max-width`.
24. تصاویر بزرگ را فشرده و با `width`/`height` مناسب بارگذاری کنید.
25. Accessibility را از ابتدا در نظر بگیرید، نه در انتها.
26. کامنت HTML/CSS فارسی برای یادگیری و تیم مفید است.
27. Live Server در VS Code برای پیش‌نمایش سریع عالی است.
28. Specificity را قبل از `!important` بررسی کنید.
29. Flexbox برای یک‌بعدی و Grid برای دو‌بعدی مناسب‌تر است.
30. Custom properties (`--var`) برای تم و maintainability عالی‌اند.
31. همیشه `lang` و `charset` را در `<head>` تنظیم کنید.
32. CSS خارجی را با `<link rel="stylesheet" href="...">` وصل کنید.
33. قبل از commit، صفحه را در حداقل دو مرورگر تست کنید.
34. از نام‌گذاری معنادار برای class و id استفاده کنید.
35. فایل‌های پروژه را در پوشه‌های `css/`، `images/` و `js/` سازماندهی کنید.
36. برای فارسی، `dir="rtl"` و فونت مناسب (Tahoma، Vazirmatn) انتخاب کنید.
37. از shorthand CSS فقط وقتی همه مقادیر را می‌دانید استفاده کنید.
38. Media query را mobile-first بنویسید: `min-width` به‌جای `max-width`.
39. تصاویر بزرگ را فشرده و با `width`/`height` مناسب بارگذاری کنید.
40. Accessibility را از ابتدا در نظر بگیرید، نه در انتها.
41. کامنت HTML/CSS فارسی برای یادگیری و تیم مفید است.
42. Live Server در VS Code برای پیش‌نمایش سریع عالی است.
43. Specificity را قبل از `!important` بررسی کنید.
44. Flexbox برای یک‌بعدی و Grid برای دو‌بعدی مناسب‌تر است.
45. Custom properties (`--var`) برای تم و maintainability عالی‌اند.
46. همیشه `lang` و `charset` را در `<head>` تنظیم کنید.
47. CSS خارجی را با `<link rel="stylesheet" href="...">` وصل کنید.
48. قبل از commit، صفحه را در حداقل دو مرورگر تست کنید.
49. از نام‌گذاری معنادار برای class و id استفاده کنید.
50. فایل‌های پروژه را در پوشه‌های `css/`، `images/` و `js/` سازماندهی کنید.
51. برای فارسی، `dir="rtl"` و فونت مناسب (Tahoma، Vazirmatn) انتخاب کنید.
52. از shorthand CSS فقط وقتی همه مقادیر را می‌دانید استفاده کنید.
53. Media query را mobile-first بنویسید: `min-width` به‌جای `max-width`.
54. تصاویر بزرگ را فشرده و با `width`/`height` مناسب بارگذاری کنید.
55. Accessibility را از ابتدا در نظر بگیرید، نه در انتها.
56. کامنت HTML/CSS فارسی برای یادگیری و تیم مفید است.
57. Live Server در VS Code برای پیش‌نمایش سریع عالی است.
58. Specificity را قبل از `!important` بررسی کنید.
59. Flexbox برای یک‌بعدی و Grid برای دو‌بعدی مناسب‌تر است.
60. Custom properties (`--var`) برای تم و maintainability عالی‌اند.
61. همیشه `lang` و `charset` را در `<head>` تنظیم کنید.
62. CSS خارجی را با `<link rel="stylesheet" href="...">` وصل کنید.
63. قبل از commit، صفحه را در حداقل دو مرورگر تست کنید.
64. از نام‌گذاری معنادار برای class و id استفاده کنید.
65. فایل‌های پروژه را در پوشه‌های `css/`، `images/` و `js/` سازماندهی کنید.
66. برای فارسی، `dir="rtl"` و فونت مناسب (Tahoma، Vazirmatn) انتخاب کنید.
67. از shorthand CSS فقط وقتی همه مقادیر را می‌دانید استفاده کنید.
68. Media query را mobile-first بنویسید: `min-width` به‌جای `max-width`.
69. تصاویر بزرگ را فشرده و با `width`/`height` مناسب بارگذاری کنید.
70. Accessibility را از ابتدا در نظر بگیرید، نه در انتها.
71. کامنت HTML/CSS فارسی برای یادگیری و تیم مفید است.
72. Live Server در VS Code برای پیش‌نمایش سریع عالی است.
73. Specificity را قبل از `!important` بررسی کنید.
74. Flexbox برای یک‌بعدی و Grid برای دو‌بعدی مناسب‌تر است.
75. Custom properties (`--var`) برای تم و maintainability عالی‌اند.
76. همیشه `lang` و `charset` را در `<head>` تنظیم کنید.
77. CSS خارجی را با `<link rel="stylesheet" href="...">` وصل کنید.
78. قبل از commit، صفحه را در حداقل دو مرورگر تست کنید.
79. از نام‌گذاری معنادار برای class و id استفاده کنید.
80. فایل‌های پروژه را در پوشه‌های `css/`، `images/` و `js/` سازماندهی کنید.
81. برای فارسی، `dir="rtl"` و فونت مناسب (Tahoma، Vazirmatn) انتخاب کنید.
82. از shorthand CSS فقط وقتی همه مقادیر را می‌دانید استفاده کنید.
83. Media query را mobile-first بنویسید: `min-width` به‌جای `max-width`.
84. تصاویر بزرگ را فشرده و با `width`/`height` مناسب بارگذاری کنید.
85. Accessibility را از ابتدا در نظر بگیرید، نه در انتها.
86. کامنت HTML/CSS فارسی برای یادگیری و تیم مفید است.
87. Live Server در VS Code برای پیش‌نمایش سریع عالی است.
88. Specificity را قبل از `!important` بررسی کنید.
89. Flexbox برای یک‌بعدی و Grid برای دو‌بعدی مناسب‌تر است.
90. Custom properties (`--var`) برای تم و maintainability عالی‌اند.
91. همیشه `lang` و `charset` را در `<head>` تنظیم کنید.
92. CSS خارجی را با `<link rel="stylesheet" href="...">` وصل کنید.
93. قبل از commit، صفحه را در حداقل دو مرورگر تست کنید.
94. از نام‌گذاری معنادار برای class و id استفاده کنید.
95. فایل‌های پروژه را در پوشه‌های `css/`، `images/` و `js/` سازماندهی کنید.
96. برای فارسی، `dir="rtl"` و فونت مناسب (Tahoma، Vazirmatn) انتخاب کنید.
97. از shorthand CSS فقط وقتی همه مقادیر را می‌دانید استفاده کنید.

---

