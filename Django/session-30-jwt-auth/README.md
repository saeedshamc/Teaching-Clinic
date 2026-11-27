# جلسه ۳۰ — JWT Authentication

## درس

JWT (JSON Web Token) برای APIهای stateless است — به‌جای session، token امضاشده برمی‌گردد.

```bash
pip install djangorestframework-simplejwt
```

### settings.py

```python
INSTALLED_APPS += ["rest_framework_simplejwt"]
REST_FRAMEWORK = {
    "DEFAULT_AUTHENTICATION_CLASSES": [
        "rest_framework_simplejwt.authentication.JWTAuthentication",
    ],
}
```

### urls — endpointهای token

```python
# config/urls.py یا api_urls.py
from rest_framework_simplejwt.views import (
    TokenObtainPairView, TokenRefreshView, TokenVerifyView,
)

path("api/token/", TokenObtainPairView.as_view(), name="token_obtain"),
path("api/token/refresh/", TokenRefreshView.as_view(), name="token_refresh"),
path("api/token/verify/", TokenVerifyView.as_view(), name="token_verify"),
```

### جریان

1. `POST /api/token/` با `{username, password}` → `{access, refresh}`
2. درخواست‌های بعدی: `Authorization: Bearer <access_token>`
3. وقتی access منقضی شد → `POST /api/token/refresh/` با `{refresh}` → access جدید

### محافظت ویو

```python
from rest_framework.permissions import IsAuthenticated

class BookViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated]   # فقط با JWT معتبر
```

## کد نمونه

بالا کامنت‌گذاری شده — در settings/urls پیاده کنید.

## تمرین

یک ویوی `me` بنویسید که اطلاعات کاربر جاری (`request.user`) را با JWT برگرداند.

## جواب تمرین

```python
class MeView(APIView):
    permission_classes = [IsAuthenticated]
    def get(self, request):
        u = request.user
        return Response({"id": u.id, "username": u.username, "email": u.email})
# urls: path("api/me/", views.MeView.as_view())
```
