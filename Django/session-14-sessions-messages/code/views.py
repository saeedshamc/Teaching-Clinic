# views.py — استفاده از session و messages
from django.contrib import messages
from django.http import HttpResponse
from django.shortcuts import redirect


def count_visit(request):
    """شمارندهٔ بازدید با session — هر کاربر عدد خودش را دارد."""
    count = request.session.get("visit_count", 0) + 1
    request.session["visit_count"] = count
    return HttpResponse(f"این بازدید شماره {count} شماست")


def set_theme(request):
    """ذخیرهٔ تم انتخابی کاربر در سشن."""
    theme = request.GET.get("theme", "light")
    if theme in ("light", "dark"):
        request.session["theme"] = theme
    return redirect("library:home")


def get_theme(request):
    """خواندن تم از سشن و استفاده در قالب."""
    theme = request.session.get("theme", "light")
    return render(request, "library/home.html", {"theme": theme})


def flash_example(request):
    """نمایش قدرت messages — پیام در رندر بعدی نمایش داده می‌شود."""
    if request.method == "POST":
        # موفقیت → پیام سبز، خطا → قرمز در قالب بعدی
        messages.success(request, "عملیات با موفقیت انجام شد")
        return redirect("library:home")
    return HttpResponse("POST کنید تا پیام ثبت شود")


from django.shortcuts import render   # noqa: E402  (برای get_theme)
