# جلسه ۱۳: tsconfig کاربردی (۹۰ دقیقه)

**محیط فرض:** فایل [`TypeScript/tsconfig.json`](../tsconfig.json)

## پیش‌نیازها
جلسات ۰۱ و ۱۰–۱۱.

## اهداف قابل‌اندازه‌گیری
1. نقش `strict`، `target`، `module`/`moduleResolution` را بگویید
2. فرق `noEmit` با emit به `dist` را توضیح دهید
3. `include`/`exclude` را تفسیر کنید
4. یک گزینه را عمداً عوض کنید و اثرش را پیش‌بینی کنید (بدون commit خراب)

## چرا مهم است؟
بدون فهم tsconfig، کپی تنظیمات تصادفی پروژه را می‌شکند — مخصوصاً با ESM.

## مفهوم عمیق
گزینه‌های مهم این دوره:

| گزینه | نقش آموزشی |
|--------|-------------|
| `strict` | بستهٔ ایمنی |
| `noEmit: true` | فقط typecheck؛ اجرا با tsx |
| `module` + `moduleResolution: NodeNext` | هم‌راستا با Node ESM |
| `skipLibCheck` | سرعت؛ چک نکردن عمیق .d.ts وابستگی‌ها |

`examples/sample-tsconfig.json` یک نمونهٔ توضیح‌دار (غیر فعال) است.

## اشتباه‌های رایج
1. قاطی کردن `commonjs` و `NodeNext` بدون دلیل.
2. خاموش کردن strict برای پنهان کردن خطا.
3. `include` نکردن فایل‌های examples و تعجب از «خطا نمی‌دهد».

## مثال گام‌به‌گام
```bash
npx tsc --showConfig | more
# یا
npx tsc --noEmit
```

## الگوی بهتر
یک tsconfig پایه در ریشه؛ در اپ واقعی گاهی `tsconfig.build.json` جدا برای emit.

## تمرین‌ها
### آسان
از `--showConfig` مقدار `strict` را پیدا کنید.
### چالشی
در یک شاخهٔ موقت شخصی، `noEmit` را false و `outDir` بگذارید؛ ببینید فایل JS کجا می‌آید؛ بعد بدون commit برگردید.

## راهنمای حل
`"noEmit": false, "outDir": "dist"` — خروجی را پاک کنید؛ به ریپو commit نکنید.

## خودآزمایی
1. `noEmit` یعنی چه؟
2. `strict` چه می‌کند؟
3. چرا NodeNext پسوند import می‌خواهد؟
4. `skipLibCheck` چه هزینه‌ای دارد؟
5. `include` چه فایل‌هایی را می‌گیرد؟

## جمع‌بندی و پل جلسهٔ بعد
تنظیمات. جلسه بعد: گردش کار روزمره با npm scripts.
