<?php
declare(strict_types=1);

/**
 * اگر composer dump-autoload زده باشید از vendor استفاده می‌شود؛
 * وگرنه یک autoload PSR-4 ساده برای دموی آموزشی.
 */
$vendor = __DIR__ . '/composer-demo/vendor/autoload.php';
if (is_file($vendor)) {
    require $vendor;
} else {
    spl_autoload_register(static function (string $class): void {
        $prefix = 'Clinic\\';
        if (!str_starts_with($class, $prefix)) {
            return;
        }
        $relative = str_replace('\\', '/', substr($class, strlen($prefix)));
        $path = __DIR__ . '/composer-demo/src/' . $relative . '.php';
        if (is_file($path)) {
            require $path;
        }
    });
}

use Clinic\Hello;

echo (new Hello())->greet('Composer') . PHP_EOL;
echo "(برای حالت رسمی: داخل composer-demo دستور composer dump-autoload را بزنید)\n";
