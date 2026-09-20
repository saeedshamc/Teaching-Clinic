# جلسه ۶: Interface در برابر Type — مدل شیء، ترکیب، index، readonly (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۳ و ۵: object/alias و تایپ تابع. این جلسه JS را مرور نمی‌کند.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. با `interface` مدل دامنه بنویسید و با `extends` گسترش دهید
2. با `type` و `&` intersection بسازید و فرق آن با `extends` را بگویید
3. بگویید کی `type` (union/tuple پیچیده) و کی `interface` مناسب‌تر است
4. index signature و `readonly` را روی property به کار ببرید
5. declaration merging را در حد آگاهی بشناسید و از سوءاستفاده پرهیز کنید

## چرا مهم است؟
تقریباً هر API واقعی «شکل شیء» است. اگر قرارداد شکل مبهم باشد، بقیهٔ دوره (کلاس، جنریک، React props) روی شن ساخته می‌شود.

در کدبیس واقعی هر دو `interface` و `type` دیده می‌شوند. انتخاب ناآگاهانه باعث API ناسازگار و بحث بی‌پایان تیمی می‌شود — قانون آموزشی این دوره جلوتر قفل می‌شود.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | interface برای شیء دامنه |
| ۲۰–۴۰ | extends در برابر intersection |
| ۴۰–۶۰ | کی type، کی interface |
| ۶۰–۸۵ | index signature و readonly |
| ۸۵–۱۰۰ | declaration merging — آگاهی |
| ۱۰۰–۱۲۰ | مثال، تمرین، خودآزمایی |

---

## مفهوم عمیق

### ۱. Interface برای شکل شیء

```ts
interface User {
  id: number;
  name: string;
}
```

`interface` نامی روی یک قرارداد ساختاری است. سازگاری ساختاری است: اگر شیء فیلدها را داشته باشد، نوعش جور است — حتی اگر صریحاً `User` annotate نشده باشد.

### ۲. Extends — گسترش سلسله‌مراتبی

```ts
interface Admin extends User {
  role: "admin";
}
```

`Admin` همهٔ فیلدهای `User` را دارد به‌علاوهٔ `role`. برای درخت مدل‌های نزدیک به‌هم خوانا است.

دام: سلسله‌مراتب عمیق (۵ سطح extends) معمولاً بوی طراحی بد می‌دهد؛ ترکیب تخت‌تر اغلب بهتر است.

### ۳. Intersection (`&`) در برابر extends

```ts
type Timestamped = User & { createdAt: Date };
```

`&` دو نوع را با هم ادغام می‌کند. روی `type` و ترکیب‌های پیچیده طبیعی‌تر است. `extends` فقط روی interface (و کلاس) است.

فرق عملی آموزشی:
- شکل شیء قابل‌گسترش تیمی → اغلب `interface` + `extends`
- چسباندن موقت دو قرارداد → `type` + `&`
- اگر intersection به تعارض property برسد، نوع ممکن است `never` روی آن فیلد شود — دام جدی.

### ۴. Type برای چیزهایی که interface نیست

```ts
type Id = string | number;
type Pair = [string, number];
type Result =
  | { ok: true; data: string }
  | { ok: false; error: string };
```

Union، tuple، mapped، alias شرطی — قلمرو `type` است. `interface` نمی‌تواند مستقیماً union باشد.

### ۵. Index signature

```ts
interface StringMap {
  [key: string]: number;
}

const scores: StringMap = { ali: 10, sara: 18 };
```

وقتی کلیدها از قبل معلوم نیستند. دام: با فیلدهای مشخص قاطی کردنش سخت است؛ اگر کلیدهای ثابت دارید، آن‌ها را بنویسید نه index باز.

```ts
interface Point {
  x: number;
  y: number;
  // [key: string]: number; // همهٔ فیلدها باید با index جور باشند
}
```

### ۶. Readonly property

```ts
interface Config {
  readonly apiUrl: string;
  timeout: number;
}

const cfg: Config = { apiUrl: "https://api", timeout: 3000 };
// cfg.apiUrl = "other"; // ❌
cfg.timeout = 5000; // ✅
```

`readonly` در سطح کامپایل است؛ در runtime جلوی جهش را اگر با حیله دور بزنید نمی‌گیرد. برای قرارداد «عمداً عوض نکن» عالی است.

### ۷. Declaration merging — آگاهی، نه عادت

دو `interface` هم‌نام در یک scope ادغام می‌شوند:

```ts
interface Box {
  width: number;
}
interface Box {
  height: number;
}
// Box = { width; height }
```

`type` alias این کار را نمی‌کند. Merging برای augment کتابخانه مفید است (جلسه ۳۳)؛ در کد دامنهٔ خودتان معمولاً یک تعریف واحد بنویسید تا خوانایی نمیرد.

### ۸. قانون آموزشی این دوره

- شکل شیء قابل‌گسترش → اغلب `interface`
- union، tuple، mapped، alias پیچیده → `type`
- یکدست بمانید؛ بحث «فقط interface» یا «فقط type» را به تیم واقعی موکول کنید

---

## اشتباه‌های رایج

1. **فکر کردن که فقط یکی از interface/type «درست» است.** علت: هر دو ابزارند با نقاط قوت متفاوت.
2. **Union را با interface نوشتن.** علت: syntax اجازه نمی‌دهد؛ از type استفاده کنید.
3. **Index signature باز روی مدل دامنهٔ ثابت.** علت: کلید غلط از نظر نوع قبول می‌شود.
4. **readonly را امنیت runtime دانستن.** علت: فقط چک کامپایل است.
5. **Merging تصادفی با دوبار تعریف interface هم‌نام.** علت: ادغام خاموش؛ باگ خوانایی.

---

## مثال گام‌به‌گام

```bash
npx tsx session-06-interface-vs-type/examples/01-interface-extends.ts
npx tsx session-06-interface-vs-type/examples/02-type-intersection-union.ts
npx tsx session-06-interface-vs-type/examples/03-index-readonly.ts
npx tsx session-06-interface-vs-type/examples/04-merging-awareness.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-interface-extends.ts](./examples/01-interface-extends.ts) | interface و extends |
| [02-type-intersection-union.ts](./examples/02-type-intersection-union.ts) | type، &، union |
| [03-index-readonly.ts](./examples/03-index-readonly.ts) | index و readonly |
| [04-merging-awareness.ts](./examples/04-merging-awareness.ts) | merging آگاهانه |

## الگوی بهتر

مدل‌های دامنه را با `interface` نام‌دار؛ نتایج یا-یا را با `type` union؛ از merging در کد خودی پرهیز؛ `readonly` برای شناسه و URLهای پیکربندی.

## تمرین‌ها

### آسان
`interface Point { x: number; y: number }` و تابع `distanceToOrigin(p: Point): number`.

### چالشی
`type Result = { ok: true; data: string } | { ok: false; error: string }` و تابع `message(r: Result): string`.

## راهنمای حل

آسان: `return Math.hypot(p.x, p.y);`

چالش: `if (r.ok) return r.data; return r.error;`

## خودآزمایی

1. سازگاری ساختاری یعنی چه؟
2. `extends` روی interface چه می‌کند؟
3. `&` چه فرقی با extends دارد؟
4. union را با کدام می‌نویسید؟
5. index signature کی مناسب است؟
6. readonly در runtime تضمین می‌کند؟
7. merging روی type هم هست؟
8. قانون این دوره برای انتخاب چیست؟
9. تعارض property در intersection چه می‌شود؟
10. چرا سلسله‌مراتب extends عمیق خطرناک است؟

## جمع‌بندی و پل جلسهٔ بعد
شکل شیء را با interface/type سند کردید. جلسهٔ بعد همان قرارداد را روی **کلاس** و `implements` می‌بریم — و می‌گوییم کی اصلاً کلاس ننویسید.
