# جلسه ۷: توابع و Scope متغیرها

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- چگونه توابع را تعریف و استفاده کنید
- چگونه آرگومان‌ها را به توابع بدهید
- چگونه مقادیر را از توابع برگردانید
- مفهوم scope متغیرها (local vs global)
- توابع بازگشتی (recursive functions)
- کتابخانه توابع و source کردن فایل‌ها

## تعریف تابع

توابع در Bash به شما اجازه می‌دهند کد را به بخش‌های قابل استفاده مجدد تقسیم کنید.

### ساختار پایه

```bash
function_name() {
    # کد
}
```

یا

```bash
function function_name {
    # کد
}
```

## مثال ۱: تابع ساده

فایل [examples/01-simple-function.sh](examples/01-simple-function.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تعریف تابع
greet() {
    echo "سلام! خوش آمدید."
}

# استفاده از تابع
echo "فراخوانی تابع greet:"
greet

echo -e "\nفراخوانی مجدد:"
greet
```

### توضیح خط به خط:

- `greet() {` - تعریف تابع به نام greet
- `echo "سلام! خوش آمدید."` - کد داخل تابع
- `}` - پایان تابع
- `greet` - فراخوانی تابع

## توابع با آرگومان

توابع می‌توانند آرگومان بگیرند مثل اسکریپت‌ها:

```bash
my_function() {
    echo "اولین آرگومان: $1"
    echo "دومین آرگومان: $2"
}
```

## مثال ۲: تابع با آرگومان

فایل [examples/02-function-arguments.sh](examples/02-function-arguments.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع با آرگومان
greet_user() {
    NAME=$1
    echo "سلام، $NAME! خوش آمدی."
}

# استفاده از تابع با آرگومان
greet_user "علی"
greet_user "رضا"

# تابع با چند آرگومان
calculate_sum() {
    NUM1=$1
    NUM2=$2
    SUM=$((NUM1 + NUM2))
    echo "جمع $NUM1 و $NUM2 برابر با $SUM است"
}

calculate_sum 10 20
calculate_sum 5 15
```

### توضیح خط به خط:

- `greet_user() {` - تعریف تابع با آرگومان
- `NAME=$1` - اولین آرگومان را در NAME ذخیره می‌کند
- `greet_user "علی"` - تابع را با آرگومان "علی" فراخوانی می‌کند
- `calculate_sum() {` - تابع با دو آرگومان
- `calculate_sum 10 20` - تابع را با دو آرگومان فراخوانی می‌کند

## برگرداندن مقدار از تابع

توابع در Bash می‌توانند مقادیر را با استفاده از `return` یا `echo` برگردانند.

### return

```bash
my_function() {
    return 0
}
```

### echo

```bash
my_function() {
    echo "result"
}
```

## مثال ۳: برگرداندن مقدار

فایل [examples/03-return-values.sh](examples/03-return-values.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع با return
check_number() {
    if [ $1 -gt 10 ]; then
        return 0  # موفق
    else
        return 1  # ناموفق
    fi
}

# استفاده از return
check_number 15
if [ $? -eq 0 ]; then
    echo "عدد بزرگتر از 10 است"
else
    echo "عدد کوچکتر یا مساوی 10 است"
fi

# تابع با echo برای برگرداندن مقدار
get_square() {
    local num=$1
    echo $((num * num))
}

# استفاده از echo
RESULT=$(get_square 5)
echo "مربع 5 برابر با $RESULT است"

RESULT=$(get_square 8)
echo "مربع 8 برابر با $RESULT است"
```

### توضیح خط به خط:

- `return 0` - کد خروجی 0 را برمی‌گرداند (موفق)
- `return 1` - کد خروجی 1 را برمی‌گرداند (ناموفق)
- `$?` - کد خروجی آخرین دستور/تابع
- `local num=$1` - متغیر محلی تعریف می‌کند
- `echo $((num * num))` - نتیجه را چاپ می‌کند (برای گرفتن با $())
- `RESULT=$(get_square 5)` - خروجی تابع را در RESULT ذخیره می‌کند

## Scope متغیرها

در Bash دو نوع scope برای متغیرها وجود دارد:

### Global Scope

متغیرهای سراسری که در همه جای اسکریپت قابل دسترسی هستند.

### Local Scope

متغیرهای محلی که فقط درون تابع قابل دسترسی هستند.

## مثال ۴: Scope متغیرها

فایل [examples/04-variable-scope.sh](examples/04-variable-scope.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# متغیر سراسری
GLOBAL_VAR="من سراسری هستم"

test_scope() {
    # متغیر محلی
    local LOCAL_VAR="من محلی هستم"
    
    echo "درون تابع:"
    echo "  GLOBAL_VAR: $GLOBAL_VAR"
    echo "  LOCAL_VAR: $LOCAL_VAR"
    
    # تغییر متغیر سراسری
    GLOBAL_VAR="من تغییر کردم"
}

echo "قبل از تابع:"
echo "  GLOBAL_VAR: $GLOBAL_VAR"

test_scope

echo "بعد از تابع:"
echo "  GLOBAL_VAR: $GLOBAL_VAR"
echo "  LOCAL_VAR: $LOCAL_VAR"  # این چاپ نمی‌شود
```

### توضیح خط به خط:

- `GLOBAL_VAR="..."` - متغیر سراسری تعریف می‌کند
- `local LOCAL_VAR="..."` - متغیر محلی تعریف می‌کند
- درون تابع، هر دو متغیر قابل دسترسی هستند
- تغییر متغیر سراسری درون تابع، آن را در بیرون هم تغییر می‌دهد
- متغیر محلی فقط درون تابع قابل دسترسی است

## توابع پیشرفته

### آرگومان‌های متغیر

```bash
my_function() {
    for arg in "$@"; do
        echo "$arg"
    done
}
```

### مقادیر پیش‌فرض

```bash
my_function() {
    local name=${1:-"کاربر"}
    echo "سلام، $name"
}
```

## مثال ۵: توابع پیشرفته

فایل [examples/05-advanced-functions.sh](examples/05-advanced-functions.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع با تعداد آرگومان متغیر
print_all() {
    echo "آرگومان‌ها:"
    for arg in "$@"; do
        echo "  - $arg"
    done
}

print_all "علی" "رضا" "محمد" "حسین"

# تابع با مقدار پیش‌فرض
greet_with_default() {
    local name=${1:-"کاربر"}
    echo "سلام، $name!"
}

greet_with_default "علی"
greet_with_default  # از مقدار پیش‌فرض استفاده می‌کند

# تابع برای محاسبه فاکتوریل
factorial() {
    local n=$1
    local result=1
    
    for ((i=1; i<=n; i++)); do
        result=$((result * i))
    done
    
    echo $result
}

echo -e "\nفاکتوریل 5: $(factorial 5)"
echo "فاکتوریل 7: $(factorial 7)"
```

### توضیح خط به خط:

- `for arg in "$@"` - روی تمام آرگومان‌ها حلقه می‌زند
- `${1:-"کاربر"}` - اگر آرگومان داده نشده، "کاربر" استفاده می‌شود
- `local n=$1` - متغیر محلی تعریف می‌کند
- `result=$((result * i))` - ضرب در حلقه
- `$(factorial 5)` - خروجی تابع را می‌گیرد

## توابع بازگشتی (Recursive)

توابع می‌توانند خودشان را فراخوانی کنند.

## مثال ۶: تابع بازگشتی

فایل [examples/06-recursive-function.sh](examples/06-recursive-function.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# فاکتوریل بازگشتی
factorial_recursive() {
    local n=$1
    
    if [ $n -le 1 ]; then
        echo 1
    else
        local prev=$(factorial_recursive $((n - 1)))
        echo $((n * prev))
    fi
}

echo "فاکتوریل بازگشتی 5: $(factorial_recursive 5)"
echo "فاکتوریل بازگشتی 7: $(factorial_recursive 7)"

# فیبوناچی بازگشتی
fibonacci() {
    local n=$1
    
    if [ $n -le 1 ]; then
        echo $n
    else
        local a=$(fibonacci $((n - 1)))
        local b=$(fibonacci $((n - 2)))
        echo $((a + b))
    fi
}

echo -e "\nفیبوناچی 10: $(fibonacci 10)"
```

### توضیح خط به خط:

- `factorial_recursive() {` - تابع بازگشتی
- `if [ $n -le 1 ]` - شرط پایه (base case)
- `echo 1` - مقدار پایه
- `factorial_recursive $((n - 1))` - فراخوانی خود تابع با n-1
- `fibonacci() {` - تابع فیبوناچی بازگشتی
- هر عدد فیبوناچی جمع دو عدد قبلی است

## کتابخانه توابع

می‌توانید توابع را در فایل‌های جداگانه ذخیره کنید و با `source` یا `.` استفاده کنید.

## مثال ۷: کتابخانه توابع

فایل [examples/07-function-library.sh](examples/07-function-library.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# سورس کردن فایل کتابخانه
source examples/mylib.sh
# یا: . examples/mylib.sh

# استفاده از توابع کتابخانه
echo "جمع 5 و 3: $(add 5 3)"
echo "تفریق 10 و 4: $(subtract 10 4)"
echo "ضرب 6 و 7: $(multiply 6 7)"

greet "علی"
```

### توضیح خط به خط:

- `source examples/mylib.sh` - فایل کتابخانه را بارگذاری می‌کند
- `. examples/mylib.sh` - روش کوتاه‌تر برای source
- توابع تعریف شده در کتابخانه قابل استفاده هستند

فایل کتابخانه [examples/mylib.sh](examples/mylib.sh):

```bash
#!/usr/bin/env bash

# توابع ریاضی ساده
add() {
    echo $(($1 + $2))
}

subtract() {
    echo $(($1 - $2))
}

multiply() {
    echo $(($1 * $2))
}

# تابع سلام
greet() {
    echo "سلام، $1!"
}
```

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن local در توابع

❌ مشکل‌دار:
```bash
my_function() {
    VAR="value"  # متغیر سراسری می‌شود
}
```

✅ درست:
```bash
my_function() {
    local VAR="value"  # متغیر محلی می‌شود
}
```

### ۲. استفاده از return برای برگرداندن رشته

❌ اشتباه:
```bash
my_function() {
    return "hello"  # فقط عدد می‌تواند برگردانده شود
}
```

✅ درست:
```bash
my_function() {
    echo "hello"  # برای رشته‌ها از echo استفاده کنید
}
```

### ۳. فراموش کردن تعریف تابع قبل از استفاده

❌ اشتباه:
```bash
my_function  # تابع هنوز تعریف نشده
my_function() {
    echo "hello"
}
```

✅ درست:
```bash
my_function() {
    echo "hello"
}
my_function  # بعد از تعریف
```

### ۴. استفاده از نام‌های تابع رزرو شده

❌ اشتباه:
```bash
cd() {  # cd یک دستور داخلی است
    echo "my cd"
}
```

✅ درست:
```bash
my_cd() {  # از نام منحصر به فرد استفاده کنید
    echo "my cd"
}
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه از local برای متغیرهای داخلی استفاده کنید** - برای جلوگیری از تداخل
2. **توابع را قبل از استفاده تعریف کنید** - یا در فایل جداگانه
3. **از echo برای برگرداندن رشته‌ها استفاده کنید** - return فقط برای اعداد
4. **نام توابع را معنادار انتخاب کنید** - برای خوانایی
5. **توابع را کوتاه و متمرکز نگه دارید** - هر تابع یک کار انجام دهد

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- چگونه توابع را تعریف و استفاده کنیم
- چگونه آرگومان‌ها را به توابع بدهیم
- چگونه مقادیر را از توابع برگردانیم
- مفهوم scope متغیرها (local vs global)
- توابع بازگشتی
- کتابخانه توابع و source کردن
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): تابع ماشین حساب

یک اسکریپت بنویسید که:
1. توابعی برای جمع، تفریق، ضرب و تقسیم داشته باشد
2. از کاربر دو عدد و عملگر بگیرد
3. تابع مناسب را فراخوانی کند
4. نتیجه را چاپ کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): تبدیل واحد

یک اسکریپت بنویسید که:
1. توابعی برای تبدیل واحد داشته باشد:
   - متر به کیلومتر
   - کیلومتر به مایل
   - سانتی‌متر به اینچ
2. از کاربر عدد و نوع تبدیل بگیرد
3. تابع مناسب را فراخوانی کند
4. نتیجه تبدیل شده را چاپ کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، آرایه‌ها (Arrays) و کار با رشته‌ها را یاد می‌گیریم.
