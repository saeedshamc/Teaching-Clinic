// تمرین ۱–۳

interface IShape { double GetArea(); double GetPerimeter(); }
class Circle : IShape { public double R; public double GetArea() => Math.PI * R * R; public double GetPerimeter() => 2 * Math.PI * R; }
class Rectangle : IShape { public double W, H; public double GetArea() => W * H; public double GetPerimeter() => 2 * (W + H); }

interface INotifier { void Send(string message); }
class EmailNotifier : INotifier { public void Send(string m) => Console.WriteLine($"📧 {m}"); }
class SmsNotifier : INotifier { public void Send(string m) => Console.WriteLine($"📱 {m}"); }

class Student : IComparable<Student>
{
    public string Name { get; set; } = "";
    public double GPA { get; set; }
    public int CompareTo(Student? other) => other == null ? 1 : GPA.CompareTo(other.GPA);
}

IShape[] shapes = { new Circle { R = 3 }, new Rectangle { W = 4, H = 5 } };
foreach (var s in shapes) Console.WriteLine($"مساحت: {s.GetArea():F2}");

INotifier[] notifiers = { new EmailNotifier(), new SmsNotifier() };
foreach (var n in notifiers) n.Send("پیام تست");

var students = new List<Student> { new() { Name = "علی", GPA = 17 }, new() { Name = "مریم", GPA = 19 } };
students.Sort();
foreach (var s in students) Console.WriteLine($"{s.Name}: {s.GPA}");
