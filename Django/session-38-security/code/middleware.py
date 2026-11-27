# middleware.py — هدرهای امنیتی (جلسه ۳۸)
from django.utils.deprecation import MiddlewareMixin


class SecurityHeadersMiddleware:
    """
    افزودن هدرهای امنیتی به همهٔ پاسخ‌ها.
    سازگار با Django 2.0+ (new-style middleware).
    """

    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        response = self.get_response(request)

        # جلوگیری از MIME sniffing — مرورگر نوع فایل را حدس نزند
        response["X-Content-Type-Options"] = "nosniff"

        # سیاست Referrer — چه اطلاعاتی از URL به سایت‌های دیگر می‌رود
        response["Referrer-Policy"] = "strict-origin-when-cross-origin"

        # Permissions Policy — محدود کردن دسترسی به featureهای مرورگر
        response["Permissions-Policy"] = "camera=(), microphone=(), geolocation=()"

        # Cross-Origin Opener Policy
        response["Cross-Origin-Opener-Policy"] = "same-origin"

        return response


# ══════════════════════════════════════════════════════════════════
# settings.py — تنظیمات امنیتی production
# ══════════════════════════════════════════════════════════════════
# DEBUG = False                          # هرگز در production True نباشد
# ALLOWED_HOSTS = ["example.com", "www.example.com"]
#
# # SECRET_KEY از env — هرگز در کد
# import os
# SECRET_KEY = os.environ.get("DJANGO_SECRET_KEY", "dev-only-change-me")
#
# # Security middleware
# SECURE_BROWSER_XSS_FILTER = True
# SECURE_CONTENT_TYPE_NOSNIFF = True
# X_FRAME_OPTIONS = "DENY"               # clickjacking
# SECURE_SSL_REDIRECT = True             # HTTP → HTTPS
# SESSION_COOKIE_SECURE = True           # cookie فقط روی HTTPS
# CSRF_COOKIE_SECURE = True
# SECURE_HSTS_SECONDS = 31536000         # ۱ سال — HSTS
# SECURE_HSTS_INCLUDE_SUBDOMAINS = True
# SECURE_HSTS_PRELOAD = True
# SECURE_PROXY_SSL_HEADER = ("HTTP_X_FORWARDED_PROTO", "https")
#
# MIDDLEWARE = [
#     "django.middleware.security.SecurityMiddleware",   # هدرهای پیش‌فرض
#     "library.middleware.SecurityHeadersMiddleware",     # هدرهای سفارشی
#     # ...
# ]

# ══════════════════════════════════════════════════════════════════
# نکات امنیتی:
# ══════════════════════════════════════════════════════════════════
# - CSRF: {% csrf_token %} در همهٔ فرم‌های POST + CsrfViewMiddleware
# - XSS: autoescape قالب + هرگز |safe روی ورودی کاربر
# - SQL Injection: ORM از پارامترسازی استفاده می‌کند — هرگز raw SQL با string concat
# - Clickjacking: X_FRAME_OPTIONS = "DENY"
# - Session fixation: rotate session key بعد از login (Django خودکار می‌کند)
# - Password: هرگز plain ذخیره نکنید — User.objects.create_user رمز را hash می‌کند
# - File upload: validate نوع/حجم در form یا view
# - SECRET_KEY: در env variable یا secrets manager — هرگز در git
