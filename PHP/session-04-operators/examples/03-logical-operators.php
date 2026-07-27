<?php
$a = true;
$b = false;
$c = true;

// عملگر AND (&&)
echo "a && b: " . var_export($a && $b, true) . "\n";  // false
echo "a && c: " . var_export($a && $c, true) . "\n";  // true

// عملگر OR (||)
echo "a || b: " . var_export($a || $b, true) . "\n";  // true
echo "b || b: " . var_export($b || $b, true) . "\n";  // false

// عملگر NOT (!)
echo "!a: " . var_export(!$a, true) . "\n";    // false
echo "!b: " . var_export(!$b, true) . "\n";    // true

// عملگر XOR (xor)
echo "a xor b: " . var_export($a xor $b, true) . "\n";  // true
echo "a xor c: " . var_export($a xor $c, true) . "\n";  // false

// مثال عملی: بررسی سن و مجوز
$age = 25;
$hasLicense = true;
$canDrive = ($age >= 18) && $hasLicense;
echo "Can drive: " . var_export($canDrive, true) . "\n";  // true

// بررسی ورود به سایت
$isLoggedIn = false;
$isAdmin = true;
$canAccess = $isLoggedIn || $isAdmin;
echo "Can access: " . var_export($canAccess, true) . "\n";  // true
?>
