<?php
declare(strict_types=1);

$tz = new DateTimeZone('Asia/Tehran');
$now = new DateTimeImmutable('now', $tz);
$nextWeek = $now->modify('+7 days');

echo 'حالا: ' . $now->format('Y-m-d H:i:s T') . PHP_EOL;
echo 'هفته بعد: ' . $nextWeek->format('Y-m-d') . PHP_EOL;

$born = new DateTimeImmutable('1990-05-01', $tz);
$age = $born->diff($now)->y;
echo "سن تقریبی: {$age}\n";
