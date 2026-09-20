# جلسه 22: Function Overloads — امضاها، ترتیب، در برابر union (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · از ریشهٔ `TypeScript/`: `npx tsc --noEmit` و `npx tsx`

## پیش‌نیازها
جلسات ۵ و ۸.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. overload با چند امضا + یک پیاده‌سازی بنویسید
2. ترتیب امضا از خاص به عام را رعایت کنید
3. بگویید کی union پارامتر کافی است
4. فرق overload با جنریک را بگویید
5. یک parse سبک با overload مدل کنید

## چرا مهم است؟
گاهی یک تابع دو قرارداد فراخوانی واقعاً متفاوت دارد. Overload به autocomplete و برگشت دقیق کمک می‌کند.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | انگیزه و مسئله |
| ۲۰–۴۵ | مفاهیم پایهٔ جلسه |
| ۴۵–۷۵ | مفاهیم میانی + مثال |
| ۷۵–۱۰۰ | دام‌ها و الگوی بهتر |
| ۱۰۰–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### 1. شکل کلی

```ts
function makeDate(timestamp: number): Date;
function makeDate(y: number, m: number, d: number): Date;
function makeDate(y: number, m?: number, d?: number): Date {
  if (m !== undefined && d !== undefined) return new Date(y, m, d);
  return new Date(y);
}
```

### 2. ترتیب

خاص‌ترین امضا بالا؛ عام‌ترین پیاده‌سازی پایین.

### 3. پیاده‌سازی پنهان

امضای پیاده‌سازی از بیرون دیده نمی‌شود؛ فقط overloadها APIاند.

### 4. در برابر union

اگر برگشت و رفتار یکی است، `(x: string | number)` ساده‌تر است.

### 5. در برابر جنریک

جنریک رابطهٔ نوعی یکسان را حمل می‌کند؛ overload رفتارهای جدا.

### 6. Autocomplete

IDE امضاها را جدا نشان می‌دهد — سود DX.

### 7. دام پیاده‌سازی گشاد

بدنه باید همهٔ امضاها را پوشش دهد وگرنه دروغ API.

### 8. کمتر بهتر

۳+ امضای گیج‌کننده = دو تابع نام‌دار شاید بهتر باشد.

---

## اشتباه‌های رایج

1. **ترتیب امضا از عام به خاص.**
2. **پوشش ندادن همهٔ حالات در بدنه.**
3. **overload برای چیزی که union حل می‌کند.**
4. **فراموش اینکه امضای implementation عمومی نیست.**
5. **overload بی‌پایان به‌جای نام‌های واضح.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-22-function-overloads/examples/01-make-date.ts
npx tsx session-22-function-overloads/examples/02-format.ts
npx tsx session-22-function-overloads/examples/03-vs-union.ts
npx tsx session-22-function-overloads/examples/04-head.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-make-date.ts](./examples/01-make-date.ts) | makeDate |
| [02-format.ts](./examples/02-format.ts) | format |
| [03-vs-union.ts](./examples/03-vs-union.ts) | union ساده‌تر |
| [04-head.ts](./examples/04-head.ts) | head overload |

## الگوی بهتر

اول union/جنریک را امتحان کنید؛ overload وقتی برگشت/قرارداد واقعاً چندشکل است.

## تمرین‌ها

### آسان
دو امضا برای format: عدد→رشته با # و رشته→trim.

### چالشی
head که برای string، کاراکتر اول و برای آرایه عنصر اول برگرداند.

## راهنمای حل

آسان: overload دو امضا + typeof در بدنه.

چالش: function head(s: string): string; function head<T>(arr: T[]): T | undefined; ...

## خودآزمایی

1. چرا ترتیب مهم است؟
2. امضای پیاده‌سازی از بیرون؟
3. کی union کافی است؟
4. جنریک یا overload؟
5. DX overload چیست؟
6. خطر امضای زیاد؟
7. makeDate چه الگویی است؟
8. آیا بدنه باید همه را پوشش دهد؟
9. autocomplete چه می‌بیند؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
Overload را ساختید. جلسهٔ ۲۳: this و call/construct signature.
