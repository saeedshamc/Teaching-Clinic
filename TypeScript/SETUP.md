# راه‌اندازی محیط TypeScript

نسخهٔ هدف: **TypeScript 5.x** + **Node.js LTS**.

این پوشه یک دورهٔ **۵۰جلسه‌ای** با عمق آموزشی است: هر جلسه README غنی + چند مثال؛ کل مسیر با یک `tsconfig` ریشه typecheck می‌شود.

## ۱. پیش‌نیاز دانش
حداقل مسیر [`js/`](../js/) تا ماژول، async و کار با داده. این دوره JavaScript را دوباره درس نمی‌دهد.

## ۲. نصب Node
از https://nodejs.org نسخهٔ LTS را نصب کنید:

```bash
node -v
npm -v
```

## ۳. وابستگی‌های این پوشه
از ریشهٔ `TypeScript/`:

```bash
cd TypeScript
npm install
```

نصب می‌کند: `typescript` (کامپایلر)، `tsx` (اجرای مستقیم `.ts`)، و `@types/node`.

## ۴. ویرایشگر
VS Code + افزونهٔ رسمی **TypeScript and JavaScript Language Features** (معمولاً توکار است).

اختیاری: افزونهٔ Error Lens برای دیدن خطا کنار خط.

## ۵. اجرای مثال‌ها و typecheck کل دوره
```bash
# بررسی نوع همهٔ جلسات (۰۱–۵۰)
npm run typecheck
# یا
npx tsc --noEmit

# اجرای یک فایل
npx tsx session-01-intro-tsc/examples/01-hello.ts
npx tsx session-50-capstone-final/examples/app/main.ts
```

عادت پیشنهادی: قبل از «تمام شدن» هر جلسه، `npm run typecheck`.

## ۶. عیب‌یابی سریع
| مشکل | کار |
|------|-----|
| `tsc` پیدا نمی‌شود | داخل `TypeScript/` باشید و `npm install` |
| خطای module / پسوند `.js` | `module`/`moduleResolution` را تغییر ندهید مگر جلسات ۱۳ و ۳۶ |
| فارسی در ترمینال به‌هم‌ریخته | UTF-8 در PowerShell |
| مثال React اجرا نمی‌شود | عمدی است؛ در Vite شخصی کپی کنید |

## ۷. اسناد
- [README.md](./README.md) — نمای دوره
- [SYLLABUS.md](./SYLLABUS.md) — جدول ۵۰ جلسه
