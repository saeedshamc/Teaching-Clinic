# تمرین‌های جلسه ۱۲ — پروژه ماشین‌حساب CLI

## کلاسی (۴۵ دقیقه)

### فاز ۱ — MVP (۲۰ دقیقه)
1. منوی ۵ گزینه‌ای (۴ عمل + خروج)
2. `while` loop تا انتخاب ۰
3. switch برای routing

### فاز ۲ — محاسبه (۱۵ دقیقه)
4. متدهای `Add`, `Subtract`, `Multiply`, `TryDivide`
5. `Calculate(a, b, op)` با switch expression
6. `ReadNumber` با retry

### فاز ۳ — Polish (۱۰ دقیقه)
7. try/catch برای تقسیم بر صفر
8. `List<string>` history + case نمایش

---

## خانه (۶۰ دقیقه)

### ۹. ANS
Enter خالی برای «عدد اول» = lastResult

### ۱۰. عملگرهای `%` و `^`
باقیمانده و توان

### ۱۱. پاک history
گزینه ۸ — Clear list

### ۱۲. محدودیت history
حداکثر ۲۰ entry — قدیمی‌ترین حذف

### ۱۳. (Stretch) ذخیره فایل
history در `history.txt` — preview جلسه ۲۸

---

## معیار قبولی

- [ ] منو کار می‌کند
- [ ] ۴ عمل صحیح
- [ ] خطای تقسیم بر صفر handle
- [ ] ورودی نامعتبر retry
- [ ] history نمایش
- [ ] کد با متد structured

---

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
