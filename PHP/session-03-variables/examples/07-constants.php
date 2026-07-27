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
?>
