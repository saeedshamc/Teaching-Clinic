<?php
// رشته‌های ساده
$singleQuoted = 'This is a single quoted string';
$doubleQuoted = "This is a double quoted string";

// تفاوت بین رشته‌های تک کوته و دو کوته
$name = "Ali";
echo 'Hello, $name!\n';   // خروجی: Hello, $name!
echo "Hello, $name!\n";  // خروجی: Hello, Ali!

// رشته‌های چندخطی (با استفاده از NOWDOC و HEREDOC)
$heredoc = <<<EOD
This is a heredoc string.
It can span multiple lines.
Variables like $name are interpolated.
EOD;

echo $heredoc . "\n";

$nowdoc = <<<'EOD'
This is a nowdoc string.
It can span multiple lines.
Variables like $name are NOT interpolated.
EOD;

echo $nowdoc . "\n";
?>
