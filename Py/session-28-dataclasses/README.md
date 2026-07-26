# جلسه ۲۸: Dataclassها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با dataclass کلاس داده بسازید
- کد تکراری __init__ را کاهش دهید
- مقایسه و نمایش اشیاء را ساده‌تر کنید

---

## dataclass چیست؟

dataclass راهی کوتاه برای ساخت کلاس‌هایی است که عمدتاً داده نگه می‌دارند.

```python
from dataclasses import dataclass

@dataclass
class User:
    name: str
    age: int

user = User("سعید", 30)
print(user)
print(user.name)
```

### توضیح
- `@dataclass` → متدهای رایج مثل __init__ را خودکار می‌سازد
- فیلدها با type hint تعریف می‌شوند

---

## فایل‌های این جلسه
- [examples/01_dataclass.py](./examples/01_dataclass.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک dataclass برای User بسازید
- تمرین متوسط: dataclass محصول با قیمت بسازید
