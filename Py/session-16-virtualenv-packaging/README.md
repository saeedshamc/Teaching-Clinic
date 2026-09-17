# جلسه ۱۶: محیط مجازی و بسته‌بندی (۱.۵ ساعت)

## ۰. مرور

در جلسه ۱۱ کتابخانه‌های بیرونی و pip را دیدید. امروز **ایزolation** — هر پروژه venv و requirements جدا.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- دلیل استفاده از محیط مجازی را توضیح دهید
- با `python -m venv` محیط بسازید و فعال/غیرفعال کنید
- بسته‌ها را با `pip install` نصب و با `pip list` ببینید
- فایل `requirements.txt` بسازید و از آن نصب کنید
- تفاوت `pip freeze` و `requirements.txt` دستی را بدانید
- ساختار ساده یک پروژه Python را تشخیص دهید
- `.gitignore` و reproducibility را درک کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | pip و کتابخانه‌های بیرونی — جلسه ۱۱ |
| 10–25 | چرا venv؟ | تداخل نسخه‌ها، ایزolation |
| 25–45 | ساخت و فعال‌سازی | Windows/Mac/Linux |
| 45–60 | pip و requirements | install، freeze، parse |
| 60–75 | ساختار پروژه | پوشه‌ها، .gitignore |
| 75–85 | تمرین کلاسی | پارس requirements |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. چرا محیط مجازی؟

**تشبیه:** هر پروژه مثل یک **آشپزخانه جدا** است. پروژه A به نمک قدیمی نیاز دارد، پروژه B به نمک جدید. اگر همه چیز در یک کابینت باشد، تداخل پیش می‌آید.

محیط مجازی (`venv`) یک پوشه جدا با Python و pip مخصوص همان پروژه است.

| بدون venv | با venv |
|-----------|---------|
| همه پروژه‌ها یک pip | هر پروژه pip جدا |
| تداخل نسخه‌ها | ایزolation |
| deploy غیرقابل پیش‌بینی | reproducible |

---

## ۲. ساخت و فعال‌سازی venv

```bash
# ۱. ساخت (یک بار)
python -m venv .venv

# ۲. فعال‌سازی

# Windows PowerShell:
.venv\Scripts\Activate.ps1

# Windows CMD:
.venv\Scripts\activate.bat

# Mac/Linux:
source .venv/bin/activate

# ۳. وقتی (venv) یا (.venv) در ابتدای خط دیدید، فعال است

# ۴. غیرفعال
deactivate
```

---

## ۳. pip — مدیر بسته

```bash
# نصب یک بسته
pip install requests

# نصب با نسخه مشخص
pip install pandas==2.0.0

# لیست بسته‌های نصب‌شده
pip list

# ذخیره لیست در requirements.txt
pip freeze > requirements.txt

# نصب از requirements
pip install -r requirements.txt

# حذف بسته
pip uninstall requests
```

---

## ۴. requirements.txt

```text
requests==2.31.0
pandas>=2.0.0
pytest>=7.0.0
# خط کامنت — pip نادیده می‌گیرد
```

| سینتکس | معنی |
|--------|------|
| `==2.31.0` | دقیقاً این نسخه |
| `>=2.0.0` | حداقل این نسخه |
| `~=2.0` | سازگار با 2.0.x |

---

## ۵. ساختار ساده پروژه

```text
my_project/
├── .venv/              # محیط مجازی (در git نیست)
├── .gitignore
├── requirements.txt
├── README.md
├── src/
│   └── main.py
└── tests/
    └── test_main.py
```

### .gitignore نمونه
```text
.venv/
__pycache__/
*.pyc
.env
```

---

## ۶. pip freeze vs requirements دستی

| روش | مزیت | عیب |
|-----|------|-----|
| `pip freeze` | دقیق، reproducible | همه وابستگی‌های transitive |
| دستی | تمیز، فقط مستقیم | ممکن است فراموش شود |

**توصیه:** برای پروژه کوچک دستی؛ برای deploy از freeze.

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_requirements_demo.py](./examples/01_requirements_demo.py) | پارس requirements |
| [02_project_structure.py](./examples/02_project_structure.py) | چک‌لیست پروژه |
| [03_version_parsing.py](./examples/03_version_parsing.py) | تجزیه نسخه |
| [04_dependency_check.py](./examples/04_dependency_check.py) | بررسی وابستگی |

---

## ۸. اشتباهات رایج (Pitfalls)

### ❌ نصب global بدون venv
```bash
pip install pandas  # ❌ روی Python سیستم
```

### ❌ commit کردن .venv
پوشه `.venv` را در git نگذارید — حجم زیاد و وابسته به OS.

### ❌ فراموش کردن requirements.txt
همکار نمی‌داند چه نصب کند.

### ❌ venv فعال نکردن قبل از pip
بسته روی Python اشتباه نصب می‌شود.

---

## ۹. خلاصه

- ✅ venv جداسازی بسته‌های هر پروژه
- ✅ `pip install` / `pip freeze` / `requirements.txt`
- ✅ `.venv` در `.gitignore`
- ✅ reproducibility با freeze

---

## ۱۰. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۱. workflow روزانه توسعه‌دهنده

```bash
# شروع کار روی پروژه
cd my_project
.venv\Scripts\Activate.ps1    # Windows
pip install -r requirements.txt

# بعد از نصب بسته جدید
pip install httpx
pip freeze > requirements.txt

# پایان کار
deactivate
```

---

## ۱۲. pip commands مفید

| دستور | کاربرد |
|-------|--------|
| `pip show requests` | اطلاعات بسته |
| `pip install -U pip` | به‌روزرسانی pip |
| `pip check` | بررسی conflict |
| `pip list --outdated` | بسته‌های قدیمی |
| `python -m pip install X` | امن‌تر از pip مستقیم |

---

## ۱۳. توضیح فایل‌های مثال

### 📄 `01_requirements_demo.py` — پارس خطوط requirements
### 📄 `02_project_structure.py` — `__file__` و چک‌لیست پوشه
### 📄 `03_version_parsing.py` — regex برای `package>=1.0`
### 📄 `04_dependency_check.py` — شبیه‌سازی `pip check`

---

## ۱۴. سوالات کلاسی

1. چرا `.venv` را commit نمی‌کنیم؟
2. تفاوت `pip install X` و `pip install -r requirements.txt`؟
3. `==` و `>=` در requirements چه زمانی استفاده می‌شوند؟

---

## ۱۵. تمرین عملی ترمینال (۲۰ دقیقه)

```bash
mkdir demo_project && cd demo_project
python -m venv .venv
.venv\Scripts\Activate.ps1
pip install requests
pip list
pip freeze > requirements.txt
type requirements.txt
deactivate
```

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [venv — Python docs](https://docs.python.org/3/library/venv.html)
- [pip user guide](https://pip.pypa.io/en/stable/user_guide/)

### چک‌لیست پایان جلسه

- [ ] venv ساخته و فعال شد
- [ ] pip install و freeze امتحان شد
- [ ] requirements.txt پارس شد
- [ ] exercise_01 و exercise_02
- [ ] `.gitignore` برای `.venv` توضیح داده شد
- [ ] pip check و conflict مفهومی

---

**جلسه بعدی:** [جلسه ۱۷ — AsyncIO](../session-17-asyncio/)
