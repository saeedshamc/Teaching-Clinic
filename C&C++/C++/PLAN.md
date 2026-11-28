# طرح درس و فازبندی دورهٔ C++ (۵۰ جلسه)

این فایل، **نقشهٔ اجرایی** دوره است: پرامپت اولیه (`prompt-cpp-course-50-sessions.md`) را به ۹ فاز و ۵۰ جلسهٔ قابل‌اندازه‌گیری شکسته است. برای هر جلسه مشخص است که چه چیزی باید ساخته شود، با چه معیاری پذیرفته می‌شود و کامیت آن چه پیامی دارد.

---

## اصول ثابت در همهٔ جلسات

هر جلسه فقط وقتی «تمام‌شده» حساب می‌شود که **هر پنج مورد** موجود باشد:

| # | مورد | جزئیات |
|---|---|---|
| ۱ | `README.md` فارسی | عنوان و اهداف یادگیری، توضیح مفهومی از صفر، توضیح بخش‌به‌بخش کد کنار همان قطعه، اشتباهات رایج، بهترین شیوه‌ها، خلاصه، ۱ تا ۲ تمرین سطح‌بندی‌شده |
| ۲ | `examples/` | حداقل ۲ فایل `.cpp` واقعی با کامنت فارسی، قابل کامپایل با `g++ -std=c++17 -Wall -Wextra` بدون خطا و هشدار |
| ۳ | `exercises/` | فایل تمرین ناقص (`exercise_0N.cpp`) + راه‌حل (`exercise_0N_solution.cpp`) که خودش هم بدون هشدار کامپایل شود |
| ۴ | تست واقعی | هر فایل کامپایل و اجرا شود؛ خروجی برنامه با آنچه در README نوشته شده مطابقت داشته باشد |
| ۵ | کامیت جدا | `git commit -m "session NN: <عنوان فارسی>"` — فقط فایل‌های همان جلسه |

**دستور تست استاندارد هر جلسه:**

```bash
cd session-NN-...
for f in examples/*.cpp exercises/*.cpp; do
  g++ -std=c++17 -Wall -Wextra -Wpedantic -o /tmp/t "$f" && /tmp/t >/dev/null || echo "FAIL: $f"
done
```

**قواعد سبک کد:** سبک مدرن C++ (`auto`، smart pointer، range-based for، `std::` صریح)، نام‌گذاری camelCase برای متغیر و PascalCase برای کلاس، بدون `using namespace std;` در سطح فایل (توضیح دلیلش در جلسهٔ ۴۰).

---

## فاز ۰ — راه‌اندازی پروژه (بدون جلسه)

| کار | خروجی |
|---|---|
| ساخت اسکلت پوشه‌ها و `.gitignore` مخصوص C++ | `.gitignore` |
| نوشتن نمای کلی دوره و فازبندی | `README.md`، همین `PLAN.md` |
| ثبت کامیت اولیه | `chore(cpp): scaffold 50-session C++ course` |

**معیار پذیرش فاز:** پوشهٔ دوره با `README.md` و `.gitignore` آماده است و `g++ --version` نسخهٔ ۱۷ یا بالاتر را نشان می‌دهد.

---

## فاز ۱ — مبانی زبان (جلسات ۱–۶)

**هدف فاز:** مخاطبی که هیچ پیش‌زمینه‌ای ندارد بتواند برنامهٔ C++ بنویسد، کامپایل کند، ورودی بگیرد و با شرط و حلقه منطق بنویسد.
**پیش‌نیاز:** فاز ۰.
**خروجی فاز:** یک برنامهٔ کنسولی که ورودی می‌گیرد، محاسبه می‌کند و با حلقه و شرط خروجی می‌دهد.

| # | جلسه (پوشه) | مفاهیم کلیدی | فایل‌های نمونه و تمرین | معیار پذیرش |
|---|---|---|---|---|
| ۱ | `session-01-intro` | C++ چیست، تفاوت با C، کامپایلر، `main`، `#include`، `std::cout`، `<<` و `\n`، مراحل کامپایل | `01_hello.cpp`، `02_comments_and_include.cpp` + تمرین چاپ چندخطی | خروجی `Hello, C++!` و کامپایل بدون هشدار |
| ۲ | `session-02-variables-types-namespace` | انواع پایه، اندازه و بازه، `const`، `constexpr`، `auto`، `namespace` و `std`، casting اولیه | `01_variables.cpp`، `02_types_sizes.cpp`، `03_namespace_auto.cpp` + تمرین محاسبهٔ سن | چاپ `sizeof` انواع و محاسبهٔ درست |
| ۳ | `session-03-io-streams` | `cin`/`cout`/`cerr`، `getline`، قالب‌بندی با `setw`/`fixed`/`setprecision`، خطای ورودی | `01_cin_cout.cpp`، `02_getline.cpp`، `03_formatting.cpp` + تمرین دریافت نام کامل | برنامه‌ای که نام کامل با فاصله را درست می‌خواند |
| ۴ | `session-04-operators` | حسابی، مقایسه، منطقی، `++`/`--`، اولویت عملگرها، تقسیم صحیح، سرریز | `01_arithmetic.cpp`، `02_precedence.cpp`، `03_division_and_overflow.cpp` + تمرین ماشین‌حساب ساده | محاسبهٔ درصد و معدل درست |
| ۵ | `session-05-conditionals` | `if`/`else if`/`else`، عملگر سه‌گانه، `switch` و `break`، `case` با `enum`، خطای `==` در برابر `=` | `01_if_else.cpp`، `02_switch.cpp`، `03_ternary.cpp` + تمرین رتبه‌بندی نمره | رتبه‌بندی درست با شرط‌های تودرتو |
| ۶ | `session-06-loops` | `for`، `while`، `do-while`، range-based for، `break`/`continue`، حلقهٔ تودرتو، جدول ضرب | `01_for.cpp`، `02_while_do.cpp`، `03_nested.cpp` + تمرین عدد اول و معکوس عدد | چاپ جدول ضرب و تشخیص عدد اول |

**ارزیابی پایان فاز ۱:** نوشتن برنامه‌ای که n عدد از کاربر بگیرد و میانگین، بیشترین و کمترین را چاپ کند (بدون استفاده از آرایه).

---

## فاز ۲ — توابع، مرجع و داده‌ها (جلسات ۷–۱۱)

**هدف فاز:** شکستن برنامه به توابع تمیز، درک تفاوت reference و pointer و ورود به `std::vector` و `std::string` و حافظهٔ پویا.
**پیش‌نیاز:** فاز ۱.
**خروجی فاز:** یک برنامهٔ چندتابعی که داده‌ها را در `vector` نگه می‌دارد و با reference ویرایش می‌کند.

| # | جلسه (پوشه) | مفاهیم کلیدی | فایل‌های نمونه و تمرین | معیار پذیرش |
|---|---|---|---|---|
| ۷ | `session-07-functions-overloading` | اعلان و تعریف تابع، `void`، مقدار بازگشتی، پارامتر پیش‌فرض، overloading، تفاوت با C، دامنهٔ متغیر | `01_functions.cpp`، `02_default_args.cpp`، `03_overloading.cpp` + تمرین تابع توان و max سه‌تایی | فراخوانی نسخهٔ درست overload و مقدار پیش‌فرض |
| ۸ | `session-08-references-pointers` | `&` در اعلان، reference به‌عنوان پارامتر، `const&`، `nullptr`، `*` و `&`، مقایسهٔ جدولی، `std::reference_wrapper` (اشاره) | `01_reference.cpp`، `02_pointer.cpp`، `03_swap_by_ref.cpp` + تمرین افزایش مقدار با دو روش | `swap` واقعی بدون copy و بدون خطا |
| ۹ | `session-09-arrays-vector` | آرایهٔ خام و محدودیت‌ها، `std::vector`، `push_back`، `size`، `at` در برابر `[]`، range-based for، `std::array` | `01_c_array.cpp`، `02_vector_basics.cpp`، `03_vector_of_scores.cpp` + تمرین آمار نمرات | خواندن `at` خارج از محدوده استثنا بدهد |
| ۱۰ | `session-10-std-string` | `std::string`، اتصال، `length`، `substr`، `find`، `replace`، مقایسه، `getline`، تبدیل عدد و رشته (`std::stoi`/`to_string`) | `01_string_basics.cpp`، `02_string_search.cpp`، `03_number_string.cpp` + تمرین شمارش کلمات | شمارش درست کلمات یک جمله |
| ۱۱ | `session-11-pointers-dynamic-memory` | `new`/`delete`، `new[]`/`delete[]`، نشتی حافظه، dangling pointer، double free، جایگزین مدرن با `vector` | `01_new_delete.cpp`، `02_dynamic_array.cpp`، `03_memory_pitfalls.cpp` + تمرین آرایهٔ پویا و آزادسازی | اجرا بدون خطا و توضیح دقیق خطرهای `new` خام |

**ارزیابی پایان فاز ۲:** برنامهٔ مدیریت فهرست کارها با `vector<string>`، توابع جدا برای افزودن/حذف/نمایش، خواندن ورودی با `getline`.

---

## فاز ۳ — شیءگرایی (جلسات ۱۲–۲۳)

**هدف فاز:** تسلط کامل بر OOP: از `struct` تا کلاس انتزاعی و چندریختی.
**پیش‌نیاز:** فاز ۲ (به‌خصوص reference و pointer).
**خروجی فاز:** یک سلسله‌مراتب کلاس واقعی (مثلاً شکل‌های هندسی یا کارمندان) با وراثت، `virtual` و تخریب درست.

| # | جلسه (پوشه) | مفاهیم کلیدی | فایل‌های نمونه و تمرین | معیار پذیرش |
|---|---|---|---|---|
| ۱۲ | `session-12-struct-vs-class` | `struct` در C++، تفاوت پیش‌فرض دسترسی، کلاس ساده، تعریف شیء، مقداردهی | `01_struct.cpp`، `02_class_basic.cpp`، `03_struct_vs_class.cpp` + تمرین struct نقطه | دو نسخه با تفاوت دسترسی روشن |
| ۱۳ | `session-13-oop-encapsulation` | شیء و کلاس، کپسوله‌سازی، اینترفیس عمومی، پنهان‌سازی پیاده‌سازی، مزایای طراحی | `01_bank_account.cpp`، `02_invariants.cpp` + تمرین کلاس `Counter` | عدم امکان تغییر نامعتبر از بیرون |
| ۱۴ | `session-14-constructors-destructors` | سازندهٔ پیش‌فرض/پارامتری، لیست مقداردهی اولیه، copy constructor، مخرب، ترتیب ساخت و تخریب | `01_constructors.cpp`، `02_initializer_list.cpp`، `03_ctor_dtor_order.cpp` + تمرین کلاس `Timer` | پیام ساخت/تخریب به ترتیب درست |
| ۱۵ | `session-15-access-control` | `public`/`private`/`protected`، getter/setter، اعتبارسنجی در setter، `friend` (پیش‌درآمد) | `01_getters_setters.cpp`، `02_validation.cpp` + تمرین کلاس `Student` | setter مقدار نامعتبر را رد کند |
| ۱۶ | `session-16-static-members` | `static` داده، `static` تابع، شمارندهٔ نمونه‌ها، `static const` و `inline static` | `01_static_counter.cpp`، `02_static_methods.cpp` + تمرین تولید شناسهٔ یکتا | شمارش دقیق نمونه‌های زنده |
| ۱۷ | `session-17-this-pointer` | `this` چیست، بازگشت `*this`، method chaining، رفع ابهام نام پارامتر، `this` در `const` متد | `01_this_basic.cpp`، `02_method_chaining.cpp` + تمرین کلاس `StringBuilder` | زنجیرهٔ `.append().append()` کار کند |
| ۱۸ | `session-18-operator-overloading` | عملگرهای `+`، `==`، `<`، `<<`، `+=`، `[]`، `++`، تفاوت member و free function | `01_complex_number.cpp`، `02_ostream_operator.cpp`، `03_index_operator.cpp` + تمرین کلاس `Fraction` | چاپ مستقیم شیء با `cout << obj` |
| ۱۹ | `session-19-friend` | تابع friend، کلاس friend، friend و اپراتور `<<`، هزینه‌های طراحی و جایگزین‌ها | `01_friend_function.cpp`، `02_friend_class.cpp` + تمرین عملگر `*` به‌صورت friend | دسترسی کنترل‌شده بدون شکستن کپسوله‌سازی |
| ۲۰ | `session-20-inheritance-basics` | `class Derived : public Base`، ارث‌بری اعضا، سازندهٔ پایه، انواع وراثت، `protected` | `01_animal_dog.cpp`، `02_base_constructor.cpp` + تمرین `Vehicle` و `Car` | فراخوانی درست سازندهٔ پایه |
| ۲۱ | `session-21-multiple-inheritance` | وراثت چندگانه، مشکل الماس، `virtual` inheritance، کاربردهای واقعی و خطرها | `01_multiple_inheritance.cpp`، `02_diamond_problem.cpp` + تمرین رابط‌های `Printable`/`Serializable` | حل الماس با وراثت مجازی |
| ۲۲ | `session-22-polymorphism` | تابع `virtual`، `override`، late binding، `virtual` destructor، برش شیء (object slicing) | `01_virtual_function.cpp`، `02_virtual_destructor.cpp`، `03_slicing.cpp` + تمرین `Shape` و `area()` | فراخوانی درست از طریق pointer پایه |
| ۲۳ | `session-23-abstract-classes` | pure virtual، کلاس انتزاعی، اینترفیس در C++، طراحی قرارداد، `final` | `01_abstract_shape.cpp`، `02_interface.cpp` + تمرین اینترفیس `IPayment` با دو پیاده‌سازی | خطای کامپایل در ساخت شیء انتزاعی |

**ارزیابی پایان فاز ۳:** برنامهٔ «کارمندان» با کلاس پایهٔ انتزاعی `Employee`، دو زیرکلاس با محاسبهٔ حقوق متفاوت و چاپ چندریخت از یک `vector<unique_ptr<Employee>>`.

---

## فاز ۴ — قالب‌ها و استثناها (جلسات ۲۴–۲۶)

**هدف فاز:** نوشتن کد جنریک و مقاوم در برابر خطا.
**پیش‌نیاز:** فاز ۳.
**خروجی فاز:** یک کلاس جنریک + یک تابع که با استثنا خطا را گزارش می‌کند.

| # | جلسه (پوشه) | مفاهیم کلیدی | فایل‌های نمونه و تمرین | معیار پذیرش |
|---|---|---|---|---|
| ۲۴ | `session-24-function-templates` | `template <typename T>`، استنتاج نوع، specialization، محدودیت‌ها، جایگزین `auto` | `01_max_template.cpp`، `02_specialization.cpp` + تمرین `template swap` | کار با `int`، `double` و `string` |
| ۲۵ | `session-25-class-templates` | قالب کلاس، `std::vector` کوچک خودساخته، members، alias template، مفاهیم `concepts` (اشاره) | `01_box_template.cpp`، `02_stack_template.cpp` + تمرین کلاس `Pair<A,B>` | پشتهٔ جنریک بدون نشتی |
| ۲۶ | `session-26-exceptions` | `throw`/`try`/`catch`، انواع استثنا، `std::exception` و `what()`، استثنا در سازنده، امنیت استثنا، `noexcept` | `01_basic_try_catch.cpp`، `02_custom_exception.cpp`، `03_ctor_exception.cpp` + تمرین اعتبارسنجی سن | گرفتن استثنای سفارشی و پیام درست |

**ارزیابی پایان فاز ۴:** تابع `divide` که با استثنای سفارشی تقسیم بر صفر راگزارش کند و برنامه‌ای که آن را مدیریت کند.

---

## فاز ۵ — کتابخانهٔ استاندارد و الگوریتم‌ها (جلسات ۲۷–۳۵)

**هدف فاز:** انتخاب و استفادهٔ حرفه‌ای از کانتینرها، تکرارگرها، الگوریتم‌ها و لامبدا.
**پیش‌نیاز:** فاز ۴.
**خروجی فاز:** برنامه‌ای که داده را در کانتینر مناسب نگه می‌دارد و با `sort`/`find_if`/لامبدا پردازش می‌کند.

| # | جلسه (پوشه) | مفاهیم کلیدی | فایل‌های نمونه و تمرین | معیار پذیرش |
|---|---|---|---|---|
| ۲۷ | `session-27-stl-overview` | معماری STL (کانتینر/تکرارگر/الگوریتم)، خانوادهٔ کانتینرها، پیچیدگی زمانی، جدول انتخاب کانتینر | `01_container_overview.cpp`، `02_complexity.cpp` + تمرین انتخاب کانتینر برای سناریوها | جدول انتخاب کانتینر در README |
| ۲۸ | `session-28-stl-vector` | ساخت، `reserve`، `capacity` در برابر `size`، حذف با `erase`، `shrink_to_fit`، `emplace_back`، پایداری تکرارگر | `01_vector_grow.cpp`، `02_erase_insert.cpp`، `03_emplace.cpp` + تمرین حذف اعداد زوج | رفتار `capacity` هنگام `reserve` روشن |
| ۲۹ | `session-29-stl-list-deque` | `list` دوطرفه، `splice`، هزینهٔ پیمایش، `deque` و تفاوت با `vector`، `forward_list` | `01_list_basics.cpp`، `02_list_splice.cpp`، `03_deque.cpp` + تمرین صف دوطرفه | درج در ابتدا با هزینهٔ ثابت |
| ۳۰ | `session-30-stl-map` | `map` درختی، `unordered_map` هش، `operator[]` در برابر `at`، `insert`/`emplace`، پیمایش، هش سفارشی | `01_map_basics.cpp`، `02_word_count.cpp`، `03_unordered_map.cpp` + تمرین شمارش تکرار کلمات | شمارش کلمات با `unordered_map` |
| ۳۱ | `session-31-stl-set` | `set`، `unordered_set`، یکتایی، `insert` نتیجه‌دار، عملگرهای مجموعه (اشتراک/تفاضل با `set_intersection`) | `01_set_basics.cpp`، `02_set_operations.cpp` + تمرین حذف تکراری‌ها | حذف تکراری با `set` |
| ۳۲ | `session-32-stl-adaptors` | `stack`، `queue`، `priority_queue`، کانتینر زیرین، `top`/`pop`، کاربرد در الگوریتم | `01_stack.cpp`، `02_queue.cpp`، `03_priority_queue.cpp` + تمرین بررسی پرانتزها | بررسی درست توازن پرانتزها |
| ۳۳ | `session-33-iterators` | `begin`/`end`، `const_iterator`، انواع تکرارگر، `advance`/`next`، نامعتبر شدن، `auto` | `01_iterator_basics.cpp`، `02_iterator_categories.cpp`، `03_invalidation.cpp` + تمرین پیمایش معکوس | پیمایش معکوس با `rbegin` |
| ۳۴ | `session-34-stl-algorithms` | `sort`، `find`/`find_if`، `count`، `accumulate`، `transform`، `remove_if` + erase idiom، `all_of` | `01_sort_find.cpp`، `02_transform_accumulate.cpp`، `03_erase_remove_if.cpp` + تمرین مرتب‌سازی دانشجوها | مرتب‌سازی با کامپارator سفارشی |
| ۳۵ | `session-35-lambdas` | syntax لامبدا، capture by value/reference، `mutable`، لامبدای جنریک، ذخیره در `std::function` | `01_lambda_basics.cpp`، `02_capture.cpp`، `03_sort_with_lambda.cpp` + تمرین فیلتر با لامبدا | لامبدای capture درست بدون dangling |

**ارزیابی پایان فاز ۵:** برنامهٔ دفترچهٔ تلفن با `map<string, vector<string>>`، جستجوی جزئی با لامبدا و مرتب‌سازی نتایج.

---

## فاز ۶ — حافظهٔ مدرن و مدیریت منابع (جلسات ۳۶–۳۹)

**هدف فاز:** حذف `new`/`delete` خام از کد روزمره و درک RAII و move semantics.
**پیش‌نیاز:** فاز ۵.
**خروجی فاز:** کدی که مالکیت منابعش صریح است و با smart pointer و `fstream` مدیریت می‌شود.

| # | جلسه (پوشه) | مفاهیم کلیدی | فایل‌های نمونه و تمرین | معیار پذیرش |
|---|---|---|---|---|
| ۳۶ | `session-36-smart-pointers` | `unique_ptr` و انتقال مالکیت، `make_unique`، `shared_ptr` و `use_count`، `weak_ptr` و شکستن چرخه | `01_unique_ptr.cpp`، `02_shared_ptr.cpp`، `03_weak_ptr_cycle.cpp` + تمرین تبدیل کد `new` به smart pointer | نبود نشتی و شمارش درست مالکیت |
| ۳۷ | `session-37-move-semantics` | lvalue/rvalue، `std::move`، move constructor و move assignment، `std::forward`، قانون پنج | `01_move_basics.cpp`، `02_move_ctor.cpp`، `03_rule_of_five.cpp` + تمرین کلاس بافر با move | کپی گرفته نشود و move انجام شود |
| ۳۸ | `session-38-raii` | اصل RAII، مدیریت منابع در سازنده/مخرب، کلاس نگهبان فایل، `lock_guard` به‌عنوان نمونه، قانون صفر | `01_raii_wrapper.cpp`، `02_raii_file_guard.cpp` + تمرین کلاس اندازه‌گیری زمان | آزادسازی حتی هنگام بروز استثنا |
| ۳۹ | `session-39-fstream` | `ifstream`/`ofstream`/`fstream`، حالت‌های باز کردن، خواندن خط‌به‌خط و کلمه‌به‌کلمه، نوشتن، بررسی خطا، فایل باینری | `01_write_file.cpp`، `02_read_lines.cpp`، `03_binary_file.cpp` + تمرین ذخیره و بازیابی رکوردها | خواندن درست فایل نوشته‌شده |

**ارزیابی پایان فاز ۶:** ذخیره و بازخوانی یک `vector` از اشیاء در فایل متنی و باینری با مدیریت RAII.

---

## فاز ۷ — C++ حرفه‌ای (جلسات ۴۰–۴۴)

**هدف فاز:** نوشتن کد در مقیاس پروژه: namespace، const-correctness، cast صحیح، فایل‌های هدر و CMake.
**پیش‌نیاز:** فاز ۶.
**خروجی فاز:** یک پروژهٔ چندفایلی که با CMake ساخته می‌شود و از هدر guard استفاده می‌کند.

| # | جلسه (پوشه) | مفاهیم کلیدی | فایل‌های نمونه و تمرین | معیار پذیرش |
|---|---|---|---|---|
| ۴۰ | `session-40-namespaces-advanced` | namespace تودرتو، anonymous namespace، `inline namespace`، `using` محدود، چرا `using namespace std;` در هدر ممنوع است، ADL | `01_nested_namespace.cpp`، `02_anonymous_namespace.cpp` + تمرین جداسازی دو ماژول هم‌نام | نبود تداخل نام بین ماژول‌ها |
| ۴۱ | `session-41-const-correctness` | `const` متغیر/پارامتر/متد/بازگشتی، `mutable`، `const_cast` ممنوعیت‌ها، `constexpr` | `01_const_methods.cpp`، `02_const_ref_params.cpp` + تمرین کلاس فقط‌خواندنی | متد `const` روی شیء `const` فراخوانی شود |
| ۴۲ | `session-42-casts` | `static_cast`، `dynamic_cast` و RTTI، `const_cast`، `reinterpret_cast`، C-style cast چرا بد است | `01_static_cast.cpp`، `02_dynamic_cast.cpp`، `03_reinterpret_cast.cpp` + تمرین تشخیص نوع در سلسله‌مراتب | `dynamic_cast` ناموفق `nullptr` بدهد |
| ۴۳ | `session-43-multifile-build` | فایل هدر و منبع، `#pragma once` و include guard، اعلان در برابر تعریف، linkage، دستور کامپایل جداگانه | پروژهٔ `library/` با `book.h`، `book.cpp`، `main.cpp` + تمرین افزودن `member` | کامپایل جداگانه و لینک بدون خطای multiple definition |
| ۴۴ | `session-44-cmake` | `CMakeLists.txt`، `cmake_minimum_required`، `add_executable`، `target_include_directories`، build type، ساخت در پوشهٔ `build/` | پروژهٔ CMake کامل + تمرین افزودن کتابخانهٔ کوچک با `add_library` | `cmake -S . -B build && cmake --build build` موفق |

**ارزیابی پایان فاز ۷:** پروژهٔ چندفایلی «کتابخانه» با CMake، namespace اختصاصی و هدرهای محافظت‌شده.

---

## فاز ۸ — ابزار، همزمانی و الگوهای طراحی (جلسات ۴۵–۴۹)

**هدف فاز:** ابزارهای عملی دیباگ و همزمانی و آشنایی با الگوهای طراحی و استانداردهای مدرن.
**پیش‌نیاز:** فاز ۷.
**خروجی فاز:** یک برنامهٔ چندنخی امن و یک برنامه که از ویژگی‌های C++17/20 استفاده می‌کند.

| # | جلسه (پوشه) | مفاهیم کلیدی | فایل‌های نمونه و تمرین | معیار پذیرش |
|---|---|---|---|---|
| ۴۵ | `session-45-gdb` | کامپایل با `-g`، breakpoint، `run`/`next`/`step`، `print`، `bt`، watch، دیباگ خطای segmentation | برنامهٔ باگ‌دار برای تمرین دیباگ + `README` با سناریوی گام‌به‌گام | پیدا کردن علت خطا با `bt` و `print` |
| ۴۶ | `session-46-threads` | `std::thread`، `join`/`detach`، `mutex` و `lock_guard`، data race، `atomic`، `async`/`future` | `01_thread_basics.cpp`، `02_mutex_counter.cpp`، `03_async_future.cpp` + تمرین شمارندهٔ امن | شمارش درست با و بدون mutex (تفاوت دیده شود) |
| ۴۷ | `session-47-design-patterns` | Singleton (و خطرهایش)، Factory Method، Strategy با `std::function`، Dependency Injection ساده | `01_singleton.cpp`، `02_factory.cpp`، `03_strategy.cpp` + تمرین Factory برای شکل‌ها | ساخت شیء از طریق factory بدون `new` خام |
| ۴۸ | `session-48-modern-cpp` | `auto`، structured bindings، `if constexpr`، `std::string_view`، `override`/`final`، `constexpr`، `concepts` (اشاره)، C++20 ranges | `01_structured_bindings.cpp`، `02_string_view.cpp`، `03_constexpr.cpp` + تمرین بازنویسی کد قدیمی | بازنویسی کد با سبک مدرن و بدون هشدار |
| ۴۹ | `session-49-std-utilities` | `std::chrono` (اندازه‌گیری زمان)، `std::optional`، `std::variant` با `std::visit`، `std::any`، `std::filesystem` (اشاره) | `01_chrono.cpp`، `02_optional.cpp`، `03_variant.cpp` + تمرین تابع تقسیم با optional | بازگشت `nullopt` و مدیریت درست آن |

**ارزیابی پایان فاز ۸:** برنامهٔ پردازش موازی اعداد با `std::async` و اندازه‌گیری زمان با `chrono`.

---

## فاز ۹ — پروژهٔ نهایی (جلسهٔ ۵۰)

**هدف فاز:** ترکیب همهٔ مفاهیم در یک برنامهٔ واقعی و قابل ارائه.
**پیش‌نیاز:** فازهای ۱ تا ۸.

| # | جلسه (پوشه) | مفاهیم | خروجی | معیار پذیرش |
|---|---|---|---|---|
| ۵۰ | `session-50-final-project` | طراحی چندلایه، کلاس‌های مدل، مخزن داده (repository)، ذخیره در فایل، منوی کنسولی، جستجو و مرتب‌سازی | پروژهٔ «سیستم مدیریت کتابخانه» با `CMakeLists.txt`، `include/`، `src/`، README طراحی و راهنما | ساخت با CMake، افزودن/جستجو/حذف/ذخیره/بازیابی کتاب، بدون هشدار کامپایل |

**معیار نهایی دوره:** پروژهٔ جلسهٔ ۵۰ اجرا شود، داده را در فایل ذخیره و پس از اجرای مجدد بازیابی کند، و کد هیچ `new`/`delete` خام نداشته باشد.

---

## پیام کامیت هر جلسه

الگوی ثابت: `session NN: <عنوان فارسی جلسه>`

```
session 01: مقدمه‌ای بر C++ و راه‌اندازی محیط
session 02: متغیرها، انواع داده و namespace
session 03: جریان‌های ورودی و خروجی
session 04: عملگرها و عبارات
session 05: شرط‌ها (if/else/switch)
session 06: حلقه‌ها (for/while/do-while)
session 07: توابع، پارامتر پیش‌فرض و overloading
session 08: مرجع در برابر اشاره‌گر
session 09: آرایه‌ها و std::vector
session 10: رشته‌ها با std::string
session 11: اشاره‌گرها و حافظهٔ پویا
session 12: تفاوت struct و class
session 13: مقدمهٔ OOP و کپسوله‌سازی
session 14: سازنده‌ها و مخرب‌ها
session 15: سطوح دسترسی و getter/setter
session 16: اعضای static در کلاس
session 17: اشاره‌گر this
session 18: سربارگذاری عملگر
session 19: توابع و کلاس‌های friend
session 20: مبانی وراثت
session 21: وراثت چندگانه
session 22: چندریختی و توابع virtual
session 23: کلاس‌های انتزاعی و pure virtual
session 24: قالب‌های تابع
session 25: قالب‌های کلاس
session 26: مدیریت استثنا
session 27: نمای کلی STL
session 28: بررسی عمیق vector
session 29: کانتینرهای list و deque
session 30: map و unordered_map
session 31: set و unordered_set
session 32: stack، queue و priority_queue
session 33: تکرارگرها
session 34: الگوریتم‌های STL
session 35: عبارات لامبدا
session 36: اشاره‌گرهای هوشمند
session 37: Move Semantics و rvalue reference
session 38: اصل RAII و مدیریت منابع
session 39: کار با فایل با fstream
session 40: namespace به‌صورت عمیق‌تر
session 41: const-correctness
session 42: تبدیل نوع (چهار cast)
session 43: پروژهٔ چندفایلی و header guard
session 44: مبانی CMake
session 45: دیباگ با gdb
session 46: برنامه‌نویسی چندنخی
session 47: الگوهای طراحی (Singleton، Factory)
session 48: ویژگی‌های مدرن C++
session 49: chrono، optional و variant
session 50: پروژهٔ نهایی — سیستم مدیریت کتابخانه
```

---

## ترتیب اجرا (چک‌لیست کار)

1. فاز ۰: اسکلت پروژه، `.gitignore`، `README.md`، `PLAN.md` + کامیت اولیه ✅
2. فاز ۱: جلسات ۱ تا ۶ — هر جلسه ساخت، کامپایل، تست و کامیت
3. فاز ۲: جلسات ۷ تا ۱۱
4. فاز ۳: جلسات ۱۲ تا ۲۳
5. فاز ۴: جلسات ۲۴ تا ۲۶
6. فاز ۵: جلسات ۲۷ تا ۳۵
7. فاز ۶: جلسات ۳۶ تا ۳۹
8. فاز ۷: جلسات ۴۰ تا ۴۴
9. فاز ۸: جلسات ۴۵ تا ۴۹
10. فاز ۹: جلسهٔ ۵۰
11. بازبینی نهایی: ساختار پوشه‌ها و `git log --oneline`
