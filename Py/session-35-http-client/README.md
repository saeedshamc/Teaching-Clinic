# جلسه ۳۵: کلاینت HTTP

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- درخواست HTTP را درک کنید
- با urllib یک درخواست ساده بزنید
- پاسخ و کد وضعیت را بخوانید

---

## HTTP Client چیست؟

کلاینت HTTP برای صحبت با APIها و وب‌سرویس‌ها استفاده می‌شود. در کتابخانه استاندارد، urllib این کار را انجام می‌دهد.

```python
from urllib.request import urlopen

with urlopen("https://example.com") as response:
    print(response.status)
    html = response.read(100)
    print(html[:50])
```

### توضیح
- `urlopen` → درخواست GET می‌فرستد
- `status` → کد وضعیت مثل ۲۰۰
- `read` → بدنه پاسخ را می‌خواند

---

## نکته شبکه

```python
# در صورت دسترسی به اینترنت:
# from urllib.request import urlopen
# with urlopen("https://example.com") as r:
#     print(r.status)
```

---

## فایل‌های این جلسه
- [examples/01_http_client.py](./examples/01_http_client.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک FakeResponse بسازید
- تمرین متوسط: خلاصه status و length را چاپ کنید
