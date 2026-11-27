# طرح درس کامل دوره Django — ۵۰ جلسه × ۲ ساعت

این سند مسیر دقیق تدریس است: هر جلسه ۱۲۰ دقیقه، تقسیم به بخش‌های teoria / کدنویسی زنده / تمرین / جمع‌بندی.
پیش‌نیاز: آشنایی مقدماتی با Python و HTML/CSS. برای جلسات ۲۶+ آشنایی با JSON و HTTP توصیه می‌شود.

> نمادها: 🟦 نظریه | 🟩 کدنویسی زنده | 🟨 تمرین فردی | 🟥 پروژه/تمرین گروهی | ⭐ نکتهٔ طلایی مدرس

---

## بخش اول — مبانی Django (جلسات ۱–۱۰)

### جلسه ۱ — معرفی Django و اولین پروژه (۲ ساعت)
- 🟦 ۰–۲۰د: Django چیست، MVT vs MVC، چه پروژه‌هایی با آن ساخته می‌شود، مقایسه با Flask/Laravel
- 🟩 ۲۰–۵۰د: نصب Python/venv → `pip install django` → `django-admin startproject config .` → `startapp library` → `runserver`
- 🟩 ۵۰–۸۰د: نوشتن اولین View و URL → مشاهده در مرورگر
- 🟨 ۸۰–۱۱۰د: تمرین README جلسه (ساخت پروژه و نمایش «سلام») + جواب
- 🟥 ۱۱۰–۱۲۰د: دانش‌آموزان پروژه را به GitHub push کنند؛ ⭐ checkpoint: همه runserver دارند

### جلسه ۲ — ساختار پروژه و درخواست‌ها (۲ ساعت)
- 🟦: manage.py، پروژه در برابر اپ، ساختار پوشه‌ها، settings.py، urls.py، flow درخواست
- 🟩: پروژهٔ نمونه را قدم‌به‌قدم می‌سازیم؛ تغییر ALLOWED_HOSTS، INSTALLED_APPS
- 🟨: تمرین: اضافه کردن اپ دوم و url مجزا
- 🟥: نمودار flow روی برد؛ ⭐ هرگز settings را با git نگه ندارید (بعداً env)

### جلسه ۳ — مدل‌ها و Migration (۲ ساعت)
- 🟦: مدل = جدول، فیلدها، PK، migration چیست و چرا
- 🟩: نوشتن مدل Book/Author → makemigrations → migrate → shell → create/get/filter
- 🟨: تمرین: مدل Tag با __str__ و ordering
- 🟥: هر تغییر مدل = migration جدید؛ ⭐ هرگز DB را دستی عوض نکنید

### جلسه ۴ — انواع فیلد و Validators (۲ ساعت)
- 🟦: CharField/TextField/Integer/Decimal/Boolean/DateTime/Email/URL/File/Image
- 🟩: تکمیل مدل Book با همهٔ فیلدها، choices، validators، Meta (ordering, verbose_name)
- 🟨: تمرین: فیلد status با choices و validator قیمت مثبت
- 🟥: مقایسهٔ فیلدها؛ ⭐ db_index=True را بعداً یاد می‌گیریم (جلسه ۴۳)

### جلسه ۵ — روابط بین مدل‌ها (۲ ساعت)
- 🟦: FK، OneToOne، M2M؛ related_name؛ on_delete (CASCADE/PROTECT/SET_NULL)
- 🟩: پیاده‌سازی Author→Book→Tag؛ تست relation در shell؛ query روی relation
- 🟨: تمرین: مدل Review با rating validator
- 🟥: رسم ER روی برد؛ ⭐ انتخاب on_delete اشتباه = از دست دادن داده

### جلسه ۶ — ORM و QuerySet (۲ ساعت)
- 🟦: ORM چیست، QuerySet lazy است، زنجیرهٔ filter
- 🟩: all/filter/get/exclude/order_by/values/count/aggregate/annotate زنده
- 🟨: تمرین queries.py — ۱۰ کوئری مختلف
- 🟥: lookupها (__gt,__contains,__in,__date)؛ ⭐ distinct() با M2M

### جلسه ۷ — Django Admin (۲ ساعت)
- 🟦: admin خودکار، ModelAdmin، list_display/filter/search/ordering
- 🟩: ثبت مدل‌ها، اکشن سفارشی، readonly_fields، date_hierarchy
- 🟨: تمرین: اکشن «غیرفعال کردن انتخاب‌شده‌ها»
- 🟥: ساخت superuser + داشبورد؛ ⭐ برای دادهٔ تکراری همیشه admin

### جلسه ۸ — URL و View (۲ ساعت)
- 🟦: URLconf، path converters (int/str/slug/uuid)، namespace، reverse
- 🟩: ویوهای function-based، HttpResponse، JsonResponse، redirect
- 🟨: تمرین: صفحهٔ «دربارهٔ ما» + صفحهٔ جزئیات کتاب با converter
- 🟥: map کردن URL روی برد؛ ⭐ نام‌گذاری urlها با نام‌گذاری viewها هماهنگ باشد

### جلسه ۹ — Templateها (۲ ساعت)
- 🟦: DTL، متغیرها، tags ({% %})، filters ({{ | }})، autoescape
- 🟩: base.html با block → extends، book_list با for/if/empty، include
- 🟨: تمرین: صفحهٔ جزئیات با inheritance + conditional
- 🟥: XSS و autoescape؛ ⭐ هرگز |safe روی ورودی کاربر نزنید

### جلسه ۱۰ — Static و Media (۲ ساعت)
- 🟦: static (فایل‌های پروژه) در برابر media (آپلود کاربر)؛ STATIC_URL/ROOT، MEDIA_URL/ROOT
- 🟩: ساخت static/style.css، اتصال به base.html، ImageField + upload_to
- 🟨: تمرین: آپلود جلد کتاب از admin و نمایش در قالب
- 🟥: collectstatic را یادآوری کن برای production؛ ⭐ media در git قرار نگیرد

---

## بخش دوم — وب کامل (جلسات ۱۱–۲۰)

### جلسه ۱۱ — فرم‌ها و ModelForm (۲ ساعت)
- 🟦: Form vs ModelForm، fields/widgets، validation (clean/clean_field)
- 🟩: BookForm با clean() سفارشی، ویوی create/update با POST/redirect/GET
- 🟨: تمرین: فرم با پیام خطای فارسی
- 🟥: چرخهٔ POST-redirect-GET؛ ⭐ هرگز دادهٔ POST را مستقیم render نکنید (refresh = دوباره submit)

### جلسه ۱۲ — ویوهای مبتنی بر کلاس (۲ ساعت)
- 🟦: Generic CBV: List/Detail/Create/Update/Delete/Template — چه زمانی FBV بهتر است
- 🟩: بازنویسی CRUD با CBV، get_queryset/get_context_data/form_valid
- 🟨: تمرین: Author CRUD با CBV
- 🟥: مقایسهٔ FBV/CBV؛ ⭐ برای CRUD استاندارد CBV، برای منطق خاص FBV

### جلسه ۱۳ — احراز هویت و کاربران (۲ ساعت)
- 🟦: User model، AbstractUser، login/logout، session، login_required، permission_required
- 🟩: login_view/logout_view سفارشی، UserCreationForm، admin user
- 🟨: تمرین: فقط نویسنده بتواند کتاب بسازد (owner field)
- 🟥: Authentication vs Authorization؛ ⭐ password هرگز plain ذخیره نمی‌شود (hash)

### جلسه ۱۴ — Session و Messages (۲ ساعت)
- 🟦: session cookie چیست، request.session، messages framework
- 🟩: تم روشن/تاریک با session، success/error messages در قالب
- 🟨: تمرین: سبد خرید ساده با session
- 🟥: امضای session cookie؛ ⭐ دادهٔ حساس در session نگذارید (فقط id/flag)

### جلسه ۱۵ — Middleware (۲ ساعت)
- 🟦: middleware چیست، ترتیب MIDDLEWARE، request/response pipeline
- 🟩: TimingMiddleware با X-Response-Time، middleware برای HTTPS redirect
- 🟨: تمرین: middleware شمارندهٔ بازدید صفحه
- 🟥: onion model middleware؛ ⭐ middleware برای کارهای cross-cutting (log/auth/timing)

### جلسه ۱۶ — CBV پیشرفته: mixinها (۲ ساعت)
- 🟦: LoginRequiredMixin، PermissionRequiredMixin، UserPassesTestMixin، custom mixin
- 🟩: CRUD کامل با mixinها، override dispatch، get_success_url
- 🟨: تمرین: فقط staff بتواند delete کند
- 🟥: MRO در mixinها؛ ⭐ ترتیب mixin مهم است (auth قبل از view)

### جلسه ۱۷ — سیگنال‌ها (۲ ساعت)
- 🟦: signals چیست، pre/post_save، pre_delete، m2m_changed، receiver
- 🟩: post_save روی Book → لاگ، apps.py.ready برای اتصال
- 🟨: تمرین: سیگنال برای ساخت Profile خودکار
- 🟥: trap: سیگنال در migration اجرا نمی‌شود؛ ⭐ برای side-effect (email/log/notify)

### جلسه ۱۸ — تست (۲ ساعت)
- 🟦: چرا تست، TestCase vs TransactionTestCase، setUp/test_*، assert*
- 🟩: تست model + view با Client، setUpTestData برای سرعت
- 🟨: تمرین: ۵ تست برای Book
- 🟥: coverage؛ ⭐ تست قبل از فیچر (TDD) در پروژه‌های بزرگ

### جلسه ۱۹ — بهینه‌سازی (۲ ساعت)
- 🟦: N+1 problem، select_related vs prefetch_related، pagination
- 🟩: DebugToolbar نصب، اندازه‌گیری کوئری، بهینه‌سازی لیست کتاب‌ها
- 🟨: تمرین: حذف N+1 از book_list
- 🟥: QuerySet lazy evaluation؛ ⭐ always check queries count

### جلسه ۲۰ — پروژهٔ نهایی بخش دوم: کتابخانه (۲ ساعت)
- 🟥: ساخت کامل اپ library با همهٔ مباحث ۱–۱۹ در یک جلسهٔ فشرده + مرور
- 🟩: مدل‌ها، admin، ویوها، قالب‌ها، auth، تست
- 🟥: ارائهٔ پروژه به کلاس؛ ⭐ checkpoint: هر دانش‌آموز یک اپ CRUD کامل دارد

---

## بخش سوم — پیشرفته (جلسات ۲۱–۳۰)

### جلسه ۲۱ — Manager و QuerySet سفارشی
- 🟦: Manager vs QuerySet، from_queryset، chaining
- 🟩: BookQuerySet.available()/expensive()، BookManager
- 🟨: تمرین AuthorQuerySet
- 🟥: متدهای manager vs queryset؛ ⭐ queryset برای chain، manager برای entry point

### جلسه ۲۲ — Abstract و Proxy Model
- 🟦: abstract=True، proxy، multi-table inheritance، parent_link
- 🟩: TimestampedModel، SluggedModel، proxy AvailableBook
- 🟨: تمرین slug خودکار
- 🟥: abstract برای reusable fields، proxy برای رفتار متفاوت روی همان جدول

### جلسه ۲۳ — Formset و ModelFormSet
- 🟦: formset، extra/can_delete، modelformset_factory، inlineformset_factory
- 🟩: bulk edit کتاب‌ها، inline tags روی Book
- 🟨: تمرین ModelFormSet برای ویرایش دسته‌ای
- 🟥: management form (formset-MANAGEMENT)؛ ⭐ inline برای parent-child

### جلسه ۲۴ — FBV در برابر CBV و View دستی
- 🟦: View class، as_view()، dispatch، method_decorator
- 🟩: BookSearchView با get/post، StatsView با get_context_data
- 🟨: تمرین BookAPIView با JsonResponse
- 🟥: decision tree FBV/CBV؛ ⭐ View برای API ساده، generics برای CRUD

### جلسه ۲۵ — جستجو و فیلتر پیشرفته
- 🟦: Q objects (OR/AND/NOT)، lookup در relation، django-filter
- 🟩: جستجوی ترکیبی title+author+tags، BookFilter
- 🟨: تمرین RangeFilter برای price
- 🟥: distinct() با M2M search؛ ⭐ index روی فیلدهای جستجو (جلسه ۴۳)

### جلسه ۲۶ — DRF مقدمه
- 🟦: REST چیست، serializer، APIView، status codes
- 🟩: BookSerializer + BookListAPI (get/post)
- 🟨: تمرین author_name با source
- 🟥: JSON vs HTML؛ ⭐ serializer = form برای API

### جلسه ۲۷ — DRF ViewSet و Router
- 🟦: ModelViewSet، actions، @action، DefaultRouter
- 🟩: BookViewSet + router، publish/archive actions
- 🟨: تمرین archive action
- 🟥: URL خودکار router؛ ⭐ basename برای queryset بدون model

### جلسه ۲۸ — احراز هویت و دسترسی در DRF
- 🟦: SessionAuth vs TokenAuth، permission classes، get_permissions
- 🟩: IsAuthenticatedOrReadOnly، get_permissions داینامیک
- 🟨: تمرین permission matrix
- 🟥: Authentication vs Permission؛ ⭐ 401 (no auth) vs 403 (no permission)

### جلسه ۲۹ — Pagination و Throttling در DRF
- 🟦: PageNumberPagination، DjangoFilterBackend، SearchFilter، OrderingFilter
- 🟩: page_size=10، search_fields، throttle rates
- 🟨: تمرین تنظیم pagination + search
- 🟥: صفحه‌بندی برای dataset بزرگ؛ ⭐ throttle برای جلوگیری از abuse

### جلسه ۳۰ — JWT Authentication
- 🟦: JWT چیست، access vs refresh token، SimpleJWT
- 🟩: token/refresh/verify endpoints، Bearer header، MeView
- 🟨: تمرین endpoint «من»
- 🟥: stateless vs session؛ ⭐ refresh rotation برای امنیت

---

## بخش چهارم — زیرساخت و Production (جلسات ۳۱–۴۰)

### جلسه ۳۱ — Caching
- 🟦: چرا cache، LocMem vs Redis، low-level API، per-view، fragment
- 🟩: get/set با timeout، cache_page، {% cache %}
- 🟨: تمرین کش page ۱
- 🟥: cache invalidation سخت‌ترین قسمت؛ ⭐ key naming convention

### جلسه ۳۲ — Celery مقدمه
- 🟦: task queue، broker (Redis/RabbitMQ)، worker، sync vs async
- 🟩: send_welcome_email با retry، .delay() vs .apply_async()
- 🟨: تمرین generate_report
- 🟥: وقتی نباید از celery استفاده کرد؛ ⭐ I/O-bound نه CPU-bound

### جلسه ۳۳ — Celery Beat
- 🟦: periodic tasks، crontab vs interval، django-celery-beat
- 🟩: daily_report ساعت ۸، IntervalSchedule در DB
- 🟨: تمرین daily_new_books
- 🟥: beat جدا از worker اجرا می‌شود؛ ⭐ timezone در crontab مهم است

### جلسه ۳۴ — Django Channels و WebSocket
- 🟦: ASGI vs WSGI، channel layer، consumer، group
- 🟩: ChatConsumer با group_add/send، routing.py
- 🟨: تمرین online counter
- 🟥: async/await mental model؛ ⭐ WebSocket برای real-time فقط

### جلسه ۳۵ — سیگنال‌های پیشرفته و Audit Log
- 🟦: m2m_changed، audit pattern، strong/weak reference
- 🟩: AuditLog model، post_save → log
- 🟨: تمرین audit ساده
- 🟥: signal برای cross-cutting concerns؛ ⭐ trap: signals در fixtures/migrations

### جلسه ۳۶ — Template Tags و Filter
- 🟦: simple_tag، inclusion_tag، custom filter، Library()
- 🟩: total_books، book_price، fa_number
- 🟨: تمرین truncate_words_fa
- 🟥: tag vs filter؛ ⭐ filter برای transform مقدار، tag برای logic

### جلسه ۳۷ — i18n و l10n
- 🟦: gettext، makemessages/compilemessages، LocaleMiddleware، i18n_patterns
- 🟩: ترجمهٔ «لیست کتاب‌ها»، blocktranslate با count
- 🟨: تمرین ترجمه به en
- 🟥: RTL considerations؛ ⭐ pluralization در gettext

### جلسه ۳۸ — امنیت
- 🟦: OWASP top 10، CSRF/XSS/SQLi/clickjacking، SECURE_* settings
- 🟩: SecurityHeadersMiddleware، SECRET_KEY از env
- 🟨: تمرین middleware هدرها
- 🟥: defense in depth؛ ⭐ هرگز input کاربر را trust نکنید

### جلسه ۳۹ — دیپلوی (Gunicorn + Nginx)
- 🟦: WSGI vs ASGI server، reverse proxy، SSL termination
- 🟩: gunicorn command، nginx.conf، collectstatic
- 🟨: checklist دیپلوی
- 🟥: workers = 2×CPU+1؛ ⭐ static را Nginx سرو کند نه Django

### جلسه ۴۰ — Docker برای Django
- 🟦: containerization، image vs container، Dockerfile layers
- 🟩: Dockerfile multi-stage، docker-compose با db+redis
- 🟨: تمرین compose کامل
- 🟥: .dockerignore، healthcheck؛ ⭐ هرگز media/db volume را در image نگذارید

---

## بخش پنجم — حرفه‌ای (جلسات ۴۱–۵۰)

### جلسه ۴۱ — Static در Production
- 🟦: ManifestStaticFilesStorage، hash در نام فایل، whitenoise، CDN
- 🟩: STORAGES config، collectstatic --clear
- 🟨: تمرین تنظیم Manifest
- 🟥: cache-busting با hash؛ ⭐ هرگز static را از Django در prod سرو نکنید

### جلسه ۴۲ — Logging
- 🟦: log levels، handlers، formatters، loggers، RotatingFileHandler
- 🟩: LOGGING dict، logger.info/warning/error در ویو
- 🟨: تمرین library logger
- 🟥: structured logging برای prod؛ ⭐ sensitive data هرگز log نشود

### جلسه ۴۳ — بهینه‌سازی دیتابیس
- 🟦: index، composite index، explain()، raw SQL
- 🟩: db_index، Meta.indexes، select_related/prefetch در عمل
- 🟨: تمرین composite index
- 🟥: index trade-off (read vs write)؛ ⭐ index روی ستون‌های WHERE/ORDER BY

### جلسه ۴۴ — تست پیشرفته
- 🟦: APITestCase، JWT در تست، factory_boy
- 🟩: BookAPITest با token، BookFactory با Faker
- 🟨: تمرین factory
- 🟥: test isolation، setUpTestData؛ ⭐ factory برای دادهٔ پیچیده

### جلسه ۴۵ — API Versioning و Throttling
- 🟦: versioning strategies (URL/header/query)، throttle scopes
- 🟩: /api/v1/ vs /api/v2/، scoped throttle روی login
- 🟨: تمرین throttle login
- 🟥: backward compatibility؛ ⭐ version در URL ساده‌ترین راه

### جلسه ۴۶ — Realtime Chat با Channels
- 🟦: consumer class، group، channel_layer، database_sync_to_async
- 🟩: ChatConsumer با auth، room_name از URL، typing indicator
- 🟨: تمرین typing indicator
- 🟥: async mental model؛ ⭐ connection lifecycle (connect/receive/disconnect)

### جلسه ۴۷ — Admin پیشرفته
- 🟦: inlines (Tabular/Stacked)، actions، custom admin views، list_display methods
- 🟩: TagInline، export_csv action، stats_view
- 🟨: تمرین ProfileInline + CSV
- 🟥: admin برای internal tools؛ ⭐ custom view با admin_site.admin_view

### جلسه ۴۸ — Cache عمیق
- 🟦: سطوح cache (template/view/low-level)، cacheops، ETag/conditional GET
- 🟩: get_or_set، cache fragment با کلید داینامیک، invalidate با signal
- 🟨: تمرین bestsellers + invalidate
- 🟥: cache stampede، dogpile effect؛ ⭐ versioned keys برای invalidate آسان

### جلسه ۴۹ — مرور جامع (Midterm)
- 🟦: نقشهٔ ذهنی کل دوره، چک‌لیست‌های self-assessment
- 🟥: mini-project «Notebook» — مدل+CRUD+auth+search+3 tests در ۲ ساعت
- 🟥: peer review؛ ⭐ هر دانش‌آموز کد دیگری را review کند

### جلسه ۵۰ — Capstone نهایی
- 🟥: پروژهٔ «کتابخانه دیجیتال» کامل: مدل‌ها+API+realtime+cache+celery+admin+tests+docker+i18n+security
- 🟩: جلسهٔ live-coding راهنما + office hours
- 🟥: دمو و دفاع پروژه؛ ⭐ این پروژه در resume/portfolio قرار می‌گیرد

---

## منابع پیشنهادی برای مدرس

- Official docs: docs.djangoproject.com (همیشه آخرین نسخه)
- تمرین: django-tutorial (polls app رسمی)
- پروژهٔ نمونه: books/restaurant/blog
- برای DRF: www.django-rest-framework.org
- برای Channels: channels.readthedocs.io
- برای Celery: docs.celeryq.dev
