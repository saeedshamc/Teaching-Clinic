<?php
declare(strict_types=1);
require __DIR__ . '/bootstrap.php';
start_secure_session();

$message = '';
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = trim((string)($_POST['email'] ?? ''));
    $password = (string)($_POST['password'] ?? '');
    if (!filter_var($email, FILTER_VALIDATE_EMAIL) || strlen($password) < 8) {
        $message = 'ایمیل یا رمز معتبر نیست (حداقل ۸ کاراکتر).';
    } else {
        $hash = password_hash($password, PASSWORD_DEFAULT);
        try {
            pdo()->prepare('INSERT INTO users (email, password_hash) VALUES (?, ?)')
                ->execute([$email, $hash]);
            $message = 'ثبت شد. می‌توانید وارد شوید.';
        } catch (PDOException) {
            $message = 'این ایمیل قبلاً ثبت شده است.';
        }
    }
}
header('Content-Type: text/html; charset=UTF-8');
?>
<!doctype html>
<html lang="fa" dir="rtl"><meta charset="utf-8"><title>ثبت‌نام</title>
<body>
<h1>ثبت‌نام</h1>
<?php if ($message): ?><p><?= htmlspecialchars($message, ENT_QUOTES, 'UTF-8') ?></p><?php endif; ?>
<form method="post">
  <label>ایمیل <input type="email" name="email" required></label><br>
  <label>رمز <input type="password" name="password" required minlength="8"></label><br>
  <button>ثبت</button>
</form>
<p><a href="login.php">ورود</a></p>
</body></html>
