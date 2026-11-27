# جلسه ۴۷ — Django Admin پیشرفته

## درس

### InlineModelAdmin — ویرایش رابطه در همان صفحه

```python
class TagInline(admin.TabularInline):   # یا StackedInline
    model = Book.tags.through
    extra = 1

@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    inlines = [TagInline]
```

### اکشن‌های سفارشی + confirmation

```python
@admin.action(description="خروجی CSV", permissions=["export"])
def export_csv(self, request, queryset):
    response = HttpResponse(content_type="text/csv")
    response["Content-Disposition"] = 'attachment; filename="books.csv"'
    writer = csv.writer(response)
    writer.writerow(["title", "pages", "price"])
    for book in queryset:
        writer.writerow([book.title, book.pages, book.price])
    return response
```

### Custom admin view — صفحهٔ اختصاصی

```python
from django.urls import path
from django.shortcuts import render

@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    def get_urls(self):
        urls = super().get_urls()
        custom = [path("stats/", self.admin_site.admin_view(self.stats_view))]
        return custom + urls

    def stats_view(self, request):
        context = {
            **self.admin_site.each_context(request),
            "total": Book.objects.count(),
            "available": Book.objects.filter(is_available=True).count(),
        }
        return render(request, "admin/library/stats.html", context)
```

### list_display با متدهای سفارشی

```python
@admin.display(description="تعداد صفحات", ordering="pages")
def pages_display(self, obj):
    return f"{obj.pages:,} صفحه"
list_display = ("title", "pages_display", "author")
```

## تمرین

یک `StackedInline` برای `Profile` روی `User` ادمین بسازید و یک اکشن CSV برای خروجی کتاب‌ها اضافه کنید.

## جواب تمرین

```python
class ProfileInline(admin.StackedInline):
    model = Profile
    can_delete = False
    verbose_name_plural = "پروفایل"
    fk_name = "user"

# اکشن export_csv همان‌طور که بالا آمد
```
