# جلسه 48: مستندسازی — JSDoc/TSDoc روی امضاهای TypeScript (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۵ و ۳۵.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. JSDoc روی تابع تایپ‌شده بنویسید
2. @param و @returns را هم‌راستا با نوع نگه دارید
3. بگویید نوع تکراری در JSDoc لازم نیست
4. مثال کوتاه در doc بگذارید
5. از doc دروغین پرهیز کنید

## چرا مهم است؟
نوع خیلی چیزها را می‌گوید؛ ولی نیت، مثال و محدودیت دامنه را مستند باید بگوید.

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

### 1. هم‌زیستی

نوع در TS؛ نیت در JSDoc.

### 2. نمونه

```ts
/** جمع امن دو عدد متناهی */
function add(a: number, b: number): number
```

### 3. @param

توضیح معنا نه تکرار `: number`.

### 4. مثال

@example در TSDoc.

### 5. لینک

ارجاع به نوع عمومی.

### 6. دام

doc که با امضا نمی‌خواند.

### 7. API عمومی

فقط سطح export را عمیق مستند کنید.

### 8. ابزار

ادیتور hover doc را نشان می‌دهد.

---

## اشتباه‌های رایج

1. **تکرار نوع در متن.**
2. **doc کهنه.**
3. **مستند کردن private بی‌دلیل.**
4. **مثال غلط.**
5. **کپی ویکیی بلند.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-48-jsdoc-tsdoc/examples/01-jsdoc-add.ts
npx tsx session-48-jsdoc-tsdoc/examples/02-example-tag.ts
npx tsx session-48-jsdoc-tsdoc/examples/03-no-repeat-type.ts
npx tsx session-48-jsdoc-tsdoc/examples/04-public-only.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-jsdoc-add.ts](./examples/01-jsdoc-add.ts) | JSDoc |
| [02-example-tag.ts](./examples/02-example-tag.ts) | @example |
| [03-no-repeat-type.ts](./examples/03-no-repeat-type.ts) | تکرار نکن |
| [04-public-only.ts](./examples/04-public-only.ts) | سطح عمومی |

## الگوی بهتر

نیت + محدودیت + مثال کوتاه؛ نوع را تکرار نکنید.

## تمرین‌ها

### آسان
برای clamp یک JSDoc یک‌خطی.

### چالشی
@example برای createUser.

## راهنمای حل

آسان: /** مقدار را بین min و max نگه می‌دارد */

چالش: بلوک @example

## خودآزمایی

1. نقش JSDoc کنار TS؟
2. @param؟
3. تکرار نوع؟
4. @example؟
5. API عمومی؟
6. doc دروغ؟
7. hover؟
8. private؟
9. کهنگی؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
امضاها مستند شدند. جلسهٔ ۴۹: ساختار پوشهٔ پروژهٔ TS.
