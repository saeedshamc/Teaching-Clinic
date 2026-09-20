<?php
declare(strict_types=1);

require_once __DIR__ . '/lib/helpers.php';
$config = require __DIR__ . '/lib/config.php';

echo e($config['app_name']) . PHP_EOL;
echo e($config['timezone']) . PHP_EOL;
