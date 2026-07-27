<?php
// مقادیر بولین
$isTrue = true;
$isFalse = false;

// چاپ مقادیر بولین
echo "True value: " . ($isTrue ? "Yes" : "No") . "\n";
echo "False value: " . ($isFalse ? "Yes" : "No") . "\n";

// مقادیر درستی/نادرستی در PHP
// در PHP، مقادیر زیر به عنوان false در نظر گرفته می‌شوند:
// - بولین false
// - عدد 0
// - رشته خالی ""
// - آرایه خالی []
// - NULL

$values = [false, 0, "", [], null];
foreach ($values as $value) {
    echo "Value: " . var_export($value, true) . " is " . ($value ? "true" : "false") . "\n";
}
?>
