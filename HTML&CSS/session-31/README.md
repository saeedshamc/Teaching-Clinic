# جلسه 31: Transform: translate, rotate, scale, skew — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- translate/rotate/scale/skew
- transform-origin
- combine transforms
- center element
- 3D intro

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | transform intro |
| 10–25 | translate rotate |
| 25–40 | scale skew |
| 40–55 | combine |
| 55–70 | patterns |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## 1. transform

transform: translate, rotate, scale, skew — بدون تغییر flow.

---

## 2. translate

`transform: translateX(10px) translateY(-5px);` یا `translate(10px, -5px)`

---

## 3. rotate

`transform: rotate(45deg);` — transform-origin مهم است.

---

## 4. scale

`transform: scale(1.1);` — hover zoom.

---

## 5. skew

`transform: skewX(-5deg);` — italic effect.

---

## 6. combine

`transform: translate(-50%, -50%) rotate(45deg);`

---

## 7. transform-origin

default center — `transform-origin: top left;`

---

## 8. center absolute

```css
.center { top: 50%; left: 50%; transform: translate(-50%, -50%); }
```

---

## 9. 3D

perspective, rotateX, rotateY — intro.

---

## 10. transition transform

smooth hover scale.

---

## 11. card flip

rotateY 180deg — advanced pattern.

---

## 12. performance

transform GPU-accelerated.

---

## مرور — مفاهیم کلیدی

در جلسه 31 (Transform: translate, rotate, scale, skew) مهم است که مثال‌ها را در مرورگر باز کنید و با DevTools آزمایش کنید.

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

پایان جلسه 31. تکالیف `question.md` را انجام دهید و مثال `04_*` را با تغییرات شخصی گسترش دهید.

---

## فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_translate.html](./examples/01_translate.html) | translate |
| [02_rotate_scale.html](./examples/02_rotate_scale.html) | rotate scale |
| [03_combine.html](./examples/03_combine.html) | combine |
| [04_center_card.html](./examples/04_center_card.html) | center + hover |

---

## اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| top/left center without translate | off-center |
| scale on text only | blur |
| many transforms | GPU overload |

---

## تمرین کلاسی

جزئیات در [question.md](./exercises/question.md)

---

## 📚 مطالعه تکمیلی

- [MDN — CSS](https://developer.mozilla.org/fa/docs/Web/CSS)
- [web.dev — Learn CSS](https://web.dev/learn/css/)

---

**جلسه قبل:** [30](../session-30/) | **بعد:** [32](../session-32/)
