# جلسه 81: Flex و Grid در Tailwind (2 ساعت)

## اهداف یادگیری
- چیدمان با `flex`، `items-*`، `justify-*`، `gap-*`
- گرید با `grid` و `grid-cols-*`
- تغییر ستون‌ها در breakpointها (`md:grid-cols-3`)

---

## 1. Flex

```html
<div class="flex items-center justify-between gap-4">
  <span>لوگو</span>
  <nav class="flex gap-3">...</nav>
</div>
```

پرکاربرد: `flex-col`، `flex-wrap`، `grow`، `shrink-0`، `self-center`.

---

## 2. Grid

```html
<div class="grid grid-cols-1 gap-4 md:grid-cols-3">
  <div>۱</div><div>۲</div><div>۳</div>
</div>
```

`col-span-2` برای گسترش یک سلول. `grid-rows-*` کمتر رایج است ولی موجود است.

---

## 3. کی Flex، کی Grid؟

- **Flex**: یک ردیف/ستون، نوار ابزار، تراز آیتم‌ها
- **Grid**: صفحه دوبعدی، کارت‌های هم‌اندازه، داشبورد

---

## اشتباهات رایج
- فراموش کردن `gap` و پر کردن با margin دستی
- `justify-between` روی ظرفی که فقط یک فرزند دارد
- `grid-cols-3` بدون نسخه موبایل (`grid-cols-1`)

---

## تمرین‌ها
### آسان
یک ردیف flex با سه دکمه و `gap-2`.
### متوسط
گرید ۲ ستونه در `sm` و ۴ ستونه در `lg`.
### چالش
هدر: لوگو چپ بصری (در RTL با justify) + منو.

## گام بعد
[جلسه 82: Typography](../session-82/index.html)
