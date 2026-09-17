// ============================================================
// جلسه ۲۵ — Join
// فایل: 01_join.cs
// ============================================================

class Order
{
    public int OrderId { get; set; }
    public int CustomerId { get; set; }
    public double Amount { get; set; }
}

class Customer
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public string City { get; set; } = "";
}

class Product
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public double Price { get; set; }
}

class OrderItem
{
    public int OrderId { get; set; }
    public int ProductId { get; set; }
    public int Quantity { get; set; }
}

var customers = new List<Customer>
{
    new() { Id = 1, Name = "علی", City = "تهران" },
    new() { Id = 2, Name = "مریم", City = "اصفهان" },
    new() { Id = 3, Name = "رضا", City = "تهران" }
};

var orders = new List<Order>
{
    new() { OrderId = 101, CustomerId = 1, Amount = 500_000 },
    new() { OrderId = 102, CustomerId = 2, Amount = 1_200_000 },
    new() { OrderId = 103, CustomerId = 1, Amount = 300_000 },
    new() { OrderId = 104, CustomerId = 3, Amount = 800_000 }
};

// Inner Join — Method Syntax
var orderDetails = orders.Join(customers,
    o => o.CustomerId,
    c => c.Id,
    (o, c) => new { o.OrderId, c.Name, c.City, o.Amount });

Console.WriteLine("=== سفارشات و مشتریان ===");
foreach (var item in orderDetails)
    Console.WriteLine($"  #{item.OrderId}: {item.Name} ({item.City}) — {item.Amount:N0}");

// Query Syntax
var tehranOrders = from o in orders
                   join c in customers on o.CustomerId equals c.Id
                   where c.City == "تهران"
                   select new { o.OrderId, c.Name, o.Amount };

Console.WriteLine("\n=== سفارشات تهران ===");
foreach (var item in tehranOrders)
    Console.WriteLine($"  #{item.OrderId}: {item.Name} — {item.Amount:N0}");

// GroupJoin — Left Join (همه مشتریان حتی بدون سفارش)
var customerOrders = customers.GroupJoin(orders,
    c => c.Id,
    o => o.CustomerId,
    (c, orderGroup) => new
    {
        c.Name,
        OrderCount = orderGroup.Count(),
        TotalAmount = orderGroup.Sum(o => o.Amount)
    });

Console.WriteLine("\n=== آمار مشتریان ===");
foreach (var co in customerOrders)
    Console.WriteLine($"  {co.Name}: {co.OrderCount} سفارش, جمع={co.TotalAmount:N0}");
