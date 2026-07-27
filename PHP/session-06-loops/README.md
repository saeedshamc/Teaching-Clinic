# جلسه ۰۶: حلقه‌ها در PHP — for, while, do-while, foreach

## 🎯 اهداف یادگیری

در پایان این جلسه، شما خواهید توانست:
- از حلقه `for` برای تکرار کد با تعداد مشخص استفاده کنید
- از حلقه `while` برای تکرار کد با شرط استفاده کنید
- از حلقه `do-while` برای تکرار کد با اجرای حداقل یک بار استفاده کنید
- از حلقه `foreach` برای پیمایش آرایه‌ها استفاده کنید
- از دستورات `break` و `continue` برای کنترل جریان حلقه استفاده کنید
- حلقه‌های تو در تو (Nested Loops) را مدیریت کنید

---

## 🔁 حلقه‌ها (Loops)

حلقه‌ها به شما اجازه می‌دهند تا یک بلوک کد را چندین بار اجرا کنید. این برای کار با مجموعه‌ای از داده‌ها یا انجام عملیات تکراری بسیار مفید است.

---

## 🔢 حلقه for

حلقه `for` برای تکرار کد با تعداد مشخص استفاده می‌شود.

### سینتکس

```php
for (initialization; condition; increment) {
    // کدهایی که در هر تکرار اجرا می‌شوند
}
```

- **initialization**: تنها یک بار در شروع اجرا می‌شود
- **condition**: در شروع هر تکرار بررسی می‌شود. اگر `false` باشد، حلقه متوقف می‌شود
- **increment**: در پایان هر تکرار اجرا می‌شود

### مثال‌های حلقه for

فایل: [`examples/01-for-loop.php`](./examples/01-for-loop.php)

```php
<?php
// مثال ۱: چاپ اعداد ۱ تا ۱۰
for ($i = 1; $i <= 10; $i++) {
    echo $i . "\n";
}

// مثال ۲: چاپ اعداد زوج ۱ تا ۲۰
for ($i = 2; $i <= 20; $i += 2) {
    echo $i . "\n";
}

// مثال ۳: چاپ اعداد به صورت معکوس
for ($i = 10; $i >= 1; $i--) {
    echo $i . "\n";
}

// مثال ۴: چاپ جدول ضرب
$number = 5;
for ($i = 1; $i <= 10; $i++) {
    echo "$number x $i = " . ($number * $i) . "\n";
}

// مثال ۵: استفاده از چندین متغیر در حلقه
for ($i = 1, $j = 10; $i <= 10; $i++, $j--) {
    echo "i: $i, j: $j\n";
}
?>
```

**توضیح کد:**
- `$i = 1`: مقداردهی اولیه
- `$i <= 10`: شرط ادامه حلقه
- `$i++`: افزایش متغیر در هر تکرار
- می‌توانید چندین متغیر را در حلقه مدیریت کنید.

---

## 🔄 حلقه while

حلقه `while` برای تکرار کد تا زمانی که شرط `true` باشد استفاده می‌شود.

### سینتکس

```php
while (condition) {
    // کدهایی که در هر تکرار اجرا می‌شوند
}
```

### مثال‌های حلقه while

فایل: [`examples/02-while-loop.php`](./examples/02-while-loop.php)

```php
<?php
// مثال ۱: چاپ اعداد ۱ تا ۱۰
$i = 1;
while ($i <= 10) {
    echo $i . "\n";
    $i++;
}

// مثال ۲: جمع اعداد ۱ تا ۱۰
$sum = 0;
$i = 1;
while ($i <= 10) {
    $sum += $i;
    $i++;
}
echo "Sum of 1 to 10: $sum\n";

// مثال ۳: خواندن داده تا زمانی که شرط برآورده شود
$numbers = [1, 2, 3, 4, 5];
$index = 0;
while ($index < count($numbers)) {
    echo "Number: " . $numbers[$index] . "\n";
    $index++;
}

// مثال ۴: حلقه بی‌نهایت (دقت کنید!)
// این کد را اجرا نکنید!
/*
$i = 1;
while (true) {
    echo $i . "\n";
    $i++;
}
*/

// مثال ۵: استفاده از break برای خروج از حلقه
$i = 1;
while (true) {
    echo $i . "\n";
    $i++;
    if ($i > 10) {
        break;  // خروج از حلقه
    }
}
?>
```

**توضیح کد:**
- حلقه `while` ابتدا شرط را بررسی می‌کند، سپس کد را اجرا می‌کند.
- اگر شرط از ابتدا `false` باشد، کد داخل حلقه هرگز اجرا نمی‌شود.
- **هشدار:** مراقب حلقه‌های بی‌نهایت باشید!

---

## 🔄 حلقه do-while

حلقه `do-while` مشابه `while` است، اما کد را **حداقل یک بار** اجرا می‌کند، حتی اگر شرط `false` باشد.

### سینتکس

```php
do {
    // کدهایی که در هر تکرار اجرا می‌شوند
} while (condition);
```

### مثال‌های حلقه do-while

فایل: [`examples/03-do-while-loop.php`](./examples/03-do-while-loop.php)

```php
<?php
// مثال ۱: چاپ اعداد ۱ تا ۱۰
$i = 1;
do {
    echo $i . "\n";
    $i++;
} while ($i <= 10);

// مثال ۲: دریافت ورودی از کاربر (در محیط خط فرمان)
// این مثال در محیط وب کار نمی‌کند
/*
$input = "";
do {
    $input = readline("Enter 'yes' to continue or 'no' to exit: ");
    echo "You entered: $input\n";
} while ($input !== "no");
*/

// مثال ۳: حداقل یک بار اجرا می‌شود
$condition = false;
do {
    echo "This will be printed at least once!\n";
} while ($condition);

// مثال ۴: منوی ساده
$choice = "";
echo "Menu:\n";
echo "1. Option 1\n";
echo "2. Option 2\n";
echo "3. Exit\n";

// در محیط واقعی، این از کاربر دریافت می‌شود
// برای مثال، ما آن را شبیه‌سازی می‌کنیم
$choices = [1, 2, 3];
foreach ($choices as $choice) {
    do {
        echo "Selected option: $choice\n";
        if ($choice === 3) {
            echo "Exiting...\n";
            break;
        }
        // در محیط واقعی: $choice = (int)readline("Enter your choice: ");
        $choice = 3; // برای خروج از حلقه
    } while (true);
    break;
}
?>
```

**توضیح کد:**
- حلقه `do-while` همیشه **حداقل یک بار** اجرا می‌شود.
- شرط در پایان حلقه بررسی می‌شود.
- برای منوها و دریافت ورودی از کاربر بسیار مفید است.

---

## 🔄 حلقه foreach

حلقه `foreach` برای پیمایش آرایه‌ها استفاده می‌شود.

### سینتکس

```php
// برای آرایه‌های indexed
foreach ($array as $value) {
    // کدهایی که برای هر عنصر اجرا می‌شوند
}

// برای آرایه‌های associative
foreach ($array as $key => $value) {
    // کدهایی که برای هر جفت کلید/مقدار اجرا می‌شوند
}
```

### مثال‌های حلقه foreach

فایل: [`examples/04-foreach-loop.php`](./examples/04-foreach-loop.php)

```php
<?php
// مثال ۱: پیمایش آرایه indexed
$fruits = ["Apple", "Banana", "Orange", "Mango"];
foreach ($fruits as $fruit) {
    echo "Fruit: $fruit\n";
}

// مثال ۲: پیمایش آرایه associative
$person = [
    "name" => "Ali",
    "age" => 25,
    "city" => "Tehran"
];

foreach ($person as $key => $value) {
    echo "$key: $value\n";
}

// مثال ۳: پیمایش آرایه چندبعدی
$users = [
    ["name" => "Ali", "age" => 25],
    ["name" => "Reza", "age" => 30],
    ["name" => "Sara", "age" => 22]
];

foreach ($users as $user) {
    echo "User: " . $user["name"] . ", Age: " . $user["age"] . "\n";
}

// مثال ۴: استفاده از index در آرایه indexed
$numbers = [10, 20, 30, 40];
foreach ($numbers as $index => $number) {
    echo "Index $index: $number\n";
}

// مثال ۵: تغییر مقادیر آرایه در حلقه
$prices = [100, 200, 300];
foreach ($prices as &$price) {
    $price *= 1.1;  // ۱۰% افزایش
}
unset($price);  // مهم: برای جلوگیری از مشکلات
print_r($prices);
?>
```

**توضیح کد:**
- `foreach` برای هر عنصر در آرایه اجرا می‌شود.
- برای آرایه‌های associative، می‌توانید هم کلید و هم مقدار را دریافت کنید.
- استفاده از `&` برای تغییر مقادیر آرایه در حلقه.
- **مهم:** پس از استفاده از `&`، متغیر را با `unset()` پاک کنید.

---

## 🛑 دستورات کنترل حلقه

### دستور break

دستور `break` برای خروج فوری از حلقه استفاده می‌شود.

فایل: [`examples/05-break-continue.php`](./examples/05-break-continue.php)

```php
<?php
// مثال ۱: خروج از حلقه با break
for ($i = 1; $i <= 10; $i++) {
    if ($i === 6) {
        break;  // خروج از حلقه زمانی که $i برابر 6 باشد
    }
    echo $i . "\n";
}
// خروجی: 1, 2, 3, 4, 5

// مثال ۲: جستجوی یک عنصر در آرایه
$numbers = [10, 20, 30, 40, 50];
$search = 30;
$found = false;

foreach ($numbers as $number) {
    if ($number === $search) {
        $found = true;
        break;  // خروج از حلقه پس از پیدا کردن
    }
}

echo $found ? "Found!\n" : "Not found!\n";

// مثال ۳: break در حلقه تو در تو
for ($i = 1; $i <= 3; $i++) {
    for ($j = 1; $j <= 3; $j++) {
        if ($i === 2 && $j === 2) {
            break;  // تنها از حلقه داخلی خارج می‌شود
        }
        echo "i: $i, j: $j\n";
    }
}
?>
```

### دستور continue

دستور `continue` برای رد کردن تکرار جاری و رفتن به تکرار بعدی استفاده می‌شود.

```php
<?php
// مثال ۴: رد کردن اعداد زوج
for ($i = 1; $i <= 10; $i++) {
    if ($i % 2 === 0) {
        continue;  // رد کردن اعداد زوج
    }
    echo $i . "\n";
}
// خروجی: 1, 3, 5, 7, 9

// مثال ۵: رد کردن مقادیر خاص در آرایه
$numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
foreach ($numbers as $number) {
    if ($number % 3 === 0) {
        continue;  // رد کردن اعدادی که بر ۳ بخش‌پذیر هستند
    }
    echo $number . "\n";
}
// خروجی: 1, 2, 4, 5, 7, 8, 10

// مثال ۶: continue در حلقه تو در تو
for ($i = 1; $i <= 3; $i++) {
    for ($j = 1; $j <= 3; $j++) {
        if ($j === 2) {
            continue;  // رد کردن تکرار جاری حلقه داخلی
        }
        echo "i: $i, j: $j\n";
    }
}
?>
```

**توضیح کد:**
- `break`: خروج فوری از حلقه
- `continue`: رد کردن تکرار جاری و رفتن به تکرار بعدی
- در حلقه‌های تو در تو، `break` و `continue` تنها روی حلقه جاری اثر می‌گذارند.

---

## 🪆 حلقه‌های تو در تو (Nested Loops)

حلقه‌های تو در تو برای کار با ساختارهای داده‌ای پیچیده مانند آرایه‌های چندبعدی استفاده می‌شوند.

فایل: [`examples/06-nested-loops.php`](./examples/06-nested-loops.php)

```php
<?php
// مثال ۱: چاپ جدول ضرب
for ($i = 1; $i <= 10; $i++) {
    for ($j = 1; $j <= 10; $j++) {
        echo "$i x $j = " . ($i * $j) . "\t";
    }
    echo "\n";
}

// مثال ۲: پیمایش آرایه چندبعدی
$matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
];

foreach ($matrix as $row) {
    foreach ($row as $value) {
        echo $value . " ";
    }
    echo "\n";
}

// مثال ۳: تولید ترکیبات
$colors = ["Red", "Green", "Blue"];
$sizes = ["S", "M", "L"];

foreach ($colors as $color) {
    foreach ($sizes as $size) {
        echo "$color $size\n";
    }
}

// مثال ۴: استفاده از break در حلقه تو در تو
for ($i = 1; $i <= 5; $i++) {
    for ($j = 1; $j <= 5; $j++) {
        if ($i * $j > 10) {
            break;  // خروج از حلقه داخلی
        }
        echo "i: $i, j: $j\n";
    }
}
?>
```

**توضیح کد:**
- حلقه‌های تو در تو برای کار با ساختارهای چندبعدی استفاده می‌شوند.
- می‌توانید هر نوع حلقه‌ای را داخل یکدیگر قرار دهید.
- **هشدار:** حلقه‌های تو در تو زیاد می‌توانند عملکرد برنامه را کاهش دهند.

---

## ⚠️ نکات رایج و اشتباهات

### اشتباهات رایج

#### ۱. فراموش کردن افزایش متغیر در حلقه for
```php
// اشتباه: حلقه بی‌نهایت
for ($i = 1; $i <= 10;) {
    echo $i . "\n";
}

// درست
for ($i = 1; $i <= 10; $i++) {
    echo $i . "\n";
}
```

#### ۲. فراموش کردن شرط در حلقه while
```php
// اشتباه: حلقه بی‌نهایت
$i = 1;
while (true) {
    echo $i . "\n";
}

// درست
$i = 1;
while ($i <= 10) {
    echo $i . "\n";
    $i++;
}
```

#### ۳. استفاده نادرست از break و continue
```php
// اشتباه: استفاده از break در خارج از حلقه
if ($condition) {
    break;  // خطا: break خارج از حلقه
}

// درست
for ($i = 1; $i <= 10; $i++) {
    if ($i === 5) {
        break;  // درست: داخل حلقه
    }
}
```

#### ۴. فراموش کردن & در foreach برای تغییر آرایه
```php
// اشتباه: تغییر آرایه کار نمی‌کند
foreach ($numbers as $number) {
    $number *= 2;  // تغییر در آرایه اصلی اعمال نمی‌شود
}

// درست: استفاده از &
foreach ($numbers as &$number) {
    $number *= 2;  // تغییر در آرایه اصلی اعمال می‌شود
}
unset($number);
```

#### ۵. استفاده از حلقه اشتباه برای کار خاص
```php
// بد: استفاده از for برای پیمایش آرایه با اندازه نامشخص
for ($i = 0; $i < count($array); $i++) {
    echo $array[$i];
}

// خوب: استفاده از foreach
foreach ($array as $item) {
    echo $item;
}
```

### بهترین شیوه‌ها

1. **برای آرایه‌ها از foreach استفاده کنید**:
   ```php
   // بد
   for ($i = 0; $i < count($array); $i++) {
       echo $array[$i];
   }
   
   // خوب
   foreach ($array as $item) {
       echo $item;
   }
   ```

2. **برای حلقه‌های با تعداد تکرار مشخص از for استفاده کنید**:
   ```php
   // بد
   $i = 1;
   while ($i <= 10) {
       echo $i;
       $i++;
   }
   
   // خوب
   for ($i = 1; $i <= 10; $i++) {
       echo $i;
   }
   ```

3. **برای دریافت ورودی از کاربر از do-while استفاده کنید**:
   ```php
   // بد
   while ($input !== "quit") {
       $input = readline("Enter command: ");
   }
   
   // خوب
   do {
       $input = readline("Enter command: ");
   } while ($input !== "quit");
   ```

4. **از نام‌های معنی‌دار برای متغیرهای حلقه استفاده کنید**:
   ```php
   // بد
   for ($i = 0; $i < 10; $i++) { ... }
   
   // خوب
   for ($userIndex = 0; $userIndex < 10; $userIndex++) { ... }
   ```

5. **از break و continue با احتیاط استفاده کنید**:
   - `break` و `continue` می‌توانند خوانایی کد را کاهش دهند.
   - سعی کنید از آن‌ها تنها زمانی استفاده کنید که ضروری باشد.

6. **حلقه‌های تو در تو را محدود کنید**:
   - حلقه‌های تو در تو زیاد می‌توانند عملکرد برنامه را کاهش دهند.
   - سعی کنید از حداکثر ۳ سطح تو در تو استفاده کنید.

---

## 📝 تمرین‌های عملی

### تمرین ۱: چاپ اعداد زوج

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. اعداد زوج ۱ تا ۲۰ را چاپ کند.
2. از حلقه `for` استفاده کنید.

**مثال خروجی:**
```
2
4
6
...
20
```

**فایل تمرین:** [`exercises/exercise-01.php`](./exercises/exercise-01.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۱: چاپ اعداد زوج
for ($i = 2; $i <= 20; $i += 2) {
    echo $i . "\n";
}
?>
```
</details>

---

### تمرین ۲: جمع اعداد

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. جمع اعداد ۱ تا ۱۰۰ را محاسبه کند.
2. نتیجه را چاپ کند.

**مثال خروجی:**
```
Sum of numbers from 1 to 100: 5050
```

**فایل تمرین:** [`exercises/exercise-02.php`](./exercises/exercise-02.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۲: جمع اعداد
$sum = 0;
for ($i = 1; $i <= 100; $i++) {
    $sum += $i;
}
echo "Sum of numbers from 1 to 100: $sum\n";
?>
```
</details>

---

### تمرین ۳: پیمایش آرایه

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. آرایه زیر را تعریف کند:
   ```php
   $fruits = ["Apple", "Banana", "Orange", "Mango"];
   ```
2. با استفاده از حلقه `foreach`، تمام میوه‌ها را چاپ کند.

**مثال خروجی:**
```
Fruit: Apple
Fruit: Banana
Fruit: Orange
Fruit: Mango
```

**فایل تمرین:** [`exercises/exercise-03.php`](./exercises/exercise-03.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۳: پیمایش آرایه
$fruits = ["Apple", "Banana", "Orange", "Mango"];
foreach ($fruits as $fruit) {
    echo "Fruit: $fruit\n";
}
?>
```
</details>

---

### تمرین ۴: استفاده از break و continue

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. اعداد ۱ تا ۲۰ را چاپ کند.
2. از چاپ عدد ۵ صرف‌نظر کند (با استفاده از `continue`).
3. زمانی که به عدد ۱۵ رسید، از حلقه خارج شود (با استفاده از `break`).

**مثال خروجی:**
```
1
2
3
4
6
7
8
9
10
11
12
13
14
```

**فایل تمرین:** [`exercises/exercise-04.php`](./exercises/exercise-04.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۴: استفاده از break و continue
for ($i = 1; $i <= 20; $i++) {
    if ($i === 5) {
        continue;  // رد کردن عدد 5
    }
    if ($i === 15) {
        break;  // خروج از حلقه در عدد 15
    }
    echo $i . "\n";
}
?>
```
</details>

---

### تمرین ۵: جدول ضرب

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. جدول ضرب ۵ را چاپ کند (از ۱ تا ۱۰).
2. از حلقه `for` استفاده کنید.

**مثال خروجی:**
```
5 x 1 = 5
5 x 2 = 10
5 x 3 = 15
...
5 x 10 = 50
```

**فایل تمرین:** [`exercises/exercise-05.php`](./exercises/exercise-05.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۵: جدول ضرب
$number = 5;
for ($i = 1; $i <= 10; $i++) {
    echo "$number x $i = " . ($number * $i) . "\n";
}
?>
```
</details>

---

## 📌 خلاصه جلسه

در این جلسه یاد گرفتیم:
✅ حلقه `for` برای تکرار با تعداد مشخص
✅ حلقه `while` برای تکرار با شرط
✅ حلقه `do-while` برای تکرار با اجرای حداقل یک بار
✅ حلقه `foreach` برای پیمایش آرایه‌ها
✅ دستورات `break` و `continue` برای کنترل جریان حلقه
✅ حلقه‌های تو در تو (Nested Loops)

---

## 🔗 لینک به فایل‌های کد

- [مثال ۱: for Loop](./examples/01-for-loop.php)
- [مثال ۲: while Loop](./examples/02-while-loop.php)
- [مثال ۳: do-while Loop](./examples/03-do-while-loop.php)
- [مثال ۴: foreach Loop](./examples/04-foreach-loop.php)
- [مثال ۵: break and continue](./examples/05-break-continue.php)
- [مثال ۶: Nested Loops](./examples/06-nested-loops.php)
- [تمرین ۱: چاپ اعداد زوج](./exercises/exercise-01.php)
- [تمرین ۲: جمع اعداد](./exercises/exercise-02.php)
- [تمرین ۳: پیمایش آرایه](./exercises/exercise-03.php)
- [تمرین ۴: استفاده از break و continue](./exercises/exercise-04.php)
- [تمرین ۵: جدول ضرب](./exercises/exercise-05.php)

---

## 📢 نکته پایانی

حلقه‌ها **ابزارهای قدرتمندی** برای انجام عملیات تکراری هستند. درک صحیح حلقه‌ها به شما کمک می‌کند تا کدهای کارآمدتر و کوتاه‌تری بنویسید.

**نکته مهم:** همیشه مراقب حلقه‌های بی‌نهایت باشید و مطمئن شوید که حلقه شما در نهایت متوقف می‌شود.

در جلسه بعدی، با **آرایه‌ها** در PHP آشنا خواهیم شد! 🚀
