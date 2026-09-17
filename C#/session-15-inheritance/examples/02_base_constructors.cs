// ============================================================
// جلسه ۱۵ — base و Constructor زنجیره‌ای
// فایل: 02_base_constructors.cs
// ============================================================

class Person
{
    public string Name { get; }
    public int BirthYear { get; }

    public Person(string name, int birthYear)
    {
        Name = name;
        BirthYear = birthYear;
    }

    public int GetAge() => DateTime.Now.Year - BirthYear;
}

class Student : Person
{
    public string StudentId { get; }
    public double GPA { get; set; }

    // base() — فراخوانی constructor والد
    public Student(string name, int birthYear, string studentId)
        : base(name, birthYear)
    {
        StudentId = studentId;
    }

    public Student(string name, int birthYear, string studentId, double gpa)
        : this(name, birthYear, studentId)
    {
        GPA = gpa;
    }

    public void PrintInfo()
    {
        // base.Member — دسترسی صریح به والد
        Console.WriteLine($"{base.Name} ({base.GetAge()} سال) — ID: {StudentId}, GPA: {GPA}");
    }
}

var s = new Student("علی احمدی", 2000, "S1401", 17.5);
s.PrintInfo();
