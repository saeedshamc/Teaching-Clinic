# serializers.py — Serializerهای پروژهٔ نهایی (جلسه ۵۰)
from rest_framework import serializers
from .models import Book, Author, Tag, Review, ReadingList, Profile, AuditLog
from django.contrib.auth import get_user_model

User = get_user_model()


class TagSerializer(serializers.ModelSerializer):
    class Meta:
        model = Tag
        fields = ["id", "name"]


class AuthorSerializer(serializers.ModelSerializer):
    book_count = serializers.SerializerMethodField()

    class Meta:
        model = Author
        fields = ["id", "name", "birth_year", "bio", "book_count"]

    def get_book_count(self, obj):
        return obj.books.count()


class UserSerializer(serializers.ModelSerializer):
    """برای اطلاعات کاربر — بدون password."""
    class Meta:
        model = User
        fields = ["id", "username", "email", "first_name", "last_name"]
        read_only_fields = ["id", "username"]


class ReviewSerializer(serializers.ModelSerializer):
    user = UserSerializer(read_only=True)
    book_title = serializers.CharField(source="book.title", read_only=True)

    class Meta:
        model = Review
        fields = ["id", "book", "book_title", "user", "rating", "comment", "created_at"]
        read_only_fields = ["user", "created_at"]

    def validate_rating(self, value):
        if not 1 <= value <= 5:
            raise serializers.ValidationError("rating باید بین ۱ و ۵ باشد.")
        return value


class ReadingListSerializer(serializers.ModelSerializer):
    book_title = serializers.CharField(source="book.title", read_only=True)

    class Meta:
        model = ReadingList
        fields = ["id", "book", "book_title", "added_at"]
        read_only_fields = ["added_at"]


class BookSerializer(serializers.ModelSerializer):
    """Serializer اصلی کتاب — کامل با relationها."""
    author_name = serializers.CharField(source="author.name", read_only=True)
    tag_names = serializers.SlugRelatedField(
        slug_field="name", many=True, read_only=True
    )
    tags = serializers.PrimaryKeyRelatedField(
        many=True, queryset=Tag.objects.all(), required=False
    )
    review_count = serializers.SerializerMethodField()
    avg_rating = serializers.SerializerMethodField()
    in_reading_lists = serializers.SerializerMethodField()

    class Meta:
        model = Book
        fields = [
            "id", "title", "author", "author_name",
            "pages", "price", "is_available",
            "tags", "tag_names", "review_count", "avg_rating",
            "in_reading_lists", "created_at", "updated_at",
        ]
        read_only_fields = ["created_at", "updated_at"]

    def get_review_count(self, obj):
        return obj.reviews.count()

    def get_avg_rating(self, obj):
        from django.db.models import Avg
        return obj.reviews.aggregate(avg=Avg("rating"))["avg"]

    def get_in_reading_lists(self, obj):
        return obj.in_lists.count()

    def create(self, validated_data):
        tags = validated_data.pop("tags", [])
        book = Book.objects.create(**validated_data)
        if tags:
            book.tags.set(tags)
        return book

    def update(self, instance, validated_data):
        tags = validated_data.pop("tags", None)
        for attr, value in validated_data.items():
            setattr(instance, attr, value)
        instance.save()
        if tags is not None:
            instance.tags.set(tags)
        return instance
