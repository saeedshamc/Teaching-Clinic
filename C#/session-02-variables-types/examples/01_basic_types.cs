// ============================================================
// جلسه ۲ — انواع پایه
// ============================================================

int age = 25;
long population = 85_000_000L;
double height = 1.75;
float ratio = 0.5f;
decimal price = 199_000m;
bool isActive = true;
char grade = 'A';
string name = "Ali";

Console.WriteLine($"int: {age}, long: {population}");
Console.WriteLine($"double: {height}, float: {ratio}");
Console.WriteLine($"decimal: {price:N0}, bool: {isActive}");
Console.WriteLine($"char: {grade}, string: {name}");

// دقت اعشاری — چرا decimal برای پول
Console.WriteLine(0.1 + 0.2);
Console.WriteLine(0.1m + 0.2m);
