<?php
// اعداد اعشاری
$pi = 3.14159;
$negativeFloat = -0.5;
$scientific = 1.5e3;  // 1.5 * 10^3 = 1500

// چاپ اعداد اعشاری
echo "Pi: $pi\n";
echo "Negative float: $negativeFloat\n";
echo "Scientific: $scientific\n";

// دقت اعداد اعشاری
$sum = 0.1 + 0.2;
echo "0.1 + 0.2 = $sum\n";  // ممکن است دقیقاً 0.3 نباشد

// بررسی اینکه آیا یک عدد، عدد اعشاری است
$number = 3.14;
if (is_float($number)) {
    echo "This is a float number.\n";
}
?>
