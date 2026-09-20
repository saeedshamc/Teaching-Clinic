# دوره TypeScript — Teaching Clinic

مسیر جلسه‌محور فارسی برای افزودن لایهٔ نوع به JavaScript مدرن.

## مخاطب
کسانی که مسیر [`js/`](../js/) را گذرانده‌اند و می‌خواهند قبل از React/Next حرفه‌ای، TypeScript را اصولی یاد بگیرند.

## پیش‌نیاز ابزار
[SETUP.md](./SETUP.md) — Node LTS، `npm install` داخل همین پوشه.

فهرست جلسات: [SYLLABUS.md](./SYLLABUS.md)

## نحوه مطالعه
1. جلسات را به‌ترتیب بخوانید.
2. هر `examples/` را با `npx tsx مسیر/فایل.ts` اجرا و با `npx tsc --noEmit` نوع‌ها را چک کنید.
3. تمرین آسان را حتماً؛ چالش را جدی بگیرید.

## نقشه پیشرفت
- **ورود و انواع (۰۱–۰۴)**
- **توابع و مدل داده (۰۵–۰۸)**
- **روزمرهٔ حرفه‌ای (۰۹–۱۲)**
- **ابزار و پل React (۱۳–۱۶)**

## قرارداد نام‌گذاری
پوشه‌ها: `session-NN-slug/` با `README.md` و `examples/*.ts`.

از ریشهٔ دوره:

```bash
cd TypeScript
npm install
npm run typecheck
npx tsx session-01-intro-tsc/examples/01-hello.ts
```

## خروجی
توانایی نوشتن ماژول تایپ‌شده، خواندن خطای کامپایلر، تنظیم `tsconfig` پایه، و ورود ذهنی به React + TypeScript — بدون دست زدن به پروژه‌های اپ موجود ریپو.

مسیر بعدی پیشنهادی: [`Reactjs/`](../Reactjs/) با پروژهٔ Vite شخصی (نه `React-project`).

SQL، Git و Docker خارج از این دورهٔ TypeScriptاند و جدا پلن می‌شوند.
