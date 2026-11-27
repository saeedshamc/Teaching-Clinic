# جلسه ۴۹ — مرور جامع و رفع اشکال (Midterm Review)

## درس

مرور کل مسیر ۱–۴۸ در یک نگاه + چک‌لیست‌های خودارزیابی.

### نقشهٔ ذهنی Django

```
Request → Middleware → URLconf → View (FBV/CBV) → ORM/Model → Template/Serializer → Response
                                    ↓
                              Form validation ← models.py ← migrations ← admin.py
```

### چک‌لیست خودارزیابی

**مدل‌ها:** FK/O2O/M2M، choices، validators، abstract، manager سفارشی، index، migration تمیز
**ویوها:** FBV vs CBV، generic views، mixinها، dispatch، context
**فرم‌ها:** Form vs ModelForm، clean()، formset، inline
**ORM:** select_related/prefetch، annotate، aggregate، Q objects، values/only/defer
**API:** DRF serializers، viewsets، router، permissions، JWT، throttling، pagination
**زیرساخت:** cache، celery، channels، signals، middleware، logging، i18n، security
**Production:** gunicorn، nginx، whitenoise، collectstatic، Docker، env vars

### چک‌لیست امنیت

- [ ] `DEBUG=False` در production
- [ ] `SECRET_KEY` از env، نه در کد
- [ ] `{% csrf_token %}` در همهٔ فرم‌های POST
- [ ] `ALLOWED_HOSTS` محدود
- [ ] `SECURE_SSL_REDIRECT` + HTTPS
- [ ] رمزها hash شده (هرگز plain)
- [ ] آپلود فایل‌ها validate شده (نوع/حجم)
- [ ] `mark_safe` فقط برای محتوای trusted

### چک‌لیست پرفورمنس

- [ ] `select_related`/`prefetch_related` برای رابطه‌ها
- [ ] index روی ستون‌های پرجستجو
- [ ] pagination برای لیست‌های بزرگ
- [ ] cache برای دادهٔ تکراری/گران
- [ ] `values()`/`only()` به‌جای مدل کامل
- [ ] bulk_create/update به‌جای حلقهٔ save()

## تمرین

یک مینی‌پروژهٔ «Notebook» بسازید: مدل Note با owner (FK به User)، لیست/ساخت/ویرایش/حذف با CBV + LoginRequiredMixin، جستجوی ساده، و ۳ تست اصلی.

## جواب تمرین

```python
class Note(models.Model):
    owner = models.ForeignKey(User, on_delete=models.CASCADE)
    title = models.CharField(max_length=200)
    body = models.TextField(blank=True)
    created_at = models.DateTimeField(auto_now_add=True)

class NoteListView(LoginRequiredMixin, ListView):
    model = Note
    def get_queryset(self):
        return Note.objects.filter(owner=self.request.user)

# + CreateView/UpdateView/DeleteView مشابه با success_url
```
