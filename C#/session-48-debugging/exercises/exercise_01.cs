// ============================================================
// تمرین ۱ جلسه ۴۸ — Fix CalculateAverage (پاسخ نمونه)
// ============================================================

double CalculateAverage(int[] scores)
{
    if (scores.Length == 0) return 0;
    int sum = 0;
    for (int i = 0; i < scores.Length; i++) // fix: < not <=
        sum += scores[i];
    return (double)sum / scores.Length;
}

var avg = CalculateAverage(new[] { 80, 90, 100 });
Console.WriteLine($"Average: {avg} (expected: 90)");
