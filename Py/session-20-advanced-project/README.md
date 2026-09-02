# جلسه ۲۰: پروژه پیشرفته نهایی — Task Manager (۱.۵ ساعت)

## ۰. مرور بخش ۱۳–۱۹

| جلسه | مهارت در پروژه |
|------|----------------|
| ۱۳ | list comprehension برای فیلتر |
| ۱۴ | decorator log |
| ۱۵ | generator برای export |
| ۱۶ | requirements.txt |
| ۱۷ | (اختیاری) async save |
| ۱۸ | unittest |
| ۱۹ | TypedDict Task |

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفاهیم جلسات ۱۳ تا ۱۹ را در یک پروژه واقعی ترکیب کنید
- یک **Task Manager** ساخت‌یافته با type hint بنویسید
- از توابع، dict، list comprehension و exception handling استفاده کنید
- کارها را در JSON ذخیره و بارگذاری کنید
- decorator برای لاگ و تست با unittest بنویسید
- کد را خوانا، قابل گسترش و قابل تست نگه دارید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | جمع‌بندی بخش پیشرفته ۱۳–۱۹ |
| 10–25 | طراحی | entity، service، storage |
| 25–45 | CRUD کارها | add، list، complete، delete |
| 45–60 | JSON persistence | save/load |
| 60–75 | تست و decorator | unittest، log decorator |
| 75–85 | تمرین | گسترش پروژه |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. معرفی پروژه

**Task Manager** — برنامه مدیریت کار روزانه:
- افزودن کار
- نمایش همه کارها
- علامت‌گذاری انجام‌شده
- حذف کار
- ذخیره در فایل JSON

---

## ۲. ساختار داده

```python
from typing import TypedDict

class Task(TypedDict):
    id: int
    title: str
    done: bool
    priority: str  # "low" | "medium" | "high"
```

---

## ۳. لایه Service

```python
class TaskService:
    def __init__(self):
        self.tasks: list[Task] = []
        self._next_id = 1

    def add(self, title: str, priority: str = "medium") -> Task:
        task: Task = {
            "id": self._next_id,
            "title": title,
            "done": False,
            "priority": priority,
        }
        self._next_id += 1
        self.tasks.append(task)
        return task

    def complete(self, task_id: int) -> bool:
        for task in self.tasks:
            if task["id"] == task_id:
                task["done"] = True
                return True
        return False

    def pending_count(self) -> int:
        return sum(1 for t in self.tasks if not t["done"])
```

---

## ۴. JSON Storage

```python
import json
from pathlib import Path

def save_tasks(tasks: list[Task], path: Path) -> None:
    with path.open("w", encoding="utf-8") as f:
        json.dump(tasks, f, ensure_ascii=False, indent=2)

def load_tasks(path: Path) -> list[Task]:
    if not path.exists():
        return []
    with path.open(encoding="utf-8") as f:
        return json.load(f)
```

---

## ۵. Comprehension در پروژه

```python
# کارهای با اولویت بالا
high = [t for t in service.tasks if t["priority"] == "high" and not t["done"]]

# dict: id → title
titles = {t["id"]: t["title"] for t in service.tasks}
```

---

## ۶. Decorator برای لاگ

```python
from functools import wraps

def log_action(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        print(f"[LOG] {func.__name__}")
        return func(*args, **kwargs)
    return wrapper
```

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_task_manager.py](./examples/01_task_manager.py) | نسخه ساده |
| [02_task_service.py](./examples/02_task_service.py) | کلاس service |
| [03_json_storage.py](./examples/03_json_storage.py) | ذخیره JSON |
| [04_full_demo.py](./examples/04_full_demo.py) | demo کامل |

---

## ۸. اشتباهات رایج (Pitfalls)

### ❌ همه چیز در main
منطق را در توابع/کلاس جدا کنید.

### ❌ بدون type hint
پروژه پیشرفته = hint برای نگهداری.

### ❌ بدون تست
حداقل `pending_count` و `complete` را تست کنید.

---

## ۹. خلاصه

این جلسه پایان **بخش دوم** دوره (جلسات ۱۳–۲۰) است. مهارت‌های comprehension، decorator، generator، venv، async، test و typing را ترکیب کردید.

---

## ۱۰. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercise_02.py)

---

## ۱۱. معماری لایه‌ای

```text
UI/CLI  →  TaskService  →  JSON Storage
              ↓
           unittest
```

---

## ۱۲. گسترش‌های پیشنهادی

- اولویت کار (low/medium/high)
- فیلتر با list comprehension
- decorator `@log_action` روی add/complete
- export به CSV (جلسه ۲۵)

---

## ۱۳. توضیح فایل‌های مثال

### 📄 `01_task_manager.py` — نسخه ساده list+dict
### 📄 `02_task_service.py` — TaskService با TypedDict
### 📄 `03_json_storage.py` — save/load با Path
### 📄 `04_full_demo.py` — decorator + JSON

---

## ۱۴. چک‌لیست تحویل پروژه

- [ ] type hint روی توابع عمومی
- [ ] حداقل ۳ unittest
- [ ] JSON persistence
- [ ] README پروژه
- [ ] requirements.txt

---

## ۱۵. سوالات کلاسی

1. چرا Task را dict نگه می‌داریم نه list ساده؟
2. JSON vs pickle برای ذخیره task؟
3. چگونه `pending_count` را تست می‌کنید؟

---

## ۱۶. نمونه خروجی CLI

```text
=== Task Manager ===
1. [ ] یادگیری Python
2. [ ] تمرین decorator
کارهای باز: 2
```

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- ترکیب مهارت‌های جلسات ۱۳–۱۹ در یک repo
- [Clean Code — نام‌گذاری و ساختار](https://github.com/ryanmcdermott/clean-code-javascript) (مفاهیم عمومی)

### چک‌لیست تحویل پروژه

- [ ] TaskService با type hint
- [ ] JSON save/load
- [ ] unittest ≥ 3 test
- [ ] README پروژه
- [ ] requirements.txt
- [ ] decorator log (اختیاری)
- [ ] demo end-to-end Task Manager

---

## نکات تکمیلی برای مدرس

این جلسه **جمع‌بندی بخش ۱۳–۱۹** است — ۳۰ دقیقه live coding TaskService، ۲۰ دقیقه JSON، ۱۵ دقیقه unittest. اگر زمان کم بود exercise_02 خانه بماند.

### اجرای تمرین‌ها

```bash
python session-20-advanced-project/examples/04_full_demo.py
python session-20-advanced-project/exercises/exercise_02.py
```

این جلسه پایان بخش پیشرفته (۱۳–۲۰) دوره Python است.

---

**جلسه بعدی:** [جلسه ۲۱ — Context Managerها](../session-21-context-managers/)
