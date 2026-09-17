// ============================================================
// جلسه ۳ — Ternary و اولویت عملگر
// ============================================================

int age = 17;
string label = age >= 18 ? "بزرگسال" : "نوجوان";
Console.WriteLine(label);

int result = 2 + 3 * 4;
Console.WriteLine($"2+3*4 = {result}");
Console.WriteLine($"(2+3)*4 = {(2 + 3) * 4}");

double price = 200_000;
bool member = true;
double pay = member ? price * 0.85 : price;
Console.WriteLine($"پرداخت: {pay:N0}");
