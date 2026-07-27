<?php
// متغیرهای سراسری (Superglobals)
// این متغیرها در تمام scopeها در دسترس هستند

// 1. $_SERVER - اطلاعات سرور و محیط
echo "Server Name: " . ($_SERVER['SERVER_NAME'] ?? 'Not available') . "\n";
echo "Server Software: " . ($_SERVER['SERVER_SOFTWARE'] ?? 'Not available') . "\n";
echo "Current File: " . $_SERVER['PHP_SELF'] . "\n";

// 2. $_GET - متغیرهای ارسال شده از طریق URL (متد GET)
// مثال: http://localhost/test.php?name=Ali&age=25
// echo "Name: " . $_GET['name'] . "\n";
// echo "Age: " . $_GET['age'] . "\n";

// 3. $_POST - متغیرهای ارسال شده از طریق فرم (متد POST)
// در جلسات آینده بیشتر با این متغیر آشنا خواهیم شد

// 4. $_REQUEST - ترکیبی از $_GET, $_POST, $_COOKIE

// 5. $_COOKIE - کوکی‌های HTTP

// 6. $_SESSION - متغیرهای Session

// 7. $_FILES - فایل‌های آپلود شده

// 8. $_ENV - متغیرهای محیطی

// 9. $GLOBALS - تمام متغیرهای سراسری
echo "\nGlobal variables count: " . count($GLOBALS) . "\n";
?>
