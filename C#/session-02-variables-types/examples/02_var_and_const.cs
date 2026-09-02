// ============================================================
// جلسه ۲ — var و const
// ============================================================

var city = "Tehran";      // string
var count = 10;           // int
var average = 18.5;       // double

const double TaxRate = 0.09;
const int MaxItems = 50;

double basePrice = 100_000;
double finalPrice = basePrice * (1 + TaxRate);

Console.WriteLine($"{city}: {count} items, final={finalPrice:N0}");
Console.WriteLine($"Max allowed: {MaxItems}");
