# urls.py — config/urls.py با سرو media در حالت DEBUG
# نکته: در production فایل‌های media باید توسط nginx/web server سرو شوند،
# نه خود جنگو — این ترفند فقط برای توسعه است.
from django.conf import settings
from django.conf.urls.static import static
from django.contrib import admin
from django.urls import include, path

urlpatterns = [
    path("admin/", admin.site.urls),
    path("", include("library.urls")),
]

# فقط وقتی DEBUG=True فایل‌های media سرو می‌شوند
if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
