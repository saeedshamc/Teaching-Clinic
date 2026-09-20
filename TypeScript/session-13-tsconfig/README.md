# جلسه ۱۳: tsconfig کاربردی — گزینه→اثر، noEmit، skipLibCheck (۱۲۰ دقیقه)

**محیط فرض:** فایل ریشه [`TypeScript/tsconfig.json`](../tsconfig.json) · `npx tsc --showConfig` · `npx tsc --noEmit`

## پیش‌نیازها
جلسات ۱، ۱۰، ۱۱. این جلسه تنظیمات را می‌فهمد، نه اینکه با کپی تصادفی پروژه را بشکند.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. نقش `strict`، `target`، `module`/`moduleResolution` را با یک جمله اثر بگویید
2. فرق `noEmit` با emit به `outDir` را عملی توضیح دهید
3. `include`/`exclude` و چرا مثال‌ها دیده می‌شوند را تفسیر کنید
4. بگویید `skipLibCheck` چه سرعتی می‌خرد و چه هزینه‌ای دارد
5. اشتباه شل کردن strict/گزینه‌ها برای پنهان کردن خطا را رد کنید

## چرا مهم است؟
tsconfig قانون بازی کامپایلر است. یک `module` اشتباه، صدها خطای import می‌سازد. یک `strict: false`، دوره را بی‌اثر می‌کند.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | آناتومی tsconfig این دوره |
| ۲۰–۴۵ | جدول گزینه→اثر |
| ۴۵–۷۰ | noEmit در برابر emit |
| ۷۰–۹۰ | include و skipLibCheck |
| ۹۰–۱۰۵ | ضدالگوی شل کردن |
| ۱۰۵–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### ۱. tsconfig منبع حقیقت ابزار است

ادیتور و `tsc` از همین فایل می‌خوانند. اگر از پوشهٔ دیگر `tsc` بزنید، ممکن است config دیگری بخورد.

### ۲. جدول گزینه→اثر (همین دوره)

| گزینه | اثر آموزشی |
|--------|-------------|
| `strict: true` | بستهٔ ایمنی (null، any ضمنی، …) |
| `noEmit: true` | فقط typecheck؛ JS ساخته نمی‌شود |
| `module` + `moduleResolution: NodeNext` | هم‌راستا با Node ESM؛ پسوند `.js` در import |
| `target: ES2022` | سطح نحو/lib خروجی ذهنی |
| `skipLibCheck: true` | `.d.ts` وابستگی‌ها را عمیق چک نکن |
| `include: session-*/examples/**/*.ts` | فقط مثال‌های جلسات |
| `isolatedModules` | هر فایل جدا transpileپذیر (سازگار bundler) |

### ۳. noEmit در برابر emit

آموزش این دوره: `noEmit` + `tsx` برای اجرا.

اپ واقعی گاهی:

```json
{
  "noEmit": false,
  "outDir": "dist",
  "rootDir": "src"
}
```

دو config (`tsconfig.json` برای ادیتور، `tsconfig.build.json` برای ساخت) رایج است.

### ۴. include / exclude

اگر فایلی خارج `include` باشد، `tsc` ممکن است نادیده‌اش بگیرد و شما فکر کنید «سالم است». مثال‌های این دوره باید زیر الگو باشند.

### ۵. skipLibCheck

سرعت CI را بالا می‌برد. هزینه: باگ نوع داخل کتابخانه را کمتر می‌بینید. برای اپ عادی معمولاً روشن است؛ خاموش کردنش وقتی به `.d.ts` مشکوکید مفید است.

### ۶. showConfig

```bash
npx tsc --showConfig
```

مقادیر نهایی بعد از defaultها را می‌بینید — بهتر از حدس زدن.

### ۷. sample غیر فعال

[`examples/sample-tsconfig.json`](./examples/sample-tsconfig.json) برای خواندن است؛ جایگزین ریشه نکنید مگر آزمایش آگاهانهٔ شخصی بدون commit خراب.

### ۸. شل کردن = باخت یادگیری

`strict: false`، `noImplicitAny: false`، `as any` همه‌جا — ظاهر سبز، مهارت صفر. خطا را با درست کردن قرارداد حل کنید.

---

## اشتباه‌های رایج

1. **قاطی commonjs و NodeNext بدون دلیل.** علت: مدل ماژول متفاوت.
2. **خاموش کردن strict برای پنهان کردن خطا.** علت: راحتی کاذب.
3. **include ناقص و تعجب از نبود خطا.** علت: فایل چک نشده.
4. **commit کردن dist آزمایشی.** علت: نویز ریپو.
5. **تغییر tsconfig ریشه دوره برای یک آزمایش و رها کردن.** علت: بقیه جلسات می‌شکنند.

---

## مثال گام‌به‌گام

```bash
npx tsc --showConfig
npx tsc --noEmit
npx tsx session-13-tsconfig/examples/01-check.ts
```

| فایل | موضوع |
|------|--------|
| [01-check.ts](./examples/01-check.ts) | تأیید محیط typecheck |
| [02-options-notes.ts](./examples/02-options-notes.ts) | یادداشت گزینه→اثر در کد |
| [sample-tsconfig.json](./examples/sample-tsconfig.json) | نمونهٔ توضیح‌دار |

## الگوی بهتر

یک tsconfig سخت در ریشه آموزش؛ در اپ: جدا کردن check و build؛ هر تغییر گزینه را با `--showConfig` و یک خطای عمدی آزمایش کنید.

## تمرین‌ها

### آسان
با `--showConfig` مقدار `strict` و `noEmit` را پیدا کنید.

### چالشی
در شاخهٔ موقت شخصی (نه commit)، `noEmit: false` و `outDir: "dist-tmp"` را آزمایش کنید؛ خروجی را پاک کنید و برگردید.

## راهنمای حل

آسان: در JSON خروجی showConfig جست‌وجو کنید.

چالش: بعد از آزمایش، تنظیمات ریشه را به حالت دوره برگردانید؛ `dist-tmp` را حذف کنید.

## خودآزمایی

1. noEmit یعنی چه؟
2. strict چه می‌کند؟
3. چرا NodeNext به پسوند import حساس است؟
4. skipLibCheck چه هزینه‌ای دارد؟
5. include چه می‌گیرد؟
6. showConfig چه چیزی نشان می‌دهد؟
7. isolatedModules چرا با bundler دوست است؟
8. خطر شل کردن چیست؟
9. فرق target و lib ذهنی چیست؟
10. چرا sample را جایگزین ریشه نکنیم؟

## جمع‌بندی و پل جلسهٔ بعد
تنظیمات را با اثرشان گره زدید. جلسهٔ بعد: **عادت روزانه** — npm scripts، خواندن خطا، و دام tsx در برابر tsc.
