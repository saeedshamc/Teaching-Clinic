# جلسه 85: فرم‌ها با Utility در Tailwind (2 ساعت)

## اهداف یادگیری
- ساخت فرم بدون کامپوننت آماده Bootstrap
- استایل `label`، `input`، `textarea`، `select`
- حالت focus و پیام خطا با utility
- دکمه submit دسترس‌پذیر

---

## 1. الگوی فیلد

```html
<label class="block text-sm font-medium text-slate-700" for="email">ایمیل</label>
<input
  id="email"
  type="email"
  class="mt-1 w-full rounded-lg border border-slate-300 px-3 py-2 shadow-sm focus:border-sky-500 focus:outline-none focus:ring-2 focus:ring-sky-200"
>
```

نکته‌ها:
- `w-full` داخل ظرف محدود (`max-w-md`)
- ring در focus به‌جای outline پیش‌فرض مرورگر (با جایگزین واضح)

---

## 2. خطا

```html
<input class="border-rose-500 focus:ring-rose-200" aria-invalid="true" aria-describedby="email-err">
<p id="email-err" class="mt-1 text-sm text-rose-600">ایمیل معتبر نیست.</p>
```

---

## 3. چیدمان

`space-y-4` روی فرم، یا Grid دو ستونه در `md` برای نام/نام‌خانوادگی.

---

## اشتباهات رایج
- فراموش کردن `for` / `id` روی label
- دکمه بدون `type="submit"` داخل فرم‌های تو در تو
- کنتراست ضعیف placeholder

---

## تمرین‌ها
### آسان
یک textarea با `rows="4"` استایل‌شده.
### متوسط
فرم دو ستونه در md.
### چالش
حالت خطای مصنوعی روی یک فیلد با `aria-invalid`.

## گام بعد
[جلسه 86: الگوهای Layout](../session-86/index.html)
