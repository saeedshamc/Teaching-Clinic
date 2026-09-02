// ============================================================
// پاسخ نمونه — تمرین‌های خانه (۵–۸)
// ============================================================
using System.Text;

// ۵. Mask email
string email = "ali.reza@gmail.com";
int atIndex = email.IndexOf('@');
string masked = email[0] + "***" + email[atIndex..];
Console.WriteLine(masked);

// ۶. CSV parser
string line = "لپ‌تاپ;25000000;2";
string[] parts = line.Split(';');
Console.WriteLine($"محصول: {parts[0]}");
Console.WriteLine($"قیمت: {double.Parse(parts[1]):N0} تومان");
Console.WriteLine($"تعداد: {parts[2]}");

// ۷. StringBuilder table
var table = new StringBuilder();
for (int row = 0; row < 5; row++)
{
    for (int col = 0; col < 5; col++)
        table.Append($"{row * 5 + col + 1,4}");
    table.AppendLine();
}
Console.WriteLine(table.ToString());

// ۸. Replace
string text = "I love Java";
Console.WriteLine(text.Replace("Java", "C#"));
