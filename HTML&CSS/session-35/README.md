# جلسه 35: پروژه — جدول قیمت Flexbox/Grid — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- pricing cards
- Flexbox vs Grid
- featured plan
- feature list
- responsive stack

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | طراحی pricing |
| 10–25 | Grid layout |
| 25–40 | card structure |
| 40–55 | featured plan |
| 55–70 | responsive |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. pricing table

جدول قیمت‌گذاری SaaS — Flexbox یا Grid.

---

## 2. Flexbox row

display:flex; gap; flex-wrap برای cards.

---

## 3. Grid equal

grid-template-columns: repeat(3, 1fr)

---

## 4. featured plan

scale + border + shadow برای plan محبوب.

---

## 5. feature list

ul با checkmark ::before.

---

## 6. CTA button

full-width button پایین card.

---

## 7. responsive stack

1 column mobile, 3 desktop.

---

## 8. pricing psychology

middle plan highlighted.

---

## 9. accessibility

heading hierarchy h2 h3.

---

## 10. comparison

Flex vs Grid برای این use case.

---

## 11. variables

token برای رنگ plan.

---

## 12. checklist

responsive, featured, CTA, a11y.

---

## مرور — مفاهیم کلیدی

در جلسه 35 (پروژه — جدول قیمت Flexbox/Grid) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 35. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_flex_pricing.html](./examples/01_flex_pricing.html) | Flexbox |
| [02_grid_pricing.html](./examples/02_grid_pricing.html) | Grid |
| [03_featured_plan.html](./examples/03_featured_plan.html) | featured |
| [04_full_pricing.html](./examples/04_full_pricing.html) | پروژه کامل |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| unequal height cards | use flex column |
| featured breaks mobile | scale down MQ |
| no semantic headings | SEO/a11y |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [34](../session-34/) | **بعد:** [36](../session-36/)
