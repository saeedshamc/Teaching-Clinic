# جلسه ۰۵: تایپ توابع (۹۰ دقیقه)

**محیط فرض:** TypeScript 5.x · strict

## پیش‌نیازها
جلسات ۰۱–۰۴.

## اهداف قابل‌اندازه‌گیری
1. پارامتر و نوع برگشت را annotate کنید
2. optional و default parameter را تایپ کنید
3. rest parameter تایپ‌شده بنویسید
4. type برای خود تابع (`(a: number) => number`) بسازید

## چرا مهم است؟
تابع مرز منطق است. اگر ورودی/خروجی مبهم باشد، کل زنجیرهٔ فراخوانی می‌لرزد.

## مفهوم عمیق
```ts
function add(a: number, b: number): number {
  return a + b;
}

function log(message: string, times: number = 1): void {
  for (let i = 0; i < times; i++) console.log(message);
}

type Mapper = (value: number) => number;
```

`void` یعنی عمداً چیزی برای استفاده برنمی‌گردانید.

## اشتباه‌های رایج
1. فراموش نوع برگشت وقتی inference گمراه‌کننده است (مثلاً چند مسیر return).
2. optional را بعد از required نیاوردن ترتیب درست.
3. callback بدون تایپ پارامتر.

## مثال گام‌به‌گام
```bash
npx tsx session-05-function-types/examples/01-functions.ts
```

## الگوی بهتر
برای callbackهای تکراری یک `type` نام‌دار بسازید.

## تمرین‌ها
### آسان
`clamp(n, min, max)` با سه `number`.
### چالشی
تابعی که `(...nums: number[]) => number` جمع بزند.

## راهنمای حل
`return nums.reduce((s, n) => s + n, 0)`

## خودآزمایی
1. `void` با `undefined` چه فرقی در نیت دارد؟
2. پارامتر optional را چطور می‌نویسید؟
3. نوع تابع به‌عنوان مقدار یعنی چه؟
4. rest را چطور تایپ می‌کنید؟
5. آیا می‌توان overload نوشت؟ (فقط بدانید وجود دارد)

## جمع‌بندی و پل جلسهٔ بعد
توابع تایپ شدند. جلسه بعد: interface در برابر type.
