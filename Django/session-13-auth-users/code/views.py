# views.py — ویوهای احراز هویت
from django.contrib import messages
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.shortcuts import redirect, render


def login_view(request):
    """صفحهٔ ورود — POST: authenticate → login → redirect."""
    if request.method == "POST":
        username = request.POST.get("username", "")
        password = request.POST.get("password", "")
        # authenticate اعتبارسنجی می‌کند؛ اگر اشتباه باشد None برمی‌گرداند
        user = authenticate(request, username=username, password=password)
        if user is not None:
            login(request, user)   # سشن کاربر را می‌کند
            messages.success(request, f"خوش آمدید {user.username}!")
            # اگر next در GET بود به آن برگرد (مثلاً از login_required)
            next_url = request.GET.get("next", "library:home")
            return redirect(next_url)
        # ورود ناموفق — پیام خطا به قالب
        return render(request, "registration/login.html", {
            "error": "نام کاربری یا رمز عبور اشتباه است",
        })
    return render(request, "registration/login.html")


def logout_view(request):
    """خروج — logout سشن را پاک می‌کند."""
    logout(request)
    messages.info(request, "خروج موفقیت‌آمیز بود")
    return redirect("library:home")


@login_required   # بدون لاگین → redirect به LOGIN_URL با ?next=
def profile(request):
    """مثال ویوی محافظت‌شده — request.user در دسترس است."""
    return render(request, "library/profile.html", {"user": request.user})
