<?php
declare(strict_types=1);
require __DIR__ . '/bootstrap.php';
start_secure_session();
if (empty($_SESSION['user_id'])) {
    header('Location: login.php');
    exit;
}
header('Content-Type: text/html; charset=UTF-8');
?>
<!doctype html>
<html lang="fa" dir="rtl"><meta charset="utf-8"><title>داشبورد</title>
<body>
<h1>وارد شده‌اید</h1>
<p>user_id=<?= (int)$_SESSION['user_id'] ?></p>
<p><a href="logout.php">خروج</a></p>
</body></html>
