<?php
// تعریف متغیرها
$a = 10;
$b = 3;

// عملگرهای پایه
echo "Addition: " . ($a + $b) . "\n";      // 13
echo "Subtraction: " . ($a - $b) . "\n";   // 7
echo "Multiplication: " . ($a * $b) . "\n"; // 30
echo "Division: " . ($a / $b) . "\n";      // 3.333...
echo "Modulus: " . ($a % $b) . "\n";       // 1

// عملگر توان
echo "Exponentiation: " . ($a ** $b) . "\n"; // 1000 (10^3)

// ترکیب عملگرها
$c = 5;
$result = ($a + $b) * $c;
echo "Combined: " . $result . "\n";  // (10 + 3) * 5 = 65

// تقسیم با دقت اعشاری
$division = 10 / 3;
echo "Precision division: " . number_format($division, 2) . "\n"; // 3.33
?>
