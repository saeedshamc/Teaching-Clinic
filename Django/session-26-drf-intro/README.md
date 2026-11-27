# جلسه ۲۶ — مقدمه Django REST Framework (DRF)

## درس

DRF برای ساخت **REST API** است — JSON به‌جای HTML.

```bash
pip install djangorestframework
# settings: INSTALLED_APPS += "rest_framework"
```

### Serializer — تبدیل مدل ↔ JSON

```python
from rest_framework import serializers

class BookSerializer(serializers.ModelSerializer):
    """ModelSerializer فیلدها را خودکار از مدل می‌گیرد."""
    class Meta:
        model = Book
        fields = ["id", "title", "author", "pages", "price", "is_available"]
        # read_only_fields = ["created_at"]
```

### APIView — ویوی DRF

```python
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status

class BookListAPI(APIView):
    def get(self, request):
        books = Book.objects.all()
        serializer = BookSerializer(books, many=True)  # many برای لیست
        return Response(serializer.data)

    def post(self, request):
        serializer = BookSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
```

### در urls.py

```python
path("api/books/", views.BookListAPI.as_view(), name="api_book_list"),
```

## کد نمونه

بالا کامنت‌گذاری شده — در `serializers.py`/`views.py` پیاده کنید.

## تمرین

یک `BookSerializer` با فیلدهای `id, title, author_name` (از `author.name`) بسازید — از `source` استفاده کنید.

## جواب تمرین

```python
class BookSerializer(serializers.ModelSerializer):
    author_name = serializers.CharField(source="author.name", read_only=True)
    class Meta:
        model = Book
        fields = ["id", "title", "author", "author_name", "pages"]
```
