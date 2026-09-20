# جلسه 33: Module Augmentation — گسترش قرارداد موجود (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۶ و ۳۲.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. interface موجود را augment کنید
2. بگویید کی مجاز است
3. خطر تداخل نسخه‌ها را بشناسید
4. فرق با wrapper را بگویید
5. نمونهٔ آموزشی کوچک بنویسید

## چرا مهم است؟
گاهی باید به interface کتابخانه فیلد اضافه کنید بدون fork. augmentation همان merging آگاهانه است.

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

### 1. Merging یادآوری

دو interface هم‌نام ادغام می‌شوند.

### 2. Augment ماژول

```ts
declare module "./config.js" {
  interface Config { debug?: boolean }
}
```

### 3. کی؟

پلاگین‌ها، افزودن فیلد به context فریم‌ورک.

### 4. کی نه؟

کد خودتان — همانجا تعریف را عوض کنید.

### 5. Wrapper بهتر؟

اغلب type جدید دور شیء بسازید تا augment نکنید.

### 6. نسخه

با آپدیت کتابخانه ممکن است بشکنید.

### 7. مستند

هر augment را در README تیم بنویسید.

### 8. دام global

آلوده کردن Window بدون نیاز.

---

## اشتباه‌های رایج

1. **augment به‌جای اصلاح منبع خودی.**
2. **بدون مستند.**
3. **تداخل نام فیلد.**
4. **global بی‌رویه.**
5. **فرض forever-stable.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-33-module-augmentation/examples/01-merge-local.ts
npx tsx session-33-module-augmentation/examples/02-wrapper.ts
npx tsx session-33-module-augmentation/examples/03-when.ts
npx tsx session-33-module-augmentation/examples/04-doc.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-merge-local.ts](./examples/01-merge-local.ts) | merge |
| [02-wrapper.ts](./examples/02-wrapper.ts) | wrapper |
| [03-when.ts](./examples/03-when.ts) | کی |
| [04-doc.ts](./examples/04-doc.ts) | مستند |

## الگوی بهتر

اول wrapper؛ augment فقط برای اکوسیستم.

## تمرین‌ها

### آسان
interface AppConfig را در دو بلوک merge کنید.

### چالشی
توضیح دهید چرا برای کد خودی merge نکنید.

## راهنمای حل

آسان: دو interface AppConfig

چالش: خوانایی و منبع حقیقت واحد.

## خودآزمایی

1. augment چیست؟
2. کی؟
3. کی نه؟
4. wrapper؟
5. نسخه؟
6. global؟
7. merging؟
8. مستند؟
9. خطر؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
گسترش قرارداد را دیدید. جلسهٔ ۳۴: path mapping در tsconfig.
