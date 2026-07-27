<?php
// مثال ۱: درجه‌بندی نمره
$score = 85;

if ($score >= 90) {
    echo "Grade: A\n";
} elseif ($score >= 80) {
    echo "Grade: B\n";
} elseif ($score >= 70) {
    echo "Grade: C\n";
} elseif ($score >= 60) {
    echo "Grade: D\n";
} else {
    echo "Grade: F\n";
}

// مثال ۲: بررسی دامنه سن
$age = 35;

if ($age < 13) {
    echo "Child\n";
} elseif ($age < 20) {
    echo "Teenager\n";
} elseif ($age < 65) {
    echo "Adult\n";
} else {
    echo "Senior\n";
}

// مثال ۳: بررسی روز هفته
$day = "Wednesday";

if ($day === "Monday") {
    echo "Start of the week.\n";
} elseif ($day === "Friday") {
    echo "Almost weekend!\n";
} elseif ($day === "Saturday" || $day === "Sunday") {
    echo "Weekend!\n";
} else {
    echo "Midweek.\n";
}
?>
