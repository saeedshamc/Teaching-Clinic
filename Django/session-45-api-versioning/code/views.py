# views.py — API Versioning و Throttling (جلسه ۴۵)
from django.contrib.auth import authenticate, login
from rest_framework import viewsets, status
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.decorators import action
from rest_framework.throttling import UserRateThrottle, AnonRateThrottle, ScopedRateThrottle
from rest_framework.versioning import (
    URLPathVersioning,
    NamespaceVersioning,
    AcceptHeaderVersioning,
    QueryParameterVersioning,
)
from .models import Book
from .serializers import BookSerializer


# ══════════════════════════════════════════════════════════════════
# Versioning — ۴ روش
# ══════════════════════════════════════════════════════════════════

# روش ۱: URL path — /api/v1/books/ و /api/v2/books/
# class BookViewSetV1(viewsets.ModelViewSet):
#     versioning_class = URLPathVersioning
#     serializer_class = BookSerializerV1
#
# class BookViewSetV2(viewsets.ModelViewSet):
#     versioning_class = URLPathVersioning
#     serializer_class = BookSerializerV2   # فیلدهای بیشتر
#
# در urls.py:
#   path("api/v1/books/", BookViewSetV1.as_view({"get": "list", "post": "create"})),
#   path("api/v2/books/", BookViewSetV2.as_view({"get": "list", "post": "create"})),

# روش ۲: Accept header — کلاینت ورژن را در header می‌فرستد
# GET /api/books/  +  Accept: application/vnd.library.v2+json
#
# روش ۳: Query param — ?version=2 (کمتر توصیه می‌شود)
#
# روش ۴: Namespace — با app_name جدا برای هر نسخه


# ══════════════════════════════════════════════════════════════════
# Serializerهای V1 و V2 — برای versioning واقعی
# ══════════════════════════════════════════════════════════════════
# class BookSerializerV1(serializers.ModelSerializer):
#     """V1 — فیلدهای پایه."""
#     class Meta:
#         model = Book
#         fields = ["id", "title", "price"]
#
# class BookSerializerV2(serializers.ModelSerializer):
#     """V2 — فیلدهای بیشتر + relationها."""
#     author_name = serializers.CharField(source="author.name", read_only=True)
#     review_count = serializers.SerializerMethodField()
#     class Meta:
#         model = Book
#         fields = ["id", "title", "author", "author_name",
#                   "pages", "price", "is_available", "review_count"]


# ══════════════════════════════════════════════════════════════════
# Throttling — Rate limiting
# ══════════════════════════════════════════════════════════════════

class BurstThrottle(UserRateThrottle):
    """۱۰ درخواست در دقیقه — برای اکشن‌های سنگین."""
    scope = "burst"


class LoginThrottle(AnonRateThrottle):
    """۵ تلاش login در دقیقه — جلوگیری از brute force."""
    scope = "login"


class ExpensiveAPIOperationThrottle(UserRateThrottle):
    """۵ درخواست در دقیقه برای عملیات گران."""
    scope = "expensive"


class BookViewSet(viewsets.ModelViewSet):
    """ViewSet با versioning + throttling."""
    queryset = Book.objects.all()
    serializer_class = BookSerializer
    versioning_class = URLPathVersioning   # یا AcceptHeaderVersioning

    # throttle پیش‌فرض برای همهٔ اکشن‌ها
    throttle_classes = [BurstThrottle]

    # throttle scoped برای اکشن خاص
    @action(detail=False, methods=["post"], throttle_scope="expensive")
    def bulk_import(self, request):
        """عملیات گران — فقط ۵ بار در دقیقه."""
        ...
        return Response({"status": "imported"})


class LoginView(APIView):
    """Login با throttle سخت‌گیرانه."""
    throttle_classes = [LoginThrottle]

    def post(self, request):
        username = request.data.get("username")
        password = request.data.get("password")
        user = authenticate(request, username=username, password=password)
        if user:
            login(request, user)
            return Response({"status": "logged in"})
        return Response({"error": "invalid credentials"}, status=400)


# ══════════════════════════════════════════════════════════════════
# settings.py — REST_FRAMEWORK با versioning و throttle
# ══════════════════════════════════════════════════════════════════
# REST_FRAMEWORK = {
#     "DEFAULT_VERSIONING_CLASS": "rest_framework.versioning.URLPathVersioning",
#     "DEFAULT_VERSION": "v1",
#     "ALLOWED_VERSIONS": ["v1", "v2"],
#     "VERSION_PARAM": "version",        # برای query param versioning
#
#     "DEFAULT_THROTTLE_CLASSES": [
#         "rest_framework.throttling.AnonRateThrottle",
#         "rest_framework.throttling.UserRateThrottle",
#     ],
#     "DEFAULT_THROTTLE_RATES": {
#         "anon": "100/hour",
#         "user": "1000/hour",
#         "burst": "10/minute",
#         "login": "5/minute",
#         "expensive": "5/minute",
#     },
# }

# ══════════════════════════════════════════════════════════════════
# مثال خروجی throttle:
# ══════════════════════════════════════════════════════════════════
# وقتی limit exceeded → 429 Too Many Requests
# {
#   "detail": "Request was throttled. Expected available in 60 seconds."
# }
#
# headerهای پاسخ:
#   X-RateLimit-Limit: 10
#   X-RateLimit-Remaining: 3
#   Retry-After: 60
