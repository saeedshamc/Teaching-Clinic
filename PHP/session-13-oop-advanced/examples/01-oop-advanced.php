<?php
// OOP پیشرفته
class Animal {
    protected $name;
    public function __construct($name) {
        $this->name = $name;
    }
}
class Dog extends Animal {
    public function bark() {
        return $this->name . ' says Woof!';
    }
}
$dog = new Dog('Rex');
echo $dog->bark() . '\n';

