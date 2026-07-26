# جلسه ۳۸: کلاس‌های انتزاعی (ABC)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با ABC رابط اجباری بسازید
- متدهای abstract تعریف کنید
- پیاده‌سازی‌های مختلف را یکدست کنید

---

## ABC چیست؟

Abstract Base Class قالبی اجباری برای کلاس‌های فرزند می‌سازد تا متدهای ضروری را پیاده‌سازی کنند.

```python
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self) -> float:
        pass

class Square(Shape):
    def __init__(self, side: float):
        self.side = side
    def area(self) -> float:
        return self.side * self.side

print(Square(4).area())
```

### توضیح
- `ABC` → پایه کلاس انتزاعی
- `abstractmethod` → متد اجباری برای فرزندان
- نمی‌توان از کلاس abstract مستقیم نمونه ساخت

---

## فایل‌های این جلسه
- [examples/01_abc.py](./examples/01_abc.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک Shape پیاده‌سازی کنید
- تمرین متوسط: Animal و Dog بسازید
