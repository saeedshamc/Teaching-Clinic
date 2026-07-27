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
