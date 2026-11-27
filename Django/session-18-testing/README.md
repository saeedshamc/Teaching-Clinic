# جلسه ۱۸ — تست در Django

## درس

Django از `unittest` استفاده می‌کند و کلاس `TestCase` دیتابیس تست جداگانه می‌سازد.

### ساختار تست

```python
# library/tests.py یا library/tests/test_models.py
from django.test import TestCase
from .models import Book, Author

class BookModelTest(TestCase):
    def setUp(self):
        # قبل از هر تست اجرا می‌شود — دادهٔ نمونه بسازید
        self.author = Author.objects.create(name="لوییس")
        self.book = Book.objects.create(
            title="جنگو", author=self.author, pages=300
        )

    def test_book_str(self):
        self.assertEqual(str(self.book), "جنگو")

    def test_book_count(self):
        self.assertEqual(Book.objects.count(), 1)

    def test_filter(self):
        self.assertTrue(Book.objects.filter(pages__gt=200).exists())
```

### تست ویو با Client

```python
from django.test import Client
from django.urls import reverse

class BookViewTest(TestCase):
    def setUp(self):
        self.client = Client()
        self.book = Book.objects.create(title="تست", pages=100)

    def test_list_view(self):
        response = self.client.get(reverse("library:book_list"))
        self.assertEqual(response.status_code, 200)
        self.assertContains(response, "تست")   # متن در HTML هست

    def test_detail_view(self):
        url = reverse("library:book_detail", args=[self.book.id])
        response = self.client.get(url)
        self.assertEqual(response.status_code, 200)

    def test_create_requires_login(self):
        response = self.client.get(reverse("library:book_create"))
        self.assertEqual(response.status_code, 302)  # redirect به login

    def test_post_create(self):
        self.client.force_login(user)   # بدون فرم login واقعی
        response = self.client.post(reverse("library:book_create"), {...})
        self.assertEqual(response.status_code, 302)
```

### اجرا

```bash
python manage.py test            # همهٔ تست‌ها
python manage.py test library.tests.test_models   # یک فایل
python manage.py test library.tests.BookModelTest.test_book_str  # یک تست
```

## کد نمونه

`code/tests.py` را ببینید.

## تمرین

یک تست بنویسید که اگر کتابی با صفحات منفی ساخته شود ValidationError بدهد.

## جواب تمرین

```python
def test_negative_pages(self):
    with self.assertRaises(ValidationError):
        Book(pages=-5).full_clean()   # full_clean اعتبارسنجی فیلدها را اجرا می‌کند
```
