# جلسه 32: Shadows و Gradients — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- box-shadow
- text-shadow
- linear/radial gradient
- gradient text
- elevation

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | box-shadow |
| 10–25 | layers |
| 25–40 | gradients |
| 40–55 | gradient text |
| 55–70 | patterns |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. box-shadow

`box-shadow: offset-x offset-y blur spread color;`

---

## 2. layered shadows

چند shadow برای عمق realistic.

---

## 3. text-shadow

`text-shadow: 2px 2px 4px rgba(0,0,0,.3);`

---

## 4. linear-gradient

`background: linear-gradient(135deg, #667eea, #764ba2);`

---

## 5. radial-gradient

`radial-gradient(circle, #fff, #000);`

---

## 6. conic-gradient

pie charts و color wheels.

---

## 7. gradient text

`background-clip: text; -webkit-text-fill-color: transparent;`

---

## 8. repeating

repeating-linear-gradient stripes.

---

## 9. inset shadow

box-shadow: inset — pressed button.

---

## 10. elevation system

sm, md, lg shadow tokens.

---

## 11. dark mode shadows

shadow تیره‌تر روی bg تیره.

---

## 12. performance

blur بزرگ گران — moderation.

---

## مرور — مفاهیم کلیدی

در جلسه 32 (Shadows و Gradients) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 32. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_box_shadow.html](./examples/01_box_shadow.html) | shadow |
| [02_gradients.html](./examples/02_gradients.html) | gradient |
| [03_gradient_text.html](./examples/03_gradient_text.html) | gradient text |
| [04_elevation.html](./examples/04_elevation.html) | elevation |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| shadow too heavy | dated look |
| no fallback color | old browsers |
| gradient banding | use more stops |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [31](../session-31/) | **بعد:** [33](../session-33/)
