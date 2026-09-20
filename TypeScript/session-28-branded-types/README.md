# جلسه 28: Branded / Opaque Types — Id و واحدها (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۴ و ۱۹.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. brand با intersection و unique symbol بسازید
2. UserId را از OrderId جدا کنید
3. تابع سازنده/validate برای brand بنویسید
4. بگویید brand فقط کامپایل است
5. واحدهایی مثل Cent را مدل کنید

## چرا مهم است؟
string با string یکی است تا وقتی کاربرId را جای orderId بگذارید. برند جلوی جابه‌جایی تصادفی را می‌گیرد.

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

### 1. مشکل

```ts
type UserId = string; type OrderId = string; // قابل جابه‌جایی
```

### 2. Brand

```ts
declare const brand: unique symbol;
type UserId = string & { readonly [brand]: "UserId" };
```

### 3. سازنده

تابع `asUserId(raw: string): UserId` بعد از validate.

### 4. واحد پول

`type Cents = number & { readonly __brand: "Cents" }`

### 5. فقط کامپایل

در runtime همان string/number است مگر خودتان چک کنید.

### 6. کی؟

Idها، واحدها، ایمیل validated.

### 7. دام

brand بدون validate = امنیت کاذب.

### 8. جایگزین

گاهی object wrapper `{ kind:'user', id:string }` صریح‌تر است.

---

## اشتباه‌های رایج

1. **brand بدون validate.**
2. **فرض امنیت runtime.**
3. **زیاده‌روی brand روی هر string.**
4. **جابه‌جایی با as کور.**
5. **فراموش سازنده.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-28-branded-types/examples/01-user-id.ts
npx tsx session-28-branded-types/examples/02-order-id.ts
npx tsx session-28-branded-types/examples/03-cents.ts
npx tsx session-28-branded-types/examples/04-email.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-user-id.ts](./examples/01-user-id.ts) | UserId |
| [02-order-id.ts](./examples/02-order-id.ts) | جداسازی |
| [03-cents.ts](./examples/03-cents.ts) | Cents |
| [04-email.ts](./examples/04-email.ts) | Email |

## الگوی بهتر

validate → brand؛ مصرف فقط نوع برنددار.

## تمرین‌ها

### آسان
Email برنددار با include @.

### چالشی
Cents و تابع addCents.

## راهنمای حل

آسان: اگر includes('@') آنگاه as Email

چالش: function add(a:Cents,b:Cents):Cents

## خودآزمایی

1. چرا UserId=string ضعیف است؟
2. brand چگونه؟
3. unique symbol؟
4. runtime؟
5. سازنده؟
6. واحد؟
7. دام as؟
8. کی object بهتر است؟
9. validate کجا؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
Idها جدا شدند. جلسهٔ ۲۹: Result/Option به‌عنوان الگوی جریان داده.
