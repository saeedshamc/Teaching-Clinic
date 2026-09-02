// ============================================================
// جلسه ۱۱ — Queue<T>
// ============================================================

// --- FIFO: First In, First Out ---
var printQueue = new Queue<string>();

printQueue.Enqueue("سند1.pdf");
printQueue.Enqueue("سند2.pdf");
printQueue.Enqueue("سند3.pdf");

Console.WriteLine($"صف چاپ: {printQueue.Count} سند");
Console.WriteLine();

// --- Dequeue — از front ---
while (printQueue.Count > 0)
{
    string doc = printQueue.Dequeue();
    Console.WriteLine($"  🖨️ چاپ: {doc} (باقی: {printQueue.Count})");
}

Console.WriteLine();

// --- Peek — نگاه بدون حذف ---
var taskQueue = new Queue<Action>();
taskQueue.Enqueue(() => Console.WriteLine("  Task 1"));
taskQueue.Enqueue(() => Console.WriteLine("  Task 2"));
taskQueue.Enqueue(() => Console.WriteLine("  Task 3"));

Console.WriteLine("=== Peek ===");
Console.WriteLine($"Next: Task در صف (Count={taskQueue.Count})");
Action? next = taskQueue.Peek();
next?.Invoke();
Console.WriteLine($"After peek, Count={taskQueue.Count}");

Console.WriteLine("\n=== Dequeue all ===");
while (taskQueue.Count > 0)
    taskQueue.Dequeue().Invoke();

Console.WriteLine();

// --- شبیه‌سازی صف مشتری ---
var customerQueue = new Queue<string>();
string[] customers = { "علی", "مریم", "رضا", "سارا" };
foreach (string c in customers)
    customerQueue.Enqueue(c);

Console.WriteLine("=== پذیرش مشتری ===");
int served = 0;
while (customerQueue.Count > 0)
{
    string customer = customerQueue.Dequeue();
    served++;
    Console.WriteLine($"  #{served}: خدمت به {customer}");
}
Console.WriteLine($"کل خدمت‌شده: {served}");

Console.WriteLine();

// --- Queue vs Stack ---
Console.WriteLine("Queue = FIFO (صف)");
Console.WriteLine("Stack = LIFO (پشته) — Push/Pop");

var stack = new Stack<string>();
stack.Push("اول");
stack.Push("دوم");
Console.WriteLine($"Stack Pop: {stack.Pop()}"); // دوم — LIFO
