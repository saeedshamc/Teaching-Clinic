# جلسه ۲۸ — احراز هویت و دسترسی در DRF

## درس

### Authentication Classes

```python
# settings.py
REST_FRAMEWORK = {
    "DEFAULT_AUTHENTICATION_CLASSES": [
        "rest_framework.authentication.SessionAuthentication",   # session وب
        "rest_framework.authentication.TokenAuthentication",     # token برای API
        # "rest_framework_simplejwt.authentication.JWTAuthentication",  # JWT
    ],
    "DEFAULT_PERMISSION_CLASSES": [
        "rest_framework.permissions.IsAuthenticated",   # پیش‌فرض: فقط لاگین‌شده
    ],
}
```

### Permission Classes

```python
from rest_framework import permissions

class BookViewSet(viewsets.ModelViewSet):
    permission_classes = [permissions.IsAuthenticatedOrReadOnly]
    # خواندن برای همه، نوشتن فقط برای لاگین‌شده

    def get_permissions(self):
        # پرمیشن داینامیک بر اساس action
        if self.action in ["destroy", "update"]:
            return [permissions.IsAdminUser()]
        return [permissions.IsAuthenticatedOrReadOnly()]
```

### Token Authentication

```bash
pip install djangorestframework
# در settings: INSTALLED_APPS += "rest_framework.authtoken"
python manage.py migrate
# ساخت token برای کاربر: Token.objects.create(user=user)
# هدر درخواست: Authorization: Token <token>
```

## کد نمونه

بالا کامنت‌گذاری شده — در settings/views پیاده کنید.

## تمرین

برای `BookViewSet` پرمیشن داینامیک بنویسید: `list/retrieve` برای همه، `create` برای لاگین‌شده، `destroy` فقط برای staff.

## جواب تمرین

```python
def get_permissions(self):
    if self.action == "destroy":
        return [permissions.IsAdminUser()]
    if self.action in ("create", "update", "partial_update"):
        return [permissions.IsAuthenticated()]
    return [permissions.AllowAny()]
```
