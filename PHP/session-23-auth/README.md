# جلسه ۲۳: احراز هویت ساده با password_hash (۱۲۰ دقیقه)

**محیط فرض:** PHP 8.2+ · `php -S localhost:8000`

## پیش‌نیازها
جلسات ۱۰، ۱۵، ۱۹.

## اهداف قابل‌اندازه‌گیری
می‌توانید رمز را با `password_hash` ذخیره کنید؛ با `password_verify` بررسی کنید؛ بعد از login، `session_regenerate_id(true)` بزنید؛ و logout امن انجام دهید — بدون آموزش ضدالگوهایی مثل MD5 خام.

## چرا مهم است؟
ذخیرهٔ رمز متنی یا هش ضعیف، فاجعه است. API داخلی PHP برای همین طراحی شده.

## مفهوم عمیق
```php
$hash = password_hash($password, PASSWORD_DEFAULT);
$ok = password_verify($password, $hash);
```
`PASSWORD_DEFAULT` الگوریتم پیشنهادی زمان اجرا را انتخاب می‌کند (امروز معمولاً bcrypt/argon وابسته به نسخه).

هرگز الگوریتم قدیمی دستی برای رمز عبور کاربر ننویسید مگر تخصص crypto دارید.

## اشتباه‌های رایج
1. `md5($password)` برای ذخیره.
2. مقایسهٔ مستقیم رشتهٔ هش با `===` روی ورودی خام.
3. فراموش regenerate id بعد از موفقیت login.

## مثال گام‌به‌گام
```bash
cd session-23-auth/examples
php -S localhost:8000
# register.php / login.php / logout.php
```

دمو از فایل SQLite محلی داخل examples استفاده می‌کند.

## الگوی بهتر
حداقل طول رمز؛ محدودیت نرخ تلاش (مفهومی)؛ HTTPS در تولید؛ پیام خطای یکسان «ایمیل یا رمز غلط» برای جلوگیری از user enumeration تا حد ممکن.

## تمرین‌ها
### آسان
ثبت یک کاربر در CLI با hash چاپ‌شده.

### چالشی
صفحهٔ login کامل با session و redirect.

## راهنمای حل
بعد از verify موفق: regenerate، سپس `$_SESSION['user_id'] = ...`.

## خودآزمایی
1. چرا password_hash؟
2. password_verify چه می‌کند؟
3. چرا MD5 برای رمز بد است؟
4. regenerate چرا؟
5. پیام خطای یکسان چه سودی دارد؟
6. هش را کجا ذخیره کنیم؟

## جمع‌بندی
احراز هویت آموزشی امن را دارید. برای مسیر بعدی: middleware، remember-me امن، و ایمیل تأیید — خارج از این دورهٔ پایه.
