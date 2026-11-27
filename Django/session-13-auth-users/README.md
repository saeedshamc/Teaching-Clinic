# جلسه ۱۳ — احراز هویت و کاربران

## درس

Django سیستم auth کامل دارد: مدل `User`، viewهای آمادهٔ login/logout، و decorator محافظت.

### workflow در ویو

```python
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required

def login_view(request):
    if request.method == "POST":
        user = authenticate(request,
            username=request.POST["username"],
            password=request.POST["password"])
        if user is not None:
            login(request, user)          # سشن کاربر را
            return redirect("library:book_list")
        return render(request, "registration/login.html",
                      {"error": "نام کاربری یا رمز اشتباه است"})
    return render(request, "registration/login.html")

def logout_view(request):
    logout(request)
    return redirect("library:home")
```

### محافظت از ویوها

```python
@login_required                    # کاربر لاگین‌نشده → redirect به /accounts/login/
def my_books(request): ...

# یا روی متد کلاس:
from django.contrib.auth.mixins import LoginRequiredMixin
class BookCreateView(LoginRequiredMixin, CreateView): ...
```

### کاربر فعلی و گروه‌ها

```python
request.user                 # کاربر فعلی — AnonymousUser اگر لاگین نشده
request.user.is_authenticated
user.groups.filter(name="editor").exists()
user.has_perm("library.add_book")
```

### ساخت کاربر

```bash
python manage.py createsuperuser
# یا در کد:
from django.contrib.auth.models import User
User.objects.create_user("ali", "ali@mail.com", "pass123")
```

## کد نمونه

`code/views.py` و `code/urls.py` را ببینید.

## تمرین

ویوی `book_create` را طوری کنید که فقط کاربر لاگین‌شده بتواند کتاب بسازد و نام کاربر به‌عنوان سازنده ثبت شود.

## جواب تمرین

```python
@login_required
def book_create(request):
    if request.method == "POST":
        form = BookForm(request.POST, request.FILES)
        if form.is_valid():
            book = form.save(commit=False)
            book.created_by = request.user
            book.save()
            form.save_m2m()
            return redirect("library:book_detail", book.id)
    else:
        form = BookForm()
    return render(request, "library/book_form.html", {"form": form})
```
