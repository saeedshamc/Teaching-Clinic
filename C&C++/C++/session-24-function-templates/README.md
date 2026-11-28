# جلسهٔ ۲۴ — قالب‌های تابع

> قالب، کپی‌پیست نیست؛ دستور ساخت تابع برای نوع‌هایی است که بعداً می‌آیند.

---

## اهداف این جلسه

1. تابع قالبی بنویسید: `template <typename T>`.
2. استنتاج نوع (type deduction) را بفهمید و بدانید کجا شکست می‌خورد.
3. قالب با چند پارامتر نوع و نوع بازگشتی `auto` بنویسید.
4. **تخصص** (specialization) کامل را برای رفتار متفاوت یک نوع به کار ببرید.
5. بدانید قالب چه هزینه‌ای دارد و چرا خطاهایش دیرتر ظاهر می‌شوند.

---

## ۱. تابع قالبی

بدون قالب، برای هر نوع یک نسخه لازم دارید:

```cpp
int    maxOf(int a, int b);
double maxOf(double a, double b);
std::string maxOf(const std::string& a, const std::string& b);
```

با قالب، **یک** نسخه:

```cpp
template <typename T>
T maxOf(const T& a, const T& b) {
    return a < b ? b : a;
}
```

`T` یک نوع جای‌نگهدار است. هنگام استفاده:

```cpp
maxOf(3, 7);                 // T = int
maxOf(2.5, 1.5);             // T = double
maxOf<std::string>("a", "b"); // T = string (صریح)
```

کامپایلر برای هر نوعی که استفاده کنید، **یک نسخهٔ واقعی** می‌سازد (instantiation). پس:

- کد تکراری در منبع ندارید، ولی در فایل اجرایی ممکن است چند نسخه باشد (code bloat).
- قالب در زمان کامپایل حل می‌شود، پس سربار اجرا ندارد (برخلاف وراثت مجازی).

---

## ۲. استنتاج نوع — و کجا شکست می‌خورد

```cpp
maxOf(3, 7.5);
// ❌ error: deduced conflicting types for parameter 'T'
//          ('int' and 'double')
```

هر دو آرگومان باید **یک نوع** بدهند. سه راه‌حل:

```cpp
maxOf<double>(3, 7.5);           // ۱) نوع را صریح بدهید

template <typename A, typename B> // ۲) دو پارامتر نوع
auto maxOf2(const A& a, const B& b);

maxOf(3.0, 7.5);                 // ۳) آرگومان‌ها را هم‌نوع کنید
```

نکتهٔ دوم: `const T&` باعث می‌شود آرایه و رشتهٔ C به `const char*` تبدیل شوند، نه `std::string`. به همین دلیل در کد `maxOf<std::string>("apple", "banana")` نوشته‌ایم؛ بدون آن، `T` می‌شود `const char*` و مقایسهٔ اشاره‌گرها انجام می‌شود، نه مقایسهٔ متن.

---

## ۳. نوع بازگشتی استنتاج‌شده

```cpp
template <typename A, typename B>
auto addDifferent(const A& a, const B& b) {
    return a + b;          // نوع از عبارت نتیجه می‌شود
}
```

```cpp
addDifferent(2, 3.5);                    // double → 5.5
addDifferent(std::string("C++"), '!');   // string → "C++!"
```

`auto` در C++14 به بعد برای نوع بازگشتی تابع مجاز است. استفادهٔ درست: وقتی نوع نتیجه از نظر خواننده **بدیهی** است. اگر نتیجه پیچیده است، `decltype` یا نوشتن صریح نوع بهتر است.

---

## ۴. تخصص (specialization)

گاه یک نوع باید رفتار متفاوت داشته باشد. قالب اصلی + تخصص:

```cpp
template <typename T>
std::string formatValue(const T& value) {
    std::ostringstream out;
    out << value;
    return out.str();
}

template <>
std::string formatValue<bool>(const bool& value) {
    return value ? "true" : "false";
}

template <>
std::string formatValue<std::string>(const std::string& value) {
    return "\"" + value + "\"";
}
```

خروجی واقعی `02_specialization.cpp`:

```
int      : 42
bool     : true
bool     : false
double   : 3.14
string   : "سلام"
char     : X
long     : 1234567890123

const int هم از قالب اصلی: 7
```

بدون تخصص `bool`، خروجی `1` و `0` بود؛ بدون تخصص `double`، `3.14159` با پنج رقم می‌آمد.

### سه تلهٔ تخصص

1. **ترتیب**: تخصص باید **قبل از** اولین استفاده دیده شود. اگر تابع را در هدر تعریف می‌کنید، تخصص‌ها هم باید در همان هدر و قبل از استفاده باشند. کامپایلر هیچ هشداری نمی‌دهد.
2. **`const` مهم است**: `formatValue<const int>` با `formatValue<int>` یکی نیست. در بیشتر موارد نیازی به تخصص برای `const` نیست، چون `const T&` خودش `const` را می‌بلعد.
3. **overload بهتر از تخصص است** در بیشتر موارد:
   ```cpp
   std::string formatValue(bool value);       // overload، نه specialization
   ```
   overload در فراخوانی عادی انتخاب می‌شود و رفتار قابل پیش‌بینی‌تری دارد. تخصص را برای وقتی نگه دارید که overload ممکن نیست (مثلاً در کلاس یا برای نوع‌های کتابخانه‌ای).

---

## ۵. کدهای این جلسه

| فایل | موضوع |
|---|---|
| `examples/01_max_template.cpp` | `maxOf`، `swapValues`، قالب با `vector`، `auto` برای نوع بازگشتی |
| `examples/02_specialization.cpp` | `formatValue` با تخصص برای `bool`، `string` و `double` |

```bash
cd session-24-function-templates
g++ -std=c++17 -Wall -Wextra -Wpedantic -o max examples/01_max_template.cpp
./max
```

### ۵.۱ `01_max_template.cpp`

```
maxOf(3, 7) = 7
maxOf(2.5, 1.5) = 2.5
maxOf("apple", "banana") = banana

after swap: a = 20 ، b = 10
after swap: دنیا سلام

اعداد: [3, 1, 4, 1, 5]
کلمات: [C++, قالب, تابع]

addDifferent(2, 3.5) = 5.5
addDifferent(string, char) = C++!

maxOf<double>(3, 7.5) = 7.5
```

سه چیز که با اجرا روشن می‌شود:

1. `maxOf("apple", "banana")` با نوع صریح `std::string` کار می‌کند؛ بدون آن، `T` یک `const char*` می‌شد و نتیجه بر اساس آدرس حافظه بود، نه الفبا.
2. `swapValues` برای `std::string` هم کار می‌کند، بدون اینکه یک خط کد اضافه بنویسیم.
3. `addDifferent(2, 3.5)` نوع `double` می‌دهد — نتیجهٔ `int + double` در C++.

### ۵.۲ `02_specialization.cpp`

`char` از قالب اصلی استفاده می‌کند و `X` می‌دهد (بدون گیومه)، چون تخصصی برای `char` ننوشتیم. `long` هم از قالب اصلی می‌آید و کامل چاپ می‌شود.

---

## ۶. اشتباهات رایج

1. **تعریف قالب در `.cpp` و استفاده در فایل دیگر** → خطای لینک. قالب باید در هدر باشد (جلسهٔ ۴۳).
2. **انتظار تبدیل ضمنی** بین آرگومان‌های قالب → `maxOf(3, 7.5)` کامپایل نمی‌شود.
3. **فراموش کردن `const T&`** → کپی گران در هر فراخوانی، و مشکل با انواع غیرقابل کپی.
4. **تخصص بعد از استفاده** → بی‌صدا نادیده گرفته می‌شود.
5. **تخصص برای `const T`** وقتی منظور `T` بوده.
6. **قالب برای همه‌چیز** — اگر تابع فقط با یک نوع کار می‌کند، قالب اضافه پیچیدگی است.
7. **خطاهای طولانی و گنگ**: پیام خطای قالب ممکن است ۵۰ خط باشد. اول **آخرین** خط را بخوانید؛ معمولاً `error:` واقعی همان‌جاست.
8. **کد تکراری در قالب** → هر instantiation کد خودش را دارد؛ برای قالب‌های سنگین، اندازهٔ فایل اجرایی را بررسی کنید.

---

## ۷. بهترین شیوه‌ها

- پارامترها را `const T&` بگیرید، مگر انواع کوچک (`int`، `double`، اشاره‌گر).
- برای انواع کوچک، گرفتن با ارزش هم درست است و کپی ارزان است.
- تخصص را کم و مستند به کار ببرید؛ overload را ترجیح دهید.
- قالب را در هدر تعریف کنید.
- نام پارامتر نوع را معنادار بگذارید: `T` برای عمومی، `Key`/`Value` در کانتینرها.
- قبل از نوشتن قالب، بررسی کنید آیا `std::` نسخهٔ آماده دارد (`std::max`، `std::swap`، `std::sort`).
- خطای قالب را از **انتهای** پیام بخوانید.

---

## ۸. خلاصه

- `template <typename T>` یک تابع برای چند نوع می‌سازد.
- استنتاج نوع باید یکتا باشد؛ وگرنه نوع را صریح بدهید.
- `auto` نوع بازگشتی را از عبارت استنتاج می‌کند.
- تخصص کامل، رفتار یک نوع خاص را عوض می‌کند و باید قبل از استفاده دیده شود.
- قالب در زمان کامپایل حل می‌شود: بدون سربار اجرا، ولی با هزینهٔ اندازهٔ کد.

---

## ۹. تمرین‌ها

### تمرین ۱ — `swapValues` و `maxOf` روی `vector` (متوسط)

`exercises/exercise_01.cpp`: دو قالب تابع که با `int`، `double` و `std::string` کار کنند.

```cpp
template <typename T> void swapValues(T& a, T& b);
template <typename T> T maxOf(const std::vector<T>& values);   // خالی → T()
```

```
swap int: a = 20 ، b = 10
swap string: first = دنیا ، second = سلام

max int = 12
max double = 9.5
max string = peach
max خالی = 0
```

سه نکته:

1. `values.front()` قبل از حلقه، تا حلقه از اندیس ۱ شروع شود و حالت خالی امن بماند.
2. `T()` برای `int` صفر و برای `std::string` رشتهٔ خالی می‌دهد — یک قرارداد ساده و شفاف.
3. برای رشته، `<` ترتیب الفبایی می‌دهد؛ `peach` بعد از `banana` و `apple` است.

راه‌حل: `exercises/exercise_01_solution.cpp`

---

## ۱۰. قدم بعدی

`maxOf` تابعی است که با انواع مختلف کار می‌کند. اگر بخواهیم **کلاس**ی بسازیم که با انواع مختلف کار کند (`Box<int>`، `Box<std::string>`)، به قالب کلاس نیاز داریم — جلسهٔ ۲۵.
