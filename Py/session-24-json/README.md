# جلسه ۲۴: کار با JSON

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- ساختار JSON را بشناسید
- داده را به JSON تبدیل و برعکس کنید
- فایل JSON را بخوانید و بنویسید

---

## JSON چیست؟

JSON یک قالب متنی استاندارد برای تبادل داده است. در Python معمولاً با دیکشنری و لیست متناظر می‌شود.

```python
import json

person = {"name": "سعید", "age": 30}
text = json.dumps(person, ensure_ascii=False)
print(text)

data = json.loads(text)
print(data["name"])
```

### توضیح
- `dumps` → شیء Python را به رشته JSON تبدیل می‌کند
- `loads` → رشته JSON را به شیء Python برمی‌گرداند
- `ensure_ascii=False` → فارسی را خوانا نگه می‌دارد

---

## فایل‌های این جلسه
- [examples/01_json.py](./examples/01_json.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک دیکشنری را dumps کنید
- تمرین متوسط: لیست کاربران را به JSON تبدیل کنید
