# دورهٔ کامل Java — ۱۵۰ جلسه

دوره‌ای کامل، عملی و پروژه‌محور از نصب JDK تا میکروسرویس و پروژه‌های نهایی.
هر جلسه شامل: طرح درس (`README.md`)، کدهای کامل و اجراشدنی (`src/`)، تمرین (`exercises/`)، پاسخ تمرین (`solutions/`) و پروژهٔ کوچک (`project/`).

**نسخهٔ مرجع:** JDK 17 LTS برای اکثر جلسات؛ مباحث مدرن تا Java 21+ پوشش داده شده.

## فازها

| فاز | مبحث | جلسات | محدوده |
|---|---|---|---|
| 1 | راه‌اندازی + مبانی | ۱۵ | 01–15 |
| 2 | شی‌گرایی + کالکشن‌ها/جنریک | ۱۵ | 16–30 |
| 3 | Exception/IO/NIO + Concurrency | ۱۵ | 31–45 |
| 4 | Java مدرن + Legacy | ۱۵ | 46–60 |
| 5 | دسکتاپ (AWT/Swing/JavaFX/SWT) | ۱۵ | 61–75 |
| 6 | اندروید (Legacy Views + Jetpack) | ۲۰ | 76–95 |
| 7 | دیتابیس/JDBC + شبکه + ابزار build | ۱۵ | 96–110 |
| 8 | وب‌بک‌اند (Servlet تا Spring Boot) | ۱۴ | 111–124 |
| 9 | تست + Design Pattern + JVM Internals | ۱۵ | 125–139 |
| 10 | امنیت + میکروسرویس/DevOps + Capstone | ۱۱ | 140–150 |

## فاز ۱ — Setup و مبانی (جلسات 01–15)

### 00-setup (جلسات 01–03)
- `01-jdk-install` — JDK/JRE/JVM، نصب روی Windows/Linux/macOS، JAVA_HOME و PATH، اولین برنامه با javac/java
- `02-ide-setup` — IntelliJ IDEA و VS Code، پروژه، Run/Debug، Maven/Gradle، Android Studio
- `03-git-github` — نصب Git، user.name/email، ریپو و push به GitHub

### 01-core-fundamentals (جلسات 04–15)
- `04-syntax-variables` — ساختار فایل، پکیج، import، متغیر و نام‌گذاری
- `05-primitive-types-operators` — تایپ‌های primitive، عملگرهای حسابی/مقایسه/منطقی/بیتی
- `06-control-flow` — if/else، switch (کلاسیک و expression)، break/continue
- `07-loops` — for، while، do-while، nested loops، الگوهای رایج
- `08-arrays` — آرایهٔ یک‌بعدی و چندبعدی، Arrays utility، foreach
- `09-strings` — String immutable، StringBuilder/StringBuffer، متدهای رایج
- `10-methods` — تعریف متد، void/return، overloading، scope
- `11-console-io` — System.out، Scanner برای ورودی، printf
- `12-casting-conversion` — implicit/explicit cast، boxing/unboxing، parse
- `13-varargs-main-args` — varargs و آرگومان‌های command line
- `14-math-common-classes` — Math، Random، BigInteger/BigDecimal، Date/Calendar (معرفی)
- `15-core-capstone` — مینی‌پروژه: ماشین‌حساب نمرات دانش‌آموز

## وضعیت نهایی دوره

همهٔ **۱۵۰ جلسه** در ۱۰ فاز انجام شد و `PROGRESS.md` به‌روزرسانی شد:

- **فاز ۱ (۱–۱۵):** Setup + Core Fundamentals ✅
- **فاز ۲ (۱۶–۳۰):** OOP + Collections/Generics ✅
- **فاز ۳ (۳۱–۴۵):** Exceptions/IO/NIO + Concurrency ✅
- **فاز ۴ (۴۶–۶۰):** Modern Java + Legacy Java ✅
- **فاز ۵ (۶۱–۷۵):** Desktop — AWT/Swing، JavaFX، SWT ✅
- **فاز ۶ (۷۶–۹۵):** Android — Legacy Views، Jetpack، Advanced ✅
- **فاز ۷ (۹۶–۱۱۰):** Databases/JDBC، Networking، Build Tools ✅
- **فاز ۸ (۱۱۱–۱۲۴):** Web Backend — Servlet تا Spring Boot ✅
- **فاز ۹ (۱۲۵–۱۳۹):** Testing + Design Patterns + JVM Internals ✅
- **فاز ۱۰ (۱۴۰–۱۵۰):** Security/Crypto + DevOps + Capstone ✅

هر جلسه شامل `README.md` (طرح درس)، `src/`، `exercises/`، `solutions/` و `project/` است.

جزئیات کامل هر جلسه در PROGRESS.md و پوشهٔ مربوطه است.
