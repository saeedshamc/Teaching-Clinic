# admin.py — Django Admin پیشرفته (جلسه ۴۷)
import csv
from django.contrib import admin
from django.db.models import Q
from django.http import HttpResponse
from django.urls import path
from django.shortcuts import render
from django.utils.html import format_html
from .models import Book, Author, Tag, Review, Profile


# ══════════════════════════════════════════════════════════════════
# Inline — ویرایش رابطه در همان صفحهٔ والد
# ══════════════════════════════════════════════════════════════════

class TagInline(admin.TabularInline):
    """Tagهای کتاب به‌صورت جدول کوچک در صفحهٔ Book."""
    model = Book.tags.through
    extra = 1
    verbose_name = "تگ"
    verbose_name_plural = "تگ‌ها"


class ReviewInline(admin.TabularInline):
    """نظرات کتاب در همان صفحه."""
    model = Review
    extra = 0
    readonly_fields = ["user", "created_at"]
    fields = ["user", "rating", "comment", "created_at"]
    show_change_link = True   # لینک به صفحهٔ ویرایش review


class ProfileInline(admin.StackedInline):
    """Profile کاربر — فرم عمودی."""
    model = Profile
    can_delete = False
    verbose_name_plural = "پروفایل"
    fk_name = "user"
    fields = ["phone", "avatar", "bio"]
    readonly_fields = ["created_at"]


# ══════════════════════════════════════════════════════════════════
# ModelAdmin برای Book
# ══════════════════════════════════════════════════════════════════

@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    # لیست نمایش
    list_display = (
        "title",
        "author_link",        # لینک به author
        "pages_display",      # متد سفارشی
        "price_display",      # متد سفارشی
        "is_available_badge", # badge رنگی
        "review_count",
        "created_at",
    )
    list_filter = ("is_available", "author", "created_at")
    search_fields = ("title", "author__name", "tags__name")
    ordering = ("-created_at",)
    date_hierarchy = "created_at"          # فیلتر بر اساس تاریخ در سایدبار
    list_per_page = 25
    list_select_related = ("author",)      # جلوگیری از N+1 در admin
    inlines = [TagInline, ReviewInline]
    readonly_fields = ("created_at", "updated_at")
    fieldsets = (
        ("اطلاعات اصلی", {
            "fields": ("title", "author", "tags", "description"),
        }),
        ("جزئیات", {
            "fields": ("pages", "price", "is_available", "cover"),
            "classes": ("collapse",),   # قابل جمع‌شدن
        }),
        ("سیستم", {
            "fields": ("created_at", "updated_at"),
            "classes": ("collapse",),
        }),
    )
    filter_horizontal = ("tags",)          # برای M2M — UI بهتر

    # ─── متدهای سفارشی list_display ────────────────────────────────

    @admin.display(description="نویسنده", ordering="author__name")
    def author_link(self, obj):
        """لینک به صفحهٔ author."""
        url = f"/admin/library/author/{obj.author.id}/change/"
        return format_html('<a href="{}">{}</a>', url, obj.author.name)

    @admin.display(description="صفحات", ordering="pages")
    def pages_display(self, obj):
        return f"{obj.pages:,}"

    @admin.display(description="قیمت", ordering="price")
    def price_display(self, obj):
        return f"{obj.price:,.0f} تومان"

    @admin.display(description="وضعیت", boolean=False)
    def is_available_badge(self, obj):
        if obj.is_available:
            return format_html('<span style="color:green">✓ موجود</span>')
        return format_html('<span style="color:red">✗ ناموجود</span>')

    @admin.display(description="نظرات")
    def review_count(self, obj):
        return obj.reviews.count()

    # ─── اکشن‌های سفارشی ──────────────────────────────────────────

    actions = ["export_csv", "mark_unavailable", "mark_available"]

    @admin.action(description="خروجی CSV")
    def export_csv(self, request, queryset):
        """دانلود CSV از کتاب‌های انتخاب‌شده."""
        response = HttpResponse(content_type="text/csv; charset=utf-8-sig")
        response["Content-Disposition"] = 'attachment; filename="books.csv"'
        # BOM برای Excel فارسی
        response.write("\ufeff")
        writer = csv.writer(response)
        writer.writerow(["title", "author", "pages", "price", "is_available"])
        for book in queryset.select_related("author"):
            writer.writerow([
                book.title, book.author.name, book.pages,
                book.price, book.is_available,
            ])
        return response

    @admin.action(description="ناموجود کردن انتخاب‌شده‌ها")
    def mark_unavailable(self, request, queryset):
        updated = queryset.update(is_available=False)
        self.message_user(request, f"{updated} کتاب ناموجود شد.")

    @admin.action(description="موجود کردن انتخاب‌شده‌ها")
    def mark_available(self, request, queryset):
        updated = queryset.update(is_available=True)
        self.message_user(request, f"{updated} کتاب موجود شد.")

    # ─── Custom admin view ────────────────────────────────────────

    def get_urls(self):
        """اضافه کردن URL سفارشی به admin."""
        urls = super().get_urls()
        custom_urls = [
            path("stats/", self.admin_site.admin_view(self.stats_view),
                 name="library_book_stats"),
        ]
        return custom_urls + urls

    def stats_view(self, request):
        """صفحهٔ آمار — /admin/library/book/stats/"""
        from django.db.models import Avg, Count, Sum
        stats = Book.objects.aggregate(
            total=Count("id"),
            available=Count("id", filter=Q(is_available=True)),
            avg_price=Avg("price"),
            total_pages=Sum("pages"),
        )
        context = {
            **self.admin_site.each_context(request),
            "title": "آمار کتابخانه",
            "stats": stats,
        }
        return render(request, "admin/library/stats.html", context)


# ══════════════════════════════════════════════════════════════════
# Admin برای مدل‌های دیگر
# ══════════════════════════════════════════════════════════════════

@admin.register(Author)
class AuthorAdmin(admin.ModelAdmin):
    list_display = ("name", "birth_year", "book_count", "created_at")
    search_fields = ("name", "bio")
    list_filter = ("birth_year",)
    readonly_fields = ("created_at",)

    @admin.display(description="تعداد کتاب", ordering="books__count")
    def book_count(self, obj):
        return obj.books.count()


@admin.register(Tag)
class TagAdmin(admin.ModelAdmin):
    list_display = ("name", "book_count")
    search_fields = ("name",)

    @admin.display(description="کتاب‌ها")
    def book_count(self, obj):
        return obj.book_set.count()


@admin.register(Review)
class ReviewAdmin(admin.ModelAdmin):
    list_display = ("book", "user", "rating", "created_at")
    list_filter = ("rating", "created_at")
    search_fields = ("book__title", "user__username", "comment")
    readonly_fields = ("created_at",)
    raw_id_fields = ("book", "user")   # برای FK با دیتای زیاد


@admin.register(Profile)
class ProfileAdmin(admin.ModelAdmin):
    list_display = ("user", "phone", "created_at")
    search_fields = ("user__username", "phone")
    raw_id_fields = ("user",)
