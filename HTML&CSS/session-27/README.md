# جلسه 27: Media Queries و اصول Responsive — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- درک responsive design و viewport
- نوشتن media query با min-width و max-width
- breakpointهای رایج: 480, 768, 1024, 1280
- تست با DevTools device mode
- ترکیب media query با Flexbox و Grid

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | responsive چیست؟ |
| 10–25 | meta viewport |
| 25–40 | syntax media query |
| 40–55 | min-width vs max-width |
| 55–70 | breakpoint strategy |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. responsive design

طراحی واکنش‌گرا یعنی layout با عرض viewport تغییر کند.

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

بدون این meta، موبایل صفحه را zoom out می‌کند.

---

## ۲. syntax پایه

```css
@media (max-width: 768px) {
  .container { flex-direction: column; }
}

@media (min-width: 769px) {
  .sidebar { display: block; }
}
```

---

## ۳. breakpointهای رایج

| نام | عرض | دستگاه |
|-----|-----|--------|
| mobile | < 480px | موبایل کوچک |
| tablet | 768px | تبلت |
| laptop | 1024px | لپ‌تاپ |
| desktop | 1280px+ | دسکتاپ |

---

## ۴. mobile vs desktop first

Desktop-first: `max-width` — استایل پایه دسکتاپ، override موبایل.
Mobile-first: `min-width` — پایه موبایل، override بزرگ‌تر (جلسه ۲۸).

---

## ۵. media features

```css
@media (orientation: landscape) { }
@media (prefers-color-scheme: dark) { }
@media (prefers-reduced-motion: reduce) { }
```

---

## ۶. container responsive

```css
.container {
  width: min(100% - 2rem, 1100px);
  margin-inline: auto;
}
```

---

## ۷. hidden/visible

```css
.mobile-only { display: none; }
@media (max-width: 768px) {
  .mobile-only { display: block; }
  .desktop-only { display: none; }
}
```

---

## ۸. تست

1. Chrome DevTools → Ctrl+Shift+M
2. عرض‌های 320, 375, 768, 1024
3. zoom 200%
4. چاپ: `@media print`

---

## ۹. Flexbox + MQ

```css
.cards { display: flex; flex-wrap: wrap; gap: 1rem; }
.card { flex: 1 1 280px; }
```

---

## ۱۰. Grid + MQ

```css
.grid { display: grid; gap: 1rem; }
@media (min-width: 600px) { .grid { grid-template-columns: 1fr 1fr; } }
@media (min-width: 900px) { .grid { grid-template-columns: repeat(3, 1fr); } }
```

---

## ۱۱. واحد rem در MQ

breakpoint بر px است؛ font-size داخل MQ با rem انعطاف‌پذیرتر است.

---

## ۱۲. range syntax

```css
@media (768px <= width <= 1024px) { /* تبلت landscape */ }
```

---

## مرور — مفاهیم کلیدی

در جلسه 27 (Media Queries و اصول Responsive) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 27. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_media.html](./examples/01_basic_media.html) | media query ساده |
| [02_breakpoints.html](./examples/02_breakpoints.html) | چند breakpoint |
| [03_layout_shift.html](./examples/03_layout_shift.html) | تغییر layout |
| [04_responsive_grid.html](./examples/04_responsive_grid.html) | Grid واکنش‌گرا |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| فراموش viewport meta | موبایل zoom out |
| فقط یک breakpoint | شکست بین اندازه‌ها |
| px ثابت everywhere | عدم انعطاف |
| !important در MQ | cascade شکسته |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [26](../session-26/) | **بعد:** [28](../session-28/)
