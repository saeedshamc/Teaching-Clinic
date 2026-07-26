# جلسه ۴۷: مبانی امنیت در Python

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- رمز و راز را در کد نگذارید
- ورودی کاربر را اعتبارسنجی کنید
- از تزریق ساده در کوئری جلوگیری کنید

---

## امنیت پایه یعنی چه؟

حتی در اسکریپت‌های کوچک، نباید رازها را hard-code کنید و نباید ورودی را مستقیم در کوئری بچسبانید.

```python
password = input_value = "admin' OR '1'='1"
# بد: query = f"SELECT * FROM users WHERE name='{password}'"
# خوب:
query = "SELECT * FROM users WHERE name=?"
params = (input_value,)
print(query, params)
```

### توضیح
- پارامتر امن (`?`) به‌جای f-string در SQL
- رازها را در محیط نگه دارید
- ورودی را همیشه اعتبارسنجی کنید

---

## فایل‌های این جلسه
- [examples/01_security_basics.py](./examples/01_security_basics.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: isalnum را برای نام چک کنید
- تمرین متوسط: اعتبارسنجی ساده ایمیل بنویسید
