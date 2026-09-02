# جلسه ۳۵: کلاینت HTTP — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- مفاهیم HTTP: request, response, status code
- `urllib.request` — GET, POST
- Headers و User-Agent
- JSON API با stdlib
- error handling: HTTPError, URLError
- الگوهای real-world: API client, retry

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | HTTP overview |
| 10–25 | urlopen GET |
| 25–40 | Request و headers |
| 40–55 | POST و JSON |
| 55–70 | error handling |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. HTTP در یک نگاه

```
Client  --GET /api/users-->  Server
Client  <--200 OK + JSON---  Server
```

| کد | معنی |
|----|------|
| 200 | OK |
| 404 | Not Found |
| 500 | Server Error |

---

## ۲. urlopen — GET

```python
from urllib.request import urlopen

with urlopen("https://example.com", timeout=10) as response:
    print(response.status)
    print(response.headers["Content-Type"])
    body = response.read()
    print(body.decode("utf-8")[:200])
```

---

## ۳. Request با headers

```python
from urllib.request import Request, urlopen

req = Request(
    "https://api.example.com/data",
    headers={"User-Agent": "MyApp/1.0", "Accept": "application/json"},
)
with urlopen(req, timeout=10) as resp:
    data = resp.read()
```

---

## ۴. POST با JSON

```python
import json
from urllib.request import Request, urlopen

payload = json.dumps({"name": "سعید"}).encode("utf-8")
req = Request(
    "https://api.example.com/users",
    data=payload,
    headers={"Content-Type": "application/json"},
    method="POST",
)
with urlopen(req) as resp:
    print(resp.read().decode())
```

---

## ۵. error handling

```python
from urllib.error import HTTPError, URLError
from urllib.request import urlopen

try:
    with urlopen("https://example.com/404") as resp:
        pass
except HTTPError as e:
    print(e.code, e.reason)
except URLError as e:
    print("شبکه:", e.reason)
```

---

## ۶. FakeResponse برای تست

```python
class FakeResponse:
    status = 200
    headers = {"Content-Type": "text/html"}

    def __enter__(self):
        return self
    def __exit__(self, *args):
        pass
    def read(self, n=-1):
        return b"<html>Hello</html>"
```

---

## ۷. parse JSON response

```python
import json
from urllib.request import urlopen

with urlopen("https://api.github.com/users/python") as resp:
    data = json.loads(resp.read().decode())
    print(data["name"])
```

---

## ۸. timeout

```python
urlopen(url, timeout=5)  # ثانیه
```

---

## ۹. urllib vs requests

| | urllib | requests |
|---|--------|----------|
| dependency | stdlib | pip install |
| API | verbose | simple |
| session | manual | Session() |

در این دوره stdlib — requests در پروژه‌های real-world رایج‌تر است.

---

## ۱۰. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_urlopen_basics.py](./examples/01_urlopen_basics.py) | GET پایه |
| [02_request_headers.py](./examples/02_request_headers.py) | headers |
| [03_post_json.py](./examples/03_post_json.py) | POST JSON |
| [04_api_client.py](./examples/04_api_client.py) | client pattern |

---

## ۱۱. امنیت

- HTTPS برای production
- API key در header — نه URL
- validate SSL (پیش‌فرض فعال)
- rate limiting

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| فراموش timeout | hang |
| read() دوبار | خالی |
| API key در query string | log leak |
| بدون User-Agent | block توسط بعضی API |

---

## ۱۳. تمرین کلاسی

1. FakeResponse — status و body
2. Request با custom header
3. POST JSON شبیه‌سازی
4. HTTPError handling

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۴. Retry pattern

```python
import time

def fetch_with_retry(url, retries=3):
    for attempt in range(retries):
        try:
            with urlopen(url, timeout=5) as r:
                return r.read()
        except URLError:
            if attempt == retries - 1:
                raise
            time.sleep(2 ** attempt)
```

---

## ۱۵. urllib.parse

```python
from urllib.parse import urlencode, urlparse

params = urlencode({"q": "پایتون", "page": 1})
url = f"https://api.test/search?{params}"
parsed = urlparse(url)
print(parsed.scheme, parsed.netloc, parsed.path)
```

---

## ۱۶. Request method

```python
Request(url, method="DELETE")
Request(url, method="PUT", data=body)
```

---

## ۱۷. سوالات کلاسی

- تفاوت HTTPError و URLError؟
- چرا timeout مهم است؟
- GET vs POST؟

---

## ۱۸. ssl context (مرجع)

```python
import ssl
ctx = ssl.create_default_context()
# urlopen(url, context=ctx)
```

---

## ۱۹. rate limiting

```python
import time
time.sleep(1)  # بین درخواست‌ها — polite scraping
```

---

## ۲۰. جمع‌بندی

HTTP client پل **برنامه شما به API جهان** — urllib stdlib است؛ requests برای ergonomics بهتر.

**تکلیف خانه:** [question.md](./exercises/question.md) — ApiClient با retry.

---

## 📚 مطالعه تکمیلی

- [urllib.request — Python docs](https://docs.python.org/3/library/urllib.request.html)
- MDN HTTP overview

---

**جلسه قبل:** [۳۴ — Concurrent Futures](../session-34-concurrent-futures/) | **بعد:** [۳۶ — HTML Parsing](../session-36-html-parsing/)
