# جلسه 19: as const و satisfies — literal دقیق با حفظ چک (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · از ریشهٔ `TypeScript/`: `npx tsc --noEmit` و `npx tsx`

## پیش‌نیازها
جلسات ۳–۴ و ۱۷.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. اثر as const روی آرایه و شیء را نشان دهید
2. فرق let و const در literal narrowing را بگویید
3. satisfies را برای اعتبارسنجی شکل بدون پهن کردن نوع به کار ببرید
4. بگویید کی as کافی نیست و خطرناک است
5. یک نقشهٔ تنظیمات با as const + satisfies بسازید

## چرا مهم است؟
گاهی می‌خواهید literalهای دقیق نگه دارید بدون اینکه چک ساختاری را از دست بدهید. `as const` و `satisfies` همین کار را می‌کنند.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | انگیزه و مسئله |
| ۲۰–۴۵ | مفاهیم پایهٔ جلسه |
| ۴۵–۷۵ | مفاهیم میانی + مثال |
| ۷۵–۱۰۰ | دام‌ها و الگوی بهتر |
| ۱۰۰–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### 1. const و literal

```ts
const status = "ok"; // نوع "ok"
let status2 = "ok"; // معمولاً string
```

### 2. as const

```ts
const roles = ["admin", "user"] as const;
// readonly ["admin", "user"]
type Role = (typeof roles)[number];
```

### 3. شیء as const

همهٔ فیلدها readonly و literal می‌شوند — عالی برای نقشهٔ ثابت.

### 4. satisfies

```ts
const cfg = {
  host: "localhost",
  port: 3000,
} satisfies { host: string; port: number };
```
شکل را چک می‌کند ولی نوع استنباط‌شدهٔ دقیق‌تر را نگه می‌دارد.

### 5. as در برابر satisfies

`as` به کامپایلر دستور می‌دهد؛ ممکن است دروغ باشد. `satisfies` چک می‌کند و دروغ را رد می‌کند.

### 6. ترکیب رایج

```ts
const routes = {
  home: "/",
  user: "/users/:id",
} as const satisfies Record<string, string>;
```

### 7. دام as const روی دادهٔ پویا

دادهٔ API را as const نکنید؛ برای ثابت‌های منبع کد است.

### 8. readonly عمیق

as const تو در تو را هم قفل می‌کند؛ برای mutate بعدی مناسب نیست.

---

## اشتباه‌های رایج

1. **as به‌جای satisfies برای «ساکت کردن» خطا.**
2. **as const روی JSON بیرونی.**
3. **انتظار mutate بعد از as const.**
4. **فراموش استخراج union با typeof arr[number].**
5. **satisfies را با annotation ساده یکی دانستن.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-19-const-satisfies/examples/01-as-const-array.ts
npx tsx session-19-const-satisfies/examples/02-as-const-object.ts
npx tsx session-19-const-satisfies/examples/03-satisfies.ts
npx tsx session-19-const-satisfies/examples/04-combo.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-as-const-array.ts](./examples/01-as-const-array.ts) | آرایه as const |
| [02-as-const-object.ts](./examples/02-as-const-object.ts) | شیء as const |
| [03-satisfies.ts](./examples/03-satisfies.ts) | satisfies |
| [04-combo.ts](./examples/04-combo.ts) | ترکیب |

## الگوی بهتر

ثابت‌های پیکربندی: as const؛ اعتبار شکل: satisfies؛ دادهٔ خارجی: validate نه cast.

## تمرین‌ها

### آسان
آرایهٔ ["fa","en"] as const و type Lang از آن.

### چالشی
شیء theme با satisfies روی { primary: string; danger: string } و نگه داشتن literalها.

## راهنمای حل

آسان: type Lang = (typeof langs)[number]

چالش: const theme = { primary: "#0af", danger: "#f33" } as const satisfies {...}

## خودآزمایی

1. as const چه می‌کند؟
2. satisfies چه می‌کند؟
3. فرق as و satisfies؟
4. چطور Role از آرایه بگیریم؟
5. کی as const نکنیم؟
6. readonly یعنی چه؟
7. let در برابر const برای literal؟
8. ترکیب as const satisfies؟
9. آیا as درستی را تضمین می‌کند؟
10. پل جلسهٔ بعد؟

## جمع‌بندی و پل جلسهٔ بعد
Literalها دقیق شدند. جلسهٔ ۲۰: enum — کی مفید است و کی بهتر است union بمانید.
