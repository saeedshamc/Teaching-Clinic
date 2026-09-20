<?php
declare(strict_types=1);

function parsePositive(string $raw): int
{
    if (!ctype_digit($raw) || (int)$raw <= 0) {
        throw new InvalidArgumentException('عدد صحیح مثبت لازم است.');
    }
    return (int)$raw;
}

try {
    $value = parsePositive('12');
    echo "OK: {$value}\n";
    parsePositive('-3');
} catch (InvalidArgumentException $e) {
    echo 'خطا: ' . $e->getMessage() . "\n";
} finally {
    echo "پایان بلوک\n";
}
