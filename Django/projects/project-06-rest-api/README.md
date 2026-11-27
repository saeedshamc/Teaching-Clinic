# پروژه ۶ — REST API کامل با JWT (جلسات ۲۶–۳۰)

## هدف
API کامل برای اپ کتابخانه با DRF + SimpleJWT + pagination + throttle.

## تسک‌ها

1. `BookSerializer` + `ReviewSerializer` با `author_name` (source)
2. `BookViewSet(ModelViewSet)` + `ReviewViewSet` با router → `/api/books/`, `/api/reviews/`
3. `@action(detail=True)` برای `publish` و `archive`
4. `IsAuthenticatedOrReadOnly` + `get_permissions()` داینامیک
5. JWT: `/api/token/` + `/api/token/refresh/` + محافظت همهٔ write endpointها
6. `PageNumberPagination(page_size=10)` + `DjangoFilterBackend` + `SearchFilter`

## معیارهای پذیرش

- [ ] `GET /api/books/` بدون auth کار می‌کند (read-only)
- [ ] `POST /api/books/` بدون token → 401
- [ ] `POST /api/token/` → access+refresh → `Bearer` در هدر
- [ ] `GET /api/books/?search=django&ordering=-price` فیلتر می‌کند
- [ ] `POST /api/books/{id}/publish/` فقط برای staff

## فایل‌های کلیدی

`serializers.py`, `views.py`, `api_urls.py`, `settings.py` (REST_FRAMEWORK), `config/urls.py`
