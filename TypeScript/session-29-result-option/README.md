# جلسه 29: Result و Option — بدون فریم‌ورک (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۲ و ۱۷.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. Result<T,E> بسازید
2. Option/Maybe سبک بنویسید
3. map روی Result را درک کنید
4. بگویید کی throw بهتر است
5. با exhaustive مصرف کنید

## چرا مهم است؟
خطای قابل‌انتظار را به‌جای throw پنهان، در نوع برمی‌گردانید تا UI مجبور به handle شود.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | انگیزه و مسئله |
| ۲۰–۴۵ | مفاهیم پایه |
| ۴۵–۷۵ | مفاهیم میانی + مثال |
| ۷۵–۱۰۰ | دام‌ها و الگو |
| ۱۰۰–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### 1. Result

```ts
type Result<T, E = string> =
  | { ok: true; value: T }
  | { ok: false; error: E };
```

### 2. Option

```ts
type Option<T> = { some: true; value: T } | { some: false };
```

### 3. سازنده

ok(value) / err(error) / some / none

### 4. کی Result؟

parse، قوانین دامنه، I/O قابل‌انتظار.

### 5. کی throw؟

باگ برنامه‌نویس، نقض invariant.

### 6. map ذهنی

اگر ok بود تابع روی value؛ وگرنه error را عبور بده.

### 7. ترکیب با async

Promise<Result<T>> رایج است.

### 8. زیاده‌روی

هر تابع را Result کردن خسته‌کننده است؛ مرزها را انتخاب کنید.

---

## اشتباه‌های رایج

1. **Result برای همه چیز.**
2. **فراموش exhaustive.**
3. **error: any.**
4. **قاطی throw و Result بدون قرارداد.**
5. **Option تو در تو بی‌نام.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-29-result-option/examples/01-result.ts
npx tsx session-29-result-option/examples/02-option.ts
npx tsx session-29-result-option/examples/03-map.ts
npx tsx session-29-result-option/examples/04-when.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-result.ts](./examples/01-result.ts) | Result |
| [02-option.ts](./examples/02-option.ts) | Option |
| [03-map.ts](./examples/03-map.ts) | map |
| [04-when.ts](./examples/04-when.ts) | کی کدام |

## الگوی بهتر

مرز parse/I/O با Result؛ invariant با throw.

## تمرین‌ها

### آسان
parseEven(n): Result<number>

### چالشی
mapResult کمکی.

## راهنمای حل

آسان: اگر زوج ok وگرنه err

چالش: if (!r.ok) return r; return { ok:true, value: fn(r.value) }

## خودآزمایی

1. Result چیست؟
2. Option؟
3. کی throw؟
4. map؟
5. E پیش‌فرض؟
6. async؟
7. زیاده‌روی؟
8. ok/err؟
9. exhaustive؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
جریان موفقیت/شکست typed شد. جلسهٔ ۳۰: خطاهای دامنه‌ای و catch unknown.
