# جلسه 29: متغیرهای CSS (Custom Properties) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- --name و var()
- :root scope
- fallback
- theming
- design tokens

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | معرفی variables |
| 10–25 | :root |
| 25–40 | scope محلی |
| 40–55 | theming |
| 55–70 | tokens |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. CSS Custom Properties

متغیرهای CSS با `--name` تعریف و با `var()` استفاده می‌شوند.

```css
:root {
  --color-primary: #3b82f6;
}
.btn { background: var(--color-primary); }
```

---

## 2. :root

متغیرهای global روی `:root` (معادل html با specificity بالاتر).

---

## 3. fallback

`var(--missing, #000)` — مقدار پیش‌فرض.

---

## 4. scope محلی

```css
.card { --card-bg: #fff; background: var(--card-bg); }
.card.dark { --card-bg: #1e293b; }
```

---

## 5. تم تاریک

```css
@media (prefers-color-scheme: dark) {
  :root { --bg: #0f172a; --text: #f1f5f9; }
}
```

---

## 6. JavaScript

`document.documentElement.style.setProperty('--color', 'red')`

---

## 7. inheritance

custom properties inherit — child می‌تواند override کند.

---

## 8. calc + var

`width: calc(100% - var(--sidebar-width));`

---

## 9. design tokens

رنگ، spacing، font-size — همه token.

---

## 10. vs Sass

native، بدون build step، dynamic.

---

## 11. component vars

هر component متغیرهای خود: `--btn-radius`.

---

## 12. best practices

نام‌گذاری `--color-*`, `--space-*`, `--font-*`.

---

## مرور — مفاهیم کلیدی

در جلسه 29 (متغیرهای CSS (Custom Properties)) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 29. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_var_basics.html](./examples/01_var_basics.html) | پایه |
| [02_local_scope.html](./examples/02_local_scope.html) | scope |
| [03_theme.html](./examples/03_theme.html) | تم |
| [04_design_tokens.html](./examples/04_design_tokens.html) | tokens |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| نام بدون -- | invalid |
| var بدون fallback critical | شکست silent |
| too many globals | سخت maintain |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [28](../session-28/) | **بعد:** [30](../session-30/)
