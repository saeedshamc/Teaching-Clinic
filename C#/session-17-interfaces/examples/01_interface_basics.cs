// ============================================================
// جلسه ۱۷ — Interface: مبانی
// فایل: 01_interface_basics.cs
// ============================================================

interface IPlayable
{
    string Title { get; }
    void Play();
    void Stop();
}

class MusicTrack : IPlayable
{
    public string Title { get; set; } = "";
    public string Artist { get; set; } = "";

    public void Play() => Console.WriteLine($"▶ {Artist} — {Title}");
    public void Stop() => Console.WriteLine($"⏹ توقف: {Title}");
}

class Podcast : IPlayable
{
    public string Title { get; set; } = "";
    public int Episode { get; set; }

    public void Play() => Console.WriteLine($"🎙️ اپیزود {Episode}: {Title}");
    public void Stop() => Console.WriteLine($"⏹ پادکست متوقف شد");
}

// یک متد — همه IPlayable
void PlayAll(IPlayable[] media)
{
    foreach (var item in media)
    {
        item.Play();
        item.Stop();
        Console.WriteLine();
    }
}

IPlayable[] playlist =
{
    new MusicTrack { Title = "آهنگ شب", Artist = "خواننده" },
    new Podcast { Title = "برنامه فناوری", Episode = 42 }
};

PlayAll(playlist);
