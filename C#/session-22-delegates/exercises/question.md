# تمرین‌های جلسه ۲۲ — Delegates

## کلاسی (۳۰ دقیقه)

### ۱. Calculator
`Func<int,int,int>` برای +, -, *, / — انتخاب عملگر در runtime.

### ۲. List Filter
`Filter<T>(list, Predicate<T>)` — فیلتر اعداد زوج و کلمات بلند.

### ۳. Multicast Logger
`Action<string>` — Console + File + Timestamp.

---

## خانه (۴۵ دقیقه)

### ۴. Password Validator
سه rule با `Func<string, bool>` — طول، عدد، حرف بزرگ.

### ۵. Sort Strategy
`Sort<T>(list, Func<T,T,int>)` — صعودی و نزولی.

---

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)
