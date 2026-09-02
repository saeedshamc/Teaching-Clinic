# جلسه ۴۷: مبانی امنیت در Python (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- OWASP Top 10 را در سطح مقدماتی بشناسید
- خطر SQL Injection و راه‌حل parameterized query را بدانید
- input validation و sanitization انجام دهید
- secrets را از کد جدا کنید (env vars)
- hashing رمز عبور با `hashlib` / `secrets` را بشناسید
- path traversal و command injection را اجتناب کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | mindset امنیت | defense in depth |
| 10–30 | input validation | whitelist |
| 30–45 | SQL injection | ? placeholders |
| 45–60 | secrets | os.environ |
| 60–75 | hashing | secrets.token |
| 75–85 | path/command | safe paths |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. امنیت یک فرآیند است

- **Validate input** — هر ورودی دشمن است
- **Least privilege** — کمترین دسترسی
- **Fail secure** — خطا اطلاعات لو ندهد
- **Never trust client** — frontend قابل دستکاری است

---

## ۲. SQL Injection

```python
# ❌ خطرناک
query = f"SELECT * FROM users WHERE name='{user_input}'"
# user_input = "' OR '1'='1" → همه users

# ✅ امن
query = "SELECT * FROM users WHERE name=?"
cursor.execute(query, (user_input,))
```

**همیشه** placeholder — sqlite3، psycopg2، SQLAlchemy.

---

## ۳. Input validation

```python
def is_safe_username(name: str) -> bool:
    return name.isalnum() and 3 <= len(name) <= 20
```

whitelist بهتر از blacklist — regex برای email/phone.

---

## ۴. Secrets در کد

```python
# ❌ هرگز
API_KEY = "sk-live-xxxxx"

# ✅
import os
API_KEY = os.environ["API_KEY"]
```

`.env` در `.gitignore` — از jupyter/notebook لو نرود.

---

## ۵. hashing رمز

```python
import hashlib
import secrets

salt = secrets.token_hex(16)
hashed = hashlib.pbkdf2_hmac("sha256", password.encode(), salt.encode(), 100_000)
```

برای production: **`bcrypt`** یا **`argon2`** — نه plain SHA256.

---

## ۶. secrets module

```python
import secrets
token = secrets.token_urlsafe(32)  # session token
```

`random` برای crypto امن نیست — `secrets` استفاده کنید.

---

## ۷. Path traversal

```python
# ❌
open(user_filename)  # user: "../../etc/passwd"

# ✅
from pathlib import Path
base = Path("/safe/dir")
path = (base / user_filename).resolve()
if not path.is_relative_to(base):
    raise ValueError("invalid path")
```

Python 3.9+: `is_relative_to`.

---

## ۸. Command injection

```python
# ❌
os.system(f"convert {user_file} out.png")

# ✅
subprocess.run(["convert", user_file, "out.png"], check=True)
```

لیست args — shell interpret نمی‌کند.

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_input_validation.py](./examples/01_input_validation.py) | validation |
| [02_sql_safe.py](./examples/02_sql_safe.py) | parameterized |
| [03_secrets_env.py](./examples/03_secrets_env.py) | env vars |
| [04_hashing_tokens.py](./examples/04_hashing_tokens.py) | hash و token |

---

## ۱۰. XSS (وب — مرور)

escape output در HTML — Flask/Jinja autoescape. خارج scope console ولی مهم.

---

## ۱۱. logging امن

```python
# ❌ logger.info(f"login password={pwd}")
logger.info("login attempt user=%s", username)
```

password، token، PII log نکنید.

---

## ۱۲. اجرای مثال‌ها

```bash
python session-47-security-basics/examples/01_input_validation.py
python session-47-security-basics/examples/02_sql_safe.py
python session-47-security-basics/examples/03_secrets_env.py
python session-47-security-basics/examples/04_hashing_tokens.py
```

---

## ۱۳. اشتباهات رایج

| اشتباه | خطر |
|--------|------|
| f-string در SQL | injection |
| pickle untrusted data | RCE |
| eval(user_input) | RCE |
| hardcoded secrets | leak |
| verbose errors to user | info disclosure |

---

## ۱۴. تمرین کلاسی

1. validator username/email
2. sqlite safe query
3. token generator

---

## ۱۵. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۶. خلاصه

```
validate input       → whitelist
SQL ? placeholders   → no f-string
os.environ           → secrets
secrets module       → crypto random
subprocess list      → no shell=True
never eval/pickle    → untrusted data
```

---

**جلسه قبل:** [۴۶ — Protocols](../session-46-protocols-typing/) | **بعد:** [۴۸ — Documentation](../session-48-documentation/)

---

## ۱۷. CSRF و session (وب)

- CSRF token برای form POST
- HttpOnly cookie برای session
- HTTPS اجباری در production

---

## ۱۸. dependency scanning

```bash
pip install pip-audit
pip-audit
```

---

## ۱۹. .env با python-dotenv

```python
from dotenv import load_dotenv
load_dotenv()  # dev only — production از env واقعی
```

---

## ۲۱. rate limiting (مرور)

```python
# token bucket — جلوگیری از brute force login
```

---

## ۲۲. CORS (وب)

API فقط از origin مجاز — header `Access-Control-Allow-Origin`.

---

## ۲۳. security checklist

- [ ] input validated
- [ ] SQL parameterized
- [ ] secrets in env
- [ ] HTTPS
- [ ] dependencies audited
- [ ] errors sanitized

---

## ۲۴. secure defaults

```python
DEBUG = os.getenv("DEBUG", "0") == "1"  # default False
```

fail secure — permissive default خطرناک است.

---

## ۲۵. مرور جلسه

امنیت لایه‌ای: validation → parameterized SQL → env secrets → safe subprocess.  
هیچ تکنیکی به‌تنهایی کافی نیست.

**منابع:** [OWASP Top 10](https://owasp.org/www-project-top-ten/) | [Python security warnings](https://docs.python.org/3/library/security_warnings.html)

---

## ۲۶. تمرین عملی در کلاس

یک endpoint فرضی login طراحی کنید:  
validation username → rate limit → parameterized query → hash password → session token.

هر مرحله یک لایه دفاع — defense in depth در عمل.

Security یک checkbox نیست — mindset مداوم در طراحی و code review است.
