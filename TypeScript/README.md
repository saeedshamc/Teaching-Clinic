# دوره TypeScript — Teaching Clinic (۵۰ جلسه)

مسیر جلسه‌محور فارسی برای افزودن لایهٔ نوع به JavaScript مدرن — با **عمق آموزشی** (اهداف، مفهوم، اشتباه رایج، چند مثال، تمرین، خودآزمایی).

## مخاطب
کسانی که مسیر [`js/`](../js/) را گذرانده‌اند و می‌خواهند قبل از React/Next حرفه‌ای، TypeScript را اصولی یاد بگیرند.

## پیش‌نیاز ابزار
[SETUP.md](./SETUP.md) — Node LTS، `npm install` داخل همین پوشه.

فهرست کامل: [SYLLABUS.md](./SYLLABUS.md)

## نحوه مطالعه
1. جلسات را به‌ترتیب بخوانید (هر README حدود ۱۲۰ دقیقه طراحی شده).
2. هر `examples/` را با `npx tsx مسیر/فایل.ts` اجرا و با `npm run typecheck` نوع‌ها را چک کنید.
3. تمرین آسان را حتماً؛ چالش را جدی بگیرید.
4. جلسات React فقط مفهومی‌اند — اجرا در Vite شخصی؛ **اپ آمادهٔ `Reactjs/` را تغییر ندهید.**

## نقشه پیشرفت
- **۰۱–۰۴** ورود و انواع پایه
- **۰۵–۱۲** توابع، مدل داده، ماژول، strict، async
- **۱۳–۱۶** ابزار روزمره + پل React + capstone میانی Notes
- **۱۷–۲۷** سیستم نوع عمیق (union، guard، conditional، mapped، infer، …)
- **۲۸–۳۵** برند، Result، خطا، declaration، paths
- **۳۶–۴۴** ابزار کیفیت، تست نوع، React/API مفاهیم
- **۴۵–۵۰** الگو، امنیت نوعی، ساختار پروژه، capstone نهایی

## قرارداد نام‌گذاری
پوشه‌ها: `session-NN-slug/` با `README.md` و `examples/**/*.ts`.

از ریشهٔ دوره:

```bash
cd TypeScript
npm install
npm run typecheck
npx tsx session-01-intro-tsc/examples/01-hello.ts
npx tsx session-50-capstone-final/examples/app/main.ts
```

## استاندارد عمق هر جلسه
مدت ۱۲۰ دقیقه، ۵ هدف، مفهوم چندبخشی، ≥۵ اشتباه رایج، ≥۳ مثال، تمرین + راهنما، ۸–۱۰ خودآزمایی، پل به جلسه بعد.

## خروجی
توانایی نوشتن ماژول/سرویس تایپ‌شده، خواندن خطای پیچیده، تنظیم tsconfig، validate مرز داده، و ورود ذهنی به React + TypeScript — بدون دست زدن به پروژه‌های اپ موجود ریپو.

مسیر بعدی پیشنهادی: [`Reactjs/`](../Reactjs/) با پروژهٔ Vite شخصی (نه تغییر `React-project`).

SQL، Git و Docker خارج از این دورهٔ TypeScriptاند و جدا پلن می‌شوند.
