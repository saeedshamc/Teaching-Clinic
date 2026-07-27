# جلسه ۰۲: اولین اسکریپت PHP — تگ‌ها، echo، print و کامنت‌ها

## 🎯 اهداف یادگیری

در پایان این جلسه، شما خواهید توانست:
- ساختار کامل یک فایل PHP را درک کنید
- از تگ‌های مختلف PHP استفاده کنید
- تفاوت بین `echo` و `print` را بشناسید
- از کامنت‌ها برای توضیح کدهای خود استفاده کنید
- اسکریپت‌های ساده PHP بنویسید و اجرا کنید

---

## 📝 ساختار یک فایل PHP

یک فایل PHP می‌تواند شامل ترکیبی از کدهای PHP، HTML، JavaScript و CSS باشد. کدهای PHP **حتماً** باید داخل **تگ‌های PHP** قرار بگیرند.

### انواع تگ‌های PHP

#### ۱. تگ‌های استاندارد (پیشنهادی)
```php
<?php
    // کدهای PHP اینجا نوشته می‌شوند
    echo "Hello, World!";
?>
```

#### ۲. تگ‌های کوتاه (Short Open Tags)
```php
<?= "Hello, World!" ?>
```

**توجه:**
- تگ‌های کوتاه (`<?= ?>`) برای چاپ سریع استفاده می‌شوند.
- استفاده از تگ‌های کوتاه نیاز به فعال بودن `short_open_tag` در تنظیمات PHP دارد.
- **پیشنهاد:** همیشه از تگ‌های استاندارد (`<?php ?>`) استفاده کنید.

#### ۳. تگ‌های ASP (منسوخ)
```php
<%
    echo "Hello, World!";
%>
```
- این تگ‌ها منسوخ شده‌اند و استفاده از آن‌ها توصیه نمی‌شود.

---

## 🔤 دستورات چاپ: `echo` و `print`

### تفاوت بین `echo` و `print`

| ویژگی | `echo` | `print` |
|--------|--------|---------|
| نوع | زبان ساخت (Language Construct) | تابع (Function) |
| مقدار بازگشتی | ندارد | همیشه `1` برمی‌گرداند |
| سرعت | کمی سریع‌تر | کمی کندتر |
| استفاده در عبارت‌ها | نمی‌توان در عبارت‌ها استفاده کرد | می‌توان در عبارت‌ها استفاده کرد |
| آرگومان‌ها | می‌تواند چندین آرگومان بگیرد | تنها یک آرگومان می‌گیرد |

### مثال‌های `echo`

فایل: [`examples/01-echo-examples.php`](./examples/01-echo-examples.php)

```php
<?php
// مثال ۱: چاپ یک رشته
echo "Hello, World!\n";

// مثال ۲: چاپ چندین رشته (با ویرگول جدا شده)
echo "Hello", " ", "World!", "\n";

// مثال ۳: چاپ متغیرها
$name = "Ali";
echo "Hello, $name!\n";  // خروجی: Hello, Ali!

// مثال ۴: چاپ با استفاده از متغیرهای داخل رشته
$language = "PHP";
echo "I am learning $language\n";  // خروجی: I am learning PHP

// مثال ۵: چاپ آرایه‌ها (با استفاده از print_r)
$fruits = ["Apple", "Banana", "Orange"];
echo "My fruits: ";
print_r($fruits);  // چاپ آرایه به صورت خوانا
?>
```

**توضیح کد:**
- `echo` می‌تواند چندین آرگومان را با ویرگول جدا شده چاپ کند.
- متغیرها داخل رشته‌های دو کوته (`""`) گسترش می‌یابند.
- برای چاپ آرایه‌ها از `print_r()` استفاده می‌کنیم.

---

### مثال‌های `print`

فایل: [`examples/02-print-examples.php`](./examples/02-print-examples.php)

```php
<?php
// مثال ۱: چاپ یک رشته
print "Hello, World!\n";

// مثال ۲: چاپ متغیر
$name = "Reza";
print "Hello, $name!\n";

// مثال ۳: استفاده از print در عبارت‌ها
$result = print "This will be printed\n";
echo "Return value of print: $result\n";  // خروجی: Return value of print: 1

// مثال ۴: چاپ با فرمت
$price = 1250;
print "Price: $price Toman\n";
?>
```

**توضیح کد:**
- `print` همیشه `1` برمی‌گرداند، بنابراین می‌توان از آن در عبارت‌ها استفاده کرد.
- `print` تنها یک آرگومان می‌گیرد.

---

## 💬 کامنت‌ها در PHP

کامنت‌ها برای توضیح کدها استفاده می‌شوند و توسط مفسر PHP نادیده گرفته می‌شوند.

### انواع کامنت‌ها

#### ۱. کامنت یک‌خطی (Single-line Comment)
```php
// این یک کامنت یک‌خطی است
echo "Hello";  // کامنت بعد از کد
```

#### ۲. کامنت چندخطی (Multi-line Comment)
```php
/*
   این یک کامنت
   چندخطی است
*/
echo "Hello";
```

#### ۳. کامنت برای مستندسازی (DocBlock)
```php
/**
 * این یک تابع برای چاپ سلام است
 * 
 * @param string $name نام کاربر
 * @return void
 */
function sayHello($name) {
    echo "Hello, $name!";
}
```

**توجه:**
- از کامنت‌ها برای توضیح **چرا** کد نوشته شده استفاده کنید، نه **چه کاری** انجام می‌دهد.
- کدهای خود را بیش از حد کامنت‌گذاری نکنید؛ کدهای تمیز نیاز به کامنت کمتری دارند.

---

### مثال کامل: استفاده از تمام مفاهیم

فایل: [`examples/03-complete-example.php`](./examples/03-complete-example.php)

```php
<?php
// این یک اسکریپت کامل PHP است

// تعریف متغیرها
$firstName = "Ali";
$lastName = "Rezaei";
$age = 25;

// چاپ اطلاعات با استفاده از echo
echo "=== User Information ===\n";
echo "First Name: $firstName\n";
echo "Last Name: $lastName\n";
echo "Age: $age\n";

// چاپ با استفاده از print
print "\n=== Using print ===\n";
print "Full Name: $firstName $lastName\n";

/*
   این بخش یک کامنت چندخطی است
   برای توضیح محاسبه سن در سال آینده
*/
$nextYearAge = $age + 1;
echo "\nNext year you will be: $nextYearAge\n";

// چاپ با استفاده از تگ کوتاه (اگر فعال باشد)
?>

<!-- خارج از تگ PHP، کدهای HTML قرار می‌گیرند -->
<p>This is HTML outside PHP tags.</p>

<?php
// بازگشت به PHP
echo "\nBack to PHP!";
?>
```

---

## ⚠️ نکات رایج و اشتباهات

### اشتباهات رایج

#### ۱. فراموش کردن تگ‌های بسته PHP
```php
<?php
    echo "Hello";
    // فراموش کردن ?>
```
- این کار اشکالی ندارد، اما اگر بعد از آن کد HTML داشته باشید، با خطا مواجه می‌شوید.

#### ۲. استفاده از `;` در پایان تگ PHP
```php
<?php echo "Hello"; ?>
<!-- اشتباه: ؛ بعد از ?> -->
```
- این کار باعث خطا می‌شود. تگ `?>` باید آخرین کاراکتر در خط باشد.

#### ۳. استفاده از متغیرهای تعریف‌نشده
```php
<?php
    echo $name;  // خطا: متغیر $name تعریف نشده است
?>
```
- همیشه قبل از استفاده از متغیرها، آن‌ها را تعریف کنید.

#### ۴. فراموش کردن کوته‌ها برای رشته‌ها
```php
<?php
    echo 'Hello, $name!';  // خروجی: Hello, $name!
    echo "Hello, $name!"; // خروجی: Hello, Ali! (اگر $name = "Ali")
?>
```
- متغیرها تنها داخل رشته‌های **دو کوته** (`""`) گسترش می‌یابند.

### بهترین شیوه‌ها

1. **همیشه از تگ‌های استاندارد استفاده کنید**:
   ```php
   <?php ... ?>  // خوب
   <?= ... ?>    // فقط برای چاپ سریع
   ```

2. **از کامنت‌های معنی‌دار استفاده کنید**:
   ```php
   // بد: کامنت واضح
   $x = 5; // عدد ۵ را به x اختصاص می‌دهد
   
   // خوب: کامنت معنی‌دار
   $maxRetryCount = 5; // حداکثر تعداد تلاش مجدد
   ```

3. **برای چاپ چندین رشته، از echo استفاده کنید**:
   ```php
   echo "Hello", " ", "World!";  // خوب
   print "Hello"; print " World!"; // بد
   ```

4. **برای چاپ متغیرها در رشته‌ها، از رشته‌های دو کوته استفاده کنید**:
   ```php
   echo "Hello, $name";  // خوب
   echo 'Hello, ' . $name; // قابل قبول، اما کمتر خوانا
   ```

---

## 📝 تمرین‌های عملی

### تمرین ۱: چاپ اطلاعات شخصی

**صورت تمرین:**
یک اسکریپت PHP بنویسید که اطلاعات شخصی زیر را چاپ کند:
- نام و نام خانوادگی
- سن
- شهر محل سکونت
- زبان برنامه‌نویسی مورد علاقه

**مثال خروجی:**
```
Name: Ali Rezaei
Age: 25
City: Tehran
Favorite Language: PHP
```

**فایل تمرین:** [`exercises/exercise-01.php`](./exercises/exercise-01.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۱: چاپ اطلاعات شخصی
$firstName = "Ali";
$lastName = "Rezaei";
$age = 25;
$city = "Tehran";
$favoriteLanguage = "PHP";

echo "Name: $firstName $lastName\n";
echo "Age: $age\n";
echo "City: $city\n";
echo "Favorite Language: $favoriteLanguage\n";
?>
```
</details>

---

### تمرین ۲: استفاده از echo و print

**صورت تمرین:**
یک اسکریپت PHP بنویسید که:
1. با استفاده از `echo`، پیام "Learning PHP is fun!" را چاپ کند.
2. با استفاده از `print`، پیام "I love coding!" را چاپ کند.
3. با استفاده از `echo`، هر دو پیام را در یک خط چاپ کند (با فاصله بین آن‌ها).

**مثال خروجی:**
```
Learning PHP is fun!
I love coding!
Learning PHP is fun! I love coding!
```

**فایل تمرین:** [`exercises/exercise-02.php`](./exercises/exercise-02.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تمرین ۲: استفاده از echo و print
echo "Learning PHP is fun!\n";
print "I love coding!\n";
echo "Learning PHP is fun! I love coding!\n";
?>
```
</details>

---

### تمرین ۳: کامنت‌گذاری کد

**صورت تمرین:**
کد زیر را با کامنت‌های مناسب توضیح دهید:
```php
<?php
$radius = 5;
$pi = 3.14;
$area = $pi * $radius * $radius;
echo "Area of circle: $area";
?>
```

**راهنمایی:**
- برای هر خط کد، یک کامنت بنویسید که توضیح دهد آن خط چه کاری انجام می‌دهد.
- از کامنت‌های یک‌خطی (`//`) استفاده کنید.

**فایل تمرین:** [`exercises/exercise-03.php`](./exercises/exercise-03.php)

<details>
<summary>🔍 نمایش راه‌حل</summary>

```php
<?php
// تعریف شعاع دایره
$radius = 5;

// تعریف عدد پی
$pi = 3.14;

// محاسبه مساحت دایره (فرمول: π * r^2)
$area = $pi * $radius * $radius;

// چاپ نتیجه
$area = $pi * $radius * $radius;
echo "Area of circle: $area";
?>
```
</details>

---

## 📌 خلاصه جلسه

در این جلسه یاد گرفتیم:
✅ ساختار کامل یک فایل PHP
✅ انواع تگ‌های PHP و تفاوت آن‌ها
✅ تفاوت بین `echo` و `print`
✅ استفاده از کامنت‌ها برای توضیح کدها
✅ نوشتن اسکریپت‌های ساده PHP

---

## 🔗 لینک به فایل‌های کد

- [مثال ۱: echo Examples](./examples/01-echo-examples.php)
- [مثال ۲: print Examples](./examples/02-print-examples.php)
- [مثال ۳: Complete Example](./examples/03-complete-example.php)
- [تمرین ۱: چاپ اطلاعات شخصی](./exercises/exercise-01.php)
- [تمرین ۲: استفاده از echo و print](./exercises/exercise-02.php)
- [تمرین ۳: کامنت‌گذاری کد](./exercises/exercise-03.php)

---

## 📢 نکته پایانی

در این جلسه با مبانی نوشتن کدهای PHP آشنا شدید. **echo** و **print** دو دستور اساسی برای چاپ خروجی هستند که در تمام اسکریپت‌های PHP از آن‌ها استفاده می‌شود. کامنت‌ها نیز برای خوانایی و نگهداری کدها بسیار مهم هستند.

در جلسه بعدی، با **متغیرها و انواع داده** در PHP آشنا خواهیم شد! 🚀
