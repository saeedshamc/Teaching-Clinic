# جلسه ۷ — پنل مدیریت (Django Admin)

## درس

جنگو از روی مدل‌هایتان **پنل ادمین** می‌سازد — بدون کدنویسی اضافه. کافی است مدل را در `admin.py` ثبت کنید:

```python
from django.contrib import admin
from .models import Book, Author

admin.site.register(Book)
admin.site.register(Author)
```

سپس `createsuperuser` بسازید و وارد `/admin/` شوید.

### سفارشی‌سازی با ModelAdmin

```python
@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    list_display = ("title", "author", "pages", "is_available")  # ستون‌های لیست
    list_filter = ("is_available", "author")                     # فیلتر کناری
    search_fields = ("title", "author__name")                    # جستجو
    list_editable = ("is_available",)                            # ویرایش inline
    readonly_fields = ("created_at",)                            # فیلد فقط‌خواندنی
    ordering = ("-created_at",)
    date_hierarchy = "created_at"                                # فیلتر بر اساس تاریخ
    fieldsets = (                                                  # گروه‌بندی فرم
        ("اطلاعات کتاب", {"fields": ("title", "author", "pages")}),
        ("وضعیت", {"fields": ("is_available", "price")}),
    )
```

### اکشن‌های سفارشی

```python
@admin.action(description="علامت‌گذاری به‌عنوان ناموجود")
def mark_unavailable(modeladmin, request, queryset):
    queryset.update(is_available=False)

@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    actions = [mark_unavailable]
```

## کد نمونه

`code/admin.py` را ببینید.

## تمرین

برای مدل `Author` یک ModelAdmin بسازید که `name` و تعداد کتاب‌ها (`book_count`) را در لیست نشان دهد.

## جواب تمرین

```python
from django.contrib import admin
from django.db.models import Count
from .models import Author

@admin.register(Author)
class AuthorAdmin(admin.ModelAdmin):
    list_display = ("name", "birth_year", "book_count")

    def get_queryset(self, request):
        return super().get_queryset(request).annotate(
            book_count=Count("books")
        )
```

## 📖 شرح فایل `code/admin.py`

- **`BookAdmin`** (با `@admin.register(Book)`):
  - `list_display` — ستون‌های جدول لیست؛ هرکدام نام فیلد یا متد مدل می‌تواند باشد.
  - `list_filter` — فیلتر کناری بر اساس فیلدهای انتخابی/Boolean/تاریخ.
  - `search_fields` — جستجو؛ `author__name` یعنی در فیلد `name` رابطهٔ `author`.
  - `list_editable` — فیلدهایی که مستقیم از لیست قابل ویرایش‌اند (باید در `list_display` باشند).
  - `readonly_fields` — نمایش بدون ویرایش (برای فیلدهای `auto_now`).
  - `date_hierarchy` — فیلتر سلسله‌مراتبی سال/ماه/روز در سایدبار.
  - `fieldsets` — گروه‌بندی فرم ویرایش؛ `"classes": ("collapse",)` بخش را جمع‌شونده می‌کند.
  - `mark_unavailable` — اکشن سفارشی با `@admin.action`؛ روی queryset انتخاب‌شده‌ها `update` می‌زند و `message_user` بازخورد می‌دهد.
- **`AuthorAdmin`**: `get_queryset` با `annotate(Count("books"))` ستون `book_count` را در لیست پر می‌کند — بدون N+1 چون annotate در همان کوئری است.
- **`admin.site.register(Tag)`** — ثبت ساده بدون سفارشی‌سازی.
