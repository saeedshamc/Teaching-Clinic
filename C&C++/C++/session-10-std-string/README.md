# جلسهٔ ۱۰ — `std::string`

> متن، رایج‌ترین دادهٔ دنیای واقعی است. `std::string` ابزاری است که این داده را بدون دردسر مدیریت می‌کند.

---

## اهداف این جلسه

1. `std::string` را بسازید، مقدار بدهید و به هم بچسبانید.
2. `length()`، `empty()`، `front()`، `back()` و دسترسی با `[]`/`at()` را به کار ببرید.
3. با `find`، `rfind`، `substr`، `replace`، `insert` و `erase` متن را پردازش کنید.
4. بدانید `std::string::npos` چیست و چرا `== npos` مقایسهٔ درست است.
5. عدد را به رشته و رشته را به عدد تبدیل کنید و خطاها را مدیریت کنید.
6. بدانید `length()` بایت می‌شمارد و با متن فارسی رفتار متفاوت است.

---

## ۱. `std::string` در برابر آرایهٔ `char`

در C، رشته آرایه‌ای از `char` با `\0` در انتها بود: `char name[20] = "Ali";` با این مشکلات:

- طول ثابت و از پیش تعیین‌شده.
- فراموش کردن `\0` → خطاهای عجیب.
- سرریز با `strcpy` و `strcat`.
- مقایسه با `strcmp`، نه `==`.

`std::string` همهٔ این‌ها را حل می‌کند: اندازه را خودش مدیریت می‌کند، با `==` مقایسه می‌شود، با `+` جمع می‌شود و حافظه را آزاد می‌کند.

```cpp
#include <string>

std::string name = "Saeed";
std::string last = "Ahmadi";
std::string full = name + " " + last;   // "Saeed Ahmadi"
```

> `#include <string>` را فراموش نکنید. `iostream` هم `std::string` را کامل نمی‌کند (فقط پیش‌اعلان دارد) و ممکن است خطاهای عجیب بگیرید.

---

## ۲. ساخت

```cpp
std::string a;                  // خالی
std::string b = "Hello";        // از رشتهٔ C
std::string c("Hello");         // همان
std::string d(3, '*');          // "***"
std::string e = b;              // کپی مستقل
std::string f(b, 0, 2);         // "He" (برش هنگام ساخت)
```

`std::string` مقدار است، نه اشاره‌گر: `e = b` یک کپی کامل می‌سازد. تغییر `e` روی `b` اثر ندارد.

---

## ۳. طول و دسترسی

```cpp
std::string s = "Hello, C++!";

s.length();     // 11
s.size();       // 11 — دقیقاً همان length
s.empty();      // false
s.front();      // 'H'
s.back();       // '!'
s[0];           // 'H' — بدون بررسی مرز
s.at(0);        // 'H' — با بررسی مرز، خارج از محدوده → std::out_of_range
```

`length()` و `size()` هیچ تفاوتی ندارند؛ `size()` برای هم‌خوانی با بقیهٔ کانتینرها اضافه شده است.

تغییر درجا:

```cpp
s[0] = 'h';          // "hello, C++!"
s.at(1) = 'E';       // "hEllo, C++!"
```

---

## ۴. اتصال

| روش | کد | نکته |
|---|---|---|
| `+` | `a + b` | رشتهٔ جدید می‌سازد |
| `+=` | `a += b` | درجا اضافه می‌کند (کارآمدتر) |
| `append` | `a.append(b)` | همان `+=` |
| `push_back` | `a.push_back('!')` | یک کاراکتر |

**تلهٔ کلاسیک:**

```cpp
std::string s = "Hi" + " there";    // خطا! دو const char* را جمع می‌کند
std::string s = std::string("Hi") + " there";   // درست
```

`+` فقط وقتی کار می‌کند که **حداقل یکی** از دو طرف `std::string` باشد. در `a + ", " + b` چون `a` رشته است، بقیه هم درست زنجیر می‌شوند.

---

## ۵. جست‌وجو

```cpp
std::string text = "C++ is powerful. C++ is fast.";

text.find("C++");          // 0
text.find("C++", 1);       // 17 — از جایگاه ۱ به بعد
text.rfind("is");          // 21 — آخرین
text.find("Java");         // std::string::npos
text.find_first_of(" .");  // 3  — اولین کاراکتر از مجموعه
text.find_last_of(" .");   // 27
```

`std::string::npos` یک مقدار `size_type` است که «پیدا نشد» را نشان می‌دهد. همیشه با `==` یا `!=` مقایسه کنید:

```cpp
if (text.find("Java") == std::string::npos) {
    std::cout << "پیدا نشد\n";
}
```

> ⚠️ `npos` در واقع بزرگ‌ترین مقدار ممکن `size_t` است (`-1` تبدیل‌شده). هرگز آن را به `int` تبدیل نکنید و از `<`/`>` برای بررسی استفاده نکنید.

شمارش همهٔ تکرارها:

```cpp
int count = 0;
std::size_t pos = text.find("C++");
while (pos != std::string::npos) {
    ++count;
    pos = text.find("C++", pos + 1);   // +1 تا همین مورد دوباره پیدا نشود
}
```

---

## ۶. برش، جایگزینی، درج و حذف

```cpp
std::string s = "Saeed Ahmadi (C++)!";

s.substr(0, 5);    // "Saeed" — از ۰، به طول ۵
s.substr(6);       // "Ahmadi (C++)!" — تا انتها

std::string m = "I like Java.";
m.replace(m.find("Java"), 4, "C++");   // "I like C++."

std::string t = "C++";
t.insert(0, "[");      // "[C++]"؟ نه! "[C++"
t.insert(t.length(), "]");   // "[C++]"
t.erase(0, 1);         // "C++]"
```

نکتهٔ مهم در `insert`/`erase`: **موقعیت‌ها با اندیس جابه‌جا می‌شوند**. اگر چند تغییر پشت‌سرهم می‌دهید، اندیس‌ها را بعد از هر تغییر دوباره حساب کنید.

تقسیم رشته با یک جداکننده (الگوی بسیار کاربردی):

```cpp
const std::string line = "ali:85:sara:92";
std::size_t start = 0;
while (start <= line.length()) {
    const std::size_t colon = line.find(':', start);
    if (colon == std::string::npos) {
        std::cout << line.substr(start) << "\n";
        break;
    }
    std::cout << line.substr(start, colon - start) << " | ";
    start = colon + 1;
}
```

---

## ۷. تبدیل عدد و رشته

### عدد → رشته

```cpp
std::to_string(42);        // "42"
std::to_string(3.14159);   // "3.141590" — همیشه ۶ رقم اعشار
std::to_string(true);      // "1"
```

`to_string` ساده است ولی کنترل قالب ندارد. برای دقت دلخواه از `std::ostringstream` یا `std::format` (C++20) استفاده کنید.

### رشته → عدد

```cpp
std::stoi("1234");     // 1234
std::stod("2.75");     // 2.75
std::stol("9000000000");
std::stoi("42abc");    // 42 — از ابتدا می‌خواند و بقیه را نادیده می‌گیرد
```

دو استثنای مهم:

| ورودی | استثنا |
|---|---|
| `"abc"` | `std::invalid_argument` |
| `"99999999999999999999"` | `std::out_of_range` |

پس همیشه در `try/catch` بگیرید یا **قبل از** تبدیل اعتبارسنجی کنید:

```cpp
bool allDigits = !input.empty();
for (const char& ch : input) {
    if (ch < '0' || ch > '9') {
        allDigits = false;
    }
}
```

---

## ۸. کدهای این جلسه

| فایل | موضوع |
|---|---|
| `examples/01_string_basics.cpp` | ساخت، اتصال، طول، دسترسی، `substr`، مقایسه، پیمایش |
| `examples/02_string_search.cpp` | `find`/`rfind`/`npos`، `replace`، `insert`، `erase`، تقسیم با جداکننده |
| `examples/03_number_string.cpp` | `to_string`، `stoi`/`stod`، مدیریت استثنا، اعتبارسنجی رقم‌ها |

```bash
cd session-10-std-string
g++ -std=c++17 -Wall -Wextra -Wpedantic -o string_basics examples/01_string_basics.cpp
./string_basics
```

### ۸.۱ `01_string_basics.cpp`

خروجی:

```
first = Hello
repeated = ***
empty.empty() = true

greeting = Hello, C++!
length = 11 ، size = 11

front = H ، back = ! ، at(1) = e
بعد از تغییر: hello, C++!

name = Saeed Ahmadi (C++)!
length = 19

substr(0, 5)  = Saeed
substr(6)     = Ahmadi (C++)!

apple < banana ؟ بله
a == "apple" ؟ بله

حروف name (بدون فاصله و نشانه) = 14
تعداد حرف t در جمله = 2

length("سلام") = 8 بایت (هر حرف فارسی ۲ بایت)
```

**نکتهٔ فارسی:** `"سلام"` چهار حرف است ولی `length()` عدد **۸** می‌دهد، چون هر حرف فارسی در UTF-8 دو بایت است. پس:
- `length()` طول بایت است، نه تعداد حرف.
- `substr(0, 2)` روی متن فارسی یک حرف کامل می‌دهد، ولی `substr(0, 1)` یک بایت نصفه و متن خراب.
- برای پردازش واقعی یونیکد باید از کتابخانهٔ جداگانه استفاده کرد. برای همین در کد دوره نام‌ها را لاتین نگه داشته‌ایم.

### ۸.۲ `02_string_search.cpp`

```
text = C++ is powerful. C++ is fast.
first find("C++") = 0
second find("C++") = 17
find("Java") == std::string::npos ؟ بله
تعداد تکرار C++ = 2
rfind("is") = 21
find_first_of(" .") = 3

message = I like C++.
tags = [C++]
بعد از erase(0, 1) = C++]

تقسیم "ali:85:sara:92" با ':' → ali | 85 | sara | 92
جایگزینی همهٔ '-' : a + b + c + d
```

### ۸.۳ `03_number_string.cpp`

```
std::to_string(42)      = 42
std::to_string(3.14159) = 3.141590  (۶ رقم اعشار پیش‌فرض)
std::to_string(big)     = 9000000000
با cout: 3.14

std::stoi("1234") = 1234 ، دو برابر = 2468
std::stod("2.75") * 4 = 11.00
std::stoi("42abc") = 42

stoi("abc") → invalid_argument: stoi
stoi(عدد بسیار بزرگ) → out_of_range: stoi

"2026" فقط رقم است؟ بله
report = بخش1 ، بخش2 ، بخش3
```

پیام `what()` برای `stoi` در کتابخانهٔ GNU فقط نام تابع است (`stoi`)؛ در پیاده‌سازی‌های دیگر ممکن است متن کامل‌تری ببینید. به متن `what()` وابسته نشوید، به **نوع استثنا** تکیه کنید.

---

## ۹. اشتباهات رایج

1. **جمع دو رشتهٔ C با `+`**: `"a" + "b"` خطاست.
2. **`#include <string>` فراموش شود**.
3. **انتساب `npos` به `int`** → مقدار منفی و مقایسهٔ غلط.
4. **`substr` با طول بیش از باقی رشته** → استثنای `std::out_of_range`. قبلش بررسی کنید: `substr(pos, len)` اگر `pos > size()` باشد اشکال دارد، ولی `len` بزرگ‌تر از باقی، فقط تا انتها برمی‌گرداند.
5. **`s[i]` با `i == size()`**: آنجا `\0` نیست که امن باشد؛ رفتار تعریف‌نشده.
6. **`std::toupper`/`tolower` روی `char` منفی** (حروف غیرASCII) → استفاده از `static_cast<unsigned char>`.
7. **حلقه روی رشته و تغییر طول آن** → اندیس‌ها نامعتبر می‌شوند.
8. **مقایسهٔ نتیجهٔ `find` با `0` برای «پیدا نشد»** → `0` یعنی «در ابتدا پیدا شد»!

---

## ۱۰. بهترین شیوه‌ها

- رشته را با `const std::string&` به تابع بدهید.
- برای ساختن متن از حلقه، `+=` بزنید نه `+` (هر `+` یک رشتهٔ جدید می‌سازد).
- اگر طول نهایی تقریبی معلوم است، `reserve` بزنید.
- قبل از `stoi` اعتبارسنجی کنید یا `try/catch` بگذارید.
- برای متن فارسی، به بایت بودن `length()` توجه کنید.
- به‌جای دستکاری دستی اندیس‌ها، از `find`/`substr` استفاده کنید.

---

## ۱۱. خلاصه

- `std::string` رشتهٔ پویا، امن و مقدارمحور است.
- `length()`/`size()` بایت می‌شمارند.
- `find` جایگاه یا `npos` می‌دهد؛ همیشه با `npos` مقایسه کنید.
- `substr`/`replace`/`insert`/`erase` ابزارهای پردازش متن‌اند و اندیس‌ها را جابه‌جا می‌کنند.
- `to_string` برای خروجی، `stoi`/`stod` برای ورودی، همراه با `invalid_argument` و `out_of_range`.
- `+` فقط وقتی کار می‌کند که یکی از طرفین `std::string` باشد.

---

## ۱۲. تمرین‌ها

### تمرین ۱ — شمارش کلمات (متوسط)

`exercises/exercise_01.cpp`: یک جمله با `std::getline` بخوانید و تعداد کلمات را بشمارید. فاصله‌های اضافی نباید کلمهٔ اضافه بشمارند.

```
جمله: C++ is a powerful language
تعداد کلمات: 5
```

و برای جملهٔ `   hello    world  ` باید `2` بدهد.

راهنما: یک `bool insideWord` نگه دارید؛ هر بار از «بیرون» به «داخل» رفتید یک کلمه اضافه کنید.

راه‌حل: `exercises/exercise_01_solution.cpp` (طولانی‌ترین کلمه را هم چاپ می‌کند)

### تمرین ۲ — پالیندروم (متوسط)

`exercises/exercise_02.cpp`: بگویید آیا متن از دو طرف یکسان خوانده می‌شود، با نادیده گرفتن فاصله، نشانه‌ها و بزرگی/کوچکی حروف.

```
متن: A man, a plan, a canal: Panama
پالیندروم است.

متن: hello
پالیندروم نیست.
```

راهنما: رشتهٔ کمکی از حروف/ارقام `tolower`شده بسازید و دو اندیس از ابتدا و انتها مقایسه کنید.

راه‌حل: `exercises/exercise_02_solution.cpp`

---

## ۱۳. قدم بعدی

حالا `vector` و `string` را داریم، اما هنوز حافظهٔ پویا را دستی مدیریت نکرده‌ایم. جلسهٔ ۱۱ به `new`/`delete`، نشتی حافظه و dangerous pointerها می‌پردازد — و نشان می‌دهد چرا در C++ مدرن تقریباً هیچ‌وقت به آن‌ها نیاز ندارید.
