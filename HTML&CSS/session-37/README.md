# جلسه 37: Pseudo-classهای تعاملی — hover, focus, nth-child — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- :hover :focus :active
- :focus-visible
- :nth-child patterns
- :not()
- touch hover

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | interactive pseudo |
| 10–25 | hover focus |
| 25–40 | active |
| 40–55 | nth-child |
| 55–70 | patterns |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. pseudo-classes interactive

:hover :focus :active :focus-visible

---

## 2. :hover

mouse over — `@media (hover: hover)` for touch.

---

## 3. :focus vs :focus-visible

keyboard-only focus ring.

---

## 4. :active

moment of click — pressed state.

---

## 5. :nth-child

`li:nth-child(odd)` zebra stripes.

---

## 6. :nth-of-type

فقط element type مشخص.

---

## 7. :first-child :last-child

edge items styling.

---

## 8. :not()

`.item:not(:last-child)` border-bottom.

---

## 9. table stripes

tr:nth-child(even) background.

---

## 10. card grid hover

lift + shadow on hover.

---

## 11. touch devices

@media (hover: none) — avoid hover-only info.

---

## 12. combination

:hover:focus-visible for keyboard users.

---

## مرور — مفاهیم کلیدی

در جلسه 37 (Pseudo-classهای تعاملی — hover, focus, nth-child) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 37. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_hover_focus.html](./examples/01_hover_focus.html) | hover focus |
| [02_active_states.html](./examples/02_active_states.html) | active |
| [03_nth_child.html](./examples/03_nth_child.html) | nth-child |
| [04_interactive_ui.html](./examples/04_interactive_ui.html) | UI تعاملی |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| outline: none without replacement | a11y fail |
| hover only info | touch users miss |
| nth-child off-by-one | wrong styling |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [36](../session-36/) | **بعد:** [38](../session-38/)
