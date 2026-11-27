# project_urls.py — config/urls.py با include برای اپ‌ها
# include() اجازه می‌دهد هر اپ urls.py خودش را داشته باشد.
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path("admin/", admin.site.urls),
    path("", include("library.urls")),   # همه مسیرهای library زیر ریشه
]
