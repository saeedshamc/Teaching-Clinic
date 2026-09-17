// ============================================================
// جلسه ۱۱ — HashSet<T>
// ============================================================

// --- unique elements ---
var numbers = new HashSet<int> { 1, 2, 3, 3, 2, 1, 4 };
Console.WriteLine($"Unique: {string.Join(", ", numbers)}"); // 1, 2, 3, 4

Console.WriteLine();

// --- Add ---
bool added1 = numbers.Add(5);   // true
bool added2 = numbers.Add(3);   // false — already exists
Console.WriteLine($"Add 5: {added1}, Add 3: {added2}");
Console.WriteLine(string.Join(", ", numbers));

Console.WriteLine();

// --- Contains — O(1) lookup ---
Console.WriteLine($"Contains 4: {numbers.Contains(4)}");
Console.WriteLine($"Contains 99: {numbers.Contains(99)}");

Console.WriteLine();

// --- Remove ---
numbers.Remove(2);
Console.WriteLine($"After remove 2: {string.Join(", ", numbers)}");

Console.WriteLine();

// --- حذف duplicate از List ---
var withDupes = new List<string> { "a", "b", "a", "c", "b", "d" };
var unique = new HashSet<string>(withDupes);
Console.WriteLine($"Unique strings: {string.Join(", ", unique)}");

Console.WriteLine();

// --- Set operations ---
var setA = new HashSet<int> { 1, 2, 3, 4, 5 };
var setB = new HashSet<int> { 4, 5, 6, 7, 8 };

setA.IntersectWith(setB);
Console.WriteLine($"Intersection: {string.Join(", ", setA)}"); // 4, 5

var setC = new HashSet<int> { 1, 2, 3 };
var setD = new HashSet<int> { 3, 4, 5 };
setC.UnionWith(setD);
Console.WriteLine($"Union: {string.Join(", ", setC)}"); // 1,2,3,4,5

Console.WriteLine();

// --- کاربرد: visited tracking ---
var visited = new HashSet<string>();
string[] pages = { "home", "about", "home", "contact", "about" };
foreach (string page in pages)
{
    if (visited.Add(page))
        Console.WriteLine($"  First visit: {page}");
    else
        Console.WriteLine($"  Already visited: {page}");
}
