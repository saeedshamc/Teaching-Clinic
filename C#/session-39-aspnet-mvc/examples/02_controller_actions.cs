// ============================================================
// جلسه ۳۹ — ASP.NET MVC
// فایل: 02_controller_actions.cs — Action Methods و Routing
// ============================================================

// شبیه‌سازی IActionResult
enum ActionResultType { View, Redirect, NotFound, Json }

record ActionResult(ActionResultType Type, object? Data = null, string? Location = null);

class ProductController
{
    private readonly Dictionary<int, string> _products = new()
    {
        [1] = "لپ‌تاپ",
        [2] = "ماوس"
    };

    public ActionResult Index()
    {
        var list = _products.Select(kv => new { kv.Key, kv.Value }).ToList();
        return new ActionResult(ActionResultType.View, list);
    }

    public ActionResult Details(int id)
    {
        if (!_products.ContainsKey(id))
            return new ActionResult(ActionResultType.NotFound);
        return new ActionResult(ActionResultType.View, _products[id]);
    }

    public ActionResult Create(string name)
    {
        int newId = _products.Keys.Max() + 1;
        _products[newId] = name;
        return new ActionResult(ActionResultType.Redirect, Location: "Index");
    }

    public ActionResult GetJson(int id)
    {
        if (!_products.ContainsKey(id))
            return new ActionResult(ActionResultType.NotFound);
        return new ActionResult(ActionResultType.Json, new { id, name = _products[id] });
    }
}

var controller = new ProductController();

Console.WriteLine("GET /Product/Index");
var indexResult = controller.Index();
Console.WriteLine($"  → {indexResult.Type}, {indexResult.Data}");

Console.WriteLine("\nGET /Product/Details/2");
var detailsResult = controller.Details(2);
Console.WriteLine($"  → {detailsResult.Type}, {detailsResult.Data}");

Console.WriteLine("\nGET /Product/Details/99");
var notFound = controller.Details(99);
Console.WriteLine($"  → {notFound.Type}");

Console.WriteLine("\nPOST /Product/Create?name=هدفون");
var createResult = controller.Create("هدفون");
Console.WriteLine($"  → {createResult.Type} → {createResult.Location}");

Console.WriteLine("\nGET /Product/GetJson/1");
var jsonResult = controller.GetJson(1);
Console.WriteLine($"  → {jsonResult.Type}, {jsonResult.Data}");
