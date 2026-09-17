// ============================================================
// جلسه ۲۹ — JSON: serialize و deserialize پایه
// ============================================================

using System.Text.Json;

var person = new Person { Name = "علی", Age = 28, IsActive = true };
string json = JsonSerializer.Serialize(person);
Console.WriteLine("JSON:");
Console.WriteLine(json);

string inputJson = """{"Name":"مریم","Age":32,"IsActive":false}""";
Person? loaded = JsonSerializer.Deserialize<Person>(inputJson);
Console.WriteLine($"\nDeserialize: {loaded?.Name}, {loaded?.Age} سال");

if (loaded is null)
    Console.WriteLine("Deserialize ناموفق!");

public class Person
{
    public string Name { get; set; } = "";
    public int Age { get; set; }
    public bool IsActive { get; set; }
}
