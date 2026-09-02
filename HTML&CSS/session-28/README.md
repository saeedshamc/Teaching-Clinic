# جلسه 28: گردش کار Mobile-First — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- mobile-first vs desktop-first
- min-width media queries
- progressive enhancement
- refactor navbar
- touch-friendly UI

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مفهوم mobile-first |
| 10–25 | min-width |
| 25–40 | typography و spacing |
| 40–55 | navigation pattern |
| 55–70 | refactor |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. mobile-first چیست؟

استایل پایه برای موبایل؛ با `min-width` برای صفحات بزرگ‌تر گسترش می‌یابد.

```css
/* پایه: موبایل */
.nav { flex-direction: column; }
@media (min-width: 768px) {
  .nav { flex-direction: row; }
}
```

---

## 2. چرا mobile-first؟

• Performance: CSS کمتر برای موبایل
• Progressive enhancement
• Google mobile-first indexing

---

## 3. min-width pattern

```css
.container { padding: 1rem; }
@media (min-width: 768px) { .container { padding: 2rem; } }
@media (min-width: 1024px) { .container { max-width: 1100px; margin: 0 auto; } }
```

---

## 4. refactor desktop-first

Desktop-first با max-width را به min-width تبدیل کنید — پایه ساده‌ترین layout باشد.

---

## 5. typography mobile-first

```css
h1 { font-size: 1.75rem; }
@media (min-width: 768px) { h1 { font-size: 2.5rem; } }
```

---

## 6. touch targets

دکمه‌ها حداقل 44×44px — padding کافی در موبایل.

---

## 7. content priority

مهم‌ترین محتوا اول در HTML — در موبایل اول دیده شود.

---

## 8. images

```css
img { max-width: 100%; height: auto; display: block; }
```

---

## 9. navigation

منوی عمودی پایه → افقی از 768px.

---

## 10. spacing scale

فاصله‌ها mobile کوچک → desktop بزرگ‌تر.

---

## 11. testing workflow

1. طراحی 320px 2. گسترش 768 3. 1024 4. تست touch

---

## 12. checklist

- viewport meta
- بدون horizontal scroll
- readable font 16px+
- tap targets

---

## مرور — مفاهیم کلیدی

در جلسه 28 (گردش کار Mobile-First) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 28. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_mobile_base.html](./examples/01_mobile_base.html) | پایه موبایل |
| [02_min_width.html](./examples/02_min_width.html) | min-width |
| [03_typography.html](./examples/03_typography.html) | تایپوگرافی |
| [04_full_workflow.html](./examples/04_full_workflow.html) | workflow کامل |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| desktop-first habit | CSS اضافه |
| breakpoint too many | پیچیدگی |
| small tap targets | UX ضعیف |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [27](../session-27/) | **بعد:** [29](../session-29/)
