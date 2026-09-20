# جلسه ۲: Annotation در برابر inference؛ primitiveها؛ `any` ممنوع به‌عنوان عادت (۱۲۰ دقیقه)

**محیط فرض:** Node LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۱ — بتوانید پیام `tsc` را بخوانید و فرق کامپایل با runtime را بگویید.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. بگویید کامپایلر کی نوع را **حدس می‌زند** (inference) و کی باید **بنویسید** (annotation)
2. شش primitive پرکاربرد را با مثال از هم جدا کنید: `string`, `number`, `boolean`, `null`, `undefined`, `bigint`
3. تفاوت `const` و `let` را روی عرض inference توضیح دهید
4. `any` را به‌عنوان فرار از سیستم نوع تشخیص دهید و جایگزین امن‌تر را نام ببرید (`unknown` مال جلسه ۱۱ است؛ فعلاً نوع دقیق)
5. حداقل سه جا که annotation لازم است و سه جا که اضافی است فهرست کنید

## چرا مهم است؟
اگر همه‌جا نوع بنویسید، کد شلوغ و viscose می‌شود. اگر هیچ‌جا ننویسید، مرز توابع مبهم می‌ماند و `any` از در پشتی می‌آید.

حرفه‌ای‌ها **کم می‌نویسند، دقیق می‌نویسند**: ورودی/خروجی عمومی را سند می‌کنند؛ داخل تابع می‌گذارند کامپایلر حدس بزند.

`any` یعنی «از این به بعد چک نکن». یک `any` می‌تواند مثل جوهر در کل ماژول پخش شود.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | inference یعنی چه؛ مثال `let` / `const` |
| ۲۰–۴۵ | primitiveها و دام‌های JS که TS حفظ کرده (`NaN`, `typeof null`) |
| ۴۵–۷۰ | کجا annotation اجباری/مفید است |
| ۷۰–۹۰ | چرا `any` عادت ممنوع است؛ ویروس نوع |
| ۹۰–۱۲۰ | مثال‌ها، تمرین، خودآزمایی |

---

## مفهوم عمیق

### ۱. Inference: کامپایلر حدس می‌زند

```ts
let title = "یادداشت"; // string
let count = 3;         // number
let ok = true;         // boolean
```

نیازی به `: string` روی `title` نیست. مقدار اولیه نوع را قفل می‌کند.

```ts
title = "عنوان جدید"; // ✅
// title = 12;        // ❌ number به string نمی‌رود
```

Inference از **مقدار** می‌آید، نه از اسم متغیر. اسم `count` هیچ کمکی نمی‌کند اگر مقدار `"سه"` باشد.

### ۲. `const` تنگ‌تر از `let`

```ts
const max = 100;
// نوع max در بسیاری از حالت‌ها literal 100 است نه number عمومی
```

`let n = 100` معمولاً `number` است چون بعداً ممکن است `n = 99` کنید.
`const n = 100` عوض نمی‌شود؛ پس کامپایلر می‌تواند همان `100` را نوع بداند.

این تفاوت در جلسهٔ ۴ (literal types) می‌ترکد. فعلاً بدانید: **هرچه متغیر ثابت‌تر، inference دقیق‌تر**.

### ۳. Annotation: شما قرارداد را می‌نویسید

```ts
function parseScore(raw: string): number {
  const n = Number(raw);
  return n;
}
```

بدون annotation روی پارامتر، `raw` می‌شود `any` (در حالت غیر `noImplicitAny` — و `strict` این دوره `noImplicitAny` را روشن دارد). پس **پارامتر تابع بدون نوع، خطا است** نه آزادی.

خروجی را گاهی می‌نویسید تا قرارداد تابع سند شود؛ گاهی می‌گذارید حدس بزند:

```ts
function parseScore(raw: string) {
  return Number(raw); // برگشت number حدس زده می‌شود
}
```

هر دو درست‌اند. الگوی بهتر برای **تابع عمومی/صادراتی**: برگشت را بنویسید تا عوض کردن `return` تصادفی، قرارداد را نشکند.

### ۴. کجا annotation لازم است؟

| مکان | چرا |
|------|-----|
| پارامتر تابع | وگرنه `noImplicitAny` |
| برگشت تابع عمومی | قرارداد پایدار برای caller |
| متغیر بدون مقدار اولیه | `let x;` نوع ندارد |
| دادهٔ خارجی که از JS می‌آید | کامپایلر حدس واقعی ندارد |

```ts
let pending: number;
pending = 2; // بدون annotation، خطا: Variable 'pending' implicitly has type 'any'
```

### ۵. کجا annotation اضافی است؟

```ts
const name: string = "علی"; // : string معمولاً نویز است
const items: number[] = [1, 2, 3]; // اگر آرایه همگن و واضح است، اغلب لازم نیست
```

قانون شست: اگر با پاک کردن annotation هیچ اطلاعاتی از دست نرود، پاکش کنید.

### ۶. Primitiveها — مدل واقعی JS

TypeScript primitiveهای JS را **اختراع نکرد**؛ همان‌ها را نام‌گذاری کرد.

```ts
const s: string = "متن";
const n: number = 3.14;      // int جدا نداریم
const b: boolean = false;
const z: null = null;
const u: undefined = undefined;
const big: bigint = 10n;
```

دام‌های مهم:

- **`number` شامل `NaN` و `Infinity` است.** نوع عدد ≠ عدد مفید.
- **`typeof null === "object"` در JS** — برای narrowing از `typeof` روی null استفاده نکنید (جلسه ۴).
- **`string` در برابر `String`:** شیء جعبه (`new String()`) را در کد جدید نخواهید. نوع کوچک (`string`) درست است.
- **`undefined` یعنی مقدار نیست؛ `null` یعنی عمداً خالی.** در این دوره هر دو را جدی بگیرید؛ `strictNullChecks` روشن است پس `string` برابر `null` نیست.

```ts
let label: string = "ok";
// label = null;      // ❌
// label = undefined; // ❌
```

اگر مقدار ممکن است نباشد:

```ts
let label: string | null = null; // union — جلسه ۴
```

فعلاً اگر لازم شد از `|` استفاده کنید؛ عمیق‌اش جلسه بعد از بعد است. هدف این جلسه: نگذارید `null` بی‌صدا داخل `string` برود.

### ۷. `any` — خاموش کردن آژیر

```ts
let data: any = "سلام";
data = 12;
data.foo.bar(); // tsc چیزی نمی‌گوید؛ runtime ممکن است بترکد
```

`any` از هر نوع قبول می‌کند و به هر نوع قابل انتساب است. یک ورودی `any` خروجی‌ها را هم آلوده می‌کند:

```ts
function wrap(x: any) {
  return x;
}
const y = wrap(1); // y هم any است
```

به این می‌گویند **ویروس any**.

جایگزین این جلسه: نوع واقعی را بنویسید. اگر واقعاً نمی‌دانید چیست، جلسه ۱۱ `unknown` را می‌دهد — نه `any`.

`noImplicitAny` جلوی any **پنهان** را می‌گیرد. `any` **آشکار** (`: any`) هنوز ممکن است. مسئولیت با شماست که ننویسید.

### ۸. Annotation در برابر assertion

```ts
const n = Number("42"); // inference: number
const forced = "42" as unknown as number; // دروغ به کامپایلر — جلسه ۱۱
```

`as` موضوع این جلسه نیست. اگر وسوسه شدید با `as` خطا را ببندید، یعنی قرارداد را درست ننوشتید.

---

## اشتباه‌های رایج

1. **نوشتن `: string` روی هر `const` واضح.** نویز؛ تمرکز را از قراردادهای واقعی می‌گیرد.
2. **پارامتر بدون نوع و بعد `// @ts-nocheck`.** صورت‌مسئله را پاک کردید.
3. **`any` برای «فعلاً رد شوم».** فعلاً تبدیل به هرگز می‌شود.
4. **گیج کردن `number` با «عدد صحیح معتبر».** `NaN` از نظر نوع number است.
5. **استفاده از `String` / `Number` / `Boolean` به‌عنوان نوع.** primitive کوچک را بخواهید.

---

## مثال گام‌به‌گام

```bash
npx tsx session-02-annotation-inference/examples/01-inference.ts
npx tsx session-02-annotation-inference/examples/02-primitives.ts
npx tsx session-02-annotation-inference/examples/03-where-to-annotate.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-inference.ts](./examples/01-inference.ts) | حدس کامپایلر روی let/const |
| [02-primitives.ts](./examples/02-primitives.ts) | primitiveها و دام‌ها |
| [03-where-to-annotate.ts](./examples/03-where-to-annotate.ts) | کجا بنویسید / ننویسید |
| [04-any-trap.ts](./examples/04-any-trap.ts) | چرا any عادت نیست |

## الگوی بهتر

- پارامتر و API عمومی: annotation
- متغیر محلی با مقدار واضح: inference
- `strict` روشن بماند
- `any` فقط اگر در کامنت علت بنویسید — در این دوره تمرین‌ها علت قابل قبول ندارند؛ ننویسید

## تمرین‌ها

### آسان
تابع `isAdult(age: number): boolean` بنویسید که برای `age >= 18` مقدار true بدهد. دو متغیر با inference بسازید و صدا بزنید. عمداً رشته بدهید و خطا را بخوانید.

### چالشی
تابعی بنویسید که یک `raw: string` بگیرد و اگر `Number(raw)` متناهی بود همان عدد را برگرداند، وگرنه `null`. نوع برگشت را صریح بنویسید. توضیح دهید چرا برگشت `number` به‌تنهایی دروغ است.

## راهنمای حل

آسان: `return age >= 18;` — آرگومان رشته `TS2345`.

چالش: `: number | null` و `Number.isFinite(n) ? n : null`. اگر فقط `number` بگذارید، caller فکر می‌کند همیشه عدد دارد و `null` را handle نمی‌کند.

## خودآزمایی

1. inference از کجا می‌آید؟
2. چرا پارامتر بدون نوع در `strict` خطا است؟
3. سه جا که annotation مفید است؟
4. سه جا که annotation نویز است؟
5. `const` چه فرقی در inference با `let` دارد؟
6. چرا `NaN` با نوع `number` جور است؟
7. `any` چرا ویروس است؟
8. تفاوت `noImplicitAny` با ممنوع کردن `any` آشکار چیست؟
9. `string` در برابر `String`؟
10. اگر مقدار ممکن است نباشد، چه باید به نوع اضافه شود؟ (اشاره: union)

## جمع‌بندی و پل جلسهٔ بعد
نوع را کم و دقیق می‌نویسید؛ primitiveها را می‌شناسید؛ `any` را عادت نمی‌کنید. جلسهٔ بعد ساختارهای داده: آرایه، tuple، object type و `type` alias.
