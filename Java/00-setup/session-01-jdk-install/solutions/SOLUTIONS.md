# پاسخ تمرین‌های جلسه ۰۱

## آسان

**۱.** در ترمینال:
```bash
java -version
javac -version
```
خروجی باید چیزی شبیه `openjdk version "17.0.x"` باشد.

**۲.**
```bash
# ویندوز
where java
# لینوکس/macOS
which java
```
مسیر معمول: `C:\Program Files\...\jdk-17\bin\java.exe` یا `/usr/lib/jvm/...`.

## متوسط

**۳.** فایل `MyFirst.java`:
```java
public class MyFirst {
    public static void main(String[] args) {
        System.out.println("نام من: ...");
    }
}
```
```bash
javac MyFirst.java
java MyFirst
```

**۴.** اگر فایل `Wrong.java` باشد و کلاس `public class MyFirst` → خطای `class MyFirst is public, should be declared in a file named MyFirst.java`. رفع: نام فایل را `MyFirst.java` کنید.

## سخت

**۵.** در ویندوز:
```cmd
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%
java -version
```
برای ماندگار: `setx JAVA_HOME "..."` و ترمینال جدید باز کنید.
