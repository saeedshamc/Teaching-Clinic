// ============================================================
// جلسه 10 — مدیریت خطا
// فایل: 03_throw_custom.cs
// ============================================================
void ValidateAge(int age)
{
    if (age < 0) throw new ArgumentOutOfRangeException(nameof(age));
}
ValidateAge(-1);
