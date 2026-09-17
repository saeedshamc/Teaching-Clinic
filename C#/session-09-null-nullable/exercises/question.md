# تمرین‌های جلسه ۹ — Null و Nullable

## کلاسی (۳۰ دقیقه)

### ۱. int? parse
سن اختیاری از کاربر — `int?` ذخیره. null = «وارد نشده».

### ۲. ?? default
`string? name = null` → با `??` «مهمان» نمایش دهید.

### ۳. ?. chain
کلاس `Person` با `Address?` — شهر را safe بخوانید.

### ۴. ??= init
`string? cache = null` — با `??=` مقدار `"default"` set کنید.

---

## خانه (۴۵ دقیقه)

### ۵. Safe profile
`username`, `email`, `phone` nullable — profile formatted با `?.` و `??`.

### ۶. Config loader
`Dictionary` با مقادیر nullable — هر key مقدار پیش‌فرض با `??`.

### ۷. Guard method
متد `Validate(string? input)` — null/empty → false.

### ۸. Nested null
`Order?.Customer?.Email ?? "no-email@local"` — تست با null های مختلف.

---

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
