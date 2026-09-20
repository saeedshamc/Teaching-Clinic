<?php

declare(strict_types=1);

function subtotal(array $prices): int
{
    return array_sum($prices);
}

function discountFor(int $amount, int $percent = 10): int
{
    if ($percent < 0 || $percent > 100) {
        throw new InvalidArgumentException('Percent must be between 0 and 100.');
    }

    return (int) round($amount * $percent / 100);
}

function invoiceTotal(array $prices, callable $discountPolicy): int
{
    $subtotal = subtotal($prices);
    return $subtotal - $discountPolicy($subtotal);
}

$total = invoiceTotal(
    [120_000, 80_000, 50_000],
    static fn (int $amount): int => discountFor($amount, 15)
);

echo 'Total: ' . number_format($total) . PHP_EOL;
