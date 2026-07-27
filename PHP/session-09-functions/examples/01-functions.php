<?php
// توابع در PHP
function greet($name = 'Guest') {
    return 'Hello, ' . $name;
}
echo greet('Ali') . '\n';
echo greet() . '\n';

