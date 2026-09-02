# جلسه ۲۵: کار با CSV (۱.۵ ساعت)

## ۰. مرور

جلسه ۲۴ JSON — CSV برای **جدول flat** و Excel؛ JSON برای **nested** و API.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- فرمت CSV و کاربرد آن را توضیح دهید
- با `csv.reader` و `csv.writer` کار کنید
- از `DictReader` و `DictWriter` برای dict-based I/O استفاده کنید
- پارامتر `newline=""` در Windows را بدانید
- delimiter سفارشی (`;` `\t`) تنظیم کنید
- داده CSV را فیلتر، تبدیل و aggregate کنید
- CSV را با Excel و ابزارهای دیگر مبادله کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | JSON — مقایسه CSV vs JSON |
| 10–25 | writer/reader | لیست لیست |
| 25–45 | DictWriter/DictReader | dict-based |
| 45–60 | delimiter و encoding | ; و utf-8-sig |
| 60–75 | aggregate | میانگین، فیلتر |
| 75–85 | تمرین | people.csv |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. CSV چیست؟

**CSV** (Comma-Separated Values) فایل متنی با ستون‌های جدا‌شده — استاندارد de facto برای جدول‌ها، Excel و BI tools.

```csv
name,age,city
سعید,30,تهران
مینا,25,اصفهان
```

**مزیت:** ساده، کوچک، سازگار با Excel.  
**عیب:** نوع داده ندارد، nested data سخت.

---

## ۲. DictWriter — نوشتن

```python
import csv
from pathlib import Path

path = Path("people.csv")
with path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["name", "age", "city"])
    writer.writeheader()
    writer.writerow({"name": "سعید", "age": 30, "city": "تهران"})
    writer.writerows([
        {"name": "مینا", "age": 25, "city": "اصفهان"},
    ])
```

**`newline=""`:** در Windows از خطوط خالی اضافه جلوگیری می‌کند.

---

## ۳. DictReader — خواندن

```python
with path.open("r", encoding="utf-8", newline="") as f:
    reader = csv.DictReader(f)
    for row in reader:
        print(row["name"], row["age"])
```

هر `row` یک `dict` با کلید = نام ستون.

---

## ۴. reader/writer ساده (لیست)

```python
with path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["name", "age"])
    writer.writerow(["سعید", 30])

with path.open("r", encoding="utf-8", newline="") as f:
    reader = csv.reader(f)
    header = next(reader)
    for row in reader:
        print(row)
```

---

## ۵. delimiter سفارشی

```python
# فایل اروپایی با ; به‌جای ,
writer = csv.writer(f, delimiter=";")

# TSV — tab-separated
writer = csv.writer(f, delimiter="\t")
```

---

## ۶. utf-8-sig برای Excel

```python
# BOM برای Excel Windows
with path.open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["name", "age"])
    ...
```

---

## ۷. Aggregate — میانگین سن

```python
ages = []
with path.open(encoding="utf-8", newline="") as f:
    for row in csv.DictReader(f):
        ages.append(int(row["age"]))
average = sum(ages) / len(ages)
```

---

## ۸. فیلتر و نوشتن CSV جدید

```python
with input_path.open(encoding="utf-8", newline="") as fin:
    reader = csv.DictReader(fin)
    rows = [r for r in reader if int(r["age"]) >= 18]

with output_path.open("w", encoding="utf-8", newline="") as fout:
    writer = csv.DictWriter(fout, fieldnames=reader.fieldnames)
    writer.writeheader()
    writer.writerows(rows)
```

---

## ۹. CSV vs JSON

| CSV | JSON |
|-----|------|
| جدول flat | nested |
| Excel-friendly | API-friendly |
| بدون type | string/number/bool |
| فایل کوچک‌تر | انعطاف بیشتر |

---

## ۱۰. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_csv.py](./examples/01_csv.py) | DictReader/Writer پایه |
| [02_reader_writer.py](./examples/02_reader_writer.py) | reader/writer |
| [03_aggregate_filter.py](./examples/03_aggregate_filter.py) | میانگین و فیلتر |
| [04_excel_export.py](./examples/04_excel_export.py) | utf-8-sig |

---

## ۱۱. اشتباهات رایج (Pitfalls)

### ❌ فراموش newline=""
در Windows ردیف‌های خالی اضافه می‌شود.

### ❌ age به‌صورت str
```python
int(row["age"])  # ✅ همیشه cast کنید
```

### ❌ fieldnames ناهماهنگ
DictWriter فقط fieldnames را می‌نویسد — کلیدهای اضافه نادیده.

---

## ۱۲. خلاصه

- ✅ DictReader/Writer برای dict
- ✅ `newline=""` در Windows
- ✅ `encoding="utf-8-sig"` برای Excel
- ✅ cast نوع بعد از خواندن

---

## ۱۳. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۴. quoting — فیلدهای خاص

```python
csv.writer(f, quoting=csv.QUOTE_NONNUMERIC)
# همه غیرعددی در quote

csv.writer(f, quoting=csv.QUOTE_MINIMAL)  # default
```

---

## ۱۵. Sniffer — تشخیص delimiter

```python
with path.open() as f:
    sample = f.read(1024)
    dialect = csv.Sniffer().sniff(sample)
    f.seek(0)
    reader = csv.reader(f, dialect)
```

---

## ۱۶. pandas (اشاره)

```python
# import pandas as pd
# df = pd.read_csv("data.csv")
# df.to_csv("out.csv", index=False)
```

برای داده بزرگ pandas راحت‌تر است — CSV module برای سبکی و بدون dependency.

---

## ۱۷. توضیح فایل‌های مثال

### 📄 `01_csv.py` — DictReader/Writer پایه
### 📄 `02_reader_writer.py` — delimiter ;
### 📄 `03_aggregate_filter.py` — میانگین و فیلتر
### 📄 `04_excel_export.py` — utf-8-sig

---

## ۱۸. سوالات کلاسی

1. چرا newline="" در Windows؟
2. age از CSV چه نوعی است؟
3. CSV vs JSON برای export Excel؟

---

## ۱۹. encoding در Excel فارسی

| encoding | Excel Windows |
|----------|---------------|
| utf-8 | ممکن است مشکل |
| utf-8-sig | ✅ با BOM |
| cp1256 | قدیمی |

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [csv — Python docs](https://docs.python.org/3/library/csv.html)
- [RFC 4180 — CSV format](https://tools.ietf.org/html/rfc4180)

### چک‌لیست پایان جلسه

- [ ] DictReader/Writer
- [ ] newline=""
- [ ] utf-8-sig برای Excel
- [ ] aggregate (میانگین)

---

**جلسه بعدی:** [جلسه ۲۶ — Pathlib](../session-26-pathlib/)
