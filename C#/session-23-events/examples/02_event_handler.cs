// ============================================================
// جلسه ۲۳ — EventHandler استاندارد
// فایل: 02_event_handler.cs
// ============================================================

class DownloadManager
{
    public event EventHandler<string>? DownloadCompleted;
    public event EventHandler<int>? ProgressChanged;

    public void Download(string fileName)
    {
        Console.WriteLine($"شروع دانلود: {fileName}");
        for (int i = 0; i <= 100; i += 25)
        {
            ProgressChanged?.Invoke(this, i);
        }
        DownloadCompleted?.Invoke(this, fileName);
    }
}

class StockMonitor
{
    public event EventHandler<double>? PriceAlert;

    public void UpdatePrice(string symbol, double price)
    {
        Console.WriteLine($"  {symbol}: {price:N0}");
        if (price > 50_000_000)
            PriceAlert?.Invoke(this, price);
    }
}

// Download
var dm = new DownloadManager();
dm.ProgressChanged += (sender, percent) =>
    Console.WriteLine($"  پیشرفت: {percent}%");
dm.DownloadCompleted += (sender, file) =>
    Console.WriteLine($"  ✅ {file} آماده است\n");
dm.Download("report.pdf");

// Stock
var monitor = new StockMonitor();
monitor.PriceAlert += (sender, price) =>
    Console.WriteLine($"  ⚠️ هشدار! قیمت: {price:N0}");
monitor.UpdatePrice("BTC", 45_000_000);
monitor.UpdatePrice("BTC", 55_000_000);
