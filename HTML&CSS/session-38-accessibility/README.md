# جلسه 38: دسترسی‌پذیری (a11y) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

1. تفاوت HTML معنایی و div-soup را بدانید
2. alt مناسب برای تصاویر محتوایی و تزئینی بنویسید
3. ARIA پایه: aria-label، aria-expanded، role را به‌درستی استفاده کنید
4. کنtrast رنگ WCAG AA را محاسبه و تست کنید
5. Skip link و ناوبری با کیبورد پیاده کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا a11y؟ WCAG و مخاطبان |
| 10–25 | HTML معنایی و heading hierarchy |
| 25–40 | alt و figure/figcaption |
| 40–55 | ARIA پایه و focus visible |
| 55–70 | کنtrast و ابزارهای تست |
| 70–85 | تمرین کلاسی |
| 85–90 | جمع‌بندی |

---

## ۱. چرا دسترسی‌پذیری؟

دسترسی‌پذیری (**Accessibility — a11y**) یعنی وب برای **همه** کار کند:
- کاربران نابینا (صفحه‌خوان)
- کاربران فقط-کیبورد
- کم‌بینایان (کنtrast)
- اختلال شنیداری (زیرنویس)

| اصل WCAG | معنی |
|----------|------|
| Perceivable | قابل درک |
| Operable | قابل عمل |
| Understandable | قابل فهم |
| Robust | پایدار و سازگار |

---

## ۲. HTML معنایی

```html
<header>
  <nav aria-label="ناوبری اصلی">...</nav>
</header>
<main id="main-content">
  <section aria-labelledby="sec1">
    <h2 id="sec1">عنوان بخش</h2>
    <article>...</article>
  </section>
</main>
<footer>...</footer>
```

**قوانین heading:** یک `<h1>` — بدون پرش سطح (h1→h3 ❌).

---

## ۳. alt

```html
<img src="chart.png" alt="نمودار فروش: فروردین ۱۰۰، اردیبهشت ۱۵۰">
<img src="decorative.svg" alt="" role="presentation">
```

---

## ۴. ARIA

**قانون اول:** HTML معنایی کافی است → ARIA نزنید.

```html
<button aria-expanded="false" aria-controls="menu">منو</button>
<ul id="menu" hidden>...</ul>
```

---

## ۵. کنtrast

- متن عادی: **۴.۵:۱**
- متن بزرگ: **۳:۱**

```css
a:focus, button:focus {
  outline: 3px solid #2563eb;
  outline-offset: 2px;
}
```

---

## ۶. Skip Link

```html
<a href="#main" class="skip-link">رفتن به محتوا</a>
```

---

## ۷. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| div برای همه‌چیز | semantic tags |
| alt="تصویر" | alt توصیفی |
| outline: none بدون جایگزین | focus واضح |

---

## ۸. تمرین کلاسی

صفحه با skip link، nav، main و سه alt مختلف بسازید.

**تکلیف:** [question.md](./exercises/question.md)

---

## 📁 فایل‌های این جلسه

| فایل | توضیح |
|------|--------|
| [01_semantic_html.html](./examples/01_semantic_html.html) | مثال 1 |
| [02_alt_text.html](./examples/02_alt_text.html) | مثال 2 |
| [03_aria_basics.html](./examples/03_aria_basics.html) | مثال 3 |
| [04_color_contrast.html](./examples/04_color_contrast.html) | مثال 4 |

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

**جلسه قبل:** [۳۷ — pseudo-classes](../session-37/) | **بعد:** [۳۹ — BEM](../session-39-bem/)

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

---

