# views.py — ساده‌ترین ویوهای Django
# هر ویو یک تابع است که request می‌گیرد و HttpResponse برمی‌گرداند.
from django.http import HttpResponse


def home(request):
    """ویوی صفحه اصلی — request شیئی است که متد و path دارد."""
    return HttpResponse("<h1>به دوره Django خوش آمدید</h1>")


def hello(request):
    """ویو با پارامتر GET — مثلاً /hello/?name=Ali"""
    name = request.GET.get("name", "کاربر")
    return HttpResponse(f"سلام {name}!")
