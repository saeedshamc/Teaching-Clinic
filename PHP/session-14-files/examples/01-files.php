<?php
declare(strict_types=1);

$dir = __DIR__ . '/storage';
if (!is_dir($dir)) {
    mkdir($dir);
}
$path = $dir . '/note.txt';
file_put_contents($path, "خط یک\nخط دو\n", LOCK_EX);
$lines = file($path, FILE_IGNORE_NEW_LINES);
foreach ($lines as $line) {
    echo $line . PHP_EOL;
}
