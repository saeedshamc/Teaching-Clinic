# تمرین‌های جلسه ۱۴ — Properties و Constructors

## کلاسی (۳۰ دقیقه)

### ۱. Temperature
کلاس `Temperature` با property `Celsius` (validation: ≥ -273.15) و `Fahrenheit` (محاسبه دوطرفه).

### ۲. Book
Constructor اجباری: `Book(string title, string author, int pages)`.
Property `IsLong` — بیش از ۳۰۰ صفحه.

### ۳. BankAccount
- `Balance` با private set
- Constructor: `BankAccount(string owner, double initialBalance)`
- `Deposit`, `Withdraw` — فقط از طریق متدها

---

## خانه (۴۵ دقیقه)

### ۴. Employee
سه constructor: پیش‌فرض، `(name)`, `(name, dept, salary)`.
Property `AnnualSalary` => `Salary * 12`.
Property `Level` => بر اساس حقوق (Junior < 10M, Mid < 20M, Senior).

### ۵. TimeSpan24
ساعت (0-23) و دقیقه (0-59) با validation.
Property `TotalMinutes`, `Display` => "HH:mm".
Constructor از `TotalMinutes`.

---

پاسخ: [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
