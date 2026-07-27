<?php
// مثال ۱: بررسی سن و مجوز
$age = 25;
$hasLicense = true;

if ($age >= 18) {
    if ($hasLicense) {
        echo "You can drive.\n";
    } else {
        echo "You are old enough but don't have a license.\n";
    }
} else {
    echo "You are too young to drive.\n";
}

// مثال ۲: بررسی ورود و نقش کاربر
$isLoggedIn = true;
$isAdmin = false;

if ($isLoggedIn) {
    if ($isAdmin) {
        echo "Welcome, Admin!\n";
    } else {
        echo "Welcome, User!\n";
    }
} else {
    echo "Please log in.\n";
}

// مثال ۳: بررسی نمره و حضور
$score = 85;
$attendance = 90;

if ($attendance >= 80) {
    if ($score >= 90) {
        echo "Grade: A (Excellent attendance and score)\n";
    } elseif ($score >= 80) {
        echo "Grade: B (Good attendance and score)\n";
    } else {
        echo "Grade: C (Good attendance but average score)\n";
    }
} else {
    echo "Failed due to low attendance.\n";
}
?>
