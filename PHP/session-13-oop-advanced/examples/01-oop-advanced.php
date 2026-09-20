<?php
declare(strict_types=1);

interface Notifier
{
    public function send(string $message): void;
}

final class EchoNotifier implements Notifier
{
    public function send(string $message): void
    {
        echo $message . PHP_EOL;
    }
}

final class OrderService
{
    public function __construct(private Notifier $notifier) {}

    public function place(string $id): void
    {
        $this->notifier->send("order {$id} placed");
    }
}

(new OrderService(new EchoNotifier()))->place('A-1');
