<?php

declare(strict_types=1);

function excerpt(string $text, int $limit = 24): string
{
    $text = trim(preg_replace('/\s+/u', ' ', $text) ?? $text);
    return mb_strlen($text, 'UTF-8') <= $limit
        ? $text
        : mb_substr($text, 0, $limit, 'UTF-8') . '…';
}

$title = '  آموزش حرفه‌ای PHP برای وب  ';
$safeTitle = htmlspecialchars(trim($title), ENT_QUOTES | ENT_SUBSTITUTE, 'UTF-8');

echo 'Characters: ' . mb_strlen(trim($title), 'UTF-8') . PHP_EOL;
echo 'Excerpt: ' . excerpt($title, 16) . PHP_EOL;
echo 'Safe HTML: <h1>' . $safeTitle . '</h1>' . PHP_EOL;
echo str_contains($title, 'PHP') ? 'PHP found' . PHP_EOL : 'PHP not found' . PHP_EOL;
