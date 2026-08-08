# جلسهٔ ۴۷ — مدیریت حافظه و valgrind

## اهداف یادگیری

در پایان این جلسه می‌توانید:

- معنی memory leak، invalid read/write و use-after-free را بفهمید
- با valgrind نشت حافظه را در برنامه پیدا کنید
- خروجی valgrind را تفسیر کنید و باگ حافظه را برطرف کنید
- برنامهٔ «پاک» از نظر valgrind بنویسید

---

## چرا valgrind؟

C حافظه را خودکار مدیریت نمی‌کند — هر `malloc` باید با `free` همراه شود. باگ‌های حافظه (نشت، دسترسی خارج از محدوده) خودشان را فوری نشان نمی‌دهند و بعداً crashهای مرموز می‌سازند. **valgrind** مثل یک بازرس، برنامه را اجرا می‌کند و هر خطای حافظه را گزارش می‌دهد.

## انواع خطاهای حافظه

| خطا | معنی |
|---|---|
| **memory leak** | حافظه تخصیص داده شد ولی `free` نشد |
| **invalid read/write** | دسترسی به حافظه‌ای که مال ما نیست (خارج از آرایه و...) |
| **use-after-free** | استفاده از اشاره‌گر بعد از `free` شدن (dangling pointer) |
| **uninitialized value** | خواندن متغیری که مقدار نگرفته است |

## اجرای valgrind

```bash
gcc -Wall -Wextra -g -o program program.c
valgrind --leak-check=full ./program
```

فلگ `-g` ضروری است تا valgrind بتواند خط و تابع را نشان دهد.

## خواندن خروجی

برای برنامهٔ سالم:

```text
HEAP SUMMARY:
    in use at exit: 0 bytes in 0 blocks
All heap blocks were freed -- no leaks are possible
```

برای برنامهٔ دارای نشت:

```text
LEAK SUMMARY:
    definitely lost: 40 bytes in 1 blocks
```

و بخش «blocks are definitely lost» نشان می‌دهد کدام خط `malloc` کرده و `free` نشده است.

مثال برنامهٔ نشت‌دار:

```c
void leak_demo(void) {
    int *p = (int *)malloc(10 * sizeof(int));
    /* استفاده می‌کنیم اما free را فراموش می‌کنیم! */
}

int main(void) {
    leak_demo();
    return 0;
}
```

valgrind گزارش می‌دهد: ۴۰ بایت (۱۰ × ۴) در تابع `leak_demo` نشت شده است.

## رفع نشت

قانون طلایی: **هر malloc یک free دارد** — حتی در مسیرهای خطا. الگوی امن:

```c
int *p = (int *)malloc(10 * sizeof(int));
if (p == NULL) {
    fprintf(stderr, "خطای تخصیص حافظه\n");
    return 1;
}

/* ... استفاده ... */

free(p);
p = NULL;   /* جلوگیری از dangling pointer */
```

## اشتباهات رایج

1. **فراموش کردن free** — شایع‌ترین نشت؛ مخصوصاً در مسیرهای early return.
2. **free در مسیر خطا** — اگر برنامه با `return` زودتر خارج شود، حافظهٔ تخصیص‌داده‌شده آزاد نمی‌شود.
3. **استفاده بعد از free** (use-after-free) — valgrind آن را با «Invalid read/write» گزارش می‌دهد.
4. **خارج از محدودهٔ آرایه** — مثلاً `arr[n]` وقتی آرایه n عنصر دارد.
5. **double free** — دو بار free کردن یک اشاره‌گر.

## بهترین شیوه‌ها

- هر تابعی که `malloc` می‌کند، یک تابع `free_*` متناظر داشته باشد (مثل `free_table` جلسهٔ ۴۳).
- بعد از `free`، اشاره‌گر را `NULL` کنید.
- مرتب (مثلاً بعد از هر تمرین) برنامه را با valgrind اجرا کنید.
- همیشه `malloc` را بررسی کنید که `NULL` برنگرداند.

## خلاصهٔ جلسه

- valgrind بهترین دوست برنامه‌نویس C است؛ هر خطای حافظه را پیدا می‌کند.
- `--leak-check=full` برای پیدا کردن نشت‌ها.
- هر malloc → یک free؛ و بعد از free، اشاره‌گر را NULL کنید.

## تمرین‌ها

### تمرین ۱ (ساده)
برنامهٔ نمونهٔ `examples/02_leaky.c` را با valgrind اجرا کنید و نشت آن را پیدا و رفع کنید.

### تمرین ۲ (متوسط)
برنامه‌ای بنویسید که آرایه‌ای پویا تخصیص بدهد، آن را پر کند، و در یک مسیر خطای عمدی (`return` زودهنگام) free را فراموش کند؛ سپس با valgrind نشت را نشان دهید و با اصلاح، آن را برطرف کنید.

راه‌حل‌ها در `exercises/` هستند.

## فایل‌های این جلسه

- `examples/01_clean_memory.c` — برنامهٔ سالم از نظر valgrind
- `examples/02_leaky.c` — برنامهٔ عمداً نشت‌دار برای تمرین
- `exercises/exercise_01.c` — رفع نشت برنامهٔ نمونه
- `exercises/exercise_02.c` — تمرین نشت در مسیر خطا
