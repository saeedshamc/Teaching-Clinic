# جلسهٔ ۲۱ — وراثت چندگانه و مشکل الماس

> گاهی یک شیء دو نقش دارد؛ ولی اگر هر دو نقش از یک ریشه بیایند، باید مراقب باشیم.

---

## اهداف این جلسه

1. وراثت چندگانه را بنویسید: `class Copier : public Printer, public Scanner`.
2. ابهام نام را تشخیص دهید و با `Base::member` یا override کردن رفعش کنید.
3. مشکل الماس را بفهمید: دو نسخه از یک پایه، ابهام و دادهٔ تکراری.
4. با **وراثت مجازی** (`virtual public`) الماس را حل کنید.
5. بدانید چرا وراثت چندگانه در C++ کم استفاده می‌شود و کجا واقعاً لازم است.

---

## ۱. وراثت چندگانه چیست؟

```cpp
class Copier : public Printer, public Scanner {
public:
    void copy(const std::string& document) {
        const std::string image = scan(document);   // از Scanner
        print(image);                               // از Printer
    }
};
```

`Copier` هر دو مجموعهٔ اعضا را دارد:

```
Copier
├── Printer  (printed_)
└── Scanner  (scanned_)
```

دو شمارندهٔ مستقل، دو مجموعه متد، یک شیء. این ترکیب وقتی بی‌خطر است که دو پایه **هیچ نام مشترکی** نداشته باشند.

---

## ۲. ابهام نام

```cpp
class Alpha { public: std::string status() const; };
class Beta  { public: std::string status() const; };
class Gamma : public Alpha, public Beta { };
```

```cpp
Gamma gamma;
gamma.status();   // ❌ error: ambiguous request for member 'status'
```

کامپایلر نمی‌داند کدام را انتخاب کند. سه راه‌حل:

```cpp
// ۱) فراخوانی صریح
gamma.Alpha::status();

// ۲) تعریف override در Gamma
std::string status() const {
    return Alpha::status() + " + " + Beta::status();
}

// ۳) using-declaration برای انتخاب یکی
class Gamma : public Alpha, public Beta {
public:
    using Alpha::status;   // status یعنی Alpha::status
};
```

راه ۲ معمولاً بهترین است: ترکیب معنادار می‌سازد و ابهام را برای همیشه حل می‌کند.

> در کد این جلسه، `copier.Printer::printed()` و `copier.Scanner::scanned()` را می‌بینید. این نحو صریح، در کد تولیدی علامت هشدار است: اگر لازم است پایه را نام ببرید، احتمالاً طراحی نیاز به بازبینی دارد.

---

## ۳. مشکل الماس

```
        Person
        /    \
   Student  Teacher
        \    /
   TeachingAssistant
```

بدون `virtual`:

```cpp
class Student : public Person { };
class Teacher : public Person { };
class TeachingAssistant : public Student, public Teacher { };
```

شیء `TeachingAssistant` **دو نسخه** از `Person` دارد: یکی از مسیر `Student`، یکی از مسیر `Teacher`. نتیجه:

- `assistant.name()` → **ابهام**: کدام `Person`؟
- حافظهٔ تکراری (دو `name_`).
- دو `Person` جدا که می‌توانند مقدار متفاوت داشته باشند — و هیچ‌کدام «درست» نیست.
- سازندهٔ `Person` دو بار اجرا می‌شود.

این همان «الماس مرگ» است که در طراحی‌های شیءگرا بدنام است.

---

## ۴. راه‌حل: وراثت مجازی

```cpp
class Student : public virtual Person { };
class Teacher : public virtual Person { };
class TeachingAssistant : public Student, public Teacher { };
```

با `virtual`، فقط **یک** `Person` مشترک وجود دارد و همهٔ مسیرها به آن می‌رسند.

دو نکتهٔ عملی:

1. **سازندهٔ مشتق‌ترین کلاس، `Person` را مستقیم می‌سازد**:
   ```cpp
   TeachingAssistant(const std::string& name)
       : Person(name), Student(name), Teacher(name) { }
   ```
   در وراثت مجازی، سازندهٔ `Student` و `Teacher` از ساختن `Person` صرف‌نظر می‌کنند؛ فقط مشتق‌ترین کلاس این کار را می‌کند. اگر `Person(name)` را ننویسید، سازندهٔ پیش‌فرض `Person` صدا زده می‌شود — و اگر نداشته باشد، خطا.
2. **`virtual` فقط روی مسیر مشترک لازم است**، نه روی هر وراثت. در این مثال، `Student` و `Teacher` هر دو `virtual` هستند.

خروجی واقعی `02_diamond_problem.cpp`:

```
--- ساخت ---
  ساخت Person(Saeed)
  ساخت Student
  ساخت Teacher
  ساخت TeachingAssistant

--- استفاده ---
  من Saeed هستم
  نقش: دانشجو + استاد
  نام از Student: Saeed
  نام از Teacher: Saeed
```

`Person` **یک بار** ساخته شد و `name()` از هر دو مسیر همان مقدار را می‌دهد.

---

## ۵. وراثت چندگانه در دنیای واقعی

دو کاربرد مشروع:

1. **ترکیب چند قابلیت مستقل** (mixin): `Printable` + `Serializable` + `Comparable`. هیچ‌کدام ریشهٔ مشترک ندارند.
2. **پیاده‌سازی چند اینترفیس** — در C++ با کلاس‌های انتزاعی (جلسهٔ ۲۳). این رایج‌ترین کاربرد سالم است.

دو ضدالگو:

1. **ارث بردن از دو کلاس concrete با داده** → الماس، ابهام، پیچیدگی.
2. **استفاده از وراثت چندگانه برای «کد مشترک»** → کوپلینگ شدید؛ ترکیب (composition) بهتر است.

نکتهٔ فنی: با وراثت چندگانه، تبدیل اشاره‌گر بین پایه‌ها **آدرس را تغییر می‌دهد** (چون هر پایه در offset متفاوتی قرار می‌گیرد). این یعنی `static_cast` بین پایه‌ها ممکن است مقدار اشاره‌گر را عوض کند — یکی از دلایلی که `reinterpret_cast` روی این اشاره‌گرها فاجعه است (جلسهٔ ۴۲).

---

## ۶. کدهای این جلسه

| فایل | موضوع |
|---|---|
| `examples/01_multiple_inheritance.cpp` | `Printer`+`Scanner`→`Copier`، ابهام `status()` و رفع آن |
| `examples/02_diamond_problem.cpp` | `Person`/`Student`/`Teacher`/`TeachingAssistant` با وراثت مجازی |

```bash
cd session-21-multiple-inheritance
g++ -std=c++17 -Wall -Wextra -Wpedantic -o multi examples/01_multiple_inheritance.cpp
./multi
```

### ۶.۱ `01_multiple_inheritance.cpp`

```
  چاپ: تصویر گزارش.pdf
  چاپ: تصویر قرارداد.pdf

چاپ‌شده: 2 ، اسکن‌شده: 2
دسترسی جدا: Printer::printed = 2 ، Scanner::scanned = 2

gamma.status() = Alpha + Beta
Alpha::status() = Alpha
Beta::status()  = Beta
```

- `copy()` اول `scan` بعد `print` را صدا می‌زند؛ خروجی، همان مسیر است.
- `printed` و `scanned` دو شمارندهٔ کاملاً مستقل‌اند — نشانهٔ این‌که `Copier` دو زیرشیء دارد.
- `gamma.status()` ترکیب هر دو پایه را می‌دهد؛ اگر override نمی‌کردیم، این خط کامپایل نمی‌شد.

### ۶.۲ `02_diamond_problem.cpp`

```
--- ساخت ---
  ساخت Person(Saeed)
  ساخت Student
  ساخت Teacher
  ساخت TeachingAssistant

--- استفاده ---
  من Saeed هستم
  نقش: دانشجو + استاد
  نام از Student: Saeed
  نام از Teacher: Saeed
```

ترتیب ساخت: `Person` (یک بار) → `Student` → `Teacher` → `TeachingAssistant` → بدنه. ترتیب پایه‌ها همان ترتیب اعلان در `class TeachingAssistant : public Student, public Teacher` است، نه ترتیب لیست مقداردهی.

---

## ۷. اشتباهات رایج

1. **فراموش کردن `virtual`** در وراثت الماسی → ابهام و دادهٔ تکراری.
2. **ننوشتن سازندهٔ پایهٔ مجازی در مشتق‌ترین کلاس** → `Person()` پیش‌فرض صدا زده می‌شود یا خطای کامپایل.
3. **`virtual` روی همه‌جا** — فقط مسیر مشترک لازم دارد؛ اضافه‌کردن بی‌دلیل، سربار و پیچیدگی می‌آورد.
4. **دسترسی به عضو بدون مشخص کردن پایه** وقتی نام تکراری است → ابهام.
5. **وراثت چندگانه از دو کلاس با داده و invariant** → دو نسخه از یک مفهوم، بدون راه حل تمیز.
6. **`static_cast` بین پایه‌ها با فرض آدرس یکسان** → جابه‌جایی اشاره‌گر در وراثت چندگانه؛ از تبدیل ضمنی یا `dynamic_cast` استفاده کنید.
7. **`==` و `=` در وراثت چندگانه** — مقایسهٔ شیء با دو زیرشیء، نیاز به تصمیم صریح دارد.
8. **سلسله‌مراتب الماسی در کلاس‌های concrete** — تقریباً همیشه نشانهٔ طراحی اشتباه است.

---

## ۸. بهترین شیوه‌ها

- وراثت چندگانه را به **ترکیب قابلیت‌های بی‌داده** محدود کنید (اینترفیس‌ها).
- اگر دادهٔ مشترک لازم است، **ترکیب** را انتخاب کنید: `class Copier { Printer printer_; Scanner scanner_; }`.
- هر ابهام را با override معنادار حل کنید، نه با `Base::` پراکنده در کد کاربر.
- در وراثت مجازی، سازندهٔ مشتق‌ترین کلاس را کامل بنویسید.
- قبل از افزودن پایهٔ دوم، بپرسید آیا رابطهٔ is-a برای هر دو برقرار است.
- برای اینترفیس‌ها، از کلاس انتزاعی بدون داده استفاده کنید (جلسهٔ ۲۳).

---

## ۹. خلاصه

- وراثت چندگانه: چند پایه، چند زیرشیء.
- نام تکراری → ابهام؛ راه‌حل: override یا `Base::`.
- الماس: دو نسخه از یک پایه؛ راه‌حل: `virtual` وراثت.
- در وراثت مجازی، مشتق‌ترین کلاس پایهٔ مجازی را می‌سازد.
- کاربرد سالم: اینترفیس‌های بی‌داده؛ نه کد مشترک.

---

## ۱۰. تمرین‌ها

### تمرین ۱ — `Printable` + `Serializable` (متوسط)

`exercises/exercise_01.cpp`: `Document` که هم چاپ می‌شود، هم به متن سریالایز می‌شود.

```cpp
class Printable {
public:
    void printLine(const std::string& line);   // با شمارهٔ خط
    int lineCount() const;
};

class Serializable {
public:
    void addField(const std::string& key, const std::string& value);
    std::string serialize() const;             // "k1=v1;k2=v2"
    const std::vector<std::string>& fields() const;
};

class Document : public Printable, public Serializable { };
```

```
[1] عنوان: گزارش فنی
[2] author=Saeed
[3] pages=12
خطوط چاپ‌شده: 3
serialize: author=Saeed;pages=12
```

نکته: `printLine` وضعیت را تغییر می‌دهد (شمارندهٔ خط)، پس `show()` نمی‌تواند `const` باشد. اگر می‌خواهید `show()` را `const` کنید، شمارنده را `mutable` کنید (جلسهٔ ۴۱) — یا شمارهٔ خط را در `main` بشمارید.

راه‌حل: `exercises/exercise_01_solution.cpp`

---

## ۱۱. قدم نهم

تا این‌جا `Dog dog; dog.sound()` صدا درست را می‌داد، ولی از طریق `Animal&` صدای پایه می‌آمد. جلسهٔ ۲۲ این را با `virtual` حل می‌کند و مخرب مجازی و برش شیء را هم نشان می‌دهد.
