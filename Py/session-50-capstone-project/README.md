# جلسه ۵۰: پروژه نهایی جامع (Capstone)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- مفاهیم پیشرفته را در یک پروژه ترکیب کنید
- یک سرویس مدیریت کار ساخت‌یافته بنویسید
- کد را با تابع، dataclass، ذخیره فایل و تست سبک پیش ببرید

---

## پروژه نهایی: Task Service

در این جلسه یک سرویس مدیریت کار می‌سازیم که کار را اضافه می‌کند، لیست می‌کند، تکمیل می‌کند و در JSON ذخیره می‌کند.

```python
from dataclasses import dataclass, asdict
import json
from pathlib import Path

@dataclass
class Task:
    title: str
    done: bool = False

tasks = [Task("یادگیری Python")]
path = Path("tasks.json")
path.write_text(json.dumps([asdict(t) for t in tasks], ensure_ascii=False), encoding="utf-8")
print(path.read_text(encoding="utf-8"))
```

### توضیح
- `dataclass` برای مدل داده
- `json` برای ذخیره‌سازی
- توابع جدا برای هر عملیات

---

## فایل‌های این جلسه
- [examples/01_task_service.py](./examples/01_task_service.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: تعداد کارهای مانده را حساب کنید
- تمرین متوسط: فقط عنوان کارهای انجام‌شده را برگردانید

---

[بازگشت به فهرست دوره](../)
