# views.py — DRF APIView (جلسه ۲۶)
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status, permissions
from .models import Book
from .serializers import BookSerializer


class BookListAPI(APIView):
    """
    GET  /api/books/    → لیست همهٔ کتاب‌ها
    POST /api/books/    → ساخت کتاب جدید
    """

    # فقط کاربران لاگین‌شده می‌توانند POST کنند
    permission_classes = [permissions.IsAuthenticatedOrReadOnly]

    def get(self, request):
        # لیست ساده — بدون pagination (برای یادگیری)
        books = Book.objects.select_related("author").all()
        serializer = BookSerializer(books, many=True)   # many=True برای لیست
        return Response(serializer.data)

    def post(self, request):
        serializer = BookSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()          # create می‌کند
            return Response(
                serializer.data,
                status=status.HTTP_201_CREATED,
            )
        # ۴۰۰ با جزئیات خطا
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class BookDetailAPI(APIView):
    """
    GET    /api/books/{id}/  → جزئیات یک کتاب
    PUT    /api/books/{id}/  → ویرایش کامل
    PATCH  /api/books/{id}/  → ویرایش جزئی
    DELETE /api/books/{id}/  → حذف
    """

    permission_classes = [permissions.IsAuthenticatedOrReadOnly]

    def get_object(self, pk):
        try:
            return Book.objects.select_related("author").get(pk=pk)
        except Book.DoesNotExist:
            return None

    def get(self, request, pk):
        book = self.get_object(pk)
        if not book:
            return Response({"error": "not found"}, status=404)
        return Response(BookSerializer(book).data)

    def put(self, request, pk):
        book = self.get_object(pk)
        if not book:
            return Response({"error": "not found"}, status=404)
        serializer = BookSerializer(book, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=400)

    def patch(self, request, pk):
        book = self.get_object(pk)
        if not book:
            return Response({"error": "not found"}, status=404)
        serializer = BookSerializer(book, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=400)

    def delete(self, request, pk):
        book = self.get_object(pk)
        if not book:
            return Response({"error": "not found"}, status=404)
        book.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# ══════════════════════════════════════════════════════════════════
# در urls.py:
# ══════════════════════════════════════════════════════════════════
# from .views import BookListAPI, BookDetailAPI
# path("api/books/", BookListAPI.as_view()),
# path("api/books/<int:pk>/", BookDetailAPI.as_view()),
