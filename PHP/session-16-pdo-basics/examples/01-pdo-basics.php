<?php
/**
 * الگوی اتصال MySQL — مقادیر را از محیط خودتان بگذارید؛ اجرا اختیاری است.
 */
declare(strict_types=1);

$dsn = getenv('PHP_COURSE_DSN') ?: 'mysql:host=127.0.0.1;dbname=clinic;charset=utf8mb4';
$user = getenv('PHP_COURSE_DB_USER') ?: 'root';
$pass = getenv('PHP_COURSE_DB_PASS') ?: '';

try {
    $pdo = new PDO($dsn, $user, $pass, [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    ]);
    echo "اتصال OK\n";
} catch (PDOException $e) {
    fwrite(STDERR, "اتصال برقرار نشد (برای دمو بدون MySQL از 01-pdo-sqlite-demo.php استفاده کنید).\n");
    fwrite(STDERR, $e->getMessage() . "\n");
    exit(1);
}
