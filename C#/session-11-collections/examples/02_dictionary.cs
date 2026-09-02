// ============================================================
// جلسه ۱۱ — Dictionary<TKey, TValue>
// ============================================================

// --- ایجاد ---
var ages = new Dictionary<string, int>
{
    ["علی"] = 25,
    ["مریم"] = 30,
    ["رضا"] = 28
};

Console.WriteLine($"علی: {ages["علی"]} سال");

Console.WriteLine();

// --- Add / update ---
ages["سارا"] = 22;
ages["علی"] = 26;  // update
Console.WriteLine($"علی (updated): {ages["علی"]}");

Console.WriteLine();

// --- TryGetValue — safe lookup ---
if (ages.TryGetValue("امیر", out int amirAge))
    Console.WriteLine($"امیر: {amirAge}");
else
    Console.WriteLine("امیر در دیکشنری نیست");

Console.WriteLine();

// --- ContainsKey ---
Console.WriteLine($"Has مریم: {ages.ContainsKey("مریم")}");

Console.WriteLine();

// --- foreach KeyValuePair ---
Console.WriteLine("=== دفترچه تلفن ===");
var phonebook = new Dictionary<string, string>
{
    ["علی"] = "09121234567",
    ["مریم"] = "09129876543",
    ["رضا"] = "09351234567"
};

foreach (KeyValuePair<string, string> entry in phonebook)
    Console.WriteLine($"  {entry.Key}: {entry.Value}");

Console.WriteLine();

// --- Keys / Values ---
Console.WriteLine("Names: " + string.Join(", ", phonebook.Keys));

Console.WriteLine();

// --- کاربرد: word count ---
string text = "سلام دنیا سلام C#";
var wordCount = new Dictionary<string, int>();
foreach (string word in text.Split(' '))
{
    if (wordCount.ContainsKey(word))
        wordCount[word]++;
    else
        wordCount[word] = 1;
}
foreach (var kv in wordCount)
    Console.WriteLine($"  '{kv.Key}': {kv.Value}");

Console.WriteLine();

// --- Remove ---
ages.Remove("رضا");
Console.WriteLine($"Count after remove: {ages.Count}");
