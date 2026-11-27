# جلسه ۱۵ — Middleware

## درس

Middleware تابع/کلاسی است که **هر درخواست و پاسخ** را قبل و بعد از ویو پردازش می‌کند — مثل لایه‌های پیاز.

```
Request → Middleware1 → Middleware2 → View → Middleware2 → Middleware1 → Response
```

### middlewareهای پیش‌فرض مهم

- `SecurityMiddleware`، `SessionMiddleware`، `CsrfViewMiddleware`، `AuthenticationMiddleware`، `MessagesMiddleware`

### ساخت middleware سفارشی

```python
# library/middleware.py
import time

class TimingMiddleware:
    """زمان پاسخ‌گویی را در هدر X-Response-Time می‌گذارد."""

    def __init__(self, get_response):
        self.get_response = get_response   # ویوی بعدی در زنجیره

    def __call__(self, request):
        start = time.time()
        response = self.get_response(request)  # ادامهٔ زنجیره تا ویو
        response["X-Response-Time"] = f"{(time.time()-start)*1000:.1f}ms"
        return response
```

ثبت در `settings.py`:

```python
MIDDLEWARE = [
    # ...
    "library.middleware.TimingMiddleware",   # آخرین = نزدیک‌ترین به ویو
]
```

### middleware قدیمی (new-style vs old-style)

نسخهٔ قدیمی `process_request/process_response/process_view/process_exception` داشت؛ نسخهٔ جدید فقط `__call__` با `get_response`.

## کد نمونه

`code/middleware.py` را ببینید — سه مثال کاربردی.

## تمرین

یک middleware بنویسید که IP کاربر را در `request.client_ip` ذخیره کند.

## جواب تمرین

```python
class ClientIPMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        x_forwarded = request.META.get("HTTP_X_FORWARDED_FOR")
        request.client_ip = x_forwarded.split(",")[0] if x_forwarded else request.META.get("REMOTE_ADDR")
        return self.get_response(request)
```
