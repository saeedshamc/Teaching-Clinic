// تمرین ۴–۵
double mid = 16, finalExam = 14;
double final = 0.4 * mid + 0.6 * finalExam;
Console.WriteLine(final >= 10 ? "قبول" : "مردود");

double price = 350_000;
double discount = price > 500_000 ? 0.15 : price > 200_000 ? 0.10 : 0;
Console.WriteLine($"قیمت نهایی: {price * (1 - discount):N0}");
