# جلسه 32: فایل‌های Declaration — نوشتن .d.ts برای shim (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۱۰.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. یک .d.ts ساده برای تابع global بنویسید
2. declare module برای پکیج بدون نوع
3. بگویید .d.ts اجرا نمی‌شود
4. فرق ambient و ماژول را بگویید
5. shim را مستند و موقت نگه دارید

## چرا مهم است؟
کتابخانهٔ JS بدون نوع یا global قدیمی — با .d.ts به کامپایلر شکل می‌دهید.

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

### 1. d.ts چیست؟

فقط برای کامپایلر؛ خروجی JS از آن نیست.

### 2. declare function

اعلام وجود تابع بدون بدنه.

### 3. declare module

```ts
declare module "left-pad-lite" {
  export function leftPad(s: string, n: number): string;
}
```

### 4. export {} برای ماژول

فایل را به module تبدیل می‌کند تا global نشود ناخواسته.

### 5. @types

اول جست‌وجوی DefinitelyTyped؛ خودتان آخرین راه‌حل.

### 6. دقت امضا

امضای غلط = ایمنی کاذب.

### 7. نگهداری

shim را با نسخهٔ کتابخانه همگام کنید.

### 8. شامل شدن

با include/typeRoots؛ در این دوره کنار examples.

---

## اشتباه‌های رایج

1. **d.ts را جای پیاده‌سازی دانستن.**
2. **امضای حدسی بدون خواندن docs.**
3. **global آلوده.**
4. **نادیده @types آماده.**
5. **shim ابدی بدون پیگیری.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-32-declaration-files/examples/clinic-banner.d.ts
npx tsx session-32-declaration-files/examples/01-use-banner.ts
npx tsx session-32-declaration-files/examples/demo-mod.d.ts
npx tsx session-32-declaration-files/examples/02-notes.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [clinic-banner.d.ts](./examples/clinic-banner.d.ts) | ambient |
| [01-use-banner.ts](./examples/01-use-banner.ts) | مصرف |
| [demo-mod.d.ts](./examples/demo-mod.d.ts) | module |
| [02-notes.ts](./examples/02-notes.ts) | یادداشت |

## الگوی بهتر

@types اول؛ shim باریک و مستند.

## تمرین‌ها

### آسان
declare function banner(title: string): void

### چالشی
declare module "clinic-demo" با export تابع.

## راهنمای حل

آسان: در .d.ts

چالش: declare module "clinic-demo" { export function hi(): string }

## خودآزمایی

1. d.ts اجرا می‌شود؟
2. declare؟
3. module؟
4. @types؟
5. export {}؟
6. خطر امضا؟
7. global؟
8. نگهداری؟
9. کی بنویسیم؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
اعلان را نوشتید. جلسهٔ ۳۳: augment کردن ماژول/اینترفیس موجود.
