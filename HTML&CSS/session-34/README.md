# جلسه 34: پروژه — گالری تصویر Responsive — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- CSS Grid gallery
- object-fit
- aspect-ratio
- hover overlay
- lazy loading

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | طراحی gallery |
| 10–25 | Grid auto-fit |
| 25–40 | object-fit |
| 40–55 | hover overlay |
| 55–70 | پروژه |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. پروژه gallery

گالری تصویر responsive با CSS Grid.

---

## 2. object-fit

`object-fit: cover;` — crop بدون distort.

---

## 3. aspect-ratio

`aspect-ratio: 4/3;` — نسبت ثابت.

---

## 4. grid auto-fit

`grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));`

---

## 5. lazy loading

`<img loading="lazy">`

---

## 6. lightbox pattern

overlay + transform scale — CSS only intro.

---

## 7. gap و padding

فاصله یکنواخت بین تصاویر.

---

## 8. hover overlay

::after gradient + opacity transition.

---

## 9. figcaption

semantic figure + figcaption.

---

## 10. masonry intro

grid-row span — pseudo masonry.

---

## 11. performance

sizes attribute — intro.

---

## 12. accessibility

alt text معنادار.

---

## مرور — مفاهیم کلیدی

در جلسه 34 (پروژه — گالری تصویر Responsive) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 34. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_grid_gallery.html](./examples/01_grid_gallery.html) | grid پایه |
| [02_object_fit.html](./examples/02_object_fit.html) | object-fit |
| [03_hover_overlay.html](./examples/03_hover_overlay.html) | overlay |
| [04_full_gallery.html](./examples/04_full_gallery.html) | پروژه کامل |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| no aspect-ratio | layout shift |
| missing alt | a11y |
| fixed height distort | use object-fit |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [33](../session-33/) | **بعد:** [35](../session-35/)
