# جلسه ۴۴ — تست پیشرفته (API + Factory)

## درس

### APITestCase برای DRF

```python
from rest_framework.test import APITestCase
from rest_framework import status

class BookAPITest(APITestCase):
    def setUp(self):
        self.author = Author.objects.create(name="تست")
        self.book = Book.objects.create(title="کتاب API", author=self.author, pages=100)
        self.user = User.objects.create_user("api", password="pass")

    def test_list(self):
        response = self.client.get("/api/books/")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.data["results"]), 1)

    def test_create_requires_auth(self):
        response = self.client.post("/api/books/", {"title": "جدید"}, format="json")
        self.assertEqual(response.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_create_with_jwt(self):
        # گرفتن توکن
        token = self.client.post("/api/token/", {
            "username": "api", "password": "pass"
        }).data["access"]
        self.client.credentials(HTTP_AUTHORIZATION=f"Bearer {token}")
        response = self.client.post("/api/books/", {"title": "جدید", "pages": 50}, format="json")
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)
```

### Factory Boy — دادهٔ تست تمیز

```bash
pip install factory_boy
```

```python
import factory
from .models import Book, Author

class AuthorFactory(factory.django.DjangoModelFactory):
    class Meta:
        model = Author
    name = factory.Sequence(lambda n: f"Author {n}")

class BookFactory(factory.django.DjangoModelFactory):
    class Meta:
        model = Book
    title = factory.Sequence(lambda n: f"Book {n}")
    author = factory.SubFactory(AuthorFactory)
    pages = 200

# استفاده: book = BookFactory()  → نویسنده هم خودکار ساخته می‌شود
```

## تمرین

با `factory_boy` یک `BookFactory` بنویسید که `author` را خودکار بسازد و در تست از آن استفاده کنید.

## جواب تمرین

```python
class BookFactory(factory.django.DjangoModelFactory):
    class Meta:
        model = Book
    title = factory.Sequence(lambda n: f"Book {n}")
    author = factory.SubFactory(AuthorFactory)
    pages = factory.Faker("random_int", min=50, max=1000)
```
