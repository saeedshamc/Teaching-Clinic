// تمرین ۱–۳
Console.Write("عدد: ");
int.TryParse(Console.ReadLine(), out int n);
Console.WriteLine(n % 2 == 0 ? "زوج" : "فرد");

Console.Write("a: "); int.TryParse(Console.ReadLine(), out int a);
Console.Write("b: "); int.TryParse(Console.ReadLine(), out int b);
Console.Write("c: "); int.TryParse(Console.ReadLine(), out int c);
int max = a;
if (b > max) max = b;
if (c > max) max = c;
Console.WriteLine($"max={max}");

Console.Write("x: "); double.TryParse(Console.ReadLine(), out double x);
Console.Write("y: "); double.TryParse(Console.ReadLine(), out double y);
Console.Write("op (+,-,*,/): ");
string? op = Console.ReadLine();
double r = op switch { "+" => x+y, "-" => x-y, "*" => x*y, "/" => y==0?0:x/y, _ => 0 };
Console.WriteLine($"={r}");
