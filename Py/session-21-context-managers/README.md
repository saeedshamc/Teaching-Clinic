# جلسه ۲۱: Context Managerها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- مفهوم context manager را درک کنید
- از دستور with برای مدیریت منابع استفاده کنید
- یک context manager ساده با contextlib بسازید

---

## Context Manager چیست؟

Context Manager به شما کمک می‌کند منابعی مثل فایل را به‌صورت امن باز و بسته کنید. با with دیگر نگران فراموش کردن close نیستید.

```python
with open("sample.txt", "w", encoding="utf-8") as file:
    file.write("سلام")
# اینجا فایل به‌صورت خودکار بسته می‌شود
```

### توضیح
- `with` → ورود و خروج امن از یک بلوک را مدیریت می‌کند
- پس از پایان بلوک، منابع آزاد می‌شوند

---

## ساخت context manager ساده

```python
from contextlib import contextmanager

@contextmanager
def greet():
    print("شروع")
    yield
    print("پایان")

with greet():
    print("بدنه اصلی")
```

---

## فایل‌های این جلسه
- [examples/01_context_manager.py](./examples/01_context_manager.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: فایل را با with بخوانید و چاپ کنید
- تمرین متوسط: یک context manager با contextlib بسازید
