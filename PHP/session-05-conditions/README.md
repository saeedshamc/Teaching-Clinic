# جلسه ۰۵: شرط‌ها در PHP — if/elseif/else و switch

## 🎯 اهداف یادگیری

در پایان این جلسه، شما خواهید توانست:
- از دستورات شرطی `if`, `elseif`, `else` استفاده کنید
- شرایط پیچیده با عملگرهای منطقی بسازید
- از عملگر سه‌تایی (`?:`) برای شرایط ساده استفاده کنید
- از دستور `switch` برای شرایط چندگانه استفاده کنید
- شرایط تو در تو (Nested Conditions) را مدیریت کنید

---

## ❓ دستورات شرطی (Conditional Statements)

دستورات شرطی به شما اجازه می‌دهند تا کدهای مختلفی را بر اساس شرایط مختلف اجرا کنید.

---

## 🔄 دستور if

دستور `if` ساده‌ترین دستور شرطی است. اگر شرط `true` باشد، بلوک کد اجرا می‌شود.

### سینتکس پایه

```php
if (condition) {
    // کدهایی که اگر شرط true باشد اجرا می‌شوند
}
```

### مثال‌های ساده if

فایل: [`examples/01-simple-if.php`](./examples/01-simple-if.php)

```php
<?php
// مثال ۱: بررسی عدد مثبت
$number = 10;

if ($number > 0) {
    echo "$number is positive.\n";
}

// مثال ۲: بررسی ورود کاربر
$isLoggedIn = true;

if ($isLoggedIn) {
    echo "Welcome back!\n";
}

// مثال ۳: بررسی سن
$age = 25;

if ($age >= 18) {
    echo "You are an adult.\n";
}
?>
```

**توضیح کد:**
- شرط داخل پرانتز قرار می‌گیرد.
- اگر شرط `true` باشد، بلوک کد داخل `{}` اجرا می‌شود.
- در PHP، مقادیر `0`, `""`, `null`, `false`, `[]` به عنوان `false` در نظر گرفته می‌شوند.

---

## 🔄 دستور if/else

دستور `else` به شما اجازه می‌دهد کدی را اجرا کنید اگر شرط `if` `false` باشد.

### سینتکس

```php
if (condition) {
    // کدهایی که اگر شرط true باشد اجرا می‌شوند
} else {
    // کدهایی که اگر شرط false باشد اجرا می‌شوند
}
```

### مثال‌های if/else

فایل: [`examples/02-if-else.php`](./examples/02-if-else.php)

```php
<?php
// مثال ۱: بررسی عدد مثبت/منفی
$number = -5;

if ($number > 0) {
    echo "$number is positive.\n";
} else {
    echo "$number is not positive.\n";
}

// مثال ۲: بررسی ورود
$isLoggedIn = false;

if ($isLoggedIn) {
    echo "Welcome back!\n";
} else {
    echo "Please log in.\n";
}

// مثال ۳: بررسی سن
$age = 16;

if ($age >= 18) {
    echo "You are an adult.\n";
} else {
    echo "You are a minor.\n";
}
?>
```

---

## 🔄 دستور if/elseif/else

دستور `elseif` به شما اجازه می‌دهد چندین شرط را بررسی کنید.

### سینتکس

```php
if (condition1) {
    // کدهایی که اگر condition1 true باشد اجرا می‌شوند
} elseif (condition2) {
    // کدهایی که اگر condition2 true باشد اجرا می‌شوند
} else {
    // کدهایی که اگر هیچ کدام از شرایط true نباشند اجرا می‌شوند
}
```

### مثال‌های if/elseif/else

فایل: [`examples/03-if-elseif-else.php`](./examples/03-if-elseif-else.php)

```php
<?php
// مثال ۱: درجه‌بندی نمره
$score = 85;

if ($score >= 90) {
    echo "Grade: A\n";
} elseif ($score >= 80) {
    echo "Grade: B\n";
} elseif ($score >= 70) {
    echo "Grade: C\n";
} elseif ($score >= 60) {
    echo "Grade: D\n";
} else {
    echo "Grade: F\n";
}

// مثال ۲: بررسی دامنه سن
$age = 35;

if ($age < 13) {
    echo "Child\n";
} elseif ($age < 20) {
    echo "Teenager\n";
} elseif ($age < 65) {
    echo "Adult\n";
} else {
    echo "Senior\n";
}

// مثال ۳: بررسی روز هفته
$day = "Wednesday";

if ($day === "Monday") {
    echo "Start of the week.\n";
} elseif ($day === "Friday") {
    echo "Almost weekend!\n";
} elseif ($day === "Saturday" || $day === "Sunday") {
    echo "Weekend!\n";
} else {
    echo "Midweek.\n";
}
?>
```

**توضیح کد:**
- PHP شرایط را از بالا به پایین بررسی می‌کند.
- پس از یافتن اولین شرط `true`، بقیه شرایط بررسی نمی‌شوند.
- می‌توانید چندین `elseif` داشته باشید.

---

## ❓ عملگر سه‌تایی (Ternary Operator)

عملگر سه‌تایی (`?:`) یک راه مختصر برای نوشتن `if/else` ساده است.

### سینتکس

```php
condition ? expr1 : expr2
```

- اگر `condition` `true` باشد، `expr1` اجرا می‌شود.
- اگر `condition` `false` باشد، `expr2` اجرا می‌شود.

### مثال‌های عملگر سه‌تایی

فایل: [`examples/04-ternary-operator.php`](./examples/04-ternary-operator.php)

```php
<?php
// مثال ۱: ساده
$age = 25;
$status = ($age >= 18) ? "Adult" : "Minor";
echo "Status: $status\n";

// مثال ۲: چاپ مستقیم
$number = 10;
echo "The number is " . ($number % 2 === 0 ? "even" : "odd") . "\n";

// مثال ۳: عملگر سه‌تایی تو در تو
$score = 85;
$grade = ($score >= 90) ? "A" : 
        (($score >= 80) ? "B" : 
        (($score >= 70) ? "C" : 
        (($score >= 60) ? "D" : "F")));
echo "Grade: $grade\n";

// مثال ۴: با echo
$isLoggedIn = true;
echo $isLoggedIn ? "Welcome back!" : "Please log in.";
?>
```

**توضیح کد:**
- عملگر سه‌تایی برای شرایط ساده بسیار مفید است.
- می‌توان عملگرهای سه‌تایی را تو در تو استفاده کرد، اما این کار خوانایی کد را کاهش می‌دهد.

---

## 🔄 دستور switch

دستور `switch` برای بررسی یک متغیر در برابر چندین مقدار استفاده می‌شود.

### سینتکس

```php
switch (variable) {
    case value1:
        // کدهایی که اجرا می‌شوند
        break;
    case value2:
        // کدهایی که اجرا می‌شوند
        break;
    default:
        // کدهایی که اگر هیچ case ای مطابقت نکند اجرا می‌شوند
}
```

### مثال‌های switch

فایل: [`examples/05-switch-statement.php`](./examples/05-switch-statement.php)

```php
<?php
// مثال ۱: روز هفته
$day = "Thursday";

switch ($day) {
    case "Monday":
        echo "Start of the work week.\n";
        break;
    case "Tuesday":
    case "Wednesday":
    case "Thursday":
        echo "Midweek.\n";
        break;
    case "Friday":
        echo "Almost weekend!\n";
        break;
    case "Saturday":
    case "Sunday":
        echo "Weekend!\n";
        break;
    default:
        echo "Invalid day.\n";
}

// مثال ۲: درجه‌بندی
$grade = "B";

switch ($grade) {
    case "A":
        echo "Excellent!\n";
        break;
    case "B":
        echo "Good job!\n";
        break;
    case "C":
        echo "Average.\n";
        break;
    case "D":
        echo "Needs improvement.\n";
        break;
    case "F":
        echo "Fail.\n";
        break;
    default:
        echo "Invalid grade.\n";
}

// مثال ۳: مقایسه با انواع مختلف
$value = "5";

switch ($value) {
    case 5:
        echo "Integer 5\n";
        break;
    case "5":
        echo "String '5'\n";
        break;
    default:
        echo "Other value\n";
}

// مثال ۴: استفاده از break
$number = 2;

switch ($number) {
    case 1:
        echo "One\n";
        // break فراموش شده است!
    case 2:
        echo "Two\n";
        break;
    case 3:
        echo "Three\n";
        break;
    default:
        echo "Other\n";
}
// خروجی: Two (چون case 1 break ندارد، به case 2 می‌رود)
?>
```

**توضیح کد:**
- `switch` متغیر را با هر `case` مقایسه می‌کند.
- `break` برای خروج از `switch` استفاده می‌شود.
- اگر `break` فراموش شود، اجرا به `case` بعدی ادامه می‌یابد (fall-through).
- `default` اختیاری است و اگر هیچ `case` ای مطابقت نکند اجرا می‌شود.

---

## 🪆 شرط‌های تو در تو (Nested Conditions)

شما می‌توانید دستورات شرطی را داخل یکدیگر قرار دهید.

### مثال‌های شرط‌های تو در تو

فایل: [`examples/06-nested-conditions.php`](./examples/06-nested-conditions.php)

```php
<?php
// مثال ۱: بررسی سن و مجوز
$age = 25;
$hasLicense = true;

if ($age >= 18) {
    if ($hasLicense) {
        echo "You can drive.\n";
    } else {
        echo "You are old enough but don't have a license.\n";
    }
} else {
    echo "You are too young to drive.\n";
}

// مثال ۲: بررسی ورود و نقش کاربر
$isLoggedIn = true;
$isAdmin = false;

if ($isLoggedIn) {
    if ($isAdmin) {
        echo "Welcome, Admin!\n";
    } else {
        echo "Welcome, User!\n";
    }
} else {
    echo "Please log in.\n";
}

// مثال ۳: بررسی نمره و حضور
$score = 85;
$attendance = 90;

if ($attendance >= 80) {
    if ($score >= 90) {
        echo "Grade: A (Excellent attendance and score)\n";
    } elseif ($score >= 80) {
        echo "Grade: B (Good attendance and score)\n";
    } else {
        echo "Grade: C (Good attendance but average score)\n";
    }
} else {
    echo "Failed due to low attendance.\n";
}
?>
```

**توضیح کد:**
- شرط‌های تو در تو برای شرایط پیچیده استفاده می‌شوند.
- می‌توانید چندین سطح تو در تو داشته باشید.
- **هشدار:** شرط‌های تو در تو زیاد می‌توانند خوانایی کد را کاهش دهند.

---

## ⚠️ نکات رایج و اشتباهات

### اشتباهات رایج

#### ۱. فراموش کردن پرانتز در شرط
```php
// اشتباه
if $age >= 18 {
    echo "Adult";
}

// درست
if ($age >= 18) {
    echo "Adult";
}
```

#### ۲. فراموش کردن `{}` برای بلوک‌های چندخطی
```php
// اشتباه
if ($age >= 18)
    echo "Adult";
    echo "You can vote.";  // این خط همیشه اجرا می‌شود

// درست
if ($age >= 18) {
    echo "Adult";
    echo "You can vote.";
}
```

#### ۳. فراموش کردن `break` در switch
```php
// اشتباه
switch ($day) {
    case "Monday":
        echo "Start of week";
    case "Tuesday":
        echo "Second day";
    // هر دو پیام چاپ می‌شوند!
}

// درست
switch ($day) {
    case "Monday":
        echo "Start of week";
        break;
    case "Tuesday":
        echo "Second day";
        break;
}
```

#### ۴. استفاده از `=` به جای `==`
```php
// اشتباه
if ($age = 18) {  // این همیشه true است (اصالح 18 به $age)
    echo "You are 18";
}

// درست
if ($age == 18) {
    echo "You are 18";
}
```

#### ۵. استفاده از `==` به جای `===` برای مقایسه نوع
```php
// اشتباه
if ("5" == 5) {  // true، اما انواع متفاوت هستند
    echo "Equal";
}

// درست
if ("5" === 5) {  // false، انواع متفاوت هستند
    echo "Equal";
} else {
    echo "Not equal or different types";
}
```

### بهترین شیوه‌ها

1. **همیشه از `{}` استفاده کنید**:
   ```php
   // بد
   if ($age >= 18)
       echo "Adult";
   
   // خوب
   if ($age >= 18) {
       echo "Adult";
   }
   ```

2. **از نام‌گذاری معنی‌دار برای متغیرهای شرطی استفاده کنید**:
   ```php
   // بد
   if ($x) { ... }
   
   // خوب
   if ($isLoggedIn) { ... }
   ```

3. **شرط‌های پیچیده را به متغیرها تقسیم کنید**:
   ```php
   // بد
   if (($age >= 18 && $hasLicense) || ($age >= 16 && $hasParentalConsent)) { ... }
   
   // خوب
   $canDriveWithLicense = $age >= 18 && $hasLicense;
   $canDriveWithConsent = $age >= 16 && $hasParentalConsent;
   if ($canDriveWithLicense || $canDriveWithConsent) { ... }
   ```

4. **از عملگر سه‌تایی برای شرایط ساده استفاده کنید**:
   ```php
   // بد
   if ($age >= 18) {
       $status = "Adult";
   } else {
       $status = "Minor";
   }
   
   // خوب
   $status = ($age >= 18) ? "Adult" : "Minor";
   ```

5. **در switch، همیشه از break استفاده کنید**:
   ```php
   // بد
   case "Monday":
       echo "Monday";
   
   // خوب
   case "Monday":
       echo "Monday";
       break;
   ```

6. **از default در switch استفاده کنید**:
   ```php
   // بد
   switch ($day) {
       case "Monday":
           echo "Monday";
           break;
   }
   
   // خوب
   switch ($day) {
       case "Monday":
           echo "Monday";
           break;
       default:
           echo "Other day";
   }
   ```

---

## 📝 تمرین‌های عملی

### تمرین ۱: بررسی عدد

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. متغیر `$number` را با مقدار 15 تعریف کند.
2. بررسی کند که آیا عدد مثبت، منفی یا صفر است.
3. نتیجه را چاپ کند.

**مثال خروجی:**
```
15 is positive.
```

**فایل تمرین:** [`exercises/exercise-01.php`](./exercises/exercise-01.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۱: بررسی عدد
$number = 15;

if ($number > 0) {
    echo "$number is positive.\n";
} elseif ($number < 0) {
    echo "$number is negative.\n";
} else {
    echo "$number is zero.\n";
}
?>
```
</details>

---

### تمرین ۲: درجه‌بندی نمره

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. متغیر `$score` را با مقدار 75 تعریف کند.
2. بر اساس نمره، درجه را تعیین کند:
   - 90-100: A
   - 80-89: B
   - 70-79: C
   - 60-69: D
   - زیر 60: F
3. نتیجه را چاپ کند.

**مثال خروجی:**
```
Grade: C
```

**فایل تمرین:** [`exercises/exercise-02.php`](./exercises/exercise-02.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۲: درجه‌بندی نمره
$score = 75;

if ($score >= 90) {
    echo "Grade: A\n";
} elseif ($score >= 80) {
    echo "Grade: B\n";
} elseif ($score >= 70) {
    echo "Grade: C\n";
} elseif ($score >= 60) {
    echo "Grade: D\n";
} else {
    echo "Grade: F\n";
}
?>
```
</details>

---

### تمرین ۳: استفاده از switch

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. متغیر `$day` را با مقدار "Wednesday" تعریف کند.
2. با استفاده از `switch`، نوع روز را تعیین کند:
   - "Monday" تا "Friday": "Weekday"
   - "Saturday" یا "Sunday": "Weekend"
   - سایر مقادیر: "Invalid day"
3. نتیجه را چاپ کند.

**مثال خروجی:**
```
Wednesday is a Weekday.
```

**فایل تمرین:** [`exercises/exercise-03.php`](./exercises/exercise-03.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۳: استفاده از switch
$day = "Wednesday";

switch ($day) {
    case "Monday":
    case "Tuesday":
    case "Wednesday":
    case "Thursday":
    case "Friday":
        echo "$day is a Weekday.\n";
        break;
    case "Saturday":
    case "Sunday":
        echo "$day is a Weekend.\n";
        break;
    default:
        echo "Invalid day.\n";
}
?>
```
</details>

---

### تمرین ۴: عملگر سه‌تایی

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. متغیر `$age` را با مقدار 20 تعریف کند.
2. با استفاده از عملگر سه‌تایی، وضعیت "Adult" یا "Minor" را تعیین کند.
3. نتیجه را چاپ کند.

**مثال خروجی:**
```
Status: Adult
```

**فایل تمرین:** [`exercises/exercise-04.php`](./exercises/exercise-04.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۴: عملگر سه‌تایی
$age = 20;
$status = ($age >= 18) ? "Adult" : "Minor";
echo "Status: $status\n";
?>
```
</details>

---

## 📌 خلاصه جلسه

در این جلسه یاد گرفتیم:
✅ دستور `if` برای شرایط ساده
✅ دستور `if/else` برای شرایط دوگانه
✅ دستور `if/elseif/else` برای شرایط چندگانه
✅ عملگر سه‌تایی (`?:`) برای شرایط مختصر
✅ دستور `switch` برای مقایسه یک متغیر با چندین مقدار
✅ شرط‌های تو در تو (Nested Conditions)

---

## 🔗 لینک به فایل‌های کد

- [مثال ۱: Simple if](./examples/01-simple-if.php)
- [مثال ۲: if/else](./examples/02-if-else.php)
- [مثال ۳: if/elseif/else](./examples/03-if-elseif-else.php)
- [مثال ۴: Ternary Operator](./examples/04-ternary-operator.php)
- [مثال ۵: switch Statement](./examples/05-switch-statement.php)
- [مثال ۶: Nested Conditions](./examples/06-nested-conditions.php)
- [تمرین ۱: بررسی عدد](./exercises/exercise-01.php)
- [تمرین ۲: درجه‌بندی نمره](./exercises/exercise-02.php)
- [تمرین ۳: استفاده از switch](./exercises/exercise-03.php)
- [تمرین ۴: عملگر سه‌تایی](./exercises/exercise-04.php)

---

## 📢 نکته پایانی

دستورات شرطی **اساس منطق برنامه‌نویسی** هستند. با استفاده از آن‌ها می‌توانید برنامه‌هایی بنویسید که تصمیمات هوشمندانه‌ای بگیرند.

**نکته مهم:** همیشه شرایط خود را به دقت بررسی کنید و از اشتباهات رایج مانند فراموش کردن `break` در `switch` یا استفاده از `=` به جای `==` خودداری کنید.

در جلسه بعدی، با **حلقه‌ها (Loops)** آشنا خواهیم شد! 🚀
