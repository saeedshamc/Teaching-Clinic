# جلسه ۷: کلاس‌ها — implements، visibility، parameter properties، کی ننویسید (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۵–۶ · آشنایی با class در مسیر [`js/`](../../js/). OOP سنگین هدف نیست.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. کلاس با فیلد تایپ‌شده و constructor بنویسید
2. `implements` روی interface را به کار ببرید و متد جاافتاده را از خطا بفهمید
3. `public` / `private` / `protected` / `readonly` را در حد کاربردی استفاده کنید
4. parameter properties را بخوانید و بنویسید
5. بگویید کی کلاس ننویسید و به `type` + توابع خالص بسنده کنید

## چرا مهم است؟
در سرویس‌ها، ORMها و بعضی کدبیس‌های قدیمی کلاس هست. باید قرارداد و visibility را بفهمید — نه اینکه همه‌چیز را class کنید.

`implements` همان «این کلاس قول می‌دهد قرارداد interface را پر کند» است. بدون آن، متد جاافتاده دیرتر معلوم می‌شود.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | فیلد، constructor، متد تایپ‌شده |
| ۲۰–۴۵ | implements |
| ۴۵–۷۰ | private / protected / readonly |
| ۷۰–۹۰ | parameter properties |
| ۹۰–۱۰۵ | کی کلاس ننویسید |
| ۱۰۵–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### ۱. کلاس تایپ‌شده پایه

```ts
class Counter {
  private value = 0;

  inc(): void {
    this.value += 1;
  }

  getValue(): number {
    return this.value;
  }
}
```

فیلدها و متدها نوع دارند. این همان لایهٔ نوع روی class جاوااسکریپتی است.

### ۲. implements — قول قرارداد

```ts
interface Logger {
  log(message: string): void;
}

class ConsoleLogger implements Logger {
  log(message: string): void {
    console.log(message);
  }
}
```

اگر متد `log` نباشد یا امضا جور نباشد، خطا قبل از اجرا. `implements` نوع نمونه را تنگ‌تر از interface نمی‌کند مگر خودتان فیلد اضافه کنید؛ فقط چک انطباق است.

### ۳. Visibility

- `public` (پیش‌فرض): از بیرون قابل‌دسترس
- `private`: فقط داخل همان کلاس (سطح کامپایل؛ emit قدیمی با `#` فرق دارد)
- `protected`: کلاس و subclasses
- `readonly`: بعد از مقداردهی اولیه در constructor قابل‌تعویض نیست

```ts
class Account {
  readonly id: number;
  private balance: number;

  constructor(id: number, balance: number) {
    this.id = id;
    this.balance = balance;
  }
}
```

دام: `private` امنیت runtime نیست. کسی با cast یا دسترسی پویا می‌تواند دور بزند. قرارداد تیمی است نه قفل گاوصندوق.

### ۴. Parameter properties

```ts
class ConsoleLogger implements Logger {
  constructor(private readonly prefix: string) {}

  log(message: string): void {
    console.log(`${this.prefix}: ${message}`);
  }
}
```

`private readonly prefix` در پارامتر constructor هم فیلد می‌سازد هم مقدار می‌دهد. کوتاه و رایج؛ اگر منطق مقداردهی پیچیده است، فیلد جدا بنویسید.

### ۵. Protected و ارث‌بری سبک

```ts
class Animal {
  constructor(protected name: string) {}
}

class Dog extends Animal {
  bark(): string {
    return `${this.name}: هاپ`;
  }
}
```

برای آموزش این دوره ارث‌بری یک‌لایه کافی است. درخت عمیق نسازید.

### ۶. کی کلاس ننویسید

دادهٔ ساده بدون رفتار پایدار:

```ts
type User = { id: number; name: string };

function display(u: User): string {
  return u.name;
}
```

کلاس وقتی معنادار است که **حالت + رفتار** دور یک عمر شیء جمع شود (اتصال، بافر، ماشین حالت). برای DTO و رکورد، `type`/`interface` سبک‌تر است.

### ۷. class در برابر type ساختاری

TypeScript ساختاری است: اگر شکل جور باشد، گاهی شیء literal هم جای کلاس می‌نشیند — مگر فیلد `private` داشته باشید که برند اسمی بسازد. این جزئیات را فعلاً بدانید که `private` روی سازگاری اثر دارد.

### ۸. چیزی که اینجا نیست

abstract class عمیق، decorator، dependency injection — خارج از محدوده. هدف: خواندن و نوشتن کلاس کاربردی تایپ‌شده.

---

## اشتباه‌های رایج

1. **ارث‌بری چندلایه برای هر مفهوم.** علت: پیچیدگی بی‌سود؛ ترکیب بهتر است.
2. **`private` را امنیت دانستن.** علت: فقط کامپایل.
3. **فراموش implements و متد جاافتاده.** علت: بدون implements ممکن است دیرتر بفهمید.
4. **کلاس برای هر DTO.** علت: نویز؛ type کافی است.
5. **parameter property را با پارامتر معمولی قاطی کردن و فیلد را دوباره تعریف کردن.** علت: تکرار و سایه.

---

## مثال گام‌به‌گام

```bash
npx tsx session-07-classes/examples/01-class-basics.ts
npx tsx session-07-classes/examples/02-implements-visibility.ts
npx tsx session-07-classes/examples/03-parameter-properties.ts
npx tsx session-07-classes/examples/04-when-not-class.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-class-basics.ts](./examples/01-class-basics.ts) | کلاس پایه |
| [02-implements-visibility.ts](./examples/02-implements-visibility.ts) | implements و private |
| [03-parameter-properties.ts](./examples/03-parameter-properties.ts) | parameter properties |
| [04-when-not-class.ts](./examples/04-when-not-class.ts) | type + تابع به‌جای کلاس |

## الگوی بهتر

داده‌های ساده → type/interface + توابع. رفتار با حالت → کلاس کوتاه با `implements`. visibility برای قرارداد تیمی، نه توهم امنیت.

## تمرین‌ها

### آسان
کلاس `Counter` با `inc()` و خواندن مقدار بدون اجازهٔ نوشتن مستقیم از بیرون.

### چالشی
`interface Repository { get(id: number): string | undefined }` و یک کلاس `MapRepository` که `implements` کند.

## راهنمای حل

آسان: فیلد `private value`؛ متدهای عمومی.

چالش: داخل کلاس یک `Map<number, string>` خصوصی؛ `get` از map بخواند.

## خودآزمایی

1. `implements` چه تضمینی می‌دهد؟
2. `readonly` یعنی چه؟
3. parameter property چیست؟
4. آیا `private` در runtime مخفی است؟
5. کی کلاس ننویسیم؟
6. `protected` با `private` چه فرقی دارد؟
7. آیا فیلد بدون modifier عمومی است؟
8. چرا DTO را class نکنیم؟
9. ارث‌بری چندلایه چه مشکلی دارد؟
10. `implements` آیا فیلد اضافی را ممنوع می‌کند؟

## جمع‌بندی و پل جلسهٔ بعد
کلاس را وقتی حالت+رفتار دارید به کار می‌برید و با interface قفل می‌کنید. جلسهٔ بعد: **جنریک** تا همان توابع و جعبه‌ها بدون `any` برای هر نوع کار کنند.
