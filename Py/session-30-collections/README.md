# جلسه ۳۰: ماژول Collections

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با Counter و defaultdict آشنا شوید
- شمارش و گروه‌بندی داده را ساده‌تر کنید
- از namedtuple برای داده سبک استفاده کنید

---

## collections چه کمکی می‌کند؟

این ماژول ساختارهای داده قدرتمندتری روی دیکشنری و تاپل ارائه می‌دهد.

```python
from collections import Counter, defaultdict

counts = Counter(["سیب", "سیب", "موزم"])
print(counts)

scores = defaultdict(int)
scores["سعید"] += 10
print(dict(scores))
```

### توضیح
- `Counter` → تعداد تکرارها را می‌شمارد
- `defaultdict` → برای کلید جدید مقدار پیش‌فرض می‌سازد

---

## فایل‌های این جلسه
- [examples/01_collections.py](./examples/01_collections.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: با Counter آیتم‌ها را بشمارید
- تمرین متوسط: پرتکرارترین کلمه را پیدا کنید
