# جلسه 30: خطاهای تایپ‌شده — catch unknown و خطای دامنه (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۱ و ۲۹.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. catch را unknown بگیرید
2. isError و خواندن message ایمن
3. کلاس/نوع خطای دامنه بسازید
4. بگویید کی Error کافی است
5. با Result مقایسه کنید

## چرا مهم است؟
throw هر چیزی مجاز است. بدون قرارداد، catch تبدیل به حدس می‌شود.

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

### 1. catch unknown

```ts
catch (e: unknown) { ... }
```

### 2. instanceof Error

اولین فیلتر رایج؛ کافی نیست برای همه پرتاب‌ها.

### 3. خطای دامنه

```ts
class NotFoundError extends Error {
  readonly code = "NOT_FOUND" as const;
}
```

### 4. narrow روی code

با discriminant روی فیلد code.

### 5. لاگ

unknown را String/inspect کنید؛ فرض message نکنید.

### 6. Result vs throw

همان جلسه قبل؛ تیم یک قرارداد بردارد.

### 7. cause

Error با cause برای زنجیره — آگاهی.

### 8. دام any در catch

برگشت به جهل.

---

## اشتباه‌های رایج

1. **catch (e: any).**
2. **فرض همیشه Error.**
3. **بلعیدن خطا بدون لاگ.**
4. **کد رشته‌ای جادویی بدون نوع.**
5. **پرتاب string خام به‌عنوان عادت.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-30-typed-errors/examples/01-catch-unknown.ts
npx tsx session-30-typed-errors/examples/02-read-message.ts
npx tsx session-30-typed-errors/examples/03-domain-error.ts
npx tsx session-30-typed-errors/examples/04-narrow-code.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-catch-unknown.ts](./examples/01-catch-unknown.ts) | catch |
| [02-read-message.ts](./examples/02-read-message.ts) | message |
| [03-domain-error.ts](./examples/03-domain-error.ts) | دامنه |
| [04-narrow-code.ts](./examples/04-narrow-code.ts) | narrow |

## الگوی بهتر

unknown → narrow → handle؛ خطای دامنه با code ثابت.

## تمرین‌ها

### آسان
تابع readMessage(err: unknown)

### چالشی
ValidationError با field: string

## راهنمای حل

آسان: instanceof Error ? message : String(err)

چالش: class extends Error + field

## خودآزمایی

1. چرا unknown؟
2. instanceof؟
3. خطای دامنه؟
4. code؟
5. string پرتابی؟
6. Result؟
7. cause؟
8. خطر any؟
9. لاگ؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
خطا قرارداد گرفت. جلسهٔ ۳۱: نوع‌های بازگشتی (JSON/درخت).
