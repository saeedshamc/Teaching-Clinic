# دورهٔ جامع آموزش زبان C — ۵۰ جلسه

> از صفر مطلق تا سطح حرفه‌ای: ساختمان داده، الگوریتم، مدیریت حافظه و برنامه‌نویسی سیستمی پایه.

این ریپازیتوری حاوی یک دورهٔ کامل و گام‌به‌گام زبان C است. تمام توضیحات به زبان فارسی نوشته شده و هر جلسه شامل یک `README.md` آموزشی، کدهای نمونهٔ قابل کامپایل (`examples/`) و تمرین‌های عملی (`exercises/`) است.

---

## فهرست مطالب

- [معرفی دوره](#معرفی-دوره)
- [پیش‌نیازها](#پیش‌نیازها)
- [نحوهٔ کامپایل و اجرا](#نحوهٔ-کامپایل-و-اجرا)
- [فهرست کامل ۵۰ جلسه](#فهرست-کامل-۵۰-جلسه)
- [نقشهٔ راه یادگیری](#نقشهٔ-راه-یادگیری)
- [ساختار کامیت‌های Git](#ساختار-کامیتهای-git)

---

## معرفی دوره

زبان C یکی از مهم‌ترین و تأثیرگذارترین زبان‌های برنامه‌نویسی دنیاست؛ هستهٔ سیستم‌عامل‌ها، مفسرها، پایگاه‌داده‌ها و تقریباً همهٔ زبان‌های مدرن به نحوی به C وابسته‌اند. یادگیری C به شما درک عمیقی از حافظه، اشاره‌گرها (Pointers) و نحوهٔ کار سخت‌افزار می‌دهد که در هیچ زبان سطح‌بالاتری به این شفافیت دیده نمی‌شود.

این دوره در **۵۰ جلسه** طراحی شده است:

- **جلسات ۱ تا ۱۰:** مبانی زبان — متغیر، عملگر، ورودی/خروجی، شرط، حلقه، تابع و بازگشت
- **جلسات ۱۱ تا ۲۳:** آرایه، رشته، اشاره‌گر، حافظهٔ پویا، struct، union، enum و typedef
- **جلسات ۲۴ تا ۳۳:** کار با فایل، پیش‌پردازنده، پروژهٔ چندفایلی، عملگرهای بیتی، مدیریت خطا و اشاره‌گر به تابع
- **جلسات ۳۴ تا ۴۴:** ساختمان داده و الگوریتم — لیست پیوندی، پشته، صف، درخت، مرتب‌سازی، جستجو و جدول هش
- **جلسات ۴۵ تا ۵۰:** ابزارهای حرفه‌ای — Makefile، gdb، valgrind، نخ‌ها (pthreads)، سوکت‌ها و پروژهٔ نهایی

هر جلسه طوری طراحی شده که روی مفاهیم جلسات قبل سوار شود؛ بنابراین ترتیب جلسات را رعایت کنید.

---

## پیش‌نیازها

برای انجام این دوره به ابزارهای زیر نیاز دارید:

| ابزار | کاربرد | نصب |
|---|---|---|
| **gcc** | کامپایلر زبان C | لینوکس: `sudo apt install gcc` — ویندوز: MinGW-w64 یا WSL |
| **make** | اتوماسیون ساخت (جلسات ۴۵+) | لینوکس: `sudo apt install make` |
| **gdb** | دیباگر (جلسهٔ ۴۶) | لینوکس: `sudo apt install gdb` |
| **valgrind** | بررسی نشت حافظه (جلسهٔ ۴۷) | لینوکس: `sudo apt install valgrind` |

> **نکتهٔ ویندوز:** ساده‌ترین راه، نصب WSL (Ubuntu) و کامپایل داخل لینوکس است. اگر از MinGW استفاده می‌کنید، دستورات مشابه هستند اما `pthread` ممکن است به تنظیمات بیشتری نیاز داشته باشد.

---

## نحوهٔ کامپایل و اجرا

همهٔ کدهای نمونه با این دستور باید بدون خطا و بدون هشدار کامپایل شوند:

```bash
gcc -Wall -Wextra -o program examples/01_example.c
./program
```

برای هر جلسه داخل پوشهٔ همان جلسه:

```bash
cd session-01-intro
gcc -Wall -Wextra -o hello examples/01_hello_world.c
./hello
```

برخی جلسات (مثل چندفایلی یا Makefile) دستورات خاص خودشان را دارند که در README همان جلسه توضیح داده شده است.

---

## فهرست کامل ۵۰ جلسه

### مبانی زبان (جلسات ۱–۱۰)

| # | جلسه | موضوع |
|---|---|---|
| ۱ | [session-01-intro](session-01-intro/README.md) | مقدمه‌ای بر C، نصب gcc، کامپایل و اجرا |
| ۲ | [session-02-variables-types](session-02-variables-types/README.md) | متغیرها، انواع دادهٔ پایه و ثابت‌ها (const) |
| ۳ | [session-03-operators](session-03-operators/README.md) | عملگرها و اولویت آن‌ها |
| ۴ | [session-04-printf-scanf](session-04-printf-scanf/README.md) | ورودی/خروجی با printf و scanf |
| ۵ | [session-05-conditionals](session-05-conditionals/README.md) | شرط‌ها (if/else/switch) |
| ۶ | [session-06-loops](session-06-loops/README.md) | حلقه‌ها (for/while/do-while) |
| ۷ | [session-07-break-continue-goto](session-07-break-continue-goto/README.md) | break، continue و goto |
| ۸ | [session-08-functions-basics](session-08-functions-basics/README.md) | مبانی توابع (Functions) |
| ۹ | [session-09-function-parameters](session-09-function-parameters/README.md) | پارامترهای تابع و pass by value |
| ۱۰ | [session-10-recursion](session-10-recursion/README.md) | بازگشت (Recursion) پایه |

### آرایه، رشته و اشاره‌گر (جلسات ۱۱–۲۳)

| # | جلسه | موضوع |
|---|---|---|
| ۱۱ | [session-11-arrays-1d](session-11-arrays-1d/README.md) | آرایه‌های یک‌بعدی |
| ۱۲ | [session-12-arrays-multidimensional](session-12-arrays-multidimensional/README.md) | آرایه‌های چندبعدی |
| ۱۳ | [session-13-strings-basics](session-13-strings-basics/README.md) | رشته‌ها (char array) — مبانی |
| ۱۴ | [session-14-string-functions](session-14-string-functions/README.md) | توابع رشته‌ای (string.h) |
| ۱۵ | [session-15-pointers-basics](session-15-pointers-basics/README.md) | مبانی اشاره‌گرها (Pointers) |
| ۱۶ | [session-16-pointers-arrays](session-16-pointers-arrays/README.md) | اشاره‌گرها و آرایه‌ها |
| ۱۷ | [session-17-pointers-functions](session-17-pointers-functions/README.md) | اشاره‌گرها و توابع (pass by reference) |
| ۱۸ | [session-18-dynamic-memory](session-18-dynamic-memory/README.md) | حافظهٔ پویا: malloc، calloc، realloc، free |
| ۱۹ | [session-19-structs](session-19-structs/README.md) | ساختارها (struct) |
| ۲۰ | [session-20-structs-nested-arrays](session-20-structs-nested-arrays/README.md) | struct تودرتو و آرایه‌ای از struct |
| ۲۱ | [session-21-unions](session-21-unions/README.md) | یونیون‌ها (union) |
| ۲۲ | [session-22-enums](session-22-enums/README.md) | شمارشی‌ها (enum) |
| ۲۳ | [session-23-typedef](session-23-typedef/README.md) | typedef |

### فایل، سیستم و ابزارها (جلسات ۲۴–۳۳)

| # | جلسه | موضوع |
|---|---|---|
| ۲۴ | [session-24-file-basics](session-24-file-basics/README.md) | کار با فایل: مبانی fopen/fread/fwrite |
| ۲۵ | [session-25-file-advanced](session-25-file-advanced/README.md) | فایل پیشرفته: fseek و فایل باینری |
| ۲۶ | [session-26-preprocessor](session-26-preprocessor/README.md) | پیش‌پردازنده (#define، #include، ماکروها) |
| ۲۷ | [session-27-multifile](session-27-multifile/README.md) | پروژهٔ چندفایلی و فایل‌های هدر |
| ۲۸ | [session-28-storage-classes](session-28-storage-classes/README.md) | کلاس‌های ذخیره‌سازی (static، extern، auto، register) |
| ۲۹ | [session-29-bitwise](session-29-bitwise/README.md) | عملگرهای بیتی (Bitwise) |
| ۳۰ | [session-30-command-line-args](session-30-command-line-args/README.md) | آرگومان‌های خط فرمان (argc، argv) |
| ۳۱ | [session-31-error-handling](session-31-error-handling/README.md) | مدیریت خطا (errno، perror) |
| ۳۲ | [session-32-function-pointers](session-32-function-pointers/README.md) | اشاره‌گر به تابع (Function Pointers) |
| ۳۳ | [session-33-void-pointers](session-33-void-pointers/README.md) | اشاره‌گر void و برنامه‌نویسی جنریک ساده |

### ساختمان داده و الگوریتم (جلسات ۳۴–۴۴)

| # | جلسه | موضوع |
|---|---|---|
| ۳۴ | [session-34-singly-linked-list](session-34-singly-linked-list/README.md) | لیست پیوندی یک‌طرفه |
| ۳۵ | [session-35-doubly-linked-list](session-35-doubly-linked-list/README.md) | لیست پیوندی دوطرفه و حلقوی |
| ۳۶ | [session-36-stack](session-36-stack/README.md) | پیاده‌سازی پشته (Stack) |
| ۳۷ | [session-37-queue](session-37-queue/README.md) | پیاده‌سازی صف (Queue) |
| ۳۸ | [session-38-binary-tree](session-38-binary-tree/README.md) | مبانی درخت دودویی |
| ۳۹ | [session-39-bst](session-39-bst/README.md) | درخت جستجوی دودویی (BST) |
| ۴۰ | [session-40-sorting-basic](session-40-sorting-basic/README.md) | مرتب‌سازی: حبابی، انتخابی، درجی |
| ۴۱ | [session-41-sorting-advanced](session-41-sorting-advanced/README.md) | مرتب‌سازی: ادغامی (Merge) و سریع (Quick) |
| ۴۲ | [session-42-searching](session-42-searching/README.md) | جستجو: خطی و دودویی |
| ۴۳ | [session-43-hash-table](session-43-hash-table/README.md) | مبانی جدول هش (Hash Table) |
| ۴۴ | [session-44-backtracking](session-44-backtracking/README.md) | بازگشت پیشرفته (Backtracking) |

### ابزارهای حرفه‌ای و پروژهٔ نهایی (جلسات ۴۵–۵۰)

| # | جلسه | موضوع |
|---|---|---|
| ۴۵ | [session-45-makefile](session-45-makefile/README.md) | Makefile و اتوماسیون build |
| ۴۶ | [session-46-gdb](session-46-gdb/README.md) | دیباگ با gdb |
| ۴۷ | [session-47-valgrind](session-47-valgrind/README.md) | مدیریت حافظه و valgrind |
| ۴۸ | [session-48-pthreads](session-48-pthreads/README.md) | برنامه‌نویسی چندنخی (pthreads) |
| ۴۹ | [session-49-sockets](session-49-sockets/README.md) | مقدمه‌ای بر سوکت‌ها (sockets) |
| ۵۰ | [session-50-final-project](session-50-final-project/README.md) | پروژهٔ نهایی: مینی‌شل و پایگاه‌دادهٔ فایلی |

---

## نقشهٔ راه یادگیری

1. **مسیر اصلی (جلسات ۱ تا ۲۳):** حتماً همه را به ترتیب کامل کنید — این پایهٔ غیرقابل‌مذاکرهٔ C است. هر جلسه تمرین‌هایش را حل کنید و کدها را دستی تایپ کنید (کپی نکنید).
2. **مسیر سیستمی (جلسات ۲۴ تا ۳۳):** کار با فایل، پیش‌پردازنده و مدیریت خطا برای هر پروژهٔ واقعی لازم است.
3. **مسیر الگوریتم (جلسات ۳۴ تا ۴۴):** ساختمان داده‌ها قلب مصاحبه‌های برنامه‌نویسی هستند؛ با لیست پیوندی شروع کنید و تا درخت و جدول هش بروید.
4. **مسیر حرفه‌ای (جلسات ۴۵ تا ۵۰):** ابزارهای روزمرهٔ توسعه‌دهندهٔ C و در نهایت پروژهٔ نهایی که همهٔ مفاهیم را ترکیب می‌کند.

**توصیهٔ طلایی:** اگر جلسه‌ای را «فهمیدید» اما تمرینش را حل نکردید، آن جلسه را تمام‌شده حساب نکنید. C فقط با تمرین عملی آموخته می‌شود.

---

## ساختار کامیت‌های Git

این ریپازیتوری دقیقاً طبق قرارداد زیر ساخته شده است:

- **۱ کامیت اولیه (ساخت اسکلت پروژه):** `git init` + فایل‌های پایه (README اصلی، .gitignore)
- **۵۰ کامیت جداگانه:** بعد از تکمیل هر جلسه (README + examples + exercises)، دقیقاً یک کامیت با پیام `session NN: ...` ثبت شده است.

برای مشاهدهٔ تاریخچه:

```bash
git log --oneline
```

> چرا هر جلسه یک کامیت جداگانه؟ چون با این کار می‌توانید پیشرفت خودتان را گام‌به‌گام دنبال کنید، هر جلسه را جداگانه مرور کنید و اگر خواستید به هر نقطهٔ تاریخی برگردید (`git checkout <hash>`).
