# admin.py — ثبت مدل‌ها و سفارشی‌سازی پنل ادمین
from django.contrib import admin
from django.db.models import Count

from .models import Author, Book, Tag


# روش ساده: admin.site.register(Book)


@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    """کنترل کامل ظاهر و رفتار Book در ادمین."""

    list_display = ("title", "author", "pages", "price", "is_available")
    list_filter = ("is_available", "author", "created_at")
    search_fields = ("title", "author__name")      # جستجو در عنوان و نام نویسنده
    list_editable = ("is_available", "price")      # ویرایش مستقیم از لیست
    readonly_fields = ("created_at", "updated_at") # فیلدهای سیستمی فقط‌خواندنی
    ordering = ("-created_at",)
    date_hierarchy = "created_at"                  # فیلتر سلسله‌مراتبی تاریخ
    list_per_page = 25
    fieldsets = (
        ("اطلاعات کتاب", {"fields": ("title", "author", "pages", "price")}),
        ("دسته‌بندی", {"fields": ("tags",)}),
        ("وضعیت", {"fields": ("is_available",)}),
        ("تاریخ‌ها", {"fields": ("created_at", "updated_at"), "classes": ("collapse",)}),
    )

    # اکشن سفارشی: روی رکوردهای انتخاب‌شده اعمال می‌شود
    @admin.action(description="علامت‌گذاری به‌عنوان ناموجود")
    def mark_unavailable(self, request, queryset):
        updated = queryset.update(is_available=False)
        self.message_user(request, f"{updated} کتاب به‌روزرسانی شد")

    actions = [mark_unavailable]


@admin.register(Author)
class AuthorAdmin(admin.ModelAdmin):
    list_display = ("name", "birth_year", "book_count")
    search_fields = ("name",)

    def get_queryset(self, request):
        # annotate برای نمایش تعداد کتاب هر نویسنده در لیست
        return super().get_queryset(request).annotate(
            book_count=Count("books")
        )

    book_count.short_description = "تعداد کتاب"


admin.site.register(Tag)   # ثبت ساده بدون سفارشی‌سازی
