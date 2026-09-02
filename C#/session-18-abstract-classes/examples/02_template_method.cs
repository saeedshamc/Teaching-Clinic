// ============================================================
// جلسه ۱۸ — Template Method Pattern
// فایل: 02_template_method.cs
// ============================================================

abstract class DataExporter
{
    // Template Method — الگوریتم ثابت
    public void Export()
    {
        Console.WriteLine($"=== Export ({GetFormat()}) ===");
        var raw = FetchData();
        var formatted = FormatData(raw);
        WriteOutput(formatted);
        Console.WriteLine("✅ انجام شد\n");
    }

    protected abstract string GetFormat();
    protected abstract string FetchData();
    protected abstract string FormatData(string data);

    // hook — قابل override
    protected virtual void WriteOutput(string data) =>
        Console.WriteLine(data);
}

class CsvExporter : DataExporter
{
    protected override string GetFormat() => "CSV";
    protected override string FetchData() => "علی,25,تهران\nمریم,30,اصفهان";
    protected override string FormatData(string data) => "Name,Age,City\n" + data;
}

class JsonExporter : DataExporter
{
    protected override string GetFormat() => "JSON";
    protected override string FetchData() => """{"users":[{"name":"علی"},{"name":"مریم"}]}""";
    protected override string FormatData(string data) => data;
    protected override void WriteOutput(string data) =>
        Console.WriteLine($"[JSON FILE]\n{data}");
}

DataExporter[] exporters = { new CsvExporter(), new JsonExporter() };
foreach (var exporter in exporters)
    exporter.Export();
