# factories.py — factory_boy برای دادهٔ تست (جلسه ۴۴)
# pip install factory_boy
import factory
from factory import fuzzy
from django.contrib.auth import get_user_model
from .models import Book, Author, Review

User = get_user_model()


class UserFactory(factory.django.DjangoModelFactory):
    """ساخت کاربر تست — password به‌صورت hash ذخیره می‌شود."""

    class Meta:
        model = User
        skip_postgeneration_save = True   # برای set_password لازم است

    username = factory.Sequence(lambda n: f"user{n}")
    email = factory.LazyAttribute(lambda o: f"{o.username}@test.com")
    is_staff = False

    @factory.post_generation
    def password(obj, create, extracted, **kwargs):
        # set_password → hash صحیح رمز
        obj.set_password(extracted or "password123")
        if create:
            obj.save()


class AuthorFactory(factory.django.DjangoModelFactory):
    class Meta:
        model = Author

    name = factory.Sequence(lambda n: f"Author {n}")
    birth_year = fuzzy.FuzzyInteger(1900, 2000)


class BookFactory(factory.django.DjangoModelFactory):
    class Meta:
        model = Book

    title = factory.Sequence(lambda n: f"Book {n}")
    author = factory.SubFactory(AuthorFactory)   # author خودکار ساخته می‌شود
    pages = fuzzy.FuzzyInteger(50, 1000)
    price = fuzzy.FuzzyInteger(10000, 500000)
    is_available = True


class ReviewFactory(factory.django.DjangoModelFactory):
    class Meta:
        model = Review

    book = factory.SubFactory(BookFactory)
    user = factory.SubFactory(UserFactory)
    rating = fuzzy.FuzzyInteger(1, 5)
    comment = factory.Faker("sentence")


# ══════════════════════════════════════════════════════════════════
# استفاده:
# ══════════════════════════════════════════════════════════════════
# book = BookFactory()                    # author هم خودکار ساخته می‌شود
# books = BookFactory.create_batch(10)    # ۱۰ کتاب
# book = BookFactory(author=author)       # با author مشخص
# user = UserFactory(password="secret")   # با رمز مشخص
# review = ReviewFactory(book=book, user=user, rating=5)
