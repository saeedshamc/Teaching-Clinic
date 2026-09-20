# جلسه 39: Type Testing — expectType و ترفند tsc (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۳۸.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. Equal ذهنی / assignability را برای تست نوع بفهمید
2. الگوی expectType ساده بنویسید
3. بگویید type test جایگزین unit test نیست
4. فایل تست نوع را در CI با tsc چک کنید
5. از تست‌های شکننده پرهیز کنید

## چرا مهم است؟
گاهی می‌خواهید خودِ نوع را قفل کنید تا refactor نشکند — بدون تست runtime.

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

### 1. ایده

اگر نوع غلط باشد، tsc قرمز می‌شود — همان assertion.

### 2. expectType سبک

```ts
function expectType<T>(_value: T): void {}
expectType<number>(1);
```

### 3. Equal پیشرفته

کتابخانه‌هایی مثل expect-type؛ اینجا ساده می‌مانیم.

### 4. نه جایگزین منطق

type test نمی‌گوید تابع درست جمع می‌زند.

### 5. جای فایل

examples یا __type_tests__.

### 6. CI

همان npm run typecheck.

### 7. دام

تست نوع روی جزئیات ناپایدار.

### 8. @ts-expect-error

برای اثبات اینکه چیزی باید خطا بدهد — با توضیح.

---

## اشتباه‌های رایج

1. **جایگزین تست منطقی.**
2. **expect بدون معنی.**
3. **ts-expect-error بی‌توضیح.**
4. **تست روی inference تصادفی.**
5. **نادیده CI.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-39-type-testing/examples/01-expect-type.ts
npx tsx session-39-type-testing/examples/02-api-lock.ts
npx tsx session-39-type-testing/examples/03-not-logic.ts
npx tsx session-39-type-testing/examples/04-ci-note.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-expect-type.ts](./examples/01-expect-type.ts) | helper |
| [02-api-lock.ts](./examples/02-api-lock.ts) | قفل API |
| [03-not-logic.ts](./examples/03-not-logic.ts) | یادآوری |
| [04-ci-note.ts](./examples/04-ci-note.ts) | CI |

## الگوی بهتر

قفل قراردادهای عمومی API با type test؛ منطق با unit test.

## تمرین‌ها

### آسان
expectType<string>("a")

### چالشی
با @ts-expect-error نشان دهید number به string نمی‌رود (در فایل جدا شخصی).

## راهنمای حل

آسان: همان helper

چالش: // @ts-expect-error ... const s: string = 1

## خودآزمایی

1. هدف type test؟
2. expectType؟
3. جایگزین unit؟
4. CI؟
5. ts-expect-error؟
6. Equal؟
7. دام؟
8. API عمومی؟
9. فایل کجا؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
نوع را قفل کردید. جلسهٔ ۴۰: مفاهیم Vitest + TS.
