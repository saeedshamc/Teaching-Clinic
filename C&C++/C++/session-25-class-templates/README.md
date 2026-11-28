# جلسهٔ ۲۵ — قالب‌های کلاس

> `Box<int>` و `Box<std::string>` دو نوع متفاوت‌اند که از یک نقشه ساخته شده‌اند.

---

## اهداف این جلسه

1. قالب کلاس بسازید: `template <typename T> class Box`.
2. بفهمید هر `Box<T>` یک **نوع مستقل** است (بدون رابطهٔ وراثتی).
3. تابع عضو قالبی و تابع دوست قالبی بنویسید.
4. یک کانتینر جنریک واقعی (`Stack<T>`) پیاده کنید.
5. با `alias template` و استنتاج نوع کلاس قالب (CTAD) آشنا شوید.

---

## ۱. قالب کلاس

```cpp
template <typename T>
class Box {
public:
    explicit Box(const T& value) : value_(value) { }
    const T& get() const { return value_; }
    void set(const T& value) { value_ = value; }
private:
    T value_;
};
```

استفاده:

```cpp
Box<int> number(42);
Box<std::string> text("سلام");
```

نکات مهم:

- `Box<int>` و `Box<std::string>` **دو نوع کاملاً متفاوت** هستند. نمی‌توانید `Box<int>` را به `Box<double>` بدهید، حتی اگر `int` به `double` تبدیل شود.
- هیچ رابطهٔ وراثتی بین instantiationها وجود ندارد؛ `Box<int>` زیرکلاس `Box<Shape>` نیست.
- هر instantiation، کد خودش را تولید می‌کند (template instantiation).

### تعریف تابع عضو بیرون از کلاس

```cpp
template <typename T>
class Box {
public:
    const T& get() const;
};

template <typename T>
const T& Box<T>::get() const { return value_; }
```

همهٔ `template` و `Box<T>` را دوباره می‌نویسید. به همین دلیل بیشتر کد قالب در هدر و داخل کلاس نوشته می‌شود.

---

## ۲. تابع عضو قالبی

عضو کلاس می‌تواند خودش قالب باشد و **نوع دیگری** بدهد:

```cpp
template <typename U>
Box<U> map(U (*function)(const T&)) const {
    return Box<U>(function(value_));
}
```

```cpp
const Box<int> length = text.map(lengthOf);   // Box<string> → Box<int>
```

این الگو در `std::optional::transform` و `std::expected::map` هم دیده می‌شود. سود: تبدیل بدون شکستن کپسوله‌سازی.

---

## ۳. کانتینر جنریک: `Stack<T>`

```cpp
template <typename T>
class Stack {
public:
    void push(const T& value);
    bool tryPop(T& out);      // خالی → false
    const T& top() const;
    bool empty() const;
    std::size_t size() const;
private:
    std::vector<T> items_;
};
```

خروجی واقعی `02_stack_template.cpp`:

```
اندازهٔ پشته: 3
بالای پشته: 3

--- خالی کردن ---
pop → 3
pop → 2
pop → 1
خالی؟ بله
tryPop روی پشتهٔ خالی: ناموفق

--- پشتهٔ رشته ---
pop → دوم
pop → اول
```

سه تصمیم طراحی که در کد دیده می‌شود:

1. **`tryPop(T&)` به‌جای `pop()`**: بدون استثنا (استثنا موضوع جلسهٔ ۲۶ است) و بدون دسترسی به مقدار ناموجود. `std::stack::pop()` مقدار برنمی‌گرداند و همین کار را با `top()` جدا می‌کند.
2. **`std::vector` به‌عنوان ذخیره‌گاه**: `push_back`/`pop_back` برای پشته کافی است. `std::deque` هم گزینهٔ خوبی است (جلسهٔ ۳۲).
3. **بدون کپی دستی و بدون `new`**: هر مقدار کپی می‌شود و با تخریب `vector` آزاد می‌شود — قانون صفر (جلسهٔ ۳۸).

---

## ۴. `Box<Box<int>>` و alias

```cpp
Box<Box<int>> nested(Box<int>(7));
nested.get().get();          // 7

using IntBox = Box<int>;     // نام کوتاه‌تر
IntBox alias(99);
```

قالب می‌تواند خودش را دربر بگیرد. در `>>` پایانی در C++11 به بعد نیازی به فاصله نیست (`Box<Box<int>>` درست است؛ در C++03 باید `> >` می‌نوشتید).

---

## ۵. CTAD — استنتاج نوع کلاس قالب (C++17)

```cpp
Box box(5);          // C++17: Box<int>
Box text(std::string("سلام"));   // Box<std::string>
```

در C++17، سازنده می‌تواند نوع قالب را از آرگومان استنتاج کند. در این دوره از نوشتن صریح `Box<int>` استفاده می‌کنیم — خواناتر است و در کد بزرگ، نوع را پنهان نمی‌کند.

---

## ۶. کدهای این جلسه

| فایل | موضوع |
|---|---|
| `examples/01_box_template.cpp` | `Box<T>`، تابع عضو قالبی (`map`)، `Box<Box<int>>`، alias |
| `examples/02_stack_template.cpp` | `Stack<T>` کامل با `tryPop`، تست با `int` و `string` |

```bash
cd session-25-class-templates
g++ -std=c++17 -Wall -Wextra -Wpedantic -o box examples/01_box_template.cpp
./box
```

### ۶.۱ `01_box_template.cpp`

```
Box<int> = 42
بعد از set(50) = 50

Box<string> = سلام
Box<double> = 0.75

Box<int> و Box<double> دو نوع جدا هستند.
number.map(doubled) = 100
text.map(lengthOf) = 8

nested.get().get() = 7
IntBox alias = 99
```

- `text.map(lengthOf) = 8`: رشتهٔ «سلام» در UTF-8 هشت بایت است (هر حرف فارسی ۲ بایت) — همان نکتهٔ جلسهٔ ۱۰.
- `number.map(doubled) = 100`: `50 × 2`.

### ۶.۲ `02_stack_template.cpp`

`Stack<int>` و `Stack<std::string>` هر دو با **همان کد** کار می‌کنند و ترتیب LIFO را حفظ می‌کنند: `3, 2, 1` و `دوم, اول`.

---

## ۷. اشتباهات رایج

1. **تعریف قالب کلاس در `.cpp`** → خطای لینک؛ قالب باید در هدر باشد.
2. **انتظار تبدیل بین instantiationها**: `Box<int>` به `Box<double>` تبدیل نمی‌شود.
3. **فراموش کردن `template <typename T>` در تعریف بیرونی متد** → خطاهای عجیب.
4. **پارامتر `T` با ارزش** در متدها → کپی گران؛ `const T&` بهتر است.
5. **استفاده از `T()` بدون اینکه `T` قابل پیش‌فرض‌سازي باشد** → خطای کامپایل فقط وقتی متد استفاده شود.
6. **کد سنگین در هدر** → هر واحد ترجمه دوباره کامپایل می‌کند؛ زمان build بالا می‌رود.
7. **خطاهای چندصدخطی**: اولین خط واقعی `error:` را پیدا کنید، بقیه معمولاً پیامدند.
8. **`swap` بین دو نوع مختلف در کلاس قالبی دوپارامتری** → فقط اگر A و B یکی باشند معنی دارد.

---

## ۸. بهترین شیوه‌ها

- قالب کلاس را در هدر بنویسید، معمولاً با پیاده‌سازی داخل کلاس.
- `const T&` برای پارامترهای ورودی.
- برای کانتینر، `std::vector` را پایه بگذارید و فقط API لازم را عمومی کنید.
- در قالب دوعضوی، ترتیب و نقش پارامترها را مستند کنید (`Pair<Key, Value>`).
- alias template برای نام‌های طولانی: `using IntBox = Box<int>;`
- متدهایی که فقط در بعضی انواع معنی دارند را جدا کنید تا خطا فقط هنگام استفاده ظاهر شود — یا با `static_assert` پیام روشن بدهید (جلسهٔ ۴۸).
- پیش از نوشتن قالب، بررسی کنید `std::` نسخهٔ آماده دارد.

---

## ۹. خلاصه

- `template <typename T> class Box` یک نقشه برای ساخت کلاس‌های متعدد است.
- هر instantiation یک نوع مستقل است.
- تابع عضو می‌تواند خودش قالب باشد (`map`).
- `Stack<T>` نشان می‌دهد کانتینر جنریک چطور بدون `new` و بدون کپی دستی نوشته می‌شود.
- قالب در هدر، با `const T&`، و با پیام‌های خطای خوانده‌شده از انتها.

---

## ۱۰. تمرین‌ها

### تمرین ۱ — `Pair<A, B>` (متوسط)

`exercises/exercise_01.cpp`: جفتی با دو نوع مختلف و تابع کارخانه‌ای.

```cpp
template <typename A, typename B> class Pair {
public:
    Pair(const A& first, const B& second);
    const A& first() const;   const B& second() const;
    void setFirst(const A& value);   void setSecond(const B& value);
    void swap();                      // فقط برای A == B معنی دارد
    std::string toText() const;       // "(first, second)"
};

template <typename A, typename B>
Pair<A, B> makePair(const A& first, const B& second);
```

```
pair1 = (1, یک)
pair2 = (2.5, x)
بعد از setSecond: (1, یک‌بار)

قبل از swap: (10, 20)
بعد از swap: (20, 10)
```

نکتهٔ مهم: `swap()` با `std::swap(first_, second_)` فقط وقتی معنی دارد که `A` و `B` یک نوع باشند. ولی چون عضوهای قالب کلاس **تا زمانی که صدا زده نشوند** ساخته نمی‌شوند، وجودش برای `Pair<int, std::string>` خطا نمی‌دهد. این یکی از تفاوت‌های مهم قالب با کلاس معمولی است.

همچنین دقت کنید `makePair(1, "یک")` نوع `Pair<int, const char*>` می‌دهد، نه `Pair<int, std::string>`؛ برای رشته، `std::string(...)` را صریح بدهید.

راه‌حل: `exercises/exercise_01_solution.cpp`

---

## ۱۱. قدم بعدی

`Stack::top()` روی پشتهٔ خالی رفتار نامشخص دارد و `tryPop` فقط `false` می‌دهد، بدون دلیل. برای گزارش خطاهای واقعی، C++ استثنا دارد — جلسهٔ ۲۶.
