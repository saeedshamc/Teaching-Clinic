# جلسه ۱۶: محیط مجازی و بسته‌بندی (Virtualenv & Packaging)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- دلیل استفاده از محیط مجازی را توضیح دهید
- با `python -m venv` محیط بسازید و فعال/غیرفعال کنید
- بسته‌ها را با `pip install` نصب و با `pip list` ببینید
- فایل `requirements.txt` بسازید و از آن نصب کنید
- تفاوت `pip freeze` و `requirements.txt` دستی را بدانید
- ساختار ساده یک پروژه Python را تشخیص دهید

---

## چرا محیط مجازی؟

**تشبیه:** هر پروژه مثل یک **آشپزخانه جدا** است. پروژه A به نمک قدیمی نیاز دارد، پروژه B به نمک جدید. اگر همه چیز در یک کابinet باشد، تداخل پیش می‌آید.

محیط مجازی (`venv`) یک پوشه جدا با Python و pip مخصوص همان پروژه است.

---

## ساخت و فعال‌سازی venv

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

## pip — مدیر بسته

```bash
# نصب یک بسته
pip install requests

# لیست بسته‌های نصب‌شده
pip list

# ذخیره لیست در requirements.txt
pip freeze > requirements.txt

# نصب از requirements
pip install -r requirements.txt
```

---

## requirements.txt

فایل متنی — هر خط یک بسته (اختیاری با نسخه):

```text
requests==2.31.0
pandas>=2.0.0
```

**چرا مهم است؟**
- همکاران همان نسخه‌ها را نصب می‌کنند
- deploy روی سرور قابل تکرار است
- پروژه بدون venv قابل reproduce نیست

---

## ساختار ساده پروژه

```text
my_project/
├── .venv/              # محیط مجازی (معمولاً در git نیست)
├── requirements.txt    # وابستگی‌ها
├── README.md
├── src/
│   └── main.py
└── tests/
    └── test_main.py
```

---

## فایل‌های این جلسه

| فایل | توضیح |
|------|-------|
| [examples/01_requirements_demo.py](./examples/01_requirements_demo.py) | شبیه‌سازی requirements در Python |
| [examples/02_project_structure.py](./examples/02_project_structure.py) | چک لیست پروژه و import محلی |
| [exercises/exercise_01.py](./exercises/exercise_01.py) | تمرین‌های این جلسه |

---

## توضیح کدها

### 📄 `01_requirements_demo.py`

```python
requirements = ["requests>=2.28", "pandas>=2.0"]
```

**خط به خط:**
- لیست رشته‌ها شبیه خطوط `requirements.txt`
- `>=` یعنی حداقل این نسخه
- `==` یعنی دقیقاً همین نسخه (برای reproducibility)

---

### 📄 `02_project_structure.py`

نمایش اینکه `__file__` مسیر فایل جاری را می‌دهد — پایه ساختار پروژه.

---

## نکات رایج اشتباهات (Common Pitfalls)

### ❌ اشتباه ۱: نصب global بدون venv
```bash
pip install pandas  # ❌ روی Python سیستم — تداخل پروژه‌ها
```

```bash
python -m venv .venv && source .venv/bin/activate
pip install pandas  # ✅ داخل پروژه
```

### ❌ اشتباه ۲: commit کردن .venv
```text
# ❌ پوشه .venv را در git نگذارید — حجم زیاد و وابسته به OS
# ✅ .gitignore → .venv/
```

### ❌ اشتباه ۳: فراموش کردن requirements.txt
```bash
# ❌ فقط pip install — همکار نمی‌داند چه نصب کند
pip freeze > requirements.txt  # ✅
```

---

## بهترین شیوه‌ها (Best Practices)

- هر پروژه یک venv جدا (`.venv` یا `venv`)
- `requirements.txt` را به‌روز نگه دارید
- برای کتابخانه publishشده از `pyproject.toml` استفاده کنید (پیشرفته)
- `.venv` را در `.gitignore` بگذارید
- قبل از deploy: `pip install -r requirements.txt` در محیط تمیز

---

## خلاصه جلسه

در این جلسه یاد گرفتید:
- ✅ venv جداسازی بسته‌های هر پروژه
- ✅ `pip install` / `pip freeze` / `requirements.txt`
- ✅ فعال‌سازی venv در Windows و Linux
- ✅ ساختار ساده پوشه پروژه
- ✅ reproducibility با freeze و requirements

---

## تمرین‌ها

### تمرین ۱ (ساده): لیست requirements
لیستی از ۳ بسته پیشنهادی برای پروژه «تحلیل داده» بنویسید.

### تمرین ۲ (متوسط): پارس requirements
رشته چندخطی شبیه `requirements.txt` را بخوانید و نام بسته‌ها را استخراج کنید.

### تمرین ۳ (پیشرفته): چک نسخه
تابعی بنویسید که خط `package>=1.0` را به نام و حداقل نسخه تجزیه کند.

راه‌حل‌ها در [exercises/exercise_01.py](./exercises/exercise_01.py) موجود است.

---

**جلسه بعدی:** [جلسه ۱۷ — AsyncIO](../session-17-asyncio/)
