<?php
declare(strict_types=1);

function e(string $s): string
{
    return htmlspecialchars($s, ENT_QUOTES | ENT_SUBSTITUTE, 'UTF-8');
}

session_start();
if (empty($_SESSION['csrf'])) {
    $_SESSION['csrf'] = bin2hex(random_bytes(16));
}

$input = '<script>alert(1)</script>';
echo 'خروجی امن: ' . e($input) . PHP_EOL;
echo 'CSRF token نمونه: ' . $_SESSION['csrf'] . PHP_EOL;
echo "یادآوری: SQL را فقط با prepare اجرا کنید؛ آپلود را سفیدلیست کنید.\n";
