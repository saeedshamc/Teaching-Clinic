# جلسهٔ ۲۲ — چندریختی و توابع virtual

> `virtual` به C++ می‌گوید: «تصمیم را تا زمان اجرا نگه دار».

---

## اهداف این جلسه

1. تفاوت **binding ایستا** و **binding پویا** را ببینید.
2. `virtual` و `override` را درست به کار ببرید.
3. بفهمید چرا مخرب پایه باید `virtual` باشد.
4. **برش شیء** (object slicing) را تشخیص دهید و از آن دوری کنید.
5. بدانید چندریختی چه هزینه‌ای دارد و کجا ارزشش را دارد.

---

## ۱. مشکل: تابع بر اساس نوع اعلان انتخاب می‌شود

بدون `virtual`:

```cpp
class Animal {
public:
    std::string sound() const { return "..."; }
};
class Dog : public Animal {
public:
    std::string sound() const { return "واق واق"; }
};

Dog dog;
const Animal& ref = dog;
ref.sound();     // "..."  ← نه "واق واق"
```

کامپایلر در **زمان کامپایل** تصمیم می‌گیرد: `ref` از نوع `Animal&` است، پس `Animal::sound` را صدا بزند. این «binding ایستا» است و برای بیشتر کدها همان چیزی است که می‌خواهیم.

ولی وقتی می‌خواهیم مجموعه‌ای از شکل‌ها را پیمایش کنیم، این رفتار کار را خراب می‌کند:

```cpp
for (const Shape* shape : shapes) {
    shape->area();   // همیشه Shape::area → همیشه 0
}
```

---

## ۲. راه‌حل: `virtual`

```cpp
class Shape {
public:
    virtual double area() const { return 0.0; }
    virtual std::string name() const { return "شکل"; }
};

class Circle : public Shape {
public:
    double area() const override { return 3.14159 * radius_ * radius_; }
};
```

با `virtual`:

- هر شیء **جدول توابع مجازی** (vtable) دارد و اشاره‌گری به آن در شیء ذخیره می‌شود.
- هنگام فراخوانی `shape->area()`، تابع از روی vtable شیء **واقعی** انتخاب می‌شود، نه از روی نوع اشاره‌گر.
- این انتخاب در زمان اجرا هزینهٔ یک lookup اضافه دارد (چند نانوثانیه) — در ۹۹٪ کدها بی‌اهمیت.

خروجی واقعی `01_virtual_function.cpp`:

```
--- از طریق Shape* ---
دایره: 12.5664
مستطیل: 12
مثلث: 15

مجموع مساحت‌ها: 39.5664

--- describe() که area() را صدا می‌زند ---
دایره با مساحت 12.5664
مستطیل با مساحت 12
مثلث با مساحت 15
```

نکتهٔ مهم در بخش دوم: `describe()` **غیر virtual** است و در کلاس پایه تعریف شده، ولی رفتارش چندریخت است — چون `area()` و `name()` را صدا می‌زند و آن‌ها virtual هستند. این الگوی «template method» است: اسکلت در پایه، جزئیات در مشتق.

---

## ۳. `override` — همیشه بنویسید

```cpp
class Circle : public Shape {
public:
    double area() const override;   // ✅ کامپایلر بررسی می‌کند
};
```

`override` به کامپایلر می‌گوید «من قصد دارم تابع پایه را بازنویسی کنم». اگر امضای تابع تفاوتی داشته باشد (مثلاً `const` جا افتاده باشد)، **خطای کامپایل** می‌دهید، نه یک باگ ساکت:

```cpp
class Broken : public Shape {
public:
    double area() override;          // ❌ error: does not override
    // در پایه: double area() const
};
```

بدون `override`، `Broken::area` یک تابع **جدید** بود و `Shape*` هرگز آن را صدا نمی‌زد. این یکی از رایج‌ترین باگ‌های C++ است؛ `override` را همیشه بنویسید.

`final` هم وجود دارد: `double area() const final;` یعنی هیچ زیرکلاسی نمی‌تواند بازنویسی کند (جلسهٔ ۲۳).

---

## ۴. مخرب virtual

```cpp
class Connection {
public:
    virtual ~Connection() = default;
};

class SecureConnection : public Connection {
public:
    ~SecureConnection() override { /* آزادسازی توکن */ }
};

Connection* p = new SecureConnection();
delete p;      // ✅ هر دو مخرب اجرا می‌شوند
```

اگر مخرب پایه `virtual` نبود:

- `delete p` فقط `~Connection` را صدا می‌زد.
- `~SecureConnection` هرگز اجرا نمی‌شد → نشتی منبع.
- این رفتار **undefined behavior** است، یعنی ممکن است این‌جا کار کند و در build بعدی نشکند.
- کامپایلر با `-Wall` هشدار می‌دهد: `-Wdelete-non-virtual-dtor`.

خروجی واقعی `02_virtual_destructor.cpp`:

```
--- شیء روی پشته ---
  باز کردن اتصال به db.local
  تخصیص توکن امنیتی
اتصال امن (TLS)
  آزاد کردن توکن امنیتی
  بستن اتصال پایه

--- از طریق pointer پایه ---
  باز کردن اتصال به db.remote:5432
  تخصیص توکن امنیتی
  باز کردن اتصال به cache.local
اتصال امن (TLS)
اتصال ساده

--- حذف از طریق Connection* ---
  آزاد کردن توکن امنیتی
  بستن اتصال پایه
  بستن اتصال پایه
```

خط `آزاد کردن توکن امنیتی` قبل از `بستن اتصال پایه` می‌آید: مخرب مشتق اول، مخرب پایه بعد — همان ترتیب معکوس ساخت.

**قاعده:** هر کلاسی که **حداقل یک تابع virtual** دارد، باید مخرب `virtual` داشته باشد. `= default` کافی است و چیزی هزینه نمی‌کند.

---

## ۵. برش شیء (object slicing)

```cpp
void printByValue(Shape shape);       // ❌ با ارزش
void printByReference(const Shape& shape);   // ✅
```

وقتی `Square` را با ارزش به پارامتری از نوع `Shape` می‌دهید، فقط **بخش `Shape`** کپی می‌شود. `side_` جا می‌ماند و شیء جدید یک `Shape` واقعی است، نه `Square`.

خروجی واقعی `03_slicing.cpp`:

```
شیء اصلی: مربع ، area = 25

سه روش پاس دادن:
  by value    → شکل ، area = 0
  by reference → مربع ، area = 25
  by pointer   → مربع ، area = 25

Shape sliced = square → شکل ، area = 0

نتیجه: برای چندریختی، همیشه reference یا pointer پاس بدهید.
```

سه حالت برش:

| کد | نتیجه |
|---|---|
| `void f(Shape s)` | برش |
| `Shape s = square;` | برش |
| `std::vector<Shape> v{square};` | برش |
| `void f(const Shape&)` | ✅ |
| `std::vector<const Shape*>` | ✅ |

`std::vector<Shape>` برای سلسله‌مراتب چندریخت **همیشه** اشتباه است؛ به‌جایش `std::vector<std::unique_ptr<Shape>>` (جلسهٔ ۳۶).

---

## ۶. کدهای این جلسه

| فایل | موضوع |
|---|---|
| `examples/01_virtual_function.cpp` | سه شکل، `virtual`، `override`، `describe()` غیر virtual با رفتار چندریخت |
| `examples/02_virtual_destructor.cpp` | مخرب virtual و آزادسازی درست از طریق `Base*` |
| `examples/03_slicing.cpp` | برش در پارامتر با ارزش، کپی مستقیم و `vector` |

```bash
cd session-22-polymorphism
g++ -std=c++17 -Wall -Wextra -Wpedantic -o shapes examples/01_virtual_function.cpp
./shapes
```

### ۶.۱ `01_virtual_function.cpp`

```
--- از طریق Shape* ---
دایره: 12.5664
مستطیل: 12
مثلث: 15

مجموع مساحت‌ها: 39.5664

--- describe() که area() را صدا می‌زند ---
دایره با مساحت 12.5664
مستطیل با مساحت 12
مثلث با مساحت 15
```

محاسبه‌ها: `π×2² ≈ 12.5664`، `3×4 = 12`، `6×5/2 = 15`. مجموع `39.5664`.

اشاره‌گرها به اشیاء روی پشته اشاره می‌کنند (`&circle`)، پس `delete` لازم نیست. این الگو برای نمونه‌های آموزشی تمیز است؛ در کد واقعی `vector<unique_ptr>` را انتخاب کنید.

### ۶.۲ `02_virtual_destructor.cpp`

سه چیز که باید در خروجی ببینید:

1. در بلوک اول، مخرب‌ها به ترتیب معکوس اجرا شدند (`توکن` قبل از `پایه`).
2. در `delete` از طریق `Connection*`، هر دو مخرب اجرا شدند.
3. `describe()` روی `Connection*` که به `SecureConnection` اشاره می‌کند، نسخهٔ امن را چاپ کرد.

### ۶.۳ `03_slicing.cpp`

`by value` نتیجهٔ `شکل ، area = 0` می‌دهد — همان مقدار پیش‌فرض `Shape::area()`. اگر این خط را در کد واقعی ببینید، یک باگ طراحی دارید، نه یک مشکل کامپایلر.

---

## ۷. اشتباهات رایج

1. **فراموش کردن `virtual` در پایه** → همیشه نسخهٔ پایه اجرا می‌شود.
2. **فراموش کردن `override`** → امضای اشتباه، بی‌صدا تابع جدید می‌سازد.
3. **جا انداختن `const` در امضای override** → با `override` خطا می‌گیرید؛ بدون آن، باگ.
4. **مخرب غیر virtual در کلاس چندریخت** → نشت منبع و UB.
5. **پاس دادن با ارزش** → برش شیء.
6. **`std::vector<Shape>` برای زیرکلاس‌ها** → برش در هر درج.
7. **صدا زدن تابع virtual در سازنده/مخرب** → نسخهٔ کلاس جاری اجرا می‌شود، نه مشتق (در زمان ساخت، شیء هنوز مشتق نشده است).
8. **`virtual` برای همه‌چیز** → کلاس‌هایی که نیازی ندارند هم هزینهٔ vtable می‌دهند و بازنویسی‌پذیر می‌شوند.
9. **حذف شیء مشتق با `delete` روی `Base*` وقتی مخرب virtual نیست** → UB.
10. **انتظار رفتار چندریخت از تابعی که با ارزش برمی‌گرداند** → `Shape get()` برش می‌دهد.

---

## ۸. بهترین شیوه‌ها

- هر کلاس پایه‌ای که برای چندریختی طراحی شده: مخرب `virtual` (حتی `= default`).
- همیشه `override` بنویسید.
- پارامترها را `const&` یا `const*` بگیرید، هرگز با ارزش برای انواع پایه.
- برای مجموعه‌های چندریخت، `std::vector<std::unique_ptr<Base>>`.
- رابط پایه را کوچک نگه دارید؛ هر تابع virtual یک نقطهٔ توسعه است.
- توابع virtual را `private`/`protected` با یک تابع عمومی غیر virtual صدا بزنید (الگوی NVI) وقتی می‌خواهید کنترل قبل/بعد از فراخوانی داشته باشید.
- در سازنده و مخرب از فراخوانی virtualهای بازنویسی‌شدنی پرهیز کنید.
- اگر کلاس پایه هیچ پیاده‌سازی‌ای ندارد، آن را انتزاعی کنید (جلسهٔ ۲۳).

---

## ۹. خلاصه

- `virtual` → انتخاب تابع بر اساس نوع واقعی شیء در زمان اجرا.
- `override` → بررسی امضا توسط کامپایلر؛ همیشه بنویسید.
- مخرب پایه در کلاس چندریخت باید `virtual` باشد.
- برش شیء: کپی با ارزش، بخش پایه را نگه می‌دارد و بقیه را دور می‌ریزد.
- چندریختی با pointer/reference کار می‌کند، نه با ارزش.

---

## ۱۰. تمرین‌ها

### تمرین ۱ — سلسله‌مراتب `Shape` با `scaledArea` (متوسط)

`exercises/exercise_01.cpp`: پایهٔ `Shape` با `area()` و `name()` و یک متد غیر virtual `scaledArea(factor) = area() × factor²`.

```cpp
class Shape {
public:
    virtual ~Shape() = default;
    virtual double area() const;
    virtual std::string name() const;
    double scaledArea(double factor) const;   // از area() استفاده می‌کند
};
```

```
دایره: area = 12.5664 ، doubled = 50.2655
مربع: area = 9 ، doubled = 36

بزرگ‌ترین: دایره
```

نکته: `50.2655 = 12.5664 × 4`؛ ضریب ۲ روی مساحت، توان دوم می‌شود (شبیه‌سازی مقیاس طول). چون `scaledArea` در پایه است ولی `area()` virtual است، هر شکل نتیجهٔ خودش را می‌دهد.

راه‌حل: `exercises/exercise_01_solution.cpp`

---

## ۱۱. قدم بعدی

`Shape` هنوز یک `area()` با مقدار پیش‌فرض `0` دارد؛ اگر کسی فراموش کند بازنویسی کند، برنامه بی‌صدا صفر می‌دهد. جلسهٔ ۲۳ این را با **تابع مجازی خالص** به خطای کامپایل تبدیل می‌کند.
