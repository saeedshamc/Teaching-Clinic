// ============================================================
// جلسه ۳ — مقایسه و منطق
// ============================================================

int x = 5, y = 8;
Console.WriteLine($"x==y: {x == y}");
Console.WriteLine($"x!=y: {x != y}");
Console.WriteLine($"x<y: {x < y}");

bool hasTicket = true;
bool isAdult = false;
bool canEnter = hasTicket && isAdult;
Console.WriteLine($"ورود مجاز: {canEnter}");

bool vip = true;
canEnter = (hasTicket && isAdult) || vip;
Console.WriteLine($"با VIP: {canEnter}");
