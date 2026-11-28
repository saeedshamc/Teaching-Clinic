# جلسه ۱۰۸ — Maven: Build Automation

## اهداف یادگیری
- `pom.xml` را برای project configuration بشناسید
- `mvn compile`, `mvn test`, `mvn package`, `mvn install` را بفهمید
- Dependencies و plugins را در pom.xml مدیریت کنید
- Maven lifecycle را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۱۰۷ (Networking Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Maven چیست؟

`Maven` — build automation و dependency management tool. `pom.xml` برای configuration:

```xml
<project>
    <groupId>com.example</groupId>
    <artifactId>myapp</artifactId>
    <version>1.0.0</version>

    <dependencies>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>32.1.3-jre</version>
        </dependency>
    </dependencies>
</project>
```

### Maven Commands

```bash
mvn compile          # compile source
mvn test             # run tests
mvn package          # compile + test + package (jar)
mvn install          # package + install to local repo
mvn clean            # delete target/
mvn dependency:tree  # show dependency tree
```

### Maven Lifecycle

```
validate → compile → test → package → verify → install → deploy
```

هر phase همهٔ phases قبل را اجرا می‌کند.

### pom.xml — Dependencies

```xml
<dependencies>
    <!-- Compile -->
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>32.1.3-jre</version>
        <scope>compile</scope>
    </dependency>

    <!-- Test -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Provided -->
    <dependency>
        <groupId>jakarta.servlet</groupId>
        <artifactId>jakarta.servlet-api</artifactId>
        <version>6.0.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Maven Project Structure

```
myapp/
├── pom.xml
├── src/
│   ├── main/java/     # production code
│   ├── main/resources/ # config files
│   └── test/java/     # test code
└── target/            # build output
```

## کد مهم

`src/pom.xml` — Maven configuration.
`src/MyApp.java` — simple Java class.

## خطاهای رایج
- `groupId`/`artifactId`/`version` را فراموش کردن → `Invalid project`.
- `mvn` را بدون `pom.xml` استفاده کردن → `POM not found`.
- `dependency` version را بدون check کردن → conflicts.
- `target/` را در git commit کردن → build artifacts.

## جمع‌بندی
- Maven برای build automation — `pom.xml` configuration.
- `compile` → `test` → `package` → `install` lifecycle.
- Dependencies با `groupId`/`artifactId`/`version`/`scope`.

**جلسهٔ بعد:** Gradle — modern build tool.
