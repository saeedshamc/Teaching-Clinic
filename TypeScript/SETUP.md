# راه‌اندازی محیط TypeScript

نسخهٔ هدف: **TypeScript 5.x** + **Node.js LTS**.

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

نصب می‌کند: `typescript` (کامپایلر) و `tsx` (اجرای مستقیم `.ts`).

## ۴. ویرایشگر
VS Code + افزونهٔ رسمی **TypeScript and JavaScript Language Features** (معمولاً توکار است).

اختیاری: افزونهٔ Error Lens برای دیدن خطا کنار خط.

## ۵. اجرای مثال‌های جلسه
```bash
# فقط بررسی نوع (بدون ساخت فایل JS)
npx tsc --noEmit

# اجرای یک فایل
npx tsx session-01-intro-tsc/examples/01-hello.ts
```

## ۶. عیب‌یابی سریع
| مشکل | کار |
|------|-----|
| `tsc` پیدا نمی‌شود | داخل `TypeScript/` باشید و `npm install` |
| خطای module | `module`/`moduleResolution` در `tsconfig.json` را تغییر ندهید مگر جلسه ۱۳ |
| فارسی در ترمینال به‌هم‌ریخته | UTF-8 در PowerShell |
