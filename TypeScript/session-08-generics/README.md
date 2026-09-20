# جلسه ۸: جنریک پایه — inference، extends، keyof سبک، ضد over-generic (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۵–۶. با تابع و type راحت باشید.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. تابع جنریک با `<T>` بنویسید و از inference استفاده کنید
2. `type Box<T>` و پاسخ API جنریک تعریف کنید
3. محدودیت ساده با `extends` بگذارید
4. `keyof` و `T[K]` را در حد pluck به کار ببرید
5. بگویید جنریک چه فرقی با `any` دارد و ضدالگوی over-generic چیست

## چرا مهم است؟
`identity`، `map`، کش، مخزن — بدون جنریک یا نوع را از دست می‌دهید (`any`) یا برای هر نوع یک کپی می‌نویسید.

جنریک یعنی: **نوع را پارامتر کنید** تا رابطهٔ ورودی/خروجی حفظ شود.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | T چیست؛ در برابر any |
| ۲۰–۴۵ | تابع جنریک و inference |
| ۴۵–۶۵ | type/interface جنریک |
| ۶۵–۸۵ | extends و محدودیت |
| ۸۵–۱۰۵ | keyof / T[K] سبک + ضدالگو |
| ۱۰۵–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### ۱. پارامتر نوع، نه مقدار

```ts
function first<T>(items: T[]): T | undefined {
  return items[0];
}
```

`T` در compile-time پر می‌شود. در runtime وجود ندارد (type erasure). `first([1,2])` → `T` می‌شود `number`.

### ۲. در برابر any

```ts
function firstAny(items: any[]): any {
  return items[0];
}
```

`any` رابطه را می‌کشد؛ مصرف‌کننده هر کاری با نتیجه می‌کند. جنریک همان نوع را از ورودی به خروجی **حمل** می‌کند.

### ۳. Inference

معمولاً `first<number>([1,2])` لازم نیست؛ کامپایلر از آرگومان `T` را حدس می‌زند. وقتی حدس گشاد یا غلط است (آرایهٔ خالی، union ناخواسته)، صریح بنویسید: `first<string>([])`.

### ۴. Type جنریک

```ts
type ApiResponse<T> = {
  data: T;
  status: number;
};

const res: ApiResponse<string> = { data: "ok", status: 200 };
```

یک شکل، محتوا متفاوت. همان الگو برای `Box<T>`، `Page<T>`، `Result<T, E>`.

### ۵. محدودیت extends

```ts
function labelOf<T extends { label: string }>(item: T): string {
  return item.label;
}
```

`T` هر چیزی نیست؛ باید حداقل `{ label: string }` را داشته باشد. بدون محدودیت، دسترسی به `.label` خطا است.

### ۶. keyof و T[K] سبک

```ts
function pluck<T, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key];
}
```

`keyof T` اتحاد کلیدها است. `K extends keyof T` کلید معتبر را محدود می‌کند. `T[K]` نوع مقدار آن کلید است. این پایهٔ utilityهای جلسه ۹ است.

### ۷. جنریک در برابر overload (ذهنی)

گاهی overload چند امضا می‌نویسد؛ جنریک یک رابطهٔ کلی. اگر رفتار واقعاً چندشکلِ جدا است → overload (جلسه ۲۲). اگر همان منطق برای نوع‌های مختلف است → جنریک.

### ۸. ضدالگوی over-generic

همه‌چیز را `<T, U, V, W>` کردن خوانایی را می‌کشد. جنریک را سر **مرزهای کمکی/کتابخانه** بگذارید؛ دامنهٔ بیزنس را با type نام‌دار (`User`, `Note`) مدل کنید.

```ts
// ضعیف: function save<T>(x: T): T
// بهتر برای دامنه: function saveNote(n: Note): Note
```

---

## اشتباه‌های رایج

1. **`<T>` بی‌قید روی همه توابع دامنه.** علت: over-generic؛ نام دامنه بهتر است.
2. **برگشت `any` داخل تابع جنریک.** علت: کل سود جنریک را می‌سوزاند.
3. **قاطی کردن T با مقدار runtime.** علت: T فقط نوع است.
4. **ننوشتن extends وقتی به فیلد دسترسی دارید.** علت: TS نمی‌داند T آن فیلد را دارد.
5. **فرض اینکه جنریک در runtime چک می‌شود.** علت: erasure.

---

## مثال گام‌به‌گام

```bash
npx tsx session-08-generics/examples/01-generic-functions.ts
npx tsx session-08-generics/examples/02-generic-types.ts
npx tsx session-08-generics/examples/03-constraints-keyof.ts
npx tsx session-08-generics/examples/04-inference-antipattern.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-generic-functions.ts](./examples/01-generic-functions.ts) | تابع جنریک |
| [02-generic-types.ts](./examples/02-generic-types.ts) | type جنریک |
| [03-constraints-keyof.ts](./examples/03-constraints-keyof.ts) | extends و pluck |
| [04-inference-antipattern.ts](./examples/04-inference-antipattern.ts) | inference و نکتهٔ دامنه |

## الگوی بهتر

جنریک روی کمک‌تابع‌های تکراری؛ مدل بیزنس نام‌دار؛ محدودیت `extends` وقتی به شکل نیاز دارید؛ از `any` داخل بدنهٔ جنریک فرار کنید.

## تمرین‌ها

### آسان
`function wrap<T>(value: T): { value: T }`

### چالشی
`function pluck<T, K extends keyof T>(obj: T, key: K): T[K]`

## راهنمای حل

آسان: `return { value };`

چالش: `return obj[key];` با همان امضای بالا.

## خودآزمایی

1. جنریک چه مشکلی از any را حل می‌کند؟
2. inference برای T از کجا می‌آید؟
3. `extends` روی پارامتر نوع چه می‌کند؟
4. `keyof T` یعنی چه؟
5. `T[K]` یعنی چه؟
6. type erasure یعنی چه؟
7. over-generic چه آسیبی دارد؟
8. کی نوع را صریح به تابع جنریک بدهیم؟
9. آیا دو پارامتر نوع مجاز است؟
10. جنریک در برابر overload چه ذهنیتی دارد؟

## جمع‌بندی و پل جلسهٔ بعد
نوع را پارامتر کردید بدون از دست دادن رابطه. جلسهٔ بعد: **Utility types** آمادهٔ TypeScript مثل `Partial` و `Pick` که روی همین ایده‌ها سوارند.
