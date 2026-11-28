# جلسه ۱۰۹ — Gradle: Modern Build Tool

## اهداف یادگیری
- `build.gradle` را برای project configuration بشناسید
- `tasks`, `dependencies`, `plugins` را در Gradle بفهمید
- `gradlew build`, `gradlew test`, `gradlew clean` را به‌کار ببرید
- Maven vs Gradle را تشخیص دهید

## پیش‌نیازها
- جلسهٔ ۱۰۸ (Maven)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Gradle چیست؟

`Gradle` — modern build automation tool. `build.gradle` برای configuration (Groovy یا Kotlin DSL):

```groovy
// build.gradle (Groovy DSL)
plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.google.guava:guava:32.1.3-jre'
    testImplementation 'junit:junit:4.13.2'
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
```

### Gradle Commands

```bash
gradlew build          # compile + test + package
gradlew test           # run tests
gradlew clean          # delete build/
gradlew run            # run application
gradlew dependencies   # show dependencies
gradlew tasks          # list available tasks
```

### build.gradle — Dependencies

```groovy
dependencies {
    // Compile
    implementation 'com.google.guava:guava:32.1.3-jre'

    // Test
    testImplementation 'junit:junit:4.13.2'

    // Provided (not in final jar)
    compileOnly 'jakarta.servlet:jakarta.servlet-api:6.0.0'

    // Runtime only
    runtimeOnly 'mysql:mysql-connector-java:8.0.33'
}
```

### Gradle Project Structure

```
myapp/
├── build.gradle
├── settings.gradle
├── gradlew / gradlew.bat
├── src/
│   ├── main/java/
│   ├── main/resources/
│   └── test/java/
└── build/             # build output
```

### Custom Tasks

```groovy
task copyJar(type: Copy) {
    from configurations.runtimeClasspath
    into 'lib'
}

task runApp(type: JavaExec) {
    classpath = sourceSets.main.runtimeClasspath
    mainClass = 'com.example.MyApp'
}
```

## کد مهم

`src/build.gradle` — Gradle configuration.
`src/settings.gradle` — project settings.
`src/MyApp.java` — simple Java class.

## خطاهای رایج
- `gradlew` را بدون `gradlew.bat` روی Windows استفاده کردن → `command not found`.
- `repositories` را فراموش کردن → `Could not resolve dependency`.
- `plugins` را بدون `id` استفاده کردن → `Plugin not found`.
- `build/` را در git commit کردن → build artifacts.

## جمع‌بندی
- Gradle برای modern builds — `build.gradle` configuration.
- `implementation`/`testImplementation`/`compileOnly`/`runtimeOnly` scopes.
- `gradlew` wrapper برای consistent builds.
- Custom tasks برای automation.

**جلسهٔ بعد:** Build Capstone.
