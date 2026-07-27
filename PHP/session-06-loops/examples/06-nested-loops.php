<?php
// مثال ۱: چاپ جدول ضرب
for ($i = 1; $i <= 10; $i++) {
    for ($j = 1; $j <= 10; $j++) {
        echo "$i x $j = " . ($i * $j) . "\t";
    }
    echo "\n";
}

// مثال ۲: پیمایش آرایه چندبعدی
$matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
];

foreach ($matrix as $row) {
    foreach ($row as $value) {
        echo $value . " ";
    }
    echo "\n";
}

// مثال ۳: تولید ترکیبات
$colors = ["Red", "Green", "Blue"];
$sizes = ["S", "M", "L"];

foreach ($colors as $color) {
    foreach ($sizes as $size) {
        echo "$color $size\n";
    }
}

// مثال ۴: استفاده از break در حلقه تو در تو
for ($i = 1; $i <= 5; $i++) {
    for ($j = 1; $j <= 5; $j++) {
        if ($i * $j > 10) {
            break;  // خروج از حلقه داخلی
        }
        echo "i: $i, j: $j\n";
    }
}
?>
