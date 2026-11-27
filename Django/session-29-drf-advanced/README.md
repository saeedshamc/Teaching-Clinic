# جلسه ۲۹ — Pagination، Filtering و Throttling در DRF

## درس

### Pagination

```python
# settings.py
REST_FRAMEWORK = {
    "DEFAULT_PAGINATION_CLASS": "rest_framework.pagination.PageNumberPagination",
    "PAGE_SIZE": 20,
}

# یا سفارشی:
class BookPagination(PageNumberPagination):
    page_size = 10
    page_size_query_param = "page_size"   # ?page_size=50
    max_page_size = 100

class BookViewSet(viewsets.ModelViewSet):
    pagination_class = BookPagination
```

خروجی: `{"count": 100, "next": "...", "previous": null, "results": [...]}`

### Filtering با django-filter در DRF

```python
class BookFilter(django_filters.FilterSet):
    class Meta:
        model = Book
        fields = {"author": ["exact"], "is_available": ["exact"], "price": ["gte", "lte"]}

class BookViewSet(viewsets.ModelViewSet):
    filter_backends = [filters.DjangoFilterBackend, filters.SearchFilter, filters.OrderingFilter]
    filterset_class = BookFilter
    search_fields = ["title", "author__name"]
    ordering_fields = ["price", "created_at"]
```

استفاده: `/api/books/?price__gte=100&search=django&ordering=-price`

### Throttling — محدودیت نرخ

```python
# settings.py
REST_FRAMEWORK = {
    "DEFAULT_THROTTLE_CLASSES": ["rest_framework.throttling.AnonRateThrottle"],
    "DEFAULT_THROTTLE_RATES": {"anon": "100/day", "user": "1000/day"},
}

# یا روی ویو:
class BookViewSet(viewsets.ModelViewSet):
    throttle_classes = [throttling.UserRateThrottle]
    throttle_scope = "user"
```

## کد نمونه

بالا کامنت‌گذاری شده — در settings/views پیاده کنید.

## تمرین

برای `BookViewSet` pagination با `page_size=5` و `search_fields=["title"]` تنظیم کنید.

## جواب تمرین

```python
class BookPagination(PageNumberPagination):
    page_size = 5
    page_size_query_param = "page_size"

class BookViewSet(viewsets.ModelViewSet):
    pagination_class = BookPagination
    filter_backends = [filters.SearchFilter]
    search_fields = ["title", "author__name"]
```
