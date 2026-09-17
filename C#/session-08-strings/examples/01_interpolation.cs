// ============================================================
// جلسه ۸ — String Interpolation و Formatting
// ============================================================

string name = "علی";
int age = 25;
double salary = 15_500_000;

// --- interpolation پایه ---
Console.WriteLine($"سلام {name}! سن شما {age} سال است.");

// --- format specifiers ---
Console.WriteLine($"حقوق: {salary:N0} تومان");
Console.WriteLine($"اعشار: {Math.PI:F4}");
Console.WriteLine($"درصد: {0.875:P1}");
Console.WriteLine($"Hex: {255:X2}");

Console.WriteLine();

// --- عبارت داخل {} ---
Console.WriteLine($"2 + 3 = {2 + 3}");
Console.WriteLine($"بزرگسال: {(age >= 18 ? "بله" : "خیر")}");

Console.WriteLine();

// --- alignment و padding ---
Console.WriteLine($"|{"چپ",-10}|{"راست",10}|");
Console.WriteLine($"|{42:D6}|");  // 000042

Console.WriteLine();

// --- raw string literal (C# 11) ---
string json = """
    {
        "name": "علی",
        "age": 25
    }
    """;
Console.WriteLine(json);

Console.WriteLine();

// --- composite formatting (مرجع) ---
Console.WriteLine(string.Format("سلام {0}! سن: {1}", name, age));

// --- verbatim path ---
string path = @"C:\Users\Ali\Documents";
Console.WriteLine(path);
