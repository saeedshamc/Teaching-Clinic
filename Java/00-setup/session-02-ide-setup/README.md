# جلسه ۰۲ — نصب و تنظیم IDE

## اهداف یادگیری
- IntelliJ IDEA Community و VS Code را برای Java نصب و تنظیم کنید
- پروژهٔ Java بسازید، Run/Debug کنید
- Maven و Gradle را به‌صورت اولیه بشناسید
- Android Studio و SDK را نصب کنید (پیش‌نیاز فاز ۶)

## پیش‌نیازها
- جلسهٔ ۰۱ کامل شده (JDK نصب و تست شده)

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### IntelliJ IDEA (توصیهٔ اصلی)
1. از jetbrains.com/community دانلود کنید (نسخهٔ Community رایگان و کافی است).
2. نصب → "New Project" → Java → SDK را JDK 17 انتخاب کنید.
3. ساختار پروژه:
   ```
   MyApp/
   ├── src/
   │   └── com/example/App.java   ← کد منبع (پکیج‌ها داخل src)
   └── out/                        ← خروجی کامپایل (خودکار)
   ```
4. **Run** (Shift+F10) و **Debug** (Shift+F9) — breakpoint با کلیک روی شمارهٔ خط.
5. Generate: `Alt+Insert` → constructor/getter/main و... (برای فاز ۲ مهم است).

### VS Code
1. VS Code نصب → Extension: **Extension Pack for Java** (Microsoft) — شامل Language Support, Debugger, Maven, Gradle, Test Runner.
2. `Ctrl+Shift+P` → "Java: Create Java Project" → No build tools → پروژهٔ خالی.
3. Run: `F5`، Debug: `Ctrl+F5`، Problems panel برای خطاها.

### Maven و Gradle (مقدمه)
هر دو **build tool** هستند: dependency، compile، test، package را خودکار می‌کنند.

- **Maven**: `pom.xml` با XML؛ lifecycle: `clean → compile → test → package → install`.
  ```bash
  mvn -v           # تست نصب
  mvn compile      # کامپایل
  mvn test         # تست
  ```
- **Gradle**: `build.gradle` با DSL (Groovy/Kotlin)؛ سریع‌تر و انعطاف‌تر.
  ```bash
  gradle -v
  ./gradlew build  # wrapper — نسخهٔ Gradle را در پروژه pin می‌کند
  ```

در فاز ۷ (جلسات ۱۰۸–۱۰۹) عمیق می‌شویم؛ فعلاً فقط بدانید کدام را پروژه‌تان استفاده می‌کند.

### Android Studio (پیش‌نیاز فاز ۶)
1. از developer.android.com/studio دانلود کنید (همراه JDK و SDK).
2. SDK Manager → Android SDK + Emulator نصب کنید.
3. AVD Manager → ساخت Virtual Device (مثلاً Pixel 6, API 34).

## کد مهم

`src/HelloIDE.java` — همان Hello World ولی با نکتهٔ پکیج (`package com.example;`) تا ساختار واقعی پروژه را ببینید. در IDE باید در `src/com/example/` باشد.

## خطاهای رایج

- `package com.example does not match expected package` → فایل باید در مسیر `src/com/example/` باشد نه ریشهٔ src.
- VS Code: "Java not found" → JDK نصب نیست یا `java.configuration.runtimes` در settings.json تنظیم نشده.
- Maven/Gradle روی PATH نیست → از IDE استفاده کنید یا مسیر bin را به PATH اضافه کنید.

## جمع‌بندی
- IntelliJ برای پروژه‌های بزرگ، VS Code سبک‌تر؛ هر دو JDK 17 را می‌شناسند.
- Maven/Gradle = build automation؛ جزئیات در فاز ۷.
- Android Studio برای فاز ۶ آماده شد.

**جلسهٔ بعد:** نصب Git و اتصال پروژه به GitHub.
