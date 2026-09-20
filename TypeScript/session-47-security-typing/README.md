# جلسه 47: امنیت لایه‌نوع — دادهٔ خارجی، as و any (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۱، ۲۸، ۴۳.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. مرز اعتماد را نام ببرید
2. خطر as و any را به زبان امنیت بگویید
3. brand+validate را ترکیب کنید
4. بگویید TS جایگزین auth/authorize نیست
5. چک‌لیست مرزی بسازید

## چرا مهم است؟
باگ امنیتی اغلب از اعتماد بی‌جا به ورودی است؛ نوع دروغ آن را پنهان می‌کند.

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

### 1. TS ≠ امنیت runtime

authorize و sanitize جدا هستند.

### 2. as any

خاموش کردن آژیر.

### 3. ورودی

query، body، فایل، webhook.

### 4. خروجی

لاگ کردن دادهٔ حساس typed هم خطرناک است.

### 5. اصل

validate در مرز؛ دامنه فقط نوع امن.

### 6. XSS/SQL

خارج از این دوره؛ ولی نوع جلوی‌شان را به‌تنهایی نمی‌گیرد.

### 7. review

در PR دنبال as و any بگردید.

### 8. چک‌لیست

unknown در مرز، parse، بدون as، تست بدشکل.

---

## اشتباه‌های رایج

1. **as روی توکن.**
2. **any در middleware.**
3. **فرض نوع = مجوز.**
4. **لاگ PII.**
5. **validate نکردن webhook.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-47-security-typing/examples/01-boundary.ts
npx tsx session-47-security-typing/examples/02-no-as.ts
npx tsx session-47-security-typing/examples/03-checklist.ts
npx tsx session-47-security-typing/examples/04-not-authz.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-boundary.ts](./examples/01-boundary.ts) | مرز |
| [02-no-as.ts](./examples/02-no-as.ts) | ضد as |
| [03-checklist.ts](./examples/03-checklist.ts) | چک‌لیست |
| [04-not-authz.ts](./examples/04-not-authz.ts) | نه authz |

## الگوی بهتر

مرز سخت؛ دامنه خوش‌بین؛ بدون as در مرز.

## تمرین‌ها

### آسان
سه منبع ورودی نام ببرید.

### چالشی
چک‌لیست ۴ موردی مرز بنویسید.

## راهنمای حل

آسان: body, query, file

چالش: unknown, parse, no as, bad-shape test

## خودآزمایی

1. TS امنیت runtime؟
2. as؟
3. any؟
4. مرز؟
5. brand؟
6. PII؟
7. PR؟
8. webhook؟
9. sanitize؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
مرز امن ذهنی شد. جلسهٔ ۴۸: مستندسازی امضاها با JSDoc/TSDoc.
