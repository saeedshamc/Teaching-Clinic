# جلسه ۳۷: متغیر محیطی و پیکربندی — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- `os.environ` — خواندن متغیر محیط
- جداسازی config از کد
- `.env` files (مفهوم — python-dotenv مرجع)
- type coercion و validation
- `@dataclass` برای Settings
- secrets و امنیت
- الگوهای real-world: 12-factor app config

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا config جدا؟ |
| 10–25 | os.environ.get |
| 25–40 | type conversion |
| 40–55 | Settings class |
| 55–70 | .env و dotenv |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. چرا hard-code بد است؟

```python
# بد
API_KEY = "sk-secret123"
DB_HOST = "localhost"
```

- dev/staging/prod متفاوت
- secrets در git leak
- deploy بدون تغییر کد

---

## ۲. os.environ

```python
import os

host = os.environ.get("APP_HOST", "localhost")
port = int(os.environ.get("APP_PORT", "8000"))
debug = os.environ.get("DEBUG", "").lower() in ("1", "true", "yes")

print(f"{host}:{port} debug={debug}")
```

---

## ۳. os.environ vs os.getenv

```python
os.getenv("KEY", "default")  # معادل os.environ.get
```

---

## ۴. set برای تست

```python
os.environ["APP_HOST"] = "test.local"
# فقط در همان process — subprocess inherit می‌کند
```

---

## ۵. Settings dataclass

```python
from dataclasses import dataclass
import os

@dataclass(frozen=True)
class Settings:
    host: str
    port: int
    debug: bool
    database_url: str

    @classmethod
    def from_env(cls) -> "Settings":
        return cls(
            host=os.environ.get("APP_HOST", "127.0.0.1"),
            port=int(os.environ.get("APP_PORT", "8000")),
            debug=os.environ.get("DEBUG", "").lower() == "true",
            database_url=os.environ.get(
                "DATABASE_URL", "sqlite:///app.db"
            ),
        )
```

---

## ۶. validation

```python
def require_env(key: str) -> str:
    value = os.environ.get(key)
    if not value:
        raise RuntimeError(f"متغیر محیط {key} الزامی است")
    return value
```

---

## ۷. .env file (مفهوم)

```
APP_HOST=0.0.0.0
APP_PORT=8080
DEBUG=true
SECRET_KEY=change-me-in-production
```

```python
# pip install python-dotenv
# from dotenv import load_dotenv
# load_dotenv()  # بارگذاری .env به environ
```

در این جلسه manual loader هم می‌سازیم.

---

## ۸. manual .env loader

```python
def load_dotenv(path: str = ".env") -> None:
    from pathlib import Path
    p = Path(path)
    if not p.exists():
        return
    for line in p.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if not line or line.startswith("#"):
            continue
        key, _, value = line.partition("=")
        os.environ.setdefault(key.strip(), value.strip())
```

---

## ۹. 12-Factor App

- Config در environment
- dev/prod parity
- secrets در vault/env — نه repo

---

## ۱۰. امنیت

| انجام دهید | انجام ندهید |
|------------|-------------|
| `.env` در `.gitignore` | commit secrets |
| env vars در CI/CD | log کردن API key |
| rotate keys | share .env در chat |

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_environ_basics.py](./examples/01_environ_basics.py) | os.environ |
| [02_settings_class.py](./examples/02_settings_class.py) | Settings |
| [03_dotenv_loader.py](./examples/03_dotenv_loader.py) | .env loader |
| [04_app_config.py](./examples/04_app_config.py) | app pattern |

---

## ۱۲. pathlib + config file

```python
from pathlib import Path
import json

config_path = Path(os.environ.get("CONFIG_PATH", "config.json"))
if config_path.exists():
    config = json.loads(config_path.read_text())
```

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| default secret در prod | insecure |
| int() بدون try | crash |
| .env در git | leak |
| print(os.environ) | secrets exposed |

---

## ۱۴. تمرین کلاسی

1. host/port با default
2. Settings.from_env()
3. require_env برای SECRET
4. load_dotenv ساده

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۵. environment prefix

```python
PREFIX = "MYAPP_"

def get_prefixed(key: str, default=None):
    return os.environ.get(f"{PREFIX}{key}", default)
```

---

## ۱۶. config hierarchy

```
1. default در کد
2. فایل config.json
3. .env
4. environment variables (بالاترین اولویت)
```

---

## ۱۷. pydantic Settings (مرجع)

```python
# pip install pydantic-settings
# class Settings(BaseSettings):
#     model_config = SettingsConfigDict(env_file=".env")
```

---

## ۱۸. سوالات کلاسی

- چرا secret در env نه کد؟
- تفاوت setdefault و override در dotenv؟
- 12-factor config چیست؟

---

## ۱۹. CI/CD secrets

```yaml
# GitHub Actions:
# env:
#   SECRET_KEY: ${{ secrets.SECRET_KEY }}
```

---

## ۲۰. config per environment

```python
# APP_ENV=development → debug on
# APP_ENV=production → require secrets
```

---

## ۲۱. جمع‌بندی

config در env = **deploy بدون تغییر کد** — اصل 12-factor را رعایت کنید.

---

## 📚 مطالعه تکمیلی

- [os.environ — Python docs](https://docs.python.org/3/library/os.html#os.environ)
- [The Twelve-Factor App — Config](https://12factor.net/config)
- python-dotenv

---

**جلسه قبل:** [۳۶ — HTML Parsing](../session-36-html-parsing/) | **بعد:** [۳۸ — ABC Interfaces](../session-38-abc-interfaces/)
