# جلسهٔ ۲۳ — typedef

## اهداف یادگیری

در پایان این جلسه می‌توانید:

- با `typedef` نام مستعار برای انواع بسازید
- کد را با typedef خواناتر و قابل حمل‌تر کنید
- typedef را برای struct، enum و اشاره‌گر به‌کار ببرید

---

## typedef چیست؟

`typedef` برای یک نوع، **نام جدید** (مستعار) تعریف می‌کند:

```c
typedef unsigned long ulong;
ulong big = 1000000;
```

یا برای struct — رایج‌ترین کاربرد:

```c
typedef struct {
    char name[50];
    int age;
} Student;

Student s1;   // به‌جای struct Student s1;
```

## کاربردهای مهم

1. **کوتاه‌کردن نوشتن:** `Student` به‌جای `struct Student`.
2. **قابل حمل بودن:** `typedef unsigned long size;` — اگر پلتفرم عوض شد فقط یک خط تغییر می‌کند.
3. **اشاره‌گر به تابع (جلسهٔ ۳۲):** `typedef int (*Op)(int, int);`
4. **انواع استاندارد:** `size_t`، `uint32_t` از `<stdint.h>` نمونه‌های typedef هستند.

## نکات

- typedef فقط «نام جدید» می‌سازد؛ نوع جدیدی نمی‌سازد.
- برای struct می‌توانید هم نام تگ بگذارید هم مستعار:

```c
typedef struct Student {
    ...
} Student;
```

- یک typedef را در یک خط بنویسید و `;` فراموش نشود.

## اشتباهات رایج

1. **فراموش کردن `;` بعد از typedef struct** — خطای کامپایل.
2. **اشتباه گرفتن typedef با تعریف متغیر** — typedef متغیر نمی‌سازد، فقط نام می‌سازد.
3. **نام‌گذاری تکراری که با متغیر تداخل کند.**

## بهترین شیوه‌ها

- برای struct ها و enum ها از typedef استفاده کنید (معمول).
- نام مستعار را با حرف بزرگ شروع کنید: `Student`، `Point`.
- برای اشاره‌گر به تابع حتماً typedef بگیرید تا خوانایی حفظ شود.

## خلاصهٔ جلسه

- typedef نام مستعار برای انواع می‌سازد.
- پرکاربردترین مورد: `typedef struct {...} Name;`
- کد را کوتاه، خوانا و قابل حمل می‌کند.

## تمرین‌ها

### تمرین ۱ (ساده)
با typedef نام `Point` برای struct با x و y بسازید و دو نقطه را تعریف و چاپ کنید.

### تمرین ۲ (متوسط)
با typedef نام `Meter` برای double و `Second` برای double بسازید و سرعت (m/s) را محاسبه کنید.

راه‌حل‌ها در `exercises/` هستند.

## فایل‌های این جلسه

- `examples/01_typedef_basics.c` — نام‌های مستعار ساده
- `examples/02_typedef_struct.c` — typedef برای struct
- `exercises/exercise_01.c` — Point
- `exercises/exercise_02.c` — Meter و Second
