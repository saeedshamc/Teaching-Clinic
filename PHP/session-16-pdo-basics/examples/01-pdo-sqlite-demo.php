<?php
declare(strict_types=1);

$pdo = new PDO('sqlite::memory:', options: [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
]);

$pdo->exec('CREATE TABLE users (id INTEGER PRIMARY KEY, email TEXT NOT NULL)');
$stmt = $pdo->prepare('INSERT INTO users (email) VALUES (?)');
$stmt->execute(['sara@example.com']);

$find = $pdo->prepare('SELECT id, email FROM users WHERE email = ?');
$find->execute(['sara@example.com']);
$user = $find->fetch();
echo json_encode($user, JSON_UNESCAPED_UNICODE) . PHP_EOL;
