<?php
namespace Clinic;

final class Hello
{
    public function greet(string $name): string
    {
        return 'سلام، ' . $name;
    }
}
