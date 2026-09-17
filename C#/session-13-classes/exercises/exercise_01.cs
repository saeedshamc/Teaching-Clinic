// تمرین ۱–۳: Student و BankAccount

class Student
{
    public string Name = "";
    public string StudentId = "";
    public double Grade;

    public bool IsPassed() => Grade >= 10;

    public string GetLetterGrade() => Grade switch
    {
        >= 17 => "A",
        >= 14 => "B",
        >= 10 => "C",
        _ => "F"
    };
}

class BankAccount
{
    public string AccountNumber = "";
    public string Owner = "";
    public double Balance;

    public void Deposit(double amount)
    {
        if (amount > 0) Balance += amount;
    }

    public bool Withdraw(double amount)
    {
        if (amount > 0 && amount <= Balance)
        {
            Balance -= amount;
            return true;
        }
        return false;
    }

    public void Transfer(BankAccount other, double amount)
    {
        if (Withdraw(amount))
            other.Deposit(amount);
    }
}

// --- Student ---
Student[] students =
{
    new() { Name = "علی", StudentId = "S001", Grade = 18.5 },
    new() { Name = "مریم", StudentId = "S002", Grade = 12 },
    new() { Name = "رضا", StudentId = "S003", Grade = 9 },
    new() { Name = "سارا", StudentId = "S004", Grade = 15.5 },
    new() { Name = "امیر", StudentId = "S005", Grade = 11 }
};

foreach (var s in students)
    Console.WriteLine($"{s.Name}: {s.Grade} → {s.GetLetterGrade()} | قبول: {s.IsPassed()}");

double avg = students.Average(s => s.Grade);
Console.WriteLine($"\nمیانگین: {avg:F1}");

// --- BankAccount ---
var a1 = new BankAccount { AccountNumber = "1001", Owner = "علی", Balance = 500_000 };
var a2 = new BankAccount { AccountNumber = "1002", Owner = "رضا", Balance = 200_000 };
a1.Transfer(a2, 150_000);
Console.WriteLine($"\n{a1.Owner}: {a1.Balance:N0} | {a2.Owner}: {a2.Balance:N0}");
