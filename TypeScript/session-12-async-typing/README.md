# جلسه ۱۲: تایپ Async — Promise، JSON، Result-style، خطای ذهنی (۱۲۰ دقیقه)

**محیط فرض:** TypeScript 5.x · `npx tsc --noEmit` · `npx tsx` · شبکه برای fetch نمونه اختیاری است

## پیش‌نیازها
جلسات ۴، ۱۱ و async در JS. اینجا فقط لایهٔ نوع.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. `Promise<T>` را بخوانید و برای تابع async نوع برگشت بنویسید
2. نتیجهٔ `json()` را `unknown` بگیرید و parse امن کنید
3. یک Result-style ساده بدون backend بسازید
4. خطای شبکه/parse را با `unknown` در catch مدیریت کنید
5. بگویید چرا `as User` روی JSON خام خطرناک است

## چرا مهم است؟
مرز شبکه جایی است که TypeScript به‌تنهایی دروغ می‌گوید اگر شما `as` بزنید. نوع داخل برنامه بعد از **validate** معنا دارد، نه بعد از download.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | Promise&lt;T&gt; و async function |
| ۲۰–۴۵ | json به‌عنوان unknown |
| ۴۵–۷۰ | parseUser و type guard |
| ۷۰–۹۵ | Result-style |
| ۹۵–۱۲۰ | خطا + تمرین |

---

## مفهوم عمیق

### ۱. Promise&lt;T&gt;

```ts
async function loadId(): Promise<number> {
  return 1;
}
```

`async` همیشه Promise برمی‌گرداند. `T` نوع مقدار resolve است نه خود Promise در await.

### ۲. JSON یعنی unknown

```ts
const data: unknown = await res.json();
```

شکل را حدس نزنید. تا validate نکرده‌اید، `User` نیست.

### ۳. Parse با guard

```ts
function isRecord(value: unknown): value is Record<string, unknown> {
  return typeof value === "object" && value !== null;
}
```

فیلد به فیلد چک کنید؛ بعد شیء دامنه بسازید.

### ۴. Result-style بدون پرتاب

```ts
type Result<T> =
  | { ok: true; value: T }
  | { ok: false; error: string };
```

برای جریان‌هایی که خطا قابل‌انتظار است، گاهی بهتر از throw است. UI می‌تواند روی `ok` branch بماند.

### ۵. fetch و HTTP

`res.ok` را چک کنید. وضعیت غیر۲xx ممکن است body خطا داشته باشد — نوع جدا.

### ۶. خطر as

```ts
const user = data as User; // دروغ راحت
```

کامپایلر ساکت می‌شود؛ runtime می‌ترکد. Validate کنید.

### ۷. خطای typed ذهنی

نوع خطا در Promise reject معمولاً در سیستم نوع TS ضعیف مدل می‌شود. عملاً: catch unknown + پیام؛ یا Result.

### ۸. تست بدون شبکه

parse را روی شیء محلی단위 آزمایش کنید؛ fetch را جدا و اختیاری.

---

## اشتباه‌های رایج

1. **`as User` روی JSON.** علت: بدون validate.
2. **فراموش Promise در نوع برگشت ذهنی.** علت: async همیشه Promise است.
3. **فرض res.json() همیشه شکل درست دارد.** علت: سرور و پروکسی خلاف می‌گویند.
4. **catch را نادیده گرفتن در دمو.** علت: خطای شبکه رایج است.
5. **Result و throw را قاطی کردن بدون قرارداد تیم.** علت: دو مسیر خطا گیج می‌کند.

---

## مثال گام‌به‌گام

```bash
npx tsx session-12-async-typing/examples/01-parse-local.ts
npx tsx session-12-async-typing/examples/02-result-style.ts
npx tsx session-12-async-typing/examples/03-fetch-user.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-parse-local.ts](./examples/01-parse-local.ts) | parse بدون شبکه |
| [02-result-style.ts](./examples/02-result-style.ts) | Result |
| [03-fetch-user.ts](./examples/03-fetch-user.ts) | fetch اختیاری |

## الگوی بهتر

`unknown` → parse → `T`. برای خطای قابل‌انتظار Result؛ برای باگ واقعی throw. هرگز as روی مرز شبکه به‌عنوان عادت.

## تمرین‌ها

### آسان
`parseProduct(data: unknown)` برای `{ id: number; title: string }`.

### چالشی
همان parse را در `Result<Product>` بپیچید بدون throw.

## راهنمای حل

آسان: isRecord + typeof فیلدها.

چالش: به‌جای throw، `{ ok: false, error }` برگردانید.

## خودآزمایی

1. async چه نوعی برمی‌گرداند؟
2. چرا json را unknown بگیریم؟
3. خطر as چیست؟
4. Result-style چه مزیتی دارد؟
5. res.ok چرا مهم است؟
6. type predicate در parse چه نقش دارد؟
7. آیا TS نوع reject را محکم تضمین می‌کند؟
8. تست parse را چطور بدون شبکه؟
9. فرق خطای HTTP با خطای شکل JSON؟
10. مرز اعتماد داده کجاست؟

## جمع‌بندی و پل جلسهٔ بعد
Promise و JSON را با validate تایپ کردید. جلسهٔ بعد: **tsconfig** — همان گزینه‌هایی که strict و module را روشن نگه می‌دارند.
