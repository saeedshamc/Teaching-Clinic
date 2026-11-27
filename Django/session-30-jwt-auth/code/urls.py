# urls.py — JWT endpoints (جلسه ۳۰)
# نکته: این فایل را در api_urls.py یا urls.py پروژه کپی کنید.
# BookViewSet و MeView باید در views.py پروژه شما تعریف شده باشند
# (نمونهٔ آن‌ها در کامنت‌های پایین همین فایل آمده است).
from django.urls import path, include
from rest_framework_simplejwt.views import (
    TokenObtainPairView,      # POST /api/token/ → access + refresh
    TokenRefreshView,          # POST /api/token/refresh/ → access جدید
    TokenVerifyView,           # POST /api/token/verify/ → بررسی معتبر بودن
    TokenBlacklistView,        # POST /api/token/blacklist/ → ابطال refresh
)
from .views import BookViewSet, MeView
from rest_framework.routers import DefaultRouter


router = DefaultRouter()
router.register("books", BookViewSet, basename="book")

urlpatterns = [
    # ─── JWT endpoints ───────────────────────────────────────────
    path("api/token/", TokenObtainPairView.as_view(), name="token_obtain_pair"),
    path("api/token/refresh/", TokenRefreshView.as_view(), name="token_refresh"),
    path("api/token/verify/", TokenVerifyView.as_view(), name="token_verify"),
    path("api/token/blacklist/", TokenBlacklistView.as_view(), name="token_blacklist"),

    # ─── اطلاعات کاربر جاری ──────────────────────────────────────
    path("api/me/", MeView.as_view(), name="me"),

    # ─── router ──────────────────────────────────────────────────
    path("api/", include(router.urls)),
]

# ══════════════════════════════════════════════════════════════════
# views.py — ویوهای JWT
# ══════════════════════════════════════════════════════════════════
# from rest_framework.views import APIView
# from rest_framework.response import Response
# from rest_framework.permissions import IsAuthenticated
# from rest_framework_simplejwt.authentication import JWTAuthentication
#
# class MeView(APIView):
#     """اطلاعات کاربر جاری — فقط با JWT معتبر."""
#     authentication_classes = [JWTAuthentication]
#     permission_classes = [IsAuthenticated]
#
#     def get(self, request):
#         u = request.user
#         return Response({
#             "id": u.id,
#             "username": u.username,
#             "email": u.email,
#             "is_staff": u.is_staff,
#         })
#
#     def patch(self, request):
#         # ویرایش پروفایل خود
#         u = request.user
#         if "email" in request.data:
#             u.email = request.data["email"]
#             u.save()
#         return Response({"updated": True})

# ══════════════════════════════════════════════════════════════════
# settings.py — تنظیمات SimpleJWT
# ══════════════════════════════════════════════════════════════════
# INSTALLED_APPS += ["rest_framework_simplejwt", "rest_framework_simplejwt.token_blacklist"]
#
# REST_FRAMEWORK = {
#     "DEFAULT_AUTHENTICATION_CLASSES": [
#         "rest_framework_simplejwt.authentication.JWTAuthentication",
#     ],
#     "DEFAULT_PERMISSION_CLASSES": [
#         "rest_framework.permissions.IsAuthenticated",
#     ],
# }
#
# SIMPLE_JWT = {
#     "ACCESS_TOKEN_LIFETIME": timedelta(minutes=15),   # access کوتاه‌مدت
#     "REFRESH_TOKEN_LIFETIME": timedelta(days=7),       # refresh بلندمدت
#     "ROTATE_REFRESH_TOKENS": True,                      # هر refresh → refresh جدید
#     "BLACKLIST_AFTER_ROTATION": True,                   # قدیمی ابطال می‌شود
# }

# ══════════════════════════════════════════════════════════════════
# جریان استفاده:
# ══════════════════════════════════════════════════════════════════
# ۱. POST /api/token/  {"username": "...", "password": "..."}
#    → {"access": "eyJ...", "refresh": "eyJ..."}
#
# ۲. GET /api/books/  +  Authorization: Bearer <access>
#
# ۳. وقتی access منقضی شد → POST /api/token/refresh/  {"refresh": "eyJ..."}
#    → {"access": "eyJ...new"}
