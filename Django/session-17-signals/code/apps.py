# apps.py — لود سیگنال‌ها هنگام راه‌اندازی اپ
from django.apps import AppConfig


class LibraryConfig(AppConfig):
    name = "library"

    def ready(self):
        # سیگنال‌ها باید قبل از استفاده register شوند — اینجا امن‌ترین جاست
        import library.signals  # noqa: F401
