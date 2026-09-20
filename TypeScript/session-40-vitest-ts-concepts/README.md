# جلسه 40: Vitest و TypeScript — مفاهیم (اجرای اختیاری) (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۴ و ۳۹.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. بگویید تست واحد چه چیزی را می‌سنجد در برابر tsc
2. امضای test/expect را ذهنی تایپ کنید
3. mock را در حد مفهوم بشناسید
4. بگویید چرا این پوشه vitest ندارد
5. برای Vite شخصی اسکریپت تست طراحی کنید

## چرا مهم است؟
در عمل تست واحد کنار TS است. اینجا مفاهیم؛ نصب Vitest در این پوشه اجباری نیست.

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

### 1. تقسیم

tsc=قرارداد نوع؛ vitest=رفتار.

### 2. مثال ذهنی

```ts
expect(add(2,3)).toBe(5)
```

### 3. تایپ تست

آرتیفکت‌ها از inference می‌آیند؛ any در تست هم بد است.

### 4. این دوره

بدون افزودن vitest به package.json آموزش — سبک بماند.

### 5. پروژه شخصی

Vite template یا vitest init.

### 6. پوشش

پوشش ≠ نوع صحیح.

### 7. mock

وابستگی را جعل کنید؛ نوع mock را نگه دارید.

### 8. ترتیب CI

typecheck سپس test.

---

## اشتباه‌های رایج

1. **فقط تست بدون tsc.**
2. **any در تست.**
3. **نصب اجباری به این ریپو.**
4. **فرض coverage کافی است.**
5. **mock بدون نوع.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-40-vitest-ts-concepts/examples/01-pure-to-test.ts
npx tsx session-40-vitest-ts-concepts/examples/02-arrange-act-assert.ts
npx tsx session-40-vitest-ts-concepts/examples/03-ci-order.ts
npx tsx session-40-vitest-ts-concepts/examples/04-no-dep-here.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-pure-to-test.ts](./examples/01-pure-to-test.ts) | تابع خالص |
| [02-arrange-act-assert.ts](./examples/02-arrange-act-assert.ts) | AAA |
| [03-ci-order.ts](./examples/03-ci-order.ts) | CI |
| [04-no-dep-here.ts](./examples/04-no-dep-here.ts) | بدون وابستگی |

## الگوی بهتر

دامنه خالص را بدون UI تست کنید؛ typecheck جدا.

## تمرین‌ها

### آسان
برای add یک expect ذهنی بنویسید.

### چالشی
اسکریپت‌های typecheck و test را در package شخصی مرتب کنید.

## راهنمای حل

آسان: expect(add(1,1)).toBe(2)

چالش: "typecheck": "tsc -b", "test": "vitest run"

## خودآزمایی

1. فرق tsc/vitest؟
2. چرا اینجا نصب نیست؟
3. CI ترتیب؟
4. any در تست؟
5. دامنه خالص؟
6. mock؟
7. coverage؟
8. Vite؟
9. expect؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
تست رفتاری را فهمیدید. جلسهٔ ۴۱: تایپ hooks در React.
