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
