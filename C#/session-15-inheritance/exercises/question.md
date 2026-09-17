# تمرین‌های جلسه ۱۵ — وراثت

## کلاسی (۳۰ دقیقه)

### ۱. Vehicle Hierarchy
`Vehicle` (Brand, Year, `StartEngine()`) → `Car` (Doors), `Motorcycle` (HasSidecar).

### ۲. Shape Hierarchy
`Shape` با `virtual GetArea()` → `Circle`, `Rectangle`, `Triangle`.

### ۳. Media Hierarchy
`MediaItem` (Title, Duration) → `Book` (Pages), `Movie` (Director).

---

## خانه (۴۵ دقیقه)

### ۴. Account Hierarchy
`BankAccount` (Balance, Deposit, Withdraw) → `SavingsAccount` (InterestRate, AddInterest), `CheckingAccount` (OverdraftLimit).

### ۵. Game Characters
`Character` (Name, HP, `Attack()`) → `Warrior` (Armor), `Mage` (Mana, `CastSpell()`).
آرایه `Character[]` — شبیه‌سازی نبرد.

---

پاسخ: [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
