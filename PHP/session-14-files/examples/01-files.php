<?php
// کار با فایل‌ها
file_put_contents('test.txt', 'Hello World');
echo file_get_contents('test.txt') . '\n';
unlink('test.txt');

