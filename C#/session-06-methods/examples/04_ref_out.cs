// ============================================================
// جلسه ۶ — ref و out
// ============================================================

// --- ref: تغییر متغیر caller ---
void Swap(ref int a, ref int b)
{
    int temp = a;
    a = b;
    b = temp;
}

int x = 10, y = 20;
Console.WriteLine($"قبل: x={x}, y={y}");
Swap(ref x, ref y);
Console.WriteLine($"بعد: x={x}, y={y}");

Console.WriteLine();

// --- ref: increment in-place ---
void Increment(ref int n) => n++;

int counter = 0;
Increment(ref counter);
Increment(ref counter);
Console.WriteLine($"counter = {counter}"); // 2

Console.WriteLine();

// --- out: Try pattern ---
bool TryParsePositive(string? input, out int result)
{
    result = 0;
    if (!int.TryParse(input, out int parsed))
        return false;
    if (parsed <= 0)
        return false;
    result = parsed;
    return true;
}

if (TryParsePositive("42", out int value))
    Console.WriteLine($"✅ مقدار مثبت: {value}");
else
    Console.WriteLine("❌ ورودی نامعتبر");

Console.WriteLine();

// --- out: چند مقدار خروجی ---
void MinMax(int[] arr, out int min, out int max)
{
    min = max = arr[0];
    foreach (int val in arr)
    {
        if (val < min) min = val;
        if (val > max) max = val;
    }
}

int[] data = { 45, 12, 78, 34, 91 };
MinMax(data, out int minimum, out int maximum);
Console.WriteLine($"min={minimum}, max={maximum}");

Console.WriteLine();

// --- out: TryDivide ---
bool TryDivide(int a, int b, out int quotient, out int remainder)
{
    quotient = remainder = 0;
    if (b == 0) return false;
    quotient = a / b;
    remainder = a % b;
    return true;
}

if (TryDivide(17, 5, out int q, out int r))
    Console.WriteLine($"17 ÷ 5 = {q} باقیمانده {r}");
