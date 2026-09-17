# جلسه ۴۰: بسته‌بندی با pyproject.toml (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت اسکریپت، ماژول و بسته (package) را توضیح دهید
- ساختار `pyproject.toml` استاندارد PEP 621 را بخوانید و بنویسید
- متادیتای پروژه (name، version، dependencies) تعریف کنید
- entry point برای CLI تنظیم کنید
- با `pip install -e .` نصب editable انجام دهید
- تفاوت `requirements.txt` و `pyproject.toml` را بدانید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | ماژول vs package vs distribution |
| 10–30 | pyproject.toml | بخش [project] |
| 30–45 | وابستگی‌ها | dependencies، optional |
| 45–60 | ساختار src/ | layout استاندارد |
| 60–75 | build و install | pip install -e . |
| 75–85 | entry points | console_scripts |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. چرا بسته‌بندی؟

تا اینجا اسکript و ماژول نوشتید. وقتی پروژه بزرگ می‌شود:
- دیگران باید بتوانند `pip install your-package` بزنند
- نسخه و وابستگی‌ها باید شفاف باشند
- تست و CI به ساختار قابل تکرار نیاز دارند

**Distribution (توزیع)** = فایل‌هایی که روی PyPI یا داخل تیم منتشر می‌شوند.

---

## ۲. سطوح سازماندهی کد

| سطح | مثال | توضیح |
|-----|------|-------|
| Script | `hello.py` | یک فایل اجرایی |
| Module | `utils.py` | یک فایل importable |
| Package | `myapp/` + `__init__.py` | پوشه با ماژول‌های مرتبط |
| Project | `pyproject.toml` + src | کل محصول قابل نصب |

---

## ۳. pyproject.toml چیست؟

فایل **تک‌منبع حقیقت** (single source of truth) برای:
- نام و نسخه پروژه
- نویسنده و توضیحات
- وابستگی‌ها
- ابزار build (setuptools، hatchling، poetry)

```toml
[project]
name = "teaching-clinic-tools"
version = "0.1.0"
description = "ابزارهای آموزشی کلینیک"
readme = "README.md"
requires-python = ">=3.10"
dependencies = [
    "requests>=2.28",
]

[project.scripts]
clinic-cli = "teaching_clinic_tools.cli:main"

[build-system]
requires = ["setuptools>=68"]
build-backend = "setuptools.build_meta"
```

---

## ۴. فیلدهای مهم [project]

| فیلد | الزامی | توضیح |
|------|--------|-------|
| `name` | ✅ | نام PyPI (با خط تیره) |
| `version` | ✅ | semver: MAJOR.MINOR.PATCH |
| `requires-python` | توصیه | حداقل نسخه Python |
| `dependencies` | اختیاری | پکیج‌های runtime |
| `optional-dependencies` | اختیاری | گروه dev، test |

---

## ۵. ساختار src layout (توصیه‌شده)

```
my-project/
├── pyproject.toml
├── README.md
├── src/
│   └── teaching_clinic_tools/
│       ├── __init__.py
│       ├── cli.py
│       └── core.py
└── tests/
    └── test_core.py
```

**مزیت src layout:** accidental import از پوشه جاری جلوگیری می‌شود — تست واقعی نصب را شبیه‌سازی می‌کند.

---

## ۶. __init__.py و __version__

```python
# src/teaching_clinic_tools/__init__.py
__version__ = "0.1.0"
```

نسخه می‌تواند در pyproject.toml باشد؛ بعضی پروژه‌ها هر دو را sync می‌کنند.

---

## ۷. نصب editable

```bash
cd my-project
python -m venv .venv
.venv\Scripts\activate        # Windows
pip install -e ".[dev]"       # نصب با extras
```

`-e` = editable: تغییر کد بلافاصله effect دارد بدون reinstall.

---

## ۸. requirements.txt vs pyproject.toml

| | requirements.txt | pyproject.toml |
|---|-----------------|----------------|
| استاندارد | قدیمی، ساده | مدرن (PEP 621) |
| metadata | فقط لیست پکیج | نام، نسخه، scripts |
| build | ❌ | ✅ |
| lock file | pip freeze | poetry.lock / uv.lock |

برای پروژه جدید: **pyproject.toml** اولویت دارد.

---

## ۹. entry points — console_scripts

```toml
[project.scripts]
mytool = "mypackage.cli:main"
```

بعد از `pip install .` دستور `mytool` در PATH در دسترس است.

---

## ۱۰. optional-dependencies

```toml
[project.optional-dependencies]
dev = ["pytest>=7", "ruff>=0.1"]
docs = ["sphinx>=7"]
```

نصب: `pip install ".[dev]"`

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_project_metadata.py](./examples/01_project_metadata.py) | خواندن متادیتا از dict |
| [02_pyproject_structure.py](./examples/02_pyproject_structure.py) | نمایش ساختار TOML |
| [03_package_layout.py](./examples/03_package_layout.py) | src layout و import |
| [04_version_and_entry.py](./examples/04_version_and_entry.py) | version و entry point |

---

## ۱۲. توضیح — `01_project_metadata.py`

شبیه‌سازی متادیتایی که در pyproject.toml ذخیره می‌شود — برای درک فیلدها بدون نیاز به build واقعی.

---

## ۱۳. build و publish (مرور)

```bash
pip install build
python -m build          # sdist + wheel در dist/
# twine upload dist/*  # انتشار PyPI (خارج از scope این جلسه)
```

---

## ۱۴. اجرای مثال‌ها

```bash
python session-40-packaging-pyproject/examples/01_project_metadata.py
python session-40-packaging-pyproject/examples/02_pyproject_structure.py
python session-40-packaging-pyproject/examples/03_package_layout.py
python session-40-packaging-pyproject/examples/04_version_and_entry.py
```

---

## ۱۵. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| نام package با `_` در PyPI | قرارداد خط تیره | `my-tool` نه `my_tool` |
| فراموش `__init__.py` | import package fail | فایل خالی هم کافی است |
| نصب از root بدون -e | import از cwd | `pip install -e .` |
| version hardcode پراکنده | ناسازگاری | یک منبع در pyproject |
| dependencies بدون bound | شکست نصب آینده | `>=2.28,<3` |

---

## ۱۶. PEPهای مرتبط

- **PEP 517/518:** build system در pyproject
- **PEP 621:** metadata استاندارد [project]
- **PEP 440:** version specifiers

---

## ۱۷. workflow تیم

1. clone repo
2. `python -m venv .venv && activate`
3. `pip install -e ".[dev]"`
4. develop + test
5. bump version در pyproject.toml
6. tag + build + publish

---

## ۱۸. تمرین کلاسی

1. dict متادیتا با name، version، dependencies بسازید
2. ساختار پوشه src/ را روی کاغذ طراحی کنید
3. entry point فرضی برای CLI تعریف کنید

---

## ۱۹. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۲۰. خلاصه

```
pyproject.toml     → متادیتا + build + deps
src/package/       → layout توصیه‌شده
pip install -e .   → نصب editable
[project.scripts]  → CLI entry point
requires-python    → حداقل نسخه
```

---

**جلسه قبل:** [۳۹ — Property](../session-39-properties/) | **بعد:** [۴۱ — pytest](../session-41-pytest-intro/)

---

## ۲۱. workflow انتشار (release)

1. bump version در pyproject.toml
2. `python -m build`
3. تست wheel: `pip install dist/*.whl`
4. tag git: `v0.2.0`
5. upload PyPI (اختیاری)

---

## ۲۲. منابع

- [PEP 621](https://peps.python.org/pep-0621/) — project metadata
- [Python Packaging User Guide](https://packaging.python.org/)
- [setuptools docs](https://setuptools.pypa.io/)

---

## ۲۳. سوالات متداول

**requirements.txt حذف شود؟**
می‌توان از pyproject.toml export کرد: `pip freeze` یا pip-tools.

**poetry vs setuptools?**
poetry راحت‌تر برای deps؛ setuptools استاندارد minimal.
