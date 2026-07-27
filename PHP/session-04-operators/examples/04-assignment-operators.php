<?php
$a = 10;
$b = 5;

// عملگرهای جمع و تفریق
$a += $b;  // $a = $a + $b
echo "a += b: $a\n";  // 15

$a -= $b;  // $a = $a - $b
echo "a -= b: $a\n";  // 10

// عملگرهای ضرب و تقسیم
$a *= $b;  // $a = $a * $b
echo "a *= b: $a\n";  // 50

$a /= $b;  // $a = $a / $b
echo "a /= b: $a\n";  // 10

// عملگر باقیمانده
$a %= $b;  // $a = $a % $b
echo "a %= b: $a\n";  // 0

// عملگر اتصال رشته
$str1 = "Hello";
$str2 = " World";
$str1 .= $str2;  // $str1 = $str1 . $str2
echo "String concatenation: $str1\n";  // "Hello World"
?>
