# جلسه ۲۴ — FBV در برابر CBV و کلاس View

## درس

### مقایسه

| | FBV | CBV |
|---|---|---|
| مناسب برای | منطق ساده، یک کار | CRUD تکراری، mixinها |
| ساختار | تابع با if method | کلاس با متدهای get/post |
| توسعه | مستقیم |Inheritance/mixin |

### کلاس View دستی — کنترل کامل

```python
from django.views import View
from django.utils.decorators import method_decorator
from django.contrib.auth.decorators import login_required

class BookSearchView(View):
    """هم GET هم POST — dispatch همه را می‌گیرد."""

    @method_decorator(login_required)   # مثل login_required روی dispatch
    def dispatch(self, request, *args, **kwargs):
        return super().dispatch(request, *args, **kwargs)

    def get(self, request):
        q = request.GET.get("q", "")
        books = Book.objects.filter(title__icontains=q) if q else Book.objects.none()
        return render(request, "library/search.html", {"books": books, "q": q})

    def post(self, request):
        # منطق POST
        ...
```

### TemplateView با get_context_data

```python
class StatsView(TemplateView):
    template_name = "library/stats.html"

    def get_context_data(self, **kwargs):
        ctx = super().get_context_data(**kwargs)
        ctx["total_books"] = Book.objects.count()
        ctx["available"] = Book.objects.filter(is_available=True).count()
        return ctx
```

## کد نمونه

بالا کامنت‌گذاری شده — در `views.py` پیاده کنید.

## تمرین

یک `View` دستی برای API بنویسید که `GET` لیست و `POST` ساخت کتاب را با `JsonResponse` انجام دهد.

## جواب تمرین

```python
class BookAPIView(View):
    def get(self, request):
        data = list(Book.objects.values("id", "title", "pages"))
        return JsonResponse(data, safe=False)

    def post(self, request):
        import json
        body = json.loads(request.body)
        book = Book.objects.create(**body)
        return JsonResponse({"id": book.id}, status=201)
```
