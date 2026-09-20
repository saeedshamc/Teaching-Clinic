# جلسه 82: Typography و رنگ در Tailwind (2 ساعت)

## اهداف یادگیری
- اندازه متن: `text-sm` تا `text-5xl`
- وزن و ارتفاع خط: `font-*`، `leading-*`
- پالت رنگ: `text-slate-*`، `bg-*`، `border-*`
- تراز و truncate

---

## 1. متن

```html
<h1 class="text-4xl font-bold tracking-tight text-slate-900">عنوان</h1>
<p class="mt-3 text-base leading-7 text-slate-600">بدنه</p>
```

| کلاس | نقش |
|------|-----|
| `font-medium` / `semibold` / `bold` | وزن |
| `leading-6` / `leading-relaxed` | ارتفاع خط |
| `tracking-tight` | فاصله حروف |
| `text-center` / `text-right` | تراز |
| `truncate` | یک‌خطی با … |

---

## 2. رنگ

مقیاس ۵۰ (خیلی روشن) تا ۹۵۰ (خیلی تیره). خانواده‌های رایج: `slate`، `gray`، `sky`، `emerald`، `rose`، `amber`.

```html
<span class="text-emerald-700 bg-emerald-50 px-2 py-1 rounded">موفق</span>
```

---

## اشتباهات رایج
- `text-black` روی پس‌زمینه تیره
- اندازه‌های خیلی بزرگ بدون نسخه موبایل (`text-2xl md:text-5xl`)
- کنتراست ضعیف (مثلاً `text-slate-400` روی سفید برای متن اصلی)

---

## تمرین‌ها
### آسان
یک نقل‌قول با `text-xl italic text-slate-500`.
### متوسط
سلسله‌مراتب: h1، h2، p، small.
### چالش
Badge رنگی برای «جدید» و «تمام‌شده».

## گام بعد
[جلسه 83: Responsive](../session-83/index.html)
