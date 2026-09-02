# جلسه 30: Transitions و انیمیشن @keyframes — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- transition
- @keyframes
- animation properties
- accessibility motion
- performance

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | transition |
| 10–25 | timing |
| 25–40 | @keyframes |
| 40–55 | animation |
| 55–70 | a11y |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. transition

`transition: property duration timing delay`

```css
.btn { transition: background 0.3s ease; }
.btn:hover { background: #2563eb; }
```

---

## 2. properties

transition-property: all | background | transform | opacity

---

## 3. timing

ease, linear, ease-in-out, cubic-bezier(0.4, 0, 0.2, 1)

---

## 4. @keyframes

```css
@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}
.box { animation: fadeIn 1s ease; }
```

---

## 5. animation props

animation-name, duration, iteration-count, direction, fill-mode

---

## 6. transform + animation

animate transform برای slide و bounce.

---

## 7. prefers-reduced-motion

```css
@media (prefers-reduced-motion: reduce) {
  * { animation: none !important; transition: none !important; }
}
```

---

## 8. loading spinner

@keyframes spin { to { transform: rotate(360deg); } }

---

## 9. stagger

animation-delay برای لیست‌ها.

---

## 10. performance

animate transform و opacity — نه width/height.

---

## 11. multiple keyframes

0%, 50%, 100% steps.

---

## 12. hover transition

UX نرم برای دکمه‌ها و لینک‌ها.

---

## مرور — مفاهیم کلیدی

در جلسه 30 (Transitions و انیمیشن @keyframes) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 30. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_transitions.html](./examples/01_transitions.html) | transition |
| [02_keyframes.html](./examples/02_keyframes.html) | keyframes |
| [03_spinner.html](./examples/03_spinner.html) | spinner |
| [04_motion_a11y.html](./examples/04_motion_a11y.html) | reduced motion |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| transition: all | performance |
| animate width | jank |
| ignore reduced-motion | a11y |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [29](../session-29/) | **بعد:** [31](../session-31/)
