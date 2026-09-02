// ============================================================
// پاسخ نمونه — تمرین‌های خانه (۵–۷)
// ============================================================

// ۵. BMI
Console.Write("وزن (kg): "); double.TryParse(Console.ReadLine(), out double weight);
Console.Write("قد (m): ");  double.TryParse(Console.ReadLine(), out double height);

if (height <= 0)
{
    Console.WriteLine("قد نامعتبر!");
}
else
{
    double bmi = weight / (height * height);
    string category = bmi switch
    {
        < 18.5  => "کم‌وزن",
        < 25.0  => "نرمال",
        < 30.0  => "اضافه‌وزن",
        _       => "چاق"
    };
    Console.WriteLine($"BMI = {bmi:F1} — {category}");
}

Console.WriteLine();

// ۶. منوی ATM
double balance = 1_000_000;
Console.WriteLine("1=موجودی  2=برداشت  3=واریز  0=خروج");
Console.Write("انتخاب: ");
int.TryParse(Console.ReadLine(), out int atmChoice);

switch (atmChoice)
{
    case 1:
        Console.WriteLine($"موجودی: {balance:N0} تومان");
        break;
    case 2:
        Console.Write("مبلغ برداشت: ");
        double.TryParse(Console.ReadLine(), out double withdraw);
        balance -= withdraw;
        Console.WriteLine($"برداشت شد. موجودی: {balance:N0}");
        break;
    case 3:
        Console.Write("مبلغ واریز: ");
        double.TryParse(Console.ReadLine(), out double deposit);
        balance += deposit;
        Console.WriteLine($"واریز شد. موجودی: {balance:N0}");
        break;
    case 0:
        Console.WriteLine("خداحافظ!");
        break;
    default:
        Console.WriteLine("گزینه نامعتبر");
        break;
}

Console.WriteLine();

// ۷. تخفیف فروشگاه
Console.Write("مبلغ خرید: "); double.TryParse(Console.ReadLine(), out double price);
Console.Write("عضو هستید؟ (y/n): ");
bool isMember = Console.ReadLine()?.ToLower() == "y";

double discountRate = isMember
    ? (price > 500_000 ? 0.20 : price > 200_000 ? 0.15 : 0)
    : (price > 500_000 ? 0.10 : 0);

double finalPrice = price * (1 - discountRate);
Console.WriteLine($"تخفیف: {discountRate:P0} — قیمت نهایی: {finalPrice:N0} تومان");
