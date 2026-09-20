<?php

declare(strict_types=1);

$products = [
    ['name' => 'Keyboard', 'price' => 1_200_000, 'stock' => 4],
    ['name' => 'Mouse', 'price' => 650_000, 'stock' => 0],
    ['name' => 'Monitor', 'price' => 8_900_000, 'stock' => 2],
];

$available = array_filter(
    $products,
    static fn (array $product): bool => $product['stock'] > 0
);

$priced = array_map(
    static fn (array $product): array => [
        ...$product,
        'final_price' => (int) round($product['price'] * 1.10),
    ],
    $available
);

usort(
    $priced,
    static fn (array $left, array $right): int =>
        $left['final_price'] <=> $right['final_price']
);

foreach ($priced as $product) {
    printf(
        "%s | stock: %d | final price: %s\n",
        $product['name'],
        $product['stock'],
        number_format($product['final_price'])
    );
}

echo 'First category: ' . ($products[0]['category'] ?? 'uncategorized') . PHP_EOL;
