# models.py — بهینه‌سازی دیتابیس (جلسه ۴۳)
from django.db import models


class Book(models.Model):
    title = models.CharField(max_length=200, db_index=True)   # index روی title
    author = models.ForeignKey(
        "Author", on_delete=models.CASCADE, related_name="books",
        db_index=True,   # FK خودش index دارد ولی صریح بنویسید
    )
    pages = models.IntegerField(default=0, db_index=True)
    price = models.DecimalField(max_digits=10, decimal_places=0, default=0)
    is_available = models.BooleanField(default=True, db_index=True)
    created_at = models.DateTimeField(auto_now_add=True, db_index=True)
    description = models.TextField(blank=True)

    class Meta:
        ordering = ["-created_at"]
        # composite index — برای کوئری‌های ترکیبی
        indexes = [
            # author + is_available — برای "کتاب‌های موجود هر نویسنده"
            models.Index(fields=["author", "is_available"]),
            # created_at + price — برای "کتاب‌های جدید گران"
            models.Index(fields=["-created_at", "price"]),
        ]
        # unique_together = ("author", "title")   # اگر لازم باشد

    def __str__(self):
        return self.title


# ══════════════════════════════════════════════════════════════════
# کوئری‌های بهینه — در views.py یا queries.py
# ══════════════════════════════════════════════════════════════════
#
# # N+1 problem:
# books = Book.objects.all()
# for b in books:
#     print(b.author.name)   # N کوئری جدا برای هر author!
#
# # راه‌حل — select_related (FK, OneToOne → JOIN)
# books = Book.objects.select_related("author").all()
# for b in books:
#     print(b.author.name)   # فقط ۱ کوئری
#
# # prefetch_related (M2M, reverse FK → کوئری جدا ولی batch)
# books = Book.objects.prefetch_related("tags", "reviews").all()
#
# # فقط فیلدهای لازم — SELECT * نزنید
# Book.objects.only("id", "title")           # فقط id و title
# Book.objects.defer("description")          # همه به‌جز description
# Book.objects.values("id", "title")         # dict به‌جای model
# Book.objects.values_list("id", "title")    # tuple
#
# # bulk operations — به‌جای حلقهٔ save()
# Book.objects.bulk_create([
#     Book(title=f"Book {i}", pages=100) for i in range(100)
# ])
#
# Book.objects.filter(is_available=False).update(stock=0)   # ۱ UPDATE
# Book.objects.filter(is_available=False).delete()           # ۱ DELETE
#
# # update_fields — فقط فیلدهای تغییرکرده را UPDATE کن
# book.title = "New"
# book.save(update_fields=["title"])
#
# # explain() — تحلیل کوئری
# print(Book.objects.filter(pages__gt=100).explain())
# # Seq Scan vs Index Scan را نشان می‌دهد
#
# # raw SQL — وقتی ORM کافی نیست
# from django.db import connection
# with connection.cursor() as cursor:
#     cursor.execute("SELECT title, price FROM library_book WHERE pages > %s", [100])
#     rows = cursor.fetchall()


# ══════════════════════════════════════════════════════════════════
# Migration برای index
# ══════════════════════════════════════════════════════════════════
# python manage.py makemigrations   # indexها را تشخیص می‌دهد
# python manage.py migrate
#
# برای index روی دیتای موجود بزرگ:
#   CREATE INDEX CONCURRENTLY در PostgreSQL — بدون lock
#   django.contrib.postgres.operations.AddIndexConcurrently
