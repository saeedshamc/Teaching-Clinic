# tests.py — مثال‌های تست با کامنت فارسی
from django.test import Client, TestCase
from django.urls import reverse

from .models import Author, Book


class BookModelTest(TestCase):
    """تست‌های مدل — هر تست در transaction جداگانه اجرا می‌شود."""

    def setUp(self):
        # دادهٔ نمونه برای همهٔ تست‌های این کلاس
        self.author = Author.objects.create(name="لوییس")
        self.book = Book.objects.create(
            title="جنگو برای مبتدیان",
            author=self.author,
            pages=300,
            price=250000,
        )

    def test_str_representation(self):
        """__str__ باید عنوان را برگرداند."""
        self.assertIn("جنگو", str(self.book))

    def test_default_ordering(self):
        """ترتیب پیش‌فرض Meta باید رعایت شود."""
        books = list(Book.objects.all())
        self.assertEqual(books[0].title, "جنگو برای مبتدیان")

    def test_filter_by_author(self):
        self.assertEqual(
            Book.objects.filter(author=self.author).count(), 1
        )

    def test_price_validation(self):
        """قیمت منفی باید ValidationError بدهد."""
        from django.core.exceptions import ValidationError
        book = Book(title="تست", author=self.author, pages=10, price=-1)
        with self.assertRaises(ValidationError):
            book.full_clean()


class BookViewTest(TestCase):
    """تست ویوها با Client شبیه‌سازی مرورگر."""

    @classmethod
    def setUpTestData(cls):
        # setUpTestData فقط یک‌بار برای کل کلاس اجرا می‌شود (سریع‌تر)
        cls.author = Author.objects.create(name="تست")
        cls.book = Book.objects.create(
            title="کتاب تست", author=cls.author, pages=100
        )
        cls.user = User.objects.create_user("tester", password="pass123")

    def test_list_returns_200(self):
        response = self.client.get(reverse("library:book_list"))
        self.assertEqual(response.status_code, 200)
        self.assertTemplateUsed(response, "library/book_list.html")

    def test_detail_404(self):
        response = self.client.get(reverse("library:book_detail", args=[9999]))
        self.assertEqual(response.status_code, 404)

    def test_create_requires_login(self):
        response = self.client.get(reverse("library:book_create"))
        # بدون لاگین باید redirect شود
        self.assertEqual(response.status_code, 302)

    def test_create_with_login(self):
        self.client.force_login(self.user)
        response = self.client.post(reverse("library:book_create"), {
            "title": "کتاب جدید",
            "author": self.author.id,
            "pages": 200,
            "price": "100000",
        })
        self.assertEqual(response.status_code, 302)
        self.assertTrue(Book.objects.filter(title="کتاب جدید").exists())
