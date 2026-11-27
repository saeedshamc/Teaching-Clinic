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

## 📖 شرح فایل `code/views.py`

- **Versioning** — چهار روش: `URLPathVersioning` (`/api/v1/`، `/api/v2/` با serializer جدا)، `AcceptHeaderVersioning` (header `vnd.library.v2+json`)، `QueryParameterVersioning` (`?version=2`)، `NamespaceVersioning`. برای سازگاری، URL prefix ساده‌ترین است.
- **Throttling** — `BurstThrottle` (۱۰/دقیقه)، `LoginThrottle` (۵/دقیقه برای brute-force)، `ExpensiveAPIOperationThrottle`؛ `throttle_scope` روی اکشن خاص.
- **`BookViewSet`** — `versioning_class` + `throttle_classes` + `@action` با `throttle_scope` جدا.
- **`LoginView`** — فقط throttle دارد؛ `authenticate`/`login` از django.contrib.auth.
- کامنت settings: `DEFAULT_VERSIONING_CLASS`/`ALLOWED_VERSIONS` و `DEFAULT_THROTTLE_RATES` برای همهٔ scopeها.
