<?php
// مثال ۱: روز هفته
$day = "Thursday";

switch ($day) {
    case "Monday":
        echo "Start of the work week.\n";
        break;
    case "Tuesday":
    case "Wednesday":
    case "Thursday":
        echo "Midweek.\n";
        break;
    case "Friday":
        echo "Almost weekend!\n";
        break;
    case "Saturday":
    case "Sunday":
        echo "Weekend!\n";
        break;
    default:
        echo "Invalid day.\n";
}

// مثال ۲: درجه‌بندی
$grade = "B";

switch ($grade) {
    case "A":
        echo "Excellent!\n";
        break;
    case "B":
        echo "Good job!\n";
        break;
    case "C":
        echo "Average.\n";
        break;
    case "D":
        echo "Needs improvement.\n";
        break;
    case "F":
        echo "Fail.\n";
        break;
    default:
        echo "Invalid grade.\n";
}

// مثال ۳: مقایسه با انواع مختلف
$value = "5";

switch ($value) {
    case 5:
        echo "Integer 5\n";
        break;
    case "5":
        echo "String '5'\n";
        break;
    default:
        echo "Other value\n";
}

// مثال ۴: استفاده از break
$number = 2;

switch ($number) {
    case 1:
        echo "One\n";
        // break فراموش شده است!
    case 2:
        echo "Two\n";
        break;
    case 3:
        echo "Three\n";
        break;
    default:
        echo "Other\n";
}
// خروجی: Two (چون case 1 break ندارد، به case 2 می‌رود)
?>
