# جلسه 20: Enum — numeric، string، const؛ کی دوری کنید (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · از ریشهٔ `TypeScript/`: `npx tsc --noEmit` و `npx tsx`

## پیش‌نیازها
جلسات ۴ و ۱۹.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. numeric و string enum بنویسید و بخوانید
2. رفتار reverse mapping عددی را بشناسید
3. const enum را در حد آگاهی بشناسید
4. بگویید کی union بهتر از enum است
5. یک وضعیت را هم با enum هم با union مدل کنید و مقایسه کنید

## چرا مهم است؟
Enum در کدبیس‌های قدیمی زیاد است؛ باید بخوانید. ولی در TypeScript مدرن اغلب union literal ساده‌تر و شفاف‌تر است.

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

### 1. Numeric enum

```ts
enum Direction { Up, Down }
```
در runtime شیء می‌ماند؛ reverse mapping دارد.

### 2. String enum

```ts
enum Role { Admin = "admin", User = "user" }
```
معمولاً امن‌تر برای سریالایز.

### 3. Union جایگزین

```ts
type Role = "admin" | "user";
```
ساده، tree-shake پذیر، بدون شیء runtime اجباری.

### 4. const enum

در جاهای زیادی inline می‌شود؛ با isolatedModules/bundlerها دردسر دارد. در این دوره توصیهٔ پیش‌فرض نیست.

### 5. کی enum؟

وقتی با API/کدبیس موجود مجبورید؛ یا تیم صریحاً enum را استاندارد کرده.

### 6. کی union؟

وضعیت UI، کلید نقشه، اکثر کد جدید این دوره.

### 7. دام numeric

مقدار عددی اضافی از بیرون ممکن است از نظر نوع بگذرد اگر با احتیاط نبندید.

### 8. مقایسه عملی

برای Role در Notes/Users تقریباً همیشه union کافی است.

---

## اشتباه‌های رایج

1. **numeric enum برای API JSON بدون نیاز.**
2. **مخلوط کردن enum و union بی‌قرارداد.**
3. **اعتماد به const enum در همه bundlerها.**
4. **فرض اینکه enum فقط type است — runtime هم دارد.**
5. **استفاده از enum وقتی یک/دو مقدار بیشتر نیست و union خواناتر است.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-20-enums/examples/01-numeric-enum.ts
npx tsx session-20-enums/examples/02-string-enum.ts
npx tsx session-20-enums/examples/03-union-alt.ts
npx tsx session-20-enums/examples/04-when-enum.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-numeric-enum.ts](./examples/01-numeric-enum.ts) | numeric |
| [02-string-enum.ts](./examples/02-string-enum.ts) | string enum |
| [03-union-alt.ts](./examples/03-union-alt.ts) | union جایگزین |
| [04-when-enum.ts](./examples/04-when-enum.ts) | یادداشت انتخاب |

## الگوی بهتر

پیش‌فرض آموزشی: union literal. Enum فقط با دلیل.

## تمرین‌ها

### آسان
string enum Status با Idle/Go.

### چالشی
همان Status را با union بنویسید و تابع label مشترک ذهنی کنید.

## راهنمای حل

آسان: enum Status { Idle = "idle", Go = "go" }

چالش: type Status = "idle" | "go"

## خودآزمایی

1. فرق numeric و string enum؟
2. reverse mapping چیست؟
3. چرا union اغلب بهتر است؟
4. const enum چه دام دارد؟
5. آیا enum فقط type است؟
6. کی مجبور به enum می‌شویم؟
7. Role را چطور با union؟
8. JSON و numeric enum؟
9. isolatedModules و const enum؟
10. پل بعدی؟

## جمع‌بندی و پل جلسهٔ بعد
Enum را آگاهانه دیدید. جلسهٔ ۲۱: tupleهای پیشرفته.
