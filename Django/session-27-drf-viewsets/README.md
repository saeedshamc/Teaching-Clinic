# جلسه ۲۷ — DRF ViewSet و Router

## درس

ViewSet جای CRUD کامل را یک‌جا می‌دهد؛ Router URLها را خودکار می‌سازد.

```python
from rest_framework import viewsets
from .serializers import BookSerializer
from .models import Book

class BookViewSet(viewsets.ModelViewSet):
    """ModelViewSet = List+Create+Retrieve+Update+Destroy."""
    queryset = Book.objects.select_related("author").all()
    serializer_class = BookSerializer
    # filter_backends, ordering, pagination اینجا تنظیم می‌شود
```

### Router — URL خودکار

```python
# library/api_urls.py
from rest_framework.routers import DefaultRouter
from .views import BookViewSet

router = DefaultRouter()
router.register("books", BookViewSet, basename="book")

urlpatterns = router.urls
# ساخته می‌شود: /api/books/ (GET list, POST create)
#              /api/books/{pk}/ (GET, PUT, PATCH, DELETE)
```

### در config/urls.py

```python
path("api/", include("library.api_urls")),
```

### اکشن سفارشی روی ViewSet

```python
from rest_framework.decorators import action

class BookViewSet(viewsets.ModelViewSet):
    @action(detail=True, methods=["post"])
    def publish(self, request, pk=None):
        book = self.get_object()
        book.is_available = True
        book.save()
        return Response({"status": "published"})
# → POST /api/books/{pk}/publish/
```

## کد نمونه

بالا کامنت‌گذاری شده — در `views.py`/`api_urls.py` پیاده کنید.

## تمرین

یک `@action` به نام `archive` اضافه کنید که `is_available=False` کند و فقط برای detail کار کند.

## جواب تمرین

```python
@action(detail=True, methods=["post"])
def archive(self, request, pk=None):
    book = self.get_object()
    book.is_available = False
    book.save()
    return Response({"status": "archived"})
```
