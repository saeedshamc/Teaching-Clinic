<?php
declare(strict_types=1);
require __DIR__ . '/bootstrap.php';
start_secure_session();

$message = '';
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = trim((string)($_POST['email'] ?? ''));
    $password = (string)($_POST['password'] ?? '');
    $stmt = pdo()->prepare('SELECT id, password_hash FROM users WHERE email = ?');
    $stmt->execute([$email]);
    $user = $stmt->fetch(PDO::FETCH_ASSOC);
    if (!$user || !password_verify($password, $user['password_hash'])) {
        $message = 'ایمیل یا رمز عبور نادرست است.';
    } else {
        session_regenerate_id(true);
        $_SESSION['user_id'] = (int)$user['id'];
        header('Location: dashboard.php');
        exit;
    }
}
header('Content-Type: text/html; charset=UTF-8');
?>
<!doctype html>
<html lang="fa" dir="rtl"><meta charset="utf-8"><title>ورود</title>
<body>
<h1>ورود</h1>
<?php if ($message): ?><p><?= htmlspecialchars($message, ENT_QUOTES, 'UTF-8') ?></p><?php endif; ?>
<form method="post">
  <label>ایمیل <input type="email" name="email" required></label><br>
  <label>رمز <input type="password" name="password" required></label><br>
  <button>ورود</button>
</form>
<p><a href="register.php">ثبت‌نام</a></p>
</body></html>
