# جلسه ۵۰ — پروژهٔ نهایی پیشرفته: «کتابخانه دیجیتال»

## درس

پروژهٔ جامع که همهٔ مباحث را ترکیب می‌کند: مدل‌های complex، REST API با JWT، realtime، cache، celery و deploy-ready.

### معماری هدف

```
library/
├── models.py        # Author, Book, Tag, Profile, Review, ReadingList
├── serializers.py   # BookSerializer, ReviewSerializer, UserSerializer
├── views.py         # APIViewSetها + سایت ویوها
├── consumers.py     # WebSocket برای notification/book_available
├── tasks.py         # celery: daily_report, send_email
├── signals.py       # post_save Book → cache invalidate + notify
├── admin.py         # ModelAdmin کامل
├── middleware.py    # request timing
└── templates/library/   # سایت + API docs
```

### فیچرهای الزامی

1. **API کامل**: `/api/books/` با CRUD + filter + search + pagination + JWT auth
2. **Review system**: هر کاربر می‌تواند برای کتاب review بنویسد (rating 1-5)
3. **Realtime**: وقتی کتاب «موجود» شد، به کاربران interested notification برود (Channels)
4. **Cache**: لیست پرفروش‌ها ۱ ساعت کش + invalidate با signal
5. **Celery**: تسک روزانه گزارش آمار به ایمیل ادمین
6. **Admin**: اکشن CSV export + custom stats page
7. **Tests**: حداقل ۱۰ تست برای model + API
8. **Docker**: docker-compose با web+db+redis+celery worker

### تعاریف مدل‌های اضافه

```python
class Review(models.Model):
    book = models.ForeignKey(Book, on_delete=models.CASCADE, related_name="reviews")
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    rating = models.IntegerField(validators=[MinValueValidator(1), MaxValueValidator(5)])
    comment = models.TextField(blank=True)
    created_at = models.DateTimeField(auto_now_add=True)
    class Meta:
        unique_together = ("book", "user")   # هر کاربر یک review برای هر کتاب

class ReadingList(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    book = models.ForeignKey(Book, on_delete=models.CASCADE)
    added_at = models.DateTimeField(auto_now_add=True)
    class Meta:
        unique_together = ("user", "book")
```

## کد نمونه

`code/models_extra.py` را ببینید.

## تمرین نهایی (پروژه)

همهٔ ۸ فیچر بالا را در یک اپ `library` پیاده کنید. برای هر فیچر یک README جدا بنویسید که مشکل، راه‌حل و کد را توضیح دهد. در پایان `docker-compose up` را تست کنید.

## جواب تمرین

پروژه را مرحله‌به‌مرحله از session-01 تا ۴۹ بازسازی کنید؛ هر فیچر به جلسهٔ متناظرش برمی‌گردد:
- Modelها → session 03-05
- API → session 26-30
- Realtime → session 34/46
- Cache → session 31/48
- Celery → session 32-33
- Admin → session 07/47
- Tests → session 18/44
- Docker → session 40

## 📖 شرح فایل‌های `code/`

**`models_extra.py`** — مدل‌های اضافی capstone:
- `Review` — نظر کاربر برای کتاب؛ `rating` با `MinValueValidator(1)`/`MaxValueValidator(5)`؛ `unique_together=("book","user")` یعنی هر کاربر یک نظر برای هر کتاب.
- `ReadingList` — لیست خواندن کاربر؛ `unique_together=("user","book")` برای جلوگیری از تکرار.

**`serializers.py`** — Serializerهای کامل پروژه:
- `UserSerializer` — بدون password؛ `read_only_fields` برای id/username.
- `ReviewSerializer` — `user`/`book_title` read_only از relation؛ `validate_rating` بازهٔ ۱–۵.
- `ReadingListSerializer` — `book_title` از `source`.
- `BookSerializer` — کامل‌ترین serializer: `author_name`، `tag_names` (Slug)، `tags` (PK برای نوشتن)، `review_count`/`avg_rating`/`in_reading_lists` با `SerializerMethodField`، و `create`/`update` سفارشی برای M2M tags.
