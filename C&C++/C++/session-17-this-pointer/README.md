# جلسهٔ ۱۷ — اشاره‌گر `this`

> هر متد غیر static، بی‌آنکه بنویسیدش، یک پارامتر پنهان دارد: `this`.

---

## اهداف این جلسه

1. بدانید `this` چیست، چه نوعی دارد و کجا ساخته می‌شود.
2. با `this->` ابهام نام پارامتر و عضو را رفع کنید.
3. `*this` را برگردانید و **method chaining** بسازید.
4. تفاوت `this` در متد `const` و غیر `const` را بفهمید.
5. بدانید چه زمانی `*this` را با ارزش برنگردانید (خطر برش و کپی).

---

## ۱. `this` چیست؟

وقتی می‌نویسید:

```cpp
counter.increment();
```

کامپایلر آن را تقریباً به این شکل ترجمه می‌کند:

```cpp
Counter::increment(&counter);
```

و داخل متد، `this` همان `&counter` است:

```cpp
void increment() {
    value_ += step_;              // همان this->value_ += this->step_
}
```

سه ویژگی:

| ویژگی | مقدار |
|---|---|
| نوع در متد غیر `const` | `Counter*` (اشاره‌گر به شیء جاری) |
| نوع در متد `const` | `const Counter*` |
| مقدار | آدرس شیئی که متد روی آن صدا زده شده |

`this` در **تابع static** وجود ندارد، چون شیئی وجود ندارد (جلسهٔ ۱۶).

اندازهٔ `this`: یک اشاره‌گر معمولی. وجودش باعث بزرگ‌تر شدن شیء نمی‌شود؛ فقط در زمان فراخوانی پاس داده می‌شود.

---

## ۲. رفع ابهام نام: `this->`

```cpp
class Counter {
public:
    void setValue(int value) {
        this->value_ = value;   // چپ: عضو ، راست: پارامتر
    }
private:
    int value_ = 0;
};
```

اگر `this->` را بردارید، `value_ = value` هم درست کار می‌کند چون نام‌ها یکی نیستند. ولی وقتی هم‌نام باشند:

```cpp
class Bad {
public:
    void setValue(int value) {
        value = value;        // ❌ خودانتساب پارامتر؛ عضو تغییر نمی‌کند
    }
private:
    int value;                // بدون _ پایانی
};
```

کامپایلر با `-Wall` هشدار `-Wself-assign` می‌دهد (در بعضی نسخه‌ها فقط با `-Winit-self`). دو راه‌حل:

1. عضو را با `_` پایانی نام‌گذاری کنید (سبک این دوره).
2. از `this->value = value;` استفاده کنید.

سبک بهتر: **هر دو** — نام‌گذاری یکدست، و `this->` وقتی ابهام واقعی هست.

---

## ۳. بازگشت `*this` و زنجیره‌سازی متد

```cpp
QueryBuilder& table(const std::string& name) {
    table_ = name;
    return *this;              // reference به همان شیء
}
```

`*this` خود شیء است (نه اشاره‌گر). با `&` برمی‌گردانیم تا **کپی** نشود:

```cpp
builder.table("students").where("age >= 18").orderBy("name").limit(10).build();
```

زنجیره از چپ به راست اجرا می‌شود؛ هر فراخوانی یک `QueryBuilder&` می‌دهد که ورودی فراخوانی بعدی است.

### اگر با ارزش برگردانید چه می‌شود؟

```cpp
QueryBuilder table(const std::string& name) {   // ❌ بدون &
    table_ = name;
    return *this;                                // کپی می‌شود
}
```

- زنجیره هنوز **کار می‌کند** (روی کپی‌ها)، ولی تغییرات روی شیء اصلی گم می‌شوند.
- در پروژه‌های واقعی این باگ ساکت است: کد کامپایل می‌شود، نتیجه غلط است.

در خروجی تمرین، خط `بازگشت همان شیء بود؟ بله` دقیقاً همین را بررسی می‌کند: آدرس شیء برگشتی با آدرس `builder` یکی است.

### زنجیره روی `const`

```cpp
const std::string build() const;
```

متد `const` می‌تواند در انتهای زنجیره باشد، ولی نمی‌تواند ادامه پیدا کند (`build()` رشته برمی‌گرداند، نه `QueryBuilder&`). همچنین نمی‌توانید در متد `const` بنویسید `QueryBuilder& ...`، چون `*this` در آن‌جا `const` است و به `QueryBuilder&` تبدیل نمی‌شود.

---

## ۴. `this` در متد `const`

```cpp
const Counter& self() const {
    return *this;            // نوع: const Counter&
}
```

- در متد `const`، `this` از نوع `const Counter*` است.
- پس نمی‌توانید عضو را تغییر دهید؛ کامپایلر جلوی `this->value_ = 5` را می‌گیرد.
- می‌توانید `*this` را به‌صورت `const&` برگردانید.

همین قاعده دلیل وجود overloadهای دوتایی است:

```cpp
int& operator[](std::size_t index);              // نسخهٔ غیر const
const int& operator[](std::size_t index) const;  // نسخهٔ const
```

جلسهٔ ۱۸ همین را با `operator[]` پیاده می‌کند.

---

## ۵. `this` و مقایسهٔ شیءها

```cpp
bool isLargerThan(const Counter& other) const {
    return value_ > other.value_;
}
```

`other` یک شیء دیگر است، نه `this`. اگر بخواهید بفهمید دو اشاره‌گر به **همان شیء** اشاره می‌کنند:

```cpp
if (this == &other) { ... }      // خودمقایسه‌ای
```

این بررسی در `operator=` و سازندهٔ کپی لازم است (جلسهٔ ۳۷: «self-assignment»). در `operator=` جلسهٔ ۱۸ هم می‌بینیدش.

---

## ۶. کدهای این جلسه

| فایل | موضوع |
|---|---|
| `examples/01_this_basic.cpp` | `this->`، مقایسه با شیء دیگر، `*this` و آدرس شیء |
| `examples/02_method_chaining.cpp` | `QueryBuilder` با زنجیرهٔ کامل متدها |

```bash
cd session-17-this-pointer
g++ -std=c++17 -Wall -Wextra -Wpedantic -o builder examples/02_method_chaining.cpp
./builder
```

### ۶.۱ `01_this_basic.cpp`

```
a: value = 10 ، step = 5
b: value = 12 ، step = 1

after setValue(20)/setStep(2): value = 20 ، step = 2
after increment: 22

a > b ؟ بله
بعد از سه addStep: 28

&a          = 0x7fff7a9233e8
&a.self()   = 0x7fff7a9233e8
same object؟ بله
```

آدرس‌ها در هر اجرا و هر سیستم متفاوت‌اند؛ **برابری** آن‌ها مهم است: `&a == &a.self()` یعنی `self()` کپی نساخته و همان شیء را برگردانده است.

محاسبهٔ مسیر: `20 → setStep(2) → increment → 22 → سه بار addStep(2) → 28`.

### ۶.۲ `02_method_chaining.cpp`

```
کوئری ساخته‌شده:
  SELECT * FROM students WHERE age >= 18 AND passed = 1 ORDER BY name LIMIT 10

کوئری دوم:
  SELECT * FROM courses ORDER BY title

کوئری سوم:
  SELECT * FROM books WHERE price < 100
```

سه چیز که در `build()` دیده می‌شود:

- بخش‌های اختیاری (شرط، ترتیب، محدودیت) فقط وقتی اضافه می‌شوند که تنظیم شده باشند.
- `build()` خودش `const` است، پس روی شیء `const` هم کار می‌کند.
- اگر `build()` را دوبار صدا بزنید، همان رشته دوباره ساخته می‌شود و شیء تغییر نمی‌کند — چون `build` وضعیت را نمی‌نویسد.

---

## ۷. اشتباهات رایج

1. **برگرداندن `*this` با ارزش** به‌جای `*this` با reference → تغییرات گم می‌شوند.
2. **برگرداندن `this` به‌جای `*this`** → نوع اشتباه؛ `this` اشاره‌گر است، `*this` شیء.
3. **`return *this;` در متدی که `QueryBuilder&` برمی‌گرداند ولی خودش `const` است** → خطای تبدیل `const` به غیر `const`.
4. **استفاده از `this` در تابع static** → خطای کامپایل.
5. **نگه‌داشتن `this` در جایی که شیء از بین می‌رود** → اشاره‌گر معلق. مثلاً لامبدایی که `this` را capture می‌کند و بعد از مرگ شیء اجرا می‌شود (جلسهٔ ۳۵).
6. **پیاده کردن `operator=` بدون بررسی `this == &other`** → در خودانتساب داده را نابود می‌کنید.
7. **استفادهٔ افراطی از زنجیره** → عبارات چندخطی غیرقابل دیباگ. زنجیره برای ساختارهای ساده (builder، تنظیمات، پرس‌وجو) خوب است، نه برای منطق تجاری.
8. **نام‌گذاری عضو بدون `_`** و تکیه بر `this->` در همه‌جا → کد پرنویز.

---

## ۸. بهترین شیوه‌ها

- عضوها را با `_` پایانی نام‌گذاری کنید تا ابهام نام کم پیش بیاید.
- `this->` را فقط جایی بنویسید که ابهام واقعی است.
- برای زنجیره‌سازی، `Type&` برگردانید و `return *this;` بزنید.
- متد انتهایی زنجیره (`build`، `str`، `toString`) را `const` کنید.
- در `operator=`/سازندهٔ کپی همیشه خودانتساب را بررسی کنید.
- زنجیره را فقط وقتی به کار ببرید که خوانایی را **بیشتر** می‌کند.

---

## ۹. خلاصه

- `this` اشاره‌گر به شیء جاری است؛ در متد `const` از نوع `const T*`.
- `this->member` ابهام نام پارامتر/عضو را رفع می‌کند.
- `*this` خود شیء است؛ با `&` برمی‌گردانیم تا کپی نشود.
- زنجیره‌سازی = برگرداندن `*this` از هر متد.
- در تابع static، `this` وجود ندارد.

---

## ۱۰. تمرین‌ها

### تمرین ۱ — کلاس `StringBuilder` (متوسط)

`exercises/exercise_01.cpp`: بافری با append زنجیره‌ای.

```cpp
StringBuilder& append(const std::string& text);
StringBuilder& appendLine(const std::string& text);
StringBuilder& clear();
const std::string& str() const;
std::size_t size() const;
bool empty() const;
```

```
متن:
سلام، دنیا
پایان
size = 30
empty؟ خیر

بازگشت همان شیء بود؟ بله
از طریق reference فقط‌خواندنی: سلام، دنیا
پایان!

بعد از clear: size = 0 ، empty؟ بله
```

نکتهٔ `size = 30`: `sizeof` نیست، تعداد **بایت** رشته است. متن فارسی UTF-8 است و هر حرف فارسی معمولاً ۲ بایت — به همین دلیل ۳۰ بایت برای ۱۴ کاراکتر منطقی. این را در جلسهٔ ۱۰ دربارهٔ `std::string` دیدید.

راه‌حل: `exercises/exercise_01_solution.cpp`

---

## ۱۱. قدم بعدی

اکنون می‌توانیم `query + " LIMIT 10"` بنویسیم؟ نه، چون `QueryBuilder` عملگر `+` ندارد. جلسهٔ ۱۸ به کلاس‌ها معنی عملگر می‌دهد: `+`، `==`، `<`، `<<`، `[]` و `++`.
