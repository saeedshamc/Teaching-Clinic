# جلسه ۴۶: Protocol در تایپ‌ها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با Protocol قرارداد ساختاری بسازید
- duck typing را با type hint ترکیب کنید
- کد قابل‌توسعه‌تر بنویسید

---

## Protocol چیست؟

Protocol می‌گوید یک شیء چه متدهایی باید داشته باشد، بدون اجبار به وراثت از یک کلاس پایه.

```python
from typing import Protocol

class Writable(Protocol):
    def write(self, data: str) -> None: ...

class MemoryFile:
    def __init__(self):
        self.data = ""
    def write(self, data: str) -> None:
        self.data += data

def save(dest: Writable, text: str) -> None:
    dest.write(text)

f = MemoryFile()
save(f, "سلام")
print(f.data)
```

### توضیح
- `Protocol` → قرارداد ساختاری
- نیازی به ارث‌بری صریح نیست
- اگر متدها موجود باشند، سازگار است

---

## فایل‌های این جلسه
- [examples/01_protocol.py](./examples/01_protocol.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک Protocol با یک متد بسازید
- تمرین متوسط: تابع hear را با Protocol بنویسید
