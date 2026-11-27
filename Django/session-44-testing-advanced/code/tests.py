# tests.py — تست پیشرفته با API و Factory (جلسه ۴۴)
from rest_framework.test import APITestCase, APIClient
from rest_framework import status
from django.contrib.auth import get_user_model
from .models import Book, Author, Review
from .factories import AuthorFactory, BookFactory, UserFactory

User = get_user_model()


# ══════════════════════════════════════════════════════════════════
# APITestCase — تست API با JWT
# ══════════════════════════════════════════════════════════════════

class BookAPITest(APITestCase):
    """تست endpointهای /api/books/"""

    def setUp(self):
        # setUp برای هر تست — دادهٔ تازه
        self.author = AuthorFactory()
        self.book = BookFactory(author=self.author, pages=200)
        self.user = UserFactory()
        self.staff = UserFactory(is_staff=True)

    def get_token(self, user):
        """گرفتن JWT access token برای کاربر."""
        response = self.client.post("/api/token/", {
            "username": user.username,
            "password": "password123",
        }, format="json")
        return response.data["access"]

    def authenticate(self, user):
        """تنظیم header Authorization برای requestهای بعدی."""
        token = self.get_token(user)
        self.client.credentials(HTTP_AUTHORIZATION=f"Bearer {token}")

    # ─── تست‌های خواندنی (بدون auth) ─────────────────────────────

    def test_book_list_public(self):
        """GET /api/books/ بدون auth → 200."""
        response = self.client.get("/api/books/")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.data["results"]), 1)

    def test_book_detail(self):
        response = self.client.get(f"/api/books/{self.book.id}/")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data["title"], self.book.title)

    # ─── تست‌های نوشتنی (با auth) ────────────────────────────────

    def test_create_requires_auth(self):
        """POST بدون token → 401."""
        response = self.client.post("/api/books/", {
            "title": "جدید",
            "pages": 100,
        }, format="json")
        self.assertIn(response.status_code, [401, 403])

    def test_create_with_jwt(self):
        """POST با JWT معتبر → 201."""
        self.authenticate(self.user)
        response = self.client.post("/api/books/", {
            "title": "کتاب جدید",
            "author": self.author.id,
            "pages": 150,
            "price": 50000,
        }, format="json")
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)
        self.assertEqual(Book.objects.count(), 2)

    def test_update_with_jwt(self):
        self.authenticate(self.user)
        response = self.client.patch(
            f"/api/books/{self.book.id}/",
            {"title": "عنوان جدید"},
            format="json",
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.book.refresh_from_db()
        self.assertEqual(self.book.title, "عنوان جدید")

    def test_delete_staff_only(self):
        """DELETE فقط برای staff."""
        self.authenticate(self.user)   # کاربر عادی
        response = self.client.delete(f"/api/books/{self.book.id}/")
        self.assertIn(response.status_code, [401, 403])

        self.authenticate(self.staff)  # staff
        response = self.client.delete(f"/api/books/{self.book.id}/")
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)

    # ─── تست‌های permission ──────────────────────────────────────

    def test_is_book_owner(self):
        """فقط owner می‌تواند ویرایش کند."""
        # فرض: book.owner = user
        self.book.owner = self.user
        self.book.save()

        other = UserFactory()
        self.authenticate(other)
        response = self.client.patch(
            f"/api/books/{self.book.id}/",
            {"title": "hacked"},
            format="json",
        )
        self.assertIn(response.status_code, [401, 403])


# ══════════════════════════════════════════════════════════════════
# TestCase — تست model و view
# ══════════════════════════════════════════════════════════════════

from django.test import TestCase, Client


class BookModelTest(TestCase):
    """تست مدل Book."""

    @classmethod
    def setUpTestData(cls):
        """دادهٔ مشترک برای همهٔ تست‌ها — فقط یک‌بار ساخته می‌شود."""
        cls.author = AuthorFactory(name="Test Author")
        cls.book = BookFactory(author=cls.author, title="Test Book", pages=200)

    def test_str(self):
        self.assertEqual(str(self.book), "Test Book")

    def test_pages_positive(self):
        self.book.pages = -5
        with self.assertRaises(Exception):   # ValidationError
            self.book.full_clean()

    def test_available_filter(self):
        BookFactory(is_available=False)
        self.assertEqual(
            Book.objects.filter(is_available=True).count(), 1
        )


class BookViewTest(TestCase):
    """تست viewهای وب."""

    def setUp(self):
        self.client = Client()
        self.author = AuthorFactory()
        self.book = BookFactory(author=self.author)

    def test_book_list_200(self):
        response = self.client.get("/books/")
        self.assertEqual(response.status_code, 200)
        self.assertContains(response, self.book.title)

    def test_book_detail_200(self):
        response = self.client.get(f"/books/{self.book.id}/")
        self.assertEqual(response.status_code, 200)

    def test_book_detail_404(self):
        response = self.client.get("/books/99999/")
        self.assertEqual(response.status_code, 404)

    def test_create_requires_login(self):
        response = self.client.post("/books/new/", {"title": "x"})
        self.assertEqual(response.status_code, 302)   # redirect to login


# ══════════════════════════════════════════════════════════════════
# factories.py در پوشهٔ code/ مجاور قرار دارد — آن را در اپ library کپی کنید.
# خلاصهٔ factoryها:
# ══════════════════════════════════════════════════════════════════
# import factory
# from factory import fuzzy
# from django.contrib.auth import get_user_model
# from .models import Book, Author, Review
# User = get_user_model()
#
# class UserFactory(factory.django.DjangoModelFactory):
#     class Meta:
#         model = User
#         skip_postgeneration_save = True
#     username = factory.Sequence(lambda n: f"user{n}")
#     email = factory.LazyAttribute(lambda o: f"{o.username}@test.com")
#     @factory.post_generation
#     def password(obj, create, extracted, **kwargs):
#         obj.set_password(extracted or "password123")
#         if create: obj.save()
#
# class AuthorFactory(factory.django.DjangoModelFactory):
#     class Meta: model = Author
#     name = factory.Sequence(lambda n: f"Author {n}")
#     birth_year = fuzzy.FuzzyInteger(1900, 2000)
#
# class BookFactory(factory.django.DjangoModelFactory):
#     class Meta: model = Book
#     title = factory.Sequence(lambda n: f"Book {n}")
#     author = factory.SubFactory(AuthorFactory)
#     pages = fuzzy.FuzzyInteger(50, 1000)
#     price = fuzzy.FuzzyInteger(10000, 500000)
#     is_available = True
#
# # استفاده: book = BookFactory() → author خودکار ساخته می‌شود
