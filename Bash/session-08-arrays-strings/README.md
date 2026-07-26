# جلسه ۸: آرایه‌ها و کار با رشته‌ها

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- چگونه آرایه‌ها را تعریف و استفاده کنید
- دسترسی به عناصر آرایه
- عملیات روی آرایه‌ها
- کار با رشته‌ها در Bash
- عملیات رشته‌ای (substring, length, replace)
- تبدیل بین رشته و آرایه

## آرایه‌ها در Bash

آرایه‌ها در Bash برای ذخیره چندین مقدار در یک متغیر استفاده می‌شوند.

### تعریف آرایه

```bash
# روش ۱: تعریف مستقیم
my_array=(item1 item2 item3)

# روش ۲: تعریف ایندکس‌دار
my_array[0]="item1"
my_array[1]="item2"
my_array[2]="item3"
```

## مثال ۱: آرایه ساده

فایل [examples/01-simple-array.sh](examples/01-simple-array.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تعریف آرایه
FRUITS=("سیب" "پرتقال" "موز" "انگور" "هلو")

echo "آرایه میوه‌ها:"
echo "${FRUITS[@]}"

# دسترسی به عناصر
echo -e "\nاولین میوه: ${FRUITS[0]}"
echo "دومین میوه: ${FRUITS[1]}"
echo "سومین میوه: ${FRUITS[2]}"

# تعداد عناصر
echo -e "\nتعداد میوه‌ها: ${#FRUITS[@]}"

# دسترسی به آخرین عنصر
echo "آخرین میوه: ${FRUITS[-1]}"
```

### توضیح خط به خط:

- `FRUITS=("سیب" "پرتقال" ...)` - آرایه با 5 عنصر تعریف می‌کند
- `${FRUITS[@]}` - تمام عناصر آرایه را برمی‌گرداند
- `${FRUITS[0]}` - اولین عنصر (ایندکس از 0 شروع می‌شود)
- `${FRUITS[1]}` - دومین عنصر
- `${#FRUITS[@]}` - تعداد عناصر آرایه
- `${FRUITS[-1]}` - آخرین عنصر آرایه

## مثال ۲: حلقه روی آرایه

فایل [examples/02-loop-array.sh](examples/02-loop-array.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تعریف آرایه
NAMES=("علی" "رضا" "محمد" "حسین" "مهدی")

echo "نام‌ها:"
for name in "${NAMES[@]}"; do
    echo "  - $name"
done

# شمارش معکوس
echo -e "\nشمارش معکوس:"
for ((i=${#NAMES[@]}-1; i>=0; i--)); do
    echo "${NAMES[$i]}"
done
```

### توضیح خط به خط:

- `for name in "${NAMES[@]}"` - روی تمام عناصر حلقه می‌زند
- `${#NAMES[@]}-1` - آخرین ایندکس آرایه
- `for ((i=...; i>=0; i--))` - حلقه معکوس
- `${NAMES[$i]}` - دسترسی به عنصر با ایندکس i

## مثال ۳: افزودن و حذف از آرایه

فایل [examples/03-modify-array.sh](examples/03-modify-array.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تعریف آرایه
NUMBERS=(1 2 3 4 5)

echo "آرایه اولیه: ${NUMBERS[@]}"

# افزودن به انتهای آرایه
NUMBERS+=(6 7 8)
echo "بعد از افزودن: ${NUMBERS[@]}"

# تغییر یک عنصر
NUMBERS[0]=10
echo "بعد از تغییر: ${NUMBERS[@]}"

# حذف یک عنصر
unset NUMBERS[2]
echo "بعد از حذف ایندکس 2: ${NUMBERS[@]}"

# ایجاد آرایه جدید بدون عنصر خالی
NEW_ARRAY=()
for num in "${NUMBERS[@]}"; do
    if [ -n "$num" ]; then
        NEW_ARRAY+=("$num")
    fi
done
echo "آرایه تمیز: ${NEW_ARRAY[@]}"
```

### توضیح خط به خط:

- `NUMBERS+=(6 7 8)` - عناصر را به انتهای آرایه اضافه می‌کند
- `NUMBERS[0]=10` - عنصر ایندکس 0 را تغییر می‌دهد
- `unset NUMBERS[2]` - عنصر ایندکس 2 را حذف می‌کند
- حلقه برای حذف عناصر خالی
- `NEW_ARRAY+=("$num")` - عنصر را به آرایه جدید اضافه می‌کند

## کار با رشته‌ها

Bash امکانات متنوعی برای کار با رشته‌ها دارد.

### طول رشته

```bash
STRING="Hello"
echo ${#STRING}  # 5
```

### substring

```bash
STRING="Hello World"
echo ${STRING:0:5}  # Hello
echo ${STRING:6}    # World
```

### جایگزینی

```bash
STRING="Hello World"
echo ${STRING/World/Bash}  # Hello Bash
```

## مثال ۴: عملیات رشته‌ای

فایل [examples/04-string-operations.sh](examples/04-string-operations.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

TEXT="Hello World from Bash"

echo "متن اصلی: $TEXT"

# طول رشته
echo "طول رشته: ${#TEXT}"

# substring
echo "5 کاراکتر اول: ${TEXT:0:5}"
echo "از ایندکس 6: ${TEXT:6}"

# جایگزینی
echo "جایگزینی World با Bash: ${TEXT/World/Bash}"

# حذف زیررشته
echo "حذف Hello: ${TEXT#Hello }"
echo "حذف از انتها: ${TEXT% Bash}"

# تبدیل به حروف بزرگ
echo "حروف بزرگ: ${TEXT^^}"

# تبدیل به حروف کوچک
echo "حروف کوچک: ${TEXT,,}"
```

### توضیح خط به خط:

- `${#TEXT}` - طول رشته را برمی‌گرداند
- `${TEXT:0:5}` - 5 کاراکتر اول
- `${TEXT:6}` - از ایندکس 6 تا انتها
- `${TEXT/World/Bash}` - World را با Bash جایگزین می‌کند
- `${TEXT#Hello }` - Hello را از ابتدا حذف می‌کند
- `${TEXT% Bash}` - Bash را از انتها حذف می‌کند
- `${TEXT^^}` - به حروف بزرگ تبدیل می‌کند
- `${TEXT,,}` - به حروف کوچک تبدیل می‌کند

## مثال ۵: تبدیل رشته به آرایه

فایل [examples/05-string-to-array.sh](examples/05-string-to-array.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تبدیل رشته به آرایه با delimiter
TEXT="apple,banana,orange,grape"

# تبدیل با IFS
IFS=',' read -ra FRUITS <<< "$TEXT"

echo "آرایه میوه‌ها:"
for fruit in "${FRUITS[@]}"; do
    echo "  - $fruit"
done

# تبدیل با default delimiter (space)
WORDS=("Hello World from Bash")

echo -e "\nکلمات:"
for word in $WORDS; do
    echo "  - $word"
done
```

### توضیح خط به خط:

- `IFS=','` - delimiter را به کاما تنظیم می‌کند
- `read -ra FRUITS` - رشته را به آرایه تبدیل می‌کند
- `<<< "$TEXT"` - رشته را به دستور read می‌دهد
- `for word in $WORDS` - با delimiter پیش‌فرض (فاصله) جدا می‌شود

## مثال ۶: آرایه انجمنی (Associative Arrays)

فایل [examples/06-associative-array.sh](examples/06-associative-array.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تعریف آرایه انجمنی
declare -A PERSON

PERSON["name"]="علی"
PERSON["age"]=25
PERSON["city"]="تهران"

echo "اطلاعات شخص:"
echo "  نام: ${PERSON["name"]}"
echo "  سن: ${PERSON["age"]}"
echo "  شهر: ${PERSON["city"]}"

# حلقه روی آرایه انجمنی
echo -e "\nتمام کلیدها:"
for key in "${!PERSON[@]}"; do
    echo "  $key: ${PERSON[$key]}"
done
```

### توضیح خط به خط:

- `declare -A PERSON` - آرایه انجمنی تعریف می‌کند
- `PERSON["name"]="علی"` - مقداردهی با کلید رشته‌ای
- `${PERSON["name"]}` - دسترسی با کلید
- `${!PERSON[@]}` - تمام کلیدها را برمی‌گرداند
- حلقه روی تمام کلیدها

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن quote در آرایه‌ها

❌ اشتباه:
```bash
NAMES=(Ali Reza Mohammad)  # هر کلمه جداگانه
```

✅ درست:
```bash
NAMES=("Ali Reza" "Mohammad")  # هر نام یک عنصر
```

### ۲. استفاده از @ به جای * در حلقه

❌ مشکل‌دار:
```bash
for name in ${NAMES[@]}; do  # بدون quote
```

✅ درست:
```bash
for name in "${NAMES[@]}"; do  # با quote
```

### ۳. فراموش کردن declare برای آرایه انجمنی

❌ اشتباه:
```bash
PERSON["name"]="Ali"  # خطا
```

✅ درست:
```bash
declare -A PERSON
PERSON["name"]="Ali"
```

### ۴. استفاده از ایندکس منفی در Bash قدیمی

❌ مشکل‌دار:
```bash
echo ${ARRAY[-1]}  # در Bash قدیمی کار نمی‌کند
```

✅ درست:
```bash
LAST_INDEX=$((${#ARRAY[@]} - 1))
echo ${ARRAY[$LAST_INDEX]}
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه از quote برای عناصر آرایه استفاده کنید** - برای جلوگیری از مشکلات با فاصله
2. **از "${ARRAY[@]}" به جای ${ARRAY[@]} استفاده کنید** - برای حفظ فاصله
3. **نام آرایه‌ها را با حروف بزرگ بنویسید** - برای تشخیص آسان
4. **قبل از استفاده، طول آرایه را بررسی کنید** - برای جلوگیری از خطا
5. **از آرایه انجمنی برای key-value استفاده کنید** - خواناتر

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- چگونه آرایه‌ها را تعریف و استفاده کنیم
- دسترسی به عناصر آرایه
- حلقه روی آرایه‌ها
- افزودن و حذف از آرایه
- عملیات رشته‌ای (length, substring, replace)
- تبدیل رشته به آرایه
- آرایه‌های انجمنی
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): مدیریت لیست کارها

یک اسکریپت بنویسید که:
1. یک آرایه از کارها داشته باشد
2. کارهای انجام شده را نمایش دهد
3. کار جدیدی به آرایه اضافه کند
4. یک کار را حذف کند
5. لیست نهایی را چاپ کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): پردازش متن

یک اسکریپت بنویسید که:
1. یک متن طولانی از کاربر بگیرد
2. آن را به کلمات تبدیل کند (آرایه)
3. تعداد کلمات را چاپ کند
4. طولانی‌ترین کلمه را پیدا کند
5. کلمات تکراری را حذف کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، کار با فایل‌ها، ریدایرکت، pipeline و process substitution را یاد می‌گیریم.
