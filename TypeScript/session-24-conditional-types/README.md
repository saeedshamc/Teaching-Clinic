# جلسه 24: Conditional Types — extends ? : و توزیع (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۸.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. T extends U ? X : Y بنویسید
2. توزیع روی union را ببینید
3. یک Flatten ذهنی بسازید
4. بگویید کی conditional زیاده‌روی است
5. با never در شاخه کار کنید

## چرا مهم است؟
ابزارهای پیشرفتهٔ نوع (مثل Exclude) روی conditional سوارند. بدون آن mapped/infer ناقص می‌ماند.

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

### 1. شکل پایه

```ts
type IsString<T> = T extends string ? true : false;
```

### 2. توزیع

روی union برهنه، conditional عضو به عضو توزیع می‌شود.

### 3. جلوگیری از توزیع

```ts
type NoDist<T> = [T] extends [string] ? true : false;
```

### 4. مثال Exclude ذهنی

```ts
type MyExclude<T, U> = T extends U ? never : T;
```

### 5. کاربرد API

استخراج نوع برگشت بر اساس ورودی — پایهٔ جلسات بعد.

### 6. خوانایی

conditional تو در تو را نام‌گذاری کنید؛ یک خط هیولا ننویسید.

### 7. دام

over-engineering نوع وقتی یک type ساده کافی است.

### 8. پل infer

جلسه ۲۶ `infer` را داخل conditional می‌گذارد.

---

## اشتباه‌های رایج

1. **conditional برای هر چیز.**
2. **نادیده توزیع روی union.**
3. **تو در تو بدون نام.**
4. **قاطی با مقدار runtime.**
5. **استفاده به‌جای if جاوااسکریپت.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-24-conditional-types/examples/01-is-string.ts
npx tsx session-24-conditional-types/examples/02-distribute.ts
npx tsx session-24-conditional-types/examples/03-my-exclude.ts
npx tsx session-24-conditional-types/examples/04-named.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-is-string.ts](./examples/01-is-string.ts) | IsString |
| [02-distribute.ts](./examples/02-distribute.ts) | توزیع |
| [03-my-exclude.ts](./examples/03-my-exclude.ts) | Exclude |
| [04-named.ts](./examples/04-named.ts) | نام‌دار |

## الگوی بهتر

conditional برای مشتق نوع؛ منطق runtime همان if/switch خودتان.

## تمرین‌ها

### آسان
type IsNumber<T> = T extends number ? true : false

### چالشی
MyExclude را پیاده و روی string|number vs string تست کنید.

## راهنمای حل

آسان: همان.

چالش: type MyExclude<T,U> = T extends U ? never : T

## خودآزمایی

1. شکل conditional؟
2. توزیع یعنی چه؟
3. چطور توزیع را قطع کنیم؟
4. Exclude ذهنی؟
5. آیا runtime اجرا می‌شود؟
6. کی زیاده‌روی است؟
7. never در شاخه؟
8. نام‌گذاری چرا؟
9. پل infer؟
10. true/false به‌عنوان نوع؟

## جمع‌بندی و پل جلسهٔ بعد
Conditional را دیدید. جلسهٔ ۲۵: mapped types.
