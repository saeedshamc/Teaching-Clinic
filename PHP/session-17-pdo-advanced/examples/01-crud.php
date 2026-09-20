<?php
declare(strict_types=1);

$pdo = new PDO('sqlite::memory:', options: [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
]);
$pdo->exec('CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL)');

$insert = $pdo->prepare('INSERT INTO notes (title) VALUES (?)');
$insert->execute(['یادداشت اول']);
$id = (int)$pdo->lastInsertId();

$update = $pdo->prepare('UPDATE notes SET title = ? WHERE id = ?');
$update->execute(['یادداشت به‌روز', $id]);

$select = $pdo->query('SELECT id, title FROM notes');
foreach ($select as $row) {
    echo $row['id'] . ': ' . $row['title'] . PHP_EOL;
}

$delete = $pdo->prepare('DELETE FROM notes WHERE id = ?');
$delete->execute([$id]);
echo 'حذف شد، باقی‌مانده: ' . $pdo->query('SELECT COUNT(*) FROM notes')->fetchColumn() . PHP_EOL;
