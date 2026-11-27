# queries.py — مثال‌های ORM با کامنت فارسی
# این فایل داخل `python manage.py shell` یا view اجرا می‌شود.
from django.db.models import Avg, Count, Sum

from library.models import Author, Book


def examples():
    # ---------- ساخت ----------
    book = Book(title="راهنمای جنگو", pages=450)
    book.save()                                   # INSERT
    Book.objects.create(title="پایتون مقدماتی", pages=200)

    # ---------- خواندن ----------
    Book.objects.all()                            # SELECT * همه
    Book.objects.get(id=1)                        # دقیقاً یک رکورد
    Book.objects.filter(pages__gt=200)            # WHERE pages > 200
    Book.objects.filter(title__icontains="جنگو")  # جستجوی بدون حساسیت به حروف
    Book.objects.exclude(pages__lt=100)           # NOT pages < 100
    Book.objects.order_by("-created_at")          # جدیدترین اول
    Book.objects.count()                          # SELECT COUNT(*)
    Book.objects.first()                          # اولین رکورد

    # ---------- join روی رابطه ----------
    Book.objects.filter(author__name="لوییس")

    # ---------- slicing (محدود کردن) ----------
    Book.objects.all()[:10]                       # ۱۰ تای اول — مثل LIMIT

    # ---------- aggregation ----------
    Book.objects.aggregate(
        total_pages=Sum("pages"),
        avg_pages=Avg("pages"),
        total=Count("id"),
    )

    # ---------- annotate: افزودن فیلد محاسباتی به هر سطر ----------
    Author.objects.annotate(num_books=Count("books")).order_by("-num_books")

    # ---------- به‌روزرسانی ----------
    Book.objects.filter(pages__lt=100).update(is_available=False)

    # ---------- حذف ----------
    Book.objects.filter(title="قدیمی").delete()
