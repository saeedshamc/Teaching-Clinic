<?php
// مثال ۱: ساده
$age = 25;
$status = ($age >= 18) ? "Adult" : "Minor";
echo "Status: $status\n";

// مثال ۲: چاپ مستقیم
$number = 10;
echo "The number is " . ($number % 2 === 0 ? "even" : "odd") . "\n";

// مثال ۳: عملگر سه‌تایی تو در تو
$score = 85;
$grade = ($score >= 90) ? "A" : 
        (($score >= 80) ? "B" : 
        (($score >= 70) ? "C" : 
        (($score >= 60) ? "D" : "F")));
echo "Grade: $grade\n";

// مثال ۴: با echo
$isLoggedIn = true;
echo $isLoggedIn ? "Welcome back!" : "Please log in.";
?>
