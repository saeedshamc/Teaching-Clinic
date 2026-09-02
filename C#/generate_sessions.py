#!/usr/bin/env python3
"""Generate C# course sessions 02-50 with README, examples, and exercises."""

from pathlib import Path

ROOT = Path(__file__).resolve().parent

SESSIONS = [
    {
        "num": 2,
        "slug": "session-02-variables-types",
        "title": "متغیرها و انواع داده",
        "objectives": [
            "تعریف متغیر با انواع پایه",
            "استفاده از var و const",
            "تبدیل نوع (casting)",
            "درک type safety در C#",
        ],
        "theory": "در C# هر متغیر نوع مشخص دارد: int، double، string، bool و ...",
        "examples": [
            (
                "01_basic_types.cs",
                """// جلسه ۲ — انواع پایه
int age = 25;
double price = 19.99;
string name = "Ali";
bool isActive = true;

Console.WriteLine($"نام: {name}, سن: {age}, قیمت: {price}, فعال: {isActive}");
""",
            ),
            (
                "02_var_and_const.cs",
                """// var: نوع از مقدار اولیه استنتاج می‌شود
var city = "Tehran";
var count = 10;

const double TaxRate = 0.09;
Console.WriteLine($"{city} - count={count} - tax={TaxRate}");
""",
            ),
            (
                "03_casting.cs",
                """// تبدیل صریح و TryParse
double d = 9.8;
int rounded = (int)d;

string input = "42";
if (int.TryParse(input, out int number))
{
    Console.WriteLine($"عدد: {number}");
}
""",
            ),
        ],
        "exercise_q": "برنامه‌ای بنویسید که دو عدد از کاربر بگیرد، جمع و میانگین را چاپ کند.",
        "exercise_a": """Console.Write("عدد اول: ");
int.TryParse(Console.ReadLine(), out int a);
Console.Write("عدد دوم: ");
int.TryParse(Console.ReadLine(), out int b);
Console.WriteLine($"جمع: {a + b}");
Console.WriteLine($"میانگین: {(a + b) / 2.0}");
""",
    },
    {
        "num": 3,
        "slug": "session-03-operators",
        "title": "عملگرها",
        "objectives": [
            "عملگرهای حسابی و مقایسه‌ای",
            "عملگرهای منطقی",
            "عملگرهای ترکیبی += و ++",
        ],
        "theory": "عملگرها برای محاسبه و تصمیم‌گیری در C# استفاده می‌شوند.",
        "examples": [
            (
                "01_arithmetic.cs",
                """int a = 10, b = 3;
Console.WriteLine($"a+b={a+b}, a-b={a-b}, a*b={a*b}, a/b={a/b}, a%b={a%b}");
""",
            ),
            (
                "02_comparison_logical.cs",
                """int x = 5, y = 8;
Console.WriteLine(x < y);
Console.WriteLine(x >= 5 && y > 0);
Console.WriteLine(x == 5 || y == 1);
""",
            ),
            (
                "03_compound.cs",
                """int score = 10;
score += 5;
score++;
Console.WriteLine(score);
""",
            ),
        ],
        "exercise_q": "برنامه‌ای بنویسید که عدد را بگیرد و زوج/فرد بودن را با عملگر % مشخص کند.",
        "exercise_a": """Console.Write("عدد: ");
int.TryParse(Console.ReadLine(), out int n);
Console.WriteLine(n % 2 == 0 ? "زوج" : "فرد");
""",
    },
    {
        "num": 4,
        "slug": "session-04-control-flow",
        "title": "ساختارهای شرطی",
        "objectives": ["if/else", "switch", "switch expression", "ternary operator"],
        "theory": "با شرط‌ها مسیر اجرای برنامه را کنترل می‌کنیم.",
        "examples": [
            (
                "01_if_else.cs",
                """Console.Write("نمره: ");
int.TryParse(Console.ReadLine(), out int score);
if (score >= 50) Console.WriteLine("قبول");
else Console.WriteLine("مردود");
""",
            ),
            (
                "02_switch.cs",
                """Console.Write("روز (1-7): ");
int.TryParse(Console.ReadLine(), out int day);
switch (day)
{
    case 6:
    case 7:
        Console.WriteLine("آخر هفته");
        break;
    default:
        Console.WriteLine("روز کاری");
        break;
}
""",
            ),
            (
                "03_ternary.cs",
                """int age = 20;
string status = age >= 18 ? "بزرگسال" : "نوجوان";
Console.WriteLine(status);
""",
            ),
        ],
        "exercise_q": "ماشین‌حساب ساده با switch برای + - * / بنویسید.",
        "exercise_a": """Console.Write("a: "); int.TryParse(Console.ReadLine(), out int a);
Console.Write("b: "); int.TryParse(Console.ReadLine(), out int b);
Console.Write("op (+,-,*,/): "); string? op = Console.ReadLine();
int result = op switch
{
    "+" => a + b,
    "-" => a - b,
    "*" => a * b,
    "/" => b == 0 ? 0 : a / b,
    _ => 0
};
Console.WriteLine($"نتیجه: {result}");
""",
    },
    {
        "num": 5,
        "slug": "session-05-loops",
        "title": "حلقه‌ها",
        "objectives": ["for", "while", "do-while", "foreach", "break/continue"],
        "theory": "حلقه‌ها تکرار عملیات را خودکار می‌کنند.",
        "examples": [
            (
                "01_for_loop.cs",
                """for (int i = 1; i <= 5; i++)
{
    Console.WriteLine($"شمارش: {i}");
}
""",
            ),
            (
                "02_while_do.cs",
                """int n = 3;
while (n > 0)
{
    Console.WriteLine(n);
    n--;
}
""",
            ),
            (
                "03_foreach.cs",
                """string[] names = { "Ali", "Sara", "Reza" };
foreach (var name in names)
{
    Console.WriteLine(name);
}
""",
            ),
        ],
        "exercise_q": "جدول ضرب عدد 1 تا 10 را چاپ کنید.",
        "exercise_a": """for (int i = 1; i <= 10; i++)
{
    Console.WriteLine($"5 x {i} = {5 * i}");
}
""",
    },
    {
        "num": 6,
        "slug": "session-06-methods",
        "title": "متدها",
        "objectives": ["تعریف متد", "پارامتر", "return", "method overloading"],
        "theory": "متدها منطق قابل استفاده مجدد را کapsule می‌کنند.",
        "examples": [
            (
                "01_basic_method.cs",
                """int Add(int a, int b) => a + b;
Console.WriteLine(Add(2, 3));
""",
            ),
            (
                "02_parameters.cs",
                """void Greet(string name, int times = 1)
{
    for (int i = 0; i < times; i++) Console.WriteLine($"سلام {name}");
}
Greet("Ali", 2);
""",
            ),
            (
                "03_overload.cs",
                """int Max(int a, int b) => a > b ? a : b;
double Max(double a, double b) => a > b ? a : b;
Console.WriteLine(Max(3, 8));
Console.WriteLine(Max(3.5, 2.1));
""",
            ),
        ],
        "exercise_q": "متدی بنویسید که عدد اول بودن را بررسی کند.",
        "exercise_a": """bool IsPrime(int n)
{
    if (n < 2) return false;
    for (int i = 2; i * i <= n; i++)
        if (n % i == 0) return false;
    return true;
}
Console.WriteLine(IsPrime(17));
""",
    },
    {
        "num": 7,
        "slug": "session-07-arrays",
        "title": "آرایه‌ها",
        "objectives": ["آرایه یک‌بعدی", "چندبعدی", "Index", "Length"],
        "theory": "آرایه مجموعه‌ای از عناصر هم‌نوع با اندیس ثابت است.",
        "examples": [
            (
                "01_array_basics.cs",
                """int[] nums = { 10, 20, 30 };
Console.WriteLine(nums[0]);
Console.WriteLine(nums.Length);
""",
            ),
            (
                "02_loop_array.cs",
                """int[] scores = { 90, 75, 88 };
int sum = 0;
foreach (var s in scores) sum += s;
Console.WriteLine($"میانگین: {sum / (double)scores.Length}");
""",
            ),
            (
                "03_multidimensional.cs",
                """int[,] grid = { { 1, 2 }, { 3, 4 } };
Console.WriteLine(grid[1, 0]);
""",
            ),
        ],
        "exercise_q": "بزرگترین عدد آرایه را پیدا کنید.",
        "exercise_a": """int[] data = { 4, 9, 2, 7 };
int max = data[0];
foreach (var x in data) if (x > max) max = x;
Console.WriteLine(max);
""",
    },
    {
        "num": 8,
        "slug": "session-08-strings",
        "title": "کار با رشته‌ها",
        "objectives": ["interpolation", "متدهای string", "StringBuilder مقدمه"],
        "theory": "string در C# immutable است؛ برای تغییر، رشته جدید ساخته می‌شود.",
        "examples": [
            (
                "01_interpolation.cs",
                """string name = "Sara";
int age = 22;
Console.WriteLine($"نام: {name}, سن: {age}");
""",
            ),
            (
                "02_string_methods.cs",
                """string text = "  Hello C#  ";
Console.WriteLine(text.Trim().ToUpper());
Console.WriteLine(text.Contains("C#"));
Console.WriteLine(text.Replace("Hello", "Hi"));
""",
            ),
            (
                "03_split_join.cs",
                """string csv = "Ali,Sara,Reza";
string[] parts = csv.Split(',');
Console.WriteLine(string.Join(" | ", parts));
""",
            ),
        ],
        "exercise_q": "تعداد حروف صدا دار کلمه ورودی را بشمارید.",
        "exercise_a": """Console.Write("کلمه: ");
string? w = Console.ReadLine() ?? "";
int count = 0;
foreach (char c in w.ToLower())
    if ("aeiou".Contains(c)) count++;
Console.WriteLine(count);
""",
    },
    {
        "num": 9,
        "slug": "session-09-null-nullable",
        "title": "Null و Nullable Types",
        "objectives": ["null reference", "nullable value types", "?? و ?."],
        "theory": "Nullable Reference Types از باگ‌های null جلوگیری می‌کنند.",
        "examples": [
            (
                "01_nullable_value.cs",
                """int? maybe = null;
Console.WriteLine(maybe ?? 0);
maybe = 5;
Console.WriteLine(maybe.Value);
""",
            ),
            (
                "02_null_coalescing.cs",
                """string? nickname = null;
string display = nickname ?? "کاربر";
Console.WriteLine(display);
""",
            ),
            (
                "03_null_conditional.cs",
                """string? text = null;
Console.WriteLine(text?.Length ?? 0);
""",
            ),
        ],
        "exercise_q": "متدی بنویسید که null یا خالی بودن رشته را امن بررسی کند.",
        "exercise_a": """bool IsNullOrEmpty(string? value) => string.IsNullOrWhiteSpace(value);
Console.WriteLine(IsNullOrEmpty("  "));
Console.WriteLine(IsNullOrEmpty("ok"));
""",
    },
    {
        "num": 10,
        "slug": "session-10-exceptions",
        "title": "مدیریت خطا",
        "objectives": ["try/catch/finally", "Exception types", "throw"],
        "theory": "Exception handling باعث پایداری برنامه در خطا می‌شود.",
        "examples": [
            (
                "01_try_catch.cs",
                """try
{
    Console.Write("a: "); int a = int.Parse(Console.ReadLine()!);
    Console.Write("b: "); int b = int.Parse(Console.ReadLine()!);
    Console.WriteLine(a / b);
}
catch (DivideByZeroException)
{
    Console.WriteLine("تقسیم بر صفر!");
}
catch (FormatException)
{
    Console.WriteLine("ورودی عددی نیست.");
}
""",
            ),
            (
                "02_finally.cs",
                """try { Console.WriteLine("کار"); }
finally { Console.WriteLine("همیشه اجرا می‌شود"); }
""",
            ),
            (
                "03_throw_custom.cs",
                """void ValidateAge(int age)
{
    if (age < 0) throw new ArgumentOutOfRangeException(nameof(age));
}
ValidateAge(-1);
""",
            ),
        ],
        "exercise_q": "تبدیل امن string به int با پیام خطای فارسی بنویسید.",
        "exercise_a": """bool TryReadInt(string? input, out int value)
{
    return int.TryParse(input, out value);
}
Console.Write("عدد: ");
if (!TryReadInt(Console.ReadLine(), out int n)) Console.WriteLine("خطا");
else Console.WriteLine(n);
""",
    },
    {
        "num": 11,
        "slug": "session-11-collections",
        "title": "مجموعه‌ها (Collections)",
        "objectives": ["List<T>", "Dictionary<K,V>", "HashSet", "Queue"],
        "theory": "Generic collections انعطاف بیشتری از آرایه دارند.",
        "examples": [
            (
                "01_list.cs",
                """var list = new List<string> { "A", "B" };
list.Add("C");
foreach (var item in list) Console.WriteLine(item);
""",
            ),
            (
                "02_dictionary.cs",
                """var ages = new Dictionary<string, int>
{
    ["Ali"] = 25,
    ["Sara"] = 30
};
Console.WriteLine(ages["Ali"]);
""",
            ),
            (
                "03_hashset.cs",
                """var set = new HashSet<int> { 1, 2, 2, 3 };
Console.WriteLine(set.Count);
""",
            ),
        ],
        "exercise_q": "لیست خرید با افزودن/حذف آیتم بسازید.",
        "exercise_a": """var cart = new List<string>();
cart.Add("Milk");
cart.Add("Bread");
cart.Remove("Milk");
foreach (var i in cart) Console.WriteLine(i);
""",
    },
    {
        "num": 12,
        "slug": "session-12-beginner-project",
        "title": "پروژه مبتدی: ماشین‌حساب CLI",
        "objectives": ["ترکیب مفاهیم جلسات 1-11", "منوی تعاملی", "مدیریت خطا"],
        "theory": "پروژه کوچک برای تثبیت مبانی ضروری است.",
        "examples": [
            (
                "01_menu_loop.cs",
                """while (true)
{
    Console.WriteLine("1.Add 2.Sub 0.Exit");
    var op = Console.ReadLine();
    if (op == "0") break;
}
""",
            ),
            (
                "02_calculator_core.cs",
                """double Calc(double a, double b, string op) => op switch
{
    "+" => a + b,
    "-" => a - b,
    "*" => a * b,
    "/" => b == 0 ? throw new DivideByZeroException() : a / b,
    _ => throw new ArgumentException("op")
};
Console.WriteLine(Calc(10, 2, "/"));
""",
            ),
            (
                "03_full_calculator.cs",
                """while (true)
{
    Console.Write("a: "); if (!double.TryParse(Console.ReadLine(), out double a)) continue;
    Console.Write("b: "); if (!double.TryParse(Console.ReadLine(), out double b)) continue;
    Console.Write("op: "); string? op = Console.ReadLine();
    try
    {
        double r = op switch
        {
            "+" => a + b,
            "-" => a - b,
            "*" => a * b,
            "/" => a / b,
            _ => throw new Exception("نامعتبر")
        };
        Console.WriteLine(r);
    }
    catch (Exception ex) { Console.WriteLine(ex.Message); }
    Console.WriteLine("---");
}
""",
            ),
        ],
        "exercise_q": "به ماشین‌حساب، تاریخچه 5 عملیات آخر را اضافه کنید.",
        "exercise_a": """var history = new Queue<string>();
history.Enqueue("2+2=4");
history.Enqueue("5*3=15");
foreach (var h in history) Console.WriteLine(h);
""",
    },
]

# Sessions 13-50 - add more compact definitions
MORE = [
    (13, "session-13-classes", "کلاس و شیء", "class, object, field, method"),
    (14, "session-14-properties-constructors", "Property و Constructor", "get/set, ctor"),
    (15, "session-15-inheritance", "وراثت", "base, derived"),
    (16, "session-16-polymorphism", "چندریختی", "virtual, override"),
    (17, "session-17-interfaces", "Interface", "IEnumerable, contract"),
    (18, "session-18-abstract-classes", "کلاس انتزاعی", "abstract"),
    (19, "session-19-encapsulation", "کپسوله‌سازی", "private, public"),
    (20, "session-20-oop-project", "پروژه OOP", "Library system"),
    (21, "session-21-generics", "Generic", "List<T>, constraints"),
    (22, "session-22-delegates", "Delegate", "Func, Action"),
    (23, "session-23-events", "Event", "EventHandler"),
    (24, "session-24-linq-basics", "LINQ پایه", "Where, Select"),
    (25, "session-25-linq-advanced", "LINQ پیشرفته", "GroupBy, Join"),
    (26, "session-26-extension-methods", "Extension Methods", "static extension"),
    (27, "session-27-async-await", "Async/Await", "Task, await"),
    (28, "session-28-file-io", "File I/O", "File, StreamReader"),
    (29, "session-29-json-serialization", "JSON", "System.Text.Json"),
    (30, "session-30-advanced-project", "پروژه Task Manager", "CRUD in memory"),
    (31, "session-31-namespaces-assemblies", "Namespace", "using, assembly"),
    (32, "session-32-nuget", "NuGet", "package restore"),
    (33, "session-33-unit-testing", "Unit Testing", "xUnit"),
    (34, "session-34-logging", "Logging", "ILogger"),
    (35, "session-35-configuration", "Configuration", "appsettings"),
    (36, "session-36-dependency-injection", "DI", "IServiceCollection"),
    (37, "session-37-ef-core", "EF Core", "DbContext"),
    (38, "session-38-web-api", "Web API", "Minimal API"),
    (39, "session-39-aspnet-mvc", "ASP.NET MVC", "Controller"),
    (40, "session-40-web-project", "پروژه وب", "REST API"),
    (41, "session-41-design-patterns", "Design Patterns", "Singleton"),
    (42, "session-42-solid", "SOLID", "SRP, OCP"),
    (43, "session-43-repository-pattern", "Repository", "data layer"),
    (44, "session-44-middleware", "Middleware", "pipeline"),
    (45, "session-45-authentication", "Authentication", "JWT intro"),
    (46, "session-46-rest-best-practices", "REST", "status codes"),
    (47, "session-47-performance", "Performance", "benchmark"),
    (48, "session-48-debugging", "Debugging", "breakpoints"),
    (49, "session-49-deployment", "Deployment", "dotnet publish"),
    (50, "session-50-final-capstone", "پروژه نهایی", "Product management"),
]

EXAMPLE_TEMPLATES = {
    13: [
        (
            "01_class_basics.cs",
            """class Person
{
    public string Name = "";
    public void SayHello() => Console.WriteLine($"Hi {Name}");
}
var p = new Person { Name = "Ali" };
p.SayHello();
""",
        ),
        (
            "02_multiple_objects.cs",
            """class Counter { public int Value; }
var c1 = new Counter(); c1.Value = 1;
var c2 = new Counter(); c2.Value = 2;
Console.WriteLine(c1.Value + c2.Value);
""",
        ),
        (
            "03_object_array.cs",
            """class Book { public string Title = ""; }
var books = new Book[] { new() { Title = "C#" }, new() { Title = "LINQ" } };
foreach (var b in books) Console.WriteLine(b.Title);
""",
        ),
    ],
    14: [
        (
            "01_properties.cs",
            """class User
{
    public string Name { get; set; } = "";
    public int Age { get; private set; }
    public void SetAge(int age) => Age = age >= 0 ? age : 0;
}
var u = new User { Name = "Sara" }; u.SetAge(20);
Console.WriteLine($"{u.Name} {u.Age}");
""",
        ),
        (
            "02_constructors.cs",
            """class Product
{
    public string Name { get; }
    public Product(string name) => Name = name;
}
var p = new Product("Phone");
Console.WriteLine(p.Name);
""",
        ),
        (
            "03_readonly.cs",
            """class Config
{
    public readonly string Env;
    public Config(string env) => Env = env;
}
Console.WriteLine(new Config("dev").Env);
""",
        ),
    ],
    27: [
        (
            "01_async_basic.cs",
            """async Task DelayHello()
{
    await Task.Delay(500);
    Console.WriteLine("Done");
}
await DelayHello();
""",
        ),
        (
            "02_async_return.cs",
            """async Task<int> ComputeAsync() { await Task.Delay(100); return 42; }
int value = await ComputeAsync();
Console.WriteLine(value);
""",
        ),
        (
            "03_parallel_tasks.cs",
            """var t1 = Task.Run(async () => { await Task.Delay(200); return 1; });
var t2 = Task.Run(async () => { await Task.Delay(100); return 2; });
int[] results = await Task.WhenAll(t1, t2);
Console.WriteLine(string.Join(",", results));
""",
        ),
    ],
    38: [
        (
            "01_minimal_api_note.cs",
            """// این مثال مفهومی است — برای اجرا نیاز به پروژه Web SDK دارید
// var builder = WebApplication.CreateBuilder(args);
// var app = builder.Build();
// app.MapGet("/", () => "Hello API");
// app.Run();
Console.WriteLine("Minimal API در پروژه Web اجرا می‌شود.");
""",
        ),
        (
            "02_route_concept.cs",
            """var routes = new Dictionary<string, string>
{
    ["/users"] = "GET users",
    ["/users/{id}"] = "GET user by id"
};
foreach (var r in routes) Console.WriteLine($"{r.Key} => {r.Value}");
""",
        ),
        (
            "03_dto_concept.cs",
            """record UserDto(int Id, string Name);
var user = new UserDto(1, "Ali");
Console.WriteLine(user);
""",
        ),
    ],
    50: [
        (
            "01_domain_model.cs",
            """record Product(int Id, string Name, decimal Price);
var products = new List<Product>
{
    new(1, "Mouse", 25),
    new(2, "Keyboard", 45)
};
foreach (var p in products) Console.WriteLine(p);
""",
        ),
        (
            "02_service_layer.cs",
            """class ProductService
{
    private readonly List<(int Id, string Name)> _items = new();
    public void Add(int id, string name) => _items.Add((id, name));
    public IEnumerable<string> GetAll() => _items.Select(x => x.Name);
}
var svc = new ProductService(); svc.Add(1, "Laptop");
foreach (var n in svc.GetAll()) Console.WriteLine(n);
""",
        ),
        (
            "03_capstone_menu.cs",
            """var products = new Dictionary<int, string> { [1] = "Book" };
Console.WriteLine("1.List 2.Add");
var op = Console.ReadLine();
if (op == "1") foreach (var p in products) Console.WriteLine($"{p.Key}:{p.Value}");
""",
        ),
    ],
}


def generic_examples(num: int, title: str) -> list[tuple[str, str]]:
    if num in EXAMPLE_TEMPLATES:
        return EXAMPLE_TEMPLATES[num]
    slug = title.split()[0] if title else "topic"
    return [
        (
            "01_concept.cs",
            f"""// جلسه {num} — {title}
Console.WriteLine("مثال 1: {title}");
Console.WriteLine("این مثال مفهوم اصلی جلسه را نشان می‌دهد.");
""",
        ),
        (
            "02_practice.cs",
            f"""// جلسه {num} — تمرین عملی
var data = new[] {{ 1, 2, 3 }};
foreach (var x in data) Console.WriteLine(x);
""",
        ),
        (
            "03_real_world.cs",
            f"""// جلسه {num} — کاربرد واقعی
Console.WriteLine("در پروژه واقعی، {title} برای کد تمیزتر استفاده می‌شود.");
""",
        ),
    ]


def write_session(num: int, slug: str, title: str, objectives=None, theory="", examples=None, exercise_q="", exercise_a=""):
    if objectives is None:
        objectives = [f"درک {title}", "اجرای مثال‌های جلسه", "انجام تمرین عملی"]
    if examples is None:
        examples = generic_examples(num, title)
    if not exercise_q:
        exercise_q = f"یک برنامه کوچک مرتبط با «{title}» بنویسید و خروجی آن را تست کنید."
    if not exercise_a:
        exercise_a = f'Console.WriteLine("پاسخ نمونه جلسه {num}: {title}");\n'

    base = ROOT / slug
    (base / "examples").mkdir(parents=True, exist_ok=True)
    (base / "exercises").mkdir(parents=True, exist_ok=True)

    prev_link = f"../session-{num-1:02d}-*/" if num > 1 else "../README.md"
    next_num = num + 1
    next_slug = MORE[next_num - 13][1] if 13 <= num < 50 else None
    next_link = f"../{next_slug}/" if next_slug else ""

    obj_md = "\n".join(f"- {o}" for o in objectives)
    files_md = "\n".join(
        f"| [examples/{name}](./examples/{name}) | مثال {i} |"
        for i, (name, _) in enumerate(examples, 1)
    )

    readme = f"""# جلسه {num}: {title}

## اهداف یادگیری

{obj_md}

---

## مفاهیم این جلسه

{theory or f"در این جلسه با «{title}» در C# آشنا می‌شوید."}

---

## فایل‌های این جلسه

| فایل | توضیح |
|------|-------|
{files_md}
| [exercises/question.md](./exercises/question.md) | تمرین |
| [exercises/exercise_01.cs](./exercises/exercise_01.cs) | پاسخ نمونه |

---

## اجرای مثال

```powershell
./run-example.ps1 {slug}/examples/{examples[0][0]}
```

---

## اشتباهات رایج

- اجرای فایل بدون SDK نصب‌شده
- فراموش کردن `;` در انتهای statement
- عدم تست تمرین بعد از نوشتن کد

---

## نکات حرفه‌ای

- کد هر مثال را خط‌به‌خط اجرا و تحلیل کنید
- تمرین را اول خودتان حل کنید، بعد پاسخ را ببینید

---

**جلسه قبل:** [{num - 1}]({prev_link})  
"""
    if next_link:
        readme += f"**جلسه بعد:** [{num + 1}]({next_link})\n"

    (base / "README.md").write_text(readme, encoding="utf-8")

    for name, code in examples:
        header = f"// ============================================================\n// جلسه {num} — {title}\n// فایل: {name}\n// ============================================================\n"
        (base / "examples" / name).write_text(header + code.strip() + "\n", encoding="utf-8")

    (base / "exercises" / "question.md").write_text(
        f"# تمرین جلسه {num}\n\n## صورت سوال\n\n{exercise_q}\n\n---\n\nراهنمای حل: [exercise_01.cs](./exercise_01.cs)\n",
        encoding="utf-8",
    )
    ex_header = f"// ============================================================\n// تمرین جلسه {num} — پاسخ نمونه\n// ============================================================\n"
    (base / "exercises" / "exercise_01.cs").write_text(ex_header + exercise_a.strip() + "\n", encoding="utf-8")


def main():
    for s in SESSIONS:
        write_session(**{k: s[k] for k in s if k != "num"}, num=s["num"])

    for num, slug, title, theory in MORE:
        write_session(num=num, slug=slug, title=title, theory=theory)

    print(f"Generated sessions 02-50 under {ROOT}")


if __name__ == "__main__":
    main()
