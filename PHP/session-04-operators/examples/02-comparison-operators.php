<?php
$a = 10;
$b = "10";
$c = 5;

// عملگرهای مساوی
echo "a == b: " . var_export($a == $b, true) . "\n";    // true (مقادیر مساوی هستند)
echo "a === b: " . var_export($a === $b, true) . "\n";  // false (مقادیر مساوی اما انواع متفاوت)

// عملگرهای نامساوی
echo "a != b: " . var_export($a != $b, true) . "\n";    // false
echo "a !== b: " . var_export($a !== $b, true) . "\n";  // true

// عملگرهای مقایسه‌ای
$num1 = 10;
$num2 = 20;
echo "num1 < num2: " . var_export($num1 < $num2, true) . "\n";   // true
echo "num1 > num2: " . var_export($num1 > $num2, true) . "\n";   // false
echo "num1 <= num2: " . var_export($num1 <= $num2, true) . "\n"; // true

// عملگر فضاپیمایی (Spaceship)
// اگر $a < $b: -1
// اگر $a == $b: 0
// اگر $a > $b: 1
$spaceship = $num1 <=> $num2;
echo "Spaceship (10 <=> 20): $spaceship\n";  // -1

$spaceship2 = 20 <=> 10;
echo "Spaceship (20 <=> 10): $spaceship2\n"; // 1

$spaceship3 = 10 <=> 10;
echo "Spaceship (10 <=> 10): $spaceship3\n"; // 0
?>
