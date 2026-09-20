# جلسه ۵: تایپ توابع — پارامتر، برگشت، callback، void، rest (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · از ریشهٔ `TypeScript/`: `npx tsc --noEmit` و `npx tsx`

## پیش‌نیازها
جلسات ۱–۴: annotation، آرایه/object، union و narrowing. JavaScriptِ تابع را دوباره درس نمی‌دهیم؛ فقط لایهٔ نوع روی همان دانش.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. پارامتر و نوع برگشت را annotate کنید و بگویید کی inference برگشت کافی است
2. optional و default parameter را درست تایپ و مرتب کنید
3. rest parameter تایپ‌شده و type برای خود تابع بنویسید
4. callback را با نام‌گذاری `type` تایپ کنید و تفاوت `void` با `undefined` را توضیح دهید
5. overload را در حد **مدل ذهنی** بشناسید (جزئیات کامل جلسه ۲۲)

## چرا مهم است؟
تابع مرز قرارداد است. اگر ورودی/خروجی مبهم باشد، کل زنجیرهٔ فراخوانی می‌لرزد — در React، در API، در ماژول بعدی.

تایپ تابع یعنی: «این جعبه چه چیزی می‌گیرد و چه چیزی برمی‌گرداند» قبل از اجرا سند شده باشد. بدون آن، یا `any` پخش می‌شود یا هر بار حدس می‌زنید.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۱۵ | پارامتر، برگشت، inference برگشت |
| ۱۵–۳۵ | optional در برابر default |
| ۳۵–۵۵ | rest و نوع تابع به‌عنوان مقدار |
| ۵۵–۸۰ | callback typed و `void` vs `undefined` |
| ۸۰–۱۰۰ | overload ذهنی + ضدالگوها |
| ۱۰۰–۱۲۰ | مثال، تمرین، خودآزمایی |

---

## مفهوم عمیق

### ۱. پارامتر و برگشت — قرارداد صریح

```ts
function add(a: number, b: number): number {
  return a + b;
}
```

پارامترها را تقریباً همیشه annotate کنید؛ مرز تابع جایی است که inference از «بیرون» نمی‌آید.

برگشت را می‌توانید بنویسید یا بگذارید inference کند:

```ts
function add2(a: number, b: number) {
  return a + b; // inference: number
}
```

کی برگشت را صریح بنویسید؟ وقتی چند مسیر `return` دارید، یا وقتی می‌خواهید عمداً قرارداد عمومی‌تر/محدودتر از inference باشد، یا وقتی تابع عمومی API است.

### ۲. Optional در برابر default

```ts
function greet(name: string, title?: string): string {
  return title ? `${title} ${name}` : name;
}

function repeat(message: string, times: number = 2): void {
  for (let i = 0; i < times; i++) console.log(message);
}
```

- `title?` یعنی `string | undefined`؛ ممکن است نیاید.
- `times = 2` یعنی اگر نیاید، مقدار پیش‌فرض؛ نوع پارامتر همچنان `number` است.

دام ترتیب: پارامتر required نمی‌تواند بعد از optional بیاید مگر default داشته باشد. ترتیب را از اجباری به اختیاری بچینید.

### ۳. Rest تایپ‌شده

```ts
function sum(...nums: number[]): number {
  return nums.reduce((acc, n) => acc + n, 0);
}
```

`...nums: number[]` یعنی صفر یا چند عدد. rest همیشه آرایه است — حتی اگر خالی صدا زده شود.

برای tuple ثابت بعداً (جلسه ۲۱) دقیق‌تر می‌شود؛ فعلاً `T[]` کافی است.

### ۴. نوع خود تابع

تابع فقط «اجرا» نیست؛ می‌تواند **مقدار تایپ‌شده** باشد:

```ts
type Mapper = (value: number) => number;

const double: Mapper = (n) => n * 2;
```

چرا مفید؟ برای callbackهای تکراری، props در React، و آرایهٔ handlerها یک نام مشترک می‌گذارید. تغییر امضا در یک جا دیده می‌شود.

شکل معادل با object-call-signature (آگاهی):

```ts
type MapperObj = {
  (value: number): number;
};
```

در این جلسه همان شکل پیکانی را ترجیح دهید.

### ۵. Callback typed

```ts
type OnScore = (score: number) => void;

function play(onScore: OnScore): void {
  onScore(10);
}
```

بدون تایپ، پارامتر callback اغلب `any` ضمنی می‌شود و زنجیرهٔ ایمنی می‌شکند.

دام: اگر callback باید مقداری برگرداند که شما استفاده می‌کنید، `void` ننویسید — نوع برگشت واقعی بنویسید (`number`، `boolean`، …).

### ۶. `void` در برابر `undefined`

- `void`: نیت «برگشت برای مصرف‌کننده مهم نیست». فراخوان‌کننده نباید روی مقدار حساب کند.
- `undefined`: مقدار واقعی `undefined` ممکن است برگردد و بخشی از قرارداد است.

```ts
function log(msg: string): void {
  console.log(msg);
  // return;  // OK
  // return undefined; // معمولاً برای void پذیرفته می‌شود
}

function findId(ids: number[], n: number): number | undefined {
  return ids.find((x) => x === n);
}
```

دام مهم در callback: تابعی که `number` برمی‌گرداند اغلب جایی که `(x) => void` انتظار می‌رود قابل‌قبول است (چون برگشت نادیده گرفته می‌شود). این ویژگی TS است؛ گیج نشوید با «پس void یعنی undefined».

### ۷. Overload — فقط مدل ذهنی این جلسه

گاهی یک تابع دو شکل فراخوانی دارد:

```ts
// ذهنی — جزئیات جلسه ۲۲
function parse(input: string): number;
function parse(input: string, radix: number): number;
```

تا آن جلسه: اگر دو رفتار نزدیک دارید، یا union پارامتر بنویسید، یا دو تابع نام‌دار. Overload را فقط بشناسید که وجود دارد و ترتیب امضاها مهم است.

### ۸. چیزی که عمداً اینجا کامل نیست

جنریک روی تابع (`function id<T>`)، `this` تایپ‌شده، overload کامل — جلسات ۸، ۲۲، ۲۳. اینجا باید حس «مرز تابع سند دارد» ساخته شود.

---

## اشتباه‌های رایج

1. **فراموش نوع پارامتر و اتکا به `any` ضمنی در callback.** علت: بدون annotation در موقعیت‌های خاص، ایمنی از دست می‌رود.
2. **optional را وسط پارامترهای required گذاشتن.** علت: سینتکس/قواعد ترتیب TS.
3. **`void` را با «هرگز چیزی برنگردان» یکی دانستن و جنگ با `return`.`** علت: void نیت مصرف است نه ممنوعیت مطلق.
4. **نوع برگشت ننوشتن وقتی چند شاخه نوع‌های متفاوت برمی‌گردانند.** علت: inference ممکن است union گشاد یا خطا بدهد؛ قرارداد صریح روشن‌تر است.
5. **rest را `number` نوشتن به‌جای `number[]`.** علت: rest همیشه آرایه جمع می‌کند.

---

## مثال گام‌به‌گام

از ریشهٔ `TypeScript/`:

```bash
npx tsx session-05-function-types/examples/01-params-return.ts
npx tsx session-05-function-types/examples/02-optional-default-rest.ts
npx tsx session-05-function-types/examples/03-callbacks-void.ts
npx tsx session-05-function-types/examples/04-function-types.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-params-return.ts](./examples/01-params-return.ts) | پارامتر و برگشت |
| [02-optional-default-rest.ts](./examples/02-optional-default-rest.ts) | optional، default، rest |
| [03-callbacks-void.ts](./examples/03-callbacks-void.ts) | callback و void |
| [04-function-types.ts](./examples/04-function-types.ts) | type برای تابع |

## الگوی بهتر

- پارامترها را annotate کنید؛ برای API عمومی برگشت را هم بنویسید.
- callback تکراری → `type` نام‌دار، نه امضای کپی‌پیست.
- `void` برای side-effect؛ `T | undefined` وقتی نبودن مقدار بخشی از نتیجه است.
- فعلاً به‌جای overload پیچیده، نام تابع واضح یا union پارامتر.

## تمرین‌ها

### آسان
تابع `clamp(n: number, min: number, max: number): number` که مقدار را بین min و max نگه دارد.

### چالشی
`type Predicate<T> = (value: T) => boolean` را فعلاً با `T = number` تمرین کنید: تابع `countWhere(nums: number[], pred: (n: number) => boolean): number`. بعداً با جنریک جلسه ۸ عوضش می‌کنید.

## راهنمای حل

آسان: `return Math.min(max, Math.max(min, n));`

چالش: یک شمارنده؛ حلقه یا `nums.filter(pred).length`. نوع pred را inline یا با `type NumPred = (n: number) => boolean` بنویسید.

## خودآزمایی

1. چرا پارامتر تابع را معمولاً annotate می‌کنیم؟
2. تفاوت `title?: string` با `title: string = ""` چیست؟
3. rest را چطور تایپ می‌کنید؟
4. `type Mapper = (n: number) => number` چه سودی دارد؟
5. `void` از نظر نیت چه می‌گوید؟
6. کی برگشت را صریح بنویسیم؟
7. آیا تابع با برگشت `number` می‌تواند جای callbackِ `void` بنشیند؟ چرا این گیج‌کننده است؟
8. overload در این جلسه چه سطح دانشی می‌خواهد؟
9. ترتیب optional و required چه قانونی دارد؟
10. مرز تابع چرا برای ایمنی نوع مهم است؟

## جمع‌بندی و پل جلسهٔ بعد
تابع را با قرارداد ورودی/خروجی، rest، callback و `void` سند کردید. جلسهٔ بعد همین قرارداد را روی **شکل شیء** می‌بریم: `interface` در برابر `type`.
