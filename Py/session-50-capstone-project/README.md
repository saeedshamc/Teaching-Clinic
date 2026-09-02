# جلسه ۵۰: پروژه نهایی جامع — Task Service (Capstone) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفاهیم جلسات ۳۹–۴۹ را در یک پروژه یکپارچه به کار ببرید
- Task Service با لایه‌بندی (model، repository، service، CLI) بسازید
- persistence با JSON و pathlib پیاده کنید
- property، dataclass و type hints ترکیب کنید
- تست و mock برای service بنویسید
- README و docstring پروژه نهایی را تکمیل کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور capstone | اهداف و معماری |
| 10–25 | models | Task dataclass |
| 25–40 | repository | JSON file |
| 40–55 | service | CRUD + validation |
| 55–70 | CLI | argparse/menu |
| 70–85 | test + polish | pytest، docs |
| 85–90 | ارائه + جمع‌بندی | demo |

---

## ۱. معرفی پروژه

**Task Service** — سرویس مدیریت کار با:
- افزودن کار
- لیست کارها (مانده / انجام‌شده)
- تکمیل کار
- ذخیره/بارگذاری JSON
- CLI ساده

این پروژه **خلاصه دوره** است — OOP، فایل، JSON، typing، testing، structure.

---

## ۲. معماری

```
┌─────────────┐
│   CLI       │  ← interface
└──────┬──────┘
       │
┌──────▼──────┐
│  Service    │  ← business logic
└──────┬──────┘
       │
┌──────▼──────┐
│ Repository  │  ← JSON persistence
└──────┬──────┘
       │
┌──────▼──────┐
│   Task      │  ← dataclass model
└─────────────┘
```

---

## ۳. Model — Task

```python
from dataclasses import dataclass, field
from datetime import datetime

@dataclass
class Task:
    title: str
    done: bool = False
    created_at: str = field(default_factory=lambda: datetime.now().isoformat())
```

---

## ۴. Repository — JSON

```python
class TaskRepository:
    def __init__(self, path: Path):
        self.path = path
        self.tasks: list[Task] = []

    def load(self): ...
    def save(self): ...
```

`asdict` / `json.dumps` — `ensure_ascii=False` برای فارسی.

---

## ۵. Service — منطق

```python
class TaskService:
    def add(self, title: str): ...
    def complete(self, index: int): ...
    def pending_count(self) -> int: ...
    def list_done_titles(self) -> list[str]: ...
```

validation: عنوان خالی، index نامعتبر.

---

## ۶. CLI

```python
def main():
    parser = argparse.ArgumentParser()
    sub = parser.add_subparsers()
    # add, list, done, ...
```

یا menu تعاملی ساده برای کلاس.

---

## ۷. مفاهیم ترکیب‌شده

| جلسه | کاربرد در capstone |
|------|-------------------|
| ۳۹ Property | optional validated title |
| ۴۰ Packaging | pyproject.toml |
| ۴۱ pytest | test service |
| ۴۲ mock | mock repository |
| ۴۳ debug | breakpoint در develop |
| ۴۴ perf | — (optional) |
| ۴۵ patterns | repository |
| ۴۶ Protocol | Writable repo |
| ۴۷ security | safe path |
| ۴۸ docs | docstring |
| ۴۹ structure | layers |

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_task_model.py](./examples/01_task_model.py) | dataclass Task |
| [02_json_repository.py](./examples/02_json_repository.py) | persistence |
| [03_task_service.py](./examples/03_task_service.py) | service کامل |
| [04_cli_menu.py](./examples/04_cli_menu.py) | menu تعاملی |

---

## ۹. extension ideas

- اولویت (priority) برای Task
- due date و sort
- SQLite به‌جای JSON
- REST API با FastAPI
- export CSV

---

## ۱۰. test plan

```python
def test_add_task():
    svc = TaskService(InMemoryRepo())
    svc.add("test")
    assert svc.pending_count() == 1

def test_complete():
    ...
```

---

## ۱۱. demo script

```bash
python examples/04_cli_menu.py
# 1. add → یادگیری Python
# 2. list
# 3. complete 0
# 4. save & exit
```

---

## ۱۲. اجرای مثال‌ها

```bash
python session-50-capstone-project/examples/01_task_model.py
python session-50-capstone-project/examples/02_json_repository.py
python session-50-capstone-project/examples/03_task_service.py
python session-50-capstone-project/examples/04_cli_menu.py
```

---

## ۱۳. checklist تحویل

- [ ] Task dataclass با docstring
- [ ] Repository load/save
- [ ] Service با validation
- [ ] CLI کار کند
- [ ] حداقل ۳ تست
- [ ] README پروژه
- [ ] JSON فارسی درست ذخیره شود

---

## ۱۴. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| همه در یک فایل | layers |
| index بعد از delete | id uuid |
| فراموش save | auto-save یا remind |
| path نسبی اشتباه | Path(__file__) |

---

## ۱۵. تبریک!

با تکمیل این جلسه **۵۰ جلسه Python** را تمام کردید — از hello world تا پروژه ساخت‌یافته.

---

## ۱۶. مسیر بعدی

- FastAPI / Django
- Data: pandas، SQL
- DevOps: Docker، CI
- Contributing open source

---

## ۱۷. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۸. خلاصه

```
Task (model) → Repository (JSON) → Service (logic) → CLI
dataclass + pathlib + json + validation + layers
= پروژه capstone آماده 포folio
```

---

**جلسه قبل:** [۴۹ — Project Structure](../session-49-project-structure/) | **بازگشت:** [فهرست دوره](../)

---

## ۱۹. rubric ارزیابی

| معیار | امتیاز |
|-------|--------|
| لایه‌بندی صحیح | ۲۵٪ |
| JSON persistence | ۲۰٪ |
| validation | ۱۵٪ |
| تست (≥۳) | ۲۰٪ |
| README + docstring | ۲۰٪ |

---

## ۲۰. نمونه README capstone

```markdown
# Task Service
## نصب: pip install -e .
## استفاده: python -m taskapp
## تست: pytest
```

---

## ۲۱. git workflow

```bash
git checkout -b feature/capstone
# develop
git add .
git commit -m "feat: task service capstone"
```

---

## ۲۲. تبریک پایانی

شما **۵۰ جلسه Python** را با موفقیت طی کردید.  
از property تا capstone — آماده پروژه‌های واقعی و یادگیری فریم‌ورک‌های وب هستید.

---

## ۲۳. منابع ادامه مسیر

- [FastAPI](https://fastapi.tiangolo.com/)
- [Real Python](https://realpython.com/)
- [Python Discord](https://pythondiscord.com/)
