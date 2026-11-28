# پاسخ تمرین‌های جلسه ۱۰۹

## آسان

**۱ و ۲.**
```groovy
plugins {
    id 'java'
}
```
```bash
gradlew build
```

## متوسط

**۳ و ۴.**
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.google.guava:guava:32.1.3-jre'
    testImplementation 'junit:junit:4.13.2'
}
```

## سخت

**۵ و ۶.**
```groovy
task runApp(type: JavaExec) {
    classpath = sourceSets.main.runtimeClasspath
    mainClass = 'com.example.MyApp'
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
```
