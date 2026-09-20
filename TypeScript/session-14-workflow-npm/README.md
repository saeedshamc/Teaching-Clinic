# جلسه ۱۴: گردش کار روزمره tsc و npm (۹۰ دقیقه)

**محیط فرض:** [`package.json`](../package.json) دوره

## پیش‌نیازها
جلسات ۰۱ و ۱۳ · آشنایی با npm از [`js/session-22`](../../js/session-22/).

## اهداف قابل‌اندازه‌گیری
1. اسکریپت `npm run typecheck` را اجرا کنید
2. یک خطای عمدی را از روی خروجی `tsc` پیدا و درست کنید
3. تفاوت `tsc` و `tsx` را در یک جمله بگویید
4. اسکریپت npm سبک به `package.json` شخصی‌تان اضافه کنید (نه الزاماً commit به این ریپو)

## چرا مهم است؟
در CI معمولاً `tsc --noEmit` قبل از merge است. عادت محلی = کمتر سورپرایز در PR.

## مفهوم عمیق
| ابزار | کار |
|--------|-----|
| `tsc --noEmit` | فقط نوع |
| `tsx file.ts` | اجرا |
| `npm run typecheck` | همان tsc از scripts |

خواندن خطا: فایل → خط → ستون → پیام → گاهی `Related information`.

## اشتباه‌های رایج
1. فقط اجرای runtime و نادیده typecheck.
2. صدها `// @ts-expect-error` بدون توضیح.
3. commit کردن `node_modules`.

## مثال گام‌به‌گام
```bash
cd TypeScript
npm run typecheck
npx tsx session-14-workflow-npm/examples/01-workflow.ts
```

## الگوی بهتر
قبل از push: format (اختیاری) + typecheck. ESLint را بعداً جدا یاد بگیرید؛ اینجا تمرکز tsc است.

## تمرین‌ها
### آسان
`typecheck` را اجرا و خروجی موفق را تأیید کنید.
### چالشی
در فایل مثال یک خطای نوعی بسازید، پیام را بخوانید، درست کنید.

## راهنمای حل
پیام `is not assignable` را با هم‌تراز کردن نوع چپ/راست حل کنید.

## خودآزمایی
1. اسکریپت npm کجا تعریف می‌شود؟
2. `tsc` با `tsx` چه فرقی دارد؟
3. چرا `node_modules` در git نیست؟
4. خروجی خطا کدام فیلدها را دارد؟
5. CI چرا typecheck می‌خواهد؟

## جمع‌بندی و پل جلسهٔ بعد
گردش کار. جلسه بعد: مفاهیم React + TypeScript (بدون دست زدن به React-project).
