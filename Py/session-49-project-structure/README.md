# جلسه ۴۹: ساختار پروژه حرفه‌ای (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- ساختار src layout vs flat layout را مقایسه کنید
- separation of concerns (models, services, cli) اعمال کنید
- پوشه tests/ و conftest.py را تنظیم کنید
- config و constants را جدا کنید
- .gitignore و pyproject.toml را تکمیل کنید
- workflow توسعه تیم را توضیح دهید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | anti-patterns | god file |
| 10–30 | src layout | tree استاندارد |
| 30–45 | layers | models/services/cli |
| 45–60 | tests structure | mirror src |
| 60–75 | config | settings module |
| 75–85 | tooling | ruff, pytest |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. مشکل «همه‌چیز در main.py»

```python
# main.py — 2000 خط 😱
# DB + API + CLI + business logic
```

**علائم:** import circular، test سخت، onboarding کند.

---

## ۲. ساختار پیشنهادی

```
myapp/
├── pyproject.toml
├── README.md
├── .gitignore
├── src/
│   └── myapp/
│       ├── __init__.py
│       ├── __main__.py      # python -m myapp
│       ├── config.py
│       ├── models/
│       ├── services/
│       └── cli/
└── tests/
    ├── conftest.py
    └── test_services/
```

---

## ۳. Separation of Concerns

| لایه | مسئولیت |
|------|---------|
| models | dataclass، validation |
| services | business logic |
| repositories | data access |
| cli/api | interface کاربر |
| config | settings |

---

## ۴. __main__.py

```python
# python -m myapp
from myapp.cli.main import run
run()
```

entry تمیز بدون logic.

---

## ۵. config.py

```python
from dataclasses import dataclass
import os

@dataclass
class Settings:
    debug: bool = False
    db_path: str = "app.db"

    @classmethod
    def from_env(cls):
        return cls(debug=os.getenv("DEBUG") == "1")
```

---

## ۶. tests mirror src

```
src/myapp/services/task.py
tests/test_services/test_task.py
```

import path یکسان با نصب editable.

---

## ۷. .gitignore essentials

```
__pycache__/
.venv/
.env
*.pyc
dist/
.pytest_cache/
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_project_layout.py](./examples/01_project_layout.py) | tree |
| [02_layered_app.py](./examples/02_layered_app.py) | layers |
| [03_config_settings.py](./examples/03_config_settings.py) | Settings |
| [04_package_entry.py](./examples/04_package_entry.py) | __main__ pattern |

---

## ۹. flat vs src

| flat | src |
|------|-----|
| myapp/ در root | src/myapp/ |
| ساده‌تر کوچک | scale بهتر |
| import accident | test واقعی‌تر |

---

## ۱۰. monorepo (اشاره)

چند package در یک repo — advanced، خارج scope.

---

## ۱۱. CI (مرور)

```yaml
# .github/workflows/test.yml
- run: pip install -e ".[dev]"
- run: pytest
- run: ruff check .
```

---

## ۱۲. اجرای مثال‌ها

```bash
python session-49-project-structure/examples/01_project_layout.py
python session-49-project-structure/examples/02_layered_app.py
python session-49-project-structure/examples/03_config_settings.py
python session-49-project-structure/examples/04_package_entry.py
```

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| circular import | services ↔ models |
| logic in cli | untestable |
| no tests/ | regression |
| config scattered | magic strings |

---

## ۱۴. تمرین کلاسی

1. tree برای bookstore
2. TaskService جدا از CLI
3. Settings.from_env

---

## ۱۵. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۶. خلاصه

```
src/package/     → layout scale
models/services  → separation
tests/ mirror    → testability
config.py        → single settings
pyproject.toml   → metadata + deps
.gitignore       → secrets/cache out
```

---

**جلسه قبل:** [۴۸ — Documentation](../session-48-documentation/) | **بعد:** [۵۰ — Capstone](../session-50-capstone-project/)

---

## ۱۷. pre-commit hooks

```yaml
# .pre-commit-config.yaml
repos:
  - repo: https://github.com/astral-sh/ruff-pre-commit
    hooks:
      - id: ruff
```

---

## ۱۸. Makefile (اختیاری)

```makefile
test:
	pytest -v
lint:
	ruff check .
```

---

## ۱۹. naming conventions

| item | convention |
|------|------------|
| package | snake_case |
| class | PascalCase |
| function | snake_case |
| constant | UPPER_SNAKE |

---

## ۲۱. dependency injection container (مرور)

برای پروژه بزرگ: dependency-injector — خارج scope پایه.

---

## ۲۲. multiple packages monorepo

```
packages/
  core/
  cli/
  api/
```

---

## ۲۳. onboarding checklist

1. clone
2. venv + pip install -e ".[dev]"
3. pytest
4. read README
5. pick issue

---

## ۲۴. technical debt markers

```python
# TODO: refactor when v2
# FIXME: race condition under load
```
