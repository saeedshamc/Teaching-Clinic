# urls.py — اتصال ویوهای auth
from django.contrib.auth import views as auth_views
from django.urls import path

from . import views

app_name = "library"

urlpatterns = [
    path("login/", views.login_view, name="login"),
    path("logout/", views.logout_view, name="logout"),
    path("profile/", views.profile, name="profile"),
    # یا از ویوهای آمادهٔ جنگو استفاده کنید:
    # path("accounts/login/", auth_views.LoginView.as_view(template_name="registration/login.html"), name="login"),
    # path("accounts/logout/", auth_views.LogoutView.as_view(), name="logout"),
]
