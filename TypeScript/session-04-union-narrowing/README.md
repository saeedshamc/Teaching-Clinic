# جلسه ۴: Union، literal types، narrowing (`typeof` / `in` / truthiness) (۱۲۰ دقیقه)

**محیط فرض:** Node LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱–۳: primitive، آرایه/object، `type` alias.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. Union بنویسید (`string | number`) و بگویید چرا قبل از استفاده باید **تنگ** شود
2. Literal type بسازید (`"idle" | "loading" | "error"`) به‌جای `string` باز
3. با `typeof`، truthiness، و `in` مقدار را narrow کنید
4. discriminated union ساده با فیلد `kind` یا `status` بخوانید و handle کنید
5. بگویید narrowing فقط در محدوده‌ای که کامپایلر می‌بیند معتبر است (پس از function call ممکن است از دست برود)

## چرا مهم است؟
دنیای واقعی یا-یا است: پاسخ API یا داده است یا خطا؛ فیلد یا عدد است یا `null`. اگر union را مثل یک نوع واحد مصرف کنید، `undefined.toUpperCase` در runtime منتظر است.

Narrowing یعنی **با یک چک واقعی، نوع در آن شاخه دقیق‌تر می‌شود**. این مهارت پایه‌ای همهٔ جلسات بعدی است — به‌خصوص async و React.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | union چیست؛ چرا بدون narrowing غیرقابل‌استفاده است |
| ۲۰–۴۰ | literal types و جایگزینی string باز |
| ۴۰–۷۰ | typeof، truthiness، in، equality |
| ۷۰–۹۵ | discriminated union |
| ۹۵–۱۲۰ | مثال، تمرین، خودآزمایی |

---

## مفهوم عمیق

### ۱. Union: این یا آن (گاهی هر دو شکل در طول عمر)

```ts
type Id = string | number;
```

مقدار یا رشته است یا عدد — در هر لحظه یکی. متد اختصاصی یک طرف روی union خام خطا است:

```ts
function show(id: string | number) {
  // id.toUpperCase(); // ❌ number این متد را ندارد
}
```

باید اول شاخه را جدا کنید.

### ۲. Narrowing با `typeof`

```ts
function show(id: string | number) {
  if (typeof id === "string") {
    console.log(id.toUpperCase()); // اینجا string است
  } else {
    console.log(id.toFixed(0)); // اینجا number است
  }
}
```

`typeof` در TS با `typeof` در JS یکی است. دام: `typeof null === "object"`. برای `null` از `=== null` استفاده کنید نه `typeof`.

```ts
function len(text: string | null) {
  if (text === null) return 0;
  return text.length;
}
```

### ۳. Truthiness — مفید و خطرناک

```ts
function label(name?: string) {
  if (name) {
    return name.trim();
  }
  return "بی‌نام";
}
```

`if (name)` هم `undefined` و هم `""` را رد می‌کند. اگر رشتهٔ خالی برای شما معنادار است، چک اشتباه است. دقیق‌تر:

```ts
if (name !== undefined && name !== null) {
  // string — حتی اگر ""
}
```

قانون: truthiness برای «چیزی برای نمایش هست» خوب است؛ برای تمایز `0` و `""` از نبود مقدار، بد است. `0` هم falsy است:

```ts
function scale(n: number | undefined) {
  if (!n) {
    // اینجا هم undefined هم 0 می‌آید
  }
}
```

برای عدد، `n === undefined` یا `n == null` دقیق‌تر است.

### ۴. عملگر `in` برای شیءها

```ts
type User = { name: string };
type Guest = { anon: true };

function greet(who: User | Guest) {
  if ("name" in who) {
    return who.name;
  }
  return "مهمان";
}
```

`in` وقتی شکل‌ها فیلدهای متفاوت دارند مفید است. وقتی هر دو فیلد هم‌نام با نوع متفاوت دارند، discriminated union بهتر است.

### ۵. Literal types

```ts
type Mode = "read" | "write";
const mode: Mode = "read";
// const bad: Mode = "admin"; // ❌
```

به‌جای `status: string` که هر چیزی قبول می‌کند، مجموعهٔ بسته بنویسید. ادیتور autocomplete می‌دهد؛ غلط املایی (`"laoding"`) خطا می‌شود.

ترکیب با `const`:

```ts
const status = "ok"; // نوع literal "ok"
let status2 = "ok";  // معمولاً string
```

اگر می‌خواهید `let` هم literal بماند:

```ts
let status2: "ok" | "err" = "ok";
```

### ۶. Discriminated union — الگوی حرفه‌ای روزمره

یک فیلد مشترک با literalهای متفاوت (discriminant):

```ts
type Ok = { ok: true; value: string };
type Fail = { ok: false; error: string };
type Result = Ok | Fail;

function handle(r: Result) {
  if (r.ok) {
    return r.value; // TS می‌داند error اینجا نیست
  }
  return r.error;
}
```

همین الگو برای state UI:

```ts
type LoadState =
  | { status: "idle" }
  | { status: "loading" }
  | { status: "success"; data: string }
  | { status: "error"; message: string };
```

`switch (state.status)` هر شاخه را تنگ می‌کند. در جلسه ۱۱ با `never` exhaustive بودن را محکم می‌کنیم.

### ۷. Narrowing از دست می‌رود

کامپایلر جریان کنترل را داخل همان تابع می‌بیند. اگر مقدار را به تابع دیگری بدهید که ممکن است جهش دهد، narrowing ممکن است معتبر نماند — به‌خصوص روی property شیء قابل‌تغییر.

برای آموزش: روی **پارامتر و const محلی** چک کنید، نه روی فیلد شیء که جای دیگر عوض می‌شود.

```ts
function isString(x: unknown): x is string {
  return typeof x === "string";
}
```

Type predicate (`x is string`) جلسه ۱۱/۸ کامل می‌شود؛ فعلاً اگر دیدید، یعنی تابع به کامپایلر قول narrowing می‌دهد. در این جلسه از `typeof` مستقیم استفاده کنید.

### ۸. Union در آرایه و فیلد

```ts
type Cell = number | null;
const row: Cell[] = [1, null, 2];
```

قبل از جمع زدن، `null` را فیلتر کنید:

```ts
const nums = row.filter((c): c is number => c !== null);
```

predicate اختیاری است؛ می‌توانید حلقه با `if (c !== null)` هم بنویسید.

---

## اشتباه‌های رایج

1. **صدا زدن متد یک طرف روی union خام.**
2. **`if (n)` برای `number | undefined` و بلعیدن صفر.**
3. **`status: string` به‌جای literal union.**
4. **دو شیء شبیه هم بدون discriminant** و جنگ با `in`.
5. **فرض اینکه `typeof null === "null"`.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-04-union-narrowing/examples/01-union-typeof.ts
npx tsx session-04-union-narrowing/examples/02-literals.ts
npx tsx session-04-union-narrowing/examples/03-in-truthiness.ts
npx tsx session-04-union-narrowing/examples/04-discriminated.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-union-typeof.ts](./examples/01-union-typeof.ts) | union + typeof |
| [02-literals.ts](./examples/02-literals.ts) | literal types |
| [03-in-truthiness.ts](./examples/03-in-truthiness.ts) | in و دام falsy |
| [04-discriminated.ts](./examples/04-discriminated.ts) | Result و LoadState |

## الگوی بهتر

برای نتیجهٔ عملیات: `{ ok: true, value } | { ok: false, error }`.
برای UI: فیلد `status` با literalها.
برای مقدار غایب: `T | null` یا `T | undefined` را آگاهانه انتخاب کنید و همان را در کل ماژول ثابت نگه دارید — قاطی نکنید.

## تمرین‌ها

### آسان
تابع `formatId(id: string | number): string` که اگر عدد بود با `#` پیشوند بدهد و اگر رشته بود trim کند.

### چالشی
`type RequestState` با چهار وضعیت idle/loading/success/error. تابع `message(state)` که برای هر وضعیت یک رشتهٔ فارسی برگرداند. در success از `data` و در error از `message` استفاده کنید.

## راهنمای حل

آسان: `if (typeof id === "number") return "#" + id; return id.trim();`

چالش: `switch (state.status)` با چهار `case`. فیلد data فقط در success وجود دارد؛ به آن در شاخهٔ idle دست نزنید.

## خودآزمایی

1. چرا `id.toUpperCase()` روی `string | number` خطا است؟
2. `typeof null` چیست؟
3. دام `if (n)` روی عدد چیست؟
4. literal type چه کمکی به غلط املایی می‌کند؟
5. discriminant یعنی چه؟
6. `in` کی مناسب است؟
7. تفاوت `let x = "ok"` و `const x = "ok"` در نوع؟
8. narrowing یعنی چه؟
9. چرا `status: string` برای ماشین حالت ضعیف است؟
10. در `Result`، بعد از `if (r.ok)` به `error` دسترسی دارید؟

## جمع‌بندی و پل جلسهٔ بعد
Union را با چک واقعی تنگ می‌کنید؛ literal جلوی رشتهٔ باز را می‌گیرد. جلسهٔ بعد همین مهارت را روی **توابع** پیاده می‌کنیم: پارامتر، برگشت، optional/default، rest.
