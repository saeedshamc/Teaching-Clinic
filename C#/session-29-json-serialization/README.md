# جلسه ۲۹: JSON Serialization — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- تبدیل object به JSON (serialize) و برعکس (deserialize)
- استفاده از `System.Text.Json`
- تنظیم `JsonSerializerOptions` (camelCase، indent، naming)
- کار با collection، nested object و nullable
- `[JsonPropertyName]` و `[JsonIgnore]`
- ذخیره/بارگذاری JSON از فایل

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | JSON چیست؟ |
| 10–25 | Serialize/Deserialize پایه |
| 25–40 | Options و naming |
| 40–55 | Attributes |
| 55–70 | فایل JSON + collection |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. JSON چیست؟

**JSON** (JavaScript Object Notation) — فرمت متنی برای تبادل داده:

```json
{
  "name": "علی",
  "age": 25,
  "active": true,
  "tags": ["csharp", "dotnet"]
}
```

در .NET از **`System.Text.Json`** استفاده می‌کنیم (built-in، سریع).

---

## ۲. Serialize — object → JSON

```csharp
var person = new Person { Name = "علی", Age = 25 };

string json = JsonSerializer.Serialize(person);
Console.WriteLine(json);
// {"Name":"علی","Age":25}
```

---

## ۳. Deserialize — JSON → object

```csharp
string json = """{"Name":"مریم","Age":30}""";
var person = JsonSerializer.Deserialize<Person>(json);
Console.WriteLine(person?.Name);
```

---

## ۴. JsonSerializerOptions

```csharp
var options = new JsonSerializerOptions
{
    WriteIndented = true,              // فرمت خوانا
    PropertyNamingPolicy = JsonNamingPolicy.CamelCase,  // name → name
    PropertyNameCaseInsensitive = true // deserialize case-insensitive
};

string json = JsonSerializer.Serialize(person, options);
```

---

## ۵. Attributes

```csharp
public class Product
{
    [JsonPropertyName("product_name")]
    public string Name { get; set; }

    [JsonIgnore]
    public string InternalCode { get; set; }
}
```

---

## ۶. Collection

```csharp
var users = new List<User>
{
    new() { Id = 1, Name = "علی" },
    new() { Id = 2, Name = "مریم" }
};

string json = JsonSerializer.Serialize(users, options);
var loaded = JsonSerializer.Deserialize<List<User>>(json);
```

---

## ۷. Nested Object

```csharp
public class Order
{
    public int Id { get; set; }
    public Customer Customer { get; set; }
    public List<OrderItem> Items { get; set; } = new();
}
```

---

## ۸. فایل JSON

```csharp
// ذخیره
string json = JsonSerializer.Serialize(data, options);
await File.WriteAllTextAsync("data.json", json);

// بارگذاری
string loaded = await File.ReadAllTextAsync("data.json");
var data = JsonSerializer.Deserialize<MyType>(loaded, options);
```

---

## ۹. Nullable و Default

```csharp
var person = JsonSerializer.Deserialize<Person>(json);
if (person is null)
    Console.WriteLine("deserialize ناموفق");

// مقدار پیش‌فرض property
public int Score { get; set; } = 0;
```

---

## ۱۰. JsonDocument — بدون class

```csharp
using JsonDocument doc = JsonDocument.Parse(json);
JsonElement root = doc.RootElement;
string name = root.GetProperty("name").GetString()!;
int age = root.GetProperty("age").GetInt32();
```

برای schema ثابت و performance — بدون تعریف class.

---

## ۱۱. System.Text.Json vs Newtonsoft

| ویژگی | System.Text.Json | Newtonsoft.Json |
|--------|------------------|-----------------|
| built-in | ✅ | NuGet |
| سرعت | سریع‌تر | کندتر |
| انعطاف | متوسط | بالا |
| ASP.NET Core default | ✅ | legacy |

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | serialize/deserialize |
| [02_practice.cs](./examples/02_practice.cs) | options و camelCase |
| [03_real_world.cs](./examples/03_real_world.cs) | save/load فایل |
| [04_attributes_collections.cs](./examples/04_attributes_collections.cs) | attributes و list |

---

## ۱۳. مثال واقعی — API Response

```csharp
public record ApiResponse<T>(bool Success, T? Data, string? Error);

var response = new ApiResponse<User>(true, user, null);
string json = JsonSerializer.Serialize(response, options);
// ارسال به client یا ذخیره در cache
```

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| property بدون public get/set | serialize نمی‌شود |
| circular reference | exception — `[JsonIgnore]` |
| naming mismatch | null بعد از deserialize |
| فراموش options در هر دو طرف | inconsistency |
| deserialize بدون null check | NullReferenceException |

---

## ۱۵. Record و JSON

```csharp
public record User(int Id, string Name, string Email);
// record positional — serialize/deserialize عادی کار می‌کند
```

---

## ۱۶. Enum

```csharp
JsonSerializer.Serialize(status, new JsonSerializerOptions
{
    Converters = { new JsonStringEnumConverter() }
});
// "Active" به جای 0
```

---

## ۱۷. تمرین کلاسی

1. کلاس `Book` → JSON → object
2. لیست کتاب‌ها serialize کنید
3. JSON را در `books.json` ذخیره و بارگذاری کنید
4. camelCase options

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۸. Validation JSON

```csharp
try
{
    var obj = JsonSerializer.Deserialize<Person>(badJson);
}
catch (JsonException ex)
{
    Console.WriteLine($"JSON نامعتبر: {ex.Message}");
}
```

---

## 📚 مطالعه تکمیلی

- [System.Text.Json overview](https://learn.microsoft.com/dotnet/standard/serialization/system-text-json-overview)
- [Migrate from Newtonsoft](https://learn.microsoft.com/dotnet/standard/serialization/system-text-json-migrate-from-newtonsoft-how-to)

---

**جلسه قبل:** [۲۸ — File I/O](../session-28-file-io/) | **بعد:** [۳۰ — پروژه پیشرفته](../session-30-advanced-project/)
