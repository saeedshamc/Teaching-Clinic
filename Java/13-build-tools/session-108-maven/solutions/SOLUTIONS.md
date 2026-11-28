# پاسخ تمرین‌های جلسه ۱۰۸

## آسان

**۱ و ۲.**
```xml
<project>
    <groupId>com.example</groupId>
    <artifactId>myapp</artifactId>
    <version>1.0.0</version>
</project>
```
```bash
mvn compile
```

## متوسط

**۳ و ۴.**
```xml
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>32.1.3-jre</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```
```bash
mvn package
```

## سخت

**۵ و ۶.**
```bash
mvn dependency:tree
```
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.11.0</version>
</plugin>
```
