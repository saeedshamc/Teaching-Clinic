# جلسه ۰۱ — نصب و راه‌اندازی JDK

## اهداف یادگیری
- فرق JDK / JRE / JVM را بفهمید
- JDK را روی Windows / Linux / macOS نصب کنید
- `JAVA_HOME` و `PATH` را تنظیم کنید
- اولین برنامهٔ Java را با `javac` و `java` کامپایل و اجرا کنید

## پیش‌نیازها
- دسترسی به اینترنت و دسترسی administrator (برای تنظیم متغیر محیطی)

## مدت زمان پیشنهادی
۶۰–۹۰ دقیقه

## درس

### JDK، JRE، JVM

- **JVM** (Java Virtual Machine): ماشین مجازی که بایت‌کد `.class` را اجرا می‌کند.
- **JRE** (Java Runtime Environment): JVM + کتابخانه‌های استاندارد — برای *اجرای* برنامه.
- **JDK** (Java Development Kit): JRE + کامپایلر `javac` و ابزارها — برای *توسعه* لازم است.

از Java 9 به بعد JRE دیگر جداگانه عرضه نمی‌شود؛ فقط JDK نصب کنید.

### نسخه‌های LTS
نسخه‌های Long-Term Support پشتیبانی طولانی دارند: 8، 11، 17، 21. برای این دوره **JDK 17 LTS** مرجع است (روی سیستم فعلی `javac 17.0.12` نصب است).

### نصب روی Windows
1. به adoptium.net (Eclipse Temurin) بروید → JDK 17 → Installer ویندوز را دانلود کنید.
2. نصب کنید و گزینهٔ **Set JAVA_HOME** و **Add to PATH** را فعال کنید.
3. اگر دستی لازم شد:
   ```
   setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot"
   setx PATH "%PATH%;%JAVA_HOME%\bin"
   ```

### نصب روی Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-17-jdk
# یا برای نسخهٔ جدیدتر: sudo apt install openjdk-21-jdk
java -version && javac -version
```

### نصب روی macOS
```bash
brew install openjdk@17
sudo ln -sfn $(brew --prefix)/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk
# یا از oracle.com دانلود کنید
```

### تست نصب
```bash
java -version    # باید نسخهٔ 17.x را نشان دهد
javac -version   # باید 17.x باشد
```

### اولین برنامه — بدون IDE
فایل `src/HelloJava.java` را بسازید، سپس:
```bash
javac src/HelloJava.java   # کامپایل → HelloJava.class ساخته می‌شود
java -cp src HelloJava    # اجرا
```
نکته: نام فایل public باید با نام کلاس یکی باشد و `java` به *نام کلاس* (نه فایل) اشاره می‌کند.

## کد مهم

`src/HelloJava.java` — ساده‌ترین برنامهٔ جاوا. خط‌به‌خط در کامنت‌ها توضیح داده شده.

## خطاهای رایج و نکات حرفه‌ای

- `'javac' is not recognized` → PATH تنظیم نشده؛ ترمینال را ببندید و دوباره باز کنید (setx نیاز به ترمینال جدید دارد).
- `Error: Could not find or load main class HelloJava` → یا کلاس کامپایل نشده یا مسیر `-cp` اشتباه است.
- `class HelloJava is public, should be declared in a file named HelloJava.java` → نام فایل و کلاس باید یکی باشند.
- دو JDK روی سیستم → `JAVA_HOME` را به نسخهٔ درست اشاره دهید.

## جمع‌بندی
- JDK = ابزار توسعه، JRE = اجرا، JVM = موتور اجرا.
- JDK 17 LTS نصب و تست شد.
- چرخهٔ `javac` → `.class` → `java` را بدون IDE انجام دادید.

**جلسهٔ بعد:** نصب IDE (IntelliJ IDEA و VS Code) و پروژه‌سازی.
