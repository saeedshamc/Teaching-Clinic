<?php
// مبانی OOP
class Person {
    public $name;
    public function greet() {
        return 'Hello, I am ' . $this->name;
    }
}
$person = new Person();
$person->name = 'Ali';
echo $person->greet() . '\n';

