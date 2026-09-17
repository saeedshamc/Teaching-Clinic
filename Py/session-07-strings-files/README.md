# جلسه ۷: رشته‌ها و کار با فایل‌ها (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- متدهای رشته: `upper`, `lower`, `strip`, `split`, `join`, `replace`
- indexing و slicing روی رشته
- f-string پیشرفته و escape characters
- فایل را با `open()` و `with` بخوانید و بنویسید
- encoding UTF-8 برای فارسی
- مسیر فایل و حالت‌های `r`, `w`, `a`

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور str و immutability |
| 10–30 | متدهای رشته |
| 30–45 | split/join و پردازش خط |
| 45–65 | خواندن/نوشتن فایل — `with` |
| 65–80 | encoding، مسیر، append |
| 80–90 | تکلیف |

---

## ۱. رشته immutable

```python
text = "Python"
print(text[0])    # P
print(text[-1])   # n
print(text[0:3])  # Pyt
# text[0] = "J"   # TypeError
```

---

## ۲. متدهای پرکاربرد

```python
msg = "  سلام Python  "
print(msg.strip())
print(msg.upper())
print(msg.lower())
print(msg.replace("Python", "پایتون"))
print("a,b,c".split(","))
print("-".join(["2026", "09", "02"]))
```

| متد | کار |
|-----|-----|
| `strip()` | حذف فاصله دو طرف |
| `split(sep)` | تقسیم به لیست |
| `join(iter)` | چسباندن |
| `startswith` / `endswith` | بررسی پیشوند/پسوند |
| `find` / `in` | جستجو |

---

## ۳. f-string پیشرفته

```python
name = "سعید"
score = 87.456
print(f"{name:>10}")      # راست‌چین
print(f"{score:.1f}")     # یک رقم اعشار
print(f"{'بله' if score >= 50 else 'خیر'}")
```

---

## ۴. خواندن و نوشتن فایل

### ۴.۱. نوشتن

```python
with open("sample.txt", "w", encoding="utf-8") as f:
    f.write("سلام از Python\n")
    f.write("خط دوم\n")
```

### ۴.۲. خواندن

```python
with open("sample.txt", "r", encoding="utf-8") as f:
    content = f.read()
    print(content)

# خط به خط
with open("sample.txt", "r", encoding="utf-8") as f:
    for line in f:
        print(line.strip())
```

**خط به خط:**
- `with` — فایل خودکار بسته می‌شود
- `encoding="utf-8"` — برای فارسی الزامی
- `"w"` بازنویسی — `"a"` append

---

## ۵. مسیر و os.path (معرفی)

```python
import os

path = os.path.join("data", "notes.txt")
print("وجود دارد؟", os.path.exists(path))
```

---

## ۶. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_strings.py](./examples/01_strings.py) | متدهای رشته |
| [02_string_processing.py](./examples/02_string_processing.py) | split/join |
| [03_file_write.py](./examples/03_file_write.py) | نوشتن فایل |
| [04_file_read.py](./examples/04_file_read.py) | خواندن فایل |

---

## ۷. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| فراموش `encoding=utf-8` | Mojibake فارسی |
| فراموش بستن فایل | از `with` استفاده کنید |
| `open` بدون مسیر درست | FileNotFoundError |
| تغییر str در حلقه | str جدید بسازید |

---

## ۸. تمرین کلاسی

1. upper/lower یک جمله
2. split CSV ساده
3. نوشتن ۳ خط در فایل
4. خواندن و شمارش خطوط

**تکلیف:** [question.md](./exercises/question.md)

### ۴.۳. pathlib (مدرن)

```python
from pathlib import Path

p = Path("data") / "notes.txt"
if p.exists():
    text = p.read_text(encoding="utf-8")
```

---

## ۱۰. تکلیف خانه

1. **word counter** فایل متنی
2. **copy file** — خواندن و نوشتن در مسیر جدید
3. **normalize** — strip + lower همه خطوط

---

## ۱۲. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_strings.py`

- رشته **immutable** — متدها instance جدید برمی‌گردانند
- `"  text  ".strip()` — فاصله دو طرف

### 📄 `03_file_write.py`

```python
with open(filename, "w", encoding="utf-8") as f:
    f.write(line + "\n")
```

- `with` — context manager — فایل auto-close
- `\n` — newline explicit برای cross-platform

### 📄 `04_file_read.py`

- `read()` — کل فایل یک str
- `readlines()` — لیست خطوط با `\n`
- `"a"` mode — append بدون overwrite

---

## ۱۴. escape characters

| کاراکتر | معنی |
|---------|------|
| `\n` | newline |
| `\t` | tab |
| `\\` | backslash |
| `\'` `\"` | quote |

Raw string: `r"C:\path\to\file"` — backslash escape نمی‌شود.

---

## ۱۵. مطالعه بیشتر

- [docs — str methods](https://docs.python.org/3/library/stdtypes.html#string-methods)
- pathlib — جایگزین os.path
- تمرین: log analyzer — count ERROR lines

---

## ۱۷. تکلیف خانه (جزئیات)

** word counter file:** فایل `.txt` بخوانید — تعداد کلمات، خطوط، کاراکترها

** slugify:** `"Hello World!"` → `"hello-world"`

** csv parse:** `"name,age,city"` → dict

---

## ۱۹. پرسش‌های کلاسی (Q&A)

**س: str immutable یعنی چه؟**  
ج: نمی‌توانید `s[0]='x'` — باید str جدید بسازید.

**س: تفاوت read و readlines؟**  
ج: read یک str — readlines لیست خطوط.

**س: چرا encoding=utf-8؟**  
ج: پشتیبانی فارسی و emoji.

**س: wb vs w؟**  
ج: wb binary — w text با encoding.

---

## ۲۰. خلاصه

- ✅ str immutable — متدها str جدید برمی‌گردانند
- ✅ `with open(..., encoding="utf-8")`
- ✅ `read()`, `readlines()`, iterate خط

---

**جلسه قبل:** [۶ — توابع](../session-06-functions/) | **بعد:** [۸ — خطا](../session-08-error-handling/)
