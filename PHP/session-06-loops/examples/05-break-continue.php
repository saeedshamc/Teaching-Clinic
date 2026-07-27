<?php
// مثال ۱: خروج از حلقه با break
for ($i = 1; $i <= 10; $i++) {
    if ($i === 6) {
        break;  // خروج از حلقه زمانی که $i برابر 6 باشد
    }
    echo $i . "\n";
}
// خروجی: 1, 2, 3, 4, 5

// مثال ۲: جستجوی یک عنصر در آرایه
$numbers = [10, 20, 30, 40, 50];
$search = 30;
$found = false;

foreach ($numbers as $number) {
    if ($number === $search) {
        $found = true;
        break;  // خروج از حلقه پس از پیدا کردن
    }
}

echo $found ? "Found!\n" : "Not found!\n";

// مثال ۳: break در حلقه تو در تو
for ($i = 1; $i <= 3; $i++) {
    for ($j = 1; $j <= 3; $j++) {
        if ($i === 2 && $j === 2) {
            break;  // تنها از حلقه داخلی خارج می‌شود
        }
        echo "i: $i, j: $j\n";
    }
}

// مثال ۴: رد کردن اعداد زوج
for ($i = 1; $i <= 10; $i++) {
    if ($i % 2 === 0) {
        continue;  // رد کردن اعداد زوج
    }
    echo $i . "\n";
}
// خروجی: 1, 3, 5, 7, 9

// مثال ۵: رد کردن مقادیر خاص در آرایه
$numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
foreach ($numbers as $number) {
    if ($number % 3 === 0) {
        continue;  // رد کردن اعدادی که بر ۳ بخش‌پذیر هستند
    }
    echo $number . "\n";
}
// خروجی: 1, 2, 4, 5, 7, 8, 10

// مثال ۶: continue در حلقه تو در تو
for ($i = 1; $i <= 3; $i++) {
    for ($j = 1; $j <= 3; $j++) {
        if ($j === 2) {
            continue;  // رد کردن تکرار جاری حلقه داخلی
        }
        echo "i: $i, j: $j\n";
    }
}
?>
