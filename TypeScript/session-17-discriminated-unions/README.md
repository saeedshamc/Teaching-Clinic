# جلسه 17: Discriminated Unions — tag، exhaustive، ماشین حالت (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · از ریشهٔ `TypeScript/`: `npx tsc --noEmit` و `npx tsx`

## پیش‌نیازها
جلسات قبلی همین دوره تا مسیر منطقی موضوع. JavaScript را دوباره درس نمی‌دهیم؛ فقط لایهٔ نوع.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. union تمایزدار با فیلد tag (مثل status/kind/ok) بنویسید
2. با switch/if روی discriminant تنگ کنید
3. exhaustive بودن را با never محکم کنید
4. LoadState چندوضعیته را مدل کنید
5. بگویید کی union ساده کافی نیست و discriminant لازم است

## چرا مهم است؟
وضعیت‌های یا-یا در UI و API بدون discriminant به جنگ `in` و فیلدهای اختیاری کاذب می‌کشند. tag مشترک، narrowing را قطعی می‌کند.

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

### 1. Discriminant چیست؟

یک فیلد literal مشترک بین اعضای union که مقدارش شاخه را مشخص می‌کند:

```ts
type Ok = { ok: true; value: string };
type Fail = { ok: false; error: string };
type Result = Ok | Fail;
```

### 2. چرا بدون tag رنج می‌برید؟

دو شیء با فیلدهای اختیاری (`data?:` و `error?:`) هر ترکیبی را از نظر نوع مجاز می‌کنند — حتی `{ data, error }` همزمان. discriminant ترکیب نامعتبر را سخت‌تر می‌کند.

### 3. switch روی tag

```ts
function message(r: Result): string {
  switch (r.ok) {
    case true: return r.value;
    case false: return r.error;
  }
}
```

### 4. LoadState

```ts
type LoadState =
  | { status: "idle" }
  | { status: "loading" }
  | { status: "success"; data: string }
  | { status: "error"; message: string };
```
هر status فیلدهای مخصوص خودش را دارد.

### 5. Exhaustive با never

از جلسه ۱۱: `default: return assertNever(state)` تا افزودن وضعیت جدید بدون case خطا بدهد.

### 6. نام tag

`status`، `kind`، `type`، `ok` رایج‌اند. در یک ماژول یک قرارداد نام انتخاب کنید.

### 7. دام پخش tag

بعد از destructure ممکن است narrowing ضعیف شود. روی خود شیء switch کنید یا discriminant را جدا const کنید با دقت.

### 8. ارتباط با جلسه بعد

type guard سفارشی وقتی discriminant ندارید یا از API خارجی می‌آید لازم می‌شود.

---

## اشتباه‌های رایج

1. **فیلدهای optional به‌جای union تمایزدار — ترکیب‌های نامعتبر مجاز می‌شوند.**
2. **فراموش exhaustive وقتی status جدید اضافه می‌شود.**
3. **استفاده از string باز برای status به‌جای literal.**
4. **چک کردن وجود data بدون نگاه به status.**
5. **destructure زودهنگام و از دست رفتن narrowing.**

---

## مثال گام‌به‌گام

از ریشهٔ `TypeScript/`:

```bash
npx tsx session-17-discriminated-unions/examples/01-result-ok.ts
npx tsx session-17-discriminated-unions/examples/02-load-state.ts
npx tsx session-17-discriminated-unions/examples/03-exhaustive.ts
npx tsx session-17-discriminated-unions/examples/04-kind-tag.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-result-ok.ts](./examples/01-result-ok.ts) | Result با ok |
| [02-load-state.ts](./examples/02-load-state.ts) | LoadState |
| [03-exhaustive.ts](./examples/03-exhaustive.ts) | assertNever |
| [04-kind-tag.ts](./examples/04-kind-tag.ts) | kind tag |

## الگوی بهتر

قرارداد نوع را صریح نگه دارید؛ از shortcutهای خطرناک (`any`، cast کور) برای بستن جلسه استفاده نکنید. مثال‌ها را اول بخوانید بعد تغییر دهید.

## تمرین‌ها

### آسان
type Animal = { kind: "dog"; bark: string } | { kind: "cat"; meow: string } و تابع speak.

### چالشی
LoadState را با assertNever exhaustive کنید و وضعیت جدید pause را عمداً اضافه/حذف کنید تا خطا را ببینید.

## راهنمای حل

آسان: switch روی kind؛ در dog bark و در cat meow.

چالش: default: assertNever(state)؛ با افزودن pause بدون case، TS خطا می‌دهد.

## خودآزمایی

1. discriminant چیست؟
2. چرا optional fields ضعیف‌اند؟
3. exhaustive یعنی چه؟
4. assertNever چه نقشی دارد؟
5. نام‌های رایج tag؟
6. آیا status: string کافی است؟
7. LoadState چند وضعیت نمونه دارد؟
8. خطر destructure چیست؟
9. Result با ok چه الگویی است؟
10. پل به type guard چیست؟

## جمع‌بندی و پل جلسهٔ بعد
Discriminant را مسلط شدید. جلسهٔ ۱۸: type guard و assertion function وقتی tag آماده ندارید.
