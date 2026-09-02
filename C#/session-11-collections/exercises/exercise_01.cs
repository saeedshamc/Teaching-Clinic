// ============================================================
// پاسخ نمونه — تمرین‌های کلاسی (۱–۴)
// ============================================================

// ۱. List CRUD
var names = new List<string> { "علی", "مریم" };
names.Add("رضا");
names.Remove("مریم");
foreach (string n in names) Console.WriteLine(n);

// ۲. Phonebook
var phone = new Dictionary<string, string>
{
    ["علی"] = "09121111111",
    ["مریم"] = "09122222222",
    ["رضا"] = "09123333333"
};
if (phone.TryGetValue("علی", out string? num))
    Console.WriteLine($"علی: {num}");

// ۳. HashSet unique
var dupes = new List<int> { 1, 2, 2, 3, 3, 3 };
var unique = new HashSet<int>(dupes);
Console.WriteLine(string.Join(", ", unique));

// ۴. Queue
var q = new Queue<string>();
for (int i = 1; i <= 5; i++) q.Enqueue($"Task-{i}");
while (q.Count > 0) Console.WriteLine(q.Dequeue());
