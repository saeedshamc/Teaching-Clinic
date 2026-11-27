# queries.py — الگوهای بهینه‌سازی ORM
from django.core.paginator import Paginator

from .models import Book


def optimized_list():
    """الگوی بهینه برای لیست کتاب‌ها — بدون N+1."""
    # select_related برای FK (author) → JOIN در یک کوئری
    # prefetch_related برای M2M (tags) → کوئری دوم ولی فقط یک‌بار
    return (Book.objects
            .select_related("author")
            .prefetch_related("tags")
            .order_by("title"))


def lean_queries():
    """خواندن فقط فیلدهای لازم — ترافیک و حافظه کمتر."""
    # فقط id و title — نه همهٔ ستون‌ها
    Book.objects.only("id", "title")
    # همه به‌جز description
    Book.objects.defer("description")
    # خروجی دیکشنری — سبک‌تر از مدل کامل
    Book.objects.values("id", "title", "price")
    # خروجی tuple — برای JSON/API
    Book.objects.values_list("id", "title")


def paginate(books, request):
    """صفحه‌بندی دستی — در ویو استفاده کنید."""
    paginator = Paginator(books, 12)          # ۱۲ تا در هر صفحه
    page_number = request.GET.get("page", 1)
    return paginator.get_page(page_number)    # page object با .object_list و .has_next


def bulk_ops():
    """عملیات گروهی — به‌جای save() در حلقه."""
    # bulk_create: یک INSERT بزرگ به‌جای N INSERT
    books = [Book(title=f"کتاب {i}", pages=100) for i in range(100)]
    Book.objects.bulk_create(books)

    # update: یک UPDATE برای همهٔ رکوردهای منطبق
    Book.objects.filter(is_available=True).update(stock=0)
