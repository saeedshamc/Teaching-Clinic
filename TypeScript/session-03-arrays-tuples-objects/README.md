# جلسه ۳: آرایه، tuple، object type، `type` alias (۱۲۰ دقیقه)

**محیط فرض:** Node LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱–۲: خواندن خطا، inference در برابر annotation، primitiveها، پرهیز از `any`.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. آرایهٔ همگن (`number[]` / `Array<number>`) بسازید و متدهای `map`/`filter` را با نوع درست مصرف کنید
2. tuple را از آرایه تشخیص دهید و بگویید کی طول و ترتیب ثابت مهم است
3. object type با property اجباری، اختیاری (`?`) و `readonly` بنویسید
4. `type` alias بسازید و تکرار شکل شیء را حذف کنید
5. بگویید index signature چیست و چرا در کد روزمره اول سراغش نروید

## چرا مهم است؟
تقریباً هر API و هر state فرانت یک «لیست از شیء» است. اگر شکل شیء را شل بنویسید، `undefined` در UI ظاهر می‌شود. اگر tuple را با آرایه عوضی بگیرید، ترتیب فیلدها بی‌صدا جابه‌جا می‌شود.

`type` alias اسم روی قرارداد می‌گذارد: به‌جای کپی شکل در پنج تابع، یک نام دارید.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۵ | آرایهٔ همگن؛ `T[]` در برابر `Array<T>` |
| ۲۵–۴۵ | tuple و تفاوت با آرایه |
| ۴۵–۷۵ | object type، optional، readonly |
| ۷۵–۹۵ | type alias و تکرارنکردن شکل |
| ۹۵–۱۲۰ | مثال، تمرین، خودآزمایی |

---

## مفهوم عمیق

### ۱. آرایه یعنی «صفر یا چند تا از یک نوع»

```ts
const scores: number[] = [18, 20, 14];
const names: Array<string> = ["سارا", "علی"];
```

`T[]` و `Array<T>` یکی‌اند؛ در این دوره `T[]` را ترجیح می‌دهیم چون کوتاه است.

Inference:

```ts
const scores = [18, 20, 14]; // number[]
```

اگر آرایه خالی باشد، کامپایلر نمی‌تواند عنصر را حدس بزند:

```ts
const scores = []; // در strict اغلب never[] یا any[] بسته به زمینه — بهتر است نوع بنویسید
const better: number[] = [];
```

آرایه **همگن** است: همهٔ عنصرها یک نوع. مخلوط بدون union:

```ts
// const mixed: number[] = [1, "دو"]; // ❌
```

با union (جلسه ۴) می‌شود `Array<number | string>`؛ اول سعی کنید مدل را همگن نگه دارید.

### ۲. متدهای آرایه نوع را حمل می‌کنند

```ts
const doubled = scores.map((n) => n * 2); // number[]
const high = scores.filter((n) => n >= 18); // number[]
```

پارامتر callback از نوع عنصر آرایه حدس زده می‌شود. اگر `scores` را `any[]` کنید، کل زنجیره آلوده می‌شود.

`scores[99]` در JS مقدار `undefined` می‌دهد. با `strict` پیش‌فرض این دوره، نوع عنصر هنوز `number` است مگر `noUncheckedIndexedAccess` (جلسه ۱۳). پس **دسترسی با ایندکس را از نظر منطق چک کنید**؛ کامپایلر پیش‌فرض وجود عنصر را تضمین نمی‌کند در runtime.

### ۳. Tuple: طول و ترتیب بخشی از نوع‌اند

```ts
type Point = [number, number];
const p: Point = [10, 20];
```

آرایه: «چند عدد». Tuple: «دقیقاً دو عدد به نام x سپس y» — حتی اگر اسم فیلد نداشته باشند.

```ts
const entry: [string, number] = ["age", 21];
```

کاربرد واقعی: جفت کلید/مقدار، برگشت چندتایی سبک بدون object، مختصات.

آنچه tuple را خراب می‌کند:

```ts
const entry: [string, number] = [21, "age"]; // ❌ ترتیب
// entry.push(1); // از نظر آرایه JS ممکن است؛ از نظر مدل tuple خطرناک
```

برای داده با فیلد نام‌دار، **object بهتر از tuple** است:

```ts
type Player = { name: string; score: number };
```

خواننده `player.score` را می‌فهمد؛ `tuple[1]` را باید حفظ کند.

`readonly [number, number]` جلوی جهش را تا حدی می‌گیرد. اگر مختصات نباید عوض شود، readonly بگذارید.

### ۴. Object type: قرارداد شکل

```ts
const note: { title: string; done: boolean } = {
  title: "خرید",
  done: false,
};
```

Property اضافه که در نوع نیست، هنگام ساخت literal معمولاً خطا می‌گیرد (excess property check):

```ts
const note: { title: string } = {
  title: "خرید",
  // extra: true, // ❌
};
```

این چک روی **object literal مستقیم** سخت‌گیر است. اگر اول در متغیر بدون نوع بسازید و بعد نسبت دهید، ممکن است شل‌تر شود. برای یادگیری: literal را مستقیم با نوع بسازید تا اضافه را ببینید.

### ۵. اختیاری در برابر `undefined`

```ts
type Note = {
  title: string;
  tag?: string; // ممکن است کلید نباشد
};
```

`tag?: string` یعنی کلید می‌تواند نباشد، یا `string` باشد. با `exactOptionalPropertyTypes` (این دوره فعلاً خاموش است) فرق `?` و `| undefined` باریک‌تر می‌شود. فعلاً:

- فیلد لازم که مقدار ندارد: مدل را دروغ نکنید؛ `?` یا `| null` بگذارید
- `note.tag` را بدون چک چاپ نکنید

```ts
function printTag(note: { tag?: string }) {
  if (note.tag !== undefined) {
    console.log(note.tag.toUpperCase());
  }
}
```

### ۶. `readonly`

```ts
type Note = {
  readonly id: string;
  title: string;
};

const n: Note = { id: "n1", title: "الف" };
n.title = "ب";
// n.id = "n2"; // ❌
```

`readonly` در compile-time است؛ در runtime کسی می‌تواند شیء را عوض کند. برای آموزش و API داخلی خیلی مفید است: «شناسه بعد از ساخت عوض نمی‌شود».

روی آرایه:

```ts
const xs: readonly number[] = [1, 2];
// xs.push(3); // ❌
```

### ۷. `type` alias — اسم روی قرارداد

```ts
type Note = {
  title: string;
  done: boolean;
};

function complete(note: Note): Note {
  return { ...note, done: true };
}
```

بدون alias، شکل در هر تابع کپی می‌شود و یک فیلد جدید یعنی پنج جا باید عوض شود.

قواعد نام: PascalCase برای شکل دامنه (`Note`, `UserId`). Alias می‌تواند union یا tuple هم باشد:

```ts
type Id = string;
type Pair = [string, number];
```

`type Id = string` گاهی مفید است برای معنا؛ گاهی فقط نویز است. وقتی معنا فرق دارد (مثلاً نباید `UserId` را با `NoteId` عوضی بگیرید) اسم ارزش دارد — هرچند TS به‌صورت پیش‌فرض این دو را اگر هر دو string باشند عوضی می‌پذیرد (nominal نیست). انتظار معجزه نداشته باشید؛ alias مستندسازی است نه برند جدا در runtime.

### ۸. Index signature — درِ پشتی

```ts
type Dict = { [key: string]: number };
```

یعنی «هر کلید رشته، مقدار عدد». انعطاف زیاد، ایمنی کم: `dict.anything` از نظر نوع عدد است حتی اگر نباشد.

برای شیء با کلیدهای معلوم، فیلدهای نام‌دار بنویسید. `Record<K, V>` جلسه ۹ است.

---

## اشتباه‌های رایج

1. **آرایهٔ خالی بدون نوع** و بعد هل دادن نوع اشتباه.
2. **Tuple را مثل آرایهٔ نامحدود استفاده کردن** (`push` ذهنی).
3. **شیء با فیلد اختیاری را مثل اجباری خواندن** (`note.tag.toUpperCase()` بدون چک).
4. **کپی شکل در هر تابع** به‌جای `type` alias.
5. **Index signature برای مدل دامنهٔ معلوم** — همه چیز `string` می‌شود.

---

## مثال گام‌به‌گام

```bash
npx tsx session-03-arrays-tuples-objects/examples/01-arrays.ts
npx tsx session-03-arrays-tuples-objects/examples/02-tuples.ts
npx tsx session-03-arrays-tuples-objects/examples/03-objects.ts
npx tsx session-03-arrays-tuples-objects/examples/04-aliases.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-arrays.ts](./examples/01-arrays.ts) | آرایهٔ همگن |
| [02-tuples.ts](./examples/02-tuples.ts) | tuple در برابر object |
| [03-objects.ts](./examples/03-objects.ts) | optional و readonly |
| [04-aliases.ts](./examples/04-aliases.ts) | type alias |

## الگوی بهتر

مدل دامنه را با `type` نام‌گذاری کنید؛ لیست را `Note[]` کنید؛ برای رکورد نام‌دار object؛ برای جفت کوتاه و محلی tuple. فیلد هویت را `readonly` کنید. Index signature را پیش‌فرض نکنید.

## تمرین‌ها

### آسان
`type Book = { title: string; pages: number }` و یک آرایهٔ دو کتاب. عناوین را با `map` چاپ کنید.

### چالشی
`type Task` با `readonly id`, `title`, `done`, و `tag?`. تابع `close(task: Task): Task` که `done: true` برگرداند بدون عوض کردن `id`. یک task بدون tag و یکی با tag بسازید.

## راهنمای حل

آسان: `books.map((b) => b.title)`.

چالش: `return { ...task, done: true };` — spread فیلدها را حفظ می‌کند. `id` را بازنویسی نکنید. برای tag از `if (task.tag)` استفاده کنید اگر چاپ می‌کنید.

## خودآزمایی

1. `T[]` و `Array<T>` چه فرقی دارند؟
2. آرایهٔ خالی را چرا باید annotate کرد؟
3. tuple چه اطلاعاتی بیش از آرایه دارد؟
4. کی object بهتر از tuple است؟
5. `tag?: string` یعنی چه؟
6. `readonly` در runtime چه می‌کند؟
7. excess property check روی چیست؟
8. فایدهٔ `type` alias چیست؟
9. index signature چه خطری دارد؟
10. `map` روی `number[]` چه نوعی برمی‌گرداند اگر تابع عنصر را دو برابر کند؟

## جمع‌بندی و پل جلسهٔ بعد
شکل داده را نام گذاشتید. جلسهٔ بعد: وقتی مقدار می‌تواند «این یا آن» باشد — union، literal types، و narrowing.
