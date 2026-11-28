# پروژهٔ جلسه ۳۱ — اعتبارسنجی فرم

یک فرم اعتبارسنجی بسازید که:
- `validateAge(int)` — باید ۰ تا ۱۵۰ باشد
- `validateEmail(String)` — باید `@` و `.` داشته باشد
- `validatePassword(String)` — حداقل ۸ کاراکتر، یک حرف بزرگ، یک عدد
- custom exceptions برای هر خطا
- در `main` چند ورودی تست کنید

نمونه:
```
Age -5 → InvalidAgeException: Age must be 0-150
Email "abc" → InvalidEmailException: Invalid email format
Password "123" → InvalidPasswordException: Too short
```

## صورت پروژه
پاسخ در `solution/ValidationForm.java` — از custom exception و try-catch استفاده کنید.
