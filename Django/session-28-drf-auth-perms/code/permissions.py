# permissions.py — Permission Classes سفارشی (جلسه ۲۸)
from rest_framework import permissions
from .models import Book


class IsBookOwner(permissions.BasePermission):
    """فقط نویسندهٔ کتاب می‌تواند آن را ویرایش/حذف کند."""

    def has_object_permission(self, request, view, obj):
        # برای unsafe methods (POST/PUT/PATCH/DELETE) چک کن
        if request.method in permissions.SAFE_METHODS:
            return True   # GET/HEAD/OPTIONS برای همه

        # فرض: Book.owner → User (از session-13 اضافه شده)
        return obj.owner == request.user


class IsStaffOrReadOnly(permissions.BasePermission):
    """خواندن برای همه، نوشتن فقط برای staff."""

    def has_permission(self, request, view):
        if request.method in permissions.SAFE_METHODS:
            return True
        return request.user and request.user.is_staff


class IsAuthorOrReadOnly(permissions.BasePermission):
    """فقط نویسنده (is_author flag) می‌تواند بنویسد."""

    def has_permission(self, request, view):
        if request.method in permissions.SAFE_METHODS:
            return True
        return request.user and getattr(request.user, "is_author", False)


# ══════════════════════════════════════════════════════════════════
# در views.py — استفاده از permissionها
# ══════════════════════════════════════════════════════════════════
# class BookViewSet(viewsets.ModelViewSet):
#     permission_classes = [IsBookOwner]   # یا ترکیب:
#     # permission_classes = [IsAuthenticatedOrReadOnly, IsBookOwner]
#
# برای permission داینامیک بر اساس action:
#     def get_permissions(self):
#         if self.action in ("update", "partial_update", "destroy"):
#             return [IsBookOwner()]
#         if self.action == "create":
#             return [IsAuthorOrReadOnly()]
#         return [permissions.AllowAny()]


# ══════════════════════════════════════════════════════════════════
# settings.py — تنظیمات authentication
# ══════════════════════════════════════════════════════════════════
# INSTALLED_APPS += ["rest_framework", "rest_framework.authtoken"]
#
# REST_FRAMEWORK = {
#     "DEFAULT_AUTHENTICATION_CLASSES": [
#         "rest_framework.authentication.SessionAuthentication",
#         "rest_framework.authentication.TokenAuthentication",
#     ],
#     "DEFAULT_PERMISSION_CLASSES": [
#         "rest_framework.permissions.IsAuthenticatedOrReadOnly",
#     ],
#     "DEFAULT_PAGINATION_CLASS": "rest_framework.pagination.PageNumberPagination",
#     "PAGE_SIZE": 20,
# }
#
# ساخت token:
#   python manage.py shell
#   >>> from rest_framework.authtoken.models import Token
#   >>> token = Token.objects.create(user=user)
#   # header: Authorization: Token <token.key>
