# جلسه ۱۲: OOP پایه — class، constructor، visibility (۱۲۰ دقیقه)

**محیط فرض:** PHP 8.2+

## پیش‌نیازها
جلسات ۰۱–۱۱.

## اهداف قابل‌اندازه‌گیری
می‌توانید کلاس با property و method بسازید؛ constructor و typed property بنویسید؛ `public`/`private`/`protected` را درست انتخاب کنید؛ و اشیاء را در مثال واقعی کوچک به‌کار ببرید.

## چرا مهم است؟
برای PDO، سرویس‌ها و سازمان کد متوسط، OOP در PHP استاندارد عملی است — نه تزئینی.

## مفهوم عمیق
کلاس قالب است؛ شیء نمونه است. Encapsulation با visibility: جزئیات داخلی `private` می‌مانند و از بیرون فقط API عمومی دیده می‌شود.

```php
final class Price
{
    public function __construct(private float $amount) {
        if ($amount < 0) {
            throw new InvalidArgumentException('منفی ممنوع');
        }
    }
    public function amount(): float { return $this->amount; }
}
```

## اشتباه‌های رایج
1. همه چیز public.
2. کلاس بدون مسئولیت مشخص (God class).
3. فراموش typed property و برگشت به آرایهٔ بی‌ساختار همه‌جا.

## مثال گام‌به‌گام
```bash
php session-12-oop-basics/examples/01-oop-basics.php
```

## الگوی بهتر
رفتار را کنار داده بگذارید؛ validate در constructor؛ ترجیح immutability وقتی ممکن است.

## تمرین‌ها
### آسان
کلاس `User` با `name` و متد `greet(): string`.

### چالشی
کلاس `BankAccount` با deposit/withdraw و جلوگیری از موجودی منفی با exception.

## راهنمای حل
موجودی private؛ withdraw قبل از کم کردن چک کند.

## خودآزمایی
1. تفاوت class و object؟
2. constructor چه زمانی صدا می‌شود؟
3. private یعنی چه؟
4. typed property چه سودی دارد؟
5. final class یعنی چه؟
6. چرا validate در constructor مفید است؟

## جمع‌بندی و پل جلسهٔ بعد
OOP پایه را دارید. جلسهٔ بعد ارث‌بری، interface، و autoload ساده.
