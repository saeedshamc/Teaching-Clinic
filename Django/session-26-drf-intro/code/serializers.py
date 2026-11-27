# serializers.py — تبدیل مدل به JSON (جلسه ۲۶)
from rest_framework import serializers
from .models import Book, Author, Tag, Review


class AuthorSerializer(serializers.ModelSerializer):
    """Serializer برای Author — فقط فیلدهای لازم."""
    class Meta:
        model = Author
        fields = ["id", "name", "birth_year"]
        # read_only_fields = ["created_at"]


class TagSerializer(serializers.ModelSerializer):
    class Meta:
        model = Tag
        fields = ["id", "name"]


class BookSerializer(serializers.ModelSerializer):
    """Serializer اصلی کتاب — شامل relationها."""

    # فیلدهای خواندنی از relation
    author_name = serializers.CharField(source="author.name", read_only=True)
    tag_names = serializers.SlugRelatedField(
        slug_field="name",
        many=True,
        read_only=True,
    )

    # برای نوشتن — لیست idهای tag
    tags = serializers.PrimaryKeyRelatedField(
        many=True, queryset=Tag.objects.all(), required=False
    )

    # فیلد محاسباتی — تعداد reviewها
    review_count = serializers.SerializerMethodField()

    class Meta:
        model = Book
        fields = [
            "id", "title", "author", "author_name",
            "pages", "price", "is_available",
            "tags", "tag_names", "review_count",
            "created_at",
        ]
        read_only_fields = ["created_at"]

    def get_review_count(self, obj):
        # هر Book → reviews related_name
        return obj.reviews.count()

    def validate_price(self, value):
        # اعتبارسنجی سفارشی — قیمت نمی‌تواند منفی باشد
        if value < 0:
            raise serializers.ValidationError("قیمت نمی‌تواند منفی باشد.")
        return value

    def create(self, validated_data):
        # tags را قبل از create حذف کن — بعداً جدا set می‌کنیم
        tags = validated_data.pop("tags", [])
        book = Book.objects.create(**validated_data)
        if tags:
            book.tags.set(tags)
        return book


class ReviewSerializer(serializers.ModelSerializer):
    """Serializer برای نظر — user از request می‌آید."""
    user_name = serializers.CharField(source="user.username", read_only=True)
    book_title = serializers.CharField(source="book.title", read_only=True)

    class Meta:
        model = Review
        fields = ["id", "book", "book_title", "user", "user_name",
                  "rating", "comment", "created_at"]
        read_only_fields = ["user", "created_at"]

    def validate_rating(self, value):
        if not 1 <= value <= 5:
            raise serializers.ValidationError("rating باید بین ۱ و ۵ باشد.")
        return value
