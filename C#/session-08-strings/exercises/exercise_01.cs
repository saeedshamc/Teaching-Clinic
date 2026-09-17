// ============================================================
// پاسخ نمونه — تمرین‌های کلاسی (۱–۴)
// ============================================================

// ۱. Reverse
string input = "programming";
string reversed = "";
for (int i = input.Length - 1; i >= 0; i--)
    reversed += input[i];
Console.WriteLine(reversed);

// ۲. شمارش کلمات
string sentence = "سلام دنیا C#";
int wordCount = sentence.Split(' ', StringSplitOptions.RemoveEmptyEntries).Length;
Console.WriteLine($"کلمات: {wordCount}");

// ۳. Palindrome
string word = "radar";
bool isPalindrome = true;
for (int i = 0; i < word.Length / 2; i++)
{
    if (word[i] != word[word.Length - 1 - i])
    { isPalindrome = false; break; }
}
Console.WriteLine(isPalindrome ? "Palindrome" : "Not palindrome");

// ۴. Trim validate
Console.Write("ورودی: ");
string? userInput = Console.ReadLine()?.Trim();
if (string.IsNullOrWhiteSpace(userInput))
    Console.WriteLine("❌ ورودی خالی!");
else
    Console.WriteLine($"✅ '{userInput}'");
