// ============================================================
// جلسه ۴ — سیستم نمره واقعی (ترکیب if + switch)
// ============================================================

Console.WriteLine("=== سیستم ثبت نمره ===");
Console.Write("نام دانشجو: ");
string? name = Console.ReadLine() ?? "ناشناس";

Console.Write("نمره (0-100): ");
bool valid = int.TryParse(Console.ReadLine(), out int score);

// Early return pattern — اعتبارسنجی اول
if (!valid || score < 0 || score > 100)
{
    Console.WriteLine("❌ نمره نامعتبر! باید بین ۰ تا ۱۰۰ باشد.");
    return;
}

// Switch expression — حرف نمره
string letter = score switch
{
    >= 90 => "A",
    >= 80 => "B",
    >= 70 => "C",
    >= 60 => "D",
    >= 50 => "E",
    _     => "F"
};

// Switch expression — توضیح فارسی
string description = letter switch
{
    "A" => "عالی",
    "B" => "خوب",
    "C" => "متوسط",
    "D" => "ضعیف",
    "E" => "حد نصاب",
    _   => "مردود"
};

// if/else — وضعیت قبولی
string status = score >= 50 ? "قبول ✅" : "مردود ❌";

// switch statement — توصیه
string advice;
switch (letter)
{
    case "A":
    case "B":
        advice = "ادامه همین روند عالی!";
        break;
    case "C":
    case "D":
        advice = "کمی تلاش بیشتر لازم است.";
        break;
    default:
        advice = "جلسات تقویتی پیشنهاد می‌شود.";
        break;
}

Console.WriteLine();
Console.WriteLine($"👤 {name}");
Console.WriteLine($"📊 نمره: {score} — حرف: {letter} ({description})");
Console.WriteLine($"📋 وضعیت: {status}");
Console.WriteLine($"💡 توصیه: {advice}");
