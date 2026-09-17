# تمرین‌های جلسه ۳۰ — Collections

## کلاسی (۳۰ دقیقه)

1. **Counter:** لیست رنگ‌ها — پرتکرارترین را چاپ کنید.
2. **defaultdict(list):** گروه‌بندی کلمات بر اساس طول.
3. **namedtuple:** `Employee(name, dept, salary)` — `_asdict()`.
4. **deque:** maxlen=3 — ۵ عدد append کنید، خروجی را ببینید.

## خانه (۴۵ دقیقه)

5. **Word frequency:** فایل متنی — Counter و top 5.
6. **Inverted index:** defaultdict(set) — کلمه → شماره سطر.
7. **Counter arithmetic:** دو Counter را جمع/تفریق کنید.
8. **ChainMap:** defaults + user config merge.

## نکات

- `defaultdict(list)` نه `defaultdict([])`
- Counter[key] برای missing → 0
- namedtuple immutable — `_replace` برای کپی
- deque برای queue/stack سریع

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
