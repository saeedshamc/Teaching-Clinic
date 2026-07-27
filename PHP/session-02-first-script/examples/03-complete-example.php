<?php
// این یک اسکریپت کامل PHP است

// تعریف متغیرها
$firstName = "Ali";
$lastName = "Rezaei";
$age = 25;

// چاپ اطلاعات با استفاده از echo
echo "=== User Information ===\n";
echo "First Name: $firstName\n";
echo "Last Name: $lastName\n";
echo "Age: $age\n";

// چاپ با استفاده از print
print "\n=== Using print ===\n";
print "Full Name: $firstName $lastName\n";

/*
   این بخش یک کامنت چندخطی است
   برای توضیح محاسبه سن در سال آینده
*/
$nextYearAge = $age + 1;
echo "\nNext year you will be: $nextYearAge\n";

// چاپ با استفاده از تگ کوتاه (اگر فعال باشد)
?>

<!-- خارج از تگ PHP، کدهای HTML قرار می‌گیرند -->
<p>This is HTML outside PHP tags.</p>

<?php
// بازگشت به PHP
echo "\nBack to PHP!";
?>
