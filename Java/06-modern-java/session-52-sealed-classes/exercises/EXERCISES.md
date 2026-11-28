# تمرین‌های جلسه ۵۲ — Sealed Classes

## آسان
1. `sealed class Shape permits Circle, Square` بسازید.
2. `sealed interface Result permits Success, Failure` با record بسازید.

## متوسط
3. `non-sealed class Motorcycle extends Vehicle` را تست کنید.
4. `switch` expression با `case Circle c ->` را برای Shape بنویسید.

## سخت
5. `sealed` را با `permits` برای hierarchy چندسطحی بسازید.
6. exhaustive switch را بدون default بنویسید — compiler باید همه cases را بداند.
