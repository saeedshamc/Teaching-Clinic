<?php
// مثال ۱: پیمایش آرایه indexed
$fruits = ["Apple", "Banana", "Orange", "Mango"];
foreach ($fruits as $fruit) {
    echo "Fruit: $fruit\n";
}

// مثال ۲: پیمایش آرایه associative
$person = [
    "name" => "Ali",
    "age" => 25,
    "city" => "Tehran"
];

foreach ($person as $key => $value) {
    echo "$key: $value\n";
}

// مثال ۳: پیمایش آرایه چندبعدی
$users = [
    ["name" => "Ali", "age" => 25],
    ["name" => "Reza", "age" => 30],
    ["name" => "Sara", "age" => 22]
];

foreach ($users as $user) {
    echo "User: " . $user["name"] . ", Age: " . $user["age"] . "\n";
}

// مثال ۴: استفاده از index در آرایه indexed
$numbers = [10, 20, 30, 40];
foreach ($numbers as $index => $number) {
    echo "Index $index: $number\n";
}

// مثال ۵: تغییر مقادیر آرایه در حلقه
$prices = [100, 200, 300];
foreach ($prices as &$price) {
    $price *= 1.1;  // ۱۰% افزایش
}
unset($price);  // مهم: برای جلوگیری از مشکلات
print_r($prices);
?>
