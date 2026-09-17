// ============================================================
// جلسه ۴۶ — HTTP Methods و Status Codes
// ============================================================

record Resource(string Id, string Data, int Version);

class RestSimulator
{
    private readonly Dictionary<string, Resource> _store = new();

    public (int code, string msg) Get(string id)
    {
        if (!_store.ContainsKey(id)) return (404, "Not Found");
        return (200, $"OK: {_store[id].Data}");
    }

    public (int code, string msg, string? id) Post(string data)
    {
        var id = Guid.NewGuid().ToString()[..8];
        _store[id] = new Resource(id, data, 1);
        return (201, $"Created: {data}", id);
    }

    public (int code, string msg) Put(string id, string data)
    {
        if (!_store.ContainsKey(id)) return (404, "Not Found");
        var old = _store[id];
        _store[id] = old with { Data = data, Version = old.Version + 1 };
        return (200, $"Replaced: {data} (v{old.Version + 1})");
    }

    public (int code, string msg) Patch(string id, string partial)
    {
        if (!_store.ContainsKey(id)) return (404, "Not Found");
        var old = _store[id];
        _store[id] = old with { Data = old.Data + " + " + partial, Version = old.Version + 1 };
        return (200, $"Patched: {_store[id].Data}");
    }

    public (int code, string msg) Delete(string id)
    {
        if (!_store.Remove(id)) return (404, "Not Found");
        return (204, "No Content");
    }
}

var api = new RestSimulator();

Console.WriteLine("=== REST Methods ===");
var (c1, m1, id) = api.Post("Patient Ali");
Console.WriteLine($"  POST → {c1}: {m1}");

Console.WriteLine($"  GET  → {api.Get(id!).code}: {api.Get(id!).msg}");
Console.WriteLine($"  PUT  → {api.Put(id!, "Patient Ali Updated").code}: {api.Put(id!, "Patient Ali Updated").msg}");
Console.WriteLine($"  PATCH→ {api.Patch(id!, "phone added").code}: {api.Patch(id!, "phone added").msg}");
Console.WriteLine($"  DELETE→ {api.Delete(id!).code}: {api.Delete(id!).msg}");
Console.WriteLine($"  GET  → {api.Get(id!).code}: {api.Get(id!).msg}");
