# جلسه 25: Mapped Types — keyof mapping و modifierها (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۸–۹ و ۲۴.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. یک mapped type با [K in keyof T] بنویسید
2. modifier اختیاری/readonly را کم/زیاد کنید
3. Partial ذهنی بسازید
4. بگویید رابطه با utilityهای آماده چیست
5. از over-map پرهیز کنید

## چرا مهم است؟
Partial و Readonly خودشان mappedاند. ساخت مشتق سفارشی بدون کپی دستی.

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

### 1. شکل

```ts
type Flags<T> = { [K in keyof T]: boolean };
```

### 2. Partial ذهنی

```ts
type MyPartial<T> = { [K in keyof T]?: T[K] };
```

### 3. Readonly ذهنی

```ts
type MyReadonly<T> = { readonly [K in keyof T]: T[K] };
```

### 4. حذف modifier

`-?` و `-readonly` برای برداشتن اختیاری/readonly.

### 5. کلیدهای انتخابی

می‌توان روی زیرمجموعهٔ کلید map کرد (پیش‌نمایش).

### 6. رابطه با جلسه ۹

اول utility آماده؛ mapped وقتی سفارشی لازم است.

### 7. دام پیچیدگی

map روی map روی conditional = خطای ناخوانا (جلسه ۳۸/۴۶).

### 8. as در mapped (آگاهی)

Key remapping با `as` در نسخه‌های جدید — جلسهٔ عمیق‌تر اختیاری.

---

## اشتباه‌های رایج

1. **بازنویسی دستی به‌جای map.**
2. **map بی‌نیاز وقتی Partial کافی است.**
3. **فراموش T[K].**
4. **modifier اشتباه.**
5. **هیولای تو در تو.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-25-mapped-types/examples/01-flags.ts
npx tsx session-25-mapped-types/examples/02-my-partial.ts
npx tsx session-25-mapped-types/examples/03-my-readonly.ts
npx tsx session-25-mapped-types/examples/04-requiredish.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-flags.ts](./examples/01-flags.ts) | Flags |
| [02-my-partial.ts](./examples/02-my-partial.ts) | MyPartial |
| [03-my-readonly.ts](./examples/03-my-readonly.ts) | MyReadonly |
| [04-requiredish.ts](./examples/04-requiredish.ts) | حذف ? |

## الگوی بهتر

utility آماده اول؛ mapped برای قرارداد ویژهٔ تیم.

## تمرین‌ها

### آسان
Flags<User> که همه boolean شوند.

### چالشی
MyPartial را پیاده کنید.

## راهنمای حل

آسان: { [K in keyof User]: boolean }

چالش: { [K in keyof T]?: T[K] }

## خودآزمایی

1. K in keyof T؟
2. MyPartial؟
3. -? یعنی چه؟
4. رابطه با Partial؟
5. T[K]؟
6. کی utility کافی است؟
7. خطر تو در تو؟
8. Readonly ذهنی؟
9. key remapping؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
Mapped را ساختید. جلسهٔ ۲۶: infer داخل conditional.
