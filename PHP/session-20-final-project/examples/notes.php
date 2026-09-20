<?php
declare(strict_types=1);

$dbPath = __DIR__ . '/notes.sqlite';
$pdo = new PDO('sqlite:' . $dbPath, options: [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
]);
$pdo->exec('CREATE TABLE IF NOT EXISTS notes (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  title TEXT NOT NULL,
  created_at TEXT NOT NULL
)');

$cmd = $argv[1] ?? 'list';

match ($cmd) {
    'add' => (function () use ($pdo, $argv): void {
        $title = trim((string)($argv[2] ?? ''));
        if ($title === '') {
            fwrite(STDERR, "عنوان خالی است\n");
            exit(1);
        }
        $stmt = $pdo->prepare('INSERT INTO notes (title, created_at) VALUES (?, ?)');
        $stmt->execute([$title, gmdate('c')]);
        echo "اضافه شد id=" . $pdo->lastInsertId() . PHP_EOL;
    })(),
    'list' => (function () use ($pdo): void {
        foreach ($pdo->query('SELECT id, title, created_at FROM notes ORDER BY id') as $row) {
            echo "{$row['id']}\t{$row['title']}\t{$row['created_at']}\n";
        }
    })(),
    default => (function () use ($cmd): void {
        fwrite(STDERR, "دستور ناشناخته: {$cmd}\n");
        exit(1);
    })(),
};
