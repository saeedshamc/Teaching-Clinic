# جلسه 33: Viewport Units (vh/vw) و calc() — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- vw vh vmin vmax
- calc()
- full viewport layouts
- mobile vh issues
- fluid sizing

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | viewport units |
| 10–25 | vh hero |
| 25–40 | calc |
| 40–55 | fluid |
| 55–70 | patterns |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. viewport units

vw = 1% viewport width, vh = 1% viewport height.

---

## 2. vmin vmax

vmin کوچک‌ترین بعد، vmax بزرگ‌ترین.

---

## 3. vh problem mobile

mobile browser address bar — 100vh گاهی overflow.

---

## 4. dvh svh lvh

dynamic viewport units — مدرn.

---

## 5. calc()

`width: calc(100% - 2rem);` — mix units.

---

## 6. calc nested

calc(100% - var(--sidebar))

---

## 7. full screen hero

min-height: 100vh یا 100dvh

---

## 8. fluid typography

font-size: calc(1rem + 1vw)

---

## 9. grid calc

grid-template-columns: calc(50% - 1rem) calc(50% - 1rem)

---

## 10. padding calc

responsive spacing.

---

## 11. clamp preview

clamp در جلسات بعد — related.

---

## 12. best practices

fallback for old browsers.

---

## مرور — مفاهیم کلیدی

در جلسه 33 (Viewport Units (vh/vw) و calc()) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

---

## تمرین گام‌به‌گام

1. فایل `01_*.html` را باز کنید.
2. CSS را خط‌به‌خط بخوانید.
3. یک property را تغییر دهید و نتیجه را ببینید.
4. تمرین `exercise_01.html` را بدون نگاه به پاسخ انجام دهید.

---

## سوالات کلاسی

- تفاوت این مبحث با جلسه قبل چیست؟
- چه اشتباه رایجی دیدید؟
- در پروژه واقعی کجا استفاده می‌شود؟

---

## چک‌لیست تحویل

- [ ] HTML معتبر
- [ ] viewport meta
- [ ] کامنت فارسی در CSS
- [ ] تست موبایل 320px
- [ ] keyboard navigation

---

## ارتباط با پروژه نهایی

مهارت‌های این جلسه در پروژه‌های responsive، landing page و dashboard کاربرد مستقیم دارند.

---

## منابع ویدیویی

- [MDN Learn](https://developer.mozilla.org/en-US/docs/Learn)
- [web.dev/learn/css](https://web.dev/learn/css/)

---

## واژگان انگلیسی

| English | فارسی |
|---------|--------|
| responsive | واکنش‌گرا |
| breakpoint | نقطه شکست |
| viewport | نمای viewport |

---

## نکات حرفه‌ای

- کد CSS را modular نگه دارید.
- از naming convention ثابت استفاده کنید.
- قبل از deploy روی دستگاه واقعی تست کنید.

---

## Debug

اگر layout شکست:
1. DevTools → Computed
2. box model را بررسی کنید
3. media query فعال را در Styles ببینید

---

## جمع‌بندی نهایی

پایان جلسه 33. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_vh_vw.html](./examples/01_vh_vw.html) | vh vw |
| [02_calc.html](./examples/02_calc.html) | calc |
| [03_hero_full.html](./examples/03_hero_full.html) | full hero |
| [04_fluid.html](./examples/04_fluid.html) | fluid type |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| 100vh mobile scroll | address bar |
| calc spacing wrong | syntax |
| vw font too small | min size needed |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [32](../session-32/) | **بعد:** [34](../session-34/)
