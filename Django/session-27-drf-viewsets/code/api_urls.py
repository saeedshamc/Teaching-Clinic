# api_urls.py — Router برای DRF (جلسه ۲۷)
from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import BookViewSet, AuthorViewSet, ReviewViewSet


# DefaultRouter → URLهای استاندارد + API root browser-friendly
router = DefaultRouter()
router.register("books", BookViewSet, basename="book")
router.register("authors", AuthorViewSet, basename="author")
router.register("reviews", ReviewViewSet, basename="review")


urlpatterns = [
    # /api/ → router.urls (همهٔ endpointهای ثبت‌شده)
    path("", include(router.urls)),
]

# SimpleRouter → فقط URLها، بدون API root page
# از DefaultRouter استفاده کنید مگر دلیلی برای Simple داشته باشید.

# نکته: basename وقتی queryset نداری لازم است.
# اگر ViewSet.queryset داشته باشد، basename خودکار از model نام می‌گیرد.
