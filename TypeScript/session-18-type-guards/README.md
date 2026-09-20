# جلسه 18: Type Guards — is، assertion functions، narrowing سفارشی (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · از ریشهٔ `TypeScript/`: `npx tsc --noEmit` و `npx tsx`

## پیش‌نیازها
جلسات قبلی همین دوره تا مسیر منطقی موضوع. JavaScript را دوباره درس نمی‌دهیم؛ فقط لایهٔ نوع.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. type predicate با `x is T` بنویسید
2. فرق guard با boolean معمولی را بگویید
3. assertion function (`asserts x is T`) را بشناسید
4. guard برای object shape بنویسید
5. از guard در filter آرایه استفاده کنید

## چرا مهم است؟
گاهی discriminant ندارید یا داده از بیرون می‌آید. guard به کامپایلر می‌گوید بعد از چک، نوع چیست.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | انگیزه و تعریف مسئله |
| ۲۰–۴۵ | مفاهیم ۱–۳ |
| ۴۵–۷۵ | مفاهیم ۴–۶ + مثال زنده |
| ۷۵–۱۰۰ | مفاهیم ۷–۸ + اشتباه‌ها |
| ۱۰۰–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### 1. Predicate

```ts
function isString(x: unknown): x is string {
  return typeof x === "string";
}
```
بعد از `if (isString(x))` نوع x در شاخه string است.

### 2. فرق با boolean

تابع `(): boolean` narrowing ایجاد نمی‌کند حتی اگر منطقاً درست باشد. باید `x is T` باشد.

### 3. Object guard

```ts
function isUser(x: unknown): x is { id: number; name: string } {
  return typeof x === "object" && x !== null &&
    typeof (x as { id?: unknown }).id === "number" &&
    typeof (x as { name?: unknown }).name === "string";
}
```

### 4. filter با predicate

```ts
const nums = mixed.filter((x): x is number => typeof x === "number");
```

### 5. asserts

```ts
function assertString(x: unknown): asserts x is string {
  if (typeof x !== "string") throw new Error("need string");
}
```
بعد از صدا، در ادامهٔ تابع x تنگ شده — یا throw.

### 6. درستی runtime

کامپایلر به قول شما اعتماد می‌کند. اگر guard دروغ بگوید، نوع دروغ است. تست کنید.

### 7. کی discriminant، کی guard؟

داخل سیستم خودتان discriminant؛ روی مرز خارجی guard/validate.

### 8. دام over-assert

asserts همه‌جا = پرتاب زیاد. برای UI گاهی Result بهتر است.

---

## اشتباه‌های رایج

1. **تابع boolean به‌جای predicate و تعجب از نبود narrowing.**
2. **guard دروغین بدون چک کافی.**
3. **as داخل guard بدون اعتبار واقعی.**
4. **asserts برای کنترل جریان عادی UI.**
5. **فراموش null در object check.**

---

## مثال گام‌به‌گام

از ریشهٔ `TypeScript/`:

```bash
npx tsx session-18-type-guards/examples/01-is-string.ts
npx tsx session-18-type-guards/examples/02-object-guard.ts
npx tsx session-18-type-guards/examples/03-filter-guard.ts
npx tsx session-18-type-guards/examples/04-asserts.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-is-string.ts](./examples/01-is-string.ts) | predicate ساده |
| [02-object-guard.ts](./examples/02-object-guard.ts) | object guard |
| [03-filter-guard.ts](./examples/03-filter-guard.ts) | filter |
| [04-asserts.ts](./examples/04-asserts.ts) | asserts |

## الگوی بهتر

قرارداد نوع را صریح نگه دارید؛ از shortcutهای خطرناک (`any`، cast کور) برای بستن جلسه استفاده نکنید. مثال‌ها را اول بخوانید بعد تغییر دهید.

## تمرین‌ها

### آسان
isNumber(x: unknown): x is number

### چالشی
isNote برای { id: string; text: string; done: boolean }

## راهنمای حل

آسان: typeof x === "number" && !Number.isNaN(x) اختیاری.

چالش: object + typeof سه فیلد.

## خودآزمایی

1. x is T یعنی چه؟
2. چرا boolean کافی نیست؟
3. asserts چه می‌کند؟
4. filter چطور تنگ می‌شود؟
5. خطر guard دروغین؟
6. null را چطور در object چک کنیم؟
7. کی discriminant بهتر است؟
8. as در guard چه نقشی دارد؟
9. Result در برابر asserts؟
10. آیا کامپایلر درستی runtime را تضمین می‌کند؟

## جمع‌بندی و پل جلسهٔ بعد
Guard مرز را امن کرد. جلسهٔ ۱۹: as const و satisfies برای literalهای دقیق بدون از دست دادن چک.
