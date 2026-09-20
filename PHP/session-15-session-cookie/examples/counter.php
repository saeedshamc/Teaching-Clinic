<?php
declare(strict_types=1);

session_set_cookie_params([
    'lifetime' => 0,
    'path' => '/',
    'secure' => false,
    'httponly' => true,
    'samesite' => 'Lax',
]);
session_start();

$_SESSION['visits'] = (int)($_SESSION['visits'] ?? 0) + 1;
header('Content-Type: text/html; charset=UTF-8');
echo '<p>بازدید: ' . (int)$_SESSION['visits'] . '</p>';
echo '<p><a href="counter.php">رفرش</a></p>';
