// ============================================================
// جلسه ۶ — Method Overloading
// ============================================================

// --- overload Add ---
int Add(int a, int b) => a + b;
double Add(double a, double b) => a + b;
int Add(int a, int b, int c) => a + b + c;

Console.WriteLine($"Add(2,3) = {Add(2, 3)}");
Console.WriteLine($"Add(2.5,3.1) = {Add(2.5, 3.1)}");
Console.WriteLine($"Add(1,2,3) = {Add(1, 2, 3)}");

Console.WriteLine();

// --- overload FormatPrice ---
string FormatPrice(int price) =>
    $"{price:N0} تومان";

string FormatPrice(double price) =>
    $"{price:N2} تومان";

string FormatPrice(int price, string currency) =>
    $"{price:N0} {currency}";

Console.WriteLine(FormatPrice(150000));
Console.WriteLine(FormatPrice(99.99));
Console.WriteLine(FormatPrice(500, "USD"));

Console.WriteLine();

// --- overload Print ---
void Print(int value)    => Console.WriteLine($"int: {value}");
void Print(double value) => Console.WriteLine($"double: {value}");
void Print(string value) => Console.WriteLine($"string: {value}");
void Print(bool value)   => Console.WriteLine($"bool: {value}");

Print(42);
Print(3.14);
Print("سلام");
Print(true);

Console.WriteLine();

// --- overload با تعداد پارامتر متفاوت ---
void ShowInfo(string name) =>
    Console.WriteLine($"نام: {name}");

void ShowInfo(string name, int age) =>
    Console.WriteLine($"نام: {name}, سن: {age}");

void ShowInfo(string name, int age, string city) =>
    Console.WriteLine($"نام: {name}, سن: {age}, شهر: {city}");

ShowInfo("علی");
ShowInfo("مریم", 28);
ShowInfo("رضا", 35, "شیراز");
