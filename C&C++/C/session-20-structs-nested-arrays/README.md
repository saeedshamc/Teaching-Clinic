# جلسهٔ ۲۰ — struct تودرتو و آرایه‌ای از struct

## اهداف یادگیری

در پایان این جلسه می‌توانید:

- struct داخل struct بگذارید (nested struct)
- آرایه‌ای از struct بسازید و پیمایش کنید
- struct را با اشاره‌گر پاس دهید و از عملگر `->` استفاده کنید
- struct را در حافظهٔ پویا بسازید

---

## struct تودرتو

یک struct می‌تواند عضو struct دیگری باشد:

```c
struct Date { int day, month, year; };

struct Student {
    char name[50];
    struct Date birth;    // struct داخل struct
};
```

دسترسی: `s.birth.year`.

## آرایه‌ای از struct

```c
struct Student students[3];
students[0].age = 20;
```

یا مقداردهی اولیه:

```c
struct Student students[2] = {
    {"Ali", 20},
    {"Sara", 22}
};
```

## اشاره‌گر به struct و عملگر ->

اگر اشاره‌گر به struct دارید، برای دسترسی به عضو از `->` استفاده کنید:

```c
struct Student *p = &students[0];
p->age = 21;           // معادل (*p).age
```

چون `(*p).age` زشت است، `p->age` را به کار می‌بریم.

## struct پویا

```c
struct Student *p = malloc(sizeof(struct Student));
if (p != NULL) {
    strcpy(p->name, "Ali");
    p->age = 20;
    free(p);
}
```

نکته: اندازهٔ struct را با `sizeof(struct Student)` بگیرید (نه مجموع دستی اعضا — به دلیل padding ممکن است بزرگ‌تر باشد).

## اشتباهات رایج

1. **فراموش کردن `.` یا `->` در دسترسی به عضو تودرتو.**
2. **استفاده از `.` با اشاره‌گر و `->` با متغیر** — به هم برعکس.
3. **`sizeof` را اشتباه محاسبه کردن** (padding را فراموش کردن).
4. **free نکردن struct پویا** → نشت حافظه.

## بهترین شیوه‌ها

- وقتی struct به تابع می‌رود و قرار است تغییر کند، با اشاره‌گر (`->`) پاس دهید.
- برای فقط خواندن، `const struct Student *s` بگیرید.
- در آرایهٔ struct، پیمایش مثل آرایهٔ معمولی است.

## خلاصهٔ جلسه

- struct تودرتو: `s.birth.year`.
- آرایهٔ struct: `students[i].member`.
- اشاره‌گر به struct: `p->member`.
- struct پویا با malloc/free.

## تمرین‌ها

### تمرین ۱ (ساده)
struct Address (خیابان، شهر، کدپستی) داخل struct Person بگذارید و نمونه‌ای چاپ کنید.

### تمرین ۲ (متوسط)
آرایه‌ای از ۳ دانشجو با struct بسازید، با حلقه چاپ کنید و میانگین معدل‌ها را حساب کنید.

راه‌حل‌ها در `exercises/` هستند.

## فایل‌های این جلسه

- `examples/01_nested_struct.c` — struct تودرتو
- `examples/02_array_of_structs.c` — آرایهٔ struct و ->
- `exercises/exercise_01.c` — آدرس داخل شخص
- `exercises/exercise_02.c` — میانگین معدل‌ها
