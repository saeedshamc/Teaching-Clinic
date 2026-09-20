<?php
declare(strict_types=1);

final class Price
{
    public function __construct(private float $amount)
    {
        if ($amount < 0) {
            throw new InvalidArgumentException('مبلغ منفی مجاز نیست.');
        }
    }

    public function amount(): float
    {
        return $this->amount;
    }

    public function withTax(float $rate): self
    {
        return new self($this->amount * (1 + $rate));
    }
}

$p = new Price(100);
$taxed = $p->withTax(0.09);
echo $p->amount() . ' -> ' . $taxed->amount() . PHP_EOL;
