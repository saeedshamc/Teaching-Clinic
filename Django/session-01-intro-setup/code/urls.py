# urls.py — مسیریابی URL در سطح پروژه (config/urls.py)
# urlpatterns لیستی از path() است؛ هر path یک الگو + ویو می‌خواهد.
from django.contrib import admin
from django.urls import path
from library import views   # اپ ما به نام library

urlpatterns = [
    path("admin/", admin.site.urls),          # پنل ادمین پیش‌فرض
    path("", views.home, name="home"),        # مسیر ریشه /
    path("hello/", views.hello, name="hello"), # مسیر /hello/
]
