// تمرین ۱–۳ — پاسخ نمونه
Console.Write("Celsius: ");
double.TryParse(Console.ReadLine(), out double c);
double f = c * 9 / 5 + 32;
Console.WriteLine($"Fahrenheit: {f}");

Console.Write("طول: "); double.TryParse(Console.ReadLine(), out double w);
Console.Write("عرض: "); double.TryParse(Console.ReadLine(), out double h);
Console.WriteLine($"مساحت: {w * h}");

const double Pi = 3.14159265;
Console.Write("شعاع: "); double.TryParse(Console.ReadLine(), out double r);
Console.WriteLine($"مساحت دایره: {Pi * r * r}");
