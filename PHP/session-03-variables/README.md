# جلسه ۰۳: متغیرها و انواع داده در PHP

## 🎯 اهداف یادگیری

در پایان این جلسه، شما خواهید توانست:
- متغیرها را در PHP تعریف و استفاده کنید
- انواع داده پایه در PHP را بشناسید
- بین انواع داده تبدیل نوع (Type Casting) انجام دهید
- ثابت‌ها (Constants) را تعریف و استفاده کنید
- با متغیرهای پیش‌تعریف شده PHP آشنا شوید

---

## 📦 متغیرها در PHP

### تعریف متغیر

در PHP، متغیرها با علامت **`$`** شروع می‌شوند و نیازی به تعریف نوع ندارند.

```php
$variableName = value;
```

**قوانین نام‌گذاری متغیرها:**
1. باید با حرف یا `_` شروع شوند
2. می‌توانند شامل حروف، اعداد و `_` باشند
3. به حروف بزرگ/کوچک حساس هستند (`$name` ≠ `$Name`)
4. نمی‌توانند از کلمات کلیدی PHP باشند (مثل `echo`, `if`, `else`)

### مثال‌های تعریف متغیر

فایل: [`examples/01-variable-declaration.php`](./examples/01-variable-declaration.php)

```php
<?php
// تعریف متغیرهای مختلف
$name = "Ali";          // رشته (String)
$age = 25;              // عدد صحیح (Integer)
$height = 175.5;        // عدد اعشاری (Float)
$isStudent = true;      // بولین (Boolean)

// چاپ متغیرها
echo "Name: $name\n";
echo "Age: $age\n";
echo "Height: $height cm\n";
echo "Is Student: " . ($isStudent ? "Yes" : "No") . "\n";
?>
```

**توضیح کد:**
- `$name`: متغیر رشته‌ای (String)
- `$age`: متغیر عددی صحیح (Integer)
- `$height`: متغیر عددی اعشاری (Float)
- `$isStudent`: متغیر بولین (Boolean)
- عملگر سه‌تایی (`?:`) برای چاپ مقادیر بولین استفاده شده است

---

## 🏷️ انواع داده در PHP

PHP از **۸ نوع داده اصلی** پشتیبانی می‌کند:

| نوع داده | مثال | توضیح |
|-----------|------|--------|
| String | `"Hello"` | رشته کاراکترها |
| Integer | `42` | اعداد صحیح |
| Float | `3.14` | اعداد اعشاری |
| Boolean | `true`, `false` | مقادیر درستی/نادرستی |
| Array | `[1, 2, 3]` | آرایه |
| Object | `new ClassName()` | شیء |
| NULL | `null` | بدون مقدار |
| Resource | - | منابع خارجی (مثل اتصال به دیتابیس) |

### ۱. رشته‌ها (Strings)

فایل: [`examples/02-strings.php`](./examples/02-strings.php)

```php
<?php
// رشته‌های ساده
$singleQuoted = 'This is a single quoted string';
$doubleQuoted = "This is a double quoted string";

// تفاوت بین رشته‌های تک کوته و دو کوته
$name = "Ali";
echo 'Hello, $name!\n';   // خروجی: Hello, $name!
echo "Hello, $name!\n";  // خروجی: Hello, Ali!

// رشته‌های چندخطی (با استفاده از NOWDOC و HEREDOC)
$heredoc = <<<EOD
This is a heredoc string.
It can span multiple lines.
Variables like $name are interpolated.
EOD;

echo $heredoc . "\n";

$nowdoc = <<<'EOD'
This is a nowdoc string.
It can span multiple lines.
Variables like $name are NOT interpolated.
EOD;

echo $nowdoc . "\n";
?>
```

**توضیح کد:**
- رشته‌های **تک کوته** (`' '`) متغیرها را گسترش نمی‌دهند.
- رشته‌های **دو کوته** (`" "`) متغیرها را گسترش می‌دهند.
- **HEREDOC** (`<<<EOD`) برای رشته‌های چندخطی با گسترش متغیرها.
- **NOWDOC** (`<<<'EOD'`) برای رشته‌های چندخطی بدون گسترش متغیرها.

---

### ۲. اعداد صحیح (Integers)

فایل: [`examples/03-integers.php`](./examples/03-integers.php)

```php
<?php
// اعداد صحیح
$positive = 42;
$negative = -10;
$zero = 0;

// نمایش اعداد در سیستم‌های عددی مختلف
echo "Decimal: $positive\n";
echo "Binary: " . decbin($positive) . "\n";    // تبدیل به باینری
echo "Hexadecimal: " . dechex($positive) . "\n"; // تبدیل به هگزا

// اعداد بزرگ
$bigNumber = 1_000_000;  // استفاده از _ برای خوانایی بهتر
echo "Big number: $bigNumber\n";

// محدوده اعداد صحیح در PHP
// PHP به صورت خودکار بین Integer و Float تبدیل می‌کند
echo "Max integer: " . PHP_INT_MAX . "\n";
?>
```

**توضیح کد:**
- اعداد صحیح می‌توانند مثبت، منفی یا صفر باشند.
- از `_` برای جدا کردن رقم‌ها در اعداد بزرگ استفاده می‌شود (PHP 7.4+).
- `PHP_INT_MAX`: حداکثر مقدار عدد صحیح در سیستم.

---

### ۳. اعداد اعشاری (Floats)

فایل: [`examples/04-floats.php`](./examples/04-floats.php)

```php
<?php
// اعداد اعشاری
$pi = 3.14159;
$negativeFloat = -0.5;
$scientific = 1.5e3;  // 1.5 * 10^3 = 1500

// چاپ اعداد اعشاری
echo "Pi: $pi\n";
echo "Negative float: $negativeFloat\n";
echo "Scientific: $scientific\n";

// دقت اعداد اعشاری
$sum = 0.1 + 0.2;
echo "0.1 + 0.2 = $sum\n";  // ممکن است دقیقاً 0.3 نباشد

// بررسی اینکه آیا یک عدد، عدد اعشاری است
$number = 3.14;
if (is_float($number)) {
    echo "This is a float number.\n";
}
?>
```

**توضیح کد:**
- اعداد اعشاری می‌توانند شامل اعشار یا نماد علمی باشند.
- اعداد اعشاری همیشه دقیق نیستند (به دلیل نحوه ذخیره‌سازی در کامپیوتر).
- `is_float()`: برای بررسی اینکه آیا یک متغیر از نوع Float است.

---

### ۴. بولین (Booleans)

فایل: [`examples/05-booleans.php`](./examples/05-booleans.php)

```php
<?php
// مقادیر بولین
$isTrue = true;
$isFalse = false;

// چاپ مقادیر بولین
echo "True value: " . ($isTrue ? "Yes" : "No") . "\n";
echo "False value: " . ($isFalse ? "Yes" : "No") . "\n";

// مقادیر درستی/نادرستی در PHP
// در PHP، مقادیر زیر به عنوان false در نظر گرفته می‌شوند:
// - بولین false
// - عدد 0
// - رشته خالی ""
// - آرایه خالی []
// - NULL

$values = [false, 0, "", [], null];
foreach ($values as $value) {
    echo "Value: " . var_export($value, true) . " is " . ($value ? "true" : "false") . "\n";
}
?>
```

**توضیح کد:**
- مقادیر بولین تنها می‌توانند `true` یا `false` باشند.
- در PHP، مقادیر خاصی به صورت خودکار به `false` تبدیل می‌شوند.
- `var_export()`: برای نمایش قابل خواندن مقادیر.

---

## 🔄 تبدیل نوع (Type Casting)

PHP به صورت خودکار بین انواع داده تبدیل می‌کند، اما می‌توانید به صورت صریح نیز تبدیل نوع انجام دهید.

فایل: [`examples/06-type-casting.php`](./examples/06-type-casting.php)

```php
<?php
// تبدیل نوع صریح
$string = "123";
$integer = (int)$string;    // تبدیل به عدد صحیح
echo "String to int: $integer\n";

$float = 3.14;
$int = (int)$float;         // تبدیل به عدد صحیح (برش داده می‌شود)
echo "Float to int: $int\n";

$number = 42;
$str = (string)$number;     // تبدیل به رشته
echo "Number to string: $str\n";

// تبدیل نوع خودکار
$autoCast = "5" + 3;        // به صورت خودکار به عدد تبدیل می‌شود
echo "Auto cast: $autoCast\n";  // خروجی: 8

// توابع تبدیل نوع
$numStr = "123.45";
$floatVal = floatval($numStr);
echo "String to float: $floatVal\n";

$intVal = intval("42");
echo "String to int: $intVal\n";

// بررسی نوع متغیر
$var = 42;
echo "Type of \$var: " . gettype($var) . "\n";
?>
```

**توضیح کد:**
- `(type)`: سینتکس تبدیل نوع صریح.
- `intval()`, `floatval()`: توابع تبدیل نوع.
- `gettype()`: برای دریافت نوع متغیر.

---

## 🔒 ثابت‌ها (Constants)

ثابت‌ها (Constants) مانند متغیرها هستند، اما پس از تعریف، نمی‌توان آن‌ها را تغییر داد.

فایل: [`examples/07-constants.php`](./examples/07-constants.php)

```php
<?php
// تعریف ثابت با استفاده از define()
define("PI", 3.14159);
define("SITE_NAME", "My Website");

// چاپ ثابت‌ها
echo "PI: " . PI . "\n";
echo "Site Name: " . SITE_NAME . "\n";

// تعریف ثابت با استفاده از const (در سطح کلاس یا خارج از توابع)
const MAX_USERS = 100;
echo "Max Users: " . MAX_USERS . "\n";

// ثابت‌های از پیش تعریف شده در PHP
echo "PHP Version: " . PHP_VERSION . "\n";
echo "OS: " . PHP_OS . "\n";

// بررسی اینکه آیا یک ثابت تعریف شده است
if (defined("PI")) {
    echo "PI is defined.\n";
}

// ثابت‌های حساس به حروف بزرگ/کوچک
define("CASE_SENSITIVE", "Yes", true);  // پارامتر سوم: حساس به حروف
case_sensitive: // این کار می‌کند
CASE_SENSITIVE: // این هم کار می‌کند (به دلیل پارامتر سوم true)
?>
```

**توضیح کد:**
- `define()`: تابع برای تعریف ثابت‌ها.
- `const`: کلمه کلیدی برای تعریف ثابت‌ها (در PHP 5.3+).
- `defined()`: برای بررسی وجود یک ثابت.
- ثابت‌های پیش‌فرض PHP: `PHP_VERSION`, `PHP_OS`, و...

---

## 🌐 متغیرهای پیش‌تعریف شده (Predefined Variables)

PHP متغیرهای خاصی را به صورت خودکار تعریف می‌کند که اطلاعات مفیدی را در اختیار شما قرار می‌دهند.

فایل: [`examples/08-predefined-variables.php`](./examples/08-predefined-variables.php)

```php
<?php
// متغیرهای سراسری (Superglobals)
// این متغیرها در تمام scopeها در دسترس هستند

// 1. $_SERVER - اطلاعات سرور و محیط
echo "Server Name: " . ($_SERVER['SERVER_NAME'] ?? 'Not available') . "\n";
echo "Server Software: " . ($_SERVER['SERVER_SOFTWARE'] ?? 'Not available') . "\n";
echo "Current File: " . $_SERVER['PHP_SELF'] . "\n";

// 2. $_GET - متغیرهای ارسال شده از طریق URL (متد GET)
// مثال: http://localhost/test.php?name=Ali&age=25
// echo "Name: " . $_GET['name'] . "\n";
// echo "Age: " . $_GET['age'] . "\n";

// 3. $_POST - متغیرهای ارسال شده از طریق فرم (متد POST)
// در جلسات آینده بیشتر با این متغیر آشنا خواهیم شد

// 4. $_REQUEST - ترکیبی از $_GET, $_POST, $_COOKIE

// 5. $_COOKIE - کوکی‌های HTTP

// 6. $_SESSION - متغیرهای Session

// 7. $_FILES - فایل‌های آپلود شده

// 8. $_ENV - متغیرهای محیطی

// 9. $GLOBALS - تمام متغیرهای سراسری
?>
```

**توضیح کد:**
- متغیرهای سراسری (`$_SERVER`, `$_GET`, `$_POST`, و...) در تمام جاهای اسکریپت در دسترس هستند.
- عملگر `??` (Null Coalescing) برای بررسی وجود یک کلید در آرایه استفاده می‌شود.

---

## ⚠️ نکات رایج و اشتباهات

### اشتباهات رایج

#### ۱. فراموش کردن `$` برای متغیرها
```php
name = "Ali";   // اشتباه: بدون $
$name = "Ali";  // درست
```

#### ۲. استفاده از متغیرهای تعریف‌نشده
```php
$name = "Ali";
echo $Name;  // خطا: $Name تعریف نشده است (حساس به حروف)
```

#### ۳. استفاده نادرست از ثابت‌ها
```php
define("PI", 3.14);
PI = 3.14159;  // خطا: نمی‌توان ثابت‌ها را تغییر داد
```

#### ۴. اشتباه در تبدیل نوع
```php
$str = "123abc";
$num = (int)$str;  // خروجی: 123 (نه خطا)
echo $num;        // 123
```
- PHP به صورت خودکار تا جایی که ممکن است، رشته را به عدد تبدیل می‌کند.

#### ۵. استفاده از کلمات کلیدی به عنوان نام متغیر
```php
$echo = "test";   // بد: استفاده از کلمه کلیدی
$myEcho = "test"; // خوب
```

### بهترین شیوه‌ها

1. **نام‌گذاری معنی‌دار برای متغیرها**:
   ```php
   $n = 42;          // بد
   $userAge = 42;    // خوب
   ```

2. **استفاده از snake_case برای نام متغیرها**:
   ```php
   $user_name = "Ali";  // خوب
   $userName = "Ali";   // قابل قبول (camelCase)
   ```

3. **برای ثابت‌ها از UPPER_CASE استفاده کنید**:
   ```php
   define("MAX_USERS", 100);  // خوب
   define("max_users", 100);   // بد
   ```

4. **متغیرها را قبل از استفاده تعریف کنید**:
   ```php
   // بد
   echo $name;
   $name = "Ali";
   
   // خوب
   $name = "Ali";
   echo $name;
   ```

5. **از متغیرهای پیش‌تعریف شده با احتیاط استفاده کنید**:
   ```php
   // همیشه بررسی کنید که کلید وجود دارد
   $name = $_GET['name'] ?? 'Guest';
   ```

---

## 📝 تمرین‌های عملی

### تمرین ۱: تعریف و چاپ متغیرها

**صورت تمرین:**
یک اسکریپت PHP بنویسید که متغیرهای زیر را تعریف کند و چاپ کند:
- نام (String)
- سن (Integer)
- قد (Float)
- آیا دانشجو هستید؟ (Boolean)

**مثال خروجی:**
```
Name: Ali
Age: 25
Height: 175.5 cm
Is Student: Yes
```

**فایل تمرین:** [`exercises/exercise-01.php`](./exercises/exercise-01.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۱: تعریف و چاپ متغیرها
$name = "Ali";
$age = 25;
$height = 175.5;
$isStudent = true;

echo "Name: $name\n";
echo "Age: $age\n";
echo "Height: $height cm\n";
echo "Is Student: " . ($isStudent ? "Yes" : "No") . "\n";
?>
```
</details>

---

### تمرین ۲: تبدیل نوع

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. رشته "123" را به عدد صحیح تبدیل کند و چاپ کند.
2. عدد 3.14 را به رشته تبدیل کند و چاپ کند.
3. رشته "45.67" را به عدد اعشاری تبدیل کند و چاپ کند.
4. نوع هر کدام را با `gettype()` چاپ کند.

**مثال خروجی:**
```
String to int: 123 (type: integer)
Number to string: 3.14 (type: string)
String to float: 45.67 (type: double)
```

**فایل تمرین:** [`exercises/exercise-02.php`](./exercises/exercise-02.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۲: تبدیل نوع
$strNum = "123";
$intNum = (int)$strNum;
echo "String to int: $intNum (type: " . gettype($intNum) . ")\n";

$floatNum = 3.14;
$strFloat = (string)$floatNum;
echo "Number to string: $strFloat (type: " . gettype($strFloat) . ")\n";

$strFloatNum = "45.67";
$floatVal = (float)$strFloatNum;
echo "String to float: $floatVal (type: " . gettype($floatVal) . ")\n";
?>
```
</details>

---

### تمرین ۳: استفاده از ثابت‌ها

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. ثابت‌های زیر را تعریف کند:
   - `SITE_NAME` با مقدار "My PHP Site"
   - `MAX_USERS` با مقدار 100
   - `PI` با مقدار 3.14159
2. ثابت‌ها را چاپ کند.
3. بررسی کند که آیا ثابت `SITE_NAME` تعریف شده است.

**مثال خروجی:**
```
Site Name: My PHP Site
Max Users: 100
PI: 3.14159
SITE_NAME is defined.
```

**فایل تمرین:** [`exercises/exercise-03.php`](./exercises/exercise-03.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۳: استفاده از ثابت‌ها
define("SITE_NAME", "My PHP Site");
define("MAX_USERS", 100);
define("PI", 3.14159);

echo "Site Name: " . SITE_NAME . "\n";
echo "Max Users: " . MAX_USERS . "\n";
echo "PI: " . PI . "\n";

if (defined("SITE_NAME")) {
    echo "SITE_NAME is defined.\n";
}
?>
```
</details>

---

## 📌 خلاصه جلسه

در این جلسه یاد گرفتیم:
✅ متغیرها در PHP و قوانین نام‌گذاری آن‌ها
✅ انواع داده پایه: String, Integer, Float, Boolean
✅ تبدیل نوع (Type Casting) صریح و خودکار
✅ تعریف و استفاده از ثابت‌ها (Constants)
✅ متغیرهای پیش‌تعریف شده PHP

---

## 🔗 لینک به فایل‌های کد

- [مثال ۱: Variable Declaration](./examples/01-variable-declaration.php)
- [مثال ۲: Strings](./examples/02-strings.php)
- [مثال ۳: Integers](./examples/03-integers.php)
- [مثال ۴: Floats](./examples/04-floats.php)
- [مثال ۵: Booleans](./examples/05-booleans.php)
- [مثال ۶: Type Casting](./examples/06-type-casting.php)
- [مثال ۷: Constants](./examples/07-constants.php)
- [مثال ۸: Predefined Variables](./examples/08-predefined-variables.php)
- [تمرین ۱: تعریف و چاپ متغیرها](./exercises/exercise-01.php)
- [تمرین ۲: تبدیل نوع](./exercises/exercise-02.php)
- [تمرین ۳: استفاده از ثابت‌ها](./exercises/exercise-03.php)

---

## 📢 نکته پایانی

متغیرها و انواع داده **اساس** هر زبان برنامه‌نویسی هستند. در PHP، متغیرها بسیار انعطاف‌پذیر هستند و می‌توانند در طول اجرای اسکریپت نوع خود را تغییر دهند. با این حال، توصیه می‌شود که از این انعطاف‌پذیری با احتیاط استفاده کنید و سعی کنید نوع متغیرها را تا حد امکان ثابت نگه دارید.

در جلسه بعدی، با **عملگرها** در PHP آشنا خواهیم شد! 🚀
