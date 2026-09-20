# جلسه ۱۰: ماژول‌ها و نوع — import type، @types، ambient کوتاه (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `module`/`moduleResolution`: NodeNext · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۵–۹ · ماژول ES در مسیر JS. اینجا فقط لایهٔ نوع روی import/export.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. `export type` و `import type` را درست به کار ببرید و بگویید چرا مهم است
2. مقدار و نوع را از یک ماژول با هم export/import کنید
3. نقش `@types/*` را توضیح دهید
4. یک ambient کوتاه (`.d.ts` shim) را بخوانید
5. side-effect import را بشناسید و بی‌دلیل استفاده نکنید

## چرا مهم است؟
در پروژهٔ واقعی نوع‌ها بین فایل‌ها جابه‌جا می‌شوند. اگر type را مثل مقدار import کنید، bundler گاهی کد اضافه نگه می‌دارد یا با `verbatimModuleSyntax` خطا می‌گیرید. `import type` قرارداد را روشن می‌کند: **فقط برای کامپایلر**.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | export مقدار و type |
| ۲۰–۴۵ | import type و import ترکیبی |
| ۴۵–۶۵ | پسوند `.js` در import با NodeNext |
| ۶۵–۸۵ | @types و DefinitelyTyped ذهنی |
| ۸۵–۱۰۵ | ambient کوتاه و side-effect |
| ۱۰۵–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### ۱. Export نوع و مقدار

```ts
export type User = { id: number; name: string };
export function createUser(name: string): User {
  return { id: Date.now(), name };
}
```

یک فایل می‌تواند هر دو را بدهد. مصرف‌کننده انتخاب می‌کند چه بیاورد.

### ۲. import type

```ts
import { createUser, type User } from "./user.js";
// یا
import type { User } from "./user.js";
```

`import type` تضمین می‌کند که بعد از erase، importی برای runtime نماند. وقتی فقط به نوع نیاز دارید، این شکل را ترجیح دهید.

### ۳. پسوند .js در مسیر import

با `NodeNext`، در import نسبی اغلب `.js` می‌نویسید حتی اگر فایل منبع `.ts` است — چون خروجی/قرارداد Node روی `.js` است. گیج‌کننده است ولی استاندارد همین دوره است:

```ts
import { createUser } from "./user.js";
```

### ۴. @types/*

بسیاری از کتابخانه‌های JS خودشان نوع ندارند. پکیج‌های `@types/name` روی DefinitelyTyped تعریف می‌گذارند. این دوره `@types/node` دارد. نصب type جدا از نصب runtime است — گاهی هر دو لازم‌اند.

### ۵. Ambient کوتاه

فایل `.d.ts` می‌تواند به کامپایلر بگوید «این ماژول وجود دارد» بدون پیاده‌سازی:

```ts
declare module "clinic-shim" {
  export function greet(name: string): string;
}
```

برای آموزش و bridge کتابخانهٔ بدون نوع. سوءاستفاده: پنهان کردن نبودِ پکیج واقعی.

### ۶. Side-effect import

```ts
import "./polyfill.js";
```

چیزی bind نمی‌کند؛ فقط فایل را اجرا می‌کند. برای polyfill. دام: وابستگی پنهان و ترتیب بارگذاری.

### ۷. type-only export مجدد

```ts
export type { User } from "./user.js";
```

برای barrel فایل‌های نوع. مراقب چرخهٔ import باشید.

### ۸. چیزی که جلسه ۳۲–۳۳ عمیق‌تر می‌کند

نوشتن `.d.ts` کامل و module augmentation — اینجا فقط خواندن و shim کوتاه.

---

## اشتباه‌های رایج

1. **import نوع به‌عنوان مقدار و انتظار شیء runtime.** علت: type erase می‌شود.
2. **فراموش پسوند .js با NodeNext.** علت: resolution خطا می‌دهد.
3. **اعلام ambient برای رد کردن خطا بدون درک.** علت: دروغ به کامپایلر.
4. **side-effect import پنهان همه‌جا.** علت: وابستگی نامرئی.
5. **قاطی کردن @types با خود پکیج.** علت: یکی نوع است یکی کد.

---

## مثال گام‌به‌گام

```bash
npx tsx session-10-modules-types/examples/main.ts
npx tsx session-10-modules-types/examples/01-import-type-demo.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [user.ts](./examples/user.ts) | export نوع و تابع |
| [main.ts](./examples/main.ts) | import ترکیبی |
| [01-import-type-demo.ts](./examples/01-import-type-demo.ts) | import type خالص |
| [clinic-shim.d.ts](./examples/clinic-shim.d.ts) | ambient کوتاه |

## الگوی بهتر

`import type` وقتی فقط نوع می‌خواهید؛ `.js` در مسیر نسبی این دوره؛ ambient را مستند و موقتی نگه دارید.

## تمرین‌ها

### آسان
فایل `product.ts` با `export type Product` و تابع سازنده؛ از فایل دیگر import کنید.

### چالشی
فقط type را با `import type` بیاورید و یک تابع محلی که آن نوع را مصرف می‌کند بنویسید.

## راهنمای حل

همان الگوی `user.ts` / `main.ts`. برای چالش: `import type { Product } from "./product.js"`.

## خودآزمایی

1. `import type` چرا مفید است؟
2. آیا type در runtime وجود دارد؟
3. چرا در این دوره `.js` در import نسبی می‌آید؟
4. `@types/*` چیست؟
5. ambient یعنی چه؟
6. side-effect import چه می‌کند؟
7. `export type` با `export interface` چه شباهتی دارد؟
8. خطر declare module بی‌پایه چیست؟
9. می‌توان type و value را از یک مسیر import کرد؟
10. barrel type-only چه موقع مناسب است؟

## جمع‌بندی و پل جلسهٔ بعد
نوع بین فایل‌ها با قرارداد ماژول جابه‌جا شد. جلسهٔ بعد: سخت‌گیری **strict**، `unknown`، و `never` برای exhaustive بودن.
