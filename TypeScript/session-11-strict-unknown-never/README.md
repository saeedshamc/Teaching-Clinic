# جلسه ۱۱: strict، unknown، never — پرچم‌ها، narrowing، exhaustive (۱۲۰ دقیقه)

**محیط فرض:** TypeScript 5.x · `strict: true` در tsconfig ریشه · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۴ و ۱۰. narrowing و ماژول را بلد باشید.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. بگویید `strict` چه خانواده‌ای از پرچم‌ها را روشن می‌کند (حداقل ۳ مورد)
2. `unknown` را به‌جای `any` برای ورودی نامطمئن به کار ببرید و قبل از استفاده narrow کنید
3. `never` را در شاخهٔ غیرممکن و `assertNever` بفهمید
4. `switch` exhaustive روی literal union بسازید
5. در `catch` مقدار را `unknown` بگیرید و ایمن بخوانید

## چرا مهم است؟
بدون strict، TypeScript نصف دندان‌هایش را از دست می‌دهد: `null` قاطی می‌شود، پارامتر ضمنی `any` می‌شود، و حس امنیت کاذب می‌آید.

`unknown` یعنی «نمی‌دانم»؛ `any` یعنی «چک نکن». فرق این دو مرز حرفه‌ای و مبتدی است.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۵ | خانوادهٔ strict |
| ۲۵–۵۰ | unknown و narrowing |
| ۵۰–۷۵ | never و assertNever |
| ۷۵–۹۵ | exhaustive switch |
| ۹۵–۱۲۰ | catch unknown + تمرین |

---

## مفهوم عمیق

### ۱. strict یعنی بستهٔ سخت‌گیری

با `strict: true` معمولاً این‌ها فعال‌اند (نسخه‌ها جزئیات دارند):

- `strictNullChecks` — `null`/`undefined` جدا
- `strictFunctionTypes` — سازگاری تابع سخت‌تر
- `strictBindCallApply`
- `noImplicitAny` — پارامتر بدون نوع → خطا
- `strictPropertyInitialization`

شل کردن «چون خطا می‌داد» یادگیری را خراب می‌کند. هر پرچم را جدا بفهمید (جزئیات بیشتر جلسه ۱۳).

### ۲. unknown در برابر any

```ts
function handle(value: unknown): void {
  // value.toUpperCase(); // ❌
  if (typeof value === "string") {
    console.log(value.toUpperCase());
  }
}
```

`unknown` مجبورتان می‌کند قبل از استفاده چک کنید. `any` همه جا پخش می‌شود.

### ۳. Narrowing روی unknown

`typeof`، `instanceof`، predicate، چک فیلد — همان مهارت جلسه ۴ روی ورودی گسترده‌تر.

```ts
if (typeof value === "number") {
  console.log(value.toFixed(1));
}
```

### ۴. never — ته مسیر غیرممکن

`never` یعنی مقداری وجود ندارد. تابعی که همیشه throw می‌کند برگشت `never` دارد. در exhaustive check، شاخهٔ باقی‌مانده باید `never` باشد.

### ۵. assertNever

```ts
function assertNever(x: never): never {
  throw new Error(`unexpected: ${String(x)}`);
}
```

اگر بعداً به union مقدار جدید اضافه کنید و case فراموش شود، `assertNever` خطا می‌دهد — چون آن مقدار `never` نیست.

### ۶. Exhaustive switch

```ts
type Level = "low" | "high";

function label(level: Level): string {
  switch (level) {
    case "low":
      return "کم";
    case "high":
      return "زیاد";
    default:
      return assertNever(level);
  }
}
```

### ۷. catch unknown

```ts
try {
  JSON.parse("{");
} catch (err: unknown) {
  if (err instanceof Error) console.log(err.message);
}
```

فرض نکنید `catch (e)` همیشه `Error` است — هر چیزی پرتاب می‌شود.

### ۸. دام any برای خاموش کردن strict

`as any` و `@ts-ignore` بدهی‌اند. اگر مجبور شدید، مرز را باریک و مستند کنید (جلسه ۴۷).

---

## اشتباه‌های رایج

1. **خاموش کردن strict به‌جای درست کردن نوع.** علت: راحتی کوتاه‌مدت.
2. **unknown را مثل any با cast کور مصرف کردن.** علت: دور زدن narrowing.
3. **فراموش default/assertNever وقتی union رشد می‌کند.** علت: case جا می‌ماند.
4. **catch را Error فرض کردن.** علت: throw هر چیزی مجاز است.
5. **noImplicitAny را دست‌کم گرفتن.** علت: پارامتر بی‌نوع سوراخ است.

---

## مثال گام‌به‌گام

```bash
npx tsx session-11-strict-unknown-never/examples/01-unknown-narrow.ts
npx tsx session-11-strict-unknown-never/examples/02-never-exhaustive.ts
npx tsx session-11-strict-unknown-never/examples/03-catch-unknown.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-unknown-narrow.ts](./examples/01-unknown-narrow.ts) | unknown + typeof |
| [02-never-exhaustive.ts](./examples/02-never-exhaustive.ts) | never و switch |
| [03-catch-unknown.ts](./examples/03-catch-unknown.ts) | catch ایمن |

## الگوی بهتر

ورودی خارجی: `unknown` → validate/narrow → نوع دامنه. Union وضعیت: exhaustive با `never`. Strict را روشن نگه دارید.

## تمرین‌ها

### آسان
تابع `asString(value: unknown): string | undefined` که فقط برای string مقدار برگرداند.

### چالشی
`type Status = "idle" | "go" | "stop"` و `label(status)` با assertNever.

## راهنمای حل

آسان: `if (typeof value === "string") return value;`

چالش: switch سه case + default assertNever.

## خودآزمایی

1. سه پرچم زیرمجموعهٔ strict نام ببرید.
2. فرق unknown و any؟
3. قبل از استفاده از unknown چه باید کرد؟
4. never یعنی چه؟
5. assertNever چه کمکی به رشد union می‌کند؟
6. چرا catch را Error ندانیم؟
7. شل کردن strict چه آسیبی دارد؟
8. noImplicitAny چه جلوی چه را می‌گیرد؟
9. exhaustive یعنی چه؟
10. cast به any چه بدهی‌ای می‌سازد؟

## جمع‌بندی و پل جلسهٔ بعد
سخت‌گیری و ورودی نامطمئن را با unknown/never مهار کردید. جلسهٔ بعد: همین‌ها روی **Promise و JSON و API**.
