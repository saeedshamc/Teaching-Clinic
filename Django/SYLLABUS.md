# سیلابس دوره Django — ۵۰ جلسه (هر جلسه ~۲ ساعت درس)

هر جلسه: `README.md` با درس کامل + کدهای کامنت‌گذاری‌شده + تمرین + جواب تمرین.
پروژه‌های عملی در `projects/project-XX/` — یکی به‌ازای هر ۵ جلسه.

## بخش اول — مبانی (جلسات ۱–۱۰)

| # | جلسه | موضوع |
|---|------|--------|
| 01 | intro-setup | Django چیست + MTM + نصب + اولین پروژه |
| 02 | project-structure | ساختار پروژه، manage.py، settings، urls، flow درخواست |
| 03 | models-basics | مدل، migration، makemigrations/migrate، __str__ |
| 04 | model-fields | انواع فیلد، choices، validators، Meta، index |
| 05 | relations | ForeignKey، OneToOne، ManyToMany، related_name، on_delete |
| 06 | queries-orm | QuerySet، lookupها، filter/get، aggregate/annotate |
| 07 | admin | ModelAdmin، list_display/filter/search، اکشن سفارشی |
| 08 | urls-views | converterها، ویو تابعی، JsonResponse، reverse |
| 09 | templates | extends/block، if/for، url tag، include، filters |
| 10 | static-media | static files، upload_to، MEDIA_ROOT، collectstatic |

**پروژه ۱** (sessions ۱–۵): اپ «کتابخانه» — مدل Author/Book/Tag با روابط کامل
**پروژه ۲** (sessions ۶–۱۰): کوئری، ادمین، ویو و قالب کامل برای همان اپ

## بخش دوم — وب کامل (جلسات ۱۱–۲۰)

| # | جلسه | موضوع |
|---|------|--------|
| 11 | forms | Form vs ModelForm، validation، widgets، clean() |
| 12 | class-based-views | TemplateView/List/Detail/Create/Update/Delete |
| 13 | auth-users | login/logout، login_required، User model، permission |
| 14 | sessions-messages | request.session، messages framework |
| 15 | middleware | ساخت middleware سفارشی، ترتیب MIDDLEWARE |
| 16 | cbv-advanced | mixinها، LoginRequired/Permission، CRUD کامل |
| 17 | signals | pre/post_save/delete، apps.py.ready |
| 18 | testing | TestCase، Client، assertContains، setUpTestData |
| 19 | performance | N+1، select_related/prefetch، pagination، bulk |
| 20 | capstone | پروژهٔ نهایی: اپ کتابخانه کامل (مرور همهٔ مباحث) |

**پروژه ۳** (sessions ۱۱–۱۵): فرم + CBV + auth + session برای اپ کتابخانه
**پروژه ۴** (sessions ۱۶–۲۰): CRUD کامل + تست + بهینه‌سازی + جمع‌بندی

## بخش سوم — پیشرفته (جلسات ۲۱–۳۰)

| # | جلسه | موضوع |
|---|------|--------|
| 21 | custom-managers | Manager سفارشی، QuerySet سفارشی، from_queryset |
| 22 | abstract-models | AbstractModel، proxy model، multi-table inheritance |
| 23 | formsets | formset، modelformset، inline formset |
| 24 | function-vs-cbv | FBV/CBV، View class، dispatch |
| 25 | search-filter | Q objects، django-filter، full-text search |
| 26 | drf-intro | REST API، serializers، APIView |
| 27 | drf-viewsets | ModelViewSet، router، actions سفارشی |
| 28 | drf-auth-perms | Token/JWT، IsAuthenticated، permission classes |
| 29 | drf-advanced | pagination، filtering، throttling در DRF |
| 30 | jwt-auth | SimpleJWT، refresh token، محافظت routeها |

**پروژه ۵** (sessions ۲۱–۲۵): manager + abstract + formset + جستجوی پیشرفته
**پروژه ۶** (sessions ۲۶–۳۰): REST API کامل برای اپ کتابخانه با JWT

## بخش چهارم — زیرساخت و production (جلسات ۳۱–۴۰)

| # | جلسه | موضوع |
|---|------|--------|
| 31 | caching | cache framework، low-level cache، per-view cache |
| 32 | celery-intro | task queue، Celery + Redis، @shared_task |
| 33 | celery-beat | تسک‌های دوره‌ای، crontab schedule |
| 34 | channels-ws | Django Channels، WebSocket intro، consumer |
| 35 | signals-advanced | audit log، m2m_changed، signal قوی/ضعیف |
| 36 | template-tags | simple_tag، inclusion_tag، custom filter |
| 37 | i18n-l10n | gettext، LocaleMiddleware، ترجمهٔ قالب |
| 38 | security | CSRF، XSS، clickjacking، secure headers، SECRET_KEY |
| 39 | deployment | gunicorn، nginx، whitenoise، ALLOWED_HOSTS |
| 40 | docker-django | Dockerfile، docker-compose، multi-stage |

**پروژه ۷** (sessions ۳۱–۳۵): کش + Celery + WebSocket + audit log
**پروژه ۸** (sessions ۳۶–۴۰): i18n + امنیت + دیپلوی واقعی با Docker

## بخش پنجم — حرفه‌ای (جلسات ۴۱–۵۰)

| # | جلسه | موضوع |
|---|------|--------|
| 41 | static-prod | collectstatic، ManifestStaticFilesStorage، CDN |
| 42 | logging | LOGGING config، فایل/ایمیل handler، log levels |
| 43 | db-optimization | index، raw SQL، migrations perf، bulk در prod |
| 44 | testing-advanced | APIClient، factory_boy، coverage |
| 45 | api-versioning | versioning، throttling، schema documentation |
| 46 | realtime-chat | Channels کامل + auth + room/group |
| 47 | admin-advanced | InlineModelAdmin، dashboard، custom views |
| 48 | cache-deep | cacheops، template fragment caching، ETag |
| 49 | review-midterm | مرور جامع + رفع اشکال + mini-project مرور |
| 50 | capstone-advanced | پروژهٔ نهایی پیشرفته: REST API + realtime + deploy |

**پروژه ۹** (sessions ۴۱–۴۵): static prod + logging + تست پیشرفته + API v2
**پروژه ۱۰** (sessions ۴۶–۵۰): چت realtime + ادمین پیشرفته + capstone نهایی
