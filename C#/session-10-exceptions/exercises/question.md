# تمرین‌های جلسه ۱۰ — Exceptions

## کلاسی (۳۰ دقیقه)

### ۱. Parse safe
`int.Parse` در try/catch — پیام خطای فارسی.

### ۲. Divide
دو عدد — handle `DivideByZeroException`.

### ۳. finally
try/catch/finally — finally همیشه «پایان» چاپ کند.

### ۴. ValidateAge
`throw ArgumentOutOfRangeException` برای سن < 0 یا > 150.

---

## خانه (۴۵ دقیقه)

### ۵. Custom ProductNotFoundException
کلاس custom + throw وقتی productId نامعتبر.

### ۶. Calculator با exception
ماشین‌حساب — FormatException و DivideByZeroException.

### ۷. Retry pattern
۳ بار تلاش برای parse — بعد give up.

### ۸. ATM کامل
Withdraw با InsufficientBalanceException + finally log.

---

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
