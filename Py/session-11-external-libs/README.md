# جلسه ۱۱: کتابخانه‌های بیرونی و pip (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت stdlib و third-party package را توضیح دهید
- با `pip install`, `pip list`, `pip freeze` کار کنید
- `requirements.txt` بسازید و از آن نصب کنید
- کتابخانه `requests` را برای HTTP استفاده کنید
- fallback بدون اینترنت (mock) برای آموزش داشته باشید
- venv + pip را در workflow پروژه ترکیب کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | PyPI، pip، venv مرور |
| 10–25 | نصب و مدیریت پکیج |
| 25–40 | requirements.txt |
| 40–60 | requests — GET و status |
| 60–75 | JSON response (معرفی) |
| 75–85 | امنیت و نسخه‌گذاری |
| 85–90 | تکلیف |

---

## ۱. کتابخانه بیرونی

**Standard Library** — همراه Python (math, os, ...)

**Third-party** — از [PyPI](https://pypi.org) با pip:

```bash
pip install requests
pip install requests==2.31.0
pip list
pip show requests
```

---

## ۲. requirements.txt

```text
requests>=2.28.0
```

```bash
pip install -r requirements.txt
pip freeze > requirements.txt
```

**بهترین عمل:** requirements را commit کنید — reproducible environment.

---

## ۳. requests — HTTP

```python
import requests

response = requests.get("https://httpbin.org/get", timeout=5)
print(response.status_code)  # 200
print(response.text[:200])
print(response.headers["Content-Type"])
```

**خط به خط:**
- `get(url)` — درخواست GET
- `timeout` — جلوگیری از hang
- `status_code` — 200 OK, 404 Not Found, ...
- `raise_for_status()` — خطا برای 4xx/5xx

---

## ۴. handle خطا

```python
try:
    r = requests.get(url, timeout=5)
    r.raise_for_status()
except requests.exceptions.RequestException as e:
    print("خطای شبکه:", e)
```

---

## ۵. بدون اینترنت — mock

```python
class MockResponse:
    status_code = 200
    text = '{"message": "offline demo"}'

# در کلاس یا تست از mock استفاده کنید
```

---

## ۶. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_pip_demo.py](./examples/01_pip_demo.py) | راهنمای pip |
| [02_requests_basic.py](./examples/02_requests_basic.py) | GET ساده |
| [03_requests_error.py](./examples/03_requests_error.py) | handle خطا |
| [04_offline_mock.py](./examples/04_offline_mock.py) | mock بدون شبکه |

---

## ۷. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| pip global بدون venv | همیشه venv |
| نسخه unpinned | requirements با نسخه |
| timeout ندادن | timeout=5 |
| API key در کد | env variable |

---

## ۸. تمرین کلاسی

1. `pip install requests`
2. GET به httpbin.org/get
3. چاپ status و ۱۰۰ کاراکتر اول
4. requirements.txt با requests

**تکلیف:** [question.md](./exercises/question.md)

### ۹.۱. virtualenv workflow

```bash
python -m venv venv
venv\Scripts\activate
pip install requests
pip freeze > requirements.txt
```

### ۹.۲. POST request (پیش‌نمایش)

```python
# requests.post(url, json={"key": "value"})
```

---

## ۱۰. تکلیف خانه

1. **weather stub** — mock API client
2. **download file** — GET و save to disk
3. **pin versions** در requirements

---

## ۱۲. توضیح خط‌به‌خط مثال‌ها

### 📄 `02_requests_basic.py`

```python
response = requests.get(URL, timeout=10, params={...})
```

- `params` → query string `?course=python`
- `timeout` — ثانیه — الزامی در production

### 📄 `04_offline_mock.py`

- MockResponse API مشابه requests
- `json.dumps(..., ensure_ascii=False)` — فارسی در JSON

### pip workflow

1. `python -m venv venv`
2. activate
3. `pip install package`
4. `pip freeze > requirements.txt`

---

## ۱۴. پکیج‌های محبوب (مرور)

| پکیج | کاربرد |
|------|--------|
| requests | HTTP |
| httpx | HTTP async |
| pandas | داده |
| flask | وب |
| pytest | تست |

---

## ۱۵. امنیت pip

- فقط از PyPI یا mirror معتبر
- `pip install package==version` — pin کنید
- API keys در `.env` نه در کد
- `.gitignore` برای venv و `.env`

---

## ۱۶. مطالعه بیشتر

- [PyPI](https://pypi.org)
- [pip docs](https://pip.pypa.io/)
- تمرین: requirements.txt + README نصب

---

## ۱۸. تکلیف خانه (جزئیات)

** fetch_and_save(url, path):** GET و save HTML

** compare versions:** `pip show requests` vs requirements

** mock test:** assert MockResponse(200).status_code == 200

---

## ۲۰. پرسش‌های کلاسی (Q&A)

**س: pip vs conda؟**  
ج: pip برای PyPI — conda ecosystem wider (مقدماتی: pip).

**س: venv vs virtualenv؟**  
ج: venv built-in Python 3.

**س: requests vs urllib؟**  
ج: requests ساده‌تر — urllib stdlib.

---

## ۲۱. خلاصه

- ✅ pip — نصب از PyPI
- ✅ requirements.txt
- ✅ requests.get + status_code
- ✅ timeout و exception handling

---

**جلسه قبل:** [۱۰ — ماژول‌ها](../session-10-modules/) | **بعد:** [۱۲ — پروژه نهایی](../session-12-final-project/)
