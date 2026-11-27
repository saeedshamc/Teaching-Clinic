# urls.py — فایل urls اپ library
# این فایل در config/urls.py با include("library.urls") وصل می‌شود.
from django.urls import path

from . import views

app_name = "library"   # namespace برای {% url 'library:book_detail' %}

urlpatterns = [
    path("books/", views.book_list, name="book_list"),
    path("books/<int:book_id>/", views.book_detail, name="book_detail"),
    path("api/books/", views.api_books, name="api_books"),
    path("api/books/<int:book_id>/", views.api_book, name="api_book"),
]
