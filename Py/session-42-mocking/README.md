# جلسه ۴۲: Mock و شبیه‌سازی وابستگی‌ها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- مفهوم mock را درک کنید
- وابستگی خارجی را شبیه‌سازی کنید
- تست را از شبکه/فایل واقعی جدا کنید

---

## Mock چیست؟

Mock یک جایگزین جعلی برای شیء واقعی است تا تست بدون وابستگی خارجی (مثل API) اجرا شود.

```python
from unittest.mock import Mock

api = Mock()
api.get_user.return_value = {"name": "سعید"}
print(api.get_user(1))
api.get_user.assert_called_once_with(1)
```

### توضیح
- `return_value` → خروجی جعلی
- `assert_called_once_with` → صحت فراخوانی را چک می‌کند
- تست سریع‌تر و پایدارتر می‌شود

---

## فایل‌های این جلسه
- [examples/01_mocking.py](./examples/01_mocking.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: return_value برای یک متد بگذارید
- تمرین متوسط: mailer.send را mock کنید
