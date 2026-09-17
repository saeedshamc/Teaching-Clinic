# جلسه ۲۴: کار با JSON (۱.۵ ساعت)

## ۰. مرور

dict و list جلسه ۵ — JSON همان ساختار را به **متن** تبدیل می‌کند برای API و فایل config.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- ساختار JSON و mapping به Python را توضیح دهید
- با `json.dumps` و `json.loads` کار کنید
- فایل JSON را بخوانید و بنویسید
- پارامترهای `ensure_ascii`، `indent` و `default` را بشناسید
- JSON Lines (JSONL) را تشخیص دهید
- خطاهای `JSONDecodeError` را مدیریت کنید
- داده تو در تو (nested) را پیمایش کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | dict/list — جلسه ۵ |
| 10–25 | dumps و loads | رشته ↔ Python |
| 25–40 | فایل JSON | read/write با Path |
| 40–55 | پارامترها | ensure_ascii، indent |
| 55–70 | nested و JSONL | داده تو در تو |
| 70–85 | تمرین | users.json |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. JSON چیست؟

**JSON** (JavaScript Object Notation) قالب متنی استاندارد برای تبادل داده — در API، config و ذخیره‌سازی.

### mapping Python ↔ JSON

| Python | JSON |
|--------|------|
| dict | object |
| list, tuple | array |
| str | string |
| int, float | number |
| True/False | true/false |
| None | null |

---

## ۲. dumps و loads

```python
import json

person = {"name": "سعید", "age": 30, "skills": ["Python", "Git"]}

# Python → رشته JSON
text = json.dumps(person, ensure_ascii=False, indent=2)
print(text)

# رشته JSON → Python
data = json.loads(text)
print(data["name"])
```

---

## ۳. ensure_ascii=False

```python
# ❌ فارسی escape می‌شود
json.dumps({"name": "سعید"})  # {"name": "\u0633\u0639\u06cc\u062f"}

# ✅ فارسی خوانا
json.dumps({"name": "سعید"}, ensure_ascii=False)  # {"name": "سعید"}
```

---

## ۴. خواندن و نوشتن فایل

```python
from pathlib import Path
import json

path = Path("data.json")

# نوشتن
with path.open("w", encoding="utf-8") as f:
    json.dump(person, f, ensure_ascii=False, indent=2)

# خواندن
with path.open(encoding="utf-8") as f:
    loaded = json.load(f)
```

**تفاوت:** `dump`/`load` = فایل؛ `dumps`/`loads` = رشته.

---

## ۵. JSONDecodeError

```python
try:
    data = json.loads("{invalid json")
except json.JSONDecodeError as e:
    print(f"خطای JSON: {e.msg} در خط {e.lineno}")
```

---

## ۶. default — سریال‌سازی انواع خاص

```python
from datetime import datetime

def json_default(obj):
    if isinstance(obj, datetime):
        return obj.isoformat()
    raise TypeError(f"نوع {type(obj)} JSON-serializable نیست")

data = {"time": datetime.now()}
text = json.dumps(data, default=json_default, ensure_ascii=False)
```

---

## ۷. داده تو در تو (Nested)

```python
config = {
    "app": "TaskManager",
    "database": {
        "host": "localhost",
        "port": 5432,
        "credentials": {"user": "admin", "password": "***"}
    },
    "features": ["auth", "logging"]
}

# دسترسی
host = config["database"]["host"]
features = config.get("features", [])
```

---

## ۸. JSON Lines (JSONL)

هر خط یک JSON object مستقل:

```python
lines = [
    json.dumps({"id": 1, "name": "علی"}, ensure_ascii=False),
    json.dumps({"id": 2, "name": "مریم"}, ensure_ascii=False),
]
text = "\n".join(lines)

records = [json.loads(line) for line in text.splitlines()]
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_json.py](./examples/01_json.py) | dumps/loads پایه |
| [02_file_json.py](./examples/02_file_json.py) | فایل JSON |
| [03_nested_config.py](./examples/03_nested_config.py) | config تو در تو |
| [04_jsonl_error.py](./examples/04_jsonl_error.py) | JSONL و خطا |

---

## ۱۰. اشتباهات رایج (Pitfalls)

### ❌ tuple در JSON
```python
json.dumps({"items": (1, 2, 3)})  # ✅ tuple → list
```

### ❌ set و datetime بدون default
```python
json.dumps({"s": {1, 2}})  # ❌ TypeError
```

### ❌ فراموش encoding=utf-8
```python
path.write_text(text, encoding="utf-8")  # ✅
```

---

## ۱۱. خلاصه

- ✅ `dumps`/`loads` = string؛ `dump`/`load` = file
- ✅ `ensure_ascii=False` برای فارسی
- ✅ `indent=2` برای خوانایی
- ✅ JSONDecodeError را catch کنید

---

## ۱۲. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۲. json.tool — pretty print در CLI

```bash
python -m json.tool data.json
python -m json.tool data.json > formatted.json
```

---

## ۱۳. انواع غیرقابل serialize

| نوع | راه‌حل |
|-----|--------|
| datetime | `default=isoformat` |
| set | `list(obj)` |
| bytes | decode یا base64 |
| custom class | dict یا dataclass |

---

## ۱۴. API pattern

```python
import urllib.request
import json

with urllib.request.urlopen("https://api.example.com/data") as resp:
    data = json.loads(resp.read().decode())
```

---

## ۱۵. توضیح فایل‌های مثال

### 📄 `01_json.py` — dumps/loads و فایل
### 📄 `02_file_json.py` — users.json
### 📄 `03_nested_config.py` — config تو در تو
### 📄 `04_jsonl_error.py` — JSONL و JSONDecodeError

---

## ۱۶. سوالات کلاسی

1. dumps vs dump؟
2. ensure_ascii=False چرا؟
3. JSON vs CSV برای config؟

---

## ۱۷. schema validation (اشاره)

برای JSON پیچیده: کتابخانه `jsonschema` — خارج از scope این جلسه.

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [json — Python docs](https://docs.python.org/3/library/json.html)
- [JSON.org — syntax](https://www.json.org/json-en.html)

### چک‌لیست پایان جلسه

- [ ] dumps/loads
- [ ] dump/load فایل
- [ ] ensure_ascii=False
- [ ] JSONDecodeError

---

**جلسه بعدی:** [جلسه ۲۵ — CSV](../session-25-csv/)
