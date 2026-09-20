# جلسه 83: Responsive Prefixes در Tailwind (2 ساعت)

## اهداف یادگیری
- تفکر mobile-first
- پیشوندها: `sm:` `md:` `lg:` `xl:` `2xl:`
- مخفی/نمایش با `hidden md:block`
- تغییر چیدمان و تایپوگرافی در عرض‌های مختلف

---

## 1. Mobile-first

کلاس بدون پیشوند = موبایل (و بالاتر، مگر override شود).

```html
<div class="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-4">
```

یعنی: ۱ ستون → از md دو ستون → از lg چهار ستون.

---

## 2. Breakpointهای پیش‌فرض (تقریبی)

| پیشوند | حداقل عرض |
|--------|-----------|
| `sm` | 640px |
| `md` | 768px |
| `lg` | 1024px |
| `xl` | 1280px |
| `2xl` | 1536px |

---

## 3. الگوهای رایج

```html
<!-- منوی دسکتاپ -->
<nav class="hidden md:flex gap-4">...</nav>
<button class="md:hidden">منو</button>

<!-- تیتر -->
<h1 class="text-2xl md:text-4xl lg:text-5xl">...</h1>
```

---

## اشتباهات رایج
- طراحی اول دسکتاپ و بعد جنگ با پیشوندها
- `hidden` بدون نسخهٔ نمایش در md
- تست نکردن با DevTools عرض‌های میانی

---

## تمرین‌ها
### آسان
پاراگراف فقط از `lg` به بالا دیده شود.
### متوسط
سایدبار `hidden lg:block` و محتوا full-width در موبایل.
### چالش
سه حالت پدینگ: `p-4 md:p-8 xl:p-12`.

## گام بعد
[جلسه 84: States](../session-84/index.html)
