# جلسه ۰۷: کلاس‌ها و implements (۹۰ دقیقه)

**محیط فرض:** TypeScript 5.x · strict

## پیش‌نیازها
جلسات ۰۵–۰۶ · آشنایی با class در [`js/` جلسه ۱۲](../../js/session-12/).

## اهداف قابل‌اندازه‌گیری
1. کلاس با فیلد تایپ‌شده و constructor بنویسید
2. `implements` روی interface را به کار ببرید
3. `public` / `private` / `readonly` را در حد کاربردی استفاده کنید
4. بدون درخت ارث‌بری عمیق بمانید

## چرا مهم است؟
در سرویس‌ها و بعضی کدبیس‌های قدیمی کلاس هست. هدف این جلسه OOP سنگین نیست؛ قرارداد و visibility است.

## مفهوم عمیق
```ts
interface Logger {
  log(message: string): void;
}

class ConsoleLogger implements Logger {
  constructor(private readonly prefix: string) {}

  log(message: string): void {
    console.log(`${this.prefix}: ${message}`);
  }
}
```

Parameter property در constructor کوتاه‌نویسی فیلد است.

## اشتباه‌های رایج
1. ارث‌بری چندلایه برای هر چیز.
2. `private` را امنیت runtime دانستن (فقط سطح کامپایل است).
3. فراموش `implements` و متد جاافتاده.

## مثال گام‌به‌گام
```bash
npx tsx session-07-classes/examples/01-classes.ts
```

## الگوی بهتر
برای دادهٔ ساده همان `type`/`interface`؛ کلاس وقتی رفتار + حالت معنادار دارید.

## تمرین‌ها
### آسان
کلاس `Counter` با `inc()` و `value` readonly از بیرون.
### چالشی
`implements` یک interface `Repository` با متد `get(id: number)`.

## راهنمای حل
فیلد خصوصی `_value` یا parameter property؛ متد عمومی برای تغییر.

## خودآزمایی
1. `implements` چه تضمینی می‌دهد؟
2. `readonly` یعنی چه؟
3. parameter property چیست؟
4. آیا `private` در JS emit شده واقعاً مخفی است؟
5. کی کلاس ننویسیم؟

## جمع‌بندی و پل جلسهٔ بعد
کلاس کاربردی. جلسه بعد: جنریک.
