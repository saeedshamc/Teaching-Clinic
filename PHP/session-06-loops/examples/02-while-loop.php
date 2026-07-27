<?php
// مثال ۱: چاپ اعداد ۱ تا ۱۰
$i = 1;
while ($i <= 10) {
    echo $i . "\n";
    $i++;
}

// مثال ۲: جمع اعداد ۱ تا ۱۰
$sum = 0;
$i = 1;
while ($i <= 10) {
    $sum += $i;
    $i++;
}
echo "Sum of 1 to 10: $sum\n";

// مثال ۳: خواندن داده تا زمانی که شرط برآورده شود
$numbers = [1, 2, 3, 4, 5];
$index = 0;
while ($index < count($numbers)) {
    echo "Number: " . $numbers[$index] . "\n";
    $index++;
}

// مثال ۴: حلقه بی‌نهایت (دقت کنید!)
// این کد را اجرا نکنید!
/*
$i = 1;
while (true) {
    echo $i . "\n";
    $i++;
}
*/

// مثال ۵: استفاده از break برای خروج از حلقه
$i = 1;
while (true) {
    echo $i . "\n";
    $i++;
    if ($i > 10) {
        break;  // خروج از حلقه
    }
}
?>
