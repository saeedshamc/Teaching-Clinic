# جلسه ۴۵ — API Versioning و Throttling

## درس

### Versioning — نسخه‌بندی API

```python
# روش ۱: URL prefix
path("api/v1/books/", BookViewSetV1.as_view({"get": "list"})),
path("api/v2/books/", BookViewSetV2.as_view({"get": "list"})),

# روش ۲: Accept header
# GET /api/books/  +  Accept: application/vnd.library.v2+json

# روش ۳: query param — ?version=2 (کمتر توصیه می‌شود)
```

### Throttling در DRF

```python
from rest_framework.throttling import UserRateThrottle, AnonRateThrottle, ScopedRateThrottle

# settings.py
REST_FRAMEWORK = {
    "DEFAULT_THROTTLE_CLASSES": [
        "rest_framework.throttling.AnonRateThrottle",
        "rest_framework.throttling.UserRateThrottle",
    ],
    "DEFAULT_THROTTLE_RATES": {
        "anon": "100/hour",       # کاربر مهمان
        "user": "1000/hour",      # کاربر لاگین‌شده
        "burst": "10/minute",     # سفارشی scoped
    },
}

# throttle scoped روی ویو خاص
class ExpensiveAPIView(APIView):
    throttle_scope = "burst"
```

### Schema documentation — drf-spectacular

```bash
pip install drf-spectacular
# settings: INSTALLED_APPS += "drf_spectacular" و DEFAULT_SCHEMA_CLASS
# urls: path("api/schema/", SpectacularAPIView.as_view()), path("api/docs/", SpectacularSwaggerView.as_view())
```

## تمرین

یک throttle scope به نام `login` با نرخ `5/minute` تعریف کنید و روی ویوی login اعمال کنید.

## جواب تمرین

```python
REST_FRAMEWORK["DEFAULT_THROTTLE_RATES"]["login"] = "5/minute"

class LoginView(APIView):
    throttle_scope = "login"
```
