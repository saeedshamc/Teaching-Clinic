# جلسه ۹: Utility Types — Partial، Pick، Omit، Record، Required، ReturnType (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۸ (جنریک و `keyof`). بدون آن، معنی داخلی این ابزارها مبهم می‌ماند.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. با قبل/بعد بگویید `Partial`، `Pick`، `Omit`، `Record` چه می‌کنند
2. `Partial` را با `Pick` ترکیب کنید برای پچ محدود
3. `Required` و تفاوتش با Partial را نشان دهید
4. `ReturnType` و `Parameters` را روی تابع موجود بخوانید
5. بگویید کی utility آماده کافی است و کی type دستی بنویسید

## چرا مهم است؟
بدون utility، برای هر فرم ویرایش یک type کپی می‌نویسید و از مدل اصلی جدا می‌شود. Utility یعنی **مشتق از منبع حقیقت** — وقتی `User` عوض شد، پچ هم هم‌راستا می‌ماند.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | Partial قبل/بعد |
| ۲۰–۴۰ | Pick و Omit |
| ۴۰–۶۰ | Record |
| ۶۰–۸۰ | Required و ترکیب Partial+Pick |
| ۸۰–۱۰۰ | ReturnType / Parameters |
| ۱۰۰–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### ۱. Partial — همه اختیاری

```ts
type User = { id: number; name: string; email: string };
type UserPatch = Partial<User>;
// { id?: number; name?: string; email?: string }
```

برای `PATCH` و merge عالی است. دام: `Partial` همه را اختیاری می‌کند؛ اگر فقط چند فیلد باید قابل‌ویرایش باشد، با `Pick` ترکیب کنید.

### ۲. Pick — انتخاب کلیدها

```ts
type UserPreview = Pick<User, "id" | "name">;
```

لیست و کارت UI اغلب فقط زیرمجموعه‌اند. منبع همچنان `User` است.

### ۳. Omit — حذف کلیدها

```ts
type UserPublic = Omit<User, "email">;
```

وقتی می‌خواهید فیلد حساس را از خروجی عمومی بردارید. از نظر خوانایی گاهی `Pick` صریح‌تر است اگر کلیدهای مانده کم باشند.

### ۴. Record — نقشهٔ کلید→مقدار

```ts
type FeatureFlags = Record<string, boolean>;
type RoleMap = Record<"admin" | "user", string>;
```

به‌جای index signature دستی. دام: `Record<string, T>` خیلی باز است؛ اگر مجموعه کلید بسته است، union literal بدهید.

### ۵. ترکیب Partial + Pick

```ts
type NameEmailPatch = Partial<Pick<User, "name" | "email">>;
```

فقط name و email، هر دو اختیاری — نه id. این الگوی روزمرهٔ فرم ویرایش است.

### ۶. Required

```ts
type Filled = Required<Partial<User>>;
// دوباره همه اجباری
```

وقتی بعد از پر شدن فرم می‌خواهید تضمین کنید چیزی `undefined` نمانده. برعکس Partial.

### ۷. ReturnType و Parameters

```ts
function createUser(name: string) {
  return { id: 1, name };
}
type Created = ReturnType<typeof createUser>;
type Args = Parameters<typeof createUser>; // [string]
```

وقتی تابع منبع حقیقت است و نمی‌خواهید type جدا تکراری بنویسید. دام: اگر تابع overload داشته باشد، رفتار subtler است (جلسه ۲۲).

### ۸. کی دستی بنویسید؟

اگر مشتق پیچیده یا نام دامنهٔ معنادار دارید (`CheckoutDraft`)، یک `type` نام‌دار دور utility بپیچید — utility خام را همه‌جا پخش نکنید.

---

## اشتباه‌های رایج

1. **Partial روی کل مدل برای فرمی که id نباید عوض شود.** علت: id هم optional می‌شود.
2. **Omit برای «امنیت» دادهٔ حساس بدون کنترل runtime.** علت: فقط نوع است؛ هنوز می‌توانید email را لاگ کنید.
3. **Record<string, T> به‌جای کلیدهای بسته.** علت: غلط املایی کلید خطا نمی‌دهد.
4. **کپی دستی UserUpdate جدا از User.** علت: انحراف تدریجی از منبع.
5. **ReturnType روی تابعی که any برمی‌گرداند.** علت: زبالهٔ any را رسمی می‌کنید.

---

## مثال گام‌به‌گام

```bash
npx tsx session-09-utility-types/examples/01-partial-pick-omit.ts
npx tsx session-09-utility-types/examples/02-record-required.ts
npx tsx session-09-utility-types/examples/03-partial-pick-combo.ts
npx tsx session-09-utility-types/examples/04-returntype-parameters.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-partial-pick-omit.ts](./examples/01-partial-pick-omit.ts) | Partial / Pick / Omit |
| [02-record-required.ts](./examples/02-record-required.ts) | Record و Required |
| [03-partial-pick-combo.ts](./examples/03-partial-pick-combo.ts) | ترکیب پچ |
| [04-returntype-parameters.ts](./examples/04-returntype-parameters.ts) | ReturnType / Parameters |

## الگوی بهتر

یک `User` منبع؛ مشتق‌ها با utility؛ پچ فرم با `Partial<Pick<...>>`؛ نام دامنه دور مشتق‌های پرتکرار.

## تمرین‌ها

### آسان
از `Product = { id: number; title: string; price: number }` نوع `ProductCard = Pick<Product, "id" | "title">` بسازید.

### چالشی
`type ProductPatch = Partial<Pick<Product, "title" | "price">>` و تابع `apply(p, patch)`.

## راهنمای حل

آسان: همان Pick.

چالش: `return { ...p, ...patch };`

## خودآزمایی

1. Partial چه می‌کند؟
2. فرق Pick و Omit؟
3. Record برای چه نقشه‌ای است؟
4. چرا Partial Alone برای فرم خطرناک است؟
5. Required چه می‌کند؟
6. ReturnType از کجا نوع می‌گیرد؟
7. Parameters چه برمی‌گرداند؟
8. کی type دستی نام‌دار بهتر است؟
9. Omit امنیت runtime می‌دهد؟
10. ترکیب Partial+Pick چه الگوی روزمره‌ای است؟

## جمع‌بندی و پل جلسهٔ بعد
مشتق نوع از منبع حقیقت با utility. جلسهٔ بعد: همین typeها را بین **فایل‌ها** با ماژول و `import type` جابه‌جا می‌کنیم.
