# جلسه 43: اعتبارسنجی Runtime — مرز JSON به نوع (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۲ و ۱۸.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. مرز اعتماد را تعریف کنید
2. validate دستی با guard بنویسید
3. ایدهٔ Zod-style را ذهنی بفهمید
4. بگویید as جایگزین validate نیست
5. Result را با validate ترکیب کنید

## چرا مهم است؟
TypeScript در runtime پاک می‌شود. دادهٔ خارجی باید validate شود وگرنه نوع دروغ است.

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

### 1. مرز

شبکه، localStorage، فرم خام.

### 2. Validate سپس نوع

unknown → guard → T.

### 3. Zod ذهنی

schema.parse(data) یا safeParse — کتابخانه در شغل؛ اینجا دستی.

### 4. as خطر

دروغ به کامپایلر.

### 5. Result

safeParse ≈ Result.

### 6. اشتراک schema

یک منبع برای نوع و validate ایده‌آل است.

### 7. این دوره

بدون اجبار نصب zod.

### 8. دام

validate ناقص و برند دادن.

---

## اشتباه‌های رایج

1. **as روی JSON.**
2. **validate نکردن localStorage.**
3. **schema و type جدا و منحرف.**
4. **throw همیشه در UI.**
5. **اعتماد به نوع سرور بدون چک.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-43-runtime-validation/examples/01-parse-id.ts
npx tsx session-43-runtime-validation/examples/02-safe-user.ts
npx tsx session-43-runtime-validation/examples/03-no-as.ts
npx tsx session-43-runtime-validation/examples/04-boundary.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-parse-id.ts](./examples/01-parse-id.ts) | parseId |
| [02-safe-user.ts](./examples/02-safe-user.ts) | Result user |
| [03-no-as.ts](./examples/03-no-as.ts) | ضد as |
| [04-boundary.ts](./examples/04-boundary.ts) | مرز |

## الگوی بهتر

unknown در مرز؛ validate؛ سپس دامنه.

## تمرین‌ها

### آسان
parseId(data: unknown): number

### چالشی
Result برای parseUser.

## راهنمای حل

آسان: typeof number

چالش: ok/err از جلسه ۲۹

## خودآزمایی

1. مرز اعتماد؟
2. چرا as بد؟
3. Zod ذهنی؟
4. Result؟
5. unknown؟
6. schema؟
7. localStorage؟
8. نصب اینجا؟
9. دام ناقص؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
مرز امن شد. جلسهٔ ۴۴: کلاینت API تایپ‌شده.
