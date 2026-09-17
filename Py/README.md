# 🐍 دوره آموزشی کامل Python — از صفر تا حرفه‌ای

> تمام توضیحات به زبان فارسی روان نوشته شده‌اند.  
> این دوره برای کسانی طراحی شده که هیچ پیش‌زمینه‌ای در برنامه‌نویسی ندارند.

---

## معرفی دوره

Python یکی از محبوب‌ترین زبان‌های برنامه‌نویسی دنیاست. از ساخت وب‌سایت و تحلیل داده گرفته تا هوش مصنوعی و اتوماسیون، Python در همه‌جا حضور دارد. سینتکس (نحو) ساده و خوانای آن، یادگیری را برای مبتدیان بسیار راحت‌تر می‌کند.

این دوره شامل **۵۰ جلسه** است که به‌صورت پیشرونده (Progressive) طراحی شده‌اند — هر جلسه روی پایه‌های جلسه قبلی می‌سازد.

> ⏱️ **هر جلسه برای ۱.۵ ساعت (۹۰ دقیقه) تدریس طراحی شده است** — شامل برنامه زمانی، مثال‌های عملی، تمرین کلاسی و تکلیف.

---

## مخاطب هدف

- کسانی که هیچ تجربه‌ای در برنامه‌نویسی ندارند
- کسانی که زبان دیگری بلدند و می‌خواهند Python یاد بگیرند
- دانشجویان و علاقه‌مندانی که به داده، اتوماسیون یا توسعه علاقه دارند

---

## پیش‌نیازها

### نصب Python

نسخه پیشنهادی: **Python 3.10 یا بالاتر**

1. به سایت رسمی بروید: [python.org/downloads](https://www.python.org/downloads/)
2. آخرین نسخه را دانلود و نصب کنید
3. **مهم برای ویندوز:** هنگام نصب، گزینه «Add Python to PATH» را حتماً تیک بزنید
4. برای تأیید نصب، این دستور را در ترمینال بزنید:

```bash
python --version
# یا در Mac/Linux:
python3 --version
```

### ابزارهای لازم

| ابزار | توضیح | نصب |
|-------|-------|-----|
| Python 3.10+ | زبان اصلی | [python.org](https://python.org) |
| pip | مدیریت پکیج (با Python نصب می‌شود) | داخلی |
| venv | ساخت محیط مجازی (داخلی Python) | داخلی |
| VS Code | ویرایشگر کد پیشنهادی | [code.visualstudio.com](https://code.visualstudio.com) |

### افزونه پیشنهادی VS Code
- **Python** (از Microsoft) — برای رنگ‌بندی کد و دیباگ

---

## نحوه اجرای اسکریپت‌ها

```bash
# روش کلی (Mac/Linux)
python3 session-01-intro/examples/01_hello_world.py

# ویندوز
python session-01-intro\examples\01_hello_world.py
```

---

## فهرست جلسات

### بخش اول: مبانی (جلسات ۱–۱۲)

| # | عنوان | موضوعات اصلی |
|---|-------|-------------|
| [۱](./session-01-intro/) | مقدمه و نصب | آشنایی با Python، venv، اولین اسکریپت |
| [۲](./session-02-variables/) | متغیرها و انواع داده | int, float, str, bool، ورودی/خروجی |
| [۳](./session-03-operators-conditions/) | عملگرها و شرط‌ها | if/elif/else، عملگرهای مقایسه‌ای و منطقی |
| [۴](./session-04-loops/) | حلقه‌ها و کنترل جریان | for, while, break, continue |
| [۵](./session-05-data-structures/) | ساختارهای داده | list, tuple, dict, set |
| [۶](./session-06-functions/) | توابع | def، پارامترها، مقدار بازگشتی، scope |
| [۷](./session-07-strings-files/) | رشته‌ها و فایل‌ها | string methods، خواندن/نوشتن فایل |
| [۸](./session-08-error-handling/) | مدیریت خطا | try/except، انواع Exception، دیباگ |
| [۹](./session-09-oop/) | برنامه‌نویسی شیءگرا | class، object، وراثت |
| [۱۰](./session-10-modules/) | ماژول‌ها و کتابخانه‌ها | import، کتابخانه استاندارد |
| [۱۱](./session-11-external-libs/) | کتابخانه‌های بیرونی | requests، pandas، pip |
| [۱۲](./session-12-final-project/) | پروژه نهایی | ابزار خط فرمان کاربردی |

### بخش دوم: موضوعات پیشرفته (جلسات ۱۳–۲۰)

| # | عنوان | موضوعات اصلی |
|---|-------|-------------|
| [۱۳](./session-13-comprehensions/) | Comprehensionها | list/dict comprehension |
| [۱۴](./session-14-decorators/) | Decoratorها | decorator ساده و کاربردها |
| [۱۵](./session-15-iterators-generators/) | Iterator و Generator | iter، next، yield |
| [۱۶](./session-16-virtualenv-packaging/) | محیط مجازی و بسته‌بندی | venv، pip، requirements.txt |
| [۱۷](./session-17-asyncio/) | AsyncIO | async، await، gather |
| [۱۸](./session-18-testing/) | تست‌نویسی | assert، unittest |
| [۱۹](./session-19-typing/) | Type Hints | annotation، list[str]، dict |
| [۲۰](./session-20-advanced-project/) | پروژه پیشرفته نهایی | Task Manager کامل |

### بخش سوم: ابزارها و استانداردها (جلسات ۲۱–۳۰)

| # | عنوان | موضوعات اصلی |
|---|-------|-------------|
| [۲۱](./session-21-context-managers/) | Context Managerها | with، contextlib |
| [۲۲](./session-22-lambda-functional/) | Lambda و توابع تابعی | lambda، map، filter |
| [۲۳](./session-23-regex/) | عبارات منظم | re، search، findall، sub |
| [۲۴](./session-24-json/) | کار با JSON | dumps، loads، فایل JSON |
| [۲۵](./session-25-csv/) | کار با CSV | DictReader، DictWriter |
| [۲۶](./session-26-pathlib/) | Pathlib | Path، وجود فایل، خواندن/نوشتن |
| [۲۷](./session-27-logging/) | لاگ‌نویسی | logging، سطوح لاگ |
| [۲۸](./session-28-dataclasses/) | Dataclassها | @dataclass، فیلدها |
| [۲۹](./session-29-enums/) | Enumها | Enum، مقادیر ثابت |
| [۳۰](./session-30-collections/) | Collections | Counter، defaultdict، namedtuple |

### بخش چهارم: سیستم، شبکه و معماری (جلسات ۳۱–۴۰)

| # | عنوان | موضوعات اصلی |
|---|-------|-------------|
| [۳۱](./session-31-argparse/) | argparse | CLI، آرگومان خط فرمان |
| [۳۲](./session-32-sqlite/) | SQLite | جدول، insert، select |
| [۳۳](./session-33-threading/) | Threading | Thread، start، join |
| [۳۴](./session-34-concurrent-futures/) | Concurrent Futures | ThreadPoolExecutor |
| [۳۵](./session-35-http-client/) | کلاینت HTTP | درخواست، status، پاسخ |
| [۳۶](./session-36-html-parsing/) | پردازش HTML | HTMLParser، استخراج تگ |
| [۳۷](./session-37-env-config/) | پیکربندی محیطی | os.environ، تنظیمات |
| [۳۸](./session-38-abc-interfaces/) | کلاس‌های انتزاعی | ABC، abstractmethod |
| [۳۹](./session-39-properties/) | Property | getter، setter، اعتبارسنجی |
| [۴۰](./session-40-packaging-pyproject/) | بسته‌بندی | pyproject.toml، متادیتا |

### بخش پنجم: کیفیت، امنیت و پروژه نهایی (جلسات ۴۱–۵۰)

| # | عنوان | موضوعات اصلی |
|---|-------|-------------|
| [۴۱](./session-41-pytest-intro/) | pytest | تست با assert، سبک pytest |
| [۴۲](./session-42-mocking/) | Mocking | Mock، شبیه‌سازی وابستگی |
| [۴۳](./session-43-debugging-pdb/) | دیباگ | breakpoint، بررسی متغیر |
| [۴۴](./session-44-performance-timeit/) | عملکرد | timeit، مقایسه پیاده‌سازی |
| [۴۵](./session-45-design-patterns/) | الگوهای طراحی | Singleton، Factory |
| [۴۶](./session-46-protocols-typing/) | Protocol | قرارداد ساختاری تایپ |
| [۴۷](./session-47-security-basics/) | مبانی امنیت | اعتبارسنجی، کوئری امن |
| [۴۸](./session-48-documentation/) | مستندسازی | docstring، help |
| [۴۹](./session-49-project-structure/) | ساختار پروژه | src، tests، README |
| [۵۰](./session-50-capstone-project/) | پروژه نهایی جامع | Task Service با JSON |

---

## ساختار هر جلسه

هر جلسه برای **۱.۵ ساعت تدریس** طراحی شده و شامل موارد زیر است:
- **README.md**: برنامه ۹۰ دقیقه‌ای، اهداف یادگیری، توضیحات مفهومی و خط‌به‌خط کدها (۲۰۰+ خط)
- **examples/**: ۴ فایل کد قابل اجرا با کامنت‌های فارسی
- **exercises/question.md**: تمرین‌های کلاسی و تکلیف خانه
- **exercises/exercise_01.py** و **exercise_02.py**: پاسخ/نمونه تمرین‌ها

---

## نقشه راه پیشنهادی

### یادگیری سریع (۱۲ هفته)
- **هفته ۱–۲:** جلسه‌های ۱ تا ۸
- **هفته ۳–۴:** جلسه‌های ۹ تا ۱۶
- **هفته ۵–۶:** جلسه‌های ۱۷ تا ۲۴
- **هفته ۷–۸:** جلسه‌های ۲۵ تا ۳۲
- **هفته ۹–۱۰:** جلسه‌های ۳۳ تا ۴۰
- **هفته ۱۱–۱۲:** جلسه‌های ۴۱ تا ۵۰

### یادگیری با دقت بیشتر (۲۰ هفته)
- هر هفته حدود ۲ تا ۳ جلسه
- بعد از هر بخش (۱۲، ۲۰، ۳۰، ۴۰) یک مرور کوتاه انجام دهید
- جلسه ۵۰ را به‌عنوان پروژه پایانی جدی بگیرید

### توصیه‌های یادگیری
1. فقط مطالعه نکنید — کدها را خودتان تایپ کنید
2. تمرین‌های هر جلسه را حل کنید قبل از رفتن به جلسه بعدی
3. اشکالی ندارد که کد اشتباه بنویسید — خطاها بهترین معلم هستند
4. پس از هر جلسه، یک پروژه کوچک مرتبط با موضوع بسازید

---

*این دوره با ❤️ به فارسی نوشته شده تا یادگیری Python برای همه فارسی‌زبانان راحت‌تر باشد.*
