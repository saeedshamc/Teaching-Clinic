# جلسه ۲۶: Pathlib و مسیر فایل‌ها — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- جایگزینی `os.path` با `pathlib.Path`
- ساخت، ترکیب و تحلیل مسیر به‌صورت cross-platform
- خواندن/نوشتن فایل با متدهای Path
- پیمایش پوشه‌ها: `iterdir`, `glob`, `rglob`
- ایجاد، حذف، rename و metadata فایل
- الگوهای real-world: پروژه، backup، جستجوی فایل

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور os.path vs pathlib |
| 10–25 | ساخت Path و عملگر `/` |
| 25–40 | خواندن/نوشتن و exists |
| 40–55 | glob و پیمایش درختی |
| 55–70 | mkdir، rename، metadata |
| 70–85 | تمرین کلاسی |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. چرا pathlib؟

قبل از Python 3.4 معمولاً از `os.path.join` استفاده می‌شد. `pathlib` شیءگرا، خوانا و cross-platform است.

```python
# قدیمی
import os
path = os.path.join("data", "2024", "report.txt")

# مدرن
from pathlib import Path
path = Path("data") / "2024" / "report.txt"
```

| مزیت | توضیح |
|------|--------|
| شیءگرا | متدها روی Path زنجیره می‌شوند |
| `/` | جداکننده امن در ویندوز و لینوکس |
| یکپارچه | read/write/exists در یک API |

---

## ۲. ساخت Path

```python
from pathlib import Path

# مسیر نسبی
p = Path("notes.txt")

# مسیر مطلق
home = Path.home()           # /home/user یا C:\Users\...
cwd = Path.cwd()             # پوشه جاری

# نسبت به فایل جاری
base = Path(__file__).parent
config = base / "config" / "app.ini"
```

### بخش‌های مسیر

```python
p = Path("/home/user/docs/report.pdf")
print(p.name)        # report.pdf
print(p.stem)        # report
print(p.suffix)      # .pdf
print(p.parent)      # /home/user/docs
print(p.parts)       # ('/', 'home', 'user', 'docs', 'report.pdf')
```

---

## ۳. بررسی وجود و نوع

```python
p = Path("data/logs/app.log")

p.exists()       # True/False
p.is_file()      # فایل معمولی؟
p.is_dir()       # پوشه؟
p.is_symlink()   # لینک نمادین؟
```

---

## ۴. خواندن و نوشتن

```python
file_path = Path("notes.txt")

# نوشتن
file_path.write_text("سلام دنیا!\nخط دوم", encoding="utf-8")

# خواندن
content = file_path.read_text(encoding="utf-8")

# باینری
data = file_path.read_bytes()
file_path.write_bytes(b"\x00\x01")

# append با open
with file_path.open("a", encoding="utf-8") as f:
    f.write("\nخط جدید")
```

**نکته:** برای فایل بزرگ از `open()` خط‌به‌خط استفاده کنید، نه `read_text()` کل فایل.

---

## ۵. ایجاد و حذف

```python
data_dir = Path("data")
data_dir.mkdir(exist_ok=True)              # یک سطح
data_dir.mkdir(parents=True, exist_ok=True)  # کل زنجیره

backup = Path("backup/old.txt")
backup.unlink(missing_ok=True)   # حذف فایل

empty_dir = Path("temp")
empty_dir.rmdir()                # فقط پوشه خالی

import shutil
shutil.rmtree("temp_tree")       # پوشه پر
```

---

## ۶. glob و rglob

```python
root = Path("project")

# همه .py در پوشه جاری
for py in root.glob("*.py"):
    print(py)

# بازگشتی — همه .py در زیردرخت
for py in root.rglob("*.py"):
    print(py.relative_to(root))

# الگوی پیچیده
list(root.glob("**/*.log"))
```

---

## ۷. Metadata

```python
p = Path("report.pdf")
stat = p.stat()
print(stat.st_size)          # اندازه بایت
print(stat.st_mtime)         # زمان آخرین تغییر

from datetime import datetime
modified = datetime.fromtimestamp(stat.st_mtime)
print(modified.strftime("%Y-%m-%d %H:%M"))
```

---

## ۸. resolve و relative_to

```python
p = Path("../data/file.txt")
print(p.resolve())           # مسیر مطلق canonical

base = Path("/app")
full = Path("/app/src/main.py")
print(full.relative_to(base))  # src/main.py
```

---

## ۹. rename و copy

```python
import shutil

src = Path("draft.txt")
dst = Path("archive/draft.txt")
dst.parent.mkdir(parents=True, exist_ok=True)

src.rename(dst)                    # جابجایی/تغییر نام
shutil.copy2(dst, Path("backup/draft.txt"))  # کپی با metadata
```

---

## ۱۰. الگوی ساختار پروژه

```python
from pathlib import Path

PROJECT_ROOT = Path(__file__).resolve().parent.parent
DATA_DIR = PROJECT_ROOT / "data"
LOG_DIR = PROJECT_ROOT / "logs"

for d in (DATA_DIR, LOG_DIR):
    d.mkdir(exist_ok=True)

log_file = LOG_DIR / f"{datetime.today():%Y-%m-%d}.log"
```

---

## ۱۱. جستجوی فایل

```python
def find_files(root: Path, pattern: str) -> list[Path]:
    return sorted(root.rglob(pattern))

def count_lines(path: Path) -> int:
    return sum(1 for _ in path.open(encoding="utf-8"))

py_files = find_files(Path("."), "*.py")
for f in py_files:
    print(f"{f}: {count_lines(f)} خط")
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_path_basics.py](./examples/01_path_basics.py) | ساخت و تحلیل Path |
| [02_read_write.py](./examples/02_read_write.py) | خواندن/نوشتن |
| [03_glob_walk.py](./examples/03_glob_walk.py) | glob و پیمایش |
| [04_project_layout.py](./examples/04_project_layout.py) | ساختار پروژه real-world |

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `str` + `\` دستی | شکست cross-platform | `Path / "sub"` |
| `read_text()` فایل 2GB | MemoryError | `open()` خط‌به‌خط |
| `mkdir()` بدون `parents` | FileNotFoundError | `mkdir(parents=True)` |
| مسیر نسبی مبهم | فایل جای اشتباه | `Path(__file__).parent` |
| فراموش encoding | کاراکتر فارسی خراب | `encoding="utf-8"` |

---

## ۱۴. pathlib vs os

| کار | os | pathlib |
|-----|-----|---------|
| join | `os.path.join(a,b)` | `Path(a)/b` |
| exists | `os.path.exists(p)` | `Path(p).exists()` |
| basename | `os.path.basename(p)` | `Path(p).name` |
| listdir | `os.listdir(d)` | `Path(d).iterdir()` |

---

## ۱۵. تمرین کلاسی

1. مسیر `data/users.txt` بسازید و نام/stem/suffix چاپ کنید
2. پوشه `logs/` ایجاد و فایل log با timestamp بنویسید
3. همه `.txt` فایل‌های پوشه را با `glob` لیست کنید
4. بزرگترین فایل در یک پوشه را پیدا کنید

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. Path در type hints

```python
from pathlib import Path

def load_config(path: Path) -> dict[str, str]:
    lines = path.read_text(encoding="utf-8").splitlines()
    return dict(line.split("=", 1) for line in lines if "=" in line)
```

---

## ۱۷. Temporary paths

```python
import tempfile

with tempfile.TemporaryDirectory() as tmp:
    work = Path(tmp) / "scratch.txt"
    work.write_text("موقت", encoding="utf-8")
# پوشه خودکار پاک می‌شود
```

---

## 📚 مطالعه تکمیلی

- [pathlib — Python docs](https://docs.python.org/3/library/pathlib.html)
- PEP 428 — pathlib introduction

---

**جلسه قبل:** [۲۵ — CSV](../session-25-csv/) | **بعد:** [۲۷ — Logging](../session-27-logging/)
