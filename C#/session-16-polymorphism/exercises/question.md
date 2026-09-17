# تمرین‌های جلسه ۱۶ — Polymorphism

## کلاسی (۳۰ دقیقه)

### ۱. Animal Zoo
`Animal[]` با `Dog`, `Cat`, `Bird` — حلقه `Speak()` و شمارش هر نوع با `is`.

### ۲. Shape Calculator
`Shape[]` — جمع مساحت و بزرگترین شکل.

### ۳. Employee Payroll
`Employee[]` — مجموع حقوق با Polymorphism.

---

## خانه (۴۵ دقیقه)

### ۴. Document Processor
`Document` (abstract `Print()`) → `PdfDocument`, `WordDocument`, `ImageDocument`.
متد `ProcessAll(Document[])` با pattern matching.

### ۵. Transport System
`Vehicle` → `Car`, `Bus`, `Bicycle` — `CalculateFare(distance)`.
Downcasting برای دسترسی به `Bus.PassengerCount`.

---

پاسخ: [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
