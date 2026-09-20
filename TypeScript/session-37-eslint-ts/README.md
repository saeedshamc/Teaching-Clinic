# جلسه 37: ذهنیت typescript-eslint — بدون نصب سنگین اجباری (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۱۴.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. فرق tsc و ESLint را بگویید
2. چند قانون مفید TS-aware نام ببرید
3. بگویید این دوره نصب اجباری نمی‌کند
4. قانون no-explicit-any را درک کنید
5. ترتیب پیشنهادی ابزارها را بگویید

## چرا مهم است؟
tsc نوع را چک می‌کند؛ ESLint سبک و باگ‌های منطقی/consistancy را. با هم مکمل‌اند.

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

### 1. تقسیم کار

tsc: نوع؛ eslint: سبک، پیچیدگی، ضدالگو.

### 2. typescript-eslint

پارسر و قوانین آگاه از نوع.

### 3. قوانین نمونه

no-explicit-any، no-floating-promises، consistent-type-imports.

### 4. این دوره

اجبار نصب نیست؛ ذهنیت و آمادگی برای پروژهٔ شخصی.

### 5. ترتیب

اول typecheck سبز؛ بعد lint.

### 6. over-lint

صد قانون در روز اول = خستگی.

### 7. CI

lint + tsc هر دو.

### 8. Prettier

فرمت جدا از lint منطقی؛ قاطی نکنید.

---

## اشتباه‌های رایج

1. **جایگزین کردن tsc با eslint.**
2. **any برای خاموش کردن lint.**
3. **فعال کردن همه rules یکجا.**
4. **نادیده floating promises.**
5. **جنگ Prettier و ESLint.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-37-eslint-ts/examples/01-tsc-vs-lint.ts
npx tsx session-37-eslint-ts/examples/02-no-any-habit.ts
npx tsx session-37-eslint-ts/examples/03-floating-note.ts
npx tsx session-37-eslint-ts/examples/04-scripts-note.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-tsc-vs-lint.ts](./examples/01-tsc-vs-lint.ts) | فرق |
| [02-no-any-habit.ts](./examples/02-no-any-habit.ts) | ضد any |
| [03-floating-note.ts](./examples/03-floating-note.ts) | promises |
| [04-scripts-note.ts](./examples/04-scripts-note.ts) | scripts |

## الگوی بهتر

tsc اجباری؛ eslint تدریجی با چند قانون پرتأثیر.

## تمرین‌ها

### آسان
سه قانون مفید نام ببرید.

### چالشی
برای پروژه شخصی ترتیب scripts را طراحی کنید.

## راهنمای حل

آسان: no-explicit-any, no-floating-promises, consistent-type-imports

چالش: typecheck && lint

## خودآزمایی

1. فرق tsc/eslint؟
2. typescript-eslint؟
3. no-explicit-any؟
4. floating promises؟
5. این دوره نصب؟
6. over-lint؟
7. CI؟
8. Prettier؟
9. ترتیب؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
ذهنیت lint را دارید. جلسهٔ ۳۸: خواندن خطاهای پیچیدهٔ جنریک.
