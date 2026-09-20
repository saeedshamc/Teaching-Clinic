# جلسه 35: Declaration Emit — .d.ts خروجی و مصرف (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۳ و ۳۲.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. نقش declaration و declarationMap را بگویید
2. فرق noEmit آموزش با emit کتابخانه
3. مصرف نوع از پکیج را ذهنی کنید
4. بگویید API عمومی چیست
5. از export الکی پرهیز کنید

## چرا مهم است؟
کتابخانهٔ TS باید نوع را به مصرف‌کننده بدهد. declaration emit همان قرارداد منتشرشده است.

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

### 1. declaration: true

کنار JS، فایل .d.ts می‌سازد.

### 2. آموزش ما

noEmit:true — این جلسه مفهومی است.

### 3. API سطح

هر export عمومی بخشی از قرارداد نسخه‌بندی است.

### 4. declarationMap

برای پرش به منبع در ادیتور مصرف‌کننده.

### 5. composite/project refs

آگاهی برای مونو‌ریپو؛ جزئیات اختیاری.

### 6. انتشار

types در package.json به .d.ts اشاره می‌کند.

### 7. دام

export کردن نوع داخلی اتفاقی.

### 8. چک

قبل از publish یک پکیج تست مصرف کنید.

---

## اشتباه‌های رایج

1. **export همه چیز.**
2. **noEmit را در lib خاموش نکردن و تعجب.**
3. **فراموش فیلد types.**
4. **نسخه شکستن نوع بدون bump.**
5. **d.ts دست‌نویس به‌جای emit وقتی منبع TS است.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-35-declaration-emit/examples/01-public-api.ts
npx tsx session-35-declaration-emit/examples/02-keep-private.ts
npx tsx session-35-declaration-emit/examples/03-notes-emit.ts
npx tsx session-35-declaration-emit/examples/04-package-fields.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-public-api.ts](./examples/01-public-api.ts) | API |
| [02-keep-private.ts](./examples/02-keep-private.ts) | خصوصی |
| [03-notes-emit.ts](./examples/03-notes-emit.ts) | یادداشت |
| [04-package-fields.ts](./examples/04-package-fields.ts) | package fields |

## الگوی بهتر

منبع TS → emit declaration؛ سطح API را آگاهانه باز کنید.

## تمرین‌ها

### آسان
سه فیلد package.json مرتبط با types را نام ببرید.

### چالشی
لیست کنید چه چیزهایی را export نمی‌کنید.

## راهنمای حل

آسان: types، typings، exports.types

چالش: helperهای داخلی، brand symbols خام، ...

## خودآزمایی

1. declaration؟
2. declarationMap؟
3. types در package؟
4. چرا API سطح؟
5. آموزش noEmit؟
6. دام export؟
7. مصرف تست؟
8. دست‌نویس؟
9. composite؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
انتشار نوع را فهمیدید. جلسهٔ ۳۶: tsconfig پیشرفته‌تر.
