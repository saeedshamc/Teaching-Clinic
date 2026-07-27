<?php
// مثال ۱: چاپ اعداد ۱ تا ۱۰
$i = 1;
do {
    echo $i . "\n";
    $i++;
} while ($i <= 10);

// مثال ۲: حداقل یک بار اجرا می‌شود
$condition = false;
do {
    echo "This will be printed at least once!\n";
} while ($condition);

// مثال ۳: منوی ساده
$choice = "";
echo "Menu:\n";
echo "1. Option 1\n";
echo "2. Option 2\n";
echo "3. Exit\n";

// در محیط واقعی، این از کاربر دریافت می‌شود
// برای مثال، ما آن را شبیه‌سازی می‌کنیم
$choices = [1, 2, 3];
foreach ($choices as $choice) {
    do {
        echo "Selected option: $choice\n";
        if ($choice === 3) {
            echo "Exiting...\n";
            break;
        }
        // در محیط واقعی: $choice = (int)readline("Enter your choice: ");
        $choice = 3; // برای خروج از حلقه
    } while (true);
    break;
}
?>
