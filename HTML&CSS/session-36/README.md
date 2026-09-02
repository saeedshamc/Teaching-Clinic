# جلسه 36: استایل فرم — input، button، focus — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- custom inputs
- appearance none
- focus ring
- checkbox hack
- validation styles

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | form reset |
| 10–25 | text inputs |
| 25–40 | buttons |
| 40–55 | checkbox radio |
| 55–70 | validation |
| 70–85 | تمرین |
| 85–90 | جمع‌bندی |

---

## 1. form styling

input, textarea, select, button — custom appearance.

---

## 2. appearance none

`appearance: none;` — reset browser default.

---

## 3. custom input

border, padding, border-radius, focus ring.

---

## 4. placeholder

::placeholder { color: #94a3b8; }

---

## 5. focus-visible

outline ring برای keyboard — نه mouse.

---

## 6. custom checkbox/radio

hidden input + styled label.

---

## 7. button states

:hover :active :disabled

---

## 8. validation CSS

:valid :invalid :required

---

## 9. floating label pattern

position label inside input.

---

## 10. fieldset legend

grouping semantic.

---

## 11. dark form

variables for form colors.

---

## 12. a11y

label for=, aria-invalid.

---

## مرور — مفاهیم کلیدی

در جلسه 36 (استایل فرم — input، button، focus) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 36. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_custom_inputs.html](./examples/01_custom_inputs.html) | inputs |
| [02_buttons.html](./examples/02_buttons.html) | buttons |
| [03_checkbox_radio.html](./examples/03_checkbox_radio.html) | checkbox |
| [04_form_complete.html](./examples/04_form_complete.html) | فرم کامل |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| no :focus-visible | a11y |
| placeholder as label | bad UX |
| tiny click target | hard to tap |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [35](../session-35/) | **بعد:** [37](../session-37/)
