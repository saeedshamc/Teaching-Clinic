<?php
// PDO پیشرفته
$stmt = $pdo->prepare('SELECT * FROM users WHERE id = ?');
$stmt->execute([1]);
$user = $stmt->fetch();
print_r($user);

