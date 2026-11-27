# filters.py — فیلتر جستجوی پیشرفته (جلسه ۲۵)
import django_filters
from django.db.models import Q
from .models import Book, Author


class BookFilter(django_filters.FilterSet):
    """فیلترهای declared برای کنترل دقیق روی lookup."""

    # جستجوی عنوان — case-insensitive contains
    title = django_filters.CharFilter(
        field_name="title",
        lookup_expr="icontains",
        label="عنوان",
    )

    # بازهٔ قیمت
    price_min = django_filters.NumberFilter(
        field_name="price", lookup_expr="gte", label="حداقل قیمت"
    )
    price_max = django_filters.NumberFilter(
        field_name="price", lookup_expr="lte", label="حداکثر قیمت"
    )

    # حداقل صفحات
    min_pages = django_filters.NumberFilter(
        field_name="pages", lookup_expr="gte", label="حداقل صفحات"
    )

    # جستجوی ترکیبی — با method به filter_search وصل می‌شود
    search = django_filters.CharFilter(
        method="filter_search", label="جستجو"
    )

    class Meta:
        model = Book
        fields = {
            "author": ["exact"],          # ?author=1
            "is_available": ["exact"],    # ?is_available=true
            "created_at": ["date"],       # ?created_at__date=2024-01-01
        }

    # متد filter برای منطق سفارشی — مثل جستجوی چندفیلدی
    def filter_search(self, queryset, name, value):
        """جستجوی ترکیبی در title + author + tags."""
        if value:
            return queryset.filter(
                Q(title__icontains=value)
                | Q(author__name__icontains=value)
                | Q(tags__name__icontains=value)
            ).distinct()   # distinct برای M2M الزامی
        return queryset


# ══════════════════════════════════════════════════════════════════
# در views.py — استفاده از فیلتر
# ══════════════════════════════════════════════════════════════════
# def book_list(request):
#     f = BookFilter(request.GET, queryset=Book.objects.select_related("author"))
#     books = f.qs   # QuerySet فیلترشده
#     return render(request, "library/book_list.html", {
#         "books": books,
#         "filter": f,   # برای رندر فرم فیلتر در قالب
#     })
#
# در template:
# <form method="get">
#     {{ filter.form }}
#     <button type="submit">فیلتر</button>
# </form>
# {% for book in books %} ... {% endfor %}


# ══════════════════════════════════════════════════════════════════
# Q objects — منطق تر از فیلتر
# ══════════════════════════════════════════════════════════════════
# Book.objects.filter(
#     Q(title__icontains="django") | Q(author__name__icontains="saeed")
# )
#
# Book.objects.filter(
#     is_available=True
# ).exclude(
#     Q(price__gt=500000) & Q(pages__lt=100)
# )
#
# Book.objects.filter(
#     ~Q(genere="fiction") | Q(created_at__year__gte=2020)
# )
