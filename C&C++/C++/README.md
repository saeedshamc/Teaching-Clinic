# دورهٔ جامع آموزش C++ — ۵۰ جلسه

> از صفر مطلق تا C++ مدرن و حرفه‌ای: شیءگرایی کامل، STL، مدیریت حافظهٔ مدرن، الگوهای طراحی و پروژهٔ نهایی.

این پوشه حاوی یک دورهٔ کامل و گام‌به‌گام زبان C++ است. همهٔ توضیحات به **زبان فارسی** نوشته شده و هر جلسه شامل یک `README.md` آموزشی، کدهای نمونهٔ قابل کامپایل (`examples/`) و تمرین‌های عملی (`exercises/`) است.

طرح درس و فازبندی کامل هر ۵۰ جلسه (مفاهیم کلیدی، فایل‌ها، معیار پذیرش و ابزار تست) در فایل [`PLAN.md`](PLAN.md) آمده است؛ این فایل نقش نمای کلی دوره را دارد.

---

## فهرست مطالب

- [معرفی دوره](#معرفی-دوره)
- [پیش‌نیازها](#پیشنیازها)
- [نحوهٔ کامپایل و اجرا](#نحوهٔ-کامپایل-و-اجرا)
- [فازبندی دوره](#فازبندی-دوره)
- [فهرست کامل ۵۰ جلسه](#فهرست-کامل-۵۰-جلسه)
- [نقشهٔ راه یادگیری](#نقشهٔ-راه-یادگیری)
- [ساختار کامیت‌های Git](#ساختار-کامیتهای-git)

---

## معرفی دوره

C++ زبانی است که در همان حال که قدرت و کنترل C را به شما می‌دهد، ابزارهای سطح بالایی مثل کلاس، قالب (Template)، مدیریت خودکار حافظه (RAII) و کتابخانهٔ استاندارد غنی (STL) را هم در اختیارتان می‌گذارد. مرورگرها، موتورهای بازی، سیستم‌های معاملاتی، پایگاه‌داده‌ها و نرم‌افزارهای بی‌درنگ امروز بیشتر با C++ نوشته می‌شوند.

این دوره در **۵۰ جلسه** و در **۹ فاز** طراحی شده است. هر فاز یک هدف مشخص دارد و جلسات درون فاز روی هم سوار می‌شوند؛ ترتیب جلسات را رعایت کنید.

| فاز | جلسات | هدف فاز |
|---|---|---|
| ۰ — راه‌اندازی | — | آماده‌سازی ابزارها، ساختار پروژه و Git |
| ۱ — مبانی زبان | ۱–۶ | نوشتن، کامپایل و اجرای برنامه؛ متغیر، ورودی/خروجی، عملگر، شرط و حلقه |
| ۲ — توابع و داده | ۷–۱۱ | تابع و overloading، reference و pointer، آرایه و vector، string، حافظهٔ پویا |
| ۳ — شیءگرایی (OOP) | ۱۲–۲۳ | کلاس و شیء، کپسوله‌سازی، سازنده/مخرب، static، this، عملگر، friend، وراثت و چندریختی |
| ۴ — قالب و استثنا | ۲۴–۲۶ | template تابع و کلاس، مدیریت استثنا |
| ۵ — کتابخانهٔ استاندارد | ۲۷–۳۵ | کانتینرهای STL، تکرارگر، الگوریتم و لامبدا |
| ۶ — حافظهٔ مدرن | ۳۶–۳۹ | smart pointer، move semantics، RAII، کار با فایل |
| ۷ — C++ حرفه‌ای | ۴۰–۴۴ | namespace، const-correctness، cast، پروژهٔ چندفایلی، CMake |
| ۸ — ابزار و همزمانی | ۴۵–۴۹ | دیباگ، thread و mutex، الگوهای طراحی، ویژگی‌های C++11 تا C++20 |
| ۹ — پروژهٔ نهایی | ۵۰ | ساخت یک برنامهٔ واقعی با همهٔ مفاهیم دوره |

---

## پیش‌نیازها

| ابزار | کاربرد | نصب |
|---|---|---|
| **g++** (یا clang++) | کامپایلر C++ با پشتیبانی C++17 | لینوکس: `sudo apt install g++` — ویندوز: WSL (Ubuntu) یا MinGW-w64 |
| **cmake** | اتوماسیون build (جلسهٔ ۴۴) | لینوکس: `sudo apt install cmake` |
| **gdb** یا **lldb** | دیباگ (جلسهٔ ۴۵) | لینوکس: `sudo apt install gdb` |
| **git** | ثبت پیشرفت هر جلسه | `sudo apt install git` |

برای بررسی نصب:

```bash
g++ --version
```

> **نکتهٔ ویندوز:** ساده‌ترین و مطمئن‌ترین راه، نصب WSL (Ubuntu) و کامپایل داخل لینوکس است. با MinGW-w64 هم دستورات همین است، فقط برای thread در جلسهٔ ۴۶ ممکن است به فلگ اضافه نیاز داشته باشید.

پیش از این دوره لازم نیست C بدانید؛ همه‌چیز از صفر توضیح داده می‌شود. اگر C می‌دانید، جلسات ۱ تا ۱۱ برایتان سریع‌تر می‌گذرد و در جلسهٔ ۸ و ۱۱ تفاوت‌های ظریف C و C++ را دقیق می‌بینید.

---

## نحوهٔ کامپایل و اجرا

همهٔ کدهای نمونه باید با این دستور **بدون خطا و بدون هشدار** کامپایل شوند:

```bash
g++ -std=c++17 -Wall -Wextra -o program examples/01_hello.cpp
./program
```

داخل پوشهٔ هر جلسه:

```bash
cd session-01-intro
g++ -std=c++17 -Wall -Wextra -o hello examples/01_hello.cpp
./hello
```

جلساتی که دستور خاص دارند (پروژهٔ چندفایلی، CMake، thread، پروژهٔ نهایی) دستور خودشان را در README همان جلسه دارند.

---

## فازبندی دوره

توضیح کامل هر فاز، مفاهیم کلیدی هر جلسه، خروجی مورد انتظار و معیار پذیرش در [`PLAN.md`](PLAN.md) است. خلاصهٔ فازها:

1. **فاز ۱ — مبانی زبان (۱–۶):** اولین برنامه، تفاوت C و C++، متغیر و نوع داده، `namespace`، `cin`/`cout`، عملگرها، شرط و حلقه. خروجی فاز: می‌توانید هر الگوریتم سادهٔ خطی را در C++ بنویسید.
2. **فاز ۲ — توابع و داده (۷–۱۱):** تابع، پارامتر پیش‌فرض، overloading، reference در برابر pointer، آرایه و `std::vector`، `std::string`، حافظهٔ پویا با `new`/`delete`. خروجی فاز: کد را به توابع تمیز بشکنید و داده‌ها را بدون خطای حافظه مدیریت کنید.
3. **فاز ۳ — شیءگرایی (۱۲–۲۳):** از `struct` تا `class`، کپسوله‌سازی، سازنده و مخرب، سطوح دسترسی، اعضای `static`، `this`، سربارگذاری عملگر، `friend`، وراثت، وراثت چندگانه، `virtual` و کلاس انتزاعی. خروجی فاز: طراحی یک مدل شیءگرای واقعی با سلسله‌مراتب کلاس.
4. **فاز ۴ — قالب و استثنا (۲۴–۲۶):** template تابع و کلاس، `try`/`catch`/`throw` و اصول نوشتن کد مقاوم.
5. **فاز ۵ — STL (۲۷–۳۵):** کانتینرها، انتخاب کانتینر درست، تکرارگرها، الگوریتم‌ها و لامبدا. خروجی فاز: به‌جای حلقه‌های دستی از الگوریتم‌های استاندارد استفاده کنید.
6. **فاز ۶ — حافظهٔ مدرن (۳۶–۳۹):** `unique_ptr`/`shared_ptr`/`weak_ptr`، move semantics، RAII و `fstream`. خروجی فاز: کد بدون `new`/`delete` خام و بدون نشت حافظه.
7. **فاز ۷ — C++ حرفه‌ای (۴۰–۴۴):** namespace، const-correctness، چهار cast، پروژهٔ چندفایلی با header guard و CMake.
8. **فاز ۸ — ابزار و همزمانی (۴۵–۴۹):** gdb، `std::thread` و `mutex`، الگوهای طراحی، ویژگی‌های C++11 تا C++20، `chrono`/`optional`/`variant`.
9. **فاز ۹ — پروژهٔ نهایی (۵۰):** یک برنامهٔ واقعی (سیستم مدیریت کتابخانه یا بازی کنسولی) با استفاده از تمام مفاهیم.

---

## فهرست کامل ۵۰ جلسه

### فاز ۱ — مبانی زبان (جلسات ۱–۶)

| # | جلسه | موضوع |
|---|---|---|
| ۱ | [session-01-intro](session-01-intro/README.md) | مقدمه‌ای بر C++، تفاوت با C، نصب g++ و اولین برنامه |
| ۲ | [session-02-variables-types-namespace](session-02-variables-types-namespace/README.md) | متغیرها، انواع داده، `namespace` و `const`/`auto` |
| ۳ | [session-03-io-streams](session-03-io-streams/README.md) | جریان‌های ورودی/خروجی (`cin`/`cout`) |
| ۴ | [session-04-operators](session-04-operators/README.md) | عملگرها و عبارات |
| ۵ | [session-05-conditionals](session-05-conditionals/README.md) | شرط‌ها (`if`/`else`/`switch`) |
| ۶ | [session-06-loops](session-06-loops/README.md) | حلقه‌ها (`for`/`while`/`do-while`) |

### فاز ۲ — توابع و داده (جلسات ۷–۱۱)

| # | جلسه | موضوع |
|---|---|---|
| ۷ | [session-07-functions-overloading](session-07-functions-overloading/README.md) | توابع، پارامتر پیش‌فرض و overloading |
| ۸ | [session-08-references-pointers](session-08-references-pointers/README.md) | مرجع (Reference) در برابر اشاره‌گر (Pointer) |
| ۹ | [session-09-arrays-vector](session-09-arrays-vector/README.md) | آرایه‌ها و مقدمه‌ای بر `std::vector` |
| ۱۰ | [session-10-std-string](session-10-std-string/README.md) | رشته‌ها با `std::string` |
| ۱۱ | [session-11-pointers-dynamic-memory](session-11-pointers-dynamic-memory/README.md) | اشاره‌گرها و حافظهٔ پویا (`new`/`delete`) |

### فاز ۳ — شیءگرایی (جلسات ۱۲–۲۳)

| # | جلسه | موضوع |
|---|---|---|
| ۱۲ | [session-12-struct-vs-class](session-12-struct-vs-class/README.md) | تفاوت `struct` و `class` |
| ۱۳ | [session-13-oop-encapsulation](session-13-oop-encapsulation/README.md) | مقدمهٔ OOP: کلاس، شیء، کپسوله‌سازی |
| ۱۴ | [session-14-constructors-destructors](session-14-constructors-destructors/README.md) | سازنده‌ها و مخرب‌ها |
| ۱۵ | [session-15-access-control](session-15-access-control/README.md) | سطوح دسترسی و getter/setter |
| ۱۶ | [session-16-static-members](session-16-static-members/README.md) | اعضای `static` در کلاس |
| ۱۷ | [session-17-this-pointer](session-17-this-pointer/README.md) | اشاره‌گر `this` |
| ۱۸ | [session-18-operator-overloading](session-18-operator-overloading/README.md) | سربارگذاری عملگر |
| ۱۹ | [session-19-friend](session-19-friend/README.md) | توابع و کلاس‌های `friend` |
| ۲۰ | [session-20-inheritance-basics](session-20-inheritance-basics/README.md) | مبانی وراثت |
| ۲۱ | [session-21-multiple-inheritance](session-21-multiple-inheritance/README.md) | وراثت چندگانه |
| ۲۲ | [session-22-polymorphism](session-22-polymorphism/README.md) | چندریختی و توابع `virtual` |
| ۲۳ | [session-23-abstract-classes](session-23-abstract-classes/README.md) | کلاس انتزاعی و pure virtual |

### فاز ۴ — قالب و استثنا (جلسات ۲۴–۲۶)

| # | جلسه | موضوع |
|---|---|---|
| ۲۴ | [session-24-function-templates](session-24-function-templates/README.md) | قالب‌های تابع |
| ۲۵ | [session-25-class-templates](session-25-class-templates/README.md) | قالب‌های کلاس |
| ۲۶ | [session-26-exceptions](session-26-exceptions/README.md) | مدیریت استثنا (`try`/`catch`/`throw`) |

### فاز ۵ — کتابخانهٔ استاندارد (جلسات ۲۷–۳۵)

| # | جلسه | موضوع |
|---|---|---|
| ۲۷ | [session-27-stl-overview](session-27-stl-overview/README.md) | نمای کلی کانتینرهای STL |
| ۲۸ | [session-28-stl-vector](session-28-stl-vector/README.md) | بررسی عمیق `vector` |
| ۲۹ | [session-29-stl-list-deque](session-29-stl-list-deque/README.md) | `list` و `deque` |
| ۳۰ | [session-30-stl-map](session-30-stl-map/README.md) | `map` و `unordered_map` |
| ۳۱ | [session-31-stl-set](session-31-stl-set/README.md) | `set` و `unordered_set` |
| ۳۲ | [session-32-stl-adaptors](session-32-stl-adaptors/README.md) | `stack`، `queue` و `priority_queue` |
| ۳۳ | [session-33-iterators](session-33-iterators/README.md) | تکرارگرها (Iterators) |
| ۳۴ | [session-34-stl-algorithms](session-34-stl-algorithms/README.md) | الگوریتم‌های STL |
| ۳۵ | [session-35-lambdas](session-35-lambdas/README.md) | عبارات لامبدا |

### فاز ۶ — حافظهٔ مدرن و منابع (جلسات ۳۶–۳۹)

| # | جلسه | موضوع |
|---|---|---|
| ۳۶ | [session-36-smart-pointers](session-36-smart-pointers/README.md) | `unique_ptr`، `shared_ptr`، `weak_ptr` |
| ۳۷ | [session-37-move-semantics](session-37-move-semantics/README.md) | Move Semantics و rvalue reference |
| ۳۸ | [session-38-raii](session-38-raii/README.md) | اصل RAII و مدیریت منابع |
| ۳۹ | [session-39-fstream](session-39-fstream/README.md) | کار با فایل با `fstream` |

### فاز ۷ — C++ حرفه‌ای (جلسات ۴۰–۴۴)

| # | جلسه | موضوع |
|---|---|---|
| ۴۰ | [session-40-namespaces-advanced](session-40-namespaces-advanced/README.md) | namespace به‌صورت عمیق‌تر |
| ۴۱ | [session-41-const-correctness](session-41-const-correctness/README.md) | const-correctness |
| ۴۲ | [session-42-casts](session-42-casts/README.md) | تبدیل نوع: `static_cast`، `dynamic_cast`، `const_cast`، `reinterpret_cast` |
| ۴۳ | [session-43-multifile-build](session-43-multifile-build/README.md) | پروژهٔ چندفایلی، header guard و کامپایل جداگانه |
| ۴۴ | [session-44-cmake](session-44-cmake/README.md) | مبانی CMake |

### فاز ۸ — ابزار، همزمانی و الگوها (جلسات ۴۵–۴۹)

| # | جلسه | موضوع |
|---|---|---|
| ۴۵ | [session-45-gdb](session-45-gdb/README.md) | دیباگ با gdb یا lldb |
| ۴۶ | [session-46-threads](session-46-threads/README.md) | برنامه‌نویسی چندنخی (`std::thread`، `mutex`) |
| ۴۷ | [session-47-design-patterns](session-47-design-patterns/README.md) | الگوهای طراحی (Singleton، Factory) |
| ۴۸ | [session-48-modern-cpp](session-48-modern-cpp/README.md) | ویژگی‌های مدرن C++ (C++11 تا C++20) |
| ۴۹ | [session-49-std-utilities](session-49-std-utilities/README.md) | ابزارهای استاندارد: `chrono`، `optional`، `variant` |

### فاز ۹ — پروژهٔ نهایی (جلسهٔ ۵۰)

| # | جلسه | موضوع |
|---|---|---|
| ۵۰ | [session-50-final-project](session-50-final-project/README.md) | پروژهٔ نهایی: سیستم مدیریت کتابخانه با همهٔ مفاهیم دوره |

---

## نقشهٔ راه یادگیری

1. **مسیر اصلی (جلسات ۱ تا ۲۶):** پایهٔ غیرقابل‌مذاکره است. هر جلسه تمرین‌هایش را حل کنید و کدها را دستی تایپ کنید (کپی نکنید).
2. **مسیر کتابخانه (جلسات ۲۷ تا ۳۵):** STL روزمرهٔ هر برنامه‌نویس C++ است؛ با `vector` شروع کنید و با الگوریتم و لامبدا تمام کنید.
3. **مسیر حافظهٔ امن (جلسات ۳۶ تا ۳۹):** مهارت تشخیص استفاده از `new`/`delete` خام و جایگزینی درست آن با smart pointer — تفاوت کد آماتور و حرفه‌ای همین‌جاست.
4. **مسیر مهندسی (جلسات ۴۰ تا ۴۹):** چندفایلی، CMake، دیباگ، thread و الگوهای طراحی؛ همان چیزی که در پروژهٔ واقعی به کار می‌آید.
5. **پروژهٔ نهایی (جلسهٔ ۵۰):** همهٔ فازها را در یک برنامهٔ کامل کنار هم می‌بینید.

**توصیهٔ طلایی:** اگر جلسه‌ای را «فهمیدید» اما تمرینش را حل نکردید، آن جلسه را تمام‌شده حساب نکنید. C++ فقط با تمرین عملی و کامپایل کردن کد آموخته می‌شود.

---

## ساختار کامیت‌های Git

این دوره دقیقاً طبق قرارداد زیر ساخته می‌شود:

- **۱ کامیت اولیه (اسکلت پروژه):** `.gitignore`، `README.md`، `PLAN.md` و اسکلت پوشه‌ها.
- **۵۰ کامیت جداگانه:** بعد از تکمیل هر جلسه (README + `examples/` + `exercises/` نوشته و کامپایل و تست‌شده)، دقیقاً یک کامیت با پیام `session NN: ...` ثبت می‌شود. هیچ‌وقت چند جلسه با هم ادغام نمی‌شوند.

برای مشاهدهٔ تاریخچه:

```bash
git log --oneline -- "C&C++/C++"
```

> چرا هر جلسه یک کامیت جداگانه؟ چون با این کار می‌توانید پیشرفت خودتان را گام‌به‌گام دنبال کنید، هر جلسه را جداگانه مرور کنید و اگر خواستید به هر نقطهٔ تاریخی برگردید (`git checkout <hash>`).
