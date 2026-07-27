<?php
// مثال ۱: چاپ یک رشته
echo "Hello, World!\n";

// مثال ۲: چاپ چندین رشته (با ویرگول جدا شده)
echo "Hello", " ", "World!", "\n";

// مثال ۳: چاپ متغیرها
$name = "Ali";
echo "Hello, $name!\n";  // خروجی: Hello, Ali!

// مثال ۴: چاپ با استفاده از متغیرهای داخل رشته
$language = "PHP";
echo "I am learning $language\n";  // خروجی: I am learning PHP

// مثال ۵: چاپ آرایه‌ها (با استفاده از print_r)
$fruits = ["Apple", "Banana", "Orange"];
echo "My fruits: ";
print_r($fruits);  // چاپ آرایه به صورت خوانا
?>
