<?php
declare(strict_types=1);

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo 'فقط POST مجاز است.';
    exit;
}

$name = trim((string)($_POST['name'] ?? ''));
$email = filter_input(INPUT_POST, 'email', FILTER_VALIDATE_EMAIL);
$message = trim((string)($_POST['message'] ?? ''));

$errors = [];
if ($name === '' || mb_strlen($name) < 2) {
    $errors[] = 'نام معتبر نیست.';
}
if ($email === false || $email === null) {
    $errors[] = 'ایمیل معتبر نیست.';
}
if (mb_strlen($message) < 10) {
    $errors[] = 'پیام باید حداقل ۱۰ کاراکتر باشد.';
}

header('Content-Type: text/html; charset=UTF-8');

if ($errors) {
    echo '<h1>خطا</h1><ul>';
    foreach ($errors as $e) {
        echo '<li>' . htmlspecialchars($e, ENT_QUOTES | ENT_SUBSTITUTE, 'UTF-8') . '</li>';
    }
    echo '</ul><a href="form.html">بازگشت</a>';
    exit;
}

echo '<h1>دریافت شد</h1>';
echo '<p>نام: ' . htmlspecialchars($name, ENT_QUOTES | ENT_SUBSTITUTE, 'UTF-8') . '</p>';
echo '<p>ایمیل: ' . htmlspecialchars((string)$email, ENT_QUOTES | ENT_SUBSTITUTE, 'UTF-8') . '</p>';
echo '<p>پیام: ' . nl2br(htmlspecialchars($message, ENT_QUOTES | ENT_SUBSTITUTE, 'UTF-8')) . '</p>';
