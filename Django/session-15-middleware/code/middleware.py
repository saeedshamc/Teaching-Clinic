# middleware.py — مثال‌های middleware سفارشی
import time


class TimingMiddleware:
    """زمان پاسخ را در هدر پاسخ می‌گذارد.

    __init__ یک‌بار هنگام راه‌اندازی اجرا می‌شود و get_response
    «بقیهٔ زنجیره» (میدل‌ورهای بعدی + ویو) را نگه می‌دارد.
    """

    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        start = time.perf_counter()
        response = self.get_response(request)   # ← اینجا ویو اجرا می‌شود
        elapsed = (time.perf_counter() - start) * 1000
        response["X-Response-Time"] = f"{elapsed:.1f}ms"
        return response


class SimpleMiddleware:
    """مثال ساده: افزودن یک متغیر به request قبل از رسیدن به ویو."""

    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        # هر چیزی اینجا قبل از ویو در دسترس است
        request.start_time = time.time()
        return self.get_response(request)


class MaintenanceMiddleware:
    """حالت تعمیر — اگر settings.MAINTENANCE_MODE فعال باشد 503 برمی‌گرداند."""

    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        from django.conf import settings
        from django.http import HttpResponse
        if getattr(settings, "MAINTENANCE_MODE", False):
            return HttpResponse("سایت در حال تعمیر است", status=503)
        return self.get_response(request)
