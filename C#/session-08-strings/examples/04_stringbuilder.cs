// ============================================================
// جلسه ۸ — StringBuilder
// ============================================================
using System.Text;

// --- مشکل concatenation در loop ---
var sw = System.Diagnostics.Stopwatch.StartNew();
string slow = "";
for (int i = 0; i < 1000; i++)
    slow += i.ToString();
sw.Stop();
Console.WriteLine($"String +: {sw.ElapsedMilliseconds} ms");

// --- StringBuilder ---
sw.Restart();
var sb = new StringBuilder();
for (int i = 0; i < 1000; i++)
    sb.Append(i);
string fast = sb.ToString();
sw.Stop();
Console.WriteLine($"StringBuilder: {sw.ElapsedMilliseconds} ms");

Console.WriteLine();

// --- Append و AppendLine ---
var report = new StringBuilder();
report.AppendLine("=== گزارش فروش ===");
report.AppendLine(new string('=', 30));
report.AppendFormat("تاریخ: {0:yyyy/MM/dd}\n", DateTime.Now);
report.AppendLine(new string('-', 30));

string[] items = { "لپ‌تاپ", "ماوس", "کیبورد" };
double[] prices = { 25_000_000, 500_000, 1_200_000 };

for (int i = 0; i < items.Length; i++)
    report.AppendLine($"  {items[i],-10} {prices[i],12:N0} تومان");

report.AppendLine(new string('-', 30));
double total = 0;
foreach (double p in prices) total += p;
report.AppendLine($"  {"جمع",-10} {total,12:N0} تومان");

Console.WriteLine(report.ToString());

Console.WriteLine();

// --- Insert و Remove ---
var text = new StringBuilder("Hello World");
text.Insert(5, " Beautiful");
text.Remove(5, 10);  // حذف " Beautiful"
Console.WriteLine(text.ToString());

Console.WriteLine();

// --- Replace در StringBuilder ---
var html = new StringBuilder("<div>content</div>");
html.Replace("<div>", "<section>").Replace("</div>", "</section>");
Console.WriteLine(html.ToString());

// --- Clear ---
var temp = new StringBuilder("some data");
temp.Clear();
Console.WriteLine($"بعد Clear: Length={temp.Length}");
