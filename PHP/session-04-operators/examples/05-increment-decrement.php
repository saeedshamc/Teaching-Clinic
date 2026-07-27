<?php
// پیش‌افزایش و پس‌افزایش
$a = 5;
echo "Initial a: $a\n";

$b = ++$a;  // اول $a را 1 افزایش می‌دهد، سپس $b = $a
echo "After ++a: a = $a, b = $b\n";  // a = 6, b = 6

$c = $a++;  // اول $c = $a، سپس $a را 1 افزایش می‌دهد
echo "After a++: a = $a, c = $c\n";  // a = 7, c = 6

// پیش‌کاهش و پس‌کاهش
$d = 10;
echo "\nInitial d: $d\n";

$e = --$d;  // اول $d را 1 کاهش می‌دهد، سپس $e = $d
echo "After --d: d = $d, e = $e\n";  // d = 9, e = 9

$f = $d--;  // اول $f = $d، سپس $d را 1 کاهش می‌دهد
echo "After d--: d = $d, f = $f\n";  // d = 8, f = 9

// استفاده در آرایه‌ها
$numbers = [1, 2, 3];
$index = 0;
echo "\nArray example:\n";
echo "numbers[" . $index++ . "]: " . $numbers[$index - 1] . "\n";  // index = 0, سپس 1
echo "numbers[" . $index++ . "]: " . $numbers[$index - 1] . "\n";  // index = 1, سپس 2
?>
